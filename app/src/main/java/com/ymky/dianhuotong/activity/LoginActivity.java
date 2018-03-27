package com.ymky.dianhuotong.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.DiaHuiTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_title)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        inIt();
    }
    private void inIt(){
        diaHuiTongBaseTitleBar.setLeftImage(R.mipmap.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView("登录");
        diaHuiTongBaseTitleBar.setBackGround(Color.parseColor("#00ffffff"));
    }
}
