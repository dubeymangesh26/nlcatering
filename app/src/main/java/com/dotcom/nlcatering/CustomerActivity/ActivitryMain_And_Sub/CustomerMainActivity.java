package com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.opengl.Visibility;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CustomerActivity.CustomerAdapter.CustomerMainActivityListAdapter;
import com.dotcom.nlcatering.CustomerActivity.CustomerBiddings.CustomerBiddingList;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerResetPassward;
import com.dotcom.nlcatering.CustomerActivity.CustomerEnquiries.CustomerEnqueriesList;
import com.dotcom.nlcatering.CustomerActivity.CustomerOrders.CustomerOrderList;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.Http_Uttils.Utilss;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerMainActivityResponse;
import com.dotcom.nlcatering.SpalshAndSelection.LoginType;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomerMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    RecyclerView menuList;
    boolean doubleBackToExitPressedOnce = false;
    List<String> url = new ArrayList<>();
    UserSessionManager session;
    public static TextView profileName;
    AdapterView adapter;
    Menu menu;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_main);

        session = new UserSessionManager(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Action Bar Colour
        getSupportActionBar().setBackgroundDrawable(
                new ColorDrawable(Color.parseColor("#fb971d")));

        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orangenormal)); //Status bar colour
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        menuList = findViewById(R.id.menuList);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().removeItem(navigationView.getMenu().add("").getItemId());
        View header = navigationView.getHeaderView(0);
        navigationView.invalidate();
        menu = navigationView.getMenu();
        profileName = header.findViewById(R.id.profileName);

        getApiData();


    }

    @Override
    protected void onStart() {
        super.onStart();
        getMenuManager();

    }

    private void getMenuManager() {
        String userName = UserPreferenceUtils.getStringUserPreference(this, Constants.FIRST_NAME);
        if (userName != null) {
            Log.e("User name", "onCreate: " + userName);
            profileName.setText(userName);
            profileName.setOnClickListener(this);
        } else {
            profileName.setText("");
        }
        if (UserPreferenceUtils.checkLogin(CustomerMainActivity.this)) {
            MenuItem target = menu.findItem(R.id.custEnquries);
            target.setVisible(true);
            MenuItem target1 = menu.findItem(R.id.custBidding);
            target1.setVisible(true);
            MenuItem target2 = menu.findItem(R.id.custLogout);
            target2.setVisible(true);
            MenuItem target3 = menu.findItem(R.id.custOrder);
            target3.setVisible(true);
            MenuItem target4 = menu.findItem(R.id.cusrResetpass);
            target4.setVisible(true);
            MenuItem target5 = menu.findItem(R.id.custLogin);
            target5.setVisible(false);

        } else {
            MenuItem target = menu.findItem(R.id.custEnquries);
            target.setVisible(false);
            MenuItem target1 = menu.findItem(R.id.custBidding);
            target1.setVisible(false);
            MenuItem target2 = menu.findItem(R.id.custLogout);
            target2.setVisible(false);
            MenuItem target3 = menu.findItem(R.id.custOrder);
            target3.setVisible(false);
            MenuItem target4 = menu.findItem(R.id.cusrResetpass);
            target4.setVisible(false);
            MenuItem target5 = menu.findItem(R.id.custLogin);
            target5.setVisible(true);
        }
    }

    private void getApiData() {
        showProgressDialog();
        Call<CustomerMainActivityResponse> customerMainActivityResponseCall = Utilss.getWebService().CUSTOMER_MAIN_ACTIVITY_RESPONSE_CALL();
        customerMainActivityResponseCall.enqueue(new Callback<CustomerMainActivityResponse>() {
            @Override
            public void onResponse(Call<CustomerMainActivityResponse> call, Response<CustomerMainActivityResponse> response) {
                Log.e("data", "onResponse: " + new GsonBuilder().create().toJson(response.body()));
                CustomerMainActivityResponse activityResponse = response.body();
                hideProgressDialog();
                if (activityResponse.getStatus() == 200) {

                    if (activityResponse.getResponse().size() > 0) {
                        menuList.setLayoutManager(new LinearLayoutManager(CustomerMainActivity.this, LinearLayoutManager.VERTICAL, false));
                        CustomerMainActivityListAdapter listAdapter = new CustomerMainActivityListAdapter(CustomerMainActivity.this, activityResponse.getResponse());
                        menuList.setAdapter(listAdapter);

                    }
                } else {
                    hideProgressDialog();

                }

            }

            @Override
            public void onFailure(Call<CustomerMainActivityResponse> call, Throwable t) {
                hideProgressDialog();
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                CustomerMainActivity.this.finish();
                Intent setIntent = new Intent(Intent.ACTION_MAIN);
                setIntent.addCategory(Intent.CATEGORY_HOME);
                setIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(setIntent);

            }

            this.doubleBackToExitPressedOnce = true;
            Toast toast =
                    Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 0);
            toast.show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.customer_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.needHelp) {
            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater != null ? inflater.inflate(R.layout.contactandsupport_dialog, null) : null;

            TextView phon = view.findViewById(R.id.customercareno);
            TextView email = view.findViewById(R.id.customercareemail);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();
            return true;
        }
        if (id == R.id.termCondition) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view2 = inflater.inflate(R.layout.termandcondition_dialog, null);
            ImageView imageView = view2.findViewById(R.id.cancle_image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cancel = new Intent(CustomerMainActivity.this, CustomerMainActivity.class);
                    startActivity(cancel);
                }
            });


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view2);
            final AlertDialog dialog = builder.create();
            dialog.show();


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.custEnquries) {
            if (UserPreferenceUtils.checkLogin(CustomerMainActivity.this)) {
                Intent returnpolicy = new Intent(CustomerMainActivity.this, CustomerEnqueriesList.class);
                startActivity(returnpolicy);

            } else {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerLogin.class);
                //item.setVisible(false);
                startActivity(intent);

            }

            // Handle the camera action
        } else if (id == R.id.custBidding) {
            if (UserPreferenceUtils.checkLogin(CustomerMainActivity.this)) {
                Intent returnpolicy = new Intent(CustomerMainActivity.this, CustomerBiddingList.class);
                startActivity(returnpolicy);
            } else {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerLogin.class);
                // item.setVisible(false);
                startActivity(intent);

            }

        } else if (id == R.id.custOrder) {
            if (UserPreferenceUtils.checkLogin(CustomerMainActivity.this)) {
                Intent returnpolicy = new Intent(CustomerMainActivity.this, CustomerOrderList.class);
                startActivity(returnpolicy);
            } else {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerLogin.class);
                startActivity(intent);

            }

        } else if (id == R.id.cusrResetpass) {
            if (UserPreferenceUtils.checkLogin(CustomerMainActivity.this)) {
                Intent returnpolicy = new Intent(CustomerMainActivity.this, CustomerResetPassward.class);
                startActivity(returnpolicy);
            } else {
                Intent intent = new Intent(CustomerMainActivity.this, CustomerLogin.class);
                startActivity(intent);

            }

        } else if (id == R.id.langselecton) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater != null ? inflater.inflate(R.layout.language_selectiondialog, null) : null;

            Button dutch = view.findViewById(R.id.dutch);
            Button english = view.findViewById(R.id.english);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            final AlertDialog dialog = builder.create();
            dialog.show();

            dutch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Locale locale = new Locale("nl");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        config.setLocales(new LocaleList(locale));
                    } else {
                        config.locale = locale;
                    }
                    getResources().updateConfiguration(config,
                            getResources().getDisplayMetrics());
                    Intent i = new Intent(CustomerMainActivity.this, CustomerMainActivity.class);
                    startActivity(i);

                }
            });

            english.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Locale locale = new Locale("en");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        config.setLocales(new LocaleList(locale));
                    } else {
                        config.locale = locale;
                    }
                    getResources().updateConfiguration(config,
                            getResources().getDisplayMetrics());
                    Intent i = new Intent(CustomerMainActivity.this, CustomerMainActivity.class);
                    startActivity(i);

                }
            });

        } else if (id == R.id.custLogin) {

            Intent returnpolicy = new Intent(CustomerMainActivity.this, CustomerLogin.class);
            startActivity(returnpolicy);

        } else if (id == R.id.custLogout) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to Logout ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            String userId = UserPreferenceUtils.getStringUserPreference(CustomerMainActivity.this, Constants.CUST_ID);
                            if (userId != null) {
                                profileName.setText("");
                                UserPreferenceUtils.clearAllPreference(CustomerMainActivity.this);
                                getMenuManager();
                            } else {

                            }
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
            Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
            pbutton.setTextColor(Color.rgb(30, 144, 255));
            Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
            nbutton.setTextColor(Color.rgb(30, 144, 255));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    Dialog progressDialog;

    private void showProgressDialog() {
        progressDialog = new Dialog(CustomerMainActivity.this);
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
    public void onClick(View v) {
        Intent profile = new Intent(CustomerMainActivity.this, CustomerProfileDetails.class);
        startActivity(profile);

    }
}