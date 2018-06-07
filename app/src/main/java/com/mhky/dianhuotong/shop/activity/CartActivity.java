package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.CartAdapter;
import com.mhky.dianhuotong.shop.bean.CartBaseInfo;
import com.mhky.dianhuotong.shop.bean.CartBodyInfo;
import com.mhky.dianhuotong.shop.bean.CartInfo;
import com.mhky.dianhuotong.shop.bean.CartTitleInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkBotttomInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkCenterInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkTitleInfo;
import com.mhky.dianhuotong.shop.precenter.CartDataPresenter;
import com.mhky.dianhuotong.shop.precenter.CartOpratePresenter;
import com.mhky.dianhuotong.shop.shopif.CartDataIF;
import com.mhky.dianhuotong.shop.shopif.CartOprateIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity implements CartOprateIF, CartDataIF, DianHuoTongBaseDialog.BaseDialogListener {
    @BindView(R.id.cart_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.cart_body)
    RecyclerView recyclerView;
    @BindView(R.id.cart_sum_button)
    TextView textViewSum;
    @BindView(R.id.cart_oprate_button)
    LinearLayout linearLayoutOparate;
    @BindView(R.id.cart_oprate_button_off)
    LinearLayout linearLayoutOparateOff;
    @BindView(R.id.cart_balance_money)
    TextView textViewMoney;
    @BindView(R.id.cart_all_check)
    CheckBox checkBoxAll;
    @BindView(R.id.base_tips)
    RelativeLayout relativeLayoutTips;
    private CartAdapter cartAdapter;
    private List<CartInfo> cartInfoList;
    private Context mContext;
    private boolean isEdite = false;
    private CartOpratePresenter cartOpratePresenter;
    private CartDataPresenter cartDataPresenter;
    private String selelctGoodsId = "";
    private double integerMoney = 0;
    private int alterGoodsNumber = -1;
    private List<Integer> parentId;
    private List<Integer> listID;
    private DianHuoTongBaseDialog dianHuoTongBaseDialog;
    private HashMap<String, List<CartBaseInfo.GoodsItemsBean>> hashMapInteger;
    private static final String TAG = "CartActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        mContext = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BaseApplication.getInstansApp().isUpdateCart()) {
            if (cartAdapter != null) {
                cartOpratePresenter.getCart(BaseApplication.getInstansApp().getLoginRequestInfo().getId(), 1);
            }
        }
    }


    private void init() {
        hashMapInteger = new HashMap<>();
        parentId = new ArrayList<>();
        listID = new ArrayList<>();
        dianHuoTongBaseDialog = new DianHuoTongBaseDialog(this, this, "温馨提示", "您确定要删除所选中的商品吗？", "取消", "确定", "0");
        initTitle();
    }

    private void initTitle() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("购物车");
        dianHuoTongBaseTitleBar.setRightText("编辑");
        dianHuoTongBaseTitleBar.setRightTextOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isEdite) {
                    isEdite = true;
                    linearLayoutOparateOff.setVisibility(View.GONE);
                    linearLayoutOparate.setVisibility(View.VISIBLE);
                    dianHuoTongBaseTitleBar.setRightText("完成");
                } else {
                    isEdite = false;
                    linearLayoutOparate.setVisibility(View.GONE);
                    linearLayoutOparateOff.setVisibility(View.VISIBLE);
                    dianHuoTongBaseTitleBar.setRightText("编辑");
                }
                // ToastUtil.makeText(mContext, "进行编辑", Toast.LENGTH_SHORT).show();
            }
        });
        cartOpratePresenter = new CartOpratePresenter(this);
        cartOpratePresenter.getCart(BaseApplication.getInstansApp().getLoginRequestInfo().getId(), 0);
        cartInfoList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.cart_sum_button)
    void doBanlance() {
        try {
            getGoodsIdList();
            if (!"".equals(selelctGoodsId)) {
                BaseTool.logPrint(TAG, "doBanlance: map-" + hashMapInteger.size());
                StringBuffer s2 = new StringBuffer();

                Iterator iter = hashMapInteger.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    String key = (String) entry.getKey();
                    BaseTool.logPrint(TAG, "doBanlance: key" + key);
                    s2.append(key + ",");
                }

                String s1 = JSON.toJSONString(hashMapInteger);
                String s3 = s2.toString().substring(0, s2.length() - 1);
                BaseTool.logPrint(TAG, "doBanlance: ------map" + s1);
                Bundle bundle = new Bundle();
//            bundle.putString("goodsIds", selelctGoodsId);
//            bundle.putString("money", Double.toString(integerMoney));
                bundle.putString("basedata", s1);
                bundle.putString("sids", s3);
                bundle.putSerializable("data", hashMapInteger);
                BaseTool.goActivityWithData(this, OderOkActivity.class, bundle);
                //BaseTool.goActivityWithData(mContext, BalanceActivity.class, bundle);
            } else {
                ToastUtil.makeText(this, "请下单商品", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @OnClick(R.id.cart_delete_button)
    void deleteCart() {
        if (!"".equals(selelctGoodsId)) {
            dianHuoTongBaseDialog.show();
        } else {
            ToastUtil.makeText(this, "请勾选商品", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.cart_all_selelct)
    void setAllSelect() {
        try {
            if (cartAdapter == null) {
                return;
            }
            if (checkBoxAll.isChecked()) {
                checkBoxAll.setChecked(false);
                //设置商品取消全部
                setAllData(0);
            } else {
                checkBoxAll.setChecked(true);
                setAllData(1);
                //设置商品全部选择
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @OnClick(R.id.cart_love_button)
    void loveGoods() {
        if (!"".equals(selelctGoodsId)) {
            //dianHuoTongBaseDialog.show();
            //ToastUtil.makeText(this, "收藏成功", Toast.LENGTH_SHORT).show();
        } else {
            ToastUtil.makeText(this, "请勾选商品", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAllData(int type) {
        if (cartAdapter != null) {
            List<CartInfo> cartInfoList3 = cartAdapter.getData();
            for (int p = 0; p < cartInfoList3.size(); p++) {
                if (cartInfoList3.get(p).isHeader) {
                    if (type == 0) {
                        cartInfoList3.get(p).getCartTitleInfo().setSelectTitle(false);
                    } else {
                        cartInfoList3.get(p).getCartTitleInfo().setSelectTitle(true);
                    }
                } else {
                    if (type == 0) {
                        cartInfoList3.get(p).getCartBodyBaseInfo().setSelectChild(false);
                    } else {
                        cartInfoList3.get(p).getCartBodyBaseInfo().setSelectChild(true);
                    }
                }
            }
            cartAdapter.notifyDataSetChanged();
            getGoodsIdList();
        }

    }

    @Override
    public void addCartSucess(int code, String result) {

    }

    @Override
    public void addCartFaild(int code, String result) {

    }

    @Override
    public void deleteCartSucess(int code, String result) {
        dianHuoTongBaseDialog.dismiss();
        ToastUtil.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
        try {
            if (cartAdapter != null && !TextUtils.isEmpty(BaseApplication.getInstansApp().getPersonInfo().getId())) {
                cartOpratePresenter.getCart(BaseApplication.getInstansApp().getPersonInfo().getId(), 1);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void deleteCartFaild(int code, String result) {

    }

    @Override
    public void getCartSucess(int code, String result, int type) {
        try {
            cartDataPresenter = null;
            cartDataPresenter = new CartDataPresenter(this);
            if (code == 200) {
                if (type == 0) {
                    cartInfoList = cartDataPresenter.getCartInfoList(result);
                    if (cartInfoList.size() <= 0) {
                        relativeLayoutTips.setVisibility(View.VISIBLE);
                    } else {
                        relativeLayoutTips.setVisibility(View.GONE);
                        cartAdapter = new CartAdapter(R.layout.item_cart_body, R.layout.item_cart_head, cartInfoList, mContext);
                        cartAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                List<CartInfo> cartInfoListChild = (List<CartInfo>) adapter.getData();
                                if (!cartInfoListChild.get(position).isHeader) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString("id", cartInfoList.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getGoodsId());
                                    BaseTool.goActivityWithData(mContext, GoodsActivity.class, bundle);
                                }
                            }
                        });
                        cartAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                List<CartInfo> cartInfoList1 = (List<CartInfo>) adapter.getData();
                                if (cartInfoList1.get(position).isHeader) {
                                    switch (view.getId()) {
                                        case R.id.cart_head_check:
                                            int a = position + 1;
                                            if (!cartInfoList1.get(position).getCartTitleInfo().isSelectTitle()) {//父按钮没被选中
                                                cartInfoList1.get(position).getCartTitleInfo().setSelectTitle(true);//设置父按钮选中
                                                for (int b = a; b < cartInfoList1.size(); b++) {
                                                    if (b != cartInfoList1.size()) {
                                                        if (!cartInfoList1.get(b).isHeader) {
                                                            cartInfoList1.get(b).getCartBodyBaseInfo().setSelectChild(true);
                                                        } else {
                                                            break;
                                                        }
                                                    }

                                                }
                                            } else {//父按钮没选中
                                                cartInfoList1.get(position).getCartTitleInfo().setSelectTitle(false);
                                                for (int b = a; b < cartInfoList1.size(); b++) {
                                                    if (b != cartInfoList1.size()) {
                                                        if (!cartInfoList1.get(b).isHeader) {
                                                            if (cartInfoList1.get(b).getCartBodyBaseInfo().isSelectChild()) {
                                                                cartInfoList1.get(b).getCartBodyBaseInfo().setSelectChild(false);
                                                            }
                                                        } else {
                                                            break;
                                                        }
                                                    }
                                                }
                                            }
                                            adapter.notifyDataSetChanged();
                                            break;
                                        case R.id.cart_head_go:
                                            Bundle bundle = new Bundle();
                                            bundle.putString("shopid", cartInfoList1.get(position).getCartTitleInfo().getShopDTOBean().getId());
                                            BaseTool.goActivityWithData(mContext, ShopActivity.class, bundle);
                                            //ToastUtil.makeText(mContext, "进入店铺" + position, Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                } else {
                                    switch (view.getId()) {
                                        case R.id.cart_body_check:
//                            ToastUtil.makeText(mContext, "选中了商品" + position + "上一级是" + cartInfoList1.get(position).getCartBodyBaseInfo().getParentNumber() + "数量是" + cartInfoList1.get(position).getCartBodyBaseInfo().getChildNumber(), Toast.LENGTH_SHORT).show();
                                            if (!cartInfoList1.get(position).getCartBodyBaseInfo().isSelectChild()) {
                                                cartInfoList1.get(position).getCartBodyBaseInfo().setSelectChild(true);
                                            } else {
                                                cartInfoList1.get(position).getCartBodyBaseInfo().setSelectChild(false);
                                            }
                                            int a = position - (cartInfoList1.get(position).getCartBodyBaseInfo().getChildIndex() + 1) + 1;//减一是因为index从0开始
                                            BaseTool.logPrint(TAG, "onItemChildClick: ----" + a);
                                            int mTemp = 0;
                                            for (int b = a; b < cartInfoList1.size(); b++) {
                                                if (b != cartInfoList1.size()) {
                                                    if (!cartInfoList1.get(b).isHeader && cartInfoList1.get(b).getCartBodyBaseInfo().isSelectChild()) {
                                                        mTemp++;
                                                        BaseTool.logPrint(TAG, "onItemChildClick: temp_number" + mTemp);
                                                    } else {
                                                        break;
                                                    }
                                                }

                                            }
                                            BaseTool.logPrint(TAG, "onItemChildClick:所有子商品数量 ----" + cartInfoList1.get(position).getCartBodyBaseInfo().getChildNumber());
                                            BaseTool.logPrint(TAG, "onItemChildClick: a==" + a);
                                            if (mTemp == cartInfoList1.get(position).getCartBodyBaseInfo().getChildNumber()) {
                                                cartInfoList1.get(a - 1).getCartTitleInfo().setSelectTitle(true);
                                                BaseTool.logPrint(TAG, "onItemChildClick: 设置父店铺设置为全选状态");
                                            } else {
                                                cartInfoList1.get(a - 1).getCartTitleInfo().setSelectTitle(false);
                                                BaseTool.logPrint(TAG, "onItemChildClick: 设置父店铺设置为未全选状态");
                                            }
                                            adapter.notifyDataSetChanged();
                                            break;
                                        case R.id.cart_popup_plus:
                                            if (cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getAmount() < cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getStock()) {
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("skuId", cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getId());
                                                hashMap.put("amount", cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getAmount() + 1);
                                                cartOpratePresenter.alterCart(hashMap, 0);
                                                alterGoodsNumber = position;
                                            }
                                            //ToastUtil.makeText(mContext, "增加了商品" + position, Toast.LENGTH_SHORT).show();
                                            break;
                                        case R.id.cart_popup_reduce:
                                            if (cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getAmount() > 0) {
                                                HashMap hashMap = new HashMap();
                                                hashMap.put("skuId", cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getId());
                                                hashMap.put("amount", cartInfoList1.get(position).getCartBodyBaseInfo().getGoodsItemsBean().getAmount() - 1);
                                                cartOpratePresenter.alterCart(hashMap, 1);
                                                alterGoodsNumber = position;
                                            }
                                            //ToastUtil.makeText(mContext, "减少了商品" + position, Toast.LENGTH_SHORT).show();
                                            break;
                                    }
                                }
                                cartInfoList = cartInfoList1;
                                sumSelelct(cartInfoList1);
                                getGoodsIdList();
                            }
                        });
                        recyclerView.setAdapter(cartAdapter);
                    }

                } else {
                    List<CartInfo> cartInfoListNew = cartDataPresenter.getCartInfoList(result);
                    if (cartInfoListNew.size() <= 0) {
                        relativeLayoutTips.setVisibility(View.VISIBLE);
                    } else {
                        relativeLayoutTips.setVisibility(View.GONE);
                    }
                    cartAdapter.setNewData(cartInfoListNew);
                    cartInfoList = cartInfoListNew;
                    getGoodsIdList();
                    checkBoxAll.setChecked(false);
                    BaseApplication.getInstansApp().setUpdateCart(false);
                    BaseTool.logPrint(TAG, "getCartSucess: ----" + cartInfoList.size());
                    //cartAdapter.notifyDataSetChanged();
                }

            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getCartFaild(int code, String result, int type) {

    }

    @Override
    public void alterCartSucess(int code, String result, int type) {
        try {
            if (code == 204) {
                //修改成功
                int number = cartAdapter.getData().get(alterGoodsNumber).getCartBodyBaseInfo().getGoodsItemsBean().getAmount();
                if (type == 0) {
                    number = number + 1;
                } else if (type == 1) {
                    number = number - 1;
                }
                cartAdapter.getData().get(alterGoodsNumber).getCartBodyBaseInfo().getGoodsItemsBean().setAmount(number);
                cartInfoList = cartAdapter.getData();
                cartAdapter.notifyDataSetChanged();
                getGoodsIdList();
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @Override
    public void alterCartFaild(int code, String result, int type) {

    }

    @Override
    public void getSkuSucess(int code, String result) {

    }

    @Override
    public void getSkuFaild(int code, String result) {

    }

    @Override
    public void getCartData(List<CartInfo> cartInfoList) {

    }

    private boolean sumSelelct(List<CartInfo> cartInfoList2) {
        int a = 0;
        int b = 0;
        for (int q = 0; q < cartInfoList2.size(); q++) {
            if (cartInfoList2.get(q).isHeader) {
                a++;
                if (cartInfoList2.get(q).getCartTitleInfo().isSelectTitle()) {
                    b++;
                }

            }
        }
        if (a == b) {
            checkBoxAll.setChecked(true);
            return true;
        } else {
            checkBoxAll.setChecked(false);
            return false;
        }
    }

    /**
     * 计算当前购物车
     *
     * @return
     */
    private String getGoodsIdList() {
        StringBuilder stringBuilder = new StringBuilder("");
        try {
            listID.clear();
            hashMapInteger.clear();
            Integer integer = new Integer(0);
            List<String> nameList = new ArrayList<>();
            List<Integer> integerList = new ArrayList<>();
            if (cartAdapter != null) {
                List<CartInfo> cartInfoListResult = cartAdapter.getData();
                if (cartInfoListResult != null) {
                    for (int i = 0; i < cartInfoListResult.size(); i++) {
                        if (!cartInfoListResult.get(i).isHeader && cartInfoListResult.get(i).getCartBodyBaseInfo().isSelectChild()) {
                            listID.add(i);
                            if (!hashMapInteger.containsKey(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getShopDTO().getId())) {
                                List<CartBaseInfo.GoodsItemsBean> list = new ArrayList();
                                list.add(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean());
                                hashMapInteger.put(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getShopDTO().getId(), list);
                            } else {
                                hashMapInteger.get(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getShopDTO().getId()).add(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean());
                            }
                            nameList.add(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getId() + "");
                            integerList.add(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getInPrice() * cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getAmount());
                        }
                    }
                }
            }
            if (nameList.size() == 1) {
                stringBuilder.append(nameList.get(0));
                integer = integerList.get(0);
            } else {
                for (int j = 0; j < nameList.size(); j++) {
                    //下面处理选中项金额
                    integer = integer + integerList.get(j);
                    //下面处理选中项id
                    stringBuilder.append(nameList.get(j));
                    if (j != nameList.size() - 1) {
                        stringBuilder.append(",");
                    }

                }
            }
            BaseTool.logPrint(TAG, "doBanlance: -------" + stringBuilder.toString());
            integerMoney = integer.doubleValue() / 100;
            selelctGoodsId = stringBuilder.toString();
            textViewMoney.setText("合计：￥" + integer.doubleValue() / 100);
        }catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
        return stringBuilder.toString();
//        if (cartInfoList != null && cartInfoList.size() > 0) {
//            for (int i = 0; i < cartInfoList.size(); i++) {
//                if (!cartInfoList.get(i).isHeader && cartInfoList.get(i).getCartBodyBaseInfo().isSelectChild()) {
//                    stringBuilder.append(cartInfoList.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getId());
//                    if (i != cartInfoList.size() - 1) {
//                        stringBuilder.append(",");
//                    }
//                }
//            }
//        }


    }

    @Override
    public void onClickBaseDialogLeft(String iTag) {
        dianHuoTongBaseDialog.dismiss();
    }

    @Override
    public void onClickBaseDialogRight(String iTag) {
        cartOpratePresenter.deleteCart(selelctGoodsId);
    }
}
