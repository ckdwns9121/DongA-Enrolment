package com.example.changjun.myapplication.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.changjun.myapplication.R;

public class SugangDialog extends Dialog {

    private Context context;
    private Button cancel;
    private TextView titleView;
    private ImageView imgView;

    private View.OnClickListener mClkListener;

    public SugangDialog(Context context, View.OnClickListener mClkListener) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.context = context;
        this.mClkListener = mClkListener;
    }

    public SugangDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_sugangdial);

        getWindow().getAttributes().windowAnimations = R.style.CustomDialogAnimation;

        titleView = (TextView) findViewById(R.id.app_title);
        cancel = (Button) findViewById(R.id.app_cancel);


        cancel.setOnClickListener(mClkListener);
    }

}
