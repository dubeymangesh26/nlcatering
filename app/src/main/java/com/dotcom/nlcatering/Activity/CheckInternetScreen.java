package com.dotcom.nlcatering.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.R;

public class CheckInternetScreen extends AppCompatActivity {

    Button tryAgain;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_internet_screen);

        tryAgain = (Button) findViewById(R.id.try_again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserPreferenceUtils.isOnline(getApplicationContext()))
                {
                    finish();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(UserPreferenceUtils.isOnline(getApplicationContext()))
        {
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
        finish();
    }
}
