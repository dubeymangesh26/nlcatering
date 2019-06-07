package com.dotcom.nlcatering.Http_Uttils;

import com.dotcom.nlcatering.CustomerActivity.ActivitryMain_And_Sub.CustomerQuotation;
import com.dotcom.nlcatering.Request.CatererRequest.CatererEnquiryBiddingRequest;
import com.dotcom.nlcatering.Request.CatererRequest.CatererRegisterRequest;
import com.dotcom.nlcatering.Request.CatererRequest.CatererResetPasswordRequest;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerEnquiryOrQuotRequest;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerQuotationEnquireyRequest;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerRegisterRequest;
import com.dotcom.nlcatering.Request.CustomerRequest.CustomerResetPasswardRequest;
import com.dotcom.nlcatering.Response.CatererResponse.CatererCityAndCatererTypeResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererEnquiryBiddingResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererEnquiryResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererForgotPasswardResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererLoginResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererOrderListResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererRegisterResponse;
import com.dotcom.nlcatering.Response.CatererResponse.CatererResetPasswordResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerBiddingResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerEnquiryOrQuotResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerForgotPassawardResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerLisrtEnquiryResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerLoginResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerMainActivityResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerOrderListResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerQuotationEnquireyResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.CustomerResetPasswardResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.FoodType_SubMenuResponse;
import com.dotcom.nlcatering.Response.CustomerResponse.QuoatationCityCatererTypeResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {

   ////////// Customer Request Resopnse   ///////////////////////

    @FormUrlEncoded
    @POST("api/Customer/CustLogin")
    Call<CustomerLoginResponse> login(
            @Field(value = "Email", encoded = true) String Email,
            @Field(value = "Password", encoded = true) String Password);

  /*  @POST("api/Customer/CustLogin")
    Call<CustomerLoginResponse> CUSTOMER_LOGIN_RESPONSE_CALL(@Body CustomerLoginRequest customerLoginRequest);*/

    @POST("api/Customer/Register")
    Call<CustomerRegisterRequest> CUSTOMER_REGISTER_REQUEST_CALL(@Body CustomerRegisterRequest customerRegisterResponse);

    @POST("api/Customer/Enquiry")
    Call<CustomerQuotationEnquireyResponse> CUSTOMER_QUOTATION_ENQUIREY_RESPONSE_CALL(@Body CustomerQuotationEnquireyRequest customerQuotationEnquireyRequest);

    @GET("api/Customer/ForgotPassword")
    Call<CustomerForgotPassawardResponse> CUSTOMER_FORGOT_PASSAWARD_RESPONSE_CALL(@Query("EmailId") String EmailId);



    @POST("api/Customer/ResetPassword")
    Call<CustomerResetPasswardResponse> CUSTOMER_RESET_PASSWARD_RESPONSE_CALL(@Body CustomerResetPasswardRequest customerResetPasswardRequest);

    @GET("api/Customer/GetAllFoodMenuList")
    Call<CustomerMainActivityResponse> CUSTOMER_MAIN_ACTIVITY_RESPONSE_CALL();

    @GET("api/Customer/ListBidding/{id}")
    Call<CustomerBiddingResponse> CUSTOMER_BIDDING_RESPONSE_CALL(@Path("id") int id);

    @GET("api/Customer/GetAllFoodMenuList")
    Call<QuoatationCityCatererTypeResponse> QUOATATION_CITY_CATERER_TYPE_RESPONSE_CALL();

    @GET("api/Customer/GetAllSubFoodMenuList/{id}")
    Call<FoodType_SubMenuResponse> FOOD_TYPE_SUB_MENU_RESPONSE_CALL(@Path("id") int id);

    @POST("api/Customer/ResetPassword")
    Call<CustomerEnquiryOrQuotResponse> CUSTOMER_ENQUIRY_OR_QUOT_RESPONSE_CALL(@Body CustomerEnquiryOrQuotRequest customerEnquiryOrQuotRequest);

    @GET("api/Customer/ListEnquiryByCustId/{id}")
    Call<CustomerLisrtEnquiryResponse> CUSTOMER_LISRT_ENQUIRY_RESPONSE_CALL (@Path("id") int id);

    @GET("api/Customer/ListOrder/{id}")
    Call<CustomerOrderListResponse> CUSTOMER_ORDER_LIST_RESPONSE_CALL (@Path("id") int id);


    /*@GET("api/Customer/ListEnquiryByCustId/{id}")
    Call<CustomerLisrtEnquiryResponse> CUSTOMER_LISRT_ENQUIRY_RESPONSE_CALL (@Path("id") int id);
*/






    ////////////////////////  Caterer Response And Request //////////////////////////////////////////////////////

    @FormUrlEncoded
    @POST("api/Caterers/CaterersLogin")
    Call<CatererLoginResponse> CATERER_LOGIN_RESPONSE_CALL(
            @Field(value = "Email", encoded = true) String Email,
            @Field(value = "Password", encoded = true) String Password);

    @POST("api/Caterers/Register")
    Call<CatererRegisterResponse> CATERER_REGISTER_RESPONSE_CALL(@Body CatererRegisterRequest catererRegisterRequest);

    @GET("api/Caterers/ForgotPassword?")
    Call<CatererForgotPasswardResponse> CATERER_FORGOT_PASSWARD_RESPONSE_CALL(@Query("EmailId") String EmailId);

    @POST("api/Caterers/ResetPassword")
    Call<CatererResetPasswordResponse> CATERER_RESET_PASSWORD_RESPONSE_CALL(@Body CatererResetPasswordRequest catererResetPasswordRequest);

    @GET("api/Caterers/GetAllCityCateringList")
    Call<CatererCityAndCatererTypeResponse> CATERER_CITY_AND_CATERER_TYPE_RESPONSE_CALL();

    @GET("api/Caterers/GetListEnquiryByCaterer/{Caterer_Id}/{Ca_type}")
    Call<CatererEnquiryResponse> CATERER_ENQUIRY_RESPONSE_CALL (@Path("Caterer_Id") int Caterer_Id, @Path("Ca_type") String Ca_type);

    @GET("api/Caterers/ListOrder/{Caterer_Id}/{Ca_type}")
    Call<CatererOrderListResponse> CATERER_ORDER_LIST_RESPONSE_CALL (@Path("Caterer_Id") int Caterer_Id, @Path("Ca_type") String Ca_type);

    @POST("api/Caterers/Bidding")
    Call<CatererEnquiryBiddingResponse> CATERER_ENQUIRY_BIDDING_RESPONSE_CALL(@Body CatererEnquiryBiddingRequest catererEnquiryBiddingRequest);


}


