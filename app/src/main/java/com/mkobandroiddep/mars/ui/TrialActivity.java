package com.mkobandroiddep.mars.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.Toast;

import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;
import com.mkobandroiddep.mars.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrialActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.input_name)  AppCompatEditText etName;
    @BindView(R.id.input_phone) AppCompatEditText etPhone;
    @BindView(R.id.input_email) AppCompatEditText etEmail;
    @BindView(R.id.input_query) AppCompatEditText etQuery;
    @BindView(R.id.btn_submit)  AppCompatButton btnSubmit;
    @BindView(R.id.ll_main)     LinearLayoutCompat llMain;

    public String strEmail ="", strUsername ="", strPhone ="",strQuery;
    Context context;

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
        Toast.makeText(context, "Submit", Toast.LENGTH_SHORT).show();
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

}
