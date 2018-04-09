package com.mhky.dianhuotong.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.login_title)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.login_phone)
    EditText editTextPhone;
    @BindView(R.id.login_pwd)
    EditText editTextPwd;
    @BindView(R.id.login_login)
    TextView textViewLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView("登录");
        diaHuiTongBaseTitleBar.setBackGround(Color.parseColor("#00ffffff"));
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick(R.id.login_login)
    void login() {
        ToastUtil.makeText(this,"登陆成功..",Toast.LENGTH_SHORT).show();
        BaseApplication.getInstansApp().setToakens("abcd1234");
        finish();
    }

    @OnClick(R.id.login_go_register)
    void goRegisterActivity() {
        BaseTool.goActivityNoData(this, RegisterActivity.class);
    }

    @OnClick(R.id.login_go_forgetpwd)
    void goForgetPwdActivity() {
        BaseTool.goActivityNoData(this, ForgetPasswordActivity.class);
    }
}
