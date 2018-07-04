package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.advert.AdvertInfo;
import com.mhky.dianhuotong.advert.AdvertMainIF;
import com.mhky.dianhuotong.advert.AdvertMainPresenter;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.activity.AllGoodsActivity;
import com.mhky.dianhuotong.shop.activity.BrandActivity;
import com.mhky.dianhuotong.shop.activity.CouponActivity;
import com.mhky.dianhuotong.shop.activity.GoodsActivity;
import com.mhky.dianhuotong.shop.activity.RecommendActivity;
import com.mhky.dianhuotong.shop.activity.VipShopActivity;
import com.mhky.dianhuotong.shop.adapter.RecommendGoodsAdapter;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.adapter.ShopMiaoShaAdapter;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.LastMinuteGoodsInfo;
import com.mhky.dianhuotong.shop.bean.RecommendBean;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.LastMinutePresenter;
import com.mhky.dianhuotong.shop.precenter.RecommentGoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.ShopInfoPresenter;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.LastMinuteIF;
import com.mhky.dianhuotong.shop.shopif.RecommentIF;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;
import com.mhky.dianhuotong.shop.tool.TimerMiaoSha;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class DianHuoTongShopActivity extends BaseActivity implements OnBannerListener, TimerMiaoSha.TimerMiaoShaListener, AdvertMainIF, RecommentIF, GoodsIF, LastMinuteIF {
    @BindView(R.id.dht_main)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    @BindView(R.id.banner_main_accordion)
    BGABanner bgaBanner;
    @BindView(R.id.shop_listview)
    RecyclerView recyclerViewRecommend;
    @BindView(R.id.shop_recycleview)
    RecyclerView recyclerView;
    @BindView(R.id.shop_hh)
    TextView textViewHH;
    @BindView(R.id.shop_mm)
    TextView textViewMM;
    @BindView(R.id.shop_ss)
    TextView textViewSS;
    @BindView(R.id.shop_bot)
    TextView textViewBot;
    private Context mContext;
    private ShopMiaoShaAdapter shopMiaoShaAdapter;
    private static final String TAG = "DianHuoTongShopActivity";
    private TimerMiaoSha timerMiaoSha;
    private ShopInfoPresenter shopInfoPresenter;
    private AdvertMainPresenter advertMainPresenter;
    private List<AdvertInfo> advertInfoList;
    private SimpleDateFormat simpleDateFormat;
    private RecommentGoodsPrecenter recommentGoodsPrecenter;
    private RecommendGoodsAdapter recommendGoodsAdapter;
    private RecommendBean recommendBean;
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private CartPopupwindow cartPopupwindow;
    private SearchGoodsAdpter searchGoodsAdpter;
    private LastMinutePresenter lastMinutePresenter;
    private LastMinuteGoodsInfo lastMinuteGoodsInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_huo_tong_shop);
        ButterKnife.bind(this);
        mContext = this;
        try {
            inIt();
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

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
        goodsPrecenter = new GoodsPrecenter(this);
        advertMainPresenter = new AdvertMainPresenter(this);
        lastMinutePresenter = new LastMinutePresenter().setLastMinuteIF(this);
        HttpParams httpParams1 = new HttpParams();
        lastMinutePresenter.getLastMinute(httpParams1,this);
        advertMainPresenter.getAdvertShopMain();
        shopInfoPresenter = new ShopInfoPresenter();
        dianHuoTongShopTitleBar.setActivity(this);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecommend.setLayoutManager(linearLayoutManager1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setAdapter(shopMiaoShaAdapter);
        timerMiaoSha = new TimerMiaoSha(3600000, 1000, this);
        timerMiaoSha.start();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String dateNew = simpleDateFormat.format(date);
        HttpParams httpParams = new HttpParams();
        httpParams.put("startDate", dateNew);
        httpParams.put("endDate", dateNew);
        httpParams.put("shelves", true);
        httpParams.put("offShelves", false);
        httpParams.put("auditStatus", "APPROVED");
        recommentGoodsPrecenter = new RecommentGoodsPrecenter().setRecommentIF(this);
        recommentGoodsPrecenter.getRecommentGoods(httpParams);
    }


    @OnClick(R.id.shop_area_allgoods)
    void goAllGoodsActivity() {
        BaseTool.goActivityNoData(this, AllGoodsActivity.class);
    }

    @OnClick(R.id.shop_area_vipshop)
    void goAllCompany() {
        BaseTool.goActivityNoData(this, VipShopActivity.class);
    }

    @OnClick(R.id.shop_brand_area)
    void goBrand() {
        BaseTool.goActivityNoData(this, BrandActivity.class);
    }

    @OnClick(R.id.shop_go_recommend_rl)
    void goRecommendActivity() {
        BaseTool.goActivityNoData(this, RecommendActivity.class);
    }

    @OnClick(R.id.shop_area_bestgoods)
    void goCouponActivity() {
        BaseTool.goActivityNoData(this, CouponActivity.class);
    }

    //初始化轮播图
    private void initImageBaner(List<?> list) {
        try {
            if (list!=null&&list.size()>0){
                bgaBanner.setData(list, new ArrayList<String>());
                bgaBanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        AdvertInfo advertInfo = (AdvertInfo) model;
                        Uri uri = Uri.parse(advertInfo.getImage());
                        Picasso.get().load(uri).into((ImageView) itemView);
                    }
                });

                bgaBanner.setAutoPlayAble(true);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
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

    @Override
    public void getAdvertMainSuccess(int code, String result) {
        if (code == 200) {
            advertInfoList = JSON.parseArray(result, AdvertInfo.class);
            if (advertInfoList!=null&&advertInfoList.size()>0){
                initImageBaner(advertInfoList);
            }

        }
    }

    @Override
    public void getAdvertMainFailed(int code, String result) {

    }

    @Override
    public void getRecommentSucess(int code, String result) {
        try {
            if (code == 200) {
                recommendBean = JSON.parseObject(result, RecommendBean.class);
                if (recommendBean != null) {
                    if (recommendBean.getContent().size() < 6) {
                        recommendGoodsAdapter = new RecommendGoodsAdapter(mContext, recommendBean.getContent());
                    } else {
                        List<RecommendBean.ContentBean> contentBeanList = recommendBean.getContent().subList(0, 6);
                        recommendGoodsAdapter = new RecommendGoodsAdapter(mContext, contentBeanList);
                    }
                    recommendGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            try {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("id", recommendBean.getContent().get(position).getId() + "");
                                BaseTool.goActivityWithData(mContext, GoodsActivity.class, bundle);
                            } catch (Exception e) {
                                PgyCrashManager.reportCaughtException(mContext, e);
                            }

                            //ToastUtil.makeText(mContext, "点击了父控件", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recommendGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            try {
                                BaseTool.logPrint(TAG, "onItemChildClick: ---" + position);
                                goodsPrecenter.getGoodsInfo(String.valueOf(recommendBean.getContent().get(position).getId()));
                            } catch (Exception e) {
                                PgyCrashManager.reportCaughtException(mContext, e);
                            }


                        }
                    });
                    recyclerViewRecommend.setAdapter(recommendGoodsAdapter);
                    recyclerViewRecommend.setHasFixedSize(true);
                    recyclerViewRecommend.setNestedScrollingEnabled(false);
                    textViewBot.setVisibility(View.VISIBLE);
                } else {
                    textViewBot.setVisibility(View.GONE);
                }

            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getRecommentFaild(int code, String result) {

    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    if (cartPopupwindow != null && cartPopupwindow.isShowing()) {
                        cartPopupwindow.dismiss();
                    }
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    cartPopupwindow = new CartPopupwindow(this, goodsInfo);
                    cartPopupwindow.showAtLocation(dianHuoTongShopTitleBar, Gravity.BOTTOM, 0, 0);
                    //ToastUtil.makeText(mContext, searchSGoodsBean.getContent().get(position).getName(), Toast.LENGTH_SHORT).show();
                } else {
                    ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {
        ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getLastMinuteSuccess(int code, String result) {
        try {
            if (code == 200) {
                lastMinuteGoodsInfo = JSON.parseObject(result, LastMinuteGoodsInfo.class);
                shopMiaoShaAdapter = new ShopMiaoShaAdapter(lastMinuteGoodsInfo.getContent());
                shopMiaoShaAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.last_minute_goods:
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("id", lastMinuteGoodsInfo.getContent().get(position).getId() + "");
                                    BaseTool.goActivityWithData(mContext, GoodsActivity.class, bundle);
                                } catch (Exception e) {
                                    PgyCrashManager.reportCaughtException(mContext, e);
                                }
                                break;
                        }
                    }
                });
                recyclerView.setAdapter(shopMiaoShaAdapter);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        }

    }

    @Override
    public void getLastMinuteFailed(int code, String result) {
        ToastUtil.makeText(mContext, "部分信息加载异常！", Toast.LENGTH_SHORT).show();
    }
}
