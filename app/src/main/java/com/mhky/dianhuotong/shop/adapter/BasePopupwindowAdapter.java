package com.mhky.dianhuotong.shop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;

import java.util.List;

public class BasePopupwindowAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public BasePopupwindowAdapter(@Nullable List<String> data) {
        super(R.layout.item_popupwindow_base,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
            helper.setText(R.id.popupwindow_base,item);
    }
}
