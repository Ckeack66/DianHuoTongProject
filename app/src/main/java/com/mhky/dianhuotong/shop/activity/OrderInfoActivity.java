package com.mhky.dianhuotong.shop.activity;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.credential.credentialif.UploadCredentialIF;
import com.mhky.dianhuotong.credential.precenter.UploadCredentialPrecenter;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBottomMenuDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.person.custom.AvatarScanHelperDialog;
import com.mhky.dianhuotong.shop.adapter.OrderInfoAdapter;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.precenter.OrderInfoPresenter;
import com.mhky.dianhuotong.shop.precenter.UploadBuyersReturnOrdersPrecenter;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciver;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciverIF;
import com.mhky.dianhuotong.shop.shopif.OrderInfoIF;
import com.mhky.dianhuotong.shop.shopif.UploadBuyersReturnOrdersIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单详情Activity
 *
 */

public class OrderInfoActivity extends TakePhotoActivity implements OrderInfoIF, BanlanceReciverIF, DianHuoTongBottomMenuDialog.DianHuoTongBottomMenuDialogListener,
        UploadCredentialIF,UploadBuyersReturnOrdersIF {

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
    private TextView tv_shop_discount;
    private TextView tv_platform_discount;
    private LinearLayout ll_tracking_num;
    private LinearLayout ll_tracking_time;
    private TextView tv_tracking_num;
    private TextView tv_tracking_time;
    private ImageView iv_receipt;

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
    private AvatarScanHelperDialog avatarScanHelperDialog;
    private DianHuoTongBottomMenuDialog dianHuoTongBottomMenuDialog;
    private Uri uri = null;
    private File file;
    private UploadCredentialPrecenter uploadCredentialPrecenter;                            //借用一下上传图片而已
    private UploadBuyersReturnOrdersPrecenter uploadBuyersReturnOrdersPrecenter;            //回执单绑定订单
    private String imageUrl;//回执单URL
    private List<String> expressCodeList = new ArrayList<>();
    private List<String> expressCompanyList = new ArrayList<>();
    private String[] expressCodes = {"SF","HTKY","ZTO","STO","YTO","YD","YZPY","EMS","HHTT","JD","UC","DBL","ZJS","TNT","UPS","DHL","FEDEX","FEDEX_GJ"};
    private String[] expressCompany = {"顺丰速运","百世快递","中通快递","申通快递","圆通速递","韵达速递","邮政快递包裹", "EMS","天天快递","京东快递",
            "优速快递","德邦快递","宅急送","TNT快递","UPS","DHL","FEDEX联邦(国内件）","FEDEX联邦(国际件）"};

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
        tv_shop_discount = findViewById(R.id.tv_shop_discount);
        tv_platform_discount = findViewById(R.id.tv_platform_discount);
        ll_tracking_num = findViewById(R.id.order_info_tracking_num);
        ll_tracking_time = findViewById(R.id.order_info_transfer_ll);
        tv_tracking_num = findViewById(R.id.tv_tracking_num);
        tv_tracking_time = findViewById(R.id.tv_tracking_time);
        iv_receipt = findViewById(R.id.iv_receipt);
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
        expressCodeList = Arrays.asList(expressCodes);
        expressCompanyList = Arrays.asList(expressCompany);
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

        uploadCredentialPrecenter = new UploadCredentialPrecenter(this);
        orderInfoPresenter = new OrderInfoPresenter().setOrderInfoIF(this);
        uploadBuyersReturnOrdersPrecenter = new UploadBuyersReturnOrdersPrecenter(this);
        orderId = getIntent().getExtras().getString("order");
        if (!TextUtils.isEmpty(orderId)) {
            orderInfoPresenter.getOrderInfoByNumber(orderId);
        }

        dianHuoTongBottomMenuDialog = new DianHuoTongBottomMenuDialog(this, this);
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
            BaseTool.logPrint(TAG + "ck", Integer.valueOf(contentBean.getFreightInfo().getFreight().toString()) + "--" + (money * 100)
                    + (Integer.valueOf(contentBean.getFreightInfo().getFreight().toString()) > contentBean.getPayment()));
            if (contentBean.getFreightInfo() != null && contentBean.getFreightInfo().getFreight() != null
                    && Integer.valueOf(contentBean.getFreightInfo().getSendAccount().toString()) > (money * 100)) {
                double k = Double.valueOf(contentBean.getFreightInfo().getFreight().toString());
                textViewTransfer.setText("￥" + k / 100);
            }
            textViewPhone.setText("联系方式：" + contentBean.getAddressInfo().getPhone());
            textViewName.setText("收货人：" + contentBean.getBuyerInfo().getName());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(contentBean.getAddressInfo().getProvince());
            stringBuilder.append(contentBean.getAddressInfo().getCity());
            stringBuilder.append(contentBean.getAddressInfo().getArea());
            stringBuilder.append(contentBean.getAddressInfo().getDetailedAddress());
            textViewShopAdress.setText(stringBuilder.toString());
            double b = contentBean.getPayment();
            textViewAllMoney.setText("￥" + b / 100);
            textViewOrderNumber.setText(contentBean.getId());
            //设置支付宝或者微信交易单号：
            if (contentBean.getPaymentInfo() != null) {
                linearLayoutPaidNumber.setVisibility(View.VISIBLE);
                linearLayoutPaidTime.setVisibility(View.VISIBLE);
                if ("ALIPAY".equals(contentBean.getPaymentInfo().getPaymentType())) {
                    textViewPayNumber.setText("支付宝交易单号：" + contentBean.getPaymentInfo().getPaymentNo());
                } else if ("WEMPPAY".equals(contentBean.getPaymentInfo().getPaymentType())) {
                    textViewPayNumber.setText("微信交易单号：" + contentBean.getPaymentInfo().getPaymentNo());
                }
                Date date = new Date(Long.valueOf(contentBean.getPaymentInfo().getPayTime()));
                textViewPaidTime.setText(simpleDateFormat.format(date));
            }
            //设置优惠券、红包的金额
            if (contentBean.getPromotionInfos() != null && contentBean.getPromotionInfos().size() > 0) {
                float shop_discount = 0;
                float platForm_discount = 0;
                List<OrderBaseInfo.ContentBean.PromotionInfosBean> list = contentBean.getPromotionInfos();
                for (int i = 0; i < list.size(); i++) {
                    switch (list.get(i).getPromotionType()) {
                        case "PING_TAI_YOU_HUI_QUAN":
                            platForm_discount = platForm_discount + list.get(i).getRealCutPrice() / 100;
                            break;
                        case "DIAN_PU_YOU_HUI_QUAN":
                            shop_discount = shop_discount + list.get(i).getRealCutPrice() / 100;
                            break;
                    }
                }
                tv_shop_discount.setText("￥" + String.format("%.2f", shop_discount));
                tv_platform_discount.setText("￥" + String.format("%.2f", platForm_discount));
            }
//            Date date1 = new Date(Long.valueOf(contentBean.getCreateTime()));
//            textViewCreatTime.setText(simpleDateFormat.format(date1));
            textViewCreatTime.setText(contentBean.getCreateTime());
            if (!BaseTool.isEmpty(contentBean.getReceipt())){
                iv_receipt.setVisibility(View.VISIBLE);
                Picasso.get().load(contentBean.getReceipt()).into(iv_receipt);
                imageUrl = contentBean.getReceipt();
            }
            switch (contentBean.getStatus()) {
                case "ORDERED"://订单已提交  待付款
                    //ToastUtil.makeText(getActivity(), "待付款" + position, Toast.LENGTH_SHORT).show();
                    relativeLayout.setVisibility(View.VISIBLE);
                    textViewStates.setText("去支付");
                    break;
                case "PAID":
                    relativeLayout.setVisibility(View.GONE);
                    //ToastUtil.makeText(getActivity(), "已付款" + position, Toast.LENGTH_SHORT).show();
                    break;
                case "CONFIRMED"://确认
                    relativeLayout.setVisibility(View.GONE);
                    break;
                case "SHIPPED"://已发货
                    relativeLayout.setVisibility(View.VISIBLE);
                    textViewStates.setText("上传回执单");
                    //设置物流单号及其发货时间
                    ll_tracking_num.setVisibility(View.VISIBLE);
                    String s = "";
                    for (int i = 0; i < expressCodeList.size(); i++){
                        if (expressCodeList.get(i).equals(contentBean.getExpressCompany())){
                            s = expressCompanyList.get(i);
                            break;
                        }
                    }
                    tv_tracking_num.setText(s + "：" + (BaseTool.isEmpty(contentBean.getExpressNo().toString()) ? "" : contentBean.getExpressNo().toString()));
//                    tv_tracking_num.setText("物流单号:" + (BaseTool.isEmpty(contentBean.getExpressNo().toString()) ? "" : contentBean.getExpressNo().toString()));
                    List<OrderBaseInfo.ContentBean.OrderCirculationsBean> orderCirculationsBeanList = contentBean.getOrderCirculations();
                    for (int i = 0; i < orderCirculationsBeanList.size(); i++){
                        if(orderCirculationsBeanList.get(i).getOrderCirculationId().getOrderStatus().equals("SHIPPED")){
                            if (!BaseTool.isEmpty(orderCirculationsBeanList.get(i).getCreateTime())){
                                ll_tracking_time.setVisibility(View.VISIBLE);
                                tv_tracking_time.setText(orderCirculationsBeanList.get(i).getCreateTime());
                            }
                        }
                    }
                    break;
                case "COMPLETED"://已完成
                    relativeLayout.setVisibility(View.GONE);
                    //ToastUtil.makeText(getActivity(), "已完成" + position, Toast.LENGTH_SHORT).show();
                    //设置物流单号及其发货时间
                    ll_tracking_num.setVisibility(View.VISIBLE);
                    String s2 = "";
                    for (int i = 0; i < expressCodeList.size(); i++){
                        if (expressCodeList.get(i).equals(contentBean.getExpressCompany())){
                            s2 = expressCompanyList.get(i);
                            break;
                        }
                    }
                    tv_tracking_num.setText(s2 + "：" + (BaseTool.isEmpty(contentBean.getExpressNo().toString()) ? "" : contentBean.getExpressNo().toString()));
//                    tv_tracking_num.setText("物流单号:" + (BaseTool.isEmpty(contentBean.getExpressNo().toString()) ? "" : contentBean.getExpressNo().toString()));
                    List<OrderBaseInfo.ContentBean.OrderCirculationsBean> orderCirculationsBeanList2 = contentBean.getOrderCirculations();
                    for (int i = 0; i < orderCirculationsBeanList2.size(); i++){
                        if(orderCirculationsBeanList2.get(i).getOrderCirculationId().getOrderStatus().equals("SHIPPED")){
                            if (!BaseTool.isEmpty(orderCirculationsBeanList2.get(i).getCreateTime())){
                                ll_tracking_time.setVisibility(View.VISIBLE);
                                tv_tracking_time.setText(orderCirculationsBeanList2.get(i).getCreateTime());
                            }
                        }
                    }
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

    @OnClick(R.id.iv_receipt)
    public void onViewClicked() {
//        avatarScanHelperDialog = new AvatarScanHelperDialog(this, Uri.fromFile(file).toString());
        avatarScanHelperDialog = new AvatarScanHelperDialog(this, imageUrl);
        avatarScanHelperDialog.show();
    }

    @OnClick(R.id.order_ok_submit)
    void goPay() {
        try {
            switch (textViewStates.getText().toString()) {
                case "去支付":
                    Bundle bundle = new Bundle();
                    bundle.putString("order", contentBean.getId());
                    double a = contentBean.getPayment();
                    bundle.putString("money", String.valueOf(a / 100));
                    bundle.putInt("state", 1);
                    Intent intent = new Intent();
                    intent.setClass(this, BalanceActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent, 1020);
                    break;
                case "上传回执单":
                    //getTakePhoto().onPickFromGallery();
                    Permissions4M.get(this)
                            // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                            // .requestForce(true)
                            // 是否支持 5.0 权限申请，默认为 false
                            // .requestUnderM(false)
                            // 权限，单权限申请仅只能填入一个
                            .requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                            // 权限码
                            .requestCodes(1001)
                            // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                            // 返回的 intent 是跳转至**系统设置页面**
                            // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                            // 返回的 intent 是跳转至**手机管家页面**
                            // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                            .request();
                    break;
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
//        BaseTool.goActivityWithData(this, BalanceActivity.class, bundle);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionsGranted(1001)
    void granSuccess() {
        dianHuoTongBottomMenuDialog.show();
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

    /**
     * 拍照
     */
    @Override
    public void getCamera() {
        try {
            uri = BaseTool.createImagePathUri(mContext);
            TakePhoto takePhoto = getTakePhoto();
            CompressConfig compressConfig = CompressConfig.ofDefaultConfig();       //压缩参数
            compressConfig.setMaxSize(1000 * 1024);
            takePhoto.onEnableCompress(compressConfig, true);   //设置为需要压缩
            takePhoto.onPickFromCapture(uri);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    /**
     * 相册
     */
    @Override
    public void getPhotos() {
        try {
            TakePhoto takePhoto = getTakePhoto();
            CompressConfig compressConfig = CompressConfig.ofDefaultConfig();
            compressConfig.setMaxSize(1000 * 1024);
            takePhoto.onEnableCompress(compressConfig, true);
            takePhoto.onPickFromGallery();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    /**
     * 拍照或者获取相册图片成功
     *
     * @param result
     */
    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        try {
            file = new File(result.getImage().getCompressPath());
            HttpParams httpParams = new HttpParams();
            httpParams.put("userName", BaseApplication.getInstansApp().getPersonInfo().getUsername());
            httpParams.put("userId", BaseApplication.getInstansApp().getPersonInfo().getId());
            httpParams.put("type", "ORDER");
            httpParams.put("file", file);
            uploadCredentialPrecenter.getImageUplaodUrl(httpParams);
//            Picasso.get().load(new File(result.getImage().getCompressPath())).into(iv_receipt);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        } finally {
            dianHuoTongBottomMenuDialog.dismiss();
        }
    }

    /**
     * 上传图片获取url成功
     * @param code
     * @param result
     */
    @Override
    public void updataCredentialImageSucess(int code, String result) {
        BaseTool.logPrint(TAG + "ck" ,code + "----" + result);
        try {
            if (code == 201) {
                if (result != null && !"".equals(result)) {
                    iv_receipt.setVisibility(View.VISIBLE);
                    imageUrl = result;
                    Picasso.get().load(result).into(iv_receipt);
                    if (!BaseTool.isEmpty(orderId)){
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("receiptImgUrl",result);
                        uploadBuyersReturnOrdersPrecenter.uploadBuyersReturnOrders(orderId,httpParams);
                    }
                }
            } else {
                ToastUtil.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void updataCredentialImageFailed(int code, String result) {
        ToastUtil.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
    }

    /**
     * 上传回执单成功
     * @param code
     * @param result
     */
    @Override
    public void uploadBuyerReturnOrdersSuccess(int code, String result) {
        try{
            if (code == 202){
                ToastUtil.makeText(this, "上传成功", Toast.LENGTH_SHORT).show();
            }else {
                ToastUtil.makeText(this, "上传失败", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    @Override
    public void uploadBuyerReturnOrdersFailed(int code, String result) {

    }
}
