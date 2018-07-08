package com.example.user.myapplication;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class secondpage extends AppCompatActivity {

    private Button btnn2;
    private Button cameraBtnn;
    private Button gallertbtnn;

    private TextView countdownText;
    private Button countdownBtnn;
    private CountDownTimer countDownTimer;
    private long timeLeftInMiliseconds = 60000;
    private boolean timerRunning;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondpage);

        btnn2 = findViewById(R.id.backbtnn);

        btnn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent loginIntent2 = new Intent(secondpage.this, MainActivity.class);
                loginIntent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(loginIntent2);

            }
        });

        cameraBtnn = findViewById(R.id.camerabtnn);

        cameraBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new OneFragment());

            }
        });

        gallertbtnn = findViewById(R.id.gallerybtnn);

        gallertbtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new TwoFragment());

            }
        });

        countdownText = findViewById(R.id.timer);
        countdownBtnn = findViewById(R.id.startbtnn);

        countdownBtnn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startStop();
            }
        });

        updateTimer();

    }

        public void startStop(){
            if(timerRunning){
                stopTimer();
            }else{
                startTimer();
                loadFragment(new OneFragment());
            }

        }

        public void startTimer(){
            countDownTimer = new CountDownTimer(timeLeftInMiliseconds, 1000) {
                @Override
                public void onTick(long l) {
                    timeLeftInMiliseconds = l;
                    updateTimer();
                }

                @Override
                public void onFinish() {

                }
            }.start();
            countdownBtnn.setText("PAUSE");
            timerRunning = true;

        }


        public void stopTimer(){
            countDownTimer.cancel();
            countdownBtnn.setText("START");
            timerRunning = false;
        }

        public void updateTimer(){
            int minutes = (int) timeLeftInMiliseconds / 60000;
            int seconds = (int) timeLeftInMiliseconds % 60000 / 1000;

            String timeLeftText;

            timeLeftText = "" + minutes;
            timeLeftText += ":";
            if (seconds < 10 ) timeLeftText += "0";
            timeLeftText += seconds;

            countdownText.setText(timeLeftText);
        }


        private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fm = fragmentManager.beginTransaction();
            fm.replace(R.id.frame,fragment);
            fm.commit();
        }

    }











