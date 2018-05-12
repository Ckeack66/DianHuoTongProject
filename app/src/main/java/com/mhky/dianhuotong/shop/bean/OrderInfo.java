package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/5/10.
 */

public class OrderInfo implements MultiItemEntity {
    public static final int TOP = 1;
    public static final int BODY = 2;
    public static final int BOTTOM = 3;

    public OrderBottomInfo getOrderBottomInfo() {
        return orderBottomInfo;
    }

    public void setOrderBottomInfo(OrderBottomInfo orderBottomInfo) {
        this.orderBottomInfo = orderBottomInfo;
    }

    private OrderBottomInfo orderBottomInfo;
    public OrderBaseInfo.ContentBean.ItemsBean getOrderBodyInfo() {
        return orderBodyInfo;
    }

    public void setOrderBodyInfo(OrderBaseInfo.ContentBean.ItemsBean orderBodyInfo) {
        this.orderBodyInfo = orderBodyInfo;
    }

    private OrderBaseInfo.ContentBean.ItemsBean orderBodyInfo;
    public OrderTopInfo getOrderTopInfo() {
        return orderTopInfo;
    }

    public void setOrderTopInfo(OrderTopInfo orderTopInfo) {
        this.orderTopInfo = orderTopInfo;
    }

    private OrderTopInfo orderTopInfo;
    public int getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(int parentNumber) {
        this.parentNumber = parentNumber;
    }

    private int parentNumber;
    private int type;

    public OrderInfo(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

}
