package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 *  展示所有商品    列表的   adapter
 */

public class SearchGoodsAdpter extends BaseQuickAdapter<SearchSGoodsBean.ContentBean, BaseViewHolder> {

    private Context context;

    public SearchGoodsAdpter(@Nullable List<SearchSGoodsBean.ContentBean> data, Context context1) {
        super(R.layout.item_goods_base, data);
        context = context1;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
//        return position;
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchSGoodsBean.ContentBean item) {
        /**
         * 如果不想RecyclerView中的Item复用，而是每次都重新显示，只需加上以下这句话：
         * false - 禁止复用 true-可以复用
         * 此处设置不可以复用，我感觉这个地方有bug   每次都是在这被卡主，设置true，有些都显示不全
         */
        helper.setIsRecyclable(false);

        if (!BaseTool.isEmpty(item.getPicture())) {
            String[] imageDate = item.getPicture().split(",");
            Picasso.get().load(imageDate[0]).error(R.drawable.default_pill_case).into((ImageView) helper.getView(R.id.goods_base_imageview));
        }
        if (item.getTitle() != null) {
            helper.setText(R.id.goods_base_title, item.getTitle());
        }
        if (item.getManufacturer() != null) {
            helper.setText(R.id.goods_base__companay, item.getManufacturer());
        }
        if (item.getShopInfo() != null && item.getShopInfo().getShopName() != null) {
            helper.setText(R.id.goods_base_shop_name, item.getShopInfo().getShopName());
        }
        double a=item.getPrice();
        NumberFormat df = new DecimalFormat("0.00");
        helper.setText(R.id.goods_base_money, "￥"+df.format(a / 100) );
        helper.addOnClickListener(R.id.goods_base_addcart_button);

    }
}
