package com.nomoressay.acadtrashmemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_splash);


            Thread myThread=new Thread(){
                @Override
                public void run() {
                    try{
                        sleep(5000);
                        Intent it=new Intent(getApplicationContext(),MainPage.class);
                        startActivity(it);
                        finish();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            };
            myThread.start();

        }
    }
