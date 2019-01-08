package com.mkobandroiddep.mars.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;
import com.mkobandroiddep.mars.util.DialogUtil;
import com.mkobandroiddep.mars.util.ProgressDialogUtil;
import com.mkobandroiddep.mars.webservices.ApiHelper;
import com.mkobandroiddep.mars.webservices.interfaces.ApiResponseHelper;
import com.mkobandroiddep.mars.webservices.webresponse.TrailResponse;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class TrialActivity extends AppCompatActivity implements View.OnClickListener , ApiResponseHelper {

    @BindView(R.id.input_name)  AppCompatEditText etName;
    @BindView(R.id.input_phone) AppCompatEditText etPhone;
    @BindView(R.id.input_email) AppCompatEditText etEmail;
    @BindView(R.id.input_query) AppCompatEditText etQuery;
    @BindView(R.id.btn_submit)  AppCompatButton btnSubmit;
    @BindView(R.id.ll_main)     LinearLayoutCompat llMain;

    public String strEmail ="", strUsername ="", strPhone ="",strQuery="";
    Context context;
    ProgressDialog pd;
    private static final String TAG = "TrialActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial);

        init();

    }

    void init() {

        context = TrialActivity.this;
        ButterKnife.bind(this);

        btnSubmit.setOnClickListener(this);
        llMain.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v==btnSubmit){
            if (!CommonUtil.isNetworkAvailable(this)) {
                DialogUtil.showDialogMsg(context,
                        "Internet Error", getResources().getString(R.string.login_offline));

            } else {
                submitQuery();
            }
        }else if (v==llMain){
            CommonUtil.hideKeyboard(TrialActivity.this);
        }

    }

    private void submitQuery() {
        CommonUtil.hideKeyboard(TrialActivity.this);

        if (!validateUsername()) {
            return;
        }
        if (!validatePhone()) {
            return;
        }
        if (!validateEmail()) {
            return;
        }
        strQuery    = etQuery.getText().toString();
        pd          = ProgressDialogUtil.getProgressDialogMsg(context, getResources().getString(R.string.loading_wait));
        pd.show();

        new ApiHelper().trailRegistration(strUsername,strEmail,strPhone,strQuery,TrialActivity.this);
    }

    /**
     * Username Validation
     */
    private boolean validateUsername() {
        strUsername = etName.getText().toString().trim();

        if (strUsername.isEmpty() ) {
            DialogUtil.showDialogMsg(context, "Username Error", getResources().getString(R.string.err_msg_username));
            return false;
        } else {

        }

        return true;
    }

    /**
     * Phone Number Validation
     */
    private boolean validatePhone() {
        strPhone = etPhone.getText().toString().trim();

        if (strPhone.isEmpty() || !CommonUtil.isValidnumber(strPhone)) {
            DialogUtil.showDialogMsg(context, "Number Error", getResources().getString(R.string.error_wrong_number));
            return false;

        } else {

        }

        return true;
    }

    /**
     * Email Validation
     */
    private boolean validateEmail() {
        strEmail = etEmail.getText().toString().trim();

            if (!CommonUtil.isValidEmail(strEmail)) {
                DialogUtil.showDialogMsg(context, "Email Error",
                        getResources().getString(R.string.err_msg_email));
                return false;
            } else {
                return true;
            }
    }

    @Override
    public void onSuccess(Response<JsonElement> response, String typeApi) {
        dismissDialog();
        if(typeApi.equalsIgnoreCase("trail_registration")) {
            TrailResponse trailResponse = new Gson().fromJson(response.body(), TrailResponse.class);
            Log.d(TAG, "onSuccess: "+trailResponse);
            if(trailResponse != null) {
                if (trailResponse.getResponseCode()==200) {

                  MDToast.makeText( context, ""+trailResponse.getMessage(),MDToast.LENGTH_SHORT,
                          MDToast.TYPE_SUCCESS).show();
                  finish();
                } else {
                    MDToast.makeText( context, ""+trailResponse.getMessage(),MDToast.LENGTH_SHORT,
                            MDToast.TYPE_WARNING).show();
                }
            } else {
                DialogUtil.showDialogMsg(context, "Error", getResources().getString(R.string.error_try_again));
            }
        }

    }

    @Override
    public void onFailure(String error) {
        dismissDialog();
        DialogUtil.showDialogMsg(context, "Server Error", getResources().getString(R.string.server_error_try_again));
    }


    private void dismissDialog() {
        try {
            if (pd != null) {
                if (pd.isShowing())
                    pd.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
