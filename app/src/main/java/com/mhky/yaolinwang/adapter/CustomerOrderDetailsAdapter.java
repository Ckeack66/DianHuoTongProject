package com.mhky.yaolinwang.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.yaolinwang.order.bean.CustomerOrderBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator  on  2018/10/19
 * Describe：订单详情页 商品Adapter
 */
public class CustomerOrderDetailsAdapter extends BaseQuickAdapter<CustomerOrderBean.ContentBean.OrderDetailSetBean,BaseViewHolder> {

    private Context mContexts;
    private int withResult;
    private int heightResult;

    public CustomerOrderDetailsAdapter(@Nullable List<CustomerOrderBean.ContentBean.OrderDetailSetBean> data,Context context) {
        super(R.layout.item_customer_order_body,data);
        mContexts = context;
        initW2H();
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerOrderBean.ContentBean.OrderDetailSetBean item) {
        if (!BaseTool.isEmpty(item.getProductIcon())) {
            String url = item.getProductIcon().split(",")[0];
            ImageView iv = helper.getView(R.id.iv_good);
            Picasso.get().load(url).error(R.drawable.default_pill_case).into(iv);
        }
        helper.setText(R.id.order_body_title, item.getProductName());
        helper.setText(R.id.tv_good_model, item.getSpec());
        helper.setText(R.id.tv_good_amount, "×" + item.getProductQuantity());
        BaseTool.logPrint("适配器",item.getProductPrice() + "----" + item.getProductPrice()/100);
        helper.setText(R.id.order_body_money, "￥ " + (float)(item.getProductPrice())/100);
        helper.addOnClickListener(R.id.rl_customer_order_body);
    }

    private void initW2H() {
        float width = mContexts.getResources().getDimension(R.dimen.x84);
        withResult = BaseTool.dip2px(mContexts, width);
        float height = mContexts.getResources().getDimension(R.dimen.x84);
        heightResult = BaseTool.dip2px(mContexts, height);
    }
}
