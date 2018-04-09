package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.precenter.ShopBannerPresenter;
import com.mhky.dianhuotong.shop.shopif.ShopBannerIF;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class DianHuoTongShopActivity extends BaseActivity implements ShopBannerIF, OnBannerListener {
    @BindView(R.id.banner_main_accordion)
    BGABanner bgaBanner;

    private ShopBannerPresenter shopBannerPresenter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_huo_tong_shop);
        ButterKnife.bind(this);
        mContext = this;
        inIt();
    }

    private void inIt() {
        shopBannerPresenter = new ShopBannerPresenter(this);
        shopBannerPresenter.getdata();
    }

    @OnClick(R.id.shop_title_left_image)
    void goBack() {
        finish();
    }

    @OnClick(R.id.shop_scan_code)
    void goScanCodeActivity() {
        ToastUtil.makeText(this, "进入扫码界面", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.shop_input)
    void goSearchActivity() {
        ToastUtil.makeText(this, "进入搜索界面", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.shop_order)
    void goOrderActivity() {
        BaseTool.goActivityNoData(this, MyselectedActivity.class);
    }

    @OnClick(R.id.shop_car)
    void goShopActivity() {
        ToastUtil.makeText(this, "进入购物车界面", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void getDataIF(List<?> stringData) {
        initImageBaner(stringData);
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
    public void OnBannerClick(int position) {

    }
}
