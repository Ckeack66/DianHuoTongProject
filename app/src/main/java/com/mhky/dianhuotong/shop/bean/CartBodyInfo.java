package com.mhky.dianhuotong.shop.bean;

/**
 * Created by Administrator on 2018/4/28.
 */

public class CartBodyInfo {
    public int getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(int parentNumber) {
        this.parentNumber = parentNumber;
    }

    public boolean isSelectChild() {
        return isSelectChild;
    }

    public void setSelectChild(boolean selectChild) {
        isSelectChild = selectChild;
    }

    private int parentNumber = -1;

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }

    private int childNumber = -1;
    private boolean isSelectChild = false;

    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int childIndex) {
        this.childIndex = childIndex;
    }
    private int childIndex=-1;

    public CartBaseInfo.GoodsItemsBean getGoodsItemsBean() {
        return goodsItemsBean;
    }

    public void setGoodsItemsBean(CartBaseInfo.GoodsItemsBean goodsItemsBean) {
        this.goodsItemsBean = goodsItemsBean;
    }

    private CartBaseInfo.GoodsItemsBean goodsItemsBean;
}
