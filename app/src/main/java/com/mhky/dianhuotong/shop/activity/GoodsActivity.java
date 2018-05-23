package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.viewexplosion.Utils;
import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.bean.ShopInfo;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.ShopPresenter;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.ShopIF;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class GoodsActivity extends BaseActivity implements GoodsIF ,ShopIF{
    @BindView(R.id.goods_title)
    TextView textViewTitle;
    @BindView(R.id.goods_price)
    TextView textViewGoodsPrice;
    @BindView(R.id.goods_info_name)
    TextView textViewGoodsName;
    @BindView(R.id.goods_info_guige)
    TextView textViewGoodsGuige;
    @BindView(R.id.goods_info_company)
    TextView textViewGoodsCompany;
    @BindView(R.id.goods_info_pzwh)
    TextView textViewPzwh;
    @BindView(R.id.banner_main_accordion)
    BGABanner bgaBanner;
    @BindView(R.id.goods_shop_name)
    TextView textViewShopName;
    @BindView(R.id.goods_base_addcart)
    TextView textViewAddCart;
    @BindView(R.id.goods_use_time)
    TextView textViewExp;
    @BindView(R.id.go_cart)
    TextView textViewGoCart;
    @BindView(R.id.goods_shop_img)
    ImageView imageViewLogo;
    private SearchSGoodsBean.ContentBean goodsInfoBase;
    private GoodsInfo goodsInfo;
    private Bundle bundle;
    private Context mContext;
    private GoodsPrecenter goodsPrecenter;
    private String goodsDes;
    private String goodsIns;
    private CartPopupwindow cartPopupwindow;
    private boolean isFirst = true;
    private String goodsId;
    private ShopPresenter shopPresenter;
    private static final String TAG = "GoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mContext = this;
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor("#ffffff").statusBarDarkFont(true).init();
        goodsPrecenter = new GoodsPrecenter(this);
        shopPresenter=new ShopPresenter(this);
        if (getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            goodsId = bundle.getString("id");
            //goodsInfoBase = (SearchSGoodsBean.ContentBean) bundle.getSerializable("getgoodsinfo");
            if (goodsId != null) {
                goodsPrecenter.getGoodsInfo(goodsId);
//                textViewTitle.setText(goodsInfoBase.getTitle());
//                textViewGoodsPrice.setText(String.valueOf(goodsInfoBase.getPrice() / 100));
//                textViewGoodsName.setText(goodsInfoBase.getName());
//                textViewGoodsCompany.setText(goodsInfoBase.getManufacturer());
//                textViewGoodsGuige.setText(goodsInfoBase.getModel());
//                textViewPzwh.setText(goodsInfoBase.getApprovalNumber());
//                textViewUseTime.setText(goodsInfoBase.getExpiryDate());
//                textViewShopName.setText("");
            } else {
                ToastUtil.makeText(mContext, "数据解析错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.goods_base_back)
    void back() {
        finish();
    }

    @OnClick(R.id.goods_base_go_more)
    void goGoodsMore() {
        if (goodsDes != null || goodsInfo != null) {
            Bundle bundle = new Bundle();
            bundle.putString("des", goodsDes);
            bundle.putString("ins", goodsIns);
            BaseTool.goActivityWithData(this, GoodsInfoActivity.class, bundle);
        }

    }

    @OnClick(R.id.goods_base_go_shop)
    void goGoodsShop() {
        if (goodsInfo != null && goodsInfo.getShopInfo().getId() != null) {
            Bundle bundle = new Bundle();
            bundle.putString("shopid", goodsInfo.getShopInfo().getId());
            BaseTool.goActivityWithData(this, ShopActivity.class, bundle);
        }
    }

    @OnClick(R.id.goods_base_addcart)
    void addCart() {
        if (goodsId != null) {
            goodsPrecenter.getGoodsInfo(goodsId + "");
        } else {
            ToastUtil.makeText(mContext, "数据解析错误", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.go_cart)
    void goCart() {
        BaseTool.goActivityNoData(this,CartActivity.class);
    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    if (goodsInfo.getAppDescription() != null) {
                        goodsDes = goodsInfo.getAppDescription();
                    }
                    if (goodsInfo.getInstruction() != null) {
                        goodsIns = goodsInfo.getInstruction();
                    }
                    if (goodsInfo.getTitle() != null) {
                        textViewTitle.setText(goodsInfo.getTitle());
                    }
                    textViewGoodsPrice.setText(String.valueOf(goodsInfo.getPrice() / 100));
                    if (goodsInfo.getName() != null) {
                        textViewGoodsName.setText(goodsInfo.getName());
                    }
                    if (goodsInfo.getManufacturer() != null) {
                        textViewGoodsCompany.setText(goodsInfo.getManufacturer());
                    }
                    if (goodsInfo.getModel() != null) {
                        textViewGoodsGuige.setText(goodsInfo.getModel());
                    }
                    if (goodsInfo.getApprovalNumber() != null) {
                        textViewPzwh.setText(goodsInfo.getApprovalNumber());
                    }
                    if (goodsInfo.getShopInfo() != null && goodsInfo.getShopInfo().getShopName() != null) {
                        textViewShopName.setText(goodsInfo.getShopInfo().getShopName());
                    }
                    if (goodsInfo.getPicture() != null) {
                        String[] imageDate = goodsInfo.getPicture().split(",");
                        initImageBaner(Arrays.asList(imageDate));
                    }
                    if (goodsInfo.getExpiryDate() != null) {
                        textViewExp.setText(goodsInfo.getExpiryDate().toString());
                    }
                    if (!isFirst) {
                        cartPopupwindow = new CartPopupwindow(this, goodsInfo);
                        cartPopupwindow.showAtLocation(textViewAddCart, Gravity.BOTTOM, 0, 0);
                    } else {
                        isFirst = false;
                    }

                }
              shopPresenter.getShopInfo(goodsInfo.getShopInfo().getId());
            }
        } catch (Exception e) {
            ToastUtil.makeText(this, "数据解析错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {

    }

    //初始化轮播图
    private void initImageBaner(List<?> list) {
        bgaBanner.setAdapter(new BGABanner.Adapter() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                Uri uri = Uri.parse((String) model);
                Picasso.with(mContext).load(uri).fit().into((ImageView) itemView);
            }
        });

        bgaBanner.setAutoPlayAble(true);
        bgaBanner.setData(list, new ArrayList<String>());
    }

    @Override
    public void getShopInfoSuccess(int code, String result) {
        if (code == 200) {
            ShopInfo shopInfo = JSON.parseObject(result, ShopInfo.class);
            if (shopInfo.getLogo() != null) {
                Picasso.with(this).load(shopInfo.getLogo()).resize(Utils.dp2Px(40),Utils.dp2Px(40)).into(imageViewLogo);
            }
            textViewShopName.setText(shopInfo.getName());
        }
    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }

    @Override
    public void getShopTypeSuccess(int code, String result) {

    }

    @Override
    public void getShopTypeFailed(int code, String result) {

    }
}
