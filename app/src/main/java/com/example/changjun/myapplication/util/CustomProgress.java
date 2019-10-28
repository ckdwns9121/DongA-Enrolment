package com.example.changjun.myapplication.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.changjun.myapplication.R;

public class CustomProgress {
    private ProgressDialog pd;

    public CustomProgress(Context context){
        pd = new ProgressDialog(context);
        pd.setIndeterminate(true);
        pd.setCancelable(false);
    }
    public void show(){ //프로그래바 enable
        pd.show();
        pd.setContentView(R.layout.custom_progress);
    }
    public void dismiss(){//프로그래스바 disable
        pd.dismiss();
    }
}
