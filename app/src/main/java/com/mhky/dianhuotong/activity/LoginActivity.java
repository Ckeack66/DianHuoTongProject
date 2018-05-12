package com.mhky.dianhuotong.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.liqi.utils.encoding.MD5Util;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.login.LoginIF;
import com.mhky.dianhuotong.login.LoginPrecenter;
import com.mhky.dianhuotong.login.LoginRequestInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginIF {
    @BindView(R.id.login_title)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.login_phone)
    EditText editTextPhone;
    @BindView(R.id.login_pwd)
    EditText editTextPwd;
    @BindView(R.id.login_login)
    TextView textViewLogin;
    private LoginPrecenter loginPrecenter;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        ImmersionBar.with(this).titleBar(diaHuiTongBaseTitleBar).transparentStatusBar().init();
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView("登录");
        diaHuiTongBaseTitleBar.setBackGround(Color.parseColor("#00ffffff"));
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loginPrecenter = new LoginPrecenter(this);
    }

    @OnClick(R.id.login_login)
    void login() {
        if (TextUtils.isEmpty(editTextPhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextPwd.getText())) {
            ToastUtil.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPwd.getText().toString().length() < 6) {
            ToastUtil.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        loginPrecenter.Login(editTextPhone.getText().toString(), String.valueOf(MD5Util.md5(editTextPwd.getText().toString().trim())));
    }

    @OnClick(R.id.login_go_register)
    void goRegisterActivity() {
        BaseTool.goActivityNoData(this, RegisterActivity.class);
    }

    @OnClick(R.id.login_go_forgetpwd)
    void goForgetPwdActivity() {
        BaseTool.goActivityNoData(this, ForgetPasswordActivity.class);
    }

    @Override
    public void LoginSucess(int code, String result) {
        if (code == 200) {
            ToastUtil.makeText(this, "登陆成功..", Toast.LENGTH_SHORT).show();
            BaseApplication.getInstansApp().setMypswsds(MD5Util.md5(editTextPwd.getText().toString().trim()));
            finish();
        } else {
            ToastUtil.makeText(this, "登陆失败..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void LoginFailed(int code, String result) {
        ToastUtil.makeText(this, "无法连接服务器..", Toast.LENGTH_SHORT).show();
    }
}
