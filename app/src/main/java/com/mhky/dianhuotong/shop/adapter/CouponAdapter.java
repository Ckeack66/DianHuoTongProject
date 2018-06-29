package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.BaseCouponInfo;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class CouponAdapter extends BaseQuickAdapter<ShopCouponInfo, BaseViewHolder> {
    private Context mContext;

    public CouponAdapter(@Nullable List<ShopCouponInfo> data, Context context) {
        super(R.layout.item_coupon, data);
        mContext = context;
    }


    @Override
    protected void convert(BaseViewHolder helper, ShopCouponInfo item) {
        try {
            helper.setText(R.id.coupon_cut_money, item.getGradientFullCut().getSignal().getCutPrice() / 100 + "");
            helper.setText(R.id.name_2, "滿" + item.getGradientFullCut().getSignal().getFullAmount() / 100 + "減" + item.getGradientFullCut().getSignal().getCutPrice() / 100);
            helper.setText(R.id.coupon_time, "使用期限：" + item.getStartDate() + "  ~  " + item.getEndDate());
            helper.addOnClickListener(R.id.coupon_get);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        }

    }
}
