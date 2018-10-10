package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
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
import com.mhky.dianhuotong.shop.activity.SearchGoodsActivity;
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
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
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

/**
 * 典货通   模块主界面
 * 1.获取限时限量优惠的时候先请求下来有优惠的商品id，同时获取到活动时间，然后通过id，查询商品获取商品展示出来
 * 2.
 */


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
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private CartPopupwindow cartPopupwindow;
    private SearchGoodsAdpter searchGoodsAdpter;
    private LastMinutePresenter lastMinutePresenter;
    private LastMinuteGoodsInfo lastMinuteGoodsInfo;

    private SmartRefreshLayout srl_recommend;                           //SmartRefreshLayout
    private NestedScrollView nsvDianHuoTong;
    private int pageNum = 0;                                            //页码
    private int isFirst = 0;                                            //是否是第一次进入
    private RecommendBean recommendBean;                                //每页请求下来的包含商品的实体类
    private List<RecommendBean.ContentBean> list = new ArrayList<RecommendBean.ContentBean>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_huo_tong_shop);
        ButterKnife.bind(this);
        srl_recommend = findViewById(R.id.srl_recommend);
        nsvDianHuoTong = findViewById(R.id.nsv_dianhuotong);
        mContext = this;
        try {
            inIt();
            initListener();
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
        dianHuoTongShopTitleBar.setActivity(this);
        goodsPrecenter = new GoodsPrecenter(this);
        lastMinutePresenter = new LastMinutePresenter().setLastMinuteIF(this);
        advertMainPresenter = new AdvertMainPresenter(this);
        shopInfoPresenter = new ShopInfoPresenter();
        HttpParams httpParams1 = new HttpParams();
        lastMinutePresenter.getLastMinute(httpParams1,this);
        advertMainPresenter.getAdvertShopMain();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecommend.setLayoutManager(linearLayoutManager1);
        recyclerViewRecommend.setHasFixedSize(false);
        recyclerViewRecommend.setNestedScrollingEnabled(false);
        srl_recommend.setEnableRefresh(false);
        srl_recommend.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        recommendGoodsAdapter = new RecommendGoodsAdapter(this,list);
        recommendGoodsAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerViewRecommend.setAdapter(recommendGoodsAdapter);

        //recyclerView.setAdapter(shopMiaoShaAdapter);
        timerMiaoSha = new TimerMiaoSha(3600000, 1000, this);
        timerMiaoSha.start();

        recommentGoodsPrecenter = new RecommentGoodsPrecenter().setRecommentIF(this);
        getRecommendData(pageNum);
    }

    /**
     * 设置加载更多
     */
    private void initListener() {
        /**
         * 监听NestedScrollView的滑动事件，解决与recyclerview的滑动冲突
         */
        nsvDianHuoTong.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                BaseTool.logPrint(TAG + "ck1" ,
                        "scrollY =" + scrollY + "; 1hei =" + v.getChildAt(0).getMeasuredHeight() + "; 2hei =" +v.getMeasuredHeight());
                //判断是否滑到的底部
//                if (  scrollY <= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                if (  266 >= (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight() - scrollY)) {
                    BaseTool.logPrint(TAG + "ck2" ,
                            "scrollY =" + scrollY + "; 1hei =" + v.getChildAt(0).getMeasuredHeight() + "; 2hei =" +v.getMeasuredHeight());
                    srl_recommend.autoLoadMore();//调用刷新控件对应的加载更多方法
                }
            }
        });

        recommendGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("id", recommendGoodsAdapter.getData().get(position).getId() + "");
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
                    goodsPrecenter.getGoodsInfo(String.valueOf(recommendGoodsAdapter.getData().get(position).getId()));
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(mContext, e);
                }
            }
        });

        srl_recommend.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                isFirst = 1;
                getRecommendData(pageNum);
            }
        });
    }

    public void getRecommendData(int page){
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String dateNew = simpleDateFormat.format(date);
        HttpParams httpParams = new HttpParams();
        httpParams.put("startDate", dateNew);
        httpParams.put("endDate", dateNew);
        httpParams.put("page", page);
        httpParams.put("size", 10);
        httpParams.put("shelves", true);
        httpParams.put("offShelves", false);
        httpParams.put("auditStatus", "APPROVED");
        recommentGoodsPrecenter.getRecommentGoods(httpParams);
    }

    @OnClick(R.id.shop_area_allgoods)
    void goAllGoodsActivity() {
        BaseTool.goActivityNoData(this, AllGoodsActivity.class);
    }

    @OnClick(R.id.shop_area_vipshop)
    void goAllCompany() {
        //上边为以前的界面，改为黄金单品后为下方界面
//        BaseTool.goActivityNoData(this, VipShopActivity.class);
        BaseTool.goActivityNoData(this, GoldGoodsActivity.class);
    }

    @OnClick(R.id.shop_brand_area)
    void goBrand() {
        //上边为以前的界面，改为整库专区后为下方界面
//        BaseTool.goActivityNoData(this, BrandActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("type", "104");
        bundle.putString("goodsnm", "");
        bundle.putString("shopnm", "");
        BaseTool.goActivityWithData(this, SearchGoodsActivity.class, bundle);
    }

    @OnClick(R.id.shop_go_recommend_rl)
    void goRecommendActivity() {
        BaseTool.goActivityNoData(this, RecommendActivity.class);
    }

    @OnClick(R.id.shop_area_bestgoods)
    void goCouponActivity() {
        //领券中心
        BaseTool.goActivityNoData(this, CouponActivity.class);
    }

    //初始化轮播图
    private void initImageBaner(List<?> list) {
        try {
            if (list != null && list.size() > 0){
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
                bgaBanner.setDelegate(new BGABanner.Delegate() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        BaseTool.logPrint(TAG,"点击了轮播图" + ((AdvertInfo)model).getType() + "--" + position);
                    }
                });
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

    /**
     * 获取banner成功
     * @param code
     * @param result
     */
    @Override
    public void getAdvertMainSuccess(int code, String result) {
        if (code == 200) {
            advertInfoList = JSON.parseArray(result, AdvertInfo.class);
            if (advertInfoList != null && advertInfoList.size() > 0){
                initImageBaner(advertInfoList);
            }
        }
    }

    @Override
    public void getAdvertMainFailed(int code, String result) {

    }

    /**
     * 获取每日推荐药品成功
     * @param code
     * @param result
     */
    @Override
    public void getRecommentSucess(int code, String result) {
        try {
            if (code == 200) {
                recommendBean = JSON.parseObject(result, RecommendBean.class);
                if (isFirst == 0) {
                    if (recommendBean != null && recommendBean.getContent().size() > 0) {
//                        recommendGoodsAdapter = new RecommendGoodsAdapter(mContext, recommendBean.getContent());
                        if (recommendBean.getContent().size() < 10){
                            textViewBot.setVisibility(View.VISIBLE);
                            srl_recommend.setEnableLoadMore(false);
                        }
                    } else {
                        textViewBot.setVisibility(View.GONE);
                        srl_recommend.setEnableLoadMore(false);
                    }
                    recommendGoodsAdapter.setNewData(recommendBean.getContent());
                } else if(isFirst == 1){
                    if(recommendBean != null && recommendBean.getContent().size() == 0){                               //数据到结尾了，无数据了
                        srl_recommend.finishLoadMore(true);
                        srl_recommend.setEnableLoadMore(false);
                        textViewBot.setVisibility(View.VISIBLE);
                    }else if(recommendBean != null && recommendBean.getContent().size() < 10){                         //最后一页
                        srl_recommend.finishLoadMore(true);
                        recommendGoodsAdapter.addData(recommendBean.getContent());
                        srl_recommend.setEnableLoadMore(false);
                        textViewBot.setVisibility(View.VISIBLE);
                    }else {
                        srl_recommend.finishLoadMore(600, true, false);
                        recommendGoodsAdapter.addData(recommendBean.getContent());
                        textViewBot.setVisibility(View.GONE);
                    }
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
                    cartPopupwindow = new CartPopupwindow(this, goodsInfo, 2);
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

    /**
     * 获取限时限量优惠商品
     * @param code
     * @param result
     */
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
