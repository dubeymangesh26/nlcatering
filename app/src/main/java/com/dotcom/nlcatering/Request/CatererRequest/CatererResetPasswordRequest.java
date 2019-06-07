package com.dotcom.nlcatering.Request.CatererRequest;

public class CatererResetPasswordRequest {


    /**
     * Email : dubeymangesh26@gmail.com
     * Password : @123Mangesh
     * NewPassword : 123456
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
