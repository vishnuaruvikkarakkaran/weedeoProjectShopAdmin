package com.weedeo.shopadmin.model.response;

import java.util.List;

public class LoginResponds {

    /**
     * data : []
     * message : This user exist
     * status : success
     * statusCode : 200…
     */

    private String message;
    private String status;
    private String statusCode;
    private List<?> data;

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

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
