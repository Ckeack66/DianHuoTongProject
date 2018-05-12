package com.mhky.dianhuotong.shop.adapter;

import android.content.Context;
import android.opengl.Visibility;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.CartInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class CartAdapter extends BaseSectionQuickAdapter<CartInfo, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    private Context mContext;
    private int withResult;
    private int heightResult;

    public CartAdapter(int layoutResId, int sectionHeadResId, List<CartInfo> data, Context context) {
        super(layoutResId, sectionHeadResId, data);
        mContext = context;
        initW2H();
    }

    @Override
    protected void convertHead(BaseViewHolder helper, CartInfo item) {
        if (item.getCartTitleInfo().isViewTab()) {
            helper.setVisible(R.id.cart_head_tab, false);
        } else {
            helper.setVisible(R.id.cart_head_tab, true);
        }
        if (item.getCartTitleInfo().isSelectTitle()) {
            helper.setChecked(R.id.cart_head_check1, true);
        } else {
            helper.setChecked(R.id.cart_head_check1, false);
        }
        helper.setText(R.id.cart_head_name, item.getCartTitleInfo().getShopDTOBean().getShopName());
        helper.addOnClickListener(R.id.cart_head_check);
        helper.addOnClickListener(R.id.cart_head_go);
    }

    @Override
    protected void convert(BaseViewHolder helper, CartInfo item) {
        if (item.getCartBodyBaseInfo().isSelectChild()) {
            helper.setChecked(R.id.cart_body_check1, true);
        } else {
            helper.setChecked(R.id.cart_body_check1, false);
        }
        helper.setText(R.id.goods_base_title, item.getCartBodyBaseInfo().getGoodsItemsBean().getTitle());
        helper.setText(R.id.goods_base__companay, item.getCartBodyBaseInfo().getGoodsItemsBean().getManufacturer());
        double a = (double)item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getRetailPrice();
        double money=a/100;
        helper.setText(R.id.goods_base_money, "￥" + money);
        helper.setText(R.id.cart_popup_numbers, item.getCartBodyBaseInfo().getGoodsItemsBean().getAmount() + "");
        String url = item.getCartBodyBaseInfo().getGoodsItemsBean().getPicture().split(",")[0];
        Picasso.with(mContext).load(url).resize(withResult, heightResult).into((ImageView) helper.getView(R.id.goods_base_imageview));
        if (item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().size() == 1) {
            helper.setText(R.id.goods_base_type, item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(0).getValue());
        } else {
            String name = "";
            String value = "";
            for (int b = 0; b < item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().size(); b++) {
                if (item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(b).getName().equals("规格")) {
                    name = item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(b).getValue();
                } else {
                    value = value + item.getCartBodyBaseInfo().getGoodsItemsBean().getSkuDTO().getSalePropertyOptions().get(b).getValue();
                }
            }
            helper.setText(R.id.goods_base_type, name + "/" + value);

        }
        helper.addOnClickListener(R.id.cart_body_check);
        helper.addOnClickListener(R.id.cart_popup_plus);
        helper.addOnClickListener(R.id.cart_popup_reduce);
    }

    private void initW2H() {
        float width = mContext.getResources().getDimension(R.dimen.x84);
        withResult = BaseTool.dip2px(mContext, width);
        float height = mContext.getResources().getDimension(R.dimen.x84);
        heightResult = BaseTool.dip2px(mContext, height);
    }
}