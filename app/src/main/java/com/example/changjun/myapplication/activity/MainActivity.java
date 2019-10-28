package com.example.changjun.myapplication.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.AppInfo;
import com.example.changjun.myapplication.util.BackPressCloseHandler;
import com.example.changjun.myapplication.util.RequsetServer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends Activity {

    private EditText id,pw;
    private TextView textView;
    private Button loginButton;
    private String userID;
    private String userPW;
    private RequsetServer requsetServer;
    private BackPressCloseHandler backPressCloseHandler;
    private static final int CHECK_USE = 1;
    private int now_USE;

    private AppInfo appinfo;

    // 다이얼로그 배경 반투명 효과용
    private LinearLayout.LayoutParams linearParams;
    private LinearLayout linearLayout;
    private LayoutInflater inflater;
    private Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.appinfo);

        id= (EditText)findViewById(R.id.editText);
        pw=(EditText)findViewById(R.id.editText2);
        loginButton=(Button)findViewById(R.id.LoginButton);
        backPressCloseHandler = new BackPressCloseHandler(this);
        requsetServer = new RequsetServer(this, MainActivity.this);
        SharedPreferences ID = getSharedPreferences("user_id",MODE_PRIVATE);
        String student_id = ID.getString("text","");
        SharedPreferences PW = getSharedPreferences("user_pw",MODE_PRIVATE);
        String student_pw = PW.getString("text2","");
        id.setText(student_id);
        pw.setText(student_pw);

        /*재수강 백그라운드 태스크 */
        new ProcessCheckVersion().execute(null, null, null);

        textView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_halfdark_background, null);
                linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);
                appinfo=new AppInfo(MainActivity.this, cancelListener);
                appinfo.setCancelable(false);
                fadeIn = new AlphaAnimation(0.0f, 1.0f); // 페이드인 효과
                fadeIn.setDuration(800);
                linearLayout.setAnimation(fadeIn);
                addContentView(linearLayout, linearParams); // inflate 레이아웃 add
                appinfo.show();
            }
        });
    }

    public void onLoginButtonClick(View view) {
        if(now_USE==CHECK_USE) {
            userID = id.getText().toString().trim();
            userPW = pw.getText().toString().trim();
            requsetServer.doLogin(userID, userPW);
        }

//        if(checkTime()) {
//            userID = id.getText().toString().trim();
//            userPW = pw.getText().toString().trim();
//            requsetServer.doLogin(userID, userPW);
//        }
        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            AlertDialog dialog =builder.setMessage("사용기간이 아닙니다.")
                    .setPositiveButton("확인",null)
                    .create();
            dialog.show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences sharedPreferences = getSharedPreferences("user_id", MODE_PRIVATE);
        SharedPreferences sharedPreferences2 = getSharedPreferences("user_pw", MODE_PRIVATE);

        SharedPreferences.Editor editor_id = sharedPreferences.edit();
        SharedPreferences.Editor editor_pw = sharedPreferences2.edit();
        String student_id = id.getText().toString();
        String student_pw = pw.getText().toString();
        editor_id.putString("text", student_id);
        editor_pw.putString("text2", student_pw);
        editor_id.commit();
        editor_pw.commit();
    }

    @Override
    public void onBackPressed(){
        backPressCloseHandler.onBackPressed();
    }

    /*사용기간 체크*/
    private class ProcessCheckVersion extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Void doInBackground(Void... params) {
            String url ="http://1.234.23.42/thisis/versionchk/sugang.php";
            try {
                Document doc = Jsoup.connect(url).get();
                String temp = doc.text();
                Log.v("현재버전",temp);
                now_USE=Integer.parseInt(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private View.OnClickListener cancelListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            appinfo.dismiss();
            // 어두움 제거
            ((ViewManager) linearLayout.getParent()).removeView(linearLayout);
        }
    };

    public boolean checkTime( ){
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat CurMonthFormat = new SimpleDateFormat("MM");
        int month = Integer.parseInt(CurMonthFormat.format(date));
        if (month ==3 || month==4) return false;
        if (month==9 || month==10) return false;
        else return true;
    }
}


