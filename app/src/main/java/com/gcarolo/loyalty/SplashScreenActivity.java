package com.gcarolo.loyalty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // on below line we are configuring our window to full screen


        setContentView(R.layout.activity_splash_screen);

        // on below line we are calling handler to run a task
        // for specific time interval
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // on below line we are
                // creating a new intent
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);

                // on below line we are
                // starting a new activity.
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.hold);

                // on the below line we are finishing
                // our current activity.
                finish();
            }
        }, 2000);
    }
}