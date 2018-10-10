package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.OrderOkAdapter;
import com.mhky.dianhuotong.shop.bean.CartBaseInfo;
import com.mhky.dianhuotong.shop.bean.CouponInfo;
import com.mhky.dianhuotong.shop.bean.FrigthInfo;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkBotttomInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkCenterInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkNewInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkTitleInfo;
import com.mhky.dianhuotong.shop.bean.ShopAdressInfo;
import com.mhky.dianhuotong.shop.custom.CouponDialog;
import com.mhky.dianhuotong.shop.precenter.BanlancePresenter;
import com.mhky.dianhuotong.shop.precenter.CouponPresenter;
import com.mhky.dianhuotong.shop.precenter.OrderOkPresenter;
import com.mhky.dianhuotong.shop.precenter.ShopAdressPresenter;
import com.mhky.dianhuotong.shop.shopif.BanlanceIF;
import com.mhky.dianhuotong.shop.shopif.CounponGetIF;
import com.mhky.dianhuotong.shop.shopif.OrderOkIF;
import com.mhky.dianhuotong.shop.shopif.ShopAdressIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 确认订单activity
 *
 * 初始化→获取运费实体类→获取优惠券实体类→获取整体list的数据→设置适配器及其部分点击事件
 * →算出仅仅是商品总的价钱allMoney→算出合计的总价 = allmoney + 总运费（shopFright）- 店铺的优惠（shopYh） - 平台的优惠
 */

public class OderOkActivity extends BaseActivity implements OrderOkAdapter.GetEditWordsListenner, OrderOkIF, BanlanceIF, ShopAdressIF, CounponGetIF {

    @BindView(R.id.order_ok_rcv)
    RecyclerView recyclerView;
    @BindView(R.id.order_ok_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.order_ok_money)
    TextView textViewMoney;
    @BindView(R.id.order_ok_adress)
    TextView textViewAdress;
    @BindView(R.id.order_ok_name)
    TextView textViewName;
    @BindView(R.id.order_ok_phone)
    TextView textViewPhone;
    @BindView(R.id.order_ok_bto_yh)
    TextView textViewAll;

