package com.dotcom.nlcatering.Request.CustomerRequest;

public class CustomerForgotPasswardRequest {

    public CustomerForgotPasswardRequest(String emailId) {
        this.Email = emailId;
    }

    private String Email;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }


}
