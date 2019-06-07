package com.dotcom.nlcatering.CustomerActivity.CustomerCredentials;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerQuotation;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.FoodType;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerLoginResponse;
import com.dotcom.nlcatering.SingletonClass.SingletonClass;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity.profileName;

public class CustomerLogin extends AppCompatActivity {

    Toolbar toolbar;
    CheckBox showPassward;
    EditText customerUsername, customerPass;
    String username, password;
    Button btn_login;
    TextView txt_skip, reg_txt, forgotpass;
    String loginstatus = "false";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);
        initView();

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.actionbar));
        reg_txt = (TextView) findViewById(R.id.regstetext);
        forgotpass = (TextView) findViewById(R.id.forgotpassword);
        showPassward = (CheckBox) findViewById(R.id.showPassward);


        showPassward.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (!isChecked) {
                    // show password
                    customerPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    customerPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        reg_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerLogin.this, CustomerRegistration.class);
                startActivity(i);
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CustomerLogin.this, CustomerForgotPassward.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = customerUsername.getText().toString();
                password = customerPass.getText().toString();


                if (UserPreferenceUtils.isOnline(CustomerLogin.this)) {
                    if (validate()) {
                        Login(username, password);
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CustomerLogin.this);
                    builder.setTitle("Internet problem");
                    builder.setMessage("Oops! seems you have lost internet connectivity. Please try again later.");
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alert = builder.create();
                    alert.show();
                    Button nbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
                    nbutton.setTextColor(Color.rgb(30, 144, 255));
                }


            }


        });
    }


    private void Login(String email, String pass) {
        showProgressDialog();
        Log.e(" ", "sendSignIn: " + email + " " + pass);

        Call<CustomerLoginResponse> customerLoginResponseCall = Utilss.getWebService().login(email, pass);
        customerLoginResponseCall.enqueue(new Callback<CustomerLoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<CustomerLoginResponse> call, @NonNull Response<CustomerLoginResponse> response) {
                CustomerLoginResponse customerLoginResponse = response.body();
                Log.e(" ", "onResponse: " + new GsonBuilder().create().toJson(customerLoginResponse));
                if (response.code() == 200) {
                    hideProgressDialog();
                    assert customerLoginResponse != null;
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.USER_NAME, customerLoginResponse.getCust_Name());
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.EMAIL, customerLoginResponse.getEmail());
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.CUST_ID, String.valueOf(customerLoginResponse.getCustomer_Id()));
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.ADDRESS, customerLoginResponse.getAddress());
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.MOBILE, customerLoginResponse.getMobileNo());
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.CITY, customerLoginResponse.getCity());
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.PINCODE, customerLoginResponse.getPinCode());
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.LOGIN_TYPE, customerLoginResponse.getLoginType());
                    SingletonClass.getInstance().setUSERID(String.valueOf(customerLoginResponse.getCustomer_Id()));
                    profileName.setText(customerLoginResponse.getCust_Name());
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KEY", String.valueOf(String.valueOf(customerLoginResponse.getCustomer_Id())));
                    editor.apply();
                    loginstatus = "true";
                    new UserSessionManager(getApplicationContext()).setUrlData(getApplicationContext(), loginstatus, UserSessionManager.LOGIN_STATUS);
                    UserPreferenceUtils.saveUserPreference(CustomerLogin.this, Constants.FIRST_NAME, customerLoginResponse.getCust_Name());
                    finish();

                } else if (response.code() == 404) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerLogin.this, "Please check email id and password.!!");
                } else if (response.code() == 400) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerLogin.this, "Please check email id and password.!!");
                } else if (response.code() == 500) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerLogin.this, "Internal server error.!!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<CustomerLoginResponse> call, @NonNull Throwable t) {
                UserPreferenceUtils.customMessage(CustomerLogin.this, t.getMessage());
                hideProgressDialog();
            }
        });

    }

    private void initView() {


        customerUsername = (EditText) findViewById(R.id.customerUsername);
        customerPass = (EditText) findViewById(R.id.customerPass);
        btn_login = (Button) findViewById(R.id.btn_login);

    }


    private boolean validate() {
        if (customerUsername.getText().toString().equals("")) {
            Toast.makeText(CustomerLogin.this, "Please enter email id.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (customerPass.getText().toString().equals("")) {
            Toast.makeText(CustomerLogin.this, "Please enter password.", Toast.LENGTH_LONG).show();
            return false;
        } else if (customerPass.getText().length() < 6) {
            Toast.makeText(CustomerLogin.this, "Password must be at least 6 characters long.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count <= 1) {
                    Intent i = new Intent(CustomerLogin.this, FoodType.class);
                    startActivity(i);
                    finish();
                } else {
                    getSupportFragmentManager().popBackStack();
                }
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }


    Dialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new Dialog(CustomerLogin.this);
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