package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.database.SearchHistoryTable;

import java.util.List;

public class SearchHistoryAdapter extends BaseQuickAdapter<SearchHistoryTable, BaseViewHolder> {
    private Context contextSearch;

    public SearchHistoryAdapter(@Nullable List<SearchHistoryTable> data, Context context) {
        super(R.layout.item_search_history, data);
        contextSearch = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchHistoryTable item) {
        helper.addOnClickListener(R.id.item_click);
        if (item.getType() == 0) {
            //商品
            helper.setText(R.id.item_search_his_1, "商品");
            helper.setTextColor(R.id.item_search_his_1, contextSearch.getResources().getColor(R.color.color333333));
        } else if (item.getType() == 1) {
            //店铺
            helper.setText(R.id.item_search_his_1, "店铺");
            helper.setTextColor(R.id.item_search_his_1, contextSearch.getResources().getColor(R.color.color04c1ab));
        }
        helper.setText(R.id.item_search_his_2, item.getBody());
        helper.addOnClickListener(R.id.item_search_delete);
    }
}
