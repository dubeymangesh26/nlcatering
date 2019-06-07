package com.dotcom.nlcatering.CustomerActivity.CustomerBiddings;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dotcom.nlcatering.CustomerActivity.PaymentActivity.CustomerPaymentActivity;
import com.dotcom.nlcatering.R;

import org.w3c.dom.Text;

public class CustomerBiddingDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView cate_Type,No_of_Person,amount;
    String cateringType;
    int toAmount,no_person;
    Button proceedPayment;
    Button proceedPay;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bidding_details);

        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.orangenormal)); //Status bar colour

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Proceed To Payment");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        proceedPayment=findViewById(R.id.proceedPayment);

        No_of_Person=findViewById(R.id.noOfPerson1);
        cate_Type=findViewById(R.id.name);
        amount=findViewById(R.id.totalAmount);

        cateringType=getIntent().getExtras().getString("typecatering");
        toAmount=getIntent().getExtras().getInt("person");
        no_person=getIntent().getExtras().getInt("totalamount");

        No_of_Person.setText(String.valueOf(no_person));
        cate_Type.setText(cateringType);
        amount.setText(String.valueOf(toAmount));

        proceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnpolicy = new Intent(CustomerBiddingDetails.this, CustomerPaymentActivity.class);
                startActivity(returnpolicy);
            }
        });
    }
}
