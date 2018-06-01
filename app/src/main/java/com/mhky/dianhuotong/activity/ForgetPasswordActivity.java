package com.mhky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.tool.TimerMessage;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.login.ForgetPassWordIF;
import com.mhky.dianhuotong.login.ForgetPasswordPrecenter;
import com.mhky.dianhuotong.person.bean.ChangeMobilePhoneInfo;
import com.mhky.dianhuotong.person.pesenter.ChangePhonePrecenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgetPasswordActivity extends BaseActivity implements ForgetPassWordIF, TimerMessage.OnTimerListener {
    @BindView(R.id.forgetpwd_titlebar)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.forgetpwd_phone)
    EditText editTextPhone;
    @BindView(R.id.forgetpwd_code)
    EditText editTextCode;
    @BindView(R.id.forgetpwd_getsms)
    TextView textViewGetSms;
    @BindView(R.id.forgetpwd_pwd)
    EditText editTextPwd;
    @BindView(R.id.forgetpwd_ishow_pwd)
    ImageView imageViewIsshowPwd;
    @BindView(R.id.forgetpwd_ok)
    TextView textViewOk;
    private boolean isSendSMS = false;
    private boolean isTimerStart = false;
    private boolean imageViewState;
    private ForgetPasswordPrecenter forgetPasswordPrecenter;
    private TimerMessage timerMessage;
    private static final String TAG = "ChangePhoneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        isTimerStart = false;
        imageViewState = false;
        diaHuiTongBaseTitleBar.setCenterTextView("忘记密码");
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        timerMessage = new TimerMessage(60000, 1000, this);
        forgetPasswordPrecenter = new ForgetPasswordPrecenter(this);
    }

    @OnClick(R.id.forgetpwd_ok)
    void changePhone() {
        if (false) {
            ToastUtil.makeText(this, "后台调试中", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(editTextPhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        } else if (!isSendSMS) {
            ToastUtil.makeText(this, "请点击获取验证码", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextCode.getText())) {
            ToastUtil.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextCode.getText().length() != 4) {
            ToastUtil.makeText(this, "验证码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextPwd.getText())) {
            ToastUtil.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPwd.getText().toString().length() < 6) {
            ToastUtil.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        forgetPasswordPrecenter.checkSMS(editTextPhone.getText().toString(), editTextCode.getText().toString());
    }

    @OnClick(R.id.forgetpwd_getsms)
    void getSMS() {
        if (TextUtils.isEmpty(editTextPhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        forgetPasswordPrecenter.getMsm(editTextPhone.getText().toString());

    }

    /**
     * 隐藏或显示密码事件
     */
    @OnClick(R.id.forgetpwd_ishow_pwd)
    void setImageView() {
        if (!imageViewState) {
            imageViewIsshowPwd.setImageResource(R.drawable.icon_view_pwd);
            editTextPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageViewState = true;
        } else {
            imageViewIsshowPwd.setImageResource(R.drawable.icon_hide_pwd);
            editTextPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            imageViewState = false;
        }
        editTextPwd.setSelection(editTextPwd.getText().length());
    }


    @Override
    public void onTick(String time) {
        textViewGetSms.setText(time);
    }

    @Override
    public void onFinish(String fi) {
        textViewGetSms.setText(fi);
        messageButtonNo();
    }

    /**
     * 设置发送密码可以点击
     */
    private void messageButtonOk() {
        textViewGetSms.setClickable(true);
        textViewGetSms.setTextColor(getResources().getColor(R.color.color04c1ab));
        textViewGetSms.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode));
    }

    /**
     * 设置发送密码不可以点击
     */
    private void messageButtonNo() {
        textViewGetSms.setTextColor(getResources().getColor(R.color.colorBFBFBF));
        textViewGetSms.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode_grey));
        textViewGetSms.setClickable(false);
    }

    @Override
    public void forgetPwdSuccess(int code, String result) {
        if (code == 200) {
            ToastUtil.makeText(this, "重置成功o(*￣▽￣*)o", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void forgetPwdFailed(int code, String result) {
        ToastUtil.makeText(this, "重置失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getForgetPwdPhoneSmsSuccess(int code, String result) {
        if (code == 200) {
            isSendSMS = true;
            timerMessage.start();
            isTimerStart = true;
            messageButtonNo();
            ToastUtil.makeText(this, "发送成功！", Toast.LENGTH_SHORT).show();
        } else if (code == 202) {
            ToastUtil.makeText(this, "手机号码未注册！", Toast.LENGTH_SHORT).show();
        } else {
            ToastUtil.makeText(this, "无法发送验证码！", Toast.LENGTH_SHORT).show();
        }
        BaseTool.logPrint(TAG, "SMSonSuccess: " + code + "-----" + result);
    }

    @Override
    public void getForgetPwdPhoneSmsFailed(int code, String result) {
        textViewGetSms.setText("获取验证码");
        messageButtonOk();
        BaseTool.logPrint(TAG, "SMSonFailed: " + code + "-----" + result);
    }

    @Override
    public void checkForgetPwdPhoneSmsSuccess(int code, String result) {
        if (code == 200) {
            BaseTool.logPrint(TAG, "checkSmsSuccess: ----");
            ChangeMobilePhoneInfo changeMobilePhoneInfo = new ChangeMobilePhoneInfo();
            changeMobilePhoneInfo.setMobile(editTextPhone.getText().toString());
//            changePhonePrecenter.changePhone(BaseApplication.getInstansApp().getLoginRequestInfo().getId(), JSON.toJSONString(changeMobilePhoneInfo));
        } else {
            ToastUtil.makeText(this, "验证码校验失败" + code, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkForgetPwdPhoneSmsFailed(int code, String result) {
        ToastUtil.makeText(this, "验证码校验失败", Toast.LENGTH_SHORT).show();
    }
}
