package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.StarCompanyAdapter;
import com.mhky.dianhuotong.shop.bean.StarCompanyInfo;
import com.mhky.dianhuotong.shop.precenter.StarShopPrecenter;
import com.mhky.dianhuotong.shop.shopif.StarShopGetIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StarCompanyActivity extends BaseActivity implements StarShopGetIF {
    @BindView(R.id.star_shop_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.star_shop_rv)
    RecyclerView recyclerView;
    @BindView(R.id.star_shop_tips)
    RelativeLayout relativeLayoutTips;
    private StarShopPrecenter starShopPrecenter;
    private List<StarCompanyInfo> starCompanyInfoList;
    private StarCompanyAdapter starCompanyAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star_company);
        ButterKnife.bind(this);
        mContext = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        starShopPrecenter = new StarShopPrecenter().setStarShopGetIF(this);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("收藏商家");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        starShopPrecenter.getStarShop();
    }

    @Override
    public void getStarShopSuccess(int code, String result) {
        try {
            if (code == 200) {
                starCompanyInfoList = JSON.parseArray(result, StarCompanyInfo.class);
                if (starCompanyInfoList != null && starCompanyInfoList.size() > 0) {
                    relativeLayoutTips.setVisibility(View.GONE);
                    starCompanyAdapter = new StarCompanyAdapter(starCompanyInfoList, this);
                    starCompanyAdapter.openLoadAnimation();
                    starCompanyAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            try {
                                switch (view.getId()) {
                                    case R.id.go_company:
                                        Bundle bundle = new Bundle();
                                        bundle.putString("shopid", starCompanyInfoList.get(position).getId() + "");
                                        BaseTool.goActivityWithData(mContext, ShopActivity.class, bundle);
                                        break;
                                }
                            } catch (Exception e) {
                                PgyCrashManager.reportCaughtException(mContext, e);
                            }
                        }
                    });
                    recyclerView.setAdapter(starCompanyAdapter);
                }else {
                    relativeLayoutTips.setVisibility(View.VISIBLE);
                }

            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getStarShopInfoFailed(int code, String result) {

    }
}
