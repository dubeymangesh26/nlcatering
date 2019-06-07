package com.dotcom.nlcatering.CustomerActivity.CustomerOrders;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dotcom.nlcatering.R;

public class CustomerOederDetails extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_oeder_details);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.orangenormal)); //Status bar colour
    }
}
