package com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.LocaleList;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CatererLogin;
import com.dotcom.nlcatering.CaterersActivity.CaterersCredentials.CaterersResetPassward;
import com.dotcom.nlcatering.CaterersActivity.CaterersEnquiries.CaterersEnquiryList;
import com.dotcom.nlcatering.CaterersActivity.CaterersOrders.CaterersOrderList;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerProfileDetails;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.FoodType;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerLogin;
import com.dotcom.nlcatering.CustomerActivity.CustomerCredentials.CustomerResetPassward;
import com.dotcom.nlcatering.Http_Uttils.Constants;
import com.dotcom.nlcatering.Http_Uttils.UserPreferenceUtils;
import com.dotcom.nlcatering.Http_Uttils.UserSessionManager;
import com.dotcom.nlcatering.R;

import java.util.Locale;

public class CatererMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    UserSessionManager session;
    TextView profileName;
    boolean doubleBackToExitPressedOnce = false;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caterer_main);

        session = new UserSessionManager(getApplicationContext());
        getWindow().setStatusBarColor(ContextCompat.getColor(this ,R.color.actionbar)); //Status bar colour

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);
        profileName = header.findViewById(R.id.profileName);

    }

    @Override
    protected void onStart() {
        super.onStart();
        String userName = UserPreferenceUtils.getStringUserPreference(this, Constants.COMPANY_NAME);
        if (userName != null) {
            Log.e("User name", "onCreate: " + userName);
            profileName.setText(userName);
            profileName.setOnClickListener(this);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            if (doubleBackToExitPressedOnce) {
                CatererMainActivity.this.finish();
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
        getMenuInflater().inflate(R.menu.caterer_main, menu);
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
        } if (id == R.id.termCondition) {

            LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final View view2 = inflater.inflate(R.layout.termandcondition_dialog, null);
            ImageView imageView = view2.findViewById(R.id.cancle_image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cancel=new Intent(CatererMainActivity.this, CatererMainActivity.class);
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

        if (id == R.id.cateresEnquiry) {
            Intent i = new Intent(CatererMainActivity.this, CaterersEnquiryList.class);

            startActivity(i);


            // Handle the camera action
        } else if (id == R.id.cateresOrder) {
            Intent i = new Intent(CatererMainActivity.this, CaterersOrderList.class);

            startActivity(i);


        }else if (id == R.id.cusrResetpass) {
            Intent returnpolicy=new Intent(CatererMainActivity.this, CaterersResetPassward.class);
            startActivity(returnpolicy);

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
                    }else
                    {
                        config.locale = locale;
                    }
                    getResources().updateConfiguration(config,
                            getResources().getDisplayMetrics());
                    Intent i = new Intent(CatererMainActivity.this, CatererMainActivity.class);
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
                    }else
                    {
                        config.locale = locale;
                    }
                    getResources().updateConfiguration(config,
                            getResources().getDisplayMetrics());
                    Intent i = new Intent(CatererMainActivity.this, CatererMainActivity.class);
                    startActivity(i);

                }
            });

        } else if (id == R.id.cateresLogout) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Are you sure you want to Logout ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (Boolean.valueOf(UserPreferenceUtils.getStringUserPreference(CatererMainActivity.this, Constants.CUST_ID)) == true) {
                                session.logoutUser();
                            } else {

                            }
                            UserPreferenceUtils.clearAllPreference(CatererMainActivity.this);
                            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            sharedPreferences.edit().clear().apply();
                            sharedPreferences.edit().remove("COU").commit();
                            Intent intent = new Intent(CatererMainActivity.this, CatererLogin.class);
                            startActivity(intent);
                            finish();
                            /*    HomeActivity.this.finish();*/
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

    @Override
    public void onClick(View v) {
        Intent profile = new Intent(CatererMainActivity.this, CaterersProfileDetails.class);
        startActivity(profile);

    }



}
