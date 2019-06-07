package com.dotcom.nlcatering.CaterersActivity.CaterersCredentials;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerForgotPassward;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerResetPassward;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Request.CatererRequest.CatererResetPasswordRequest;
import com.dotcom.nlcatering.Response.CatererResponse.CatererForgotPasswardResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerForgotPassawardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaterersForgotPassword extends AppCompatActivity {

    Toolbar toolbar;
    TextView reset;
    EditText emaiId;
    Button submitPassword;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterers_forgot_password);
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour

        reset = (TextView) findViewById(R.id.resetpass);
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Forgot Password");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CaterersForgotPassword.this, CaterersResetPassward.class);
                startActivity(i);
            }
        });
        emaiId = (EditText) findViewById(R.id.member_email);
        submitPassword = (Button) findViewById(R.id.submit_password);


        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (UserPreferenceUtils.isOnline(CaterersForgotPassword.this)) {

                    if (validate()) {
                        final String id = emaiId.getText().toString().trim();
                        forgotPassword(id);
                    }

                } else {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CaterersForgotPassword.this);
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

    }


    private void forgotPassword(String emailId) {
        showProgressDialog();

        Call<CatererForgotPasswardResponse> catererForgotPasswardResponseCall = Utilss.getWebService().CATERER_FORGOT_PASSWARD_RESPONSE_CALL(emailId);
        catererForgotPasswardResponseCall.enqueue(new Callback<CatererForgotPasswardResponse>() {
            @Override
            public void onResponse(Call<CatererForgotPasswardResponse> call, Response<CatererForgotPasswardResponse> response) {
                if (response.code() == 200) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CaterersForgotPassword.this);
                    builder.setMessage("Password send on your email id. Please check your email id")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    finish();

                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                } else if (response.code() == 400) {
                    UserPreferenceUtils.customMessage(CaterersForgotPassword.this, "Please Enter correct email id");
                } else if (response.code() == 404) {
                    UserPreferenceUtils.customMessage(CaterersForgotPassword.this, "Email Id Not Found");
                } else if (response.code() == 500) {
                    UserPreferenceUtils.customMessage(CaterersForgotPassword.this, "Internal Server Error");
                } else {
                    UserPreferenceUtils.customMessage(CaterersForgotPassword.this, "Something is wroung");
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<CatererForgotPasswardResponse> call, Throwable t) {
                hideProgressDialog();
            }
        });
    }

    private boolean validate() {
        if (emaiId.getText().toString().length() == 0) {
            UserPreferenceUtils.customMessage(CaterersForgotPassword.this, "Pleas Enter Email Id");
            return false;
        }
        return true;

    }

    Dialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new Dialog(CaterersForgotPassword.this);
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
