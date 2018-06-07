package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.OrderInfoAdapter;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.ShopAdressInfo;
import com.mhky.dianhuotong.shop.precenter.OrderInfoPresenter;
import com.mhky.dianhuotong.shop.precenter.ShopAdressPresenter;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciver;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciverIF;
import com.mhky.dianhuotong.shop.shopif.OrderInfoIF;
import com.mhky.dianhuotong.shop.shopif.ShopAdressIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderInfoActivity extends BaseActivity implements OrderInfoIF, BanlanceReciverIF {
    @BindView(R.id.order_info_rv)
    RecyclerView recyclerView;
    @BindView(R.id.order_ok_head_name)
    TextView textViewShop;
    @BindView(R.id.order_ok_goods_money)
    TextView textViewGoodsMoney;
    @BindView(R.id.order_info_transfer)
    TextView textViewTransfer;
    @BindView(R.id.order_info_all_money)
    TextView textViewAllMoney;
    @BindView(R.id.order_info_number)
    TextView textViewOrderNumber;
    @BindView(R.id.order_info_pay_number)
    TextView textViewPayNumber;
    @BindView(R.id.order_info_creattime)
    TextView textViewCreatTime;
    @BindView(R.id.order_ok_submit)
    TextView textViewStates;
    @BindView(R.id.order_info_bottom)
    RelativeLayout relativeLayout;
    @BindView(R.id.order_info_pay_ll)
    LinearLayout linearLayoutPaidNumber;
    @BindView(R.id.order_info_paid_ll)
    LinearLayout linearLayoutPaidTime;
    @BindView(R.id.order_info_paid_time)
    TextView textViewPaidTime;
    @BindView(R.id.order_ok_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.order_info_adress)
    TextView textViewShopAdress;
    @BindView(R.id.order_info_name)
    TextView textViewName;
    @BindView(R.id.order_info_phone)
    TextView textViewPhone;
    private OrderBaseInfo.ContentBean contentBean;
    private OrderInfoAdapter orderInfoAdapter;
    private String orderId;
    private OrderInfoPresenter orderInfoPresenter;
    private OrderBaseInfo orderBaseInfo;
    private LoadingDialog loadingDialog;
    private Context mContext;
    private BanlanceReciver banlanceReciver;
    private DateFormat simpleDateFormat;
    private static final String TAG = "OrderInfoActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1020) {
//            loadingDialog = new LoadingDialog(this);
//            loadingDialog.show();
//            TimerTask timerTask = new TimerTask() {
//                @Override
//                public void run() {
//                    if (!TextUtils.isEmpty(orderId)) {
//                        orderInfoPresenter.getOrderInfoByNumber(orderId);
//                    }
//                }
//            };
//            Timer timer = new Timer();
//            timer.schedule(timerTask, 3000);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);
        mContext = this;
        ButterKnife.bind(this);
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(banlanceReciver);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void init() {
//        simpleDateFormat=DateFormat.getDateTimeInstance();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        banlanceReciver = new BanlanceReciver().setBanlanceReciverIF(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.wxAction);
        registerReceiver(banlanceReciver, intentFilter);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("订单详情");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        orderInfoPresenter = new OrderInfoPresenter().setOrderInfoIF(this);
        orderId = getIntent().getExtras().getString("order");
        if (!TextUtils.isEmpty(orderId)) {
            orderInfoPresenter.getOrderInfoByNumber(orderId);
        }
    }

    private void initView() {
        //contentBean = (OrderBaseInfo.ContentBean) getIntent().getExtras().getSerializable("order");
        if (contentBean != null) {
            textViewShop.setText(contentBean.getSellerInfo().getName());
            double money = 0;
            for (int a = 0; a < contentBean.getItems().size(); a++) {
                double i = contentBean.getItems().get(a).getRealPrice() * contentBean.getItems().get(a).getQuantity();
                money = money + i / 100;
            }
            textViewGoodsMoney.setText("￥" + money);
            if (contentBean.getFreightInfo().getFreight() != null && Integer.valueOf(contentBean.getFreightInfo().getFreight().toString()) != 0) {
                double k = Double.valueOf(contentBean.getFreightInfo().getFreight().toString());
                textViewTransfer.setText("￥" + k / 100);
            }
            textViewPhone.setText("联系方式："+contentBean.getAddressInfo().getPhone());
            textViewName.setText("收货人："+contentBean.getBuyerInfo().getName());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(contentBean.getAddressInfo().getProvince());
            stringBuilder.append(contentBean.getAddressInfo().getCity());
            stringBuilder.append(contentBean.getAddressInfo().getArea());
            stringBuilder.append(contentBean.getAddressInfo().getDetailedAddress());
            textViewShopAdress.setText(stringBuilder.toString());
            double b = contentBean.getPayment();
            textViewAllMoney.setText("￥" + b / 100);
            textViewOrderNumber.setText(contentBean.getId());
            if (contentBean.getPaymentInfo() != null) {
                linearLayoutPaidNumber.setVisibility(View.VISIBLE);
                linearLayoutPaidTime.setVisibility(View.VISIBLE);
                if ("ALIPAY".equals(contentBean.getPaymentInfo().getPaymentType())) {
                    textViewPayNumber.setText("支付宝交易号：" + contentBean.getPaymentInfo().getPaymentNo());
                } else if ("WECHATPAY".equals(contentBean.getPaymentInfo().getPaymentType())) {
                    textViewPayNumber.setText("微信交易号：" + contentBean.getPaymentInfo().getPaymentNo());
                }
                Date date = new Date(Long.valueOf(contentBean.getPaymentInfo().getPayTime()));
                textViewPaidTime.setText(simpleDateFormat.format(date));
            }
            Date date1 = new Date(Long.valueOf(contentBean.getCreateTime()));
            textViewCreatTime.setText(simpleDateFormat.format(date1));
            switch (contentBean.getStatus()) {
                case "ORDERED":
                    //ToastUtil.makeText(getActivity(), "待付款" + position, Toast.LENGTH_SHORT).show();
                    relativeLayout.setVisibility(View.VISIBLE);
                    textViewStates.setText("去支付");
                    break;
                case "PAID":
                    relativeLayout.setVisibility(View.GONE);
                    //ToastUtil.makeText(getActivity(), "已付款" + position, Toast.LENGTH_SHORT).show();
                    break;
                case "COMPLETED":
                    relativeLayout.setVisibility(View.GONE);
                    //ToastUtil.makeText(getActivity(), "已完成" + position, Toast.LENGTH_SHORT).show();
                    break;
                case "CANCELLED":
                    relativeLayout.setVisibility(View.GONE);
                    //ToastUtil.makeText(getActivity(), "已取消" + position, Toast.LENGTH_SHORT).show();
                    break;
            }
            BaseTool.logPrint(TAG, "init: -----" + contentBean.getItems().size());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            orderInfoAdapter = new OrderInfoAdapter(contentBean.getItems(), mContext);
            recyclerView.setAdapter(orderInfoAdapter);

        }

    }

    @OnClick(R.id.order_info_goshop)
    void goShop() {
        try {
            if (contentBean != null && contentBean.getSellerInfo() != null && !TextUtils.isEmpty(contentBean.getSellerInfo().getId())) {
                Bundle bundle = new Bundle();
                bundle.putString("shopid", contentBean.getSellerInfo().getId());
                BaseTool.goActivityWithData(this, ShopActivity.class, bundle);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @OnClick(R.id.order_ok_submit)
    void goPay() {
        try {
            Bundle bundle = new Bundle();
            bundle.putString("order", contentBean.getId());
            double a = contentBean.getPayment();
            bundle.putString("money", String.valueOf(a / 100));
            bundle.putInt("state", 1);
            Intent intent = new Intent();
            intent.setClass(this, BalanceActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, 1020);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

//        BaseTool.goActivityWithData(this, BalanceActivity.class, bundle);
    }

    @Override
    public void getOrderInfoSuccess(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (code == 200) {
            orderBaseInfo = JSON.parseObject(result, OrderBaseInfo.class);
            contentBean = orderBaseInfo.getContent().get(0);
            try {
                initView();
            } catch (Exception e) {
                PgyCrashManager.reportCaughtException(this, e);
            }

        }
    }

    @Override
    public void getOrderInfoFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void doBanlance(int code) {
        if (code == 0) {
            loadingDialog = new LoadingDialog(this);
            loadingDialog.show();
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    if (!TextUtils.isEmpty(orderId)) {
                        orderInfoPresenter.getOrderInfoByNumber(orderId);
                    }
                }
            };
            Timer timer = new Timer();
            timer.schedule(timerTask, 3000);
        } else if (code == 1) {
            ToastUtil.makeText(this, "取消支付", Toast.LENGTH_SHORT).show();
        } else if (code == -1) {
            ToastUtil.makeText(this, "支付失败", Toast.LENGTH_SHORT).show();
        }
    }
}
