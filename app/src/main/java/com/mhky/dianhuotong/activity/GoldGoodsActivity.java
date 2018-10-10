package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseActivityCK;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.goldgoods.GoldGoodDetailsPresenter;
import com.mhky.dianhuotong.goldgoods.GoldGoodDetailsView;
import com.mhky.dianhuotong.goldgoods.GoldGoodsPresenter;
import com.mhky.dianhuotong.goldgoods.GoldGoodsView;
import com.mhky.dianhuotong.shop.activity.GoodsActivity;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 黄金单品activity
 */

public class GoldGoodsActivity extends BaseActivityCK implements GoldGoodsView,GoldGoodDetailsView{

    @BindView(R.id.search_title)
    DianHuoTongShopTitleBar searchTitle;
    @BindView(R.id.search_recyclelistview)
    RecyclerView searchRecyclelistview;
    @BindView(R.id.search_text)
    TextView searchText;
    @BindView(R.id.base_tips)
    RelativeLayout baseTips;
    @BindView(R.id.goods_base_refresh)
    SmartRefreshLayout goodsBaseRefresh;

    private static final String TAG = "GoldGoodsActivity";
    private Context context;                        //上下文
    private List<BasePresenter> list_presenter = new ArrayList<>();         //activity内实体化的presenter
    private int pageNum = 0;                            //当前请求页码
    private SearchGoodsAdpter searchGoodsAdpter;            //展示商品的适配器
    private SearchSGoodsBean searchSGoodsBean;              //请求下来的商品类
    private List<SearchSGoodsBean.ContentBean> list = new ArrayList<SearchSGoodsBean.ContentBean>();
    private List<SearchSGoodsBean.ContentBean> temp_list = new ArrayList<SearchSGoodsBean.ContentBean>();
    private GoldGoodsPresenter goldGoodsPresenter;          //golegoods表示器
    private int isRefreshOrLoadMore = 0;                    //0:刷新    1：加载
    private boolean isFirst = true;                         //是否是第一次进来

    private GoldGoodDetailsPresenter goldGoodDetailsPresenter;                  //黄金单品商品详情 表示器
    private CartPopupwindow cartPopupwindow;                //添加商品popwindow
    private GoodsInfo goodsInfo;                            //黄金单品商品实体类


    @Override
    public List<BasePresenter> getPresenter() {
        return list_presenter;
    }

