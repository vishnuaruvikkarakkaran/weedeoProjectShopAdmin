package com.weedeo.shopadmin.model.response;

import org.json.JSONObject;

public class ShopTimingUpdationResponse {

    String shop_id;
    String start_time;
    String end_time;

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public JSONObject getActive_times() {
        return active_times;
    }

    public void setActive_times(JSONObject active_times) {
        this.active_times = active_times;
    }

    JSONObject active_times;

}
