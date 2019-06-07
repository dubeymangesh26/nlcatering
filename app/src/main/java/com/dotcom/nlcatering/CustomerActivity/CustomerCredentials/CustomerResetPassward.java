package com.dotcom.nlcatering.CustomerActivity.CustomerCredentials;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererRegistration;
import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CaterersResetPassward;
import com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub.CatererMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerResetPasswardRequest;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerResetPasswardResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerResetPassward extends AppCompatActivity {
    Toolbar toolbar;
    EditText oPassword, nPassword, emails, cPassword;
    Button changePassword;
    ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_passward);


       oPassword = (EditText) findViewById(R.id.oldpassword);
        nPassword = (EditText) findViewById(R.id.newpassword);
        emails = (EditText) findViewById(R.id.emailid);
        cPassword = (EditText) findViewById(R.id.conformpassword);
        changePassword = (Button) findViewById(R.id.resetpasswrd);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Reset Password");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (UserPreferenceUtils.isOnline(CustomerResetPassward.this)) {

                    final String oldPassw = oPassword.getText().toString().trim();
                    final String newPassw = nPassword.getText().toString().trim();
                    final String emailadd = emails.getText().toString().trim();
                  //  final String cnfrmpass = cPassword.getText().toString().trim();


                    if (validate())
                        changePassword(newPassw, oldPassw, emailadd);
                } else {

                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CustomerResetPassward.this);
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

    private void changePassword(String newPass, String oldPass, final String email) {

        showProgressDialog();
        CustomerResetPasswardRequest changePassRequest = new CustomerResetPasswardRequest();
        changePassRequest.setEmail(email);
        changePassRequest.setPassword(oldPass);
      //  changePassRequest.equals(cnfrmpasrd);
        changePassRequest.setNewPassword(newPass);

        Call<CustomerResetPasswardResponse> changePassResponseCall = Utilss.getWebService().CUSTOMER_RESET_PASSWARD_RESPONSE_CALL(changePassRequest);
        changePassResponseCall.enqueue(new Callback<CustomerResetPasswardResponse>() {
            @Override
            public void onResponse(Call<CustomerResetPasswardResponse> call, Response<CustomerResetPasswardResponse> response) {
                //    ChangePasswordResponse guestFeedbackResponse = response.body();
                //  Log.e("FunctionResponse ", "onResponse: " + new GsonBuilder().create().toJson(guestFeedbackResponse));
                if (response.code() == 200) {

                    UserPreferenceUtils.customMessage(CustomerResetPassward.this, "Password Change successfully");
                    oPassword.setText("");
                    nPassword.setText("");
                    emails.setText("");
                    Intent intent = new Intent(CustomerResetPassward.this, CustomerMainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();


                } else if (response.code() == 400) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CustomerResetPassward.this);
                    builder.setMessage("Old Password does not match")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                    oPassword.setText("");
                                    nPassword.setText("");
                                    emails.setText("");


                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();

                } else if (response.code() == 404) {
                    UserPreferenceUtils.customMessage(CustomerResetPassward.this, "Old Password does not match");
                    oPassword.setText("");
                    nPassword.setText("");
                    emails.setText("");


                } else if (response.code() == 500) {
                    UserPreferenceUtils.customMessage(CustomerResetPassward.this, "Internal Server Error");
                    oPassword.setText("");
                    nPassword.setText("");
                    emails.setText("");

                } else {
                    UserPreferenceUtils.customMessage(CustomerResetPassward.this, "Something is wroung");
                    oPassword.setText("");
                    nPassword.setText("");
                    emails.setText("");
                }
                hideProgressDialog();
            }

            @Override
            public void onFailure(Call<CustomerResetPasswardResponse> call, Throwable t) {
                hideProgressDialog();
            }
        });
    }


    private boolean validate() {
        String pass = nPassword.getText().toString();
        String cpass = cPassword.getText().toString();
        if (oPassword.getText().toString().length() == 0) {
            UserPreferenceUtils.customMessage(CustomerResetPassward.this, "Pleas Enter Old Password");
            return false;
        } else if (nPassword.getText().toString().length() == 0) {
            UserPreferenceUtils.customMessage(CustomerResetPassward.this, "Pleas Enter New Password");
            return false;
        } else if (!pass.equals(cpass)) {
            Toast.makeText(CustomerResetPassward.this, "Password Not matching", Toast.LENGTH_SHORT).show();
            return false;

        }
        return true;

    }
    Dialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new Dialog(CustomerResetPassward.this);
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
