package com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.R;

public class CustomerProfileDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView name,moblineNo,emailAddress,address,pincode;
    UserSessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile_details);

        session = new UserSessionManager(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Customer Profile Details");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        name=findViewById(R.id.name);
        moblineNo=findViewById(R.id.moblineNo);
        emailAddress=findViewById(R.id.emailAddress);
        address=findViewById(R.id.address);
        pincode=findViewById(R.id.pincode);


        String userName = UserPreferenceUtils.getStringUserPreference(this, Constants.USER_NAME);
        Log.e("User name", "onCreate: "+userName );
        name.setText(userName);

        String userEmail = UserPreferenceUtils.getStringUserPreference(this,Constants.EMAIL);
        Log.e("User Email", "onCreate: "+userEmail );
        emailAddress.setText(userEmail);

        String userContact = UserPreferenceUtils.getStringUserPreference(this,Constants.MOBILE);
        Log.e("User Contact", "onCreate: "+userContact );
        moblineNo.setText(userContact);

        String userAddress = UserPreferenceUtils.getStringUserPreference(this, Constants.ADDRESS);
        Log.e("User Address", "onCreate: "+userAddress );
        address.setText(userAddress);

        String userState = UserPreferenceUtils.getStringUserPreference(this,  Constants.PINCODE);
        Log.e("User Address", "onCreate: "+userState );
        pincode.setText(userState);
    }
}
