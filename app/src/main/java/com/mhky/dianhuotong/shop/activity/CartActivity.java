package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.CartAdapter;
import com.mhky.dianhuotong.shop.bean.CartBodyInfo;
import com.mhky.dianhuotong.shop.bean.CartInfo;
import com.mhky.dianhuotong.shop.bean.CartTitleInfo;
import com.mhky.dianhuotong.shop.precenter.CartDataPresenter;
import com.mhky.dianhuotong.shop.precenter.CartOpratePresenter;
import com.mhky.dianhuotong.shop.shopif.CartDataIF;
import com.mhky.dianhuotong.shop.shopif.CartOprateIF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CartActivity extends BaseActivity implements CartOprateIF, CartDataIF {
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
    private CartAdapter cartAdapter;
    private List<CartInfo> cartInfoList;
    private Context mContext;
    private boolean isEdite = false;
    private CartOpratePresenter cartOpratePresenter;
    private CartDataPresenter cartDataPresenter;
    private String selelctGoodsId = "";
    private double integerMoney = 0;
    private static final String TAG = "CartActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        ButterKnife.bind(this);
        mContext = this;
        init();
    }

    private void init() {
        initTitle();

    }

    private void initTitle() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
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
                ToastUtil.makeText(mContext, "进行编辑", Toast.LENGTH_SHORT).show();
            }
        });
        cartOpratePresenter = new CartOpratePresenter(this);
        cartOpratePresenter.getCart(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        cartDataPresenter = new CartDataPresenter(this);
        cartInfoList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @OnClick(R.id.cart_sum_button)
    void doBanlance() {
        getGoodsIdList();
        Bundle bundle = new Bundle();
        bundle.putString("goodsIds", selelctGoodsId);
        bundle.putString("money", Double.toString(integerMoney));
        BaseTool.goActivityWithData(mContext, BalanceActivity.class, bundle);
    }

    @Override
    public void addCartSucess(int code, String result) {

    }

    @Override
    public void addCartFaild(int code, String result) {

    }

    @Override
    public void deleteCartSucess(int code, String result) {

    }

    @Override
    public void deleteCartFaild(int code, String result) {

    }

    @Override
    public void getCartSucess(int code, String result) {
        if (code == 200) {
            cartInfoList = cartDataPresenter.getCartInfoList(result);
            cartAdapter = new CartAdapter(R.layout.item_cart_body, R.layout.item_cart_head, cartInfoList, mContext);
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
                                Log.d(TAG, "onItemChildClick: ----" + a);
                                int mTemp = 0;
                                for (int b = a; b < cartInfoList1.size(); b++) {
                                    if (b != cartInfoList1.size()) {
                                        if (!cartInfoList1.get(b).isHeader && cartInfoList1.get(b).getCartBodyBaseInfo().isSelectChild()) {
                                            mTemp++;
                                            Log.d(TAG, "onItemChildClick: temp_number" + mTemp);
                                        } else {
                                            break;
                                        }
                                    }

                                }
                                Log.d(TAG, "onItemChildClick:所有子商品数量 ----" + cartInfoList1.get(position).getCartBodyBaseInfo().getChildNumber());
                                Log.d(TAG, "onItemChildClick: a==" + a);
                                if (mTemp == cartInfoList1.get(position).getCartBodyBaseInfo().getChildNumber()) {
                                    cartInfoList1.get(a - 1).getCartTitleInfo().setSelectTitle(true);
                                    Log.d(TAG, "onItemChildClick: 设置父店铺设置为全选状态");
                                } else {
                                    cartInfoList1.get(a - 1).getCartTitleInfo().setSelectTitle(false);
                                    Log.d(TAG, "onItemChildClick: 设置父店铺设置为未全选状态");
                                }
                                adapter.notifyDataSetChanged();
                                break;
                            case R.id.cart_popup_plus:
                                ToastUtil.makeText(mContext, "增加了商品" + position, Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.cart_popup_reduce:
                                ToastUtil.makeText(mContext, "减少了商品" + position, Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                    cartInfoList = cartInfoList1;
                    getGoodsIdList();
                }
            });
            recyclerView.setAdapter(cartAdapter);
        }
    }

    @Override
    public void getCartFaild(int code, String result) {

    }

    @Override
    public void alterCartSucess(int code, String result) {

    }

    @Override
    public void alterCartFaild(int code, String result) {

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

    private String getGoodsIdList() {
        StringBuilder stringBuilder = new StringBuilder("");
        Integer integer = new Integer(0);
        List<String> nameList = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>();
        if (cartAdapter != null) {
            List<CartInfo> cartInfoListResult = cartAdapter.getData();
            if (cartInfoListResult != null) {
                for (int i = 0; i < cartInfoListResult.size(); i++) {
                    if (!cartInfoListResult.get(i).isHeader && cartInfoListResult.get(i).getCartBodyBaseInfo().isSelectChild()) {
                        nameList.add(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getId() + "");
                        integerList.add(cartInfoListResult.get(i).getCartBodyBaseInfo().getGoodsItemsBean().getInPrice());
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
        Log.d(TAG, "doBanlance: -------" + stringBuilder.toString());
        integerMoney = integer.doubleValue()/100;
        selelctGoodsId = stringBuilder.toString();
        textViewMoney.setText("合计：￥" + integer.doubleValue()/100 );
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

}
