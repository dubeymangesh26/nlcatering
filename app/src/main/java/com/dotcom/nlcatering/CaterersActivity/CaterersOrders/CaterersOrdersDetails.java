package com.dotcom.nlcatering.CaterersActivity.CaterersOrders;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dotcom.nlcatering.R;

public class CaterersOrdersDetails extends AppCompatActivity {
    Toolbar toolbar;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterers_orders_details);
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Bidding List");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
