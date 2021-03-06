package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.SearchCompanyAdapter;
import com.mhky.dianhuotong.shop.bean.SearchCompanyInfo;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.precenter.SearchCompanyPresenter;
import com.mhky.dianhuotong.shop.shopif.SearchCompanyIF;
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

public class SearchCompanyActivity extends BaseActivity implements SearchCompanyIF {
    @BindView(R.id.search_company_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    @BindView(R.id.search_company_rv)
    RecyclerView recyclerView;
    @BindView(R.id.search_company_res)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.base_tips)
    RelativeLayout relativeLayoutTips;
    private SearchCompanyAdapter searchCompanyAdapter;
    private int pageNumber = 0;
    private int isNew = -1;
    private String type;
    private String companyType100;
    private SearchCompanyPresenter searchCompanyPresenter;
    private SearchCompanyInfo searchCompanyInfo;
    private Context context;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_company);
        ButterKnife.bind(this);
        context = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        bundle = getIntent().getExtras();
        searchCompanyPresenter = new SearchCompanyPresenter(this);
        dianHuoTongShopTitleBar.setActivity(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        setRefesh();
        if (bundle != null) {
            type = bundle.getString("type");
        }
        if (!TextUtils.isEmpty(type)) {
            if ("100".equals(type)) {
                //搜索店铺名
                companyType100 = getIntent().getExtras().getString("shopnm");
                getNameInfo();
            } else {
                getInfo();
            }
        } else {
            getInfo();
        }


    }

    private void setRefesh() {
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.setEnableLoadMore(true);
                pageNumber = 0;
                isNew = 0;
                if ("100".equals(type)) {
                    getNameInfo();
                } else {
                    getInfo();
                }
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isNew = 1;
                if ("100".equals(type)) {
                    getNameInfo();
                } else {
                    getInfo();
                }
            }
        });
    }

    private void getInfo() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", pageNumber);
        searchCompanyPresenter.getCompany(httpParams);
    }

    private void getNameInfo() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", pageNumber);
        httpParams.put("name", companyType100);
        searchCompanyPresenter.getCompany(httpParams);
    }

    @Override
    public void getCompanyListSuccess(int code, String result) {
        if (code == 200) {
            SearchCompanyInfo searchCompanyInfo1 = JSON.parseObject(result, SearchCompanyInfo.class);
            if (isNew == -1) {
                if (searchCompanyInfo1 != null && searchCompanyInfo1.getContent() != null && searchCompanyInfo1.getContent().size() == 0) {
                    relativeLayoutTips.setVisibility(View.VISIBLE);
                }
                searchCompanyInfo = searchCompanyInfo1;
                searchCompanyAdapter = new SearchCompanyAdapter(searchCompanyInfo.getContent(), context);
                searchCompanyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.go_company:
                                Bundle bundle=new Bundle();
                                bundle.putString("shopid", String.valueOf(searchCompanyInfo.getContent().get(position).getId()));
                                BaseTool.goActivityWithData(context,ShopActivity.class,bundle);
                                break;
                        }
                    }
                });
                recyclerView.setAdapter(searchCompanyAdapter);
                pageNumber = 1;
            } else if (isNew == 0) {
                if (searchCompanyInfo1 != null && searchCompanyInfo1.getContent() != null && searchCompanyInfo1.getContent().size() == 0) {
                    relativeLayoutTips.setVisibility(View.VISIBLE);
                } else {
                    relativeLayoutTips.setVisibility(View.GONE);
                }
                smartRefreshLayout.finishRefresh();
                searchCompanyInfo = searchCompanyInfo1;
                searchCompanyAdapter.setNewData(searchCompanyInfo.getContent());
                pageNumber++;
            } else if (isNew == 1) {
                smartRefreshLayout.finishLoadMore();
                if (searchCompanyInfo1.getContent().size() == 0) {
                    smartRefreshLayout.setEnableLoadMore(false);
                    ToastUtil.makeText(this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                } else if (searchCompanyInfo1.getContent().size() < 10) {
                    smartRefreshLayout.setEnableLoadMore(false);
                    searchCompanyAdapter.addData(searchCompanyInfo1.getContent());
                    ToastUtil.makeText(this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                } else {
                    pageNumber++;
                    searchCompanyAdapter.addData(searchCompanyInfo1.getContent());
                    ToastUtil.makeText(this, "加载了一批数据~", Toast.LENGTH_SHORT).show();
                }
            }


        } else {
            ToastUtil.makeText(this, "获取数据异常~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getCompanyListFailed(int code, String result) {
        ToastUtil.makeText(this, "服务器异常~" + code, Toast.LENGTH_SHORT).show();
    }
}
