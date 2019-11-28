package com.weedeo.shopadmin.model.request;

public class ProfileAgainOtpRequest {

    /**
     * mobile : 9638527410
     * user_type : all
     */

    private String mobile;
    private String user_type;


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }
}
