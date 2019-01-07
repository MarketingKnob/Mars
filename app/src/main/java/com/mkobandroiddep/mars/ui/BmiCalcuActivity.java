package com.mkobandroiddep.mars.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.mkobandroiddep.mars.R;
import java.text.DecimalFormat;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BmiCalcuActivity extends AppCompatActivity implements View.OnClickListener{

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calcu);

        init ();

    }

    private void init () {
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
                break;
            case "cm":
                heightResult = Float.parseFloat(etHeight.getText().toString());
                result = heightResult * 0.3937f;
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
                break;
            case "lbs":
                result = Float.parseFloat(etWeight.getText().toString());
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
                Float weight = ConvertWeight();
                Float height = ConvertHeight();

                DecimalFormat df = new DecimalFormat("#.##");
                Float result=Result(weight , height);
                displayBMI(result);
                circularProgressBar.setVisibility(View.VISIBLE);
                circularProgressBar.setProgress(result);
                tvResult.setText(df.format(Result(weight , height)).toString());
            }
        }
    }

    private void displayBMI(float bmi) {
        String bmiLabel = "";

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
        tvStatus.setText(bmiLabel);
    }
}
