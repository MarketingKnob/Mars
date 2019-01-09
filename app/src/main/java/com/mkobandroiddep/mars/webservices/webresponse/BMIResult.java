package com.mkobandroiddep.mars.webservices.webresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BMIResult {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}