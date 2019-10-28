package com.example.changjun.myapplication.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.CustomProgress;
import com.example.changjun.myapplication.util.Department;
import com.example.changjun.myapplication.util.RequsetServer;
import com.example.changjun.myapplication.util.SugangDialog;

public class BasketActivity extends AppCompatActivity {

    private Button basketButton ,scheduleButton;
    public CustomProgress customProgress;

    public static TextView totalCredit;

    private RequsetServer requsetServer;
    private Department information;

    public static int total=0;
    public static int dialCount =0;

    private LinearLayout.LayoutParams linearParams;
    private LinearLayout linearLayout;
    private LayoutInflater inflater;
    private Animation fadeIn;
    private SugangDialog sugangDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requsetServer = RequsetServer.getInstance();
        information=Department.getInstace();

        basketButton = (Button) findViewById(R.id.basketButton);
        scheduleButton= (Button) findViewById(R.id.scheduleButton);
        totalCredit = (TextView) findViewById(R.id.totalCredit);

        if(dialCount==0) {
            inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_halfdark_background, null);
            linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            sugangDialog = new SugangDialog(BasketActivity.this, cancelListener);
            sugangDialog.setCancelable(false);
            fadeIn = new AlphaAnimation(0.0f, 1.0f); // 페이드인 효과
            fadeIn.setDuration(800);
            linearLayout.setAnimation(fadeIn);
            addContentView(linearLayout, linearParams); // inflate 레이아웃 add
            sugangDialog.show();
            dialCount++;
        }
        totalCredit.setText("등록한 학점은  총 " + total+ "학점 입니다.");

        basketButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimarySimple));
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment, new BasketFragment());
        fragmentTransaction.commit();

        basketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basketButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimarySimple));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new BasketFragment());
                fragmentTransaction.commit();
            }
        });
        scheduleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basketButton.setBackgroundColor(getResources().getColor(R.color.colorPrimarySimple));
                scheduleButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new ScheduleFragment());
                fragmentTransaction.commit();
            }
        });

    }
    public void onClickDoSugangButton(View view) {
        if(information.addList.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this);
            AlertDialog dialog = builder.setMessage("장바구니가 비어있습니다.")
                    .setPositiveButton("확인", null)
                    .create();
            dialog.show();
        }
        else new Sugang().execute(null, null, null);
    }

    private class Sugang extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            customProgress = new CustomProgress(BasketActivity.this);
            customProgress.show();
        }
        @Override
        protected Void doInBackground(Void... params) {

            requsetServer.requsetSugang(information.addList);
            Log.e("조인 ", "조인하고잇음");
            try {
                requsetServer.sugangThread.join();
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
                AlertDialog.Builder builder = new AlertDialog.Builder(BasketActivity.this);
                AlertDialog dialog = builder.setMessage(requsetServer.sugangResult)
                        .setPositiveButton("확인", null)
                        .create();
                dialog.show();
                BasketFragment.addListView.clearChoices();
                BasketFragment.addListAdapter.notifyDataSetChanged();
                totalCredit.setText("등록한 학점은 총 " + total +"학점 입니다.");
                Log.e("조인", "끝");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    private View.OnClickListener cancelListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            sugangDialog.dismiss();
            // 어두움 제거
            ((ViewManager) linearLayout.getParent()).removeView(linearLayout);
        }
    };
}
