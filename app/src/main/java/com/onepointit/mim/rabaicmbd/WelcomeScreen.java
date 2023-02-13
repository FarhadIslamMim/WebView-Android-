package com.onepointit.mim.rabaicmbd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class WelcomeScreen extends AppCompatActivity {

    private ProgressBar progressBar;
    int progressTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        progressBar = findViewById(R.id.progressBar);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                doWork();
                startApp();
            }
        });
        thread.start();

    }
    public void doWork(){
        for (progressTime = 20; progressTime<= 100; progressTime= progressTime+50){
            try {
                Thread.sleep(1000);
                progressBar.setProgress(progressTime);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void startApp(){
        Intent intent = new Intent(WelcomeScreen.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}