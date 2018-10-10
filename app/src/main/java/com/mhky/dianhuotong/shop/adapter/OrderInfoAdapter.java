package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderInfoAdapter extends BaseQuickAdapter<OrderBaseInfo.ContentBean.ItemsBean,BaseViewHolder> {
    private Context mContexts;
    private int withResult;
    private int heightResult;
    public OrderInfoAdapter(@Nullable List<OrderBaseInfo.ContentBean.ItemsBean> data,Context context) {
        super(R.layout.item_order_ok_center,data);
        mContexts = context;
        initW2H();
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderBaseInfo.ContentBean.ItemsBean item) {
        helper.setText(R.id.order_ok_body_title,item.getGoodsInfo().getName());
        helper.setText(R.id.order_ok_body_companay,item.getGoodsInfo().getManufacturer());
        if(!BaseTool.isEmpty(item.getGoodsInfo().getImageUrl())){
            String url = item.getGoodsInfo().getImageUrl().split(",")[0];
            if(!BaseTool.isEmpty(url)){
                Picasso.get().load(url).resize(withResult,heightResult).error(R.drawable.default_pill_case).into((ImageView) helper.getView(R.id.order_body_imageview));
            }
        }
//        String url = item.getGoodsInfo().getImageUrl().split(",")[0];
//        Picasso.get().load(url).resize(withResult,heightResult).into((ImageView) helper.getView(R.id.order_body_imageview));
        helper.setText(R.id.order_ok_goods_base_type,item.getGoodsInfo().getSpec());
        helper.setText(R.id.order_ok_numbers,"x"+item.getQuantity());
        double a = (double) item.getRealPrice();
        double money = a / 100;
        helper.setText(R.id.order_ok_money,"￥" + money);
    }
    private void initW2H() {
        float width = mContexts.getResources().getDimension(R.dimen.x84);
        withResult = BaseTool.dip2px(mContexts, width);
        float height = mContexts.getResources().getDimension(R.dimen.x84);
        heightResult = BaseTool.dip2px(mContexts, height);
    }
}
