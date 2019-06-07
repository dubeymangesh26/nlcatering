package com.dotcom.nlcatering.CaterersActivity.CaterersCredentials;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub.CatererMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerQuotation;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerForgotPassward;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CatererResponse.CatererLoginResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerLoginResponse;
import com.dotcom.nlcatering.SingletonClass.CatererMainSingletonClass;
import com.dotcom.nlcatering.SingletonClass.SingletonClass;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatererLogin extends AppCompatActivity {
    Toolbar toolbar;
    CheckBox showPassward;
    EditText catererUsername, catererPass;
    String username, password;
    Button btn_login;
    TextView txt_skip, reg_txt, forgotpass;
    private ProgressDialog pDialog;
    private AppCompatAutoCompleteTextView autoTextViewCustom;
    public String TAG = CatererLogin.class.getSimpleName();
    String loginstatus = "false";


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_login);
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour

        reg_txt = (TextView) findViewById(R.id.regstetext);
        forgotpass = (TextView) findViewById(R.id.forgotpassword);
        showPassward = (CheckBox) findViewById(R.id.showPassward);

        catererUsername = (EditText) findViewById(R.id.catererUsername);
        catererPass = (EditText) findViewById(R.id.catererPass);
        btn_login = (Button) findViewById(R.id.btn_login);

        showPassward.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (!isChecked) {
                    // show password
                    catererPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    catererPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        reg_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CatererLogin.this, CatererRegistration.class);
                startActivity(i);
                // finish();
            }
        });
        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CatererLogin.this, CustomerForgotPassward.class);
                startActivity(i);
                // finish();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = catererUsername.getText().toString();
                password = catererPass.getText().toString();


                if (UserPreferenceUtils.isOnline(CatererLogin.this)) {
                    if (validate()) {
                        Login(username, password);
                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CatererLogin.this);
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
        final Dialog dialog = new Dialog(CatererLogin.this);
        CustomerLogin loginResponse = new CustomerLogin();
        showProgressDialog();

        Log.e(" ", "sendSignIn: " + email + " " + pass);

        Call<CatererLoginResponse> customerLoginResponseCall = Utilss.getWebService().CATERER_LOGIN_RESPONSE_CALL(email, pass);
        customerLoginResponseCall.enqueue(new Callback<CatererLoginResponse>() {
            @Override
            public void onResponse(@NonNull Call<CatererLoginResponse> call, @NonNull Response<CatererLoginResponse> response) {
                CatererLoginResponse customerLoginResponse = response.body();
                Log.e(" ", "onResponse: " + new GsonBuilder().create().toJson(  customerLoginResponse));
                if (response.code() == 200) {
                    hideProgressDialog();
                    assert customerLoginResponse != null;
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.COMPANY_NAME, customerLoginResponse.getCompany_Name());
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.EMAIL, customerLoginResponse.getEmail());
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.CATERER_ID, (String.valueOf(customerLoginResponse.getCaterers_Id())));
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.ADDRESS, customerLoginResponse.getAddress());
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.CATERER_TYPE, customerLoginResponse.getTypecatering());
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.CITY, customerLoginResponse.getCity());
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.LOGIN_TYPE, customerLoginResponse.getLoginType());
                    CatererMainSingletonClass.getInstance().setUSERID(String.valueOf(customerLoginResponse.getCaterers_Id()));
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("KEY", String.valueOf(String.valueOf(customerLoginResponse.getCaterers_Id())));
                    editor.apply();
                    loginstatus = "true";
                    new UserSessionManager(getApplicationContext()).setUrlData(getApplicationContext(), loginstatus, UserSessionManager.LOGIN_STATUS);
                    UserPreferenceUtils.saveUserPreference(CatererLogin.this, Constants.FIRST_NAME, customerLoginResponse.getCompany_Name());


                      /*  Log.e("", "groomid" + guestLoginResponse.getGroomId());
                        Log.e("", "bridid" + guestLoginResponse.getBrideId());
*/

                    Intent intent = new Intent(CatererLogin.this, CatererMainActivity.class);
                    startActivity(intent);
                    finish();

                } else if (response.code() == 404) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererLogin.this, "Please check email id and password.!!");
                } else if (response.code() == 400) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererLogin.this, "Please check email id and password.!!");
                } else if (response.code() == 500) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererLogin.this, "Internal server error.!!");
                }
            }

            @Override
            public void onFailure(@NonNull Call<CatererLoginResponse> call, @NonNull Throwable t) {
                UserPreferenceUtils.customMessage(CatererLogin.this, t.getMessage());
                hideProgressDialog();
            }
        });

    }

    private void initView() {


        catererUsername = (EditText) findViewById(R.id.customerUsername);
        catererPass = (EditText) findViewById(R.id.customerPass);
        btn_login = (Button) findViewById(R.id.btn_login);

    }


    private boolean validate() {
        if (catererUsername.getText().toString().equals("")) {
            Toast.makeText(CatererLogin.this, "Please enter email id.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (catererPass.getText().toString().equals("")) {
            Toast.makeText(CatererLogin.this, "Please enter password.", Toast.LENGTH_LONG).show();
            return false;
        } else if (catererPass.getText().length() < 6) {
            Toast.makeText(CatererLogin.this, "Password must be at least 6 characters long.", Toast.LENGTH_LONG).show();
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
                    Intent i = new Intent(CatererLogin.this, CustomerMainActivity.class);
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
        progressDialog = new Dialog(CatererLogin.this);
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
