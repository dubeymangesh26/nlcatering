package com.dotcom.nlcatering.Http_Uttils;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {


    public static final String RESULT_PAYMENT = "Thank You";
    public static final String REFUSE_PAYMENT = "Sorry Try Again Later";
    public static final String OTP = "otp";
    public static final String CUST_ID ="CustomerID" ;
    public static final String LANG_SELECTED = "LanguageSelected";
    public static final String EMAIL ="email" ;
    public static final String IMAGE = "image";
    public static final String ENQUARY_ID = "ENQUARY_ID";
    public static final String COMPANY_NAME ="COMPANY_NAME" ;
    public static final String CATERER_ID = "CATERER_ID";
    public static final String CATERER_TYPE = "CATERER_TYPe";
    public static final String LOGIN_TYPE = "LOGIN_TYPE" ;
    public static Constants shareInstace = new Constants();
    public static final String CAT_ID ="catId";
    public static final String RJ_ID = "RJ_ID";
    public static final String ACCESS_TYPE = "accessType";
    public static final String USER_NAME = "EmailID";
    public static final String FIRST_NAME = "FullName";
    public static final String ADDRESS = "Address";
    public static final String MOBILE = "MobNo";
    public static final String COUNTRY = "Country";
    public static final String CITY = "City";
    public static final String STATE = "State";
    public static final String LOCALITY = "Locality";
    public static final String LANDMARK = "Landmark";
    public static final String PINCODE = "Pincode";
    public static final String AVAILABILITY = "availability";
    public static final String ALLOCATE_TRIP = "allocateTrip";
    public static final String STAR_TRIP = "starttrip";
    public static final String SITE_URL = "http://mrsam.in/sam/PhotographerImages/";
    public static final String IS_CLIK_RIDE = "ride";
    public static final String CURRENT_STATUS = "current_status";
    public static final String PAYMENT_STATUS = "payment_status";
    public static final String PAST_STATUS = "past_status";
    public static final String IS_LOGIN = "fromlogin";
    public static final String REFER_FRAGMENT = "referFragment";
    public static final String STOP_TRIP = "stoptrip";
    public static final String TRIP_SHARED = "tripshred";
    public static ArrayList<HashMap<String, String>> arrcontactList;
    public static String arrSubCateogry;
    public ArrayList<HashMap<String, String>> arrSubCateogrySK;
    public String arrSubCateogry1;
    public ArrayList<HashMap<String, String>> arrcontactList1;
    public static ArrayList<HashMap<String, String>> arrcontactList2;
    public static ArrayList<ArrayList<HashMap<String, String>>> arrSubCateogry2;
    public ArrayList<HashMap<String, String>> arrSubCateogrySK2;


    Constants() {
        arrcontactList = new ArrayList<>();
        arrcontactList1 = new ArrayList<>();

        //arrSubCateogry = String.valueOf(new ArrayList<>());
        arrSubCateogrySK = new ArrayList<>();
        arrSubCateogry1 = String.valueOf(new ArrayList<>());
    }
}
