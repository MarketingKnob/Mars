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

}
