package com.dotcom.nlcatering.CustomerActivity.PaymentActivity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.textclassifier.TextClassification;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.dotcom.nlcatering.R;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

public class CustomerPaymentActivity extends AppCompatActivity {

    String order;

    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_payment);

        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.mollie.com/payscreen/select-method/7UhSN1zuXS");

/*

        private void startPayment(Order order) {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("orderId", order.getId())
                    .build();

            TextClassification.Request request = new TextClassification.Request.Builder()
                    .url("https://www.thisismylink.com/api/create-payment")
                    .post(requestBody)
                    .build();

            try {
                response = client.newCall(request).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (response == null || !response.isSuccessful()) {
                Log.w("Create Payment", "HTTPS-call failed");
            } else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(response.body().string()));
                startActivity(browserIntent);
            }
        }
*/

    }
}
