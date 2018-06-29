package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.CouponAdapter;
import com.mhky.dianhuotong.shop.bean.BaseCouponInfo;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.mhky.dianhuotong.shop.precenter.CouponPresenter;
import com.mhky.dianhuotong.shop.shopif.CounponAddIF;
import com.mhky.dianhuotong.shop.shopif.CounponGetIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CouponActivity extends BaseActivity implements CounponGetIF, CounponAddIF {
    @BindView(R.id.coupon_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.coupon_recyclerview)
    RecyclerView recyclerView;
    private CouponAdapter couponAdapter;
    private CouponPresenter couponPresenter;
    private List<ShopCouponInfo> shopCouponInfoList;
    private LoadingDialog loadingDialog;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        ButterKnife.bind(this);
        mContext = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        loadingDialog = new LoadingDialog(this);
        couponPresenter = new CouponPresenter().setCounponGetIF(this).setCounponAddIF(this);
        Map map = new HashMap();
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
                            try {
                                loadingDialog.show();
                                Map map = new HashMap();
                                map.put("promotionId", shopCouponInfoList.get(position).getId());
                                map.put("shopId", BaseApplication.getInstansApp().getPersonInfo().getShopId());
                                couponPresenter.bindCouponByShop(map);
                            } catch (Exception e) {
                                PgyCrashManager.reportCaughtException(mContext, e);
                            }
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

    @Override
    public void addCouponSuccess(int code, String result) {
        try {
            if (code == 201) {
                ToastUtil.makeText(mContext, "领取成功！", Toast.LENGTH_SHORT).show();
            } else {
                ToastUtil.makeText(mContext, "领取失败!" + code + result, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        } finally {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
        }
    }

    @Override
    public void addCouponFailed(int code, String result) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }
}
