package com.weedeo.shopadmin.model.request;

public class LocationModel {


    /**
     * gps_location : {"lat":10.32424,"lng":5.84978}
     * shop_id : 5dad53e1fea6ad2cdbb35ae3
     */

    private GpsLocationBean gps_location;
    private String shop_id;

    public GpsLocationBean getGps_location() {
        return gps_location;
    }

    public void setGps_location(GpsLocationBean gps_location) {
        this.gps_location = gps_location;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public static class GpsLocationBean {
        /**
         * lat : 10.32424
         * lng : 5.84978
         */

        private String lat;
        private String lng;

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLng() {
            return lng;
        }

        public void setLng(String lng) {
            this.lng = lng;
        }
    }
}
