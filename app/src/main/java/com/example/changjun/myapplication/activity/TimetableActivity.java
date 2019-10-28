package com.example.changjun.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.Course;
import com.example.changjun.myapplication.util.CourseListAdapter;
import com.example.changjun.myapplication.util.CustomProgress;
import com.example.changjun.myapplication.util.Department;
import com.example.changjun.myapplication.util.RequsetServer;
import com.example.changjun.myapplication.util.TimetableProgress;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

/*
수정할 부분
=> 각 단대 , 학과에 [] 이 들어가 있는데 substring으로 자르기
=> 스피너 글자크기 조절
=> 중복되는 값 처리하기
=> 리스트뷰에 버튼생성해서 시간 조회하기
 */
public class TimetableActivity extends Activity {


    private ListView courseListView; //강의 정보를 담을 리스트뷰
    private ArrayList<Course> courseList; // 강의정보가 담겨있는 배열
    private Course course; //임시로 생성해준 객체
    private CourseListAdapter courseListAdapter ; //리스트 뷰와 연동해줄 사용자 정의 어댑터
    private TimetableProgress timetableProgress ;

    private Spinner college1, department, major; //단과대,학과,전공 스피너
    private Button addButton; //선택과목추가 버튼
    private TextView titleTextView;
    public CustomProgress customProgress; //강의정보 파싱할때 쓸 프로그레스 바

    public ArrayList<String> collageList = new ArrayList<>(); //단과대명 (스피너랑 연결할 리스트)
    public ArrayList<String> dptList = new ArrayList<>(); //학과명 (스피너랑 연결할 리스트)
    public ArrayAdapter<String> collageAdapter, dptAdapter, subjectAdapter; //스피너 어뎁터
    public String ddlColl, ddlDpt, ddMajor; //각 스피너에 대한 value 값 저장 변수
    public String collageName, departmentName; // HashMap 에서 value 값을 찾을 키값(이름)
    public Department information; //강의 정보
    public RequsetServer requsetServer; //로그인 세션을 유지할 객체

    public String year;
    public int month;
    public String titleText;

    int number = 0;
    //여기 현재 날짜 넣어야함 아니면 RequsetServer 클래스에서 만들어도 댐
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        customProgress= new CustomProgress(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_timetable);
        titleTextView =(TextView) findViewById(R.id.titleTextview);
        titleTextView.setText(getTitleText(titleText));
        timetableProgress = new TimetableProgress(this);
        department = (Spinner) findViewById(R.id.spinner2);
        requsetServer = RequsetServer.getInstance();
        information = Department.getInstace(); //강의정보의 인스턴스를 그대로 들고와줍니다.

        /*단과대 값들 information.ddCollValue List 안에 저장되어있으니 그값을 들고와서 스피너를 셋팅합니다.*/
        for (int i = 0; i < information.ddCollValue.size(); i++) {
            collageList.add(information.ddCollValue.get(i).keySet().toString().substring(1,information.ddCollValue.get(i).keySet().toString().length() - 1));
        }

