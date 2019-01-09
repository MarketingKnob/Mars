package com.mkobandroiddep.mars.webservices;

import android.util.Log;
import com.google.gson.JsonElement;
import com.mkobandroiddep.mars.webservices.interfaces.ApiResponseHelper;
import com.mkobandroiddep.mars.webservices.interfaces.WebApi;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Akshya.
 */

public class ApiHelper {

    private ApiResponseHelper apiResponseHelper;

    /*/Trail Registration/*/
    public void trailRegistration(String name,String email,String phone,String fitness_goal, final ApiResponseHelper apiResponseHelper) {

        this.apiResponseHelper = apiResponseHelper;
        Call<JsonElement> call = ApiClient.getClient().create(WebApi.class).trailRegistration(name,email,phone,fitness_goal);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                apiResponseHelper.onSuccess(response, "trail_registration");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("trail_registration", "//" + t.getMessage());
                apiResponseHelper.onFailure(t.getMessage());
            }
        });
    }


    /*/Trail Registration/*/
    public void traineeLogin(String username,String passwprd, final ApiResponseHelper apiResponseHelper) {

        this.apiResponseHelper = apiResponseHelper;
        Call<JsonElement> call = ApiClient.getClient().create(WebApi.class).traineeLogin(username,passwprd);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                apiResponseHelper.onSuccess(response, "trainee_Login");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("trainee_Login", "//" + t.getMessage());
                apiResponseHelper.onFailure(t.getMessage());
            }
        });
    }

    /*/BMI Calculator/*/
    public void bmiCalculator(String userId,String age,String height,String weight,String bmiResult, final ApiResponseHelper apiResponseHelper) {

        this.apiResponseHelper = apiResponseHelper;
        Call<JsonElement> call = ApiClient.getClient().create(WebApi.class).bmiCalculator(userId,age,height,weight,bmiResult);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                apiResponseHelper.onSuccess(response, "bmi_result");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("bmi_result", "//" + t.getMessage());
                apiResponseHelper.onFailure(t.getMessage());
            }
        });
    }


    /*/BMI Calculator/*/
    public void traineeProfileUpdate(String userId,String name,String gender,String dob,String phone, final ApiResponseHelper apiResponseHelper) {

        this.apiResponseHelper = apiResponseHelper;
        Call<JsonElement> call = ApiClient.getClient().create(WebApi.class).traineeUpdate(userId,name,gender,dob,phone);

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {
                apiResponseHelper.onSuccess(response, "trainee_profile_update");
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {
                Log.e("trainee_profile_update", "//" + t.getMessage());
                apiResponseHelper.onFailure(t.getMessage());
            }
        });
    }


}
