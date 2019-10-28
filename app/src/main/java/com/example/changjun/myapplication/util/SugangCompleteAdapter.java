package com.example.changjun.myapplication.util;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.activity.ListActivity;

import java.util.ArrayList;

public class SugangCompleteAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<SugangComplete> sugangCompletes;
    public Department information;
    public RequsetServer requsetServer;
    public SugangComplete sugangComplete;
    public String curi;
    public String cls;
    public String curinm;

    public SugangCompleteAdapter(Context context, ArrayList<SugangComplete> sugangCompletes) {
        this.context = context;
        this.sugangCompletes = sugangCompletes;
    }

    @Override
    public int getCount() {
        return sugangCompletes.size();
    }

    @Override
    public Object getItem(int position) {
        return sugangCompletes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view =  View.inflate(context, R.layout.sugangcomplete,null);
        information= Department.getInstace();
        TextView completeName = (TextView) view.findViewById(R.id.completeName);
        TextView completeProfessor = (TextView) view.findViewById(R.id.completeProfessor);
        TextView completeWeekTime = (TextView) view.findViewById(R.id.completeWeekTime);
        TextView completeCredit = (TextView) view.findViewById(R.id.completeCredit);
        TextView completeDivision = (TextView) view.findViewById(R.id.completeDivision);
        TextView completeClass = (TextView) view.findViewById(R.id.completeClass);
        TextView completeNumber = (TextView) view.findViewById(R.id.completeNumber);
        TextView completeTime = (TextView) view.findViewById(R.id.completeTime);

        completeName.setText(sugangCompletes.get(position).getCourseName());
        completeProfessor.setText(sugangCompletes.get(position).getCourseProfessor());
        completeWeekTime.setText("주야: "+sugangCompletes.get(position).getCourseWeekOrNight());
        completeCredit.setText("학점: "+sugangCompletes.get(position).getCourseCredits());
        completeDivision.setText(sugangCompletes.get(position).getCourseDivision());
        completeClass.setText("분반: "+sugangCompletes.get(position).getCourseClass());
        completeNumber.setText(sugangCompletes.get(position).getCourseNumber());
        completeTime.setText("시간: "+sugangCompletes.get(position).getCourseTime());
        Button cancle = (Button) view.findViewById(R.id.cancelButton);
        cancle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /*다이얼로그랑 토스트 메시지 띄워야함 */
                /* 현재 과목을 삭제하시겠습니까? 라는 다이얼로그 띄우기
                 */
                curi = information.sugangCompletesList.get(position).getCourseNumber();
                cls = information.sugangCompletesList.get(position).getCourseClass();
                curinm = information.sugangCompletesList.get(position).getCourseName();
                information.sugangCompletesList.remove(position);
                new CancleTask().execute(null,null,null);
            }
        });
        return view;
    }

    private class CancleTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            requsetServer = RequsetServer.getInstance();
        }

        protected Void doInBackground(Void... params) {
            requsetServer.requsetCancle(curi,cls,curinm);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            try {
                Log.e("조인 ", "조인하고잇음");
                requsetServer.cancleThread.join();
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                AlertDialog dialog = builder.setMessage("강의가 취소되었습니다.")
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();
                ListActivity.sugangListView.clearChoices();
                ListActivity.sugangCompleteAdapter.notifyDataSetChanged();

                Log.e("조인", "끝");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
