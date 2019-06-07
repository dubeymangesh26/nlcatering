package com.dotcom.nlcatering.SpalshAndSelection;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Handler;
import android.os.LocaleList;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.dotcom.nlcatering.Activity.CheckInternetScreen;
import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererLogin;
import com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub.CatererMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.R;

import java.util.Locale;

public class SplashScreen extends AppCompatActivity {

    ImageView splash;
    TextView splashText;
    String loginstatus = "false";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String language = UserPreferenceUtils.getStringUserPreference(SplashScreen.this, Constants.LANG_SELECTED);
        setLanguage(language);

        splash = findViewById(R.id.splash);
        splashText = findViewById(R.id.splashText);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotateanim);
        splash.setAnimation(animation);

        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.downanim);
        splashText.setAnimation(animation1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UserPreferenceUtils.isOnline(getApplicationContext())) {
            getPermission();
        } else {
            Intent intent = new Intent(SplashScreen.this, CheckInternetScreen.class);
            startActivity(intent);
        }
    }

    private void getPermission() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String type = UserPreferenceUtils.getStringUserPreference(SplashScreen.this, Constants.LOGIN_TYPE);
                if (type != null) {
                    if (type.equals("CA")) {
                        Intent intent = new Intent(SplashScreen.this, CatererMainActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent intent = new Intent(SplashScreen.this, CustomerMainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Intent intent = new Intent(SplashScreen.this, LanguageSelection.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, 3000);

    }

    private void setLanguage(String language) {

        if (language != null) {
            if (language.contains("nl")) {
                Locale locale = new Locale("nl");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    config.setLocales(new LocaleList(locale));
                } else {
                    config.locale = locale;
                }
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(SplashScreen.this, LanguageSelection.class);
                        startActivity(intent);
                        finish();

                    }
                }, 3000);
            } else {
                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    config.setLocales(new LocaleList(locale));
                } else {
                    config.locale = locale;
                }
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());


            }


        } /*else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, LanguageSelection.class);
                    startActivity(intent);
                    finish();

                }
            }, 3000);
        }*/
    }


}
