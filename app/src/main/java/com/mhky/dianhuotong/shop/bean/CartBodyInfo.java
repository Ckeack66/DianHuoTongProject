package com.mhky.dianhuotong.shop.bean;

/**
 * Created by Administrator on 2018/4/28.
 */

public class CartBodyInfo {

    private int parentNumber = -1;
    private int childNumber = -1;
    private boolean isSelectChild = false;
    private int childIndex=-1;
    private CartBaseInfo.GoodsItemsBean goodsItemsBean;


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

    public int getChildNumber() {
        return childNumber;
    }

    public void setChildNumber(int childNumber) {
        this.childNumber = childNumber;
    }
    public int getChildIndex() {
        return childIndex;
    }

    public void setChildIndex(int childIndex) {
        this.childIndex = childIndex;
    }

    public CartBaseInfo.GoodsItemsBean getGoodsItemsBean() {
        return goodsItemsBean;
    }

    public void setGoodsItemsBean(CartBaseInfo.GoodsItemsBean goodsItemsBean) {
        this.goodsItemsBean = goodsItemsBean;
    }
}
