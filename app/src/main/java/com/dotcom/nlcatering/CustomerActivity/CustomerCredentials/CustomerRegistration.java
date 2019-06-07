package com.dotcom.nlcatering.CustomerActivity.CustomerCredentials;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererRegistration;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerRegisterRequest;
import com.dotcom.nlcatering.Response.CatererResponse.CatererCityAndCatererTypeResponse;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerRegistration extends AppCompatActivity {

    Toolbar toolbar;
    TextView signintxt;
    Spinner city;
    AutoCompleteTextView locality, pincode, act, landmark;
    EditText use_name, email_add, cont_no, location;
    Button sign_up;
    public String TAG = CustomerRegistration.class.getSimpleName();
    List<String> cityListName;
    List<Integer> cityListId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_registration);

        cityListName = new ArrayList<>();
        cityListId = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        signintxt = (TextView) findViewById(R.id.signintxt);
        use_name = (EditText) findViewById(R.id.customerName);
        email_add = (EditText) findViewById(R.id.emailAddress);
        cont_no = (EditText) findViewById(R.id.conttctno);
        location = (EditText) findViewById(R.id.address);
        sign_up = (Button) findViewById(R.id.btnSignup);
        city = (Spinner) findViewById(R.id.city);
        pincode = (AutoCompleteTextView) findViewById(R.id.pincode);

        toolbar.setTitle("Customer Register");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signintxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserPreferenceUtils.isOnline(CustomerRegistration.this)) {
                    if (validate()) {

                        String username = use_name.getText().toString().trim();
                        String emailId = email_add.getText().toString().trim();
                        String mob = cont_no.getText().toString().trim();
                        String loctn = location.getText().toString().trim();
                        int cty = 0;
                        for (int i = 0; i < cityListName.size(); i++) {
                            if (city.getSelectedItem().toString().equals(cityListName.get(i))) {
                                cty = cityListId.get(i - 1);
                            }
                        }
                        String pin = pincode.getText().toString().trim();

                        if (validate1()) {
                            customerRegistration(username, emailId, mob, cty, pin, loctn);
                        }

                    }

                } else {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CustomerRegistration.this);
                    builder.setTitle("Internet problem");
                    builder.setMessage("Oops! seems you have lost internet connectivity. Please try again later.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    android.app.AlertDialog alert = builder.create();
                    alert.show();
                    Button nbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                    nbutton.setTextColor(Color.rgb(30, 144, 255));
                }
            }


        });
        getCityList();

    }

    private void getCityList() {
        showProgressDialog();
        Call<CatererCityAndCatererTypeResponse> cityAndCatererTypeResponseCall = Utilss.getWebService().CATERER_CITY_AND_CATERER_TYPE_RESPONSE_CALL();
        cityAndCatererTypeResponseCall.enqueue(new Callback<CatererCityAndCatererTypeResponse>() {
            @Override
            public void onResponse(Call<CatererCityAndCatererTypeResponse> call, Response<CatererCityAndCatererTypeResponse> response) {
                Log.e("data", "onResponse: " + new GsonBuilder().create().toJson(response.body()));
                CatererCityAndCatererTypeResponse activityResponse = response.body();
                hideProgressDialog();
                if (response.code() == 200) {

                    if (activityResponse.getResponse().getCity().size() > 0) {

                        cityListName.add("Select City");
                        for (int i = 0; i < activityResponse.getResponse().getCity().size(); i++) {
                            cityListName.add(activityResponse.getResponse().getCity().get(i).getName());
                            cityListId.add(activityResponse.getResponse().getCity().get(i).getId());
                        }

                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CustomerRegistration.this, R.layout.spinner_dropdown, cityListName);
                        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        city.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(CustomerRegistration.this, "Something went worng", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();

                }

            }

            @Override
            public void onFailure(Call<CatererCityAndCatererTypeResponse> call, Throwable t) {

            }
        });

    }


    private void customerRegistration(String username, String emailId, String mob, int cty, String pin, String loctn) {
        final Dialog dialog = new Dialog(CustomerRegistration.this);
        showProgressDialog();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds")

        CustomerRegisterRequest registrationRequest = new CustomerRegisterRequest();
        registrationRequest.setCust_Name(username);
        registrationRequest.setEmail(emailId);
        registrationRequest.setMobileNo(mob);
        registrationRequest.setAddress(loctn);
        registrationRequest.setCity(String.valueOf(cty));
        registrationRequest.setPinCode(pin);

        Log.e(TAG, "registration request : " + new GsonBuilder().create().toJson(registrationRequest));

        Call<CustomerRegisterRequest> customerRegisterResponse = Utilss.getWebService().CUSTOMER_REGISTER_REQUEST_CALL(registrationRequest);
        customerRegisterResponse.enqueue(new Callback<CustomerRegisterRequest>() {
            @Override
            public void onResponse(@NonNull Call<CustomerRegisterRequest> call, @NonNull Response<CustomerRegisterRequest> response) {
                CustomerRegisterRequest registtnResponsemain = response.body();
                Log.e(TAG, "onResponse: " + new GsonBuilder().create().toJson(registtnResponsemain));
                if (response.code() == 200) {
                    UserPreferenceUtils.customMessage(CustomerRegistration.this, "Registration successfull");
                    hideProgressDialog();
                    finish();

                } else if (response.code() == 400) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerRegistration.this, "Email id already exists.");
                } else if (response.code() == 404) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerRegistration.this, "There is problem to register.");
                } else if (response.code() == 409) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerRegistration.this, "Email id already exists.");
                } else if (response.code() == 500) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerRegistration.this, "Internal server error.");
                } else {
                    hideProgressDialog();
                    assert registtnResponsemain != null;
                    // Toast.makeText(CustomerRegistration.this, registtnResponsemain.,  Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerRegisterRequest> call, Throwable t) {
                hideProgressDialog();
                UserPreferenceUtils.customMessage(CustomerRegistration.this, t.getMessage());
            }
        });
    }

    private boolean validate1() {
        boolean temp = true;
        String checkemail = email_add.getText().toString();

        if (!isValidEmaillId(checkemail)) {
            Toast.makeText(CustomerRegistration.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            temp = false;
        }
        return temp;

    }

    private boolean validate() {

        if (use_name.getText().toString().equals("")) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Valid User Name.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (email_add.getText().toString().equals("")) {
            Toast.makeText(CustomerRegistration.this, "Please enter email id.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (cont_no.getText().toString().equals("")) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Contact no.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (cont_no.getText().toString().equals("")) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Contact no.", Toast.LENGTH_LONG).show();
            return false;
        } else if (cont_no.getText().length() < 6) {
            Toast.makeText(CustomerRegistration.this, "Invalid Mobile Number.", Toast.LENGTH_LONG).show();
            return false;
        } else if (cont_no.getText().length() > 16) {
            Toast.makeText(CustomerRegistration.this, "Invalid Mobile Number.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (city.getSelectedItem().toString().equals("Select City")) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Your City", Toast.LENGTH_LONG).show();
            return false;

        }
        if (pincode.getText().toString().equals("")) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Your  Pin Code", Toast.LENGTH_LONG).show();
            return false;
        } else if (pincode.getText().length() < 3) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Valid Pin Code", Toast.LENGTH_LONG).show();
            return false;
        } else if (pincode.getText().length() > 10) {
            Toast.makeText(CustomerRegistration.this, "Please Enter Valid Pin Code.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }


    public static boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    Dialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new Dialog(CustomerRegistration.this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_dialog_progress);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();

    }

}
