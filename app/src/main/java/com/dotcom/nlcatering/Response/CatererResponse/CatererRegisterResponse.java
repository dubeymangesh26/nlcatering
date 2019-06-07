package com.dotcom.nlcatering.Response.CatererResponse;

public class CatererRegisterResponse {

    /**
     * success : true
     * Messege : Thank you for registration. Your registration is pending for approval from administrator
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
}
