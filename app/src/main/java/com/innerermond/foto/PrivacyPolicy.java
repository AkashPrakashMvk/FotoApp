package com.innerermond.foto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.innerermond.foto.base.BaseActivity;

public class PrivacyPolicy extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.activity_privacy_policy);

        WebView mywebview = (WebView) findViewById(R.id.privacypolicy);
         mywebview.loadUrl("https://sites.google.com/view/innerermondprivacypolicy");

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