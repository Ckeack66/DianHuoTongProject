package com.mhky.yaolinwang.adapter;

import android.content.Context;
import android.graphics.Color;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.yaolinwang.order.bean.CustomerOrder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator  on  2018/10/17
 * Describe：C 端订单列表适配器
 */
public class CustomerOrderAdapter extends BaseMultiItemQuickAdapter<CustomerOrder,BaseViewHolder> {

    private int withResult;
    private int heightResult;
    private Context mContexts;

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CustomerOrderAdapter(List<CustomerOrder> data,Context context) {
        super(data);
        mContexts = context;
        addItemType(OrderInfo.TOP, R.layout.item_customer_order_top);
        addItemType(OrderInfo.BODY, R.layout.item_customer_order_body);
        addItemType(OrderInfo.BOTTOM, R.layout.item_customer_order_bottom);
        initW2H();
    }

    @Override
    protected void convert(BaseViewHolder helper, CustomerOrder item) {
        helper.setIsRecyclable(false);
        switch (helper.getItemViewType()){
            case CustomerOrder.TOP:
                helper.setText(R.id.order_head_name,item.getCustomerOrderTopInfo().getName());
                switch (item.getCustomerOrderTopInfo().getState()){
                    case "ORDERED":
                        helper.setText(R.id.order_state, "待付款");
                        break;
                    case "PAID":
                        helper.setText(R.id.order_state, "待发货");
                        break;
                    case "SHIPPED":
                        helper.setText(R.id.order_state, "待收货");
                        break;
                    case "COMPLETED":
                        helper.setText(R.id.order_state, "待评价");
                        break;
                    case "CANCELLED":
                        helper.setText(R.id.order_state, "订单取消");
                        break;
                    default:
                        helper.setVisible(R.id.order_state,false);
                        break;
                }
                helper.addOnClickListener(R.id.rl_order_top);
                break;
            case CustomerOrder.BODY:
                if (!BaseTool.isEmpty(item.getOrderDetailSetBean().getProductIcon())) {
                    String url = item.getOrderDetailSetBean().getProductIcon().split(",")[0];
                    ImageView iv = helper.getView(R.id.iv_good);
                    Picasso.get().load(url).error(R.drawable.default_pill_case).into(iv);
                }
                helper.setText(R.id.order_body_title, item.getOrderDetailSetBean().getProductName());
                helper.setText(R.id.tv_good_model, item.getOrderDetailSetBean().getSpec());
                helper.setText(R.id.tv_good_amount, "×" + item.getOrderDetailSetBean().getProductQuantity());
                BaseTool.logPrint("适配器",item.getOrderDetailSetBean().getProductPrice() + "----" + item.getOrderDetailSetBean().getProductPrice()/100);
                helper.setText(R.id.order_body_money, "￥ " + (float)(item.getOrderDetailSetBean().getProductPrice())/100);
                helper.addOnClickListener(R.id.rl_customer_order_body);
                break;
            case CustomerOrder.BOTTOM:
                helper.setText(R.id.tv_goods_amount, String.valueOf(item.getCustomerOrderBottomInfo().getGoodsMoney()/100));
                helper.setText(R.id.tv_goods_count, "共" + item.getCustomerOrderBottomInfo().getGoodsNum());
                switch (item.getCustomerOrderBottomInfo().getOrderState()){
                    case "ORDERED":
                        helper.setText(R.id.btn_1, "去付款");
                        break;
                    case "PAID":
                        helper.setText(R.id.btn_1, "申请退款");
                        helper.setTextColor(R.id.btn_1, Color.parseColor("#333333"));
                        helper.setBackgroundRes(R.id.btn_1,R.drawable.tv_bg_grey);
                        break;
                    case "SHIPPED":
                        helper.setVisible(R.id.btn_2,true);
                        helper.setVisible(R.id.btn_3,true);
                        break;
                    case "COMPLETED":
                        helper.setText(R.id.btn_1, "去评价");
                        break;
                    case "CANCELLED":
                        helper.setText(R.id.btn_1, "订单已取消");
                        break;
                    case "AFTERSALESRETURN":
                        helper.setText(R.id.btn_1, "查看进度");
                        break;
                    default:
                        helper.setVisible(R.id.order_state,false);
                        break;
                }
                helper.addOnClickListener(R.id.btn_1);
                helper.addOnClickListener(R.id.btn_2);
                helper.addOnClickListener(R.id.btn_3);
                break;
        }
    }

    private void initW2H() {
        float width = mContexts.getResources().getDimension(R.dimen.x84);
        withResult = BaseTool.dip2px(mContexts, width);
        float height = mContexts.getResources().getDimension(R.dimen.x84);
        heightResult = BaseTool.dip2px(mContexts, height);
    }
}
