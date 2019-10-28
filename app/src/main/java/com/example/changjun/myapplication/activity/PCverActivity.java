package com.example.changjun.myapplication.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.changjun.myapplication.R;

public class PCverActivity extends AppCompatActivity {
    private WebView webview;
    private String url="https://sugang.donga.ac.kr/login.aspx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pcver);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webview = (WebView) findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadUrl(url);//웹뷰 실행
        webview.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
        webview.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기
    }
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