    private OrderOkAdapter orderOkAdapter;
    private List<OrderOkInfo> orderOkInfoList;                                   //被加载的数据集合（头部+center+底部）
    private HashMap<String, List<CartBaseInfo.GoodsItemsBean>> hashMapInteger;   //被选中的商品的map集合（key值为上游B公司id，value为商品实体类）
    private Context mContext;
    private String baseData;                                                    //  JSON.toJSONString(hashMapInteger)
    private String shopIDs;                                                     //上游B id 拼接字符串    中间用“，”隔开
    private String goodsIDs;                                                    //结算商品的id，用“，”拼接
    private Bundle bundle;
    private double allMoney = 0;//仅仅是所有商品的总价
    private double allMoney1 = 0;//合计的总价（商品总价+总运费-店铺优惠-平台优惠）
    private double shopYh = 0;//所有的店铺优惠
    private double shopFright = 0;//所有的运费
    private OrderOkPresenter orderOkPresenter;
    private BanlancePresenter banlancePresenter;
    private ShopAdressPresenter shopAdressPresenter;
    private LoadingDialog loadingDialog;
    private CouponPresenter couponPresenter;
    private List<CouponInfo> couponInfoList;                                        //可用的优惠券信息列表
    private List<CouponInfo> couponInfoListPT;                                      //平台的优惠券list
    private CouponInfo couponInfo;                                                  //选中的平台优惠券实体类
    private StringBuilder stringBuilder1;                                           //店铺优惠券的id的拼接，中间用“，”拼接
    private Map<String, String> hashMapRemark;                                      //设置留言的hashMap     key值为上游B  id      value值为留言情况
    private static final String TAG = "OderOkActivity";
    private static final String TERMINAL1 = "FULL_SITE";
    private static final String TERMINAL2 = "MOBILE";
    private int totle_price = 0;                                                    //测试用，暂时无用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_ok);
        mContext = this;
        ButterKnife.bind(this);
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    /**
     * 初始化控件
     */
    private void inIt() {
        stringBuilder1 = new StringBuilder();
        couponInfoListPT = new ArrayList<>();
        hashMapRemark = new HashMap<>();
        loadingDialog = new LoadingDialog(this);

        shopAdressPresenter = new ShopAdressPresenter(this);
        shopAdressPresenter.getShopAdress();
        couponPresenter = new CouponPresenter().setCounponGetIF(this);
        banlancePresenter = new BanlancePresenter(this);
        orderOkPresenter = new OrderOkPresenter(this);

        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getShopName() != null) {
            textViewName.setText("收货人：" + BaseApplication.getInstansApp().getPersonInfo().getShopName().toString());
        }
        textViewPhone.setText("联系方式：" + BaseApplication.getInstansApp().getPersonInfo().getMobile());

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
        //使NestedScrolling与recyclerview的滑动冲突解决
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
            BaseTool.logPrint(TAG, "inIt: ------" + orderOkInfoList.size());
            BaseTool.logPrint(TAG, "inIt: -----map" + hashMapInteger.size());
            if (shopIDs != null) {
                orderOkPresenter.getFright(shopIDs);
            }
        }

    }

    //监听留言框，根据position向orderOkInfoList指定item设定words；
    @Override
    public void getEditData(String s, int position) {
        if (orderOkAdapter != null) {
            orderOkInfoList.get(position).getOrderOkBotttomInfo().setWords(s);
        }
    }

    /**
     * 批量获取快递费成功
     * @param code
     * @param result
     */
    @Override
    public void getOrderFrightSucess(int code, String result) {
        try {
            if (code == 200) {
                List<FrigthInfo> list = JSON.parseArray(result, FrigthInfo.class);
                for (int a = 0; a < list.size(); a++) {
                    if (hashMapInteger.containsKey(list.get(a).getCompanyId().toString())) {
                        BaseTool.logPrint(TAG, "getOrderFrightSucess: ----asdffff");
                        List<CartBaseInfo.GoodsItemsBean> list1 = hashMapInteger.get(list.get(a).getCompanyId().toString());
                        list1.get(0).setFrigthInfo(list.get(a));
                        //添加该上游B公司的运费后，更改hasMapInteneger的值
                        hashMapInteger.put(list.get(a).getCompanyId().toString(), list1);
                    }
                }
                //请求优惠券信息
                couponPresenter.getCoupon();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getOrderFrightFaild(int code, String result) {

    }

    /**
     * 初始化主题list的数据，并设置适配器
     */
    private void sumData() {
        totle_price = 0;
        try {

            StringBuilder stringBuilder = new StringBuilder();                      //商品id拼接字符串，中间用“，”拼接
            Iterator<Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>>> iter = hashMapInteger.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>> entry = iter.next();
                String key = entry.getKey();
                BaseTool.logPrint(TAG, "doBanlance: key" + key);

                //先设定头部数据
                OrderOkInfo orderOkInfo = new OrderOkInfo();
                orderOkInfo.setType(OrderOkInfo.TOP);
                OrderOkTitleInfo orderOkTitleInfo = new OrderOkTitleInfo();
                BaseTool.logPrint(TAG, "sumData: ------" + entry.getValue().toString());
                List<CartBaseInfo.GoodsItemsBean> list = entry.getValue();
                orderOkTitleInfo.setShopDTOBean(list.get(0).getShopDTO());
                orderOkInfo.setOrderOkTitleInfo(orderOkTitleInfo);
                orderOkInfoList.add(orderOkInfo);

                //设定Center部数据，并计算出当前一家上游B所包含的订购商品的money
                int money = 0;
                for (int a = 0; a < list.size(); a++) {
                    BaseTool.logPrint(TAG, "doBanlance: ----goodsID" + list.get(a).getGoodsId());
                    stringBuilder.append(list.get(a).getSkuDTO().getId() + ",");
                    OrderOkInfo orderOkInfo1 = new OrderOkInfo();
                    orderOkInfo1.setType(OrderOkInfo.CENTER);
                    OrderOkCenterInfo orderOkCenterInfo = new OrderOkCenterInfo();
                    orderOkCenterInfo.setGoodsItemsBean(list.get(a));
                    orderOkInfo1.setOrderOkCenterInfo(orderOkCenterInfo);
                    orderOkInfoList.add(orderOkInfo1);
                    if (list.get(a).getAmount() >= list.get(a).getSkuDTO().getBatchNums()) {
                        money = money + (list.get(a).getAmount() * list.get(a).getSkuDTO().getWholesalePrice());
                        totle_price = totle_price + (list.get(a).getAmount() * list.get(a).getSkuDTO().getWholesalePrice());
                    } else {
                        money = money + (list.get(a).getAmount() * list.get(a).getSkuDTO().getRetailPrice());
                        totle_price = totle_price + (list.get(a).getAmount() * list.get(a).getSkuDTO().getRetailPrice());
                    }
                    BaseTool.logPrint(TAG, "ck:money =" + money  + ";totle =" + totle_price);
                }


                //设定底部数据
                OrderOkInfo orderOkInfo2 = new OrderOkInfo();
                orderOkInfo2.setType(OrderOkInfo.BOTTOM);
                OrderOkBotttomInfo orderOkBotttomInfo = new OrderOkBotttomInfo();
                orderOkBotttomInfo.setMoney(money);
                orderOkBotttomInfo.setParentId(key);
                orderOkBotttomInfo.setShopDTOBean(list.get(0).getShopDTO());
                orderOkBotttomInfo.setFrigthInfo(list.get(0).getFrigthInfo());//本界面先获取的运费实体类，并更新到  hashMapInteger  了
                orderOkBotttomInfo.setGoodsNumber(String.valueOf(list.size()));

                List<CouponInfo> couponInfoList1 = new ArrayList<>();
                if (couponInfoList != null) {
                    for (int a = 0; a < couponInfoList.size(); a++) {
                        String goodsChannel = couponInfoList.get(a).getPromotionItem().getGoodsChannel();
                        if(goodsChannel.equals(TERMINAL1) || goodsChannel.equals(TERMINAL2)){          //手机可用的优惠券
                            switch (couponInfoList.get(a).getPromotionItem().getPromotionType()){
                                case "SHANG_PIN_MAN_LI_JIAN":                           //商品满立减（满立减的不用领取优惠券）

                                    break;
                                case "DIAN_PU_MAN_LI_JIAN":                             //店铺满立减（满立减的不用领取优惠券）

                                    break;
                                case "XIAN_SHI_XIAN_LIANG_ZHE_KOU":                     //限时限量折扣（不用领取优惠券）

                                    break;
//                                case "PING_TAI_YOU_HUI_QUAN":                           //平台优惠券（需要去领取，平台的优惠券添加到平台红包里面，不添加到店铺优惠券里面）
//                                    if(totle_price >= couponInfoList.get(a).getPromotionItem().getGradientFullCut().getFullAmount()){
//                                        couponInfoList1.add(couponInfoList.get(a));
//                                    }
//                                    break;
                                case "DIAN_PU_YOU_HUI_QUAN":                           //店铺优惠券（需要去领取）

                                    break;
                                case "XIAN_LIANG_ZHE_KOU":                             //限量折扣（不用领取优惠券）

                                    break;
                                case "SHANG_PIN_MAN_ZHE_KOU":                          //商品满折扣（不用领取优惠券）

                                    break;
//                                case "PING_TAI_HUO_DONG":                              //平台活动（不用领取优惠券，平台的优惠券添加到平台红包里面，不添加到店铺优惠券里面）
//
//                                    break;
                            }

                            /**
                             * 这个地方的逻辑需要等着增加新的优惠券之后再仔细检查
                             */
                            if (key.equals(couponInfoList.get(a).getCompanyId())) {
                                if (couponInfoList.get(a).getPromotionItem().getGradientFullCut().getFullAmount() <= money) {
                                    couponInfoList1.add(couponInfoList.get(a));
                                }
                            }
                        }
                    }
                }
                orderOkBotttomInfo.setCouponInfoList(couponInfoList1);
                orderOkInfo2.setOrderOkBotttomInfo(orderOkBotttomInfo);
                orderOkInfoList.add(orderOkInfo2);
            }

            goodsIDs = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
            BaseTool.logPrint(TAG, "sumData: ----goods" + goodsIDs);
            if (orderOkInfoList != null) {
                orderOkAdapter = new OrderOkAdapter(orderOkInfoList, this, this);
                orderOkAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.order_ok_bto_select:      //请选择优惠券
                                CouponDialog couponDialog = new CouponDialog(mContext, new CouponDialog.CredentialBaseDialogListener() {
                                    @Override
                                    public void OnClickCredentialBaseDialogListviewItem(CouponInfo couponInfo, String tag) {
                                        if (couponInfo != null) {
                                            orderOkInfoList.get(Integer.valueOf(tag)).getOrderOkBotttomInfo().setCouponInfo(couponInfo);
                                        } else {
                                            orderOkInfoList.get(Integer.valueOf(tag)).getOrderOkBotttomInfo().setCouponInfo(null);
                                        }
                                        orderOkAdapter.notifyDataSetChanged();
                                        sumInitYh();
                                    }
                                }, orderOkInfoList.get(position).getOrderOkBotttomInfo().getCouponInfoList(), "请选择优惠券", "取消", position + "");
                                couponDialog.show();
                                break;
                        }
                        //ToastUtil.makeText(mContext, "点击了-" + position, Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(orderOkAdapter);
            }
            sumInitMoney();
        } catch (Exception e) {
            BaseTool.logPrint(TAG, "sumData: ---" + e);
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    /**
     * 提交订单的点击事件
     */
    @OnClick(R.id.order_ok_submit)
    void sumOrder() {
        BaseTool.logPrint(TAG, "sumOrder: ------" + goodsIDs);
        try {
            if (goodsIDs != null && !"".equals(goodsIDs)) {
                loadingDialog.show();
                OrderOkNewInfo orderOkNewInfo = new OrderOkNewInfo();
                orderOkNewInfo.setBuyerId(BaseApplication.getInstansApp().getPersonInfo().getId());
                orderOkNewInfo.setRemark(hashMapRemark);
                orderOkNewInfo.setSkuIds(Arrays.asList(goodsIDs.split(",")));
                if (TextUtils.isEmpty(stringBuilder1.toString())) {
                    List<String> list = new ArrayList<String>();
                    if (couponInfo != null) {
                        list.add(couponInfo.getId());
                    }
                    orderOkNewInfo.setCouponIds(list);
                } else {
                    List<String> list = new ArrayList<>(Arrays.asList(stringBuilder1.toString().split(",")));
                    if (couponInfo != null) {
                        list.add(couponInfo.getId());
                    }
                    orderOkNewInfo.setCouponIds(list);
                }
                orderOkNewInfo.setInvoiced(false);
                orderOkNewInfo.setSource("MOBILE");
                BaseTool.logPrint(TAG, "order: ------" + JSON.toJSONString(orderOkNewInfo));
                banlancePresenter.doBanlance(JSON.toJSONString(orderOkNewInfo));
            } else {
                ToastUtil.makeText(mContext, "商品信息错误", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    /**
     * 平台红包的点击事件
     */
    @OnClick(R.id.order_ok_select)
    void selectCuopon() {
        if (couponInfoListPT.size() > 0) {
            CouponDialog couponDialog = new CouponDialog(mContext, new CouponDialog.CredentialBaseDialogListener() {
                @Override
                public void OnClickCredentialBaseDialogListviewItem(CouponInfo couponInfo1, String tag) {
                    couponInfo = couponInfo1;
                    sumInitYh();
                }
            }, couponInfoListPT, "请选择红包", "取消", "1");
            couponDialog.show();
        } else {
            ToastUtil.makeText(mContext, "暂无可用红包", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 仅仅是计算所有商品的总价
     */
    private void sumInitMoney() {
        Iterator<Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>>> iter = hashMapInteger.entrySet().iterator();
        allMoney = 0;
        while (iter.hasNext()) {
            Map.Entry<String, List<CartBaseInfo.GoodsItemsBean>> entry = iter.next();
            String key = entry.getKey();
            BaseTool.logPrint(TAG, "doBanlance: key" + key);
            List<CartBaseInfo.GoodsItemsBean> list = entry.getValue();
            double money = 0;
            for (int a = 0; a < list.size(); a++) {
                if (list.get(a).getAmount() >= list.get(a).getSkuDTO().getBatchNums()) {
                    money = money + (list.get(a).getAmount() * list.get(a).getSkuDTO().getWholesalePrice());
                } else {
                    money = money + (list.get(a).getAmount() * list.get(a).getSkuDTO().getRetailPrice());
                }

            }
            allMoney = allMoney + money;
        }

        if (couponInfoList!=null){
            for (int a = 0; a < couponInfoList.size(); a++) {
                //是平台优惠券    而且是   所有商品的总价超过了满减值
                if ("PING_TAI_YOU_HUI_QUAN".equals(couponInfoList.get(a).getPromotionItem().getPromotionType()) && allMoney >= couponInfoList.get(a).getPromotionItem().getGradientFullCut().getFullAmount()) {
                    couponInfoListPT.add(couponInfoList.get(a));
                    BaseTool.logPrint(TAG, "sumInitMoney: -----all" + allMoney + "------" + couponInfoList.get(a).getPromotionItem().getGradientFullCut().getFullAmount());
                }
            }
        }

        sumInitYh();
    }

    /**
     *计算最后总的合计价格
     * 1.选择店铺内的优惠券后会走此方法
     * 2.选择平台内的优惠券后会走此方法
     * 3.第一次进入，算完其他钱之后，会接着走此方法算一下优惠后的价格
     */
    private void sumInitYh() {
        shopYh = 0;
        shopFright = 0;
        stringBuilder1.delete(0, stringBuilder1.length());
        for (int a = 0; a < orderOkInfoList.size(); a++) {
            /**
             * 先计算运费的
             */
            if (orderOkInfoList.get(a).getItemType() == 3 && orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo() != null && orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo().getSendAccount() != null) {
                double b = (double) orderOkInfoList.get(a).getOrderOkBotttomInfo().getMoney();
                double money1 = b / 100;
                if (money1 < (Double.valueOf(orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo().getSendAccount().toString()) / 100)) {
                    BigDecimal bigDecimal = new BigDecimal(shopFright).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    BigDecimal bigDecimal1 = new BigDecimal(Double.valueOf(orderOkInfoList.get(a).getOrderOkBotttomInfo().getFrigthInfo().getFreight().toString()) / 100).setScale(2, BigDecimal.ROUND_HALF_DOWN);
                    shopFright = bigDecimal.add(bigDecimal1).doubleValue();
                }
            }
            /**
             * 计算优惠券
             */
            if (orderOkInfoList.get(a).getItemType() == 3 && orderOkInfoList.get(a).getOrderOkBotttomInfo().getCouponInfo() != null) {
                BigDecimal bigDecimal = new BigDecimal(String.valueOf(shopYh));
                BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(Double.valueOf(orderOkInfoList.get(a).getOrderOkBotttomInfo().getCouponInfo().getPromotionItem().getGradientFullCut().getCutPrice()) / 100));
                shopYh = bigDecimal.add(bigDecimal1).doubleValue();
                stringBuilder1.append(orderOkInfoList.get(a).getOrderOkBotttomInfo().getCouponInfo().getId());
                stringBuilder1.append(",");
            }
            BaseTool.logPrint(TAG + "ck" ,"stringbuilder =" + stringBuilder1);
            /**
             * 设置留言的hashMap     key值为上游B  id      value值为留言情况
             */
            if (orderOkInfoList.get(a).getItemType() == OrderOkInfo.BOTTOM) {
                hashMapRemark.put(orderOkInfoList.get(a).getOrderOkBotttomInfo().getShopDTOBean().getId(), orderOkInfoList.get(a).getOrderOkBotttomInfo().getWords());
            }
        }
        /**
         * 所用红包情况
         */
        if (couponInfo != null) {
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(allMoney / 100));             //药品总价格
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(shopFright));                //总的运费
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(shopYh));                    //总的优惠
            BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(couponInfo.getPromotionItem().getGradientFullCut().getCutPrice() / 100));          //平台的红包
            allMoney1 = bigDecimal.add(bigDecimal1).subtract(bigDecimal2).subtract(bigDecimal3).doubleValue();
            textViewAll.setText("满" + couponInfo.getPromotionItem().getGradientFullCut().getFullAmount() / 100 + "减" + couponInfo.getPromotionItem().getGradientFullCut().getCutPrice() / 100);
        } else {
            BigDecimal bigDecimal = new BigDecimal(String.valueOf(allMoney / 100));             //药品总价格
            BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(shopFright));                //总的运费
            BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(shopYh));                    //总的优惠
            allMoney1 = bigDecimal.add(bigDecimal1).subtract(bigDecimal2).doubleValue();
            BaseTool.logPrint("优惠券后的价格", String.valueOf(allMoney1));
            textViewAll.setText("");
        }
        textViewMoney.setText("￥" + allMoney1);
    }


    /**
     * 提交订单成功
     * @param code
     * @param result
     */
    @Override
    public void doBanlanceSucess(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        BaseTool.logPrint("订单", code + result);

        try {
            if (code == 201) {//201是成功
                List<OrderBaseInfo.ContentBean> contentBeanList = JSON.parseArray(result, OrderBaseInfo.ContentBean.class);
                if (contentBeanList.size()>0){
                    ToastUtil.makeText(this, "订单提交成功！", Toast.LENGTH_SHORT).show();
                    BaseApplication.getInstansApp().setUpdateCart(true);
                    StringBuffer stringBuffer = new StringBuffer();

                    //计算订单总金额    拼接订单id
                    int mon = 0;
                    if (contentBeanList.size() == 1) {
                        stringBuffer.append(contentBeanList.get(0).getId());
                        mon = contentBeanList.get(0).getPayment();
                    } else {
                        for (int a = 0; a < contentBeanList.size(); a++) {
                            mon = mon + contentBeanList.get(a).getPayment();
                            stringBuffer.append(contentBeanList.get(a).getId());
                            if (a != contentBeanList.size() - 1) {
                                stringBuffer.append(",");
                            }
                        }
                    }

                    String orderIDs = stringBuffer.toString();
                    Bundle bundle = new Bundle();
                    bundle.putString("order", orderIDs);
                    bundle.putString("money", String.valueOf((double) mon / 100));
                    BaseTool.goActivityWithData(this, BalanceActivity.class, bundle);
                    finish();
                }else {
                    ToastUtil.makeText(this, "订单提交失败！", Toast.LENGTH_SHORT).show();
                }

            }else {
                ToastUtil.makeText(this, "订单提交失败！"+result, Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    @Override
    public void doBanlanceFaild(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "订单提交失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getPayCodeSucess(int code, String result) {

    }

    @Override
    public void getPayCodeFaild(int code, String result) {

    }

    /**
     * 获取下游B店铺地址成功
     * @param code
     * @param result
     */
    @Override
    public void getShopAdressSuccess(int code, String result) {
        try {
            if (code == 200) {
                ShopAdressInfo shopAdressInfo = JSON.parseObject(result, ShopAdressInfo.class);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(shopAdressInfo.getAddress().getProvince());
                stringBuilder.append(shopAdressInfo.getAddress().getCity());
                stringBuilder.append(shopAdressInfo.getAddress().getDistrict());
//                stringBuilder.append(shopAdressInfo.getAddress().getTown());
                stringBuilder.append(shopAdressInfo.getAddress().getRoad());
                textViewAdress.setText(stringBuilder.toString());
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getShopAdressFailed(int code, String result) {
    }

    /**
     * 请求优惠券信息成功
     * @param code
     * @param result
     */
    @Override
    public void getCouponSuccess(int code, String result) {
        try{
            if (code == 200) {
                couponInfoList = JSON.parseArray(result, CouponInfo.class);
                sumData();
            } else {
                BaseTool.logPrint("abcd----","测试");
                sumData();
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @Override
    public void getCouponFailed(int code, String result) {
        sumData();
    }

}
