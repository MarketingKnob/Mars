package com.mkobandroiddep.mars.ui;

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

import com.mkobandroiddep.mars.PickerPopWin.DatePickerPopWin;
import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.ghyeok.stickyswitch.widget.StickySwitch;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.input_name)          AppCompatEditText etName;
    @BindView(R.id.input_phone)         AppCompatEditText etPhone;
    @BindView(R.id.tv_dob)              AppCompatTextView tvDob;
    @BindView(R.id.ll_main)             LinearLayout llMain;
    @BindView(R.id.sticky_switch)       StickySwitch stickySwitch;

    Context context;
    private String strDOB="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        init();

    }

    void init() {

        context = ProfileActivity.this;
        ButterKnife.bind(this);
        tvDob.setOnClickListener(this);
        llMain.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v==tvDob){
            CommonUtil.hideKeyboard(this);
            datePicker();
        }else if (v==llMain){
            CommonUtil.hideKeyboard(this);

        }

    }

    /*Date Picker for Date of Birth Like as IOs*/
    void datePicker() {

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR,1);
        int CurrentYear = calendar.get(Calendar.YEAR);
        DatePickerPopWin pickerPopWin = new DatePickerPopWin.Builder(ProfileActivity.this,
                new DatePickerPopWin.OnDatePickedListener() {
                    @Override
                    public void onDatePickCompleted(int year, int month, int day, String dateDesc) {

                strDOB=""+day+"/"+month+"/"+year;
                tvDob.setText(strDOB);
                    }
                }).textConfirm("CONFIRM") //text of confirm button
                .textCancel("CANCEL") //text of cancel button
                .btnTextSize(16) // button text size
                .viewTextSize(25) // pick view text size
                .colorCancel(Color.parseColor("#999999")) //color of cancel button
                .colorConfirm(Color.parseColor("#009900"))//color of confirm button
                .minYear(1960) //min year in loop
                .maxYear(CurrentYear) // max year in loop
//                .dateChose("2013-11-11") // date chose when init popwindow
                .build();
        pickerPopWin.showPopWin(ProfileActivity.this);
    }
}
