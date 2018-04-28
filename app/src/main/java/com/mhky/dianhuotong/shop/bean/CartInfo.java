package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2018/4/28.
 */

public class CartInfo extends SectionEntity<CartBodyInfo> {
    public CartTitleInfo getCartTitleInfo() {
        return cartTitleInfo;
    }

    public void setCartTitleInfo(CartTitleInfo cartTitleInfo) {
        this.cartTitleInfo = cartTitleInfo;
    }



    private CartTitleInfo cartTitleInfo;

    public CartBodyInfo getCartBodyBaseInfo() {
        return cartBodyBaseInfo;
    }

    private CartBodyInfo cartBodyBaseInfo;

    public CartInfo(boolean isHeader, String header, CartTitleInfo cartTitleInfo1) {
        super(isHeader, header);
        cartTitleInfo = cartTitleInfo1;
    }

    public CartInfo(CartBodyInfo cartBodyInfo) {
        super(cartBodyInfo);
        cartBodyBaseInfo = cartBodyInfo;
    }
}
