package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.OrderOkAdapter;
import com.mhky.dianhuotong.shop.bean.CartBaseInfo;
import com.mhky.dianhuotong.shop.bean.FrigthInfo;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkBotttomInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkCenterInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkTitleInfo;
import com.mhky.dianhuotong.shop.precenter.BanlancePresenter;
import com.mhky.dianhuotong.shop.precenter.OrderOkPresenter;
import com.mhky.dianhuotong.shop.shopif.BanlanceIF;
import com.mhky.dianhuotong.shop.shopif.OrderOkIF;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OderOkActivity extends BaseActivity implements OrderOkAdapter.GetEditWordsListenner, OrderOkIF, BanlanceIF {
    @BindView(R.id.order_ok_rcv)
    RecyclerView recyclerView;
    @BindView(R.id.order_ok_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.order_ok_money)
    TextView textViewMoney;
    private OrderOkAdapter orderOkAdapter;
    private List<OrderOkInfo> orderOkInfoList;
    private HashMap<String, List<CartBaseInfo.GoodsItemsBean>> hashMapInteger;
    private Context mContext;
    private String baseData;
    private String shopIDs;
    private String goodsIDs;
    private Bundle bundle;
    private double devYh = 0;//平台优惠
    private double allMoney = 0;//总价
    private double allMoney1 = 0;//总价不含运费
    private double shopYh=0;//店铺优惠
    private double shopFright=0;
    private OrderOkPresenter orderOkPresenter;
    private static final String TAG = "OderOkActivity";
    private BanlancePresenter banlancePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_ok);
        mContext = this;
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        banlancePresenter = new BanlancePresenter(this);
        orderOkPresenter = new OrderOkPresenter(this);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("确认订单");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            baseData = bundle.getString("basedata");
            shopIDs = bundle.getString("sids");
            orderOkInfoList = new ArrayList<>();
            hashMapInteger = new HashMap<>();
