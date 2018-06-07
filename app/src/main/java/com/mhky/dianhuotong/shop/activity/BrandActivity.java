package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.BrandAdapter;
import com.mhky.dianhuotong.shop.adapter.SearchCompanyAdapter;
import com.mhky.dianhuotong.shop.bean.BrandInfo;
import com.mhky.dianhuotong.shop.bean.SearchCompanyInfo;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.precenter.BrandPresenter;
import com.mhky.dianhuotong.shop.shopif.BrandIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BrandActivity extends BaseActivity implements BrandIF {
    @BindView(R.id.brand_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    @BindView(R.id.base_layout_res)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.base_layout_rv)
    RecyclerView recyclerView;
    @BindView(R.id.base_layout_tips)
    RelativeLayout relativeLayoutTips;
    private BrandPresenter brandPresenter;
    private BrandAdapter brandAdapter;
    private BrandInfo brandInfo;
    private int pageNumber = 0;
    private int isNew = -1;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brand);
        ButterKnife.bind(this);
        context = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        dianHuoTongShopTitleBar.setActivity(this);
        brandPresenter=new BrandPresenter().setBrandIF(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        getInfo();
        initFresh();
    }
    private void getInfo() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", pageNumber);
        httpParams.put("size",20 );
        brandPresenter.getBrandInfo(httpParams);
    }

    private void initFresh() {
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                pageNumber = 0;
                isNew = 0;
                getInfo();
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                isNew = 1;
                getInfo();
            }
        });
    }

    @Override
    public void getBrandSuccess(int code, String result) {
        if (code == 200) {
            BrandInfo brandInfo1= JSON.parseObject(result, BrandInfo.class);
            if (isNew == -1) {
                if (brandInfo1 != null && brandInfo1.getContent() != null && brandInfo1.getContent().size() == 0) {
                    relativeLayoutTips.setVisibility(View.VISIBLE);
                }
                brandInfo = brandInfo1;
                brandAdapter = new BrandAdapter(brandInfo.getContent(), context);
                brandAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                        switch (view.getId()) {
//                            case R.id.go_company:
//                                Bundle bundle = new Bundle();
//                                bundle.putString("shopid", searchCompanyInfo.getContent().get(position).getId()+"");
//                                BaseTool.goActivityWithData(context, ShopActivity.class, bundle);
//                                break;
//                        }
                    }
                });
                recyclerView.setAdapter(brandAdapter);
                pageNumber = 1;
            } else if (isNew == 0) {
                if (brandInfo1 != null && brandInfo1.getContent() != null && brandInfo1.getContent().size() == 0) {
                    relativeLayoutTips.setVisibility(View.VISIBLE);
                } else {
                    relativeLayoutTips.setVisibility(View.GONE);
                }
                smartRefreshLayout.finishRefresh();
                brandInfo = brandInfo1;
                brandAdapter.setNewData(brandInfo.getContent());
                pageNumber++;
            } else if (isNew == 1) {
                smartRefreshLayout.finishLoadMore();
                if (brandInfo1.getContent().size() == 0) {
                    smartRefreshLayout.setEnableLoadMore(false);
                    ToastUtil.makeText(this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                } else if (brandInfo1.getContent().size() < 10) {
                    smartRefreshLayout.setEnableLoadMore(false);
                    brandAdapter.addData(brandInfo1.getContent());
                    ToastUtil.makeText(this, "没有更多数据了~", Toast.LENGTH_SHORT).show();
                } else {
                    pageNumber++;
                    brandAdapter.addData(brandInfo1.getContent());
                    ToastUtil.makeText(this, "加载了一批数据~", Toast.LENGTH_SHORT).show();
                }
            }


        } else {
            ToastUtil.makeText(this, "获取数据异常~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getBrandFailed(int code, String result) {

    }
}
