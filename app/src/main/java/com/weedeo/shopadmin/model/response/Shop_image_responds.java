package com.weedeo.shopadmin.model.response;

import java.util.List;

public class Shop_image_responds {


    /**
     * data : [{"shop_id":"5da6f3962c005a000765a171","item_path":"SampleVideo_1280x720_1mb.mp4","approve_status":3,"status":true,"id":"5db8228245b9db00076b61b3"},{"shop_id":"5da6f3962c005a000765a171","item_path":"SampleVideo_1280x720_1mb.mp4","approve_status":2,"status":true,"id":"5db822ae4401e9000833b3fc"},{"shop_id":"5da6f3962c005a000765a171","item_path":"Screenshot_20191018-181731_WeedeoAdmin.jpg","approve_status":1,"status":true,"id":"5dc161237b17560007744fb7"},{"shop_id":"5da6f3962c005a000765a171","item_path":"Screenshot_20190830-155359_Maven Silicon.jpg","approve_status":1,"status":true,"id":"5dc1615555f0f10008caa842"},{"shop_id":"5da6f3962c005a000765a171","item_path":"IMG_20191029_1055235728417108192751079.jpg","approve_status":1,"status":true,"id":"5dc161ac7b17560007744fb8"},{"shop_id":"5da6f3962c005a000765a171","item_path":"Screenshot_20190916-110330_Maven Silicon.jpg","approve_status":1,"status":true,"id":"5dc1665955f0f10008caa847"},{"shop_id":"5da6f3962c005a000765a171","item_path":"1552457795959.png","approve_status":1,"status":true,"id":"5dc25cd6751d8a0007a08b3c"},{"shop_id":"5da6f3962c005a000765a171","item_path":"1552457851337.png","approve_status":1,"status":true,"id":"5dc25dbe5871b50008dbbc6d"},{"shop_id":"5da6f3962c005a000765a171","item_path":"1552457851337.png","approve_status":1,"status":true,"id":"5dc260417b7e1c0008a29b52"}]
     * message : Add profile image successfully
     * status : success
     * statusCode : 200
     */

    private String message;
    private String status;
    private int statusCode;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * shop_id : 5da6f3962c005a000765a171
         * item_path : SampleVideo_1280x720_1mb.mp4
         * approve_status : 3
         * status : true
         * id : 5db8228245b9db00076b61b3
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
