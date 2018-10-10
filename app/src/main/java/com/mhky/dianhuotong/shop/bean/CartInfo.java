package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2018/4/28.
 * 购物车商品实体类  Ⅰ
 */

public class CartInfo extends SectionEntity<CartBodyInfo> {

    private CartTitleInfo cartTitleInfo;
    private CartBodyInfo cartBodyBaseInfo;

    public CartTitleInfo getCartTitleInfo() {
        return cartTitleInfo;
    }

    public void setCartTitleInfo(CartTitleInfo cartTitleInfo) {
        this.cartTitleInfo = cartTitleInfo;
    }



    public CartBodyInfo getCartBodyBaseInfo() {
        return cartBodyBaseInfo;
    }

    public CartInfo(boolean isHeader, String header, CartTitleInfo cartTitleInfo1) {
        super(isHeader, header);
        cartTitleInfo = cartTitleInfo1;
    }

    public CartInfo(CartBodyInfo cartBodyInfo) {
        super(cartBodyInfo);
        cartBodyBaseInfo = cartBodyInfo;
    }
}
