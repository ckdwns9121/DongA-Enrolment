package com.example.changjun.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.changjun.myapplication.R;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_menu);
    }
    public void onClickTimetable(View view)
    {
        Intent intent = new Intent(MenuActivity.this, TimetableActivity.class);
        startActivity(intent);
    }
    public void onClickBasket(View view)
    {
        Intent intent = new Intent(MenuActivity.this, BasketActivity.class);
        startActivity(intent);
    }
    public void onClickList(View view)
    {
        Intent intent = new Intent(MenuActivity.this, ListActivity.class);
        startActivity(intent);
    }
    public void onClickPcver(View view)
    {
        Intent intent = new Intent(MenuActivity.this, PCverActivity.class);
        startActivity(intent);
    }
    public void onClickDSIS_main(View view)
    {
        Intent intent = new Intent(MenuActivity.this, DSISHomeActivity.class);
        startActivity(intent);
    }
}
