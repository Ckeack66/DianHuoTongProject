package com.mhky.dianhuotong.shop.bean;

public class OrderOkCenterInfo {
    public CartBaseInfo.GoodsItemsBean getGoodsItemsBean() {
        return goodsItemsBean;
    }

    public void setGoodsItemsBean(CartBaseInfo.GoodsItemsBean goodsItemsBean) {
        this.goodsItemsBean = goodsItemsBean;
    }

    private CartBaseInfo.GoodsItemsBean goodsItemsBean;
}
