package com.mhky.dianhuotong.shop.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.SearchCompanyInfo;

import java.util.List;

public class SearchCompanyAdapter extends BaseQuickAdapter<SearchCompanyInfo.ContentBean,BaseViewHolder> {
    public SearchCompanyAdapter(@Nullable List<SearchCompanyInfo.ContentBean> data) {
        super(R.layout.item_search_shop,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchCompanyInfo.ContentBean item) {

    }
}
