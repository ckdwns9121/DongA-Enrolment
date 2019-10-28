package com.example.changjun.myapplication.util;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.activity.BasketActivity;

import java.util.ArrayList;
import java.util.List;

public class CourseListAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<Course> courseList;
    public Department information;

    private Schedule schedule;
    private List<Integer> courseIDlsit;

    public CourseListAdapter(Context context, ArrayList<Course> courseList) {
        this.context = context;
        this.courseList = courseList;
        schedule = Schedule.getInstance();
        courseIDlsit = new ArrayList<Integer>();
        //new BackgroundTask().excute();
    }

    @Override
    public int getCount() {
        return courseList.size();
    }

    @Override
    public Object getItem(int position) {
        return courseList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view =  View.inflate(context, R.layout.course,null);
        information= Department.getInstace();

        TextView courseName = (TextView) view.findViewById(R.id.courseName);
        TextView courseProfessor = (TextView) view.findViewById(R.id.courseProfessor);
        TextView courseGrade = (TextView) view.findViewById(R.id.courseGrade);
        TextView courseNumber = (TextView) view.findViewById(R.id.courseNumber);
        TextView courseDivision = (TextView) view.findViewById(R.id.courseDivision);
        TextView courseClass = (TextView) view.findViewById(R.id.courseClass);
        TextView courseCredit = (TextView) view.findViewById(R.id.courseCredit);
        TextView courseTime = (TextView) view.findViewById(R.id.courseTime);
        TextView courseLimit = (TextView) view.findViewById(R.id.courseLimit);
        final Button addButton = (Button) view.findViewById(R.id.addButton);

        courseDivision.setText(courseList.get(position).getCourseDivision());
        courseName.setText(courseList.get(position).getCourseName());
        courseProfessor.setText(courseList.get(position).getCourseProfessor());
        courseGrade.setText("학년: "+courseList.get(position).getCourseGrade());
        courseClass.setText("분반: "+courseList.get(position).getCourseClass());
        courseNumber.setText(courseList.get(position).getCourseNumber());
        courseCredit.setText("학점: "+courseList.get(position).getCourseCredit());
        courseTime.setText("시간: "+courseList.get(position).getCourseTime());
        courseLimit.setText("신청현황: " + courseList.get(position).getCourseAttendPerson() + "/" + courseList.get(position).getCourseLimitPerson());

        if(TextUtils.isEmpty(courseList.get(position).getCourseAttendPerson())){
            Log.e("여긴 왜들어옴?" ,courseList.get(position).getCourseName());
            courseList.get(position).setCourseAttendPerson("0");
        }
        if(TextUtils.isEmpty(courseList.get(position).getCourseLimitPerson())){
            Log.e("여긴 또 왜들어오냐" ,courseList.get(position).getCourseName());
            courseList.get(position).setCourseLimitPerson("없음");
        }
        else if( (!courseList.get(position).getCourseLimitPerson().equals(null) && !courseList.get(position).getCourseAttendPerson().equals(null))){
            Log.e("이거 왜터지노" ,courseList.get(position).getCourseName() + courseList.get(position).getCourseAttendPerson() + courseList.get(position).getCourseLimitPerson());
            if(courseList.get(position).getCourseLimitPerson().equals("없음")){
                Log.e("이래도 터지나?", "없음 걸러냇는데도?");
            }
            else{
                int AttendPerson = Integer.parseInt(courseList.get(position).getCourseAttendPerson());
                Log.e("어텐드는 나옴?",AttendPerson+"나오네");
                int LimitPerson = Integer.parseInt(courseList.get(position).getCourseLimitPerson());
                if (AttendPerson>LimitPerson) {
                    String strColor = "#ffff4444";
                    courseLimit.setTextColor(Color.parseColor(strColor));
                } else {
                    String strColor = "#36C200";
                    courseLimit.setTextColor(Color.parseColor(strColor));
                }
            }

        }
        courseLimit.setText("신청현황: " + courseList.get(position).getCourseAttendPerson() + "/" + courseList.get(position).getCourseLimitPerson());

        for(int i=0; i<information.addList.size();i++){
            /* 과목코드와 분반이 같으면 같은과목으로 식별 */
            if(courseList.get(position).getCourseNumber().equals(information.addList.get(i).getCourseNumber()) && courseList.get(position).getCourseClass().equals(information.addList.get(i).getCourseClass())){
                String strColor = "#ffffbb33";
                addButton.setBackgroundColor(Color.parseColor(strColor));
                addButton.setText("완료");
                break;
            }
        }

//        int AttendPerson = Integer.parseInt(courseList.get(position).getCourseAttendPerson());
//        int LimitPesron = Integer.parseInt(courseList.get(position).getCourseLimitPerson());
//        double CompetitionRate= AttendPerson/(double)LimitPesron;
//        String temp = String.format("%1f",CompetitionRate);
//        courseCompetition.setText(temp+":1");
        view.setTag(courseList.get(position).getCourseID());

        addButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                /*중복된 과목 체크하기*/
                boolean chk = false;
                for(int i=0;i<information.addList.size();i++){
                    /* 과목코드와 분반이 다르고 과목명은 같을때 중복담기 제거 */
                    if(courseList.get(position).getCourseName().equals(information.addList.get(i).getCourseName())){
                        chk= true;
                        break;
                    }
                }
                if(chk)
                    Toast.makeText(v.getContext(), "중복된 강의내역이 있습니다.", Toast.LENGTH_SHORT).show();
                else {
                    if(schedule.overlapCheck(courseList.get(position).getCourseTime())) {
                        String strColor = "#ffffbb33";
                        Toast.makeText(v.getContext(), "강의가 추가되었습니다", Toast.LENGTH_SHORT).show();
                        addButton.setBackgroundColor(Color.parseColor(strColor));
                        addButton.setText("완료");
                        information.addList.add(courseList.get(position));
//                        SugangEnrollmentActivity.total+= Integer.parseInt(courseList.get(position).getCourseCredit());
                        BasketActivity.total+= Integer.parseInt(courseList.get(position).getCourseCredit());
                        schedule.addSchedule(courseList.get(position).getCourseTime(), courseList.get(position).getCourseName(), courseList.get(position).getCourseProfessor(), courseList.get(position).getCourseNumber(), courseList.get(position).getCourseClass());
                    }
                    else{
                        Toast.makeText(v.getContext(), "중복된 시간이 있습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                Log.v("들어와지나?", position+"번째 선택");
            }
        });
    return view;
    }
}

