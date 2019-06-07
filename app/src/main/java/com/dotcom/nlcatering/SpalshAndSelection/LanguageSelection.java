package com.dotcom.nlcatering.SpalshAndSelection;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.R;

import java.util.Locale;

public class LanguageSelection extends AppCompatActivity {
    Button dutch, english;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // loadLocale();
        setContentView(R.layout.activity_longuage_selection);

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.actionbar));

        //   getWindow().setTitle(getResources().getString(R.string.app_name));


        dutch = (Button) findViewById(R.id.dutch);
        english = (Button) findViewById(R.id.english);

        dutch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Locale locale = new Locale("nl");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    config.setLocales(new LocaleList(locale));
                    UserPreferenceUtils.saveUserPreference(LanguageSelection.this, Constants.LANG_SELECTED, "nl");
                } else {
                    config.locale = locale;
                    UserPreferenceUtils.saveUserPreference(LanguageSelection.this, Constants.LANG_SELECTED, "nl");
                }
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());

                Intent i = new Intent(LanguageSelection.this, LoginType.class);
                startActivity(i);
            }

        });
        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Locale locale = new Locale("en");
                Locale.setDefault(locale);
                Configuration config = new Configuration();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    config.setLocales(new LocaleList(locale));
                    UserPreferenceUtils.saveUserPreference(LanguageSelection.this, Constants.LANG_SELECTED, "en");
                } else {
                    config.locale = locale;
                    UserPreferenceUtils.saveUserPreference(LanguageSelection.this, Constants.LANG_SELECTED, "en");
                }
                getResources().updateConfiguration(config,
                        getResources().getDisplayMetrics());

                Intent i = new Intent(LanguageSelection.this, LoginType.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.rgb(30, 144, 255));
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.rgb(30, 144, 255));
    }


}


