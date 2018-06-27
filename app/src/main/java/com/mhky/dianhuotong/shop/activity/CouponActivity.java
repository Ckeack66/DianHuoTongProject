package com.mhky.dianhuotong.shop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.CouponAdapter;
import com.mhky.dianhuotong.shop.bean.BaseCouponInfo;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.mhky.dianhuotong.shop.precenter.CouponPresenter;
import com.mhky.dianhuotong.shop.shopif.CounponGetIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponActivity extends BaseActivity implements CounponGetIF {
    @BindView(R.id.coupon_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.coupon_recyclerview)
    RecyclerView recyclerView;
    private CouponAdapter couponAdapter;
    private CouponPresenter couponPresenter;
    private List<ShopCouponInfo> shopCouponInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        couponPresenter = new CouponPresenter().setCounponGetIF(this);
        Map map=new HashMap();
        couponPresenter.getCouponByPlatform(map);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("领券中心");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        couponAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
    }

    @Override
    public void getCouponSuccess(int code, String result) {
        if (code == 200) {
            shopCouponInfoList = JSON.parseArray(result, ShopCouponInfo.class);
            couponAdapter = new CouponAdapter(shopCouponInfoList, this);
            couponAdapter.openLoadAnimation();
            couponAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.coupon_get:
                            //领取优惠券
                            break;
                    }
                }
            });
            recyclerView.setAdapter(couponAdapter);
        }
    }

    @Override
    public void getCouponFailed(int code, String result) {

    }
}
