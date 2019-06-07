package com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub;

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

public class CaterersProfileDetails extends AppCompatActivity {
    Toolbar toolbar;
    TextView name,catererType,emailAddress,address;
    UserSessionManager session;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterers_profile_details);

        session = new UserSessionManager(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Caterer Profile Details");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        name=findViewById(R.id.name);
        catererType=findViewById(R.id.catererType);
        emailAddress=findViewById(R.id.emailAddress);
        address=findViewById(R.id.address);


        String compName = UserPreferenceUtils.getStringUserPreference(this,Constants.FIRST_NAME);
        Log.e("User name", "onCreate: "+compName );
        name.setText(compName);

        String userEmail = UserPreferenceUtils.getStringUserPreference(this,Constants.EMAIL);
        Log.e("User Email", "onCreate: "+userEmail );
        emailAddress.setText(userEmail);

        String cattypr = UserPreferenceUtils.getStringUserPreference(this,Constants.CATERER_TYPE);
        Log.e("User Contact", "onCreate: "+cattypr );
        catererType.setText(cattypr);

        String userAddress = UserPreferenceUtils.getStringUserPreference(this, Constants.ADDRESS);
        Log.e("User Address", "onCreate: "+userAddress );
        address.setText(userAddress);


    }
}
