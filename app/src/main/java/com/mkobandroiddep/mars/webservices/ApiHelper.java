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


}
