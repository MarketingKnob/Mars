package com.mkobandroiddep.mars.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.mkobandroiddep.mars.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.btn_login)  AppCompatButton btnLogin;
    @BindView(R.id.btn_trial)  AppCompatButton btnTrial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        init();
    }

    void init() {

        ButterKnife.bind(this);

        btnTrial.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==btnTrial){
            startActivity(new Intent(this,TrialActivity.class));

        }else if (v==btnLogin){
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
    }
}
