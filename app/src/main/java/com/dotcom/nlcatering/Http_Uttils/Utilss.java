package com.dotcom.nlcatering.Http_Uttils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.widget.Toast;

import com.dotcom.nlcatering.R;

import java.io.IOException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.ACTIVITY_SERVICE;

public class Utilss {
    public static final String USER_PREFERENCE = "UserPreferences";
    public static final String TAG = "fragment";
    public static final String SETTING_PREFERENCE = "SettingPreferences";
    private static final String DISPLAY_MESSAGE_ACTION = "com.microdatum.DISPLAY_MESSAGE";
    //public static final String WEB_API_BASE_URL = "http://citigoapp.com/api/";
    public static final String WEB_API_BASE_URL = "http://worldindia.in/NLCateringAndroidAPI/";

    /**
     * Retrofit client to call web service.
     */
    public static WebService getWebService() {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.readTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(7000, TimeUnit.SECONDS);

        return new retrofit2.Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(WEB_API_BASE_URL)                                       // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(WebService.class);
    }

    public static WebService getWebServiceForAuthorization(final Context context) {
        Log.e(TAG, "Authorization" + " Bearer " + Utilss.getStringUserPreference(context, Constants.USER_NAME));

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.readTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                final Request request = chain.request().newBuilder()
                        .addHeader("Authorization", " Bearer " + Utilss.getStringUserPreference(context, Constants.USER_NAME))
                        .build();
                return chain.proceed(request);
            }
        });


        return new retrofit2.Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(WEB_API_BASE_URL)                                // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(WebService.class);
    }


    public static WebService getWebServiceForGoogleApi(String url) {

        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.readTimeout(7000, TimeUnit.SECONDS);
        okHttpClient.writeTimeout(7000, TimeUnit.SECONDS);

        return new retrofit2.Retrofit.Builder()                                   // Retrofit client.
                .baseUrl(url)                                // Base domain URL.
                .addConverterFactory(GsonConverterFactory.create())     // Added converter factory.
                .client(okHttpClient.build())
                .build()                                                // Build client.
                .create(WebService.class);
    }
    public static void noDataFoundMessage(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("Sorry no data founud!")
                //.setTitle("")
                .setCancelable(false)
                // .setIcon(R.mipmap.logo)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
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


    /**
     * Notifies UI to display a message.
     * <p>
     * This method is defined in the common helper because it's used both by
     * the UI and the background service.
     * </p>
     *
     * @param context application's context.
     * @param message message to be displayed.
     */
    public static void displayMessage(Context context, String message) {
        Intent intent = new Intent(DISPLAY_MESSAGE_ACTION);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        context.sendBroadcast(intent);
    }

    /**
     * Save String content to User Shared Preference. Like menu, wishlist item, etc.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link String}
     * @param value   Value as {@link String}
     */
    public static void saveUserPreference(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.apply();
    }


    /**
     * Save boolean content to User Shared Preference. Like menu, wishlist item, etc.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link Boolean}
     * @param value   Value as {@link String}
     */
    public static void saveUserPreference(Context context, String key, boolean value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /**
     * Save Integer content to User Shared Preference. Like menu, wishlist item, etc.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link Integer}
     * @param value   Value as {@link String}
     */
    public static void saveUserPreference(Context context, String key, int value) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    /**
     * Get string content from User Shared Preference.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link String}
     * @return Value as {@link String}
     */
    public static String getStringUserPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }


    /**
     * Get boolean content from User Shared Preference.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link Boolean}
     * @return Value as {@link String}
     */
    public static boolean getBooleanUserPreference(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }

    /**
     * Get int content from User Shared Preference.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link Integer}
     * @return Value as {@link String}
     */
    public static int getIntegerUserPreference1(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, -1);
    }

    /**
     * Check if preference already exists.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name to check existence
     * @return True if it exists else false.
     */
    public static boolean isUserPreferenceExists(Context context, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPref.contains(key);
    }

    /**
     * Remove a content from any Shared Preference by it's key and file name.
     *
     * @param context {@link Activity} {@link Context}
     * @param key     Key name as {@link String}
     * @return True if successfully deleted the preference else False.
     */
    public static boolean removePreference(Context context, String preference, String key) {
        SharedPreferences sharedPref = context.getSharedPreferences(preference, Context.MODE_PRIVATE);
        return sharedPref.edit().remove(key).commit();
    }

    /**
     * Delete all Preferences.
     *
     * @param context {@link Activity} {@link Context}
     * @return True if successfully deleted all preferences else False.
     */
    public static boolean clearAllPreference(Context context) {
        SharedPreferences sharedUserPref = context.getSharedPreferences(USER_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences sharedSettingPref = context.getSharedPreferences(SETTING_PREFERENCE, Context.MODE_PRIVATE);
        return sharedUserPref.edit().clear().commit() && sharedSettingPref.edit().clear().commit();
    }

    /**
     * Check user internet connectivity.
     *
     * @param context Activity context
     * @return Connected to internet or not. (True / False)
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager manager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        @SuppressLint("MissingPermission") NetworkInfo networkInfo = manager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    /**
     * A Toast message if device is not connected to internet.
     *
     * @param context Activity context.
     */
    public static void offlineMessage(Context context) {
        //Toast.makeText(context, context.getResources().getString(R.string.device_offline_message), Toast.LENGTH_LONG).show();
    }

    /**
     * Pass 29-04-2016 Time and getFormat
     *
     * @param datetime
     * @return String date
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateFormat(String datetime) {
        if (datetime != null)
            try { // Expiry date
                String s;
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(datetime);
                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
                s = sdf.format(date).split("-")[2] +
                        "-" + sdf.format(date).split("-")[1] +
                        "-" + sdf.format(date).split("-")[0];
                return s;
            } catch (Exception e) {
                Log.e("AppUtilDateFormat", "dateFormat: Parsing exception ", e);
            }
        return null;
    }

    /*yyyy-mm-dd*/
    @SuppressLint("SimpleDateFormat")
    public static String dateFor(String datetime) {
        if (datetime != null)
            try { // Expiry date
                String s;
                DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(datetime);
                sdf.setTimeZone(TimeZone.getTimeZone("IST"));
                s = sdf.format(date).split("-")[0] +
                        "-" + sdf.format(date).split("-")[1] +
                        "-" + sdf.format(date).split("-")[2];
                return s;
            } catch (Exception e) {
                Log.e("AppUtilDateFormat", "dateFormat: Parsing exception ", e);
            }
        return null;
    }

    /**
     * Convert Time from 24 hours to 12 hours.
     *
     * @param datetime
     * @return
     */
    public static String timeFormat(String datetime) {
        SimpleDateFormat inTimeFormat = new SimpleDateFormat("HH:mm", Locale.US);
        SimpleDateFormat outTimeFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        try { // Expiry date
            Date eDate = inTimeFormat.parse(datetime.split(" ")[1]);
            return outTimeFormat.format(eDate);
        } catch (ParseException e) {
            Log.e("AppUtilDateFormat", "timeFormat: Parsing exception ", e);
        }
        return null;
    }


    public static String numberFormat(Float number) {
        NumberFormat formatter;
        // Expiry date
        formatter = NumberFormat.getNumberInstance();
        formatter.setMinimumFractionDigits(2);
        formatter.setMaximumFractionDigits(2);
        return formatter.format(number);

    }

    public static boolean isValidMobile(String phone) {
        if (phone.length() != 10) {
            return false;
        } else {
            return android.util.Patterns.PHONE.matcher(phone).matches();
        }
    }

    public static boolean isValidMobileNumber(CharSequence target) {

        boolean flg1 = false, flg2 = false;
        String MobilePattern = "^[0-9]{8}$";

        Pattern pattern = Pattern.compile(MobilePattern);
        Matcher matcher = pattern.matcher(target);
        flg1 = matcher.matches();
        //
        String MobilePattern2 = "^[0-9]{10}$";

        Pattern pattern2 = Pattern.compile(MobilePattern2);
        Matcher matcher2 = pattern2.matcher(target);
        flg2 = matcher2.matches();
        if (flg1 || flg2)
            return true;
        else
            return false;

    }

    public static boolean checkChar(String target) {

        Pattern pattern = Pattern.compile("^[a-zA-Z\\s]+$");
        Matcher matcher = pattern.matcher(target);
        return matcher.matches();
    }

    public static boolean isValidEmaillId(String email) {

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }

    public static boolean isValidPassword(String password) {
        return Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*]).{6,20})").matcher(password).matches();
    }


    // Check internet is available or not..


    public static Toast customMessage(Context ctx, String message) {

        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;

    }


    public static boolean messageDialog(final Context activity, final boolean close, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(msg)
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("Easy Shopping")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
        return true;
    }

    public static boolean noNetwork(final Context activity, final boolean close) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("NO INTERNET AVAILABLE!!")
                .setCancelable(false)
                .setIcon(R.mipmap.ic_launcher)
                .setTitle("NO INTERNET AVAILABLE!!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (close) {
                            System.exit(1);
                        }
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

        return true;
    }

    public static String getDayName(int pos) {
        String[] daysName = new String[]{"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return daysName[pos];
    }

    public static int getDayInInteger(String s) {
        switch (s) {
            case "Sunday":
                return 0;
            case "Monday":
                return 1;
            case "Tuesday":
                return 2;
            case "Wednesday":
                return 3;
            case "Thursday":
                return 4;
            case "Friday":
                return 5;
            case "Saturday":
                return 6;
            default:
                return 0;
        }
    }


    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April ";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
        }
        return null;
    }

    public static String fullDay(int month) {
        switch (month) {
            case 1:
                return "01";
            case 2:
                return "02";
            case 3:
                return "03";
            case 4:
                return "04";
            case 5:
                return "05";
            case 6:
                return "06";
            case 7:
                return "07";
            case 8:
                return "08";
            case 9:
                return "09";
            default:
                return "" + month;

        }
    }

    public static int getMonthPos(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April ":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
        }
        return 0;
    }

    public static String getMonthNameFromMM(String month) {
        switch (month) {
            case "Jan":
                return "January";
            case "Feb":
                return "February";
            case "Mar":
                return "March";
            case "Apr":
                return "April ";
            case "May":
                return "May";
            case "Jun":
                return "June";
            case "Jul":
                return "July";
            case "Aug":
                return "August";
            case "Sep":
                return "September";
            case "Oct":
                return "October";
            case "Nov":
                return "November";
            case "Dec":
                return "December";
        }
        return null;
    }


    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null) {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
