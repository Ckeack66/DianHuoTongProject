package com.mhky.dianhuotong.shop.adapter;

import android.opengl.Visibility;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.CartInfo;

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
    public CartAdapter(int layoutResId, int sectionHeadResId, List<CartInfo> data) {
        super(layoutResId, sectionHeadResId, data);
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
        helper.addOnClickListener(R.id.cart_body_check);
        helper.addOnClickListener(R.id.cart_popup_plus);
        helper.addOnClickListener(R.id.cart_popup_reduce);
    }
}
