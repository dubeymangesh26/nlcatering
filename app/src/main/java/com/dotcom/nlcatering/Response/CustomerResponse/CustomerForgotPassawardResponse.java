package com.dotcom.nlcatering.Response.CustomerResponse;

public class CustomerForgotPassawardResponse {


    /**
     * success : true
     * Messege : Cradential has been Send Your Register Email
     * status : 200
     */

    private boolean success;
    private String Messege;
    private int status;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessege() {
        return Messege;
    }

    public void setMessege(String Messege) {
        this.Messege = Messege;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public CustomerForgotPassawardResponse(String emailId) {
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

