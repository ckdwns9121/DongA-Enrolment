package com.example.changjun.myapplication.util;

import android.content.Context;

public class ToastClass {
    public static Context context;
    public static CustomProgress customProgress;
    public ToastClass (Context context){
        this.context = context;
        customProgress= new CustomProgress(context);
    }
}
