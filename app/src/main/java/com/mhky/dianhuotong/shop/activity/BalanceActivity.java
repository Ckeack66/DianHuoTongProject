package com.mhky.dianhuotong.shop.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alipay.sdk.app.PayTask;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.pay.WXPayInfo;
import com.mhky.dianhuotong.pay.alipay.AuthResult;
import com.mhky.dianhuotong.pay.alipay.PayResult;
import com.mhky.dianhuotong.shop.bean.ShopAdressInfo;
import com.mhky.dianhuotong.shop.precenter.BanlancePresenter;
import com.mhky.dianhuotong.shop.precenter.ShopAdressPresenter;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciver;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciverIF;
import com.mhky.dianhuotong.shop.shopif.BanlanceIF;
import com.mhky.dianhuotong.shop.shopif.ShopAdressIF;
import com.mhky.dianhuotong.wxapi.Constants;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mhky.dianhuotong.wxapi.Constants.APP_ID;

/**
 * 选择支付方式界面
 */

public class BalanceActivity extends BaseActivity implements BanlanceIF, ShopAdressIF, BanlanceReciverIF {

    @BindView(R.id.balance_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.balance_pay11)
    CheckBox checkBox1;
    @BindView(R.id.balance_pay21)
    CheckBox checkBox2;
    @BindView(R.id.balance_pay31)
    CheckBox checkBox3;
    @BindView(R.id.balance_pay3_lilayout)
    LinearLayout linearLayout;
    @BindView(R.id.banlance_money)
    TextView textViewMoney;
    @BindView(R.id.balance_pay)
    TextView textViewPay;
    @BindView(R.id.balance_shop_adress)
    TextView textViewShopAdress;
    private String money;
    private int payType = -1;
    private BanlancePresenter banlancePresenter;
    String orderID = null;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private static final String TAG = "BalanceActivity";
    private ShopAdressPresenter shopAdressPresenter;
    private LoadingDialog loadingDialog;
    private int state;
    private BanlanceReciver banlanceReciver;
    private final IWXAPI msgApi = WXAPIFactory.createWXAPI(this, null);

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    BaseTool.logPrint(TAG, "handleMessage: ----" + resultStatus);
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(BalanceActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                        if (state == 1) {
                            setResult(1020);
                            Intent intent = new Intent();
                            intent.putExtra("result", 0);
                            intent.setAction(BaseApplication.wxAction);
                            sendBroadcast(intent);
                        }
                        finish();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(BalanceActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(BalanceActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(BalanceActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        }

        ;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        msgApi.registerApp(APP_ID);
//        msgApi.registerApp("wx7df0bb99c5bf9af5");
        try {
            init();
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(banlanceReciver);
    }

    private void init() {
        banlanceReciver = new BanlanceReciver().setBanlanceReciverIF(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.wxAction);
        registerReceiver(banlanceReciver, intentFilter);
        loadingDialog = new LoadingDialog(this);
        shopAdressPresenter = new ShopAdressPresenter(this);
        shopAdressPresenter.getShopAdress();
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("结算");
        Bundle bundle = getIntent().getExtras();
        orderID = bundle.getString("order");
        money = bundle.getString("money");
        state = bundle.getInt("state");
        textViewMoney.setText(money + "元");
        banlancePresenter = new BanlancePresenter(this);
        //ToastUtil.makeText(this, goodsId, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.balance_pay1)
    void choosePay1() {
        setChoosePayState(checkBox1);
        payType = 1;
    }

    @OnClick(R.id.balance_pay2)
    void choosePay2() {
        setChoosePayState(checkBox2);
        payType = 2;
    }

    @OnClick(R.id.balance_pay3)
    void choosePay3() {
        setChoosePayState(checkBox3);
        //linearLayout.setVisibility(View.VISIBLE);
        payType = 3;
    }

    @OnClick(R.id.balance_pay)
    void doPay() {
        if (payType == -1) {
            ToastUtil.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
            return;
        } else if (payType == 1) {
            HashMap hashMap = new HashMap();
            hashMap.put("orderIds", orderID);
            hashMap.put("paymentType", "ALIPAY");
            banlancePresenter.getPayID(hashMap);
            loadingDialog.show();
            // ToastUtil.makeText(this, "支付宝结账中...请等待", Toast.LENGTH_SHORT).show();
        } else if (payType == 2) {
//            ToastUtil.makeText(this, "微信支付暂未开通", Toast.LENGTH_SHORT).show();
            HashMap hashMap = new HashMap();
            hashMap.put("orderIds", orderID);
            hashMap.put("paymentType", "WECHATPAY");
            banlancePresenter.getPayID(hashMap);
            loadingDialog.show();
            //ToastUtil.makeText(this, "微信结账中...请等待", Toast.LENGTH_SHORT).show();
        } else if (payType == 3) {
            ToastUtil.makeText(this, "线下支付暂未开通", Toast.LENGTH_SHORT).show();
        }

    }

    private void setChoosePayState(CheckBox choosePayState) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        linearLayout.setVisibility(View.GONE);
        choosePayState.setChecked(true);
        BaseTool.logPrint(TAG, "setChoosePayState: " + payType);

    }

    @Override
    public void doBanlanceSucess(int code, String result) {

    }

    @Override
    public void doBanlanceFaild(int code, String result) {

    }

    @Override
    public void getPayCodeSucess(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        try {
            final String orderInfo = result;
            if (code == 200) {
                if (payType == 1) {
                    if (orderInfo != null) {
                        Runnable payRunnable = new Runnable() {
                            @Override
                            public void run() {

                                PayTask alipay = new PayTask(BalanceActivity.this);
                                Map<String, String> result = alipay.payV2(orderInfo, true);
                                Log.i("msp", result.toString());
                                Message msg = new Message();
                                msg.what = SDK_PAY_FLAG;
                                msg.obj = result;
                                mHandler.sendMessage(msg);
                            }

                        };
                        Thread payThread = new Thread(payRunnable);
                        payThread.start();
                    }

                } else if (payType == 2) {
                    // ToastUtil.makeText(this, "微信结账中...请等待", Toast.LENGTH_SHORT).show();
                    final WXPayInfo wxPayInfo = JSON.parseObject(result, WXPayInfo.class);
                    if (wxPayInfo != null) {
                        Runnable payRunnable1 = new Runnable() {
                            @Override
                            public void run() {
                                PayReq req = new PayReq();
                                req.appId = wxPayInfo.getAppid();
                                req.partnerId = Constants.PARTNER_ID;
                                req.prepayId = wxPayInfo.getPrepay_id();
                                req.nonceStr = wxPayInfo.getNonce_str();
                                req.timeStamp = wxPayInfo.getTimestamp();
                                req.packageValue = "Sign=WXPay";
                                req.sign = wxPayInfo.getSign();
                                BaseTool.logPrint("sign", req.sign  + "------" + req.appId);
                                msgApi.sendReq(req);
                            }
                        };
                        Thread payThread1 = new Thread(payRunnable1);
                        payThread1.start();
                    }


                } else if (payType == 3) {
                    ToastUtil.makeText(this, "此订单将进行线下结账...请仔细核对商家信息", Toast.LENGTH_SHORT).show();
                }
            } else {
                ToastUtil.makeText(this, "获取支付信息失败！", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getPayCodeFaild(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }


    @Override
    public void getShopAdressSuccess(int code, String result) {
        try {
            if (code == 200) {
                ShopAdressInfo shopAdressInfo = JSON.parseObject(result, ShopAdressInfo.class);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(shopAdressInfo.getAddress().getProvince());
                stringBuilder.append(shopAdressInfo.getAddress().getCity());
                stringBuilder.append(shopAdressInfo.getAddress().getDistrict());
                stringBuilder.append(shopAdressInfo.getAddress().getTown());
                stringBuilder.append(shopAdressInfo.getAddress().getRoad());
                textViewShopAdress.setText(stringBuilder.toString());
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void getShopAdressFailed(int code, String result) {

    }

    @Override
    public void doBanlance(int code) {
        if (code == 0) {
            ToastUtil.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
            finish();
        } else if (code == 1) {
            ToastUtil.makeText(this, "取消支付", Toast.LENGTH_LONG).show();
        } else if (code == -1) {
            ToastUtil.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
        }
    }
}
