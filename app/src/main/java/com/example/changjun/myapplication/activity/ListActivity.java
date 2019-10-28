package com.example.changjun.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ListView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.CustomProgress;
import com.example.changjun.myapplication.util.Department;
import com.example.changjun.myapplication.util.RequsetServer;
import com.example.changjun.myapplication.util.SugangCompleteAdapter;

/* 수강신청 결과를 담을 리스트 엑티비티 입니다. */
public class ListActivity extends Activity {
    private CustomProgress customProgress;
    private RequsetServer requsetServer;
    private Department information;
    public static ListView sugangListView;
    public static SugangCompleteAdapter sugangCompleteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requsetServer = RequsetServer.getInstance();
        information = Department.getInstace();
        sugangListView = (ListView) findViewById(R.id.sugangListView);
        sugangCompleteAdapter = new SugangCompleteAdapter(this,information.sugangCompletesList);
        sugangListView.setAdapter(sugangCompleteAdapter);
        new GetSugangTask().execute(null,null,null);
    }

    private class GetSugangTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            customProgress= new CustomProgress(ListActivity.this);
            customProgress.show();
        }
        @Override
        protected Void doInBackground(Void... params) {
            requsetServer.getList();
            Log.e("조인 ","조인하고잇음");
            try {
                requsetServer.getSugangList.join();
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
                    if (information.sugangCompletesList.isEmpty()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
                        AlertDialog dialog = builder.setMessage(requsetServer.cancleResult)
                                .setPositiveButton("확인", null)
                                .create();
                        dialog.show();
                    }
                sugangListView.setAdapter(sugangCompleteAdapter);
                Log.e("조인","끝");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
