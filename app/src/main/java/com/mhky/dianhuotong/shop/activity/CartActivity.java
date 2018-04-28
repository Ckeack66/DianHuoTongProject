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
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.CartAdapter;
import com.mhky.dianhuotong.shop.bean.CartBodyInfo;
import com.mhky.dianhuotong.shop.bean.CartInfo;
import com.mhky.dianhuotong.shop.bean.CartTitleInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CartActivity extends AppCompatActivity {
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
    private CartAdapter cartAdapter;
    private List<CartInfo> cartInfoList;
    private Context mContext;
    private boolean isEdite = false;
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
        cartInfoList = new ArrayList<>();
        for (int a = 0; a < 10; a++) {
            CartTitleInfo cartTitleInfo = new CartTitleInfo();
            if (a == 0) {
                cartTitleInfo.setViewTab(true);
            }
            CartInfo cartInfoTitle = new CartInfo(true, "", cartTitleInfo);
            cartInfoList.add(cartInfoTitle);
            for (int b = 0; b < 4; b++) {
                CartBodyInfo cartBodyInfo = new CartBodyInfo();
                cartBodyInfo.setParentNumber(a);
                cartBodyInfo.setChildNumber(4);
                cartBodyInfo.setChildIndex(b);
                CartInfo cartInfoBody = new CartInfo(cartBodyInfo);
                cartInfoList.add(cartInfoBody);
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        cartAdapter = new CartAdapter(R.layout.item_cart_body, R.layout.item_cart_head, cartInfoList);
        cartAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<CartInfo> cartInfoList1 = (List<CartInfo>) adapter.getData();
                if (cartInfoList1.get(position).isHeader) {
                    switch (view.getId()) {
                        case R.id.cart_head_check:
                            int a = position + 1;
                            if (!cartInfoList1.get(position).getCartTitleInfo().isSelectTitle()) {
                                cartInfoList1.get(position).getCartTitleInfo().setSelectTitle(true);
                                while (!cartInfoList1.get(a).isHeader) {
                                    if (!cartInfoList1.get(a).getCartBodyBaseInfo().isSelectChild()) {
                                        cartInfoList1.get(a).getCartBodyBaseInfo().setSelectChild(true);
                                    }
                                    a++;
                                }
                            } else {
                                cartInfoList1.get(position).getCartTitleInfo().setSelectTitle(false);
                                while (!cartInfoList1.get(a).isHeader) {
                                    if (cartInfoList1.get(a).getCartBodyBaseInfo().isSelectChild()) {
                                        cartInfoList1.get(a).getCartBodyBaseInfo().setSelectChild(false);
                                    }
                                    a++;
                                }
                            }
                            adapter.notifyDataSetChanged();
                            break;
                        case R.id.cart_head_go:
                            ToastUtil.makeText(mContext, "进入店铺" + position, Toast.LENGTH_SHORT).show();
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
                            int a = position - cartInfoList1.get(position).getCartBodyBaseInfo().getChildIndex();
                            Log.d(TAG, "onItemChildClick: ----" + a);
                            int mTemp = 0;
                            while (a < cartInfoList1.size() && !cartInfoList1.get(a).isHeader) {
                                if (cartInfoList1.get(a).getCartBodyBaseInfo().isSelectChild()) {
                                    mTemp++;
                                    Log.d(TAG, "onItemChildClick: temp_number" + mTemp);
                                }
                                a++;
                            }
                            if (mTemp == cartInfoList1.get(position).getCartBodyBaseInfo().getChildNumber()) {
                                cartInfoList1.get(position - cartInfoList1.get(position).getCartBodyBaseInfo().getChildIndex() - 1).getCartTitleInfo().setSelectTitle(true);
                            } else {
                                cartInfoList1.get(position - cartInfoList1.get(position).getCartBodyBaseInfo().getChildIndex() - 1).getCartTitleInfo().setSelectTitle(false);
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
            }
        });
        recyclerView.setAdapter(cartAdapter);
    }
}
