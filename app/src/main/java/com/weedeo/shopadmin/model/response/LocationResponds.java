package com.weedeo.shopadmin.model.response;

public class LocationResponds {


    /**
     * data : {"shop_name":"vishnu aruvikkara","email":"shopadmin@yopmail.com","registered_address":"abcd","primary_mobile":9497572861,"shop_status":0,"shop_online_status":false,"gps_location":{"lat":8.557015208692558,"lng":76.88207678496838},"primary_image":"1553247889992.jpg"}
     * message : shop update successfully
     * status : success
     * statusCode : 200
     */

    private DataBean data;
    private String message;
    private String status;
    private int statusCode;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public static class DataBean {
        /**
         * shop_name : vishnu aruvikkara
         * email : shopadmin@yopmail.com
         * registered_address : abcd
         * primary_mobile : 9497572861
         * shop_status : 0
         * shop_online_status : false
         * gps_location : {"lat":8.557015208692558,"lng":76.88207678496838}
         * primary_image : 1553247889992.jpg
         */

        private String shop_name;
        private String email;
        private String registered_address;
        private long primary_mobile;
        private int shop_status;
        private boolean shop_online_status;
        private GpsLocationBean gps_location;
        private String primary_image;

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRegistered_address() {
            return registered_address;
        }

        public void setRegistered_address(String registered_address) {
            this.registered_address = registered_address;
        }

        public long getPrimary_mobile() {
            return primary_mobile;
        }

        public void setPrimary_mobile(long primary_mobile) {
            this.primary_mobile = primary_mobile;
        }

        public int getShop_status() {
            return shop_status;
        }

        public void setShop_status(int shop_status) {
            this.shop_status = shop_status;
        }

        public boolean isShop_online_status() {
            return shop_online_status;
        }

        public void setShop_online_status(boolean shop_online_status) {
            this.shop_online_status = shop_online_status;
        }

        public GpsLocationBean getGps_location() {
            return gps_location;
        }

        public void setGps_location(GpsLocationBean gps_location) {
            this.gps_location = gps_location;
        }

        public String getPrimary_image() {
            return primary_image;
        }

        public void setPrimary_image(String primary_image) {
            this.primary_image = primary_image;
        }

        public static class GpsLocationBean {
            /**
             * lat : 8.557015208692558
             * lng : 76.88207678496838
             */

            private double lat;
            private double lng;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLng() {
                return lng;
            }

            public void setLng(double lng) {
                this.lng = lng;
            }
        }
    }
}
