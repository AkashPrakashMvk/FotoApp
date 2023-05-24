package com.innerermond.foto;

import android.os.Bundle;
import android.webkit.WebView;

import com.innerermond.foto.base.BaseActivity;

public class TermsOfUse extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.activity_terms_of_use);

        WebView mywebview = (WebView) findViewById(R.id.termsofuse);
        mywebview.loadUrl("https://sites.google.com/view/innerermondtermsofuse");

        /*String data = "<html><body><h1>Hello, Javatpoint!</h1></body></html>";
        mywebview.loadData(data, "text/html", "UTF-8"); */

        // mywebview.loadUrl("file:///android_asset/myresource.html");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,android.R.anim.slide_out_right);

    }
}
