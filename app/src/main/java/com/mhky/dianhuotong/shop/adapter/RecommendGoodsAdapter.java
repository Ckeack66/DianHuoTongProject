package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.RecommendBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommendGoodsAdapter extends BaseQuickAdapter<RecommendBean.ContentBean,BaseViewHolder> {
    private Context context;
    public RecommendGoodsAdapter(Context context1,@Nullable List<RecommendBean.ContentBean> data) {
        super(R.layout.item_goods_base, data);
        context = context1;
    }

    @Override
    protected void convert(BaseViewHolder helper, RecommendBean.ContentBean item) {
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
        helper.setText(R.id.goods_base_money, "￥"+(a / 100) );
        helper.addOnClickListener(R.id.goods_base_addcart_button);
    }
}
