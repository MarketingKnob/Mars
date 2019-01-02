package com.mkobandroiddep.mars.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mkobandroiddep.mars.R;
import com.mkobandroiddep.mars.util.CommonUtil;
import com.mkobandroiddep.mars.util.DialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.input_username)  AppCompatEditText etName;
    @BindView(R.id.input_pass)      AppCompatEditText etPassword;
    @BindView(R.id.btn_login)       AppCompatButton btnLogin;
    @BindView(R.id.ll_main)         LinearLayout llMain;
    Context context;
    public String strPass ="", strUsername ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    void init() {

        context = LoginActivity.this;
        ButterKnife.bind(this);
        llMain.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btnLogin){
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

        Toast.makeText(context, "Login", Toast.LENGTH_SHORT).show();

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
}
