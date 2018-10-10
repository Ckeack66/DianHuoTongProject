package com.mhky.dianhuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.liqi.utils.encoding.MD5Util;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.shop.bean.SaleManInfo;
import com.mhky.dianhuotong.shop.precenter.SaleManPresenter;
import com.mhky.dianhuotong.shop.shopif.SaleManIF;
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

/**
 * 注册的时候，先验证验证码是否符合，然后再去注册
 */

public class RegisterActivity extends BaseActivity implements TimerMessage.OnTimerListener, RegisterIF, SaleManIF, DianHuoTongBaseDialog.BaseDialogListener {
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
    @BindView(R.id.register_myweiter_code)
    EditText editTextWaiterCode;
    @BindView(R.id.register_checkBox)
    CheckBox checkBox;
    private TimerMessage timerMessage;
    private boolean isTimerStart;
    private boolean imageViewState;
    //    private ShapeLoadingDialog shapeLoadingDialog;
    private RegisterPrecenter registerPrecenter;
    private RegisterPostDataInfo registerPostDataInfo;
    private boolean isSendSMS = false;
    private LoadingDialog loadingDialog;
    private SaleManPresenter saleManPresenter;
    private DianHuoTongBaseDialog dianHuoTongBaseDialog;
    private SaleManInfo saleManInfo;
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
        loadingDialog = new LoadingDialog(this);
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
        saleManPresenter = new SaleManPresenter(this);
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
        messageButtonNo();
    }

    /**
     * 跳往协议页面
     */
    @OnClick(R.id.register_file)
    void goDocumentActivity() {
        BaseTool.goActivityNoData(this, DocumentActivity.class);
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
        } else if (!checkBox.isChecked()) {
            ToastUtil.makeText(this, "请勾选用户注册协议", Toast.LENGTH_SHORT).show();
            return;
        }
//        if (shapeLoadingDialog != null) {
//            shapeLoadingDialog.show();
//        }

//        if (!TextUtils.isEmpty(editTextWaiterCode.getText().toString())) {
////            if (editTextWaiterCode.getText().toString().trim().length() != 4) {
////                ToastUtil.makeText(this, "请输入四位服务专员工号", Toast.LENGTH_SHORT).show();
////                return;
////            } else {
////                saleManPresenter.getSaleMan(editTextWaiterCode.getText().toString().trim());
////            }
//        } else {

        loadingDialog.show();
        registerPrecenter.checkSMS(editTextPhone.getText().toString().trim(), editTextPhoneCode.getText().toString().trim());
//        }

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
     * 设置发送验证码不可以点击
     */
    private void messageButtonNo() {
        txtMessage.setClickable(false);
        txtMessage.setTextColor(getResources().getColor(R.color.colorBFBFBF));
        txtMessage.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode_grey));
    }

    /**
     * 注册成功回调
     *
     * @param code
     * @param result
     */
    @Override
    public void registerSuccess(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (code == 200) {
            ToastUtil.makeText(this, "注册成功！", Toast.LENGTH_SHORT).show();
            Bundle b = new Bundle();
            b.putString("phone",editTextPhone.getText().toString().trim());
            b.putString("pwd",editTextPwd.getText().toString().trim());
            Intent intent = new Intent();
            intent.putExtras(b);
            RegisterActivity.this.setResult(7,intent);
            RegisterActivity.this.finish();
        } else {
            ToastUtil.makeText(this, "发生未知错误-" + code, Toast.LENGTH_SHORT).show();
        }
        BaseTool.logPrint(TAG, "onSuccess: " + code + "-----" + result);
    }

    /**
     * 注册失败回调
     *
     * @param code
     * @param result
     */
    @Override
    public void registerFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "注册失败！", Toast.LENGTH_SHORT).show();
        BaseTool.logPrint(TAG, "onFailed: " + code + "-----" + result);
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
            ToastUtil.makeText(this, "发送成功！", Toast.LENGTH_SHORT).show();
        } else if (code == 202) {
            messageButtonOk();
            ToastUtil.makeText(this, "手机号码已注册！", Toast.LENGTH_SHORT).show();
        } else {
            messageButtonOk();
            ToastUtil.makeText(this, "无法发送验证码！", Toast.LENGTH_SHORT).show();
        }
        BaseTool.logPrint(TAG, "SMSonSuccess: " + code + "-----" + result);
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
        if ("@null".equals(result)) {
            ToastUtil.makeText(this, "连接超时！", Toast.LENGTH_SHORT).show();
        } else {
            ToastUtil.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
        //ToastUtil.makeText(this, "无法发送验证码！", Toast.LENGTH_SHORT).show();
        BaseTool.logPrint(TAG, "SMSonFailed: " + code + "-----" + result);
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
            if (!TextUtils.isEmpty(editTextWaiterCode.getText().toString())) {
                registerPostDataInfo.setCode(editTextWaiterCode.getText().toString());
            }
            BaseTool.logPrint(TAG, "checkSmsSuccess: ----" + String.valueOf(MD5Util.md5(editTextPwd.getText().toString().trim())));
            registerPrecenter.register(JSON.toJSONString(registerPostDataInfo));
        } else {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
//            ToastUtil.makeText(this, "验证码校验失败" + code, Toast.LENGTH_SHORT).show();
            ToastUtil.makeText(this, result, Toast.LENGTH_SHORT).show();
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
        if ( loadingDialog!=null&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "验证码校验失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickBaseDialogLeft(String iTag) {
        if (dianHuoTongBaseDialog != null) {
            dianHuoTongBaseDialog.dismiss();
        }
    }

    @Override
    public void onClickBaseDialogRight(String iTag) {
        dianHuoTongBaseDialog.dismiss();
        loadingDialog.show();
        registerPrecenter.checkSMS(editTextPhone.getText().toString().trim(), editTextPhoneCode.getText().toString().trim());
    }

    @Override
    public void getSaleManSuccess(int code, String result) {
        if (code == 200) {
            saleManInfo = JSON.parseObject(result, SaleManInfo.class);
            dianHuoTongBaseDialog = new DianHuoTongBaseDialog(this, this, "温馨提示", "您确定要让服务专员:" + saleManInfo.getName() + "为您服务吗？", "取消", "确定", "add");
            dianHuoTongBaseDialog.show();
        } else {
            if ( loadingDialog!=null&&loadingDialog.isShowing()){
                loadingDialog.dismiss();
            }
            ToastUtil.makeText(this, "没有查询到该服务专员哦~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSaleManFailed(int code, String result) {
        if ( loadingDialog!=null&&loadingDialog.isShowing()){
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "系统异常~", Toast.LENGTH_SHORT).show();
    }


//    测试代码
    //        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//// 获取状态栏高度
//        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//
//        BaseTool.logPrint("测试", "onCreate: "+resourceId+"=====+"+statusBarHeight);
    //         StatusBarUtil.setTranslucent(this);
//        StatusBarUtil.setColor(this, Color.parseColor("#04c1ab"));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
}
