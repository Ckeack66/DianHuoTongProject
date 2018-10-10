package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/5/10.
 * 订单Adapter
 */

public class OrderAdapter extends BaseMultiItemQuickAdapter<OrderInfo, BaseViewHolder> {

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    private int withResult;
    private int heightResult;
    private Context mContexts;

    public OrderAdapter(List<OrderInfo> data, Context context) {
        super(data);
        mContexts = context;
        addItemType(OrderInfo.TOP, R.layout.item_order_top);
        addItemType(OrderInfo.BODY, R.layout.item_order_body);
        addItemType(OrderInfo.BOTTOM, R.layout.item_order_bottom);
        initW2H();
    }

    @Override
    protected void convert(BaseViewHolder helper, OrderInfo item) {
        helper.setIsRecyclable(false);
        switch (helper.getItemViewType()) {
            case OrderInfo.TOP:
                helper.setText(R.id.order_head_name, item.getOrderTopInfo().getName());
//                helper.addOnClickListener(R.id.order_head_go);
                helper.addOnClickListener(R.id.rl_order_top);
                break;
            case OrderInfo.BODY:
                if (!BaseTool.isEmpty(item.getOrderBodyInfo().getGoodsInfo().getImageUrl())) {
                    String url = item.getOrderBodyInfo().getGoodsInfo().getImageUrl().split(",")[0];
                    Picasso.get().load(url).resize(withResult, heightResult)
                            .error(R.drawable.default_pill_case).into((ImageView) helper.getView(R.id.order_body_imageview));
                }
                helper.setText(R.id.order_body_title, item.getOrderBodyInfo().getGoodsInfo().getName());
                //helper.setText(R.id.order_body_companay,item.getOrderBodyInfo().getGoodsInfo().)
                helper.setText(R.id.goods_base_type, item.getOrderBodyInfo().getGoodsInfo().getSpec());
                double a = (double) item.getOrderBodyInfo().getRealPrice();
                double money = a / 100;
                helper.setText(R.id.order_body_money, "￥" + money);
                helper.setText(R.id.order_body_goods_number, "x" + item.getOrderBodyInfo().getQuantity());
                helper.addOnClickListener(R.id.order_body_goods);
                break;
            case OrderInfo.BOTTOM:
                helper.setText(R.id.order_bottom_text1, "共" + item.getOrderBottomInfo().getAllGoodsNumber() + "件商品   ");
                double b = (double) item.getOrderBottomInfo().getMoney();
                double money1 = b / 100;
                helper.setText(R.id.order_bottom_text2, "合计：￥" + money1);
//                double c = (double) item.getOrderBottomInfo().getFreightInfoBean().getFreight();
//                double money2 = c / 100;
                if (item.getOrderBottomInfo().getFreightInfoBean().getFreight() != null
                        && Integer.valueOf(item.getOrderBottomInfo().getFreightInfoBean().getSendAccount().toString()) <= item.getOrderBottomInfo().getContentBean().getPayment()) {
                    helper.setText(R.id.order_bottom_text3, "（已免邮）");
                } else if (item.getOrderBottomInfo().getFreightInfoBean().getFreight() == null){
                    helper.setText(R.id.order_bottom_text3, "（运费未知）");
                }else {
                    helper.setText(R.id.order_bottom_text3, "（含运费￥" + Double.valueOf(item.getOrderBottomInfo().getFreightInfoBean().getFreight().toString())/100+"）");
                }
                switch (item.getOrderBottomInfo().getOrderStatus()) {
                    case "ORDERED":
                        helper.setText(R.id.order_info_button, "待付款");
                        break;
                    case "PAID":
                        helper.setText(R.id.order_info_button, "已付款");
                        break;
                    case "CONFIRMED":
                        helper.setText(R.id.order_info_button, "已确认");
                        break;
                    case "SHIPPED":
                        helper.setText(R.id.order_info_button, "已发货");
                        break;
                    case "COMPLETED":
                        helper.setText(R.id.order_info_button, "已完成");
                        break;
                    case "CANCELLED":
                        helper.setText(R.id.order_info_button, "已取消");
                        break;
                }
                helper.addOnClickListener(R.id.order_info_button);

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
