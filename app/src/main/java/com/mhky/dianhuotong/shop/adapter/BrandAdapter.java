package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.BrandInfo;

import java.util.List;

public class BrandAdapter extends BaseQuickAdapter<BrandInfo.ContentBean,BaseViewHolder>{
    public BrandAdapter(@Nullable List<BrandInfo.ContentBean> data, Context context) {
        super(R.layout.item_brand,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BrandInfo.ContentBean item) {
        helper.setText(R.id.brand_name,item.getName());
        helper.addOnClickListener(R.id.brand_go_search);
    }
}
