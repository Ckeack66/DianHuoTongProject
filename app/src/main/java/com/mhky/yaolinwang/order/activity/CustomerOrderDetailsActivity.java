package com.mhky.yaolinwang.order.activity;

import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseActivityCK;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.OrderInfoAdapter;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.precenter.OrderInfoPresenter;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciver;
import com.mhky.dianhuotong.shop.receiver.BanlanceReciverIF;
import com.mhky.yaolinwang.order.bean.CustomerOrderBean;
import com.mhky.yaolinwang.order.presenter.CustomerOrderDetailsPresenter;
import com.mhky.yaolinwang.order.view.CustomerOrderDetailsView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 订单详情
 */

public class CustomerOrderDetailsActivity extends BaseActivityCK implements CustomerOrderDetailsView,BanlanceReciverIF {

    @BindView(R.id.order_ok_title)
    DianHuoTongBaseTitleBar orderOkTitle;
    @BindView(R.id.order_ok_image)
    ImageView orderOkImage;
    @BindView(R.id.order_info_name)
    TextView orderInfoName;
    @BindView(R.id.order_info_phone)
    TextView orderInfoPhone;
    @BindView(R.id.order_info_change_address)
    TextView orderInfoChangeAddress;
    @BindView(R.id.order_info_address)
    TextView orderInfoAddress;
    @BindView(R.id.ll_order_address)
    LinearLayout llOrderAddress;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.order_ok_head_name)
    TextView orderOkHeadName;
    @BindView(R.id.order_ok_head_go)
    FrameLayout orderOkHeadGo;
    @BindView(R.id.order_info_go_shop)
    RelativeLayout orderInfoGoShop;
    @BindView(R.id.v1)
    View v1;
    @BindView(R.id.rv_customer_order)
    RecyclerView rvCustomerOrder;
    @BindView(R.id.order_ok_goods_money)
    TextView orderOkGoodsMoney;
    @BindView(R.id.rl_goods_amount)
    RelativeLayout rlGoodsAmount;
    @BindView(R.id.order_info_transfer)
    TextView orderInfoTransfer;
    @BindView(R.id.rl_freight)
    RelativeLayout rlFreight;
    @BindView(R.id.tv_shop_discount)
    TextView tvShopDiscount;
    @BindView(R.id.rl_shop_discounts)
    RelativeLayout rlShopDiscounts;
    @BindView(R.id.tv_platform_discount)
    TextView tvPlatformDiscount;
    @BindView(R.id.rl_platform_discounts)
    RelativeLayout rlPlatformDiscounts;
    @BindView(R.id.order_info_all_money)
    TextView orderInfoAllMoney;
    @BindView(R.id.rl_customer_order_amount)
    RelativeLayout rlCustomerOrderAmount;
    @BindView(R.id.order_info_number)
    TextView orderInfoNumber;
    @BindView(R.id.ll_customer_order_num)
    LinearLayout llCustomerOrderNum;
    @BindView(R.id.order_info_creattime)
    TextView orderInfoCreattime;
    @BindView(R.id.ll_order_info_creattime)
    LinearLayout llOrderInfoCreattime;
    @BindView(R.id.order_info_pay_number)
    TextView orderInfoPayNumber;
    @BindView(R.id.ll_order_info_pay_num)
    LinearLayout llOrderInfoPayNum;
    @BindView(R.id.order_info_pay_time)
    TextView orderInfoPayTime;
    @BindView(R.id.ll_order_info_pay_time)
    LinearLayout llOrderInfoPayTime;
    @BindView(R.id.tv_tracking_num)
    TextView tvTrackingNum;
    @BindView(R.id.ll_order_info_tracking_num)
    LinearLayout llOrderInfoTrackingNum;
    @BindView(R.id.tv_tracking_time)
    TextView tvTrackingTime;
    @BindView(R.id.ll_order_info_tracking_time)
    LinearLayout llOrderInfoTrackingTime;
    @BindView(R.id.order_ok_submit)
    TextView orderOkSubmit;
    @BindView(R.id.order_info_bottom)
    RelativeLayout orderInfoBottom;


    private List<BasePresenter> presenterList = new ArrayList<>();
    private CustomerOrderDetailsPresenter customerOrderDetailsPresenter;
    private CustomerOrderBean.ContentBean contentBean;
    private OrderInfoAdapter orderInfoAdapter;
    private String orderId;
    private OrderBaseInfo orderBaseInfo;
    private Context mContext;
    private BanlanceReciver banlanceReciver;
    private DateFormat simpleDateFormat;

    @Override
    public List<BasePresenter> getPresenter() {
        return presenterList;
    }

    @Override
    public void initPresenter() {
        customerOrderDetailsPresenter = new CustomerOrderDetailsPresenter();
        presenterList.add(customerOrderDetailsPresenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_order_details);
        ButterKnife.bind(this);

        init();
        initListener();
        initData();
    }

    private void init() {
        orderId = getIntent().getExtras().getString("order");
        banlanceReciver = new BanlanceReciver().setBanlanceReciverIF(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BaseApplication.wxAction);
        registerReceiver(banlanceReciver, intentFilter);

        orderOkTitle.setLeftImage(R.drawable.icon_back);
        orderOkTitle.setCenterTextView("订单详情");
    }

    private void initListener() {
        orderOkTitle.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initData() {
        if (!BaseTool.isEmpty(orderId)) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("orderId",orderId);
            customerOrderDetailsPresenter.getCustomerOrderDetails(httpParams);
        }
    }

    /**
     * 获取订单详情成功
     * @param data
     */
    @Override
    public void getCustomerOrderDetailsSuccess(String data) {

    }

    @Override
    public void getCustomerOrderDetailsFailed(String data) {

    }

    /**
     * 付款广播
     * @param code
     */
    @Override
    public void doBanlance(int code) {

    }
}
