package com.mkobandroiddep.mars.webservices.webresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TraineeLoginRespose {

    @SerializedName("response_code")
    @Expose
    private Integer responseCode;
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("trainee_status")
    @Expose
    private Integer traineeStatus;
    @SerializedName("trainee_data")
    @Expose
    private TraineeData traineeData;

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getTraineeStatus() {
        return traineeStatus;
    }

    public void setTraineeStatus(Integer traineeStatus) {
        this.traineeStatus = traineeStatus;
    }

    public TraineeData getTraineeData() {
        return traineeData;
    }

    public void setTraineeData(TraineeData traineeData) {
        this.traineeData = traineeData;
    }

    public class TraineeData {

        @SerializedName("trainee_id")
        @Expose
        private Integer traineeId;
        @SerializedName("trainee_name")
        @Expose
        private String traineeName;
        @SerializedName("trainee_email")
        @Expose
        private String traineeEmail;
        @SerializedName("trainee_phone_no")
        @Expose
        private String traineePhoneNo;
        @SerializedName("trainee_gender")
        @Expose
        private String traineeGender;
        @SerializedName("trainer_id")
        @Expose
        private Integer trainerId;
        @SerializedName("membership_id")
        @Expose
        private String membershipId;
        @SerializedName("trainee_image")
        @Expose
        private String traineeImage;
        @SerializedName("trainee_date_of_birth")
        @Expose
        private String traineeDateOfBirth;
        @SerializedName("trainee_address1")
        @Expose
        private String traineeAddress1;
        @SerializedName("trainee_age")
        @Expose
        private String traineeAge;
        @SerializedName("trainee_state")
        @Expose
        private String traineeState;
        @SerializedName("trainee_city")
        @Expose
        private String traineeCity;
        @SerializedName("trainee_post_code")
        @Expose
        private Integer traineePostCode;
        @SerializedName("trainee_experience")
        @Expose
        private String traineeExperience;
        @SerializedName("trainee_blood_group")
        @Expose
        private String traineeBloodGroup;
        @SerializedName("trainee_medical_injury")
        @Expose
        private String traineeMedicalInjury;
        @SerializedName("trainee_medical_discription")
        @Expose
        private String traineeMedicalDiscription;

        public Integer getTraineeId() {
            return traineeId;
        }

        public void setTraineeId(Integer traineeId) {
            this.traineeId = traineeId;
        }

        public String getTraineeName() {
            return traineeName;
        }

        public void setTraineeName(String traineeName) {
            this.traineeName = traineeName;
        }

        public String getTraineeEmail() {
            return traineeEmail;
        }

        public void setTraineeEmail(String traineeEmail) {
            this.traineeEmail = traineeEmail;
        }

        public String getTraineePhoneNo() {
            return traineePhoneNo;
        }

        public void setTraineePhoneNo(String traineePhoneNo) {
            this.traineePhoneNo = traineePhoneNo;
        }

        public String getTraineeGender() {
            return traineeGender;
        }

        public void setTraineeGender(String traineeGender) {
            this.traineeGender = traineeGender;
        }

        public Integer getTrainerId() {
            return trainerId;
        }

        public void setTrainerId(Integer trainerId) {
            this.trainerId = trainerId;
        }

        public String getMembershipId() {
            return membershipId;
        }

        public void setMembershipId(String membershipId) {
            this.membershipId = membershipId;
        }

        public String getTraineeImage() {
            return traineeImage;
        }

        public void setTraineeImage(String traineeImage) {
            this.traineeImage = traineeImage;
        }

        public String getTraineeDateOfBirth() {
            return traineeDateOfBirth;
        }

        public void setTraineeDateOfBirth(String traineeDateOfBirth) {
            this.traineeDateOfBirth = traineeDateOfBirth;
        }

        public String getTraineeAddress1() {
            return traineeAddress1;
        }

        public void setTraineeAddress1(String traineeAddress1) {
            this.traineeAddress1 = traineeAddress1;
        }

        public String getTraineeAge() {
            return traineeAge;
        }

        public void setTraineeAge(String traineeAge) {
            this.traineeAge = traineeAge;
        }

        public String getTraineeState() {
            return traineeState;
        }

        public void setTraineeState(String traineeState) {
            this.traineeState = traineeState;
        }

        public String getTraineeCity() {
            return traineeCity;
        }

        public void setTraineeCity(String traineeCity) {
            this.traineeCity = traineeCity;
        }

        public Integer getTraineePostCode() {
            return traineePostCode;
        }

        public void setTraineePostCode(Integer traineePostCode) {
            this.traineePostCode = traineePostCode;
        }

        public String getTraineeExperience() {
            return traineeExperience;
        }

        public void setTraineeExperience(String traineeExperience) {
            this.traineeExperience = traineeExperience;
        }

        public String getTraineeBloodGroup() {
            return traineeBloodGroup;
        }

        public void setTraineeBloodGroup(String traineeBloodGroup) {
            this.traineeBloodGroup = traineeBloodGroup;
        }

        public String getTraineeMedicalInjury() {
            return traineeMedicalInjury;
        }

        public void setTraineeMedicalInjury(String traineeMedicalInjury) {
            this.traineeMedicalInjury = traineeMedicalInjury;
        }

        public String getTraineeMedicalDiscription() {
            return traineeMedicalDiscription;
        }

        public void setTraineeMedicalDiscription(String traineeMedicalDiscription) {
            this.traineeMedicalDiscription = traineeMedicalDiscription;
        }

    }
}