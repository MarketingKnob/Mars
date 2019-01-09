package com.mkobandroiddep.mars.ui;

import android.app.Activity;
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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;
import com.mkobandroiddep.mars.util.DialogUtil;
import com.mkobandroiddep.mars.util.ProgressDialogUtil;
import com.mkobandroiddep.mars.util.TinyDB;
import com.mkobandroiddep.mars.webservices.ApiHelper;
import com.mkobandroiddep.mars.webservices.interfaces.ApiResponseHelper;
import com.mkobandroiddep.mars.webservices.webresponse.TrailResponse;
import com.mkobandroiddep.mars.webservices.webresponse.TraineeLoginRespose;
import com.valdesekamdem.library.mdtoast.MDToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class LoginActivity extends Activity implements View.OnClickListener , ApiResponseHelper {

    @BindView(R.id.input_username)  AppCompatEditText etName;
    @BindView(R.id.input_pass)      AppCompatEditText etPassword;
    @BindView(R.id.btn_login)       AppCompatButton btnLogin;
    @BindView(R.id.ll_main)         LinearLayout llMain;
    Context context;
    public String strPass ="", strUsername ="";
    private ProgressDialog pd;
    private static final String TAG = "LoginActivity";
    TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    void init() {

        tinyDB  = new TinyDB(this);
        context = LoginActivity.this;
        ButterKnife.bind(this);
        llMain.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btnLogin){
            CommonUtil.hideKeyboard(this);
            if (!CommonUtil.isNetworkAvailable(this)) {
                DialogUtil.showDialogMsg(context,
                        "Internet Error", getResources().getString(R.string.login_offline));

            } else {
                login();
            }
        }else if (v==llMain){
            CommonUtil.hideKeyboard(this);
        }

    }

    private void login() {

        if (!validateUsername()) {
            return;
        }
        if (!validatePassword()) {
            return;
        }

        pd          = ProgressDialogUtil.getProgressDialogMsg(context, getResources().getString(R.string.login_online));
        pd.show();

        new ApiHelper().traineeLogin(strUsername,strPass,LoginActivity.this);

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
     * Username Validation
     */

    private boolean validatePassword() {
        strPass = etPassword.getText().toString().trim();

        if (strPass.isEmpty() ) {
            DialogUtil.showDialogMsg(context, "Pass Error", getResources().getString(R.string.err_msg_pass));
            return false;
        } else {

        }

        return true;
    }

    @Override
    public void onSuccess(Response<JsonElement> response, String typeApi) {
        dismissDialog();
        if(typeApi.equalsIgnoreCase("trainee_Login")) {
            TraineeLoginRespose traineeLoginRespose = new Gson().fromJson(response.body(), TraineeLoginRespose.class);
            Log.d(TAG, "onSuccess: "+traineeLoginRespose);
            if(traineeLoginRespose != null) {
                if (traineeLoginRespose.getResponseCode()==200) {

                    if (traineeLoginRespose.getTraineeStatus()==1){
                        MDToast.makeText( context, ""+traineeLoginRespose.getMessage(),MDToast.LENGTH_SHORT,
                                MDToast.TYPE_SUCCESS).show();

                        tinyDB.putInt("TraineeStatus",traineeLoginRespose.getTraineeStatus());
                        tinyDB.putString("TraineeName",traineeLoginRespose.getTraineeData().getTraineeName());
                        tinyDB.putInt("TraineeId",traineeLoginRespose.getTraineeData().getTraineeId());
                        tinyDB.putString("TraineeGender",traineeLoginRespose.getTraineeData().getTraineeGender());
                        tinyDB.putString("TraineeDOB",traineeLoginRespose.getTraineeData().getTraineeDateOfBirth());
                        tinyDB.putString("TraineePhone",traineeLoginRespose.getTraineeData().getTraineePhoneNo());

                        startActivity(new Intent(context,HomeActivity.class));
                        finish();

                    }
                    else {
                        MDToast.makeText( context, "You are deactivated now.",MDToast.LENGTH_SHORT,
                                MDToast.TYPE_WARNING).show();
                    }

                } else {
                    MDToast.makeText( context, ""+traineeLoginRespose.getMessage(),MDToast.LENGTH_SHORT,
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
