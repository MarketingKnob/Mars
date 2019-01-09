package com.mkobandroiddep.mars.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mkobandroiddep.mars.PickerPopWin.DatePickerPopWin;
import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;
import com.mkobandroiddep.mars.util.DialogUtil;
import com.mkobandroiddep.mars.util.ProgressDialogUtil;
import com.mkobandroiddep.mars.util.TinyDB;
import com.mkobandroiddep.mars.webservices.ApiHelper;
import com.mkobandroiddep.mars.webservices.interfaces.ApiResponseHelper;
import com.mkobandroiddep.mars.webservices.webresponse.TraineeProfileResponse;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.ghyeok.stickyswitch.widget.StickySwitch;
import retrofit2.Response;

public class TraineeProfileActivity extends AppCompatActivity implements View.OnClickListener,ApiResponseHelper {

    @BindView(R.id.input_name)          AppCompatEditText etName;
    @BindView(R.id.input_phone)         AppCompatEditText etPhone;
    @BindView(R.id.tv_dob)              AppCompatTextView tvDob;
    @BindView(R.id.ll_main)             LinearLayout llMain;
    @BindView(R.id.btn_submit)          AppCompatButton btnUpdate;
    @BindView(R.id.sticky_switch)       StickySwitch stickySwitch;

    Context context;
    String strTraineeDOB ="",strTraineeName="", strTraineeGender ="Male",strTraineePhone="";
    private static final String TAG = "TraineeProfileActivity";
    TinyDB tinyDB;
    ProgressDialog pd;
    int traineeId =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

    }

    void init() {

        tinyDB  = new TinyDB(this);
        context = TraineeProfileActivity.this;

        ButterKnife.bind(this);
        tvDob.setOnClickListener(this);
        llMain.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);

        traineeId           = tinyDB.getInt("TraineeId");
        strTraineeName      = tinyDB.getString("TraineeName");
        strTraineeGender    = tinyDB.getString("TraineeGender");
        strTraineeDOB       = tinyDB.getString("TraineeDOB");
        strTraineePhone     = tinyDB.getString("TraineePhone");

        Log.d(TAG, "init: "+strTraineeGender);

        etName.setText(strTraineeName);
        etPhone.setText(strTraineePhone);

        if (strTraineeGender.equalsIgnoreCase("Male")){
            stickySwitch.setDirection(StickySwitch.Direction.LEFT);
        }else {
            stickySwitch.setDirection(StickySwitch.Direction.RIGHT);
        }

        tvDob.setText(strTraineeDOB);

        stickySwitch.setOnSelectedChangeListener(new StickySwitch.OnSelectedChangeListener() {
            @Override
            public void onSelectedChange(StickySwitch.Direction direction, String s) {
                strTraineeGender=s.trim();
                Log.d(TAG, "onSelectedChange: "+strTraineeGender);
            }
        });

    }


    @Override
    public void onClick(View v) {

        if (v==tvDob){
            CommonUtil.hideKeyboard(this);
            datePicker();
        }else if (v==llMain){
            CommonUtil.hideKeyboard(this);

        }else if (v==btnUpdate){
            CommonUtil.hideKeyboard(this);
            if (!CommonUtil.isNetworkAvailable(this)) {
                DialogUtil.showDialogMsg(context,
                        "Internet Error", getResources().getString(R.string.login_offline));

            } else {
                profileUpdate();

            }

        }

    }


    private void profileUpdate() {

        if (!validateUsername()) {
            return;
        }
        if (!validatePhone()) {
            return;
        }
        if (!validateDOB()) {
            return;
        }


        pd          = ProgressDialogUtil.getProgressDialogMsg(context, getResources().getString(R.string.loading_wait));
        pd.show();
        new ApiHelper().traineeProfileUpdate(String.valueOf(traineeId),strTraineeName,strTraineeGender,strTraineeDOB,strTraineePhone,TraineeProfileActivity.this);

    }


    /**
     * Username Validation
     */

    private boolean validateUsername() {
        strTraineeName = etName.getText().toString();

        if (strTraineeName.isEmpty() ) {
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
        strTraineePhone = etPhone.getText().toString();

        if (strTraineePhone.isEmpty() || !CommonUtil.isValidnumber(strTraineePhone)) {
            DialogUtil.showDialogMsg(context, "Number Error", getResources().getString(R.string.error_wrong_number));
            return false;

        } else {

        }

        return true;
    }

    /**
     * DOB Number Validation
     */
    private boolean validateDOB() {

        if (strTraineeDOB.isEmpty() ) {
            DialogUtil.showDialogMsg(context, "Number Error","Choose Valid date.");
            return false;

        } else {

        }

        return true;
    }

    /*Date Picker for Date of Birth Like as IOs*/
    void datePicker() {

        DateFormat originalFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        DateFormat targetFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = originalFormat.parse(strTraineeDOB);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String formattedDate = targetFormat.format(date);  // 20120821
        Log.d(TAG, "datePicker: "+formattedDate);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        int CurrentYear = calendar.get(Calendar.YEAR);
        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(TraineeProfileActivity.this,
                new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {

                        Log.d(TAG, "onDatePickCompleted: "+dateDesc+" Y"+year+" M"+month+" d"+day);
                strTraineeDOB =""+month+"/"+day+"/"+year;
                tvDob.setText(strTraineeDOB);
                    }
                }).textConfirm("CONFIRM") //text of confirm button
                .textCancel("CANCEL") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                .minYear(1960) //min year in loop
                .maxYear(CurrentYear) // max year in loop
                .dateChose(formattedDate) // date chose when init popwindow
                .build();

        pickerPopWin.showPopWin(TraineeProfileActivity.this);
    }

    @Override
    public void onSuccess(Response<JsonElement> response, String typeApi) {
        dismissDialog();
        if(typeApi.equalsIgnoreCase("trainee_profile_update")) {
            TraineeProfileResponse traineeProfileResponse = new Gson().fromJson(response.body(), TraineeProfileResponse.class);
            Log.d(TAG, "onSuccess: "+traineeProfileResponse);
            if(traineeProfileResponse != null) {
                if (traineeProfileResponse.getResponseCode()==200) {

                        MDToast.makeText( context, ""+traineeProfileResponse.getMessage(),MDToast.LENGTH_SHORT,
                                MDToast.TYPE_SUCCESS).show();

                        tinyDB.putString("TraineeName",traineeProfileResponse.getProfileData().getName());
                        tinyDB.putString("TraineeGender",traineeProfileResponse.getProfileData().getGender());
                        tinyDB.putString("TraineeDOB",traineeProfileResponse.getProfileData().getDateOfBirth());
                        tinyDB.putString("TraineePhone",traineeProfileResponse.getProfileData().getPhoneNo());

                } else {
                    MDToast.makeText( context, ""+traineeProfileResponse.getMessage(),MDToast.LENGTH_SHORT,
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
