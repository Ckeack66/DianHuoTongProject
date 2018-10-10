package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.List;

/**
 * 店铺优惠券Adapter
 */

public class ShopCouponAdapter extends BaseQuickAdapter<ShopCouponInfo, BaseViewHolder> {
    private Context mContext;

    public ShopCouponAdapter(@Nullable List<ShopCouponInfo> data, Context context) {
        super(R.layout.item_shop_coupon, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopCouponInfo item) {
        try {
            helper.setText(R.id.coupon_money_number, String.valueOf(item.getGradientFullCut().getSignal().getCutPrice() / 100));
            helper.setText(R.id.coupon_money_number_txt, "满" + String.valueOf(item.getGradientFullCut().getSignal().getFullAmount() / 100 )+ "元可使用");
            helper.addOnClickListener(R.id.shop_coupon);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        }

    }
}
