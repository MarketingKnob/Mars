package com.mkobandroiddep.mars.webservices.webresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TraineeProfileResponse {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("profile_data")
    @Expose
    private ProfileData profileData;
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

    public ProfileData getProfileData() {
        return profileData;
    }

    public void setProfileData(ProfileData profileData) {
        this.profileData = profileData;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public class ProfileData {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("date_of_birth")
        @Expose
        private String dateOfBirth;
        @SerializedName("phone_no")
        @Expose
        private String phoneNo;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getDateOfBirth() {
            return dateOfBirth;
        }

        public void setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
        }

        public String getPhoneNo() {
            return phoneNo;
        }

        public void setPhoneNo(String phoneNo) {
            this.phoneNo = phoneNo;
        }

    }
}