package com.weedeo.shopadmin.model.response;

public class OtpMemberResponds {

    /**
     * data : {"shop_data":{"shop_name":"vishnu aruvikkara","primary_mobile":9497572861,"status":true,"shop_status":1,"shop_online_status":false,"location_status":true},"user_data":{"name":"vishnu","mobile":"9497572861","role":2,"online_status":false,"block_status":false,"shop_id":"5da6f3962c005a000765a171","profile_pic":"default.jpg","fcm_uid":"boaWtxxlTsSM34b6HNZHIooZ3YF2","email_verified":false,"profile_completion":20,"id":"5da6f3962c005a000765a172","createdAt":"2019-10-16T10:40:22.810Z","updatedAt":"2019-11-11T04:14:12.774Z"}}
     * message : Get user details successfully
     * status : success
     * statusCode : 200
     * token : bZ7wp2WQIu72vmdcjSN7GptMcklq35FfAAJVjgOADpdiFceRxBHcQhzM6GygWDCD
     */

    private DataBean data;
    private String message;
    private String status;
    private int statusCode;
    private String token;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * shop_data : {"shop_name":"vishnu aruvikkara","primary_mobile":9497572861,"status":true,"shop_status":1,"shop_online_status":false,"location_status":true}
         * user_data : {"name":"vishnu","mobile":"9497572861","role":2,"online_status":false,"block_status":false,"shop_id":"5da6f3962c005a000765a171","profile_pic":"default.jpg","fcm_uid":"boaWtxxlTsSM34b6HNZHIooZ3YF2","email_verified":false,"profile_completion":20,"id":"5da6f3962c005a000765a172","createdAt":"2019-10-16T10:40:22.810Z","updatedAt":"2019-11-11T04:14:12.774Z"}
         */

        private ShopDataBean shop_data;
        private UserDataBean user_data;
        private String device_id;

        public ShopDataBean getShop_data() {
            return shop_data;
        }

        public void setShop_data(ShopDataBean shop_data) {
            this.shop_data = shop_data;
        }

        public UserDataBean getUser_data() {
            return user_data;
        }

        public void setUser_data(UserDataBean user_data) {
            this.user_data = user_data;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public static class ShopDataBean {
            /**
             * shop_name : vishnu aruvikkara
             * primary_mobile : 9497572861
             * status : true
             * shop_status : 1
             * shop_online_status : false
             * location_status : true
             */

            private String shop_name;
            private long primary_mobile;
            private boolean status;
            private int shop_status;
            private boolean shop_online_status;
            private boolean location_status;

            public String getShop_name() {
                return shop_name;
            }

            public void setShop_name(String shop_name) {
                this.shop_name = shop_name;
            }

            public long getPrimary_mobile() {
                return primary_mobile;
            }

            public void setPrimary_mobile(long primary_mobile) {
                this.primary_mobile = primary_mobile;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
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

            public boolean isLocation_status() {
                return location_status;
            }

            public void setLocation_status(boolean location_status) {
                this.location_status = location_status;
            }
        }

        public static class UserDataBean {
            /**
             * name : vishnu
             * mobile : 9497572861
             * role : 2
             * online_status : false
             * block_status : false
             * shop_id : 5da6f3962c005a000765a171
             * profile_pic : default.jpg
             * fcm_uid : boaWtxxlTsSM34b6HNZHIooZ3YF2
             * email_verified : false
             * profile_completion : 20
             * id : 5da6f3962c005a000765a172
             * createdAt : 2019-10-16T10:40:22.810Z
             * updatedAt : 2019-11-11T04:14:12.774Z
             */

            private String name;
            private String mobile;
            private int role;
            private boolean online_status;
            private boolean block_status;
            private String shop_id;
            private String profile_pic;
            private String fcm_uid;
            private boolean email_verified;
            private int profile_completion;
            private String id;
            private String createdAt;
            private String updatedAt;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

            public int getRole() {
                return role;
            }

            public void setRole(int role) {
                this.role = role;
            }

            public boolean isOnline_status() {
                return online_status;
            }

            public void setOnline_status(boolean online_status) {
                this.online_status = online_status;
            }

            public boolean isBlock_status() {
                return block_status;
            }

            public void setBlock_status(boolean block_status) {
                this.block_status = block_status;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getProfile_pic() {
                return profile_pic;
            }

            public void setProfile_pic(String profile_pic) {
                this.profile_pic = profile_pic;
            }

            public String getFcm_uid() {
                return fcm_uid;
            }

            public void setFcm_uid(String fcm_uid) {
                this.fcm_uid = fcm_uid;
            }

            public boolean isEmail_verified() {
                return email_verified;
            }

            public void setEmail_verified(boolean email_verified) {
                this.email_verified = email_verified;
            }

            public int getProfile_completion() {
                return profile_completion;
            }

            public void setProfile_completion(int profile_completion) {
                this.profile_completion = profile_completion;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }
        }
    }
}
