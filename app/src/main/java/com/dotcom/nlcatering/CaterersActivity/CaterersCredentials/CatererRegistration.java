
package com.dotcom.nlcatering.CaterersActivity.CaterersCredentials;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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

import com.dotcom.nlcatering.Activity.MultiSelectionSpinner;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerRegistration;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Request.CatererRequest.CatererRegisterRequest;
import com.dotcom.nlcatering.Response.CatererResponse.CatererCityAndCatererTypeResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererRegisterResponse;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CatererRegistration extends AppCompatActivity {
    MultiSelectionSpinner typeCatering;
    Toolbar toolbar;
    TextView signintxt;
    Spinner city;
    AutoCompleteTextView website,kyk,pincode,workArea;
    EditText companyName, address, conttctno1, conttctno2, emailAddress;
    Button btnSignup;
    public String TAG = CatererRegistration.class.getSimpleName();

    List<String> cityListName;
    List<Integer> cityListId;
    List<String> catererTypeName;
    List<Integer> catererTypeID;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_registration);
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour
        cityListName = new ArrayList<>();
        cityListId = new ArrayList<>();
        catererTypeName = new ArrayList<>();
        catererTypeID = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
        signintxt = (TextView) findViewById(R.id.signintxt);

        companyName = (EditText) findViewById(R.id.companyName);
        address = (EditText) findViewById(R.id.address);
        conttctno1 = (EditText) findViewById(R.id.conttctno1);
        conttctno2 = (EditText) findViewById(R.id.conttctno2);
        emailAddress = (EditText) findViewById(R.id.emailAddress);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        typeCatering = (MultiSelectionSpinner) findViewById(R.id.typeCatering);
        website = (AutoCompleteTextView) findViewById(R.id.website);
        kyk = (AutoCompleteTextView) findViewById(R.id.kyk);
        city = (Spinner) findViewById(R.id.city);
        pincode = (AutoCompleteTextView) findViewById(R.id.pincode);
        workArea = (AutoCompleteTextView) findViewById(R.id.workArea);

        toolbar.setTitle("Register");
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

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserPreferenceUtils.isOnline(CatererRegistration.this)) {
                    if (validate()) {
                        String company = companyName.getText().toString().trim();
                        String webste = website.getText().toString().trim();
                        String emailId = emailAddress.getText().toString().trim();
                        String mob1 = conttctno1.getText().toString().trim();
                        String mob2 = conttctno2.getText().toString().trim();
                        String worlaea = workArea.getText().toString().trim();
                        String ctyy = city.getSelectedItem().toString();
                        String cat_type =typeCatering.getSelectedItem().toString();
                        String kyak = kyk.getText().toString().trim();
                        String add = address.getText().toString().trim();
                        String pin = pincode.getText().toString().trim();

                        if (validate1()) {
                            customerRegistration(company,webste, emailId, mob1,mob2,worlaea, ctyy, cat_type,kyak,add,pin);
                        }

                    }

                } else {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(CatererRegistration.this);
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
        getCatererType();
    }

    private void getCatererType() {

        showProgressDialog();
        Call<CatererCityAndCatererTypeResponse> cityAndCatererTypeResponseCall = Utilss.getWebService().CATERER_CITY_AND_CATERER_TYPE_RESPONSE_CALL();
        cityAndCatererTypeResponseCall.enqueue(new Callback<CatererCityAndCatererTypeResponse>() {
            @Override
            public void onResponse(Call<CatererCityAndCatererTypeResponse> call, Response<CatererCityAndCatererTypeResponse> response) {
                Log.e("data", "onResponse: " + new GsonBuilder().create().toJson(response.body()));
                CatererCityAndCatererTypeResponse activityResponse = response.body();
                hideProgressDialog();
                if (response.code() == 200) {

                    if (activityResponse.getResponse().getFoodMenu().size() > 0) {

                       //catererTypeName.add("Select Caterer Type");

                        for (int i = 0; i < activityResponse.getResponse().getFoodMenu().size(); i++) {

                            catererTypeName.add(activityResponse.getResponse().getFoodMenu().get(i).getCat_Name());
                            catererTypeID.add(activityResponse.getResponse().getFoodMenu().get(i).getCat_Id());
                            typeCatering.setItems(catererTypeName);


                        }

                       /* //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CatererRegistration.this, R.layout.spinner_dropdown,  catererTypeName);
                        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        typeCatering.setAdapter(adapter);*/


                    }
                } else {
                    Toast.makeText(CatererRegistration.this, "You have Not Any Enquiry", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();

                }

            }

            @Override
            public void onFailure(Call<CatererCityAndCatererTypeResponse> call, Throwable t) {
                hideProgressDialog();
            }
        });


    }

    private void getCityList() {


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
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CatererRegistration.this, R.layout.spinner_dropdown, cityListName);
                        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        city.setAdapter(adapter);
                    }
                } else {
                    Toast.makeText(CatererRegistration.this, "You have Not Any Enquiry", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();

                }

            }

            @Override
            public void onFailure(Call<CatererCityAndCatererTypeResponse> call, Throwable t) {

            }
        });
    }


    private void customerRegistration(String company, String webste, String emailId,String mob1,String mob2,String worlaea,String ctyy,String cat_type,String kyak,String add,String pin) {
        final Dialog dialog = new Dialog(CatererRegistration.this);
        showProgressDialog();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds")

        CatererRegisterRequest registrationRequest = new CatererRegisterRequest();
        registrationRequest.setCompany_Name(company);
        registrationRequest.setWebsite(webste);
        registrationRequest.setEmail(emailId);
        registrationRequest.setTelephone(mob1);
        registrationRequest.setTelephone2(mob2);
        registrationRequest.setWork_area(worlaea);
        registrationRequest.setCity(ctyy);
        registrationRequest.setTypecatering(cat_type);
        registrationRequest.setKVK(kyak);
        registrationRequest.setAddress(add);
        registrationRequest.setPinCode(pin);


        Log.e(TAG, "registration request : " + new GsonBuilder().create().toJson(registrationRequest));

        Call<CatererRegisterResponse> customerRegisterResponse = Utilss.getWebService().CATERER_REGISTER_RESPONSE_CALL(registrationRequest);
        customerRegisterResponse.enqueue(new Callback<CatererRegisterResponse>() {
            @Override
            public void onResponse(@NonNull Call<CatererRegisterResponse> call, @NonNull Response<CatererRegisterResponse> response) {
                CatererRegisterResponse registtnResponsemain = response.body();
                Log.e(TAG, "onResponse: " + new GsonBuilder().create().toJson(registtnResponsemain));
                if (response.code() == 200) {
                    UserPreferenceUtils.customMessage(CatererRegistration.this, "Registration successfull");
                    hideProgressDialog();
                    startActivity(new Intent(CatererRegistration.this, CatererLogin.class));
                    finish();

                } else if (response.code() == 400) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererRegistration.this, "Email id already exists.");
                } else if (response.code() == 404) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererRegistration.this, "There is problem to register.");
                } else if (response.code() == 409) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererRegistration.this, "Email id already exists.");
                } else if (response.code() == 500) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CatererRegistration.this, "Internal server error.");
                } else {
                    hideProgressDialog();
                    assert registtnResponsemain != null;
                    // Toast.makeText(CustomerRegistration.this, registtnResponsemain.,  Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CatererRegisterResponse> call, Throwable t) {
                hideProgressDialog();
                UserPreferenceUtils.customMessage(CatererRegistration.this, t.getMessage());
            }
        });
    }

    private boolean validate1() {
        boolean temp = true;
        String checkemail = emailAddress.getText().toString();

        if (!isValidEmaillId(checkemail)) {
            Toast.makeText(CatererRegistration.this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            temp = false;
        }
        return temp;

    }

    private boolean validate() {

        if (companyName.getText().toString().equals("")) {
            Toast.makeText(CatererRegistration.this, "Please Enter Valid User Name.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (emailAddress.getText().toString().equals("")) {
            Toast.makeText(CatererRegistration.this, "Please enter email id.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (conttctno1.getText().toString().equals("")) {
            Toast.makeText(CatererRegistration.this, "Please Enter Contact no.", Toast.LENGTH_LONG).show();
            return false;
        } else if (conttctno1.getText().length() < 6) {
            Toast.makeText(CatererRegistration.this, "Invalid Mobile Number.", Toast.LENGTH_LONG).show();
            return false;
        } else if (conttctno1.getText().length() > 16) {
            Toast.makeText(CatererRegistration.this, "Invalid Mobile Number.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (city.getSelectedItem().toString().equals("Select City")) {
            Toast.makeText(CatererRegistration.this, "Please Select Your City", Toast.LENGTH_LONG).show();
            return false;
        }if (typeCatering.getSelectedItem().toString().equals("Select Caterer Type")) {
            Toast.makeText(CatererRegistration.this, "Please Enter Your Caterer Type", Toast.LENGTH_LONG).show();
            return false;

        }/*if (typeCatering.getSelectedItem().toString().equals("2") ) {
        Toast.makeText(CatererRegistration.this, "Please Select Only Two Type of Catering", Toast.LENGTH_LONG).show();
        return false;

    }*/
        if (pincode.getText().toString().equals("")) {
            Toast.makeText(CatererRegistration.this, "Please Enter Your  Pin Code", Toast.LENGTH_LONG).show();
            return false;
        }else if (pincode.getText().length() <3) {
            Toast.makeText(CatererRegistration.this, "Please Enter Valid Pin Code", Toast.LENGTH_LONG).show();
            return false;
        }else if (pincode.getText().length() >10) {
            Toast.makeText(CatererRegistration.this, "Please Enter Valid Pin Code.", Toast.LENGTH_LONG).show();
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
        progressDialog = new Dialog(CatererRegistration.this);
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