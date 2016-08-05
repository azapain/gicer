package com.fap.gic.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.fap.gic.R;
import com.fap.gic.model.GicApplication;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
//        if (GicApplication.getInstance().readShareLogin(0) > 0) {
//            Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(this, "Fkkkk", Toast.LENGTH_LONG).show();
//        }
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
