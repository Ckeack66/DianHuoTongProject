package com.mhky.dianhuotong.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.gyf.barlibrary.OnKeyboardListener;
import com.liqi.utils.encoding.MD5Util;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.login.LoginIF;
import com.mhky.dianhuotong.login.LoginPrecenter;
import com.mhky.dianhuotong.login.LoginRequestInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录页
 */

public class LoginActivity extends BaseActivity implements LoginIF {

    @BindView(R.id.login_title)
    DianHuoTongBaseTitleBar diaHuoTongBaseTitleBar;
    @BindView(R.id.login_phone)
    EditText editTextPhone;
    @BindView(R.id.login_pwd)
    EditText editTextPwd;
    @BindView(R.id.login_login)
    TextView textViewLogin;
    @BindView(R.id.login_imageview)
    ImageView imageView;
    private LoadingDialog loadingDialog;
    private LoginPrecenter loginPrecenter;
    private boolean imageViewState;
    private static final String TAG = "LoginActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        BaseTool.logPrint("返回结果",resultCode + "");
        if (resultCode == 7){
            Bundle bundle = data.getExtras();
            editTextPhone.setText(bundle.getString("phone"));
            editTextPwd.setText(bundle.getString("pwd"));
            BaseTool.logPrint("返回结果",bundle.getString("phone") + "--" + bundle.getString("pwd"));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        ImmersionBar.with(this).titleBar(diaHuoTongBaseTitleBar).transparentStatusBar().keyboardEnable(true,WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).setOnKeyboardListener(new OnKeyboardListener() {
            @Override
            public void onKeyboardChange(boolean isPopup, int keyboardHeight) {
                BaseTool.logPrint("键盘",String.valueOf(isPopup)+keyboardHeight);
            }
        }).init();
        imageViewState = false;
        loadingDialog = new LoadingDialog(this);
        diaHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuoTongBaseTitleBar.setCenterTextView("登录");
        diaHuoTongBaseTitleBar.setBackGround(Color.parseColor("#00ffffff"));
        diaHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        loginPrecenter = new LoginPrecenter(this);
        editTextPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editTextPwd.getText()) && s.length()>0){
                    textViewLogin.setEnabled(true);
                }else {
                    textViewLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        editTextPwd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!TextUtils.isEmpty(editTextPhone.getText())&&s.length()>0){
                    textViewLogin.setEnabled(true);
                }else {
                    textViewLogin.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
        loadingDialog.show();
        loginPrecenter.Login(editTextPhone.getText().toString(), String.valueOf(MD5Util.md5(editTextPwd.getText().toString().trim())));
    }

    @OnClick(R.id.login_imageview)
    void setImageView() {
        if (!imageViewState) {
            imageView.setImageResource(R.drawable.icon_view_pwd);
            editTextPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageViewState = true;
        } else {
            imageView.setImageResource(R.drawable.icon_hide_pwd);
            editTextPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imageViewState = false;
        }
        editTextPwd.setSelection(editTextPwd.getText().length());
    }

    @OnClick(R.id.login_go_register)
    void goRegisterActivity() {
//        BaseTool.goActivityNoData(this, RegisterActivity.class);
//        startActivityForResult(new Intent(this,RegisterActivity.class),6);
        startActivityForResult(new Intent(this,SelectRoleForRegisterActivity.class),6);
    }

    @OnClick(R.id.login_go_forgetpwd)
    void goForgetPwdActivity() {
        BaseTool.goActivityNoData(this, ForgetPasswordActivity.class);
    }

    @Override
    public void LoginSucess(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (code == 200) {
            ToastUtil.makeText(this, "登陆成功..", Toast.LENGTH_SHORT).show();
            BaseApplication.getInstansApp().setUserLoginInfo(editTextPhone.getText().toString(), MD5Util.md5(editTextPwd.getText().toString().trim()));
            BaseApplication.getInstansApp().setMypswsds(MD5Util.md5(editTextPwd.getText().toString().trim()));
            finish();
        } else {
            ToastUtil.makeText(this, "登陆失败..", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void LoginFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "无法连接服务器..", Toast.LENGTH_SHORT).show();
    }
}
