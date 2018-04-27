package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SearchGoodsAdpter extends BaseQuickAdapter<SearchSGoodsBean.ContentBean, BaseViewHolder> {
    private Context context;

    public SearchGoodsAdpter(@Nullable List<SearchSGoodsBean.ContentBean> data, Context context1) {
        super(R.layout.item_goods_base, data);
        context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchSGoodsBean.ContentBean item) {
        if (item.getPicture() != null) {
            String[] imageDate = item.getPicture().split(",");
            Picasso.with(context).load(imageDate[0]).into((ImageView) helper.getView(R.id.goods_base_imageview));
        }
        helper.setText(R.id.goods_base_title, item.getTitle())
                .setText(R.id.goods_base__companay, item.getManufacturer())
                .setText(R.id.goods_base_money, (item.getPrice() / 100) + "");
        helper.addOnClickListener(R.id.goods_base_addcart_button);

    }
}
