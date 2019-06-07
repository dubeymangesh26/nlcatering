package com.dotcom.nlcatering.CaterersActivity.CaterersEnquiries;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererRegistration;
import com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub.CatererMainActivity;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Request.CatererRequest.CatererEnquiryBiddingRequest;
import com.dotcom.nlcatering.Request.CatererRequest.CatererRegisterRequest;
import com.dotcom.nlcatering.Response.CatererResponse.CatererEnquiryBiddingResponse;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaterersEnquiryDetails extends AppCompatActivity {
    Toolbar toolbar;
    int noOfPerson, enquiryId;
    String catType, dates, time, place, decription, bidstatus;
    UserSessionManager session;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterers_enquiry_details);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.actionbar)); //Status bar colour
        session = new UserSessionManager(getApplicationContext());

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Enquiry Bid Details");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView text = (TextView) findViewById(R.id.name);
        TextView text2 = (TextView) findViewById(R.id.noOfPerson1);
        TextView text3 = (TextView) findViewById(R.id.date);
        TextView text4 = (TextView) findViewById(R.id.time);
        TextView text5 = (TextView) findViewById(R.id.place);
        TextView text6 = (TextView) findViewById(R.id.decription);
        TextView text7 = (TextView) findViewById(R.id.bidstatus);

        Button bid = (Button) findViewById(R.id.bid);

        catType = getIntent().getExtras().getString("typecatering");
        noOfPerson = getIntent().getExtras().getInt("person");
        time = getIntent().getExtras().getString("time");
        place = getIntent().getExtras().getString("place");
        dates = getIntent().getExtras().getString("date");
        decription = getIntent().getExtras().getString("description");
        bidstatus = getIntent().getExtras().getString("bidstatus");
        enquiryId = getIntent().getExtras().getInt("enquiryId");
        bid.setVisibility(bidstatus.contentEquals("Bid Pending")? View.VISIBLE : View.GONE);//Bid status check .

        text2.setText(String.valueOf(noOfPerson));
        text.setText(catType);
        String date[] = dates.split("T");
        String  reverseDate[] = date[0].split("-");
        String actualDate = reverseDate[2]+"-"+reverseDate[1]+"-"+reverseDate[0];
        text3.setText(actualDate);
        text4.setText(time);
        text5.setText(place);
        text6.setText(decription);
        text7.setText(bidstatus);
        bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Dialog dialog = new Dialog(CaterersEnquiryDetails.this);
                dialog.setContentView(R.layout.bidsend_dialog);
                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.CENTER;

                ImageView close = (ImageView) dialog.findViewById(R.id.cancle_image);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();



                    }
                });

                final TextInputEditText amount = dialog.findViewById(R.id.bidAmount);
                final TextInputEditText description = dialog.findViewById(R.id.description);
                Button bidSend = dialog.findViewById(R.id.bidSend);


                bidSend.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (UserPreferenceUtils.isOnline(CaterersEnquiryDetails.this)) {
                            if (validate()) {
                                String amt = amount.getText().toString().trim();
                                String desc = description.getText().toString().trim();

                                if (validate1()) {
                                    catererEnquiry(amt, desc);
                                }


                            }

                        } else {
                            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CaterersEnquiryDetails.this);
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

                    private void catererEnquiry(String amt, String desc) {
                        showProgressDialog();
                        final CatererEnquiryBiddingRequest catererEnquiryBiddingRequest = new CatererEnquiryBiddingRequest();
                        catererEnquiryBiddingRequest.setTotal_Amount(Integer.valueOf(amt));
                        catererEnquiryBiddingRequest.setDescription(desc);
                        catererEnquiryBiddingRequest.setEnquiry_Id(enquiryId);
                        catererEnquiryBiddingRequest.setOrder_Type(catType);
                        catererEnquiryBiddingRequest.setQty(noOfPerson);
                        String userId = Utilss.getStringUserPreference(CaterersEnquiryDetails.this, Constants.CATERER_ID);
                        catererEnquiryBiddingRequest.setCaterers_Id(Integer.parseInt(userId));
                        Log.e("CaterEnquiry Details", "catererEnquiry: " + new GsonBuilder().create().toJson(catererEnquiryBiddingRequest));
                        Call<CatererEnquiryBiddingResponse> responseCall = Utilss.getWebService().CATERER_ENQUIRY_BIDDING_RESPONSE_CALL(catererEnquiryBiddingRequest);
                        responseCall.enqueue(new Callback<CatererEnquiryBiddingResponse>() {
                            @Override
                            public void onResponse(Call<CatererEnquiryBiddingResponse> call, Response<CatererEnquiryBiddingResponse> response) {
                                CatererEnquiryBiddingResponse enquiryBiddingResponse = response.body();
                                Log.e(" ", "onResponse: " + new GsonBuilder().create().toJson(enquiryBiddingResponse));
                                if (response.code() == 200) {
                                    UserPreferenceUtils.customMessage(CaterersEnquiryDetails.this, "Bid Send successfull");
                                    dialog.dismiss();
                                    hideProgressDialog();
                                    Intent intent = new Intent(CaterersEnquiryDetails.this, CaterersEnquiryList.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();

                                } else if (response.code() == 400) {
                                    hideProgressDialog();
                                    UserPreferenceUtils.customMessage(CaterersEnquiryDetails.this, "Something Went Wrong.");
                                } else if (response.code() == 404) {
                                    hideProgressDialog();
                                    UserPreferenceUtils.customMessage(CaterersEnquiryDetails.this, "There is problem to register.");
                                } else if (response.code() == 409) {
                                    hideProgressDialog();
                                    UserPreferenceUtils.customMessage(CaterersEnquiryDetails.this, "Email id already exists.");
                                } else if (response.code() == 500) {
                                    hideProgressDialog();
                                    UserPreferenceUtils.customMessage(CaterersEnquiryDetails.this, "Internal server error.");
                                } else {
                                    hideProgressDialog();
                                    assert enquiryBiddingResponse != null;
                                    // Toast.makeText(CustomerRegistration.this, registtnResponsemain.,  Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<CatererEnquiryBiddingResponse> call, Throwable t) {

                            }
                        });


                    }



                    private boolean validate1() {
                        return true;
                    }

                    private boolean validate() {
                        if (amount.getText().toString().equals("")) {
                            Toast.makeText(CaterersEnquiryDetails.this, "Please Enter Amount", Toast.LENGTH_LONG).show();
                            return false;
                        }

                        return true;


                    }


                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });


    }

    Dialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new Dialog(CaterersEnquiryDetails.this);
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
