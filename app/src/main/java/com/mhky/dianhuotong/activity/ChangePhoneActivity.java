package com.mhky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.liqi.utils.encoding.MD5Util;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.tool.TimerMessage;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.person.bean.ChangeMobilePhoneInfo;
import com.mhky.dianhuotong.person.personif.ChangePhoneIF;
import com.mhky.dianhuotong.person.pesenter.ChangePhonePrecenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePhoneActivity extends BaseActivity implements ChangePhoneIF, TimerMessage.OnTimerListener {
    @BindView(R.id.change_phone_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.change_phone_text)
    EditText editTextChangePhone;
    @BindView(R.id.change_phone_code)
    EditText editTextChangeCode;
    @BindView(R.id.change_phone_sendSMS)
    TextView textViewSendSMS;
    @BindView(R.id.change_phone_button)
    TextView textViewChangeButton;
    private boolean isSendSMS = false;
    private boolean isTimerStart = false;
    private ChangePhonePrecenter changePhonePrecenter;
    private TimerMessage timerMessage;
    private static final String TAG = "ChangePhoneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bind_phone);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.bindphone_title));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        changePhonePrecenter = new ChangePhonePrecenter(this);
        timerMessage = new TimerMessage(60000, 1000, this);
    }

    @OnClick(R.id.change_phone_button)
    void changePhone() {
        if (TextUtils.isEmpty(editTextChangePhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextChangePhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        } else if (!isSendSMS) {
            ToastUtil.makeText(this, "请点击获取验证码", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextChangeCode.getText())) {
            ToastUtil.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextChangeCode.getText().length() != 4) {
            ToastUtil.makeText(this, "验证码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        changePhonePrecenter.checkSMS(editTextChangePhone.getText().toString(), editTextChangeCode.getText().toString());
    }

    @OnClick(R.id.change_phone_sendSMS)
    void getSMS() {
        if (TextUtils.isEmpty(editTextChangePhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextChangePhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        changePhonePrecenter.getMsm(editTextChangePhone.getText().toString());

    }

    @Override
    public void changePhoneSuccess(int code, String result) {
        if (code == 200) {
            ToastUtil.makeText(this, "修改成功o(*￣▽￣*)o", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    @Override
    public void changePhoneFailed(int code, String result) {
        ToastUtil.makeText(this, "修改失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getchangePhoneSmsSuccess(int code, String result) {
        if (code == 200) {
            isSendSMS = true;
            timerMessage.start();
            isTimerStart = true;
            messageButtonNo();
            ToastUtil.makeText(this, "发送成功！", Toast.LENGTH_SHORT).show();
        } else if (code == 202) {
            ToastUtil.makeText(this, "手机号码已注册！", Toast.LENGTH_SHORT).show();
        } else {
            ToastUtil.makeText(this, "无法发送验证码！", Toast.LENGTH_SHORT).show();
        }
        BaseTool.logPrint(TAG, "SMSonSuccess: " + code + "-----" + result);
    }

    @Override
    public void getchangePhoneSmsfailed(int code, String result) {
        textViewSendSMS.setText("获取验证码");
        messageButtonOk();
        BaseTool.logPrint(TAG, "SMSonFailed: " + code + "-----" + result);
    }

    @Override
    public void checkchangePhoneSmsSuccess(int code, String result) {
        if (code == 200) {
            BaseTool.logPrint(TAG, "checkSmsSuccess: ----");
            ChangeMobilePhoneInfo changeMobilePhoneInfo = new ChangeMobilePhoneInfo();
            changeMobilePhoneInfo.setMobile(editTextChangePhone.getText().toString());
            changePhonePrecenter.changePhone(BaseApplication.getInstansApp().getLoginRequestInfo().getId(), JSON.toJSONString(changeMobilePhoneInfo));
        } else {
            ToastUtil.makeText(this, "验证码校验失败" + code, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void checkchangePhoneSmsFailed(int code, String result) {
        ToastUtil.makeText(this, "验证码校验失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTick(String time) {
        textViewSendSMS.setText(time);
    }

    @Override
    public void onFinish(String fi) {
        textViewSendSMS.setText(fi);
        messageButtonNo();
    }

    /**
     * 设置发送密码可以点击
     */
    private void messageButtonOk() {
        textViewSendSMS.setClickable(true);
        textViewSendSMS.setTextColor(getResources().getColor(R.color.color04c1ab));
        textViewSendSMS.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode));
    }

    /**
     * 设置发送密码不可以点击
     */
    private void messageButtonNo() {
        textViewSendSMS.setTextColor(getResources().getColor(R.color.colorBFBFBF));
        textViewSendSMS.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode_grey));
        textViewSendSMS.setClickable(false);
    }
}
