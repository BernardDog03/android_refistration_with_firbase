package com.example.project_akhir;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import gr.net.maroulis.library.EasySplashScreen;

public class splash extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EasySplashScreen config = new EasySplashScreen(splash.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3500)
                .withLogo(R.drawable.logo)
                .withFooterText("BernardDog ©2020 All right reserverd");

        config.getFooterTextView().setTextColor(R.color.colorPrimary);

        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
