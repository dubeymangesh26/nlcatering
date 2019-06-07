package com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.dotcom.nlcatering.CustomerActivity.CustomerAdapter.CustomerFoodTypeListAdapter;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.FoodType_SubMenuResponse;
import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoodType extends AppCompatActivity {
    Toolbar toolbar;
    String email;
    RecyclerView menuList;
    TextView mainFoodname;
    String mainMenu;
    int catID;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_type);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orangenormal)); //Status bar colour

        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Sub Menu");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        email = UserPreferenceUtils.getStringUserPreference(FoodType.this, Constants.EMAIL);
        mainFoodname=findViewById(R.id.mainFoodname);
        menuList = findViewById(R.id.menuList);

        catID = getIntent().getExtras().getInt(Constants.CAT_ID);
        mainMenu=getIntent().getExtras().getString("mainFoodName");
        mainFoodname.setText(mainMenu);

        getApiData();

    }

    private void getApiData() {

        showProgressDialog();

        Call<FoodType_SubMenuResponse> foodType_subMenuResponseCall = Utilss.getWebService().FOOD_TYPE_SUB_MENU_RESPONSE_CALL(catID);
        foodType_subMenuResponseCall.enqueue(new Callback<FoodType_SubMenuResponse>() {
            @Override
            public void onResponse(Call<FoodType_SubMenuResponse> call, Response<FoodType_SubMenuResponse> response) {
                Log.e("data", "onResponse: " + new GsonBuilder().create().toJson(response.body()));
                FoodType_SubMenuResponse activityResponse = response.body();
                if(activityResponse!=null){
                    if (activityResponse.getStatus() == 200) {

                        if (activityResponse.getResponse().size() > 0) {
                            menuList.setLayoutManager(new LinearLayoutManager(FoodType.this, LinearLayoutManager.VERTICAL, false));
                            CustomerFoodTypeListAdapter listAdapter = new CustomerFoodTypeListAdapter(FoodType.this, activityResponse.getResponse());
                            menuList.setAdapter(listAdapter);

                        }
                    } else {
                        hideProgressDialog();

                    }

                }

                hideProgressDialog();

            }

            @Override
            public void onFailure(Call<FoodType_SubMenuResponse> call, Throwable t) {

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                int count = getSupportFragmentManager().getBackStackEntryCount();
                if (count <= 1) {
                    Intent i = new Intent(FoodType.this, CustomerMainActivity.class);
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
        progressDialog = new Dialog(FoodType.this);
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