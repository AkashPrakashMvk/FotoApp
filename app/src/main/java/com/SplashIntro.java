package com;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.innerermond.foto.EditImageActivity;
import com.innerermond.foto.LoginActivity;
import com.innerermond.foto.R;
import com.innerermond.foto.base.BaseActivity;

public class SplashIntro extends BaseActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeFullScreen();
        setContentView(R.layout.splashintro);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.stay);

            }
        },3280);
    }
}
