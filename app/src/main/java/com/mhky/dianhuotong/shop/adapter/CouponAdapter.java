package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/9.
 */

public class CouponAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;

    public CouponAdapter(@Nullable List<String> data, Context context) {
        super(R.layout.item_coupon, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
