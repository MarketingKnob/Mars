package com.mkobandroiddep.mars.webservices.interfaces;

import com.google.gson.JsonElement;
import com.mkobandroiddep.mars.webservices.WebConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Akshay
 */

public interface WebApi {

    /*SignUp*/
    @FormUrlEncoded
    @POST(WebConstants.TRIAL_REGISTRATION)
    Call<JsonElement> trailRegistration(@Field("name") String name, @Field("email") String email, @Field("phone_no") String phone_no,
                                 @Field("fitness_goal") String fitness_goal);

   /*Login Trainee*/
    @FormUrlEncoded
    @POST(WebConstants.TRAINEE_LOGIN)
    Call<JsonElement> traineeLogin(@Field("username") String username, @Field("password") String password);


    /*BMI calculator*/
    @FormUrlEncoded
    @POST(WebConstants.BMI_CALCULATOR)
    Call<JsonElement> bmiCalculator(@Field("user_id") String user_id, @Field("age") String age, @Field("height") String height,
                                    @Field("weight") String weight, @Field("bmi_result") String bmi_result);


    /*Trainee Prof Update*/
    @FormUrlEncoded
    @POST(WebConstants.Traineee_Prof_update)
    Call<JsonElement> traineeUpdate(@Field("user_id") String user_id, @Field("name") String name, @Field("gender") String gender,
                                    @Field("date_of_birth") String date_of_birth, @Field("phone_no") String phone_no);

}
