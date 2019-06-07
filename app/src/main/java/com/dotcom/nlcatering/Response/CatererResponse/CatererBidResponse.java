package com.dotcom.nlcatering.Response.CatererResponse;

public class CatererBidResponse {

    /**
     * loginType : 2
     * success : true
     * Messege : Bid Send Successfully.
     * status : 200
     */

    private int loginType;
    private boolean success;
    private String Messege;
    private int status;

    public int getLoginType() {
        return loginType;
    }

    public void setLoginType(int loginType) {
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
