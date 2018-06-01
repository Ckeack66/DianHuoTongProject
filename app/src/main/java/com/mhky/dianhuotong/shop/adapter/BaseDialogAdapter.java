package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;

import java.util.List;

public class BaseDialogAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    private Context mContext;
    public BaseDialogAdapter(@Nullable List<String> data,Context context) {
        super(R.layout.item_coupon_dialog, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_coup,item);
        helper.addOnClickListener(R.id.item_coup);
    }
}
