package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

public class OrderOkInfo implements MultiItemEntity {

    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int BOTTOM = 3;
    private int type;
    private OrderOkTitleInfo orderOkTitleInfo;
    private OrderOkCenterInfo orderOkCenterInfo;
    private OrderOkBotttomInfo orderOkBotttomInfo;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public OrderOkInfo() {
    }

    public OrderOkTitleInfo getOrderOkTitleInfo() {
        return orderOkTitleInfo;
    }

    public void setOrderOkTitleInfo(OrderOkTitleInfo orderOkTitleInfo) {
        this.orderOkTitleInfo = orderOkTitleInfo;
    }

    public OrderOkCenterInfo getOrderOkCenterInfo() {
        return orderOkCenterInfo;
    }

    public void setOrderOkCenterInfo(OrderOkCenterInfo orderOkCenterInfo) {
        this.orderOkCenterInfo = orderOkCenterInfo;
    }

    public OrderOkBotttomInfo getOrderOkBotttomInfo() {
        return orderOkBotttomInfo;
    }

    public void setOrderOkBotttomInfo(OrderOkBotttomInfo orderOkBotttomInfo) {
        this.orderOkBotttomInfo = orderOkBotttomInfo;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
