package com.mhky.dianhuotong.shop.bean;

/**
 * Created by Administrator on 2018/4/21.
 */

public class Popuwindow1ChildInfo {
    String number;
    GoodsBaseInfo goodsBaseInfo;
    GoodsCategories goodsCategories;

    public GoodsCategories getGoodsCategories() {
        return goodsCategories;
    }

    public void setGoodsCategories(GoodsCategories goodsCategories) {
        this.goodsCategories = goodsCategories;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public GoodsBaseInfo getGoodsBaseInfo() {
        return goodsBaseInfo;
    }

    public void setGoodsBaseInfo(GoodsBaseInfo goodsBaseInfo) {
        this.goodsBaseInfo = goodsBaseInfo;
    }
}
