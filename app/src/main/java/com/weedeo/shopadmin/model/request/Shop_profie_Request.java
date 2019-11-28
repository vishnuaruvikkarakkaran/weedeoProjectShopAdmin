package com.weedeo.shopadmin.model.request;

public class Shop_profie_Request {


    /**
     * shop_id : 5d95c0432b15d61cfb1a1c5e
     * item_path : 1234
     * item_type : 1
     */

    private String shop_id;
    private String item_path;
    private int item_type;

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getItem_path() {
        return item_path;
    }

    public void setItem_path(String item_path) {
        this.item_path = item_path;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }
}
