package com.example.changjun.myapplication.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.changjun.myapplication.R;
import com.example.changjun.myapplication.util.Schedule;

public class ScheduleActivity extends AppCompatActivity {
    private TextView monday[] = new TextView[20];
    private TextView tuesday[] = new TextView[20];
    private TextView wednesday[] = new TextView[20];
    private TextView thursday[] = new TextView[20];
    private TextView friday[] = new TextView[20];
    private Schedule schedule ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_schedule);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        schedule = Schedule.getInstance();
//        schedule=new Schedule();
        monday[0] = (TextView)findViewById(R.id.monday1);
        monday[1] = (TextView)findViewById(R.id.monday2);
        monday[2] = (TextView)findViewById(R.id.monday3);
        monday[3] = (TextView)findViewById(R.id.monday4);
        monday[4] = (TextView)findViewById(R.id.monday5);
        monday[5] = (TextView)findViewById(R.id.monday6);
        monday[6] = (TextView)findViewById(R.id.monday7);
        monday[7] = (TextView)findViewById(R.id.monday8);
        monday[8] = (TextView)findViewById(R.id.monday9);
        monday[9] = (TextView)findViewById(R.id.monday10);
        monday[10] = (TextView)findViewById(R.id.monday11);
        monday[11] = (TextView)findViewById(R.id.monday12);
        monday[12] = (TextView)findViewById(R.id.monday13);
        monday[13] = (TextView)findViewById(R.id.monday14);
        monday[14] = (TextView)findViewById(R.id.monday15);
        monday[15] = (TextView)findViewById(R.id.monday16);
        monday[16] = (TextView)findViewById(R.id.monday17);
        monday[17] = (TextView)findViewById(R.id.monday18);
        monday[18] = (TextView)findViewById(R.id.monday19);
        monday[19] = (TextView)findViewById(R.id.monday20);

        tuesday[0] = (TextView)findViewById(R.id.tuesday1);
        tuesday[1] = (TextView)findViewById(R.id.tuesday2);
        tuesday[2] = (TextView)findViewById(R.id.tuesday3);
        tuesday[3] = (TextView)findViewById(R.id.tuesday4);
        tuesday[4] = (TextView)findViewById(R.id.tuesday5);
        tuesday[5] = (TextView)findViewById(R.id.tuesday6);
        tuesday[6] = (TextView)findViewById(R.id.tuesday7);
        tuesday[7] = (TextView)findViewById(R.id.tuesday8);
        tuesday[8] = (TextView)findViewById(R.id.tuesday9);
        tuesday[9] = (TextView)findViewById(R.id.tuesday10);
        tuesday[10] = (TextView)findViewById(R.id.tuesday11);
        tuesday[11] = (TextView)findViewById(R.id.tuesday12);
        tuesday[12] = (TextView)findViewById(R.id.tuesday13);
        tuesday[13] = (TextView)findViewById(R.id.tuesday14);
        tuesday[14] = (TextView)findViewById(R.id.tuesday15);
        tuesday[15] = (TextView)findViewById(R.id.tuesday16);
        tuesday[16] = (TextView)findViewById(R.id.tuesday17);
        tuesday[17] = (TextView)findViewById(R.id.tuesday18);
        tuesday[18] = (TextView)findViewById(R.id.tuesday19);
        tuesday[19] = (TextView)findViewById(R.id.tuesday20);

        wednesday[0] = (TextView)findViewById(R.id.wednseday1);
        wednesday[1] = (TextView)findViewById(R.id.wednseday2);
        wednesday[2] = (TextView)findViewById(R.id.wednseday3);
        wednesday[3] = (TextView)findViewById(R.id.wednseday4);
        wednesday[4] = (TextView)findViewById(R.id.wednseday5);
        wednesday[5] = (TextView)findViewById(R.id.wednseday6);
        wednesday[6] = (TextView)findViewById(R.id.wednseday7);
        wednesday[7] = (TextView)findViewById(R.id.wednseday8);
        wednesday[8] = (TextView)findViewById(R.id.wednseday9);
        wednesday[9] = (TextView)findViewById(R.id.wednseday10);
        wednesday[10] = (TextView)findViewById(R.id.wednseday11);
        wednesday[11] = (TextView)findViewById(R.id.wednseday12);
        wednesday[12] = (TextView)findViewById(R.id.wednseday13);
        wednesday[13] = (TextView)findViewById(R.id.wednseday14);
        wednesday[14] = (TextView)findViewById(R.id.wednseday15);
        wednesday[15] = (TextView)findViewById(R.id.wednseday16);
        wednesday[16] = (TextView)findViewById(R.id.wednseday17);
        wednesday[17] = (TextView)findViewById(R.id.wednseday18);
        wednesday[18] = (TextView)findViewById(R.id.wednseday19);
        wednesday[19] = (TextView)findViewById(R.id.wednseday20);

        thursday[0] = (TextView)findViewById(R.id.thursday1);
        thursday[1] = (TextView)findViewById(R.id.thursday2);
        thursday[2] = (TextView)findViewById(R.id.thursday3);
        thursday[3] = (TextView)findViewById(R.id.thursday4);
        thursday[4] = (TextView)findViewById(R.id.thursday5);
        thursday[5] = (TextView)findViewById(R.id.thursday6);
        thursday[6] = (TextView)findViewById(R.id.thursday7);
        thursday[7] = (TextView)findViewById(R.id.thursday8);
        thursday[8] = (TextView)findViewById(R.id.thursday9);
        thursday[9] = (TextView)findViewById(R.id.thursday10);
        thursday[10] = (TextView)findViewById(R.id.thursday11);
        thursday[11] = (TextView)findViewById(R.id.thursday12);
        thursday[12] = (TextView)findViewById(R.id.thursday13);
        thursday[13] = (TextView)findViewById(R.id.thursday14);
        thursday[14] = (TextView)findViewById(R.id.thursday15);
        thursday[15] = (TextView)findViewById(R.id.thursday16);
        thursday[16] = (TextView)findViewById(R.id.thursday17);
        thursday[17] = (TextView)findViewById(R.id.thursday18);
        thursday[18] = (TextView)findViewById(R.id.thursday19);
        thursday[19] = (TextView)findViewById(R.id.thursday20);

        friday[0] = (TextView)findViewById(R.id.friday1);
        friday[1] = (TextView)findViewById(R.id.friday2);
        friday[2] = (TextView)findViewById(R.id.friday3);
        friday[3] = (TextView)findViewById(R.id.friday4);
        friday[4] = (TextView)findViewById(R.id.friday5);
        friday[5] = (TextView)findViewById(R.id.friday6);
        friday[6] = (TextView)findViewById(R.id.friday7);
        friday[7] = (TextView)findViewById(R.id.friday8);
        friday[8] = (TextView)findViewById(R.id.friday9);
        friday[9] = (TextView)findViewById(R.id.friday10);
        friday[10] = (TextView)findViewById(R.id.friday11);
        friday[11] = (TextView)findViewById(R.id.friday12);
        friday[12] = (TextView)findViewById(R.id.friday13);
        friday[13] = (TextView)findViewById(R.id.friday14);
        friday[14] = (TextView)findViewById(R.id.friday15);
        friday[15] = (TextView)findViewById(R.id.friday16);
        friday[16] = (TextView)findViewById(R.id.friday17);
        friday[17] = (TextView)findViewById(R.id.friday18);
        friday[18] = (TextView)findViewById(R.id.friday19);
        friday[19] = (TextView)findViewById(R.id.friday20);
        new ScheduleTask().execute(null, null, null);
    }

    private class ScheduleTask extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }
        @Override
        protected Void doInBackground(Void... params) {
            schedule.setting(monday,tuesday,wednesday,thursday,friday);
            Log.e("시간표테스트","시작");
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}
