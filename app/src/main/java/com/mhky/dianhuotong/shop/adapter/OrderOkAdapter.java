package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkInfo;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
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
        try {
            switch (helper.getItemViewType()) {
                case OrderOkInfo.TOP:
                    helper.setText(R.id.order_ok_head_name, item.getOrderOkTitleInfo().getShopDTOBean().getShopName());
                    break;
                case OrderOkInfo.CENTER:
                    if (item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().size() == 1) {
                        helper.setText(R.id.order_ok_goods_base_type, item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(0).getValue());
                    } else {
                        String name = "";
                        String value = "";
                        for (int b = 0; b < item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().size(); b++) {
                            if (item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(b).getName().equals("规格")) {
                                name = item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(b).getValue();
                            } else {
                                value = value + item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(b).getValue();
                            }
                        }
                        helper.setText(R.id.order_ok_goods_base_type, name + "/" + value);
                    }
                    helper.setText(R.id.order_ok_numbers, "x" + item.getOrderOkCenterInfo().getGoodsItemsBean().getAmount());
                    helper.setText(R.id.order_ok_body_title, item.getOrderOkCenterInfo().getGoodsItemsBean().getTitle());
                    String url = item.getOrderOkCenterInfo().getGoodsItemsBean().getPicture().split(",")[0];
                    Picasso.get().load(url).resize(withResult, heightResult).into((ImageView) helper.getView(R.id.order_body_imageview));
                    helper.setText(R.id.order_ok_body_companay, item.getOrderOkCenterInfo().getGoodsItemsBean().getManufacturer());
                    double a;
                    if (item.getOrderOkCenterInfo().getGoodsItemsBean().getAmount() > item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getBatchNums()) {
                        a = (double) item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getWholesalePrice();
                    } else {
                        a = (double) item.getOrderOkCenterInfo().getGoodsItemsBean().getSkuDTO().getRetailPrice();
                    }
                    BigDecimal bigDecimal = new BigDecimal(String.valueOf(a));
                    BigDecimal bigDecimal1 = new BigDecimal(String.valueOf(Double.valueOf(100)));
                    double money = bigDecimal.divide(bigDecimal1).doubleValue();
                    helper.setText(R.id.order_ok_money, "￥" + money);
                    break;
                case OrderOkInfo.BOTTOM:
                    helper.setText(R.id.order_ok_bto_yh, item.getOrderOkBotttomInfo().getyH());
                    helper.setText(R.id.order_ok_bto_words, item.getOrderOkBotttomInfo().getWords());
                    helper.setText(R.id.order_ok_bto_number, "共" + item.getOrderOkBotttomInfo().getGoodsNumber() + "件商品   小计：");
                    if (item.getOrderOkBotttomInfo().getCouponInfoList() != null && item.getOrderOkBotttomInfo().getCouponInfoList().size() > 0) {
                        helper.getView(R.id.order_ok_bto_select).setVisibility(View.VISIBLE);
                        if (item.getOrderOkBotttomInfo().getCouponInfo() != null) {
                            helper.setText(R.id.order_ok_bto_yh, "满" + item.getOrderOkBotttomInfo().getCouponInfo().getPromotionItem().getGradientFullCut().getFullAmount() / 100 + "减" + item.getOrderOkBotttomInfo().getCouponInfo().getPromotionItem().getGradientFullCut().getCutPrice() / 100);
                        }
                    } else {
                        helper.getView(R.id.order_ok_bto_select).setVisibility(View.GONE);
                    }
                    helper.addOnClickListener(R.id.order_ok_bto_select);
                    double b = (double) item.getOrderOkBotttomInfo().getMoney();
                    BigDecimal bigDecimal2 = new BigDecimal(String.valueOf(b));
                    BigDecimal bigDecimal3 = new BigDecimal(String.valueOf(Double.valueOf(100)));
                    double money1 = bigDecimal2.divide(bigDecimal3).doubleValue();
                    BigDecimal bigDecimal4 = new BigDecimal(String.valueOf(money1));
                    BigDecimal bigDecimal5 = new BigDecimal(String.valueOf(Double.valueOf(item.getOrderOkBotttomInfo().getFrigthInfo().getFreight().toString()) / 100));
                    if (item.getOrderOkBotttomInfo().getFrigthInfo() != null && item.getOrderOkBotttomInfo().getFrigthInfo().getSendAccount() != null && money1 < (Double.valueOf(item.getOrderOkBotttomInfo().getFrigthInfo().getSendAccount().toString()) / 100)) {
                        if (item.getOrderOkBotttomInfo().getCouponInfo() != null) {
                            BigDecimal bigDecimal6 = new BigDecimal(String.valueOf((double) item.getOrderOkBotttomInfo().getCouponInfo().getPromotionItem().getGradientFullCut().getCutPrice() / 100));
                            helper.setText(R.id.order_ok_bto_child_money, "￥" + bigDecimal4.add(bigDecimal5).subtract(bigDecimal6).doubleValue());
                        } else {
                            helper.setText(R.id.order_ok_bto_child_money, "￥" + bigDecimal4.add(bigDecimal5).doubleValue());
                        }
                        helper.setText(R.id.order_ok_frgit, "￥" + bigDecimal5.doubleValue());
                    } else {
                        if (item.getOrderOkBotttomInfo().getCouponInfo() != null) {
                            BigDecimal bigDecimal6 = new BigDecimal(String.valueOf((double) item.getOrderOkBotttomInfo().getCouponInfo().getPromotionItem().getGradientFullCut().getCutPrice() / 100));
                            helper.setText(R.id.order_ok_bto_child_money, "￥" + bigDecimal4.subtract(bigDecimal6).doubleValue());
                        } else {
                            helper.setText(R.id.order_ok_bto_child_money, "￥" + bigDecimal4.doubleValue());
                        }

                    }
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
                            BaseTool.logPrint(TAG, "afterTextChanged: ------正在输入第" + helper.getLayoutPosition() + "个留言框+" + s.toString());
                            if (getEditWordsListenner != null) {
                                getEditWordsListenner.getEditData(s.toString(), helper.getLayoutPosition());
                            }
                        }
                    });

                    break;
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContexts, e);
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