        /*커스텀 한 spinner를 셋팅해줍니다. */
        college1 = (Spinner) findViewById(R.id.spinner);
        collageAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item, collageList);
        college1.setAdapter(collageAdapter);
        if(collageList.size()==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(TimetableActivity.this);
            AlertDialog dialog = builder.setMessage("조회기간이 아닙니다.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
        }

        dptList.add("학부(과)을 선택해주세요");
        dptAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dptList);
        department.setAdapter(dptAdapter);

        courseListView = (ListView) findViewById(R.id.courseListView);
        courseListAdapter = new CourseListAdapter(this,information.courseList);

        /*로그인 성공시 담아뒀던 단과대 리스트를 어뎁터와 연결시켜줍니다.*/
        college1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*다른 단과대학이 선택됬을시 들고왔던 강의정보와 학과정보를 초기화 합니다.*/
                information.courseList.clear();
                dptList.clear();
                courseListView.setAdapter(courseListAdapter);

                /*스피너의 포지션이 0번째 ('대학을 선택해주시오') 가아니라면 선택한 단과대로 학과 정보를 불러와줍니다.*/
                if (position != 0) {
                    /* 세션이 유지된 객체의 파서 함수를 실행합니다 이때 파라메터는 단과대 하나입니다.*/
                    /* 이 파서는 학과 정보를 불러와줍니다*/
                    dptList.clear();
                    number=1;
                    collageName = parent.getItemAtPosition(position).toString();
                    ddlColl = information.ddCollValue.get(position).get(collageName); //대학키값으로 대학벨류값 뽑는 코드입니다.
                    new ParsingTask().execute(null, null, null);
                }
                else{
                    dptList.clear();
                    dptList.add("학부(과)을 선택해주세요");
                    dptAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dptList);
                    department.setAdapter(dptAdapter);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        /*선택한 학과정보로 학부 스피너를 셋팅해줍니다.*/
        department.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    /* 학과 정보가 선택 됬다면 강의정보를 파싱하는 파서를 실행해줍니다.*/
                    /* 이때 파라메터 값으로는 선택한 단과대 및 학과 입니다. */
                    departmentName = parent.getItemAtPosition(position).toString();
                    ddlDpt = information.ddDptValue.get(position).get(departmentName); //대학 이름으로 학과 벨류값 뽑아오기
                    number=2;
                    new ParsingTask().execute(null, null, null);
                    Log.e("알림","시간표들고오기");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }
    //검색버튼 미개발
    public void onClickSerchButton(View view) {
        requsetServer.doParse(null, null, null);
    }

    public void onClickMoveBaket(View view) {
        Intent intent = new Intent(TimetableActivity.this, BasketActivity.class);
        startActivity(intent);
    }

    /* 타이틀을 학기별로 설정해줍니다 */
    public String getTitleText(String titleText){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        year = CurYearFormat.format(date);
        month = Integer.parseInt(CurMonthFormat.format(date));
        /* 몇학기인지 ddlSmt 값 setting 해주기 */
        if (month <=1) titleText = year+"년 동계 강의시간표"; //동계
        else if (month <= 6) titleText = year+"년 1학기 강의시간표"; //1학기
        else if (month <= 7) titleText = year+"년 하계 강의시간표"; //하계
        else titleText = year+"년 2학기"; //2학기
        return titleText;
    }

    /*과목번호랑 코드가 같을때 시간을 합쳐줍니다 */
    public void isSame(ArrayList<Course> courseList){
        for(int i=0;i<courseList.size()-1;i++){
            if ((courseList.get(i).getCourseNumber().equals(courseList.get(i + 1).getCourseNumber())) && (courseList.get(i).getCourseClass().equals(courseList.get(i+1).getCourseClass())))
            {
                String now = courseList.get(i).getCourseTime();
                String next = courseList.get(i+1).getCourseTime();
                courseList.get(i).setCourseTime(now+"/"+next);
                courseList.remove(i+1);
            }
        }
    }

    private class ParsingTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            customProgress= new CustomProgress(TimetableActivity.this);
            customProgress.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            if(number==1){
                requsetServer.doParse(ddlColl);
            }
            else if(number==2){
                requsetServer.doParse(ddlColl, ddlDpt, null);
            }
            try {
                requsetServer.parseingThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                customProgress.dismiss();
                if(number==1) {
                    for (int i = 0; i < information.ddDptValue.size(); i++) {
                        dptList.add(information.ddDptValue.get(i).keySet().toString().substring(1, information.ddDptValue.get(i).keySet().toString().length() - 1));
                    }
                    /*커스텀한 spiner xml 파일을 어뎁터와 연동해줍니다 */
                    dptAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, dptList);
                    department.setAdapter(dptAdapter);
                }
                else {
                    if (information.courseList.isEmpty()){
                        Toast.makeText(TimetableActivity.this, "수강목록이 없습니다.", LENGTH_SHORT).show();
                        }
                    courseListView.setAdapter(courseListAdapter);
                }
                Log.e("조인","끝");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
