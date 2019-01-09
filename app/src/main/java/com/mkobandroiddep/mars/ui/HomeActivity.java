package com.mkobandroiddep.mars.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mkobandroiddep.mars.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_profile)   LinearLayout llMain;
    @BindView(R.id.ll_bmi)       LinearLayout llBmi;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        init();
    }

    void init() {

        context = HomeActivity.this;
        ButterKnife.bind(this);
        llMain.setOnClickListener(this);
        llBmi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==llMain){
            startActivity(new Intent(context,TraineeProfileActivity.class));
        }   else if (v==llBmi){
            startActivity(new Intent(context,BmiCalcuActivity.class));
        }
    }
}
