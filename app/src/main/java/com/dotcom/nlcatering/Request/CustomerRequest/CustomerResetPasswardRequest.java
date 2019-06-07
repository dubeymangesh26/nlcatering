package com.dotcom.nlcatering.Request.CustomerRequest;

public class CustomerResetPasswardRequest {


    /**
     * Email : swapnali.thakur@worldindia.com
     * Password : Swap@123
     * NewPassword : Swap@1234
     */

    private String Email;
    private String Password;
    private String NewPassword;

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getNewPassword() {
        return NewPassword;
    }

    public void setNewPassword(String NewPassword) {
        this.NewPassword = NewPassword;
    }
}
