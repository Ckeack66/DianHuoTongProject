package com.mhky.dianhuotong.shop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.CouponInfo;

import java.util.List;

public class CouponDialogAdapter extends BaseQuickAdapter<CouponInfo, BaseViewHolder> {
    public CouponDialogAdapter(@Nullable List<CouponInfo> data) {
        super(R.layout.item_coupon_dialog, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CouponInfo item) {
        helper.addOnClickListener(R.id.item_coup);
        helper.setText(R.id.item_coup, "满" + String.valueOf(item.getPromotionItem().getGradientFullCut().getFullAmount()/100) + "减" + String.valueOf(item.getPromotionItem().getGradientFullCut().getCutPrice()/100));
    }
}