//            hashMapInteger = (HashMap<String, List<CartBaseInfo.GoodsItemsBean>>) JSON.parseObject(baseData, Map.class);
            hashMapInteger = (HashMap<String, List<CartBaseInfo.GoodsItemsBean>>) bundle.getSerializable("data");
            Log.d(TAG, "inIt: ------" + orderOkInfoList.size());
            Log.d(TAG, "inIt: -----map" + hashMapInteger.size());
            if (shopIDs != null) {
                orderOkPresenter.getFright(shopIDs);
            }
        }

    }

    @Override
    public void getEditData(String s, int position) {
        if (orderOkAdapter != null) {
            orderOkInfoList.get(position).getOrderOkBotttomInfo().setWords(s);
        }
    }

    @Override
    public void getOrderFrightSucess(int code, String result) {
        if (code == 200) {
            List<FrigthInfo> list = JSON.parseArray(result, FrigthInfo.class);
            for (int a = 0; a < list.size(); a++) {
                if (hashMapInteger.containsKey(list.get(a).getCompanyId().toString())) {
                    Log.d(TAG, "getOrderFrightSucess: ----asdffff");
                    List<CartBaseInfo.GoodsItemsBean> list1 = hashMapInteger.get(list.get(a).getCompanyId().toString());
                    list1.get(0).setFrigthInfo(list.get(a));
                    hashMapInteger.put(list.get(a).getCompanyId().toString(), list1);
//                    List<CartBaseInfo.GoodsItemsBean> list1=hashMapInteger.get(list.get(a).getCompanyId());
//                    list1.get(0).setFrigthInfo(list.get(a));
//                    hashMapInteger.put(list.get(a).getCompanyId(),list1);
                }
            }
            sumData();
        }
    }

    private void sumData() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>>> iter = hashMapInteger.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>> entry = iter.next();
                String key = entry.getKey();
                Log.d(TAG, "doBanlance: key" + key);
                OrderOkInfo orderOkInfo = new OrderOkInfo();
                orderOkInfo.setType(OrderOkInfo.TOP);
                OrderOkTitleInfo orderOkTitleInfo = new OrderOkTitleInfo();
                Log.d(TAG, "sumData: ------" + entry.getValue().toString());
                List<CartBaseInfo.GoodsItemsBean> list = entry.getValue();
                orderOkTitleInfo.setShopDTOBean(list.get(0).getShopDTO());
                orderOkInfo.setOrderOkTitleInfo(orderOkTitleInfo);
                orderOkInfoList.add(orderOkInfo);
                int money = 0;
                for (int a = 0; a < list.size(); a++) {
                    Log.d(TAG, "doBanlance: ----goodsID" + list.get(a).getGoodsId());
                    stringBuilder.append(list.get(a).getSkuDTO().getId() + ",");
                    OrderOkInfo orderOkInfo1 = new OrderOkInfo();
                    orderOkInfo1.setType(OrderOkInfo.CENTER);
                    OrderOkCenterInfo orderOkCenterInfo = new OrderOkCenterInfo();
                    orderOkCenterInfo.setGoodsItemsBean(list.get(a));
                    orderOkInfo1.setOrderOkCenterInfo(orderOkCenterInfo);
                    orderOkInfoList.add(orderOkInfo1);
                    money = money + (list.get(a).getAmount() * list.get(a).getInPrice());
                }
                OrderOkInfo orderOkInfo2 = new OrderOkInfo();
                orderOkInfo2.setType(OrderOkInfo.BOTTOM);
                OrderOkBotttomInfo orderOkBotttomInfo = new OrderOkBotttomInfo();
                orderOkBotttomInfo.setMoney(money);
                orderOkBotttomInfo.setParentId(key);
                orderOkBotttomInfo.setFrigthInfo(list.get(0).getFrigthInfo());
                orderOkBotttomInfo.setGoodsNumber(String.valueOf(list.size()));
                orderOkInfo2.setOrderOkBotttomInfo(orderOkBotttomInfo);
                orderOkInfoList.add(orderOkInfo2);
            }
            goodsIDs = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
            if (orderOkInfoList != null) {
                orderOkAdapter = new OrderOkAdapter(orderOkInfoList, this, this);
                orderOkAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        ToastUtil.makeText(mContext, "点击了-" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(orderOkAdapter);
            }
            sumMoney();
        } catch (Exception e) {
            Log.d(TAG, "sumData: ---" + e);
        }

    }

    @OnClick(R.id.order_ok_submit)
    void sumOrder() {
        HashMap<String, String> m = new HashMap();
        m.put("skuIds", goodsIDs);
        banlancePresenter.doBanlance(m);
    }

    private void sumMoney() {
        Iterator<Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>>> iter = hashMapInteger.entrySet().iterator();
        allMoney = 0;
        while (iter.hasNext()) {
            Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>> entry = iter.next();
            String key = entry.getKey();
            Log.d(TAG, "doBanlance: key" + key);
            List<CartBaseInfo.GoodsItemsBean> list = entry.getValue();
            double money = 0;
            for (int a = 0; a < list.size(); a++) {
                money = money + (list.get(a).getAmount() * list.get(a).getInPrice());
            }
            allMoney = allMoney + money;
        }
        sumYh();
    }
    private void sumYh(){
        shopYh=0;
        shopFright=0;
        for (int a=0;a<orderOkInfoList.size();a++){
            if (orderOkInfoList.get(a).getItemType()==3&&orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo()!=null&&orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo().getSendAccount()!=null){
                double b = (double) orderOkInfoList.get(a).getOrderOkBotttomInfo().getMoney();
                double money1 = b / 100;
                if (money1 <Double.valueOf(orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo().getSendAccount().toString())){
                    shopFright=shopFright+Double.valueOf(orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo().getFreight().toString());
                }
            }
            if (orderOkInfoList.get(a).getItemType()==3&&orderOkInfoList.get(a).getOrderOkBotttomInfo().getCouponInfo()!=null){
                shopYh=shopYh+orderOkInfoList.get(a).getOrderOkBotttomInfo().getCouponInfo().getPromotionItem().getGradientFullCut().getCutPrice();
            }

        }
        allMoney1=allMoney/100+shopFright;
        textViewMoney.setText("￥"+allMoney1);
    }

    @Override
    public void getOrderFrightFaild(int code, String result) {

    }

    @Override
    public void doBanlanceSucess(int code, String result) {
        if (code == 201) {
            ToastUtil.makeText(this, "订单提交成功！", Toast.LENGTH_SHORT).show();
            BaseApplication.getInstansApp().setUpdateCart(true);
            StringBuffer stringBuffer = new StringBuffer();
            // OrderBaseInfo orderBaseInfo = JSON.parseObject(result, OrderBaseInfo.class);
            List<OrderBaseInfo.ContentBean> contentBeanList = JSON.parseArray(result, OrderBaseInfo.ContentBean.class);
            if (contentBeanList.size() == 1) {
                stringBuffer.append(contentBeanList.get(0).getId());
            } else {
                for (int a = 0; a < contentBeanList.size(); a++) {
                    stringBuffer.append(contentBeanList.get(a).getId());
                    if (a != contentBeanList.size() - 1) {
                        stringBuffer.append(",");
                    }
                }
            }
            String orderIDs = stringBuffer.toString();
//            HashMap hashMap = new HashMap();
//            hashMap.put("orderIds", orderID);
//            hashMap.put("paymentType", "ALIPAY");
//            banlancePresenter.getPayID(hashMap);
            Bundle bundle=new Bundle();
            bundle.putString("order",orderIDs);
            bundle.putString("money",String.valueOf(allMoney1));
            BaseTool.goActivityWithData(this,BalanceActivity.class,bundle);
            finish();
        }
    }

    @Override
    public void doBanlanceFaild(int code, String result) {

    }

    @Override
    public void getPayCodeSucess(int code, String result) {

    }

    @Override
    public void getPayCodeFaild(int code, String result) {

    }
}
