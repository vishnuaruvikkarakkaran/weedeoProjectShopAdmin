package com.weedeo.shopadmin.model.response;

import java.util.List;
/** created by vishnu
 * 12/11/2019
 * */

public class Profile_info_responds  {

    /**
     * data : {"shop_name":"vishnu shop","primary_mobile":9497572861,"status":true,"shop_status":1,"shop_online_status":false,"location_status":true,"primary_image":"wall-of-china_0.jpg","primary_image_status":2,"id":"5dca5a4b6c729f0008ad887b","shopProfiles":[{"shop_id":"5dca5a4b6c729f0008ad887b","item_path":"IMG_20191112_1505567843661511046944421.jpg","approve_status":2,"status":true,"id":"5dca7d333c633300072cae6f"}]}
     * message : Shop details get successfully
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
         * shop_name : vishnu shop
         * primary_mobile : 9497572861
         * status : true
         * shop_status : 1
         * shop_online_status : false
         * location_status : true
         * primary_image : wall-of-china_0.jpg
         * primary_image_status : 2
         * id : 5dca5a4b6c729f0008ad887b
         * shopProfiles : [{"shop_id":"5dca5a4b6c729f0008ad887b","item_path":"IMG_20191112_1505567843661511046944421.jpg","approve_status":2,"status":true,"id":"5dca7d333c633300072cae6f"}]
         */

        private String shop_name;
        private long primary_mobile;
        private boolean status;
        private int shop_status;
        private boolean shop_online_status;
        private boolean location_status;
        private String primary_image;
        private int primary_image_status;
        private String id;
        private List<ShopProfilesBean> shopProfiles;

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

        public String getPrimary_image() {
            return primary_image;
        }

        public void setPrimary_image(String primary_image) {
            this.primary_image = primary_image;
        }

        public int getPrimary_image_status() {
            return primary_image_status;
        }

        public void setPrimary_image_status(int primary_image_status) {
            this.primary_image_status = primary_image_status;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<ShopProfilesBean> getShopProfiles() {
            return shopProfiles;
        }

        public void setShopProfiles(List<ShopProfilesBean> shopProfiles) {
            this.shopProfiles = shopProfiles;
        }

        public static class ShopProfilesBean {
            /**
             * shop_id : 5dca5a4b6c729f0008ad887b
             * item_path : IMG_20191112_1505567843661511046944421.jpg
             * approve_status : 2
             * status : true
             * id : 5dca7d333c633300072cae6f
             */

            private String shop_id;
            private String item_path;
            private int approve_status;
            private boolean status;
            private String id;

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

            public int getApprove_status() {
                return approve_status;
            }

            public void setApprove_status(int approve_status) {
                this.approve_status = approve_status;
            }

            public boolean isStatus() {
                return status;
            }

            public void setStatus(boolean status) {
                this.status = status;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }
    }
}
