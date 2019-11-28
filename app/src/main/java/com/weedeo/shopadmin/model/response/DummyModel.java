package com.weedeo.shopadmin.model.response;

import java.util.List;

public class DummyModel {


    private List<ShopProfilesBean> shopProfiles;

    public List<ShopProfilesBean> getShopProfiles() {
        return shopProfiles;
    }

    public void setShopProfiles(List<ShopProfilesBean> shopProfiles) {
        this.shopProfiles = shopProfiles;
    }

    public static class ShopProfilesBean {
        /**
         * shop_id :101010
         * item_path : frame7446.png
         * approve_status : 1
         * status : true
         * id : 5db8228245b9db00076b61b3
         */

        private String shop_id="101010";
        private String item_path="addnew";
        private int approve_status;
        private boolean status;
        private String id="addnew";

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
