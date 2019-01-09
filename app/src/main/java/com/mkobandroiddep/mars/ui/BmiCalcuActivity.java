package com.mkobandroiddep.mars.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;
import com.mkobandroiddep.mars.util.DialogUtil;
import com.mkobandroiddep.mars.util.ProgressDialogUtil;
import com.mkobandroiddep.mars.util.TinyDB;
import com.mkobandroiddep.mars.webservices.ApiHelper;
import com.mkobandroiddep.mars.webservices.interfaces.ApiResponseHelper;
import com.mkobandroiddep.mars.webservices.webresponse.BMIResult;
import com.mkobandroiddep.mars.webservices.webresponse.TraineeLoginRespose;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.text.DecimalFormat;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class BmiCalcuActivity extends AppCompatActivity implements View.OnClickListener,ApiResponseHelper {

    @BindView(R.id.input_age)       AppCompatEditText etAge;
    @BindView(R.id.et_weight)       AppCompatEditText etWeight;
    @BindView(R.id.et_height)       AppCompatEditText etHeight;
    @BindView(R.id.tv_bmi_result)   AppCompatTextView tvResult;
    @BindView(R.id.tv_status)       AppCompatTextView tvStatus;
    @BindView(R.id.btn_bmi)         AppCompatButton btnBmi;
    @BindView(R.id.SpinnerWeight)   AppCompatSpinner Sweight;
    @BindView(R.id.SpinnerHeight)   AppCompatSpinner Sheight;
    @BindView(R.id.cpb_main)        CircularProgressBar circularProgressBar;

    Context context;

    String strAge="",strWeight="",strHeight="",strBMIResult="",strWeightUnit="",strHeightUnit="", bmiLabel = "";
    int traineeId=0;
    Float weight,height,result;
    private TinyDB tinyDB;
    private static final String TAG = "BmiCalcuActivity";
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calcu);

        init ();

    }

    private void init () {

        tinyDB        = new TinyDB(this);
        traineeId     = tinyDB.getInt("TraineeId");
        Log.d(TAG, "init: "+traineeId);
        ButterKnife.bind(this);
        context = BmiCalcuActivity.this;
        btnBmi.setOnClickListener(this);

    }

    private Float ConvertHeight() {
        Float result = 0.0f;
        Float heightResult = 0.0f;
        String unit = Sheight.getSelectedItem().toString();

        switch (unit){
            case "ft":
                heightResult = Float.parseFloat(etHeight.getText().toString());
                result = heightResult * 12.0f;
                strHeightUnit ="ft";
                break;
            case "cm":
                heightResult = Float.parseFloat(etHeight.getText().toString());
                result = heightResult * 0.3937f;
                strHeightUnit ="cm";
                break;
            default:
                result = 0.0f;
        }
        return result;
    }

    private Float ConvertWeight() {
        Float result = 0.0f;
        String unit = Sweight.getSelectedItem().toString();

        switch (unit){
            case "kg":
                Float weightResult = Float.parseFloat(etWeight.getText().toString());
                result = weightResult * 2.2f;
                strWeightUnit="Kg";
                break;
            case "lbs":
                result = Float.parseFloat(etWeight.getText().toString());
                strWeightUnit="lbs";
                break;
            default:
                result = 0.0f;
        }
        return result;
    }

    private Float Result(Float weight, Float height) {
        Float result = 0.0f;
        Float resultWeight;
        Float resultHeight;

        resultWeight = weight * 0.45f;
        resultHeight = height * 0.025f;

        resultHeight = resultHeight * resultHeight;

        result = resultWeight/resultHeight;

        return result;
    }

    private boolean IsValid() {
        boolean result = true;

        if (etAge.getText().toString().trim().length() == 0){
            etAge.setError("Enter your Age");
            return false;
        }
        if (etWeight.getText().toString().trim().length() == 0){
            etWeight.setError("Enter your weight");
            return false;
        }

        if (etHeight.getText().toString().trim().length() == 0){
            etHeight.setError("Enter your height");
            return false;
        }

        return result;
    }


    @Override
    public void onClick(View v) {
        if (v==btnBmi){

            if (IsValid()){
                strAge       = etAge.getText().toString();
                weight = ConvertWeight();
                height = ConvertHeight();

                strWeight =etWeight.getText().toString().trim()+" "+strWeightUnit;
                strHeight =etHeight.getText().toString().trim()+" "+strHeightUnit;

                result=Result(weight , height);
                displayBMI(result);

                Log.d(TAG, "onClick: HeightUnit"+strHeight+" WeightUnit"+strWeight+" Age"+strAge);

                if (!CommonUtil.isNetworkAvailable(this)) {
                    DialogUtil.showDialogMsg(context,
                            "Internet Error", getResources().getString(R.string.login_offline));

                } else {
                    pd          = ProgressDialogUtil.getProgressDialogMsg(context, getResources().getString(R.string.loading_wait));
                    pd.show();
                    new ApiHelper().bmiCalculator(String.valueOf(traineeId),strAge,strHeight,strWeight,String.valueOf(result),BmiCalcuActivity.this);
                }

            }
        }
    }

    private void displayBMI(float bmi) {


        if (Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 16f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 16f) > 0  &&  Float.compare(bmi, 18.5f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 18.5f) > 0  &&  Float.compare(bmi, 25f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 25f) > 0  &&  Float.compare(bmi, 30f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 30f) > 0  &&  Float.compare(bmi, 35f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 35f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel =""+ bmiLabel;

    }

    @Override
    public void onSuccess(Response<JsonElement> response, String typeApi) {
        dismissDialog();
        if(typeApi.equalsIgnoreCase("bmi_result")) {
            BMIResult bmiResult = new Gson().fromJson(response.body(), BMIResult.class);

            if(bmiResult != null) {
                if (bmiResult.getResponseCode()==200) {

                    MDToast.makeText( context, ""+bmiResult.getMessage(),MDToast.LENGTH_SHORT,
                            MDToast.TYPE_SUCCESS).show();

                    DecimalFormat df = new DecimalFormat("#.##");
                    circularProgressBar.setVisibility(View.VISIBLE);
                    circularProgressBar.setProgress(result);
                    tvResult.setText(df.format(Result(weight , height)).toString());
                    tvStatus.setText(bmiLabel);

                } else {
                    MDToast.makeText( context, ""+bmiResult.getMessage(),MDToast.LENGTH_SHORT,
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
