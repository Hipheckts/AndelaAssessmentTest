package com.challenge.andela.jdl;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Created by mokeam on 3/4/17.
 */

//This class displays the SplashScreen On Application Startup

public class SplashScreen extends AppCompatActivity {

    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        thread = new Thread(){
            @Override
            public void run() {
                try{
                    sleep(3000);
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();

                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };

        thread.start();
    }
}
