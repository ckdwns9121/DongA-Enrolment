package com.example.changjun.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.changjun.myapplication.R;

/*디스이즈 공식홈페이지를 보여주는 엑티비티*/
public class DSISHomeActivity extends Activity {

    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsishome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        webview = (WebView) findViewById(R.id.webView);

        webview.getSettings().setJavaScriptEnabled(true);//자바스크립트 허용
        webview.getSettings().setLoadWithOverviewMode(true);
        webview.getSettings().setUseWideViewPort(true);
        webview.loadUrl("http://1.234.23.42/thisis/WebViewParts/DSIS_ProtoType/index.html");//디스이즈 홈
        webview.setWebChromeClient(new WebChromeClient());//웹뷰에 크롬 사용 허용//이 부분이 없으면 크롬에서 alert가 뜨지 않음
        webview.setWebViewClient(new WebViewClientClass());//새창열기 없이 웹뷰 내에서 다시 열기

    }
    @Override
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

