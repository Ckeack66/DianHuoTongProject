package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mhky.dianhuotong.shop.activity.AllGoodsActivity;
import com.mhky.dianhuotong.shop.adapter.ShopListviewAdapter;
import com.mhky.dianhuotong.shop.adapter.ShopMiaoShaAdapter;
import com.mhky.dianhuotong.shop.tool.TimerMiaoSha;
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
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class DianHuoTongShopActivity extends BaseActivity implements ShopBannerIF, OnBannerListener, TimerMiaoSha.TimerMiaoShaListener {
    @BindView(R.id.banner_main_accordion)
    BGABanner bgaBanner;
    @BindView(R.id.shop_listview)
    ListView listView;
    @BindView(R.id.shop_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.shop_hh)
    TextView textViewHH;
    @BindView(R.id.shop_mm)
    TextView textViewMM;
    @BindView(R.id.shop_ss)
    TextView textViewSS;
    private ShopBannerPresenter shopBannerPresenter;
    private Context mContext;
    private ShopListviewAdapter shopListviewAdapter;
    private ShopMiaoShaAdapter shopMiaoShaAdapter;
    private static final String TAG = "DianHuoTongShopActivity";
    private TimerMiaoSha timerMiaoSha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_huo_tong_shop);
        ButterKnife.bind(this);
        mContext = this;
        inIt();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timerMiaoSha.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void inIt() {
        shopBannerPresenter = new ShopBannerPresenter(this);
        shopBannerPresenter.getdata();
        int a = new Random().nextInt(10);
        Log.d(TAG, "inIt:--------- " + a);
        shopListviewAdapter = new ShopListviewAdapter(mContext, a);
        listView.setAdapter(shopListviewAdapter);
        BaseTool.setListViewHeightBasedOnChildren(listView);
        shopMiaoShaAdapter = new ShopMiaoShaAdapter(a, mContext);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(shopMiaoShaAdapter);
        timerMiaoSha = new TimerMiaoSha(3600000, 1000, this);
        timerMiaoSha.start();

    }

    @OnClick(R.id.shop_title_left_image)
    void goBack() {
        finish();
    }

    @OnClick(R.id.shop_scan_code)
    void goScanCodeActivity() {
        //ToastUtil.makeText(this, "进入扫码界面", Toast.LENGTH_SHORT).show();
        BaseTool.goActivityNoData(this, ScanCodeActivity.class);
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

    @OnClick(R.id.shop_area_allgoods)
    void goAllGoodsActivity() {
        BaseTool.goActivityNoData(this, AllGoodsActivity.class);
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

    @Override
    public void onCountdowning(String hh, String mm, String ss) {

        setMiashaTime(hh, mm, ss);
    }

    @Override
    public void onCountDownfinish(String hh, String mm, String ss) {
        setMiashaTime(hh, mm, ss);
    }

    private void setMiashaTime(String h, String m, String s) {
        textViewHH.setText(h);
        textViewMM.setText(m);
        textViewSS.setText(s);
    }
}