    @Override
    public void initPresenter() {
        list_presenter.clear();
        goldGoodsPresenter = new GoldGoodsPresenter();
        goldGoodDetailsPresenter = new GoldGoodDetailsPresenter();
        list_presenter.add(goldGoodsPresenter);
        list_presenter.add(goldGoodDetailsPresenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gold_goods);
        ButterKnife.bind(this);
        try {
//            goldGoodsPresenter.attachView(this);
            init();
            initListener();
            initData(pageNum,isRefreshOrLoadMore);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        goldGoodsPresenter.detachView();
    }

    /**
     * 初始化
     */
    private void init() {
        searchTitle.setActivity(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        searchRecyclelistview.setLayoutManager(linearLayoutManager);
        searchGoodsAdpter = new SearchGoodsAdpter(list,this);
        searchGoodsAdpter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        goodsBaseRefresh.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        goodsBaseRefresh.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        searchRecyclelistview.setAdapter(searchGoodsAdpter);

        searchGoodsAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("id", searchGoodsAdpter.getData().get(position).getId() + "");
                    bundle.putString("activitySign","1");
                    BaseTool.goActivityWithData(GoldGoodsActivity.this, GoodsActivity.class, bundle);
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(GoldGoodsActivity.this, e);
                }
//                ToastUtil.makeText(GoldGoodsActivity.this, searchGoodsAdpter.getData().get(position).getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        searchGoodsAdpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                try {
                    BaseTool.logPrint(TAG, "onItemChildClick: ---" + searchGoodsAdpter.getData().get(position).getTitle() + "------" + position);
                    goldGoodDetailsPresenter.getData(searchGoodsAdpter.getData().get(position).getId() + "");
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(GoldGoodsActivity.this, e);
                }

            }
        });
    }

    /**
     * 初始化点击事件
     */
    private void initListener() {
        goodsBaseRefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNum = 0;
                isFirst = false;
                isRefreshOrLoadMore = 0;
                refreshLayout.setEnableLoadMore(false);
                initData(pageNum,isRefreshOrLoadMore);
            }
        });
        goodsBaseRefresh.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                pageNum++;
                isFirst = false;
                isRefreshOrLoadMore = 1;
                refreshLayout.setEnableRefresh(false);
                initData(pageNum,isRefreshOrLoadMore);
            }
        });
    }

    /**
     * 请求数据
     */
    private void initData(int pageNum, int isRefreshOrLoadMore) {
        HttpParams httpParams = new HttpParams();
//        if(pageNum > 1){
//            httpParams.put("size",6);
//        }else {
            httpParams.put("size",10);
//        }
        httpParams.put("page",pageNum);
        httpParams.put("shopId",81);
        httpParams.put("shelves",true);
        httpParams.put("offShelves",false);
        httpParams.put("auditStatus", "APPROVED");
        goldGoodsPresenter.getData(httpParams, isRefreshOrLoadMore);
    }

    /**
     * 获取所有商品列表
     * @param data 数据源
     * @param isRefreshOrLoadMore
     */
    @Override
    public void showData(String data,int isRefreshOrLoadMore) {
        try {
            searchSGoodsBean = JSON.parseObject(data, SearchSGoodsBean.class);
            switch (isRefreshOrLoadMore){
                case 0:             //刷新
                    list.clear();
                    list = searchSGoodsBean.getContent();
                    if(searchSGoodsBean != null && list.size() == 0){                               //无数据
                        baseTips.setVisibility(View.VISIBLE);
                        goodsBaseRefresh.setEnableLoadMore(false);
                        goodsBaseRefresh.finishRefresh(600, true);
                    }else if(searchSGoodsBean != null && list.size() < 10){                         //最后一页
                        baseTips.setVisibility(View.GONE);
                        goodsBaseRefresh.setEnableLoadMore(false);
                        goodsBaseRefresh.finishRefresh(600, true);
                        if(!isFirst){
                            ToastUtil.makeText(this, "刷新成功-没有更多数据了", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        baseTips.setVisibility(View.GONE);
                        goodsBaseRefresh.setEnableLoadMore(true);
                        goodsBaseRefresh.finishRefresh(600, true);
                        if (!isFirst){
                            ToastUtil.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                    searchGoodsAdpter.setNewData(list);
                    break;
                case 1:             //加载
                    //不能增加下方语句，不让会让数据重复叠加
//                temp_list.clear();
//                temp_list = searchSGoodsBean.getContent();
//                for(int i = 0;i < temp_list.size(); i++){
//                    list.add(temp_list.get(i));
//                }
                    if(searchSGoodsBean != null && searchSGoodsBean.getContent().size() == 0){                               //数据到结尾了，无数据了
                        goodsBaseRefresh.finishLoadMore(true);
                        goodsBaseRefresh.setEnableLoadMore(false);
                        ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                    }else if(searchSGoodsBean != null && searchSGoodsBean.getContent().size() < 10){                         //最后一页
                        goodsBaseRefresh.finishLoadMore(true);
                        searchGoodsAdpter.addData(searchSGoodsBean.getContent());
                        goodsBaseRefresh.setEnableLoadMore(false);
                        ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                    }else {
                        goodsBaseRefresh.finishLoadMore(600, true, false);
                        searchGoodsAdpter.addData(searchSGoodsBean.getContent());
                        ToastUtil.makeText(this, "加载了更多", Toast.LENGTH_SHORT).show();
                    }
                    goodsBaseRefresh.setEnableRefresh(true);
                    break;
            }
        }catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
            ToastUtil.makeText(this, "系统异常~", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 请求黄金单品  商品详情成功
     * @param data 数据源
     */
    @Override
    public void getGoldGoodDetailsSuccess(String data) {
        try{
            if(!BaseTool.isEmpty(data)){
                goodsInfo = JSON.parseObject(data,GoodsInfo.class);
                BaseTool.logPrint(TAG + "_ck",goodsInfo.getId() + "");
                cartPopupwindow = new CartPopupwindow(this,goodsInfo,1);
                cartPopupwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                cartPopupwindow.showAtLocation(searchTitle, Gravity.BOTTOM,0,0);
            }else {
                ToastUtil.makeText(GoldGoodsActivity.this, "获取信息失败！", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void showToast(String msd) {
        super.showToast(msd);
        ToastUtil.makeText(this,msd, Toast.LENGTH_SHORT).show();
    }
}
