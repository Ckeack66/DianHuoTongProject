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
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.CouponAdapter;
import com.mhky.dianhuotong.shop.bean.BaseCouponInfo;
import com.mhky.dianhuotong.shop.bean.CouponInfo;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.mhky.dianhuotong.shop.precenter.CouponPresenter;
import com.mhky.dianhuotong.shop.shopif.CounponAddIF;
import com.mhky.dianhuotong.shop.shopif.CounponGetIF;
import com.mhky.dianhuotong.shop.shopif.CouponIF;
import com.mhky.dianhuotong.shop.shopif.HavedCouponIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 领券中心
 */

public class CouponActivity extends BaseActivity implements HavedCouponIF,CounponGetIF, CounponAddIF {

    @BindView(R.id.coupon_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.coupon_recyclerview)
    RecyclerView recyclerView;

    private CouponAdapter couponAdapter;
    private CouponPresenter couponPresenter;
    private List<ShopCouponInfo> shopCouponInfoList;
    private List<CouponInfo> couponInfoList;                                        //用户已领取的优惠券信息列表
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
        couponPresenter = new CouponPresenter().setCounponGetIF(this).setCounponAddIF(this).setHavedCouponIF(this);
        couponPresenter.getHavedCoupon();
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
    }

    @Override
    public void addCouponSuccess(int code, String result) {
        try {
            if (code == 201) {
                ToastUtil.makeText(mContext, "领取成功！", Toast.LENGTH_SHORT).show();
            } else {
                ToastUtil.makeText(mContext, result, Toast.LENGTH_SHORT).show();
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
        ToastUtil.makeText(mContext, "领取失败！", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取平台发布的优惠券列表
     * @param code
     * @param result
     */
    @Override
    public void getCouponSuccess(int code, String result) {
        if (code == 200) {
            shopCouponInfoList = JSON.parseArray(result, ShopCouponInfo.class);
            couponAdapter = new CouponAdapter(shopCouponInfoList, this, couponInfoList);
            couponAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
            couponAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    switch (view.getId()) {
                        case R.id.coupon_get:
                            //领取优惠券
                            BaseTool.logPrint("sign",couponAdapter.getData().get(position).isHaved() + "");
                            if (couponAdapter.getData().get(position).isHaved()){
                                ToastUtil.makeText(CouponActivity.this,"已领取过此优惠券",Toast.LENGTH_SHORT).show();
                                return;
                            }
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

    /**
     * 获取用户已领取的优惠券列表
     * @param code
     * @param result
     */
    @Override
    public void getHavedCouponSuccess(int code, String result) {
        if (code == 200){
            couponInfoList = JSON.parseArray(result,CouponInfo.class);
            Map map = new HashMap();
            couponPresenter.getCouponByPlatform(map);
        }
    }

    @Override
    public void getHavedCouponFailed(int code, String result) {

    }
}