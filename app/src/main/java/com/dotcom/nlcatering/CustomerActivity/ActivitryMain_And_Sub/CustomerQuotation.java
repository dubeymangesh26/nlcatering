package com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererRegistration;
import com.dotcom.nlcatering.CaterersActivity.CaterersEnquiries.CaterersEnquiryDetails;
import com.dotcom.nlcatering.CaterersActivity.CaterersOrders.CaterersOrderList;
import com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub.CatererMainActivity;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Request.CatererRequest.CatererRegisterRequest;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerQuotationEnquireyRequest;
import com.dotcom.nlcatering.Response.CatererResponse.CatererCityAndCatererTypeResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererRegisterResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerQuotationEnquireyResponse;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerQuotation extends AppCompatActivity {
    Button quotSubmit;
    Toolbar toolbar;
    Spinner typeCatering;
    EditText selectdate, time, noOfPerson, placeOfPerformance, description;
    AutoCompleteTextView keepWarm,withStaff;
    List<String> catererTypeName;
    List<Integer> catererTypeID;
    String amPm;
    int currentHour;
    int currentMinute;
    List<String> KeepWarm = new ArrayList<>();
    List<String> Withstaff = new ArrayList<>();
    public String TAG = CustomerQuotation.class.getSimpleName();
    final Calendar myCalendar = Calendar.getInstance();
    UserSessionManager session;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_quotation);

        session = new UserSessionManager(getApplicationContext());
        catererTypeName = new ArrayList<>();
        catererTypeID = new ArrayList<>();

        quotSubmit=findViewById(R.id.quotSubmit);
        selectdate = (EditText) findViewById(R.id.selectdate);
        time = (EditText) findViewById(R.id.time);
        noOfPerson = (EditText) findViewById(R.id.noOfPerson);
        placeOfPerformance = (EditText) findViewById(R.id.placeOfPerformance);
        description = (EditText) findViewById(R.id.description);
        typeCatering = (Spinner) findViewById(R.id.typeCatering);
        keepWarm = (AutoCompleteTextView) findViewById(R.id.keepWarm);
        withStaff = (AutoCompleteTextView) findViewById(R.id.withStaff);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Request For A Quote");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerQuotation.this, CustomerMainActivity.class);
                startActivity(i);
                onBackPressed();
            }
        });
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.orangenormal)); //Status bar colour

        final ArrayAdapter<String> loadTypeArrayAdapter = new ArrayAdapter<>(CustomerQuotation.this, R.layout.custom_spinner_item, KeepWarm);
        loadTypeArrayAdapter.setDropDownViewResource(R.layout.custom_spinner_m_dropdown_item);
        keepWarm.setThreshold(1);
        keepWarm.setAdapter(loadTypeArrayAdapter);
        KeepWarm.add("Yes");
        keepWarm.getAutoLinkMask();
        KeepWarm.add("No");
        keepWarm.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                keepWarm.showDropDown();
                return false;
            }
        });
        final ArrayAdapter<String> loadTypeArrayAdapter1 = new ArrayAdapter<>(CustomerQuotation.this, R.layout.custom_spinner_item, Withstaff);
        loadTypeArrayAdapter1.setDropDownViewResource(R.layout.custom_spinner_m_dropdown_item);
        withStaff.setThreshold(0);
        withStaff.setAdapter(loadTypeArrayAdapter1);
        Withstaff.add("Yes");
        Withstaff.add("No");
        withStaff.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                withStaff.showDropDown();
                return false;
            }
        });






        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

            private void updateLabel() {
                String myFormat = "yyyy/MM/dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                selectdate.setText(sdf.format(myCalendar.getTime()));
                // enddtae.setText(sdf.format(myCalendar.getTime()));
            }

        };
        selectdate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(CustomerQuotation.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                int second = mcurrentTime.get(Calendar.SECOND);
                TimePickerDialog mTimePicker;

                mTimePicker = new TimePickerDialog(CustomerQuotation.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                       // time.setText(selectedHour + ":" + selectedMinute);
                        if (selectedHour >= 12) {
                            amPm = "PM";
                        } else {
                            amPm = "AM";
                        }
                        time.setText(String.format("%02d:%02d", selectedHour, selectedMinute) + amPm);
                    }
                }, currentHour, currentMinute, false);

                mTimePicker.show();
            }

        });

        quotSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserPreferenceUtils.isOnline(CustomerQuotation.this)) {
                    if (validate()) {
                        String datee = selectdate.getText().toString().trim();
                        String timee = time.getText().toString().trim();
                        String noOfPer = noOfPerson.getText().toString().trim();
                        String placeOfPe = placeOfPerformance.getText().toString().trim();
                        String desc = description.getText().toString().trim();
                        String cat_type =typeCatering.getSelectedItem().toString();
                        String keepWar = keepWarm.getText().toString().trim();
                        String withStaf = withStaff.getText().toString().trim();

                        if (validate1()) {
                            customerquot(datee,timee, noOfPer, placeOfPe,desc,cat_type,keepWar,withStaf);
                        }

                    }

                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CustomerQuotation.this);
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

                        catererTypeName.add("Select Caterer Type");
                        for (int i = 0; i < activityResponse.getResponse().getFoodMenu().size(); i++) {
                            catererTypeName.add(activityResponse.getResponse().getFoodMenu().get(i).getCat_Name());
                            catererTypeID.add(activityResponse.getResponse().getFoodMenu().get(i).getCat_Id());
                        }

                        //Creating the ArrayAdapter instance having the country list
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CustomerQuotation.this, R.layout.spinner_dropdown, catererTypeName);
                        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
                        //Setting the ArrayAdapter data on the Spinner
                        typeCatering.setAdapter(adapter);

                    }
                } else {
                    Toast.makeText(CustomerQuotation.this, "You have Not Any Enquiry", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();

                }

            }

            @Override
            public void onFailure(Call<CatererCityAndCatererTypeResponse> call, Throwable t) {
                hideProgressDialog();
            }
        });


    }

    private void customerquot(String datee, String timee, String noOfPer,String placeOfPe,String desc,String cat_type,String keepWar,String withStaf) {
        final Dialog dialog = new Dialog(CustomerQuotation.this);
        showProgressDialog();
        TelephonyManager manager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        @SuppressLint("HardwareIds")

        CustomerQuotationEnquireyRequest registrationRequest = new CustomerQuotationEnquireyRequest();
        registrationRequest.setOrder_Date(datee);
        registrationRequest.setTime(timee);
        registrationRequest.setNo_of_person(Integer.valueOf(noOfPer));
        registrationRequest.setPlace_of_performance(placeOfPe);
        registrationRequest.setDescription(desc);
        registrationRequest.setType_catering(cat_type);
        registrationRequest.setKeep_Worm(Boolean.getBoolean(keepWar));
        registrationRequest.setWith_staff(Boolean.getBoolean(withStaf));
        String userId = Utilss.getStringUserPreference(CustomerQuotation.this, Constants.CUST_ID);
        registrationRequest.setCustomer_Id(Integer.parseInt(userId));

        Log.e(" ", "registration request : " + new GsonBuilder().create().toJson(registrationRequest));

        Call<CustomerQuotationEnquireyResponse> customerRegisterResponse = Utilss.getWebService().CUSTOMER_QUOTATION_ENQUIREY_RESPONSE_CALL(registrationRequest);
        customerRegisterResponse.enqueue(new Callback<CustomerQuotationEnquireyResponse>() {
            @Override
            public void onResponse(@NonNull Call<CustomerQuotationEnquireyResponse> call, @NonNull Response<CustomerQuotationEnquireyResponse> response) {
                CustomerQuotationEnquireyResponse registtnResponsemain = response.body();
                Log.e(" ", "onResponse: " + new GsonBuilder().create().toJson(registtnResponsemain));
                if (response.code() == 200) {
                    UserPreferenceUtils.customMessage(CustomerQuotation.this, "Enquiry Send successfull");
                    hideProgressDialog();
                    startActivity(new Intent(CustomerQuotation.this, CustomerMainActivity.class));
                    finish();

                } else if (response.code() == 400) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerQuotation.this, "Something Went Wrong");
                } else if (response.code() == 404) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerQuotation.this, "There is problem to register.");
                } else if (response.code() == 409) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerQuotation.this, "Email id already exists.");
                } else if (response.code() == 500) {
                    hideProgressDialog();
                    UserPreferenceUtils.customMessage(CustomerQuotation.this, "Internal server error.");
                } else {
                    hideProgressDialog();
                    assert registtnResponsemain != null;
                    // Toast.makeText(CustomerRegistration.this, registtnResponsemain.,  Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerQuotationEnquireyResponse> call, Throwable t) {
                hideProgressDialog();
                UserPreferenceUtils.customMessage(CustomerQuotation.this, t.getMessage());
            }
        });
    }

    private boolean validate1() {
        boolean temp = true;

        return temp;
    }

    private boolean validate() {

        if (selectdate.getText().toString().equals("")) {
            Toast.makeText(CustomerQuotation.this, "Please Enter Date.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (time.getText().toString().equals("")) {
            Toast.makeText(CustomerQuotation.this, "Please enterTime", Toast.LENGTH_LONG).show();
            return false;
        }
        if (noOfPerson.getText().toString().equals("")) {
            Toast.makeText(CustomerQuotation.this, "Please Enter No. Person", Toast.LENGTH_LONG).show();
            return false;
        } if (withStaff.getText().toString().equals("Select Type")) {
            Toast.makeText(CustomerQuotation.this, "Please Select Your Choice", Toast.LENGTH_LONG).show();
            return false;
        } if (keepWarm.getText().toString().equals("Select Type")) {
            Toast.makeText(CustomerQuotation.this, "Please Select Your Choice", Toast.LENGTH_LONG).show();
            return false;
        }
        if (typeCatering.getSelectedItem().toString().equals("Select Caterer Type")) {
            Toast.makeText(CustomerQuotation.this, "Please Enter Your Caterer Type", Toast.LENGTH_LONG).show();
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
        progressDialog = new Dialog(CustomerQuotation.this);
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        progressDialog.setContentView(R.layout.custom_dialog_progress);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void hideProgressDialog() {
        progressDialog.dismiss();

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count <= 1) {
                    Intent i = new Intent(CustomerQuotation.this, FoodType.class);
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

}
