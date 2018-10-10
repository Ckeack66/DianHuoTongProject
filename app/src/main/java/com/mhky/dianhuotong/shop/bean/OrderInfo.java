package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2018/5/10.
 * 订单详情
 */

public class OrderInfo implements MultiItemEntity {

    public static final int TOP = 1;
    public static final int BODY = 2;
    public static final int BOTTOM = 3;

    private String parentNumber;
    private int type;

    private OrderTopInfo orderTopInfo;
    private OrderBaseInfo.ContentBean.ItemsBean orderBodyInfo;
    private OrderBottomInfo orderBottomInfo;


    public OrderBottomInfo getOrderBottomInfo() {
        return orderBottomInfo;
    }

    public void setOrderBottomInfo(OrderBottomInfo orderBottomInfo) {
        this.orderBottomInfo = orderBottomInfo;
    }
    public OrderBaseInfo.ContentBean.ItemsBean getOrderBodyInfo() {
        return orderBodyInfo;
    }

    public void setOrderBodyInfo(OrderBaseInfo.ContentBean.ItemsBean orderBodyInfo) {
        this.orderBodyInfo = orderBodyInfo;
    }

    public OrderTopInfo getOrderTopInfo() {
        return orderTopInfo;
    }
    public void setOrderTopInfo(OrderTopInfo orderTopInfo) {
        this.orderTopInfo = orderTopInfo;
    }

    public String getParentNumber() {
        return parentNumber;
    }

    public void setParentNumber(String parentNumber) {
        this.parentNumber = parentNumber;
    }

    public OrderInfo(int type) {
        this.type = type;
    }

    @Override
    public int getItemType() {
        return type;
    }

}
