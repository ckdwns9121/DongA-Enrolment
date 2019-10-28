package com.example.changjun.myapplication.util;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.activity.BasketActivity;
import com.example.changjun.myapplication.activity.BasketFragment;

import java.util.ArrayList;

public class AddListAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<Course> addList;
    public Department information;
    private Schedule schedule;
    public AddListAdapter(Context context, ArrayList<Course> addList) {
        this.context = context;
        this.addList = addList;
        schedule= Schedule.getInstance();
}

    @Override
    public int getCount() {
        return addList.size();
    }

    @Override
    public Object getItem(int position) {
        return addList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view =  View.inflate(context, R.layout.addlist,null);
        information= Department.getInstace();
        TextView addName = (TextView) view.findViewById(R.id.addName);
        TextView addProfessor = (TextView) view.findViewById(R.id.addProfessor);
        TextView addGrade = (TextView) view.findViewById(R.id.addGrade);
        TextView addCredit = (TextView) view.findViewById(R.id.addCredit);
        TextView addDivision = (TextView) view.findViewById(R.id.addDivision);
        TextView addClass = (TextView) view.findViewById(R.id.addClass);
        TextView addNumber = (TextView) view.findViewById(R.id.addNumber);
        TextView addTime = (TextView) view.findViewById(R.id.addTime);

        addName.setText(addList.get(position).getCourseName());
        addProfessor.setText(addList.get(position).getCourseProfessor());
        addGrade.setText("학년: "+addList.get(position).getCourseGrade());
        addCredit.setText("학점: "+addList.get(position).getCourseCredit());
        addDivision.setText(addList.get(position).getCourseDivision());
        addClass.setText("분반: "+addList.get(position).getCourseClass());
        addNumber.setText(addList.get(position).getCourseNumber());
        addTime.setText("시간: "+addList.get(position).getCourseTime());
        view.setTag(addList.get(position).getCourseID());
        Button deleteButton = (Button) view.findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*다이얼로그랑 토스트 메시지 띄워야함 */
                /* 현재 과목을 삭제하시겠습니까? 라는 다이얼로그 띄우기
                 */
//                SugangEnrollmentActivity.total-=Integer.parseInt(information.addList.get(position).getCourseCredit());
//                SugangEnrollmentActivity.totalCredit.setText("등록한 학점은 총 " + SugangEnrollmentActivity.total +"학점 입니다.");

                BasketActivity.total-=Integer.parseInt(information.addList.get(position).getCourseCredit());
                BasketActivity.totalCredit.setText("등록한 학점은 총 " + BasketActivity.total +"학점 입니다.");
                schedule.deleteSchedule(information.addList.get(position).getCourseName());
                information.addList.remove(position);
                //시간표 삭제 추가.
//                SugangEnrollmentActivity.addListView.clearChoices();
//                SugangEnrollmentActivity.addListAdapter.notifyDataSetChanged();

                BasketFragment.addListView.clearChoices();
                BasketFragment.addListAdapter.notifyDataSetChanged();



                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                AlertDialog dialog =builder.setMessage("강의가 삭제되었습니다.")
                        .setPositiveButton("확인",null)
                        .create();
                dialog.show();
            }
        });
    return view;
    }
}

