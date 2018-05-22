package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.liqi.utils.encoding.MD5Util;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mingle.widget.ShapeLoadingDialog;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.bean.RegisterPostDataInfo;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.tool.TimerMessage;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.register.RegisterIF;
import com.mhky.dianhuotong.register.RegisterPrecenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements TimerMessage.OnTimerListener, RegisterIF {
    @BindView(R.id.register_titlebar)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.register_getmessage)
    TextView txtMessage;
    @BindView(R.id.register_phone)
    EditText editTextPhone;
    @BindView(R.id.register_phone_code)
    EditText editTextPhoneCode;
    @BindView(R.id.register_pwd)
    EditText editTextPwd;
    @BindView(R.id.register_imageview)
    ImageView imageView;
    private TimerMessage timerMessage;
    private boolean isTimerStart;
    private boolean imageViewState;
//    private ShapeLoadingDialog shapeLoadingDialog;
    private RegisterPrecenter registerPrecenter;
    private RegisterPostDataInfo registerPostDataInfo;
    private boolean isSendSMS = false;
    private LoadingDialog loadingDialog;
    private static final String TAG = "RegisterActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        inIt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerMessage != null && isTimerStart) {
            timerMessage.cancel();
            messageButtonOk();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 页面初始化
     */
    private void inIt() {
        loadingDialog=new LoadingDialog(this);
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView("注册");
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        isTimerStart = false;
        imageViewState = false;
        timerMessage = new TimerMessage(60000, 1000, this);
//        shapeLoadingDialog = new ShapeLoadingDialog.Builder(this).loadText("正在注册...").build();
//        shapeLoadingDialog.getWindow().setDimAmount(0);
        registerPrecenter = new RegisterPrecenter(this);
        registerPostDataInfo = new RegisterPostDataInfo();
    }

    /**
     * 发送信息按钮
     */

    @OnClick(R.id.register_getmessage)
    void sendMessage() {
        if (TextUtils.isEmpty(editTextPhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        registerPrecenter.getMsm(editTextPhone.getText().toString());
    }

    /**
     * 注册按钮
     */
    @OnClick(R.id.register_register_button)
    void register() {
        if (TextUtils.isEmpty(editTextPhone.getText())) {
            ToastUtil.makeText(this, "请输入手机号码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPhone.getText().toString().length() < 11) {
            ToastUtil.makeText(this, "手机号码位数不正确", Toast.LENGTH_SHORT).show();
            return;
        } else if (!isSendSMS) {
            ToastUtil.makeText(this, "请点击获取验证码", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextPhoneCode.getText())) {
            ToastUtil.makeText(this, "请输入验证码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPhoneCode.getText().length() != 4) {
            ToastUtil.makeText(this, "验证码不正确", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextPwd.getText())) {
            ToastUtil.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (editTextPwd.getText().toString().length() < 6) {
            ToastUtil.makeText(this, "密码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (shapeLoadingDialog != null) {
//            shapeLoadingDialog.show();
//        }
        loadingDialog.show();
        registerPrecenter.checkSMS(editTextPhone.getText().toString().trim(), editTextPhoneCode.getText().toString().trim());
    }

    /**
     * 隐藏或显示密码事件
     */
    @OnClick(R.id.register_imageview)
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

    /**
     * 定时器每秒回调方法
     *
     * @param time 回调过来的时间+文字
     */
    @Override
    public void onTick(String time) {
        txtMessage.setText(time);
    }

    /**
     * 定时器计时结束回调方法
     *
     * @param fi 重新发送
     */
    @Override
    public void onFinish(String fi) {
        txtMessage.setText(fi);
        messageButtonOk();
    }

    /**
     * 设置发送密码可以点击
     */
    private void messageButtonOk() {
        txtMessage.setClickable(true);
        txtMessage.setTextColor(getResources().getColor(R.color.color04c1ab));
        txtMessage.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode));
    }

    /**
     * 设置发送密码不可以点击
     */
    private void messageButtonNo() {
        txtMessage.setTextColor(getResources().getColor(R.color.colorBFBFBF));
        txtMessage.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode_grey));
        txtMessage.setClickable(false);
    }

    /**
     * 注册成功回调
     *
     * @param code
     * @param result
     */
    @Override
    public void registerSuccess(int code, String result) {
//        shapeLoadingDialog.dismiss();
        if (loadingDialog!=null&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
        if (code == 200) {
            ToastUtil.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            ToastUtil.makeText(this, "发生未知错误-" + code, Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG, "onSuccess: " + code + "-----" + result);
    }

    /**
     * 注册失败回调
     *
     * @param code
     * @param result
     */
    @Override
    public void registerFailed(int code, String result) {
        if (loadingDialog!=null&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
//        shapeLoadingDialog.dismiss();
        ToastUtil.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onFailed: " + code + "-----" + result);
    }

    /**
     * 获取短信验证码成功回调
     *
     * @param code
     * @param result
     */
    @Override
    public void getSmsSuccess(int code, String result) {
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
        Log.d(TAG, "SMSonSuccess: " + code + "-----" + result);
    }

    /**
     * 获取短信验证码失败回调
     *
     * @param code
     * @param result
     */
    @Override
    public void getSmsfailed(int code, String result) {
        txtMessage.setText("获取验证码");
        messageButtonOk();
        Log.d(TAG, "SMSonFailed: " + code + "-----" + result);
    }

    /**
     * 检查验证码成功回调
     *
     * @param code
     * @param result
     */
    @Override
    public void checkSmsSuccess(int code, String result) {
        if (code == 200) {
            registerPostDataInfo.setMobile(editTextPhone.getText().toString());
            registerPostDataInfo.setPassword(String.valueOf(MD5Util.md5(editTextPwd.getText().toString().trim())));
            Log.d(TAG, "checkSmsSuccess: ----" + String.valueOf(MD5Util.md5(editTextPwd.getText().toString().trim())));
            registerPrecenter.register(JSON.toJSONString(registerPostDataInfo));
        } else {
//            shapeLoadingDialog.dismiss();
            ToastUtil.makeText(this, "验证码校验失败" + code, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 检查验证码失败回调
     *
     * @param code
     * @param result
     */
    @Override
    public void checkSmsFailed(int code, String result) {
//        shapeLoadingDialog.dismiss();
        ToastUtil.makeText(this, "验证码校验失败", Toast.LENGTH_SHORT).show();
    }


//    测试代码
    //        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//// 获取状态栏高度
//        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//
//        Log.d("测试", "onCreate: "+resourceId+"=====+"+statusBarHeight);
    //         StatusBarUtil.setTranslucent(this);
//        StatusBarUtil.setColor(this, Color.parseColor("#04c1ab"));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
}
