package com.dotcom.nlcatering.Response.CustomerResponse;

public class CustomerQuotationEnquireyResponse {

    /**
     * loginType : null
     * success : true
     * Messege : Register Successfull
     * status : 200
     */

    private Object loginType;
    private boolean success;
    private String Messege;
    private int status;

    public Object getLoginType() {
        return loginType;
    }

    public void setLoginType(Object loginType) {
        this.loginType = loginType;
    }

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
}
