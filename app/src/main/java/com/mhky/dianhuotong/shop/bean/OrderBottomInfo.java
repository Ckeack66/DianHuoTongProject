package com.mhky.dianhuotong.shop.bean;

/**
 * Created by Administrator on 2018/5/11.
 */

public class OrderBottomInfo {
    public int getChildNumbers() {
        return childNumbers;
    }

    public void setChildNumbers(int childNumbers) {
        this.childNumbers = childNumbers;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public OrderBaseInfo.ContentBean.FreightInfoBean getFreightInfoBean() {
        return freightInfoBean;
    }

    public void setFreightInfoBean(OrderBaseInfo.ContentBean.FreightInfoBean freightInfoBean) {
        this.freightInfoBean = freightInfoBean;
    }

    public int getAllGoodsNumber() {
        return allGoodsNumber;
    }

    public void setAllGoodsNumber(int allGoodsNumber) {
        this.allGoodsNumber = allGoodsNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    private String orderStatus;
    private int allGoodsNumber;
    private int childNumbers;
    private int money;
    private OrderBaseInfo.ContentBean.FreightInfoBean freightInfoBean;
}
