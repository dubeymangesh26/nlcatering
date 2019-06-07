package com.dotcom.nlcatering.SpalshAndSelection;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererLogin;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerQuotation;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.FoodType;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.R;

public class LoginType extends AppCompatActivity {

    Button customer,caterer;
    Toolbar toolbar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login_type);
        getWindow().setTitle(getResources().getString(R.string.app_name));

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Login Type");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        customer = (Button) findViewById(R.id.customer);
        caterer = (Button) findViewById(R.id.caterer);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour

        customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginType.this, CustomerMainActivity.class);
                startActivity(i);
            }
        });
        caterer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(LoginType.this, CatererLogin.class);
                startActivity(i);
            }
        });

    }

}
