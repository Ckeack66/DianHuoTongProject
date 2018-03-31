package com.ymky.dianhuotong.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mingle.widget.ShapeLoadingDialog;
import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.tool.TimerMessage;
import com.ymky.dianhuotong.custom.viewgroup.DiaHuiTongBaseTitleBar;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity implements TimerMessage.OnTimerListener {
    @BindView(R.id.register_titlebar)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.register_getmessage)
    TextView txtMessage;
    private TimerMessage timerMessage;
    private boolean isTimerStart;
    private ShapeLoadingDialog shapeLoadingDialog;
    private static final String TAG = "RegisterActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        inIt();

//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//// 获取状态栏高度
//        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//
//        Log.d("测试", "onCreate: "+resourceId+"=====+"+statusBarHeight);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timerMessage != null && isTimerStart) {
            timerMessage.cancel();
            messageButtonOk();
        }
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView("注册");
        isTimerStart = false;
        timerMessage = new TimerMessage(60000, 1000, this);
        shapeLoadingDialog = new ShapeLoadingDialog.Builder(this).loadText("正在登陆...").build();
        shapeLoadingDialog.getWindow().setDimAmount(0);
//         StatusBarUtil.setTranslucent(this);
//        StatusBarUtil.setColor(this, Color.parseColor("#04c1ab"));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
    }

    @OnClick(R.id.register_getmessage)
    void sendMessage() {
        timerMessage.start();
        isTimerStart = true;
        messageButtonNo();
    }

    @OnClick(R.id.register_register_button)
    void register() {
        if (shapeLoadingDialog != null) {
            shapeLoadingDialog.show();
        }


        Map<String,String> params=new HashMap<String,String>();
        params.put("userDto", "{\n" +
                "  \"authorities\": [\n" +
                "    \"1\"\n" +
                "  ],\n" +
                "  \"enabled\": true,\n" +
                "  \"mobile\": \"13212341111\",\n" +
                "  \"password\": \"123456\",\n" +
                "  \"username\": \"123456\"\n" +
                "}");
        JSONObject jsonObject=new JSONObject(params);
        OkGo.<String>post("http://192.168.2.158:9088/user").upJson(jsonObject).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: "+response.code()+"-----"+response.body()+"----"+response.message());
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: "+response.code()+"-----"+response.body()+"----"+response.message());
            }

            @Override
            public void onError(Response<String> response) {
                Log.d(TAG, "onSuccess: "+response.code()+"-----"+response.body()+"----"+response.message());
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                return null;
            }
        });
    }

    @Override
    public void onTick(String time) {
        txtMessage.setText(time);
    }

    @Override
    public void onFinish(String fi) {
        txtMessage.setText(fi);
        messageButtonOk();
    }


    private void messageButtonOk() {
        txtMessage.setClickable(true);
        txtMessage.setTextColor(getResources().getColor(R.color.color04c1ab));
        txtMessage.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode));
    }

    private void messageButtonNo() {
        txtMessage.setTextColor(getResources().getColor(R.color.colorBFBFBF));
        txtMessage.setBackground(getResources().getDrawable(R.drawable.shape_register_getcode_grey));
        txtMessage.setClickable(false);
    }
}
