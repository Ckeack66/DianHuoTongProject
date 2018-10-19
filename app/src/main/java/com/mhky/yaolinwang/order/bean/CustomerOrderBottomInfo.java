package com.mhky.yaolinwang.order.bean;

/**
 * Created by Administrator  on  2018/10/17
 * Describe：
 */
public class CustomerOrderBottomInfo {
    private CustomerOrderBean.ContentBean contentBean;
    private int goodsNum;
    private int goodsMoney;
    private String orderState;

    public CustomerOrderBean.ContentBean getContentBean() {
        return contentBean;
    }

    public void setContentBean(CustomerOrderBean.ContentBean contentBean) {
        this.contentBean = contentBean;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public int getGoodsMoney() {
        return goodsMoney;
    }

    public void setGoodsMoney(int goodsMoney) {
        this.goodsMoney = goodsMoney;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
