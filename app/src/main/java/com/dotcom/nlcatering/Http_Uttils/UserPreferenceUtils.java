package com.dotcom.nlcatering.Http_Uttils;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.transition.Explode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dotcom.nlcatering.CaterersActivity.Caterers_Main_Sub.CatererMainActivity;
import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerMainActivity;
import com.dotcom.nlcatering.R;
import com.dotcom.nlcatering.SpalshAndSelection.LoginType;
import com.dotcom.nlcatering.SpalshAndSelection.SplashScreen;

import static com.dotcom.nlcatering.Http_Uttils.Utilss.SETTING_PREFERENCE;
import static com.dotcom.nlcatering.Http_Uttils.Utilss.USER_PREFERENCE;

public class UserPreferenceUtils {
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void setExplodTransition(Context context) {
        Explode explode = new Explode();
        explode.setDuration(400);
        ((Activity) context).getWindow().setEnterTransition(explode);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public static void moveNextWithAnimation(Context context, Class activity) {
        ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(((Activity) context));
        Intent intent = new Intent(context, activity);
        context.startActivity(intent, activityOptions.toBundle());

    }

    public static Toast customMessage(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;

    }

    public static String getStringUserPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }

    public static void saveUserPreference(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static boolean clearAllPreference(Context context) {
        SharedPreferences sharedUserPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences sharedSettingPref = context.getSharedPreferences(SETTING_PREFERENCE, Context.MODE_PRIVATE);
        return sharedUserPref.edit().clear().commit() && sharedSettingPref.edit().clear().commit();
    }

    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public static boolean dataNotFound(final Activity activity, final boolean close) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View dialogLayout = inflater.inflate(R.layout.page_not_found_layout, null);
        builder.setView(dialogLayout)
                // builder.setMessage("Sorry No Records Found..")
                .setCancelable(false)
                .setIcon(R.drawable.logo)
                .setTitle("NO RECORD FOUND!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (close) {
                            activity.finish();
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }

    public static boolean timeOutDialog(final Activity activity, final boolean close) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Connection timed out - please try again")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Server error !!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (close) {
                            activity.finish();
                            activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }


    public static boolean checkLogin(Context context) {
        String email = UserPreferenceUtils.getStringUserPreference(context, Constants.EMAIL);
        if (email != null) {
            return true;
        } else {
            return false;
        }


    }

}
