package com.weedeo.shopadmin.model.request;

public class UpdateMobileNumberRequest {


    /**
     * primary_mobile : 1234567890
     * shop_id : 5dad53e1fea6ad2cdbb35ae3
     * user_id : 5dad53e1fea6ad2cdbb35ae3
     * fcm_uid : ssdsdsd
     */

    private String primary_mobile;
    private String shop_id;
    private String user_id;
    private String fcm_uid;
    private String primary_image;

    public String getPrimary_image() {
        return primary_image;
    }

    public void setPrimary_image(String primary_image) {
        this.primary_image = primary_image;
    }

    public String getPrimary_mobile() {
        return primary_mobile;
    }

    public void setPrimary_mobile(String primary_mobile) {
        this.primary_mobile = primary_mobile;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFcm_uid() {
        return fcm_uid;
    }

    public void setFcm_uid(String fcm_uid) {
        this.fcm_uid = fcm_uid;
    }
}
