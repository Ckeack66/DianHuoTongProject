package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderOkAdapter extends BaseMultiItemQuickAdapter<OrderOkInfo, BaseViewHolder> {
    private int withResult;
    private int heightResult;
    private Context mContexts;
    private GetEditWordsListenner getEditWordsListenner;
    private static final String TAG = "OrderOkAdapter";

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public OrderOkAdapter(List<OrderOkInfo> data, Context context, GetEditWordsListenner getEditWordsListenner1) {
        super(data);
        mContexts = context;
        getEditWordsListenner = getEditWordsListenner1;
        addItemType(OrderOkInfo.TOP, R.layout.item_order_ok_title);
        addItemType(OrderOkInfo.CENTER, R.layout.item_order_ok_center);
        addItemType(OrderOkInfo.BOTTOM, R.layout.item_order_ok_bottom);
        initW2H();
    }

    @Override
    protected void convert(final BaseViewHolder helper, OrderOkInfo item) {
        switch (helper.getItemViewType()) {
            case OrderOkInfo.TOP:
//                helper.setText(R.id.order_head_name, item.getOrderTopInfo().getName());
//                helper.addOnClickListener(R.id.order_head_go);
                break;
            case OrderOkInfo.CENTER:
//                if (item.getOrderBodyInfo().getGoodsInfo().getImageUrl() != null) {
//                    String url = item.getOrderBodyInfo().getGoodsInfo().getImageUrl().split(",")[0];
//                    Picasso.with(mContexts).load(url).resize(withResult, heightResult).into((ImageView) helper.getView(R.id.order_body_imageview));
//                }
//                helper.setText(R.id.order_body_title, item.getOrderBodyInfo().getGoodsInfo().getName());
//                //helper.setText(R.id.order_body_companay,item.getOrderBodyInfo().getGoodsInfo().)
//                helper.setText(R.id.goods_base_type, item.getOrderBodyInfo().getGoodsInfo().getSpec());
//                double a = (double) item.getOrderBodyInfo().getRealPrice();
//                double money = a / 100;
//                helper.setText(R.id.order_body_money, "￥" + money);
//                helper.setText(R.id.order_body_goods_number, "x" + item.getOrderBodyInfo().getQuantity());
//                helper.addOnClickListener(R.id.order_body_goods);
                break;
            case OrderOkInfo.BOTTOM:
//                helper.setText(R.id.order_bottom_text1, "共" + item.getOrderBottomInfo().getAllGoodsNumber() + "件商品   ");
//                double b = (double) item.getOrderBottomInfo().getMoney();
//                double money1 = b / 100;
//                helper.setText(R.id.order_bottom_text2, "合计：￥" + money1);
//                double c = (double) item.getOrderBottomInfo().getFreightInfoBean().getFreight();
//                double money2 = c / 100;
//                if (item.getOrderBottomInfo().getFreightInfoBean().getFreight() == 0) {
//                    helper.setText(R.id.order_bottom_text3, "（已免邮）");
//                } else {
//                    helper.setText(R.id.order_bottom_text3, "（含运费￥）" + money2);
//                }
//                switch (item.getOrderBottomInfo().getOrderStatus()) {
//                    case "ORDERED":
//                        helper.setText(R.id.order_info_button, "待付款");
//                        break;
//                    case "PAID":
//                        helper.setText(R.id.order_info_button, "已付款");
//                        break;
//                    case "COMPLETED":
//                        helper.setText(R.id.order_info_button, "已完成");
//                        break;
//                    case "CANCELLED":
//                        helper.setText(R.id.order_info_button, "已取消");
//                        break;
//                }
//                helper.addOnClickListener(R.id.order_info_button);
                helper.setText(R.id.order_ok_bto_words, item.getOrderOkBotttomInfo().getWords());
                EditText editText = helper.getView(R.id.order_ok_bto_words);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        Log.d(TAG, "afterTextChanged: ------正在输入第" + helper.getLayoutPosition()+ "个留言框");
                        if (getEditWordsListenner != null) {
                            getEditWordsListenner.getEditData(s.toString(), helper.getLayoutPosition());
                        }
                    }
                });
                break;
        }
    }

    private void initW2H() {
        float width = mContexts.getResources().getDimension(R.dimen.x84);
        withResult = BaseTool.dip2px(mContexts, width);
        float height = mContexts.getResources().getDimension(R.dimen.x84);
        heightResult = BaseTool.dip2px(mContexts, height);
    }

    public interface GetEditWordsListenner {
        void getEditData(String s, int position);
    }
}
