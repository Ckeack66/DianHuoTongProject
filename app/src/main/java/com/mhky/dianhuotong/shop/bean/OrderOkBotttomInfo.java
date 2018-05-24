package com.mhky.dianhuotong.shop.bean;

import java.util.List;

public class OrderOkBotttomInfo {
    public OrderBaseInfo.ContentBean.FreightInfoBean getFreightInfoBean() {
        return freightInfoBean;
    }

    public void setFreightInfoBean(OrderBaseInfo.ContentBean.FreightInfoBean freightInfoBean) {
        this.freightInfoBean = freightInfoBean;
    }

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    private OrderBaseInfo.ContentBean.FreightInfoBean freightInfoBean;
    private String goodsNumber;
    private String words = "";

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    private int money;

    public String getyH() {
        return yH;
    }

    public void setyH(String yH) {
        this.yH = yH;
    }

    private String yH = "";


    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    private String parentId;

    public FrigthInfo getFrigthInfo() {
        return frigthInfo;
    }

    public void setFrigthInfo(FrigthInfo frigthInfo) {
        this.frigthInfo = frigthInfo;
    }

    private FrigthInfo frigthInfo;

    public CouponInfo getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(CouponInfo couponInfo) {
        this.couponInfo = couponInfo;
    }

    private CouponInfo couponInfo;

    public List<CouponInfo> getCouponInfoList() {
        return couponInfoList;
    }

    public void setCouponInfoList(List<CouponInfo> couponInfoList) {
        this.couponInfoList = couponInfoList;
    }

    private List<CouponInfo> couponInfoList;

    public boolean isShowCoupon() {
        return showCoupon;
    }

    public void setShowCoupon(boolean showCoupon) {
        this.showCoupon = showCoupon;
    }

    private boolean showCoupon=false;
}
