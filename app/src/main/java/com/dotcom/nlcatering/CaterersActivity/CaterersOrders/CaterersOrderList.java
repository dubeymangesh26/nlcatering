package com.dotcom.nlcatering.CaterersActivity.CaterersOrders;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.CatererAdapter.CatererEnquiryListAdapter;
import com.dotcom.nlcatering.CaterersActivity.CatererAdapter.CatererOrderListAdapter;
import com.dotcom.nlcatering.CaterersActivity.CaterersEnquiries.CaterersEnquiryList;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CatererResponse.CatererEnquiryResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererOrderListResponse;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaterersOrderList extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView menuList;
    UserSessionManager session;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterers_order_list);
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour

        menuList = findViewById(R.id.menuList);
        session = new UserSessionManager(getApplicationContext());
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Order List");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getApiData();

    }

    private void getApiData() {
        showProgressDialog();
        Call<CatererOrderListResponse> customerLisrtEnquiryResponseCall = Utilss.getWebService().CATERER_ORDER_LIST_RESPONSE_CALL(Integer.valueOf(Utilss.getStringUserPreference(CaterersOrderList.this, Constants.CATERER_ID)),Utilss.getStringUserPreference(CaterersOrderList.this, Constants.CATERER_TYPE));
        customerLisrtEnquiryResponseCall.enqueue(new Callback<CatererOrderListResponse>() {
            @Override
            public void onResponse(Call<CatererOrderListResponse> call, Response<CatererOrderListResponse> response) {
                Log.e("data", "onResponse: " + new GsonBuilder().create().toJson(response.body()));
                CatererOrderListResponse activityResponse = response.body();
                hideProgressDialog();
                if (response.code() == 200) {

                    if (activityResponse.getResponse().size() > 0) {
                        menuList.setLayoutManager(new LinearLayoutManager(CaterersOrderList.this, LinearLayoutManager.VERTICAL, false));
                        CatererOrderListAdapter listAdapter = new CatererOrderListAdapter(CaterersOrderList.this, activityResponse.getResponse());
                        menuList.setAdapter(listAdapter);

                    }
                } else {
                    Toast.makeText(CaterersOrderList.this, "You have Not Any Enquiry", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();

                }

            }

            @Override
            public void onFailure(Call<CatererOrderListResponse> call, Throwable t) {

            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count <= 1) {
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
        progressDialog = new Dialog(CaterersOrderList.this);
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
