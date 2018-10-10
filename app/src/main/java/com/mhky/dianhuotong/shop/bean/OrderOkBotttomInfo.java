package com.mhky.dianhuotong.shop.bean;

import java.util.List;

public class OrderOkBotttomInfo {

    private OrderBaseInfo.ContentBean.FreightInfoBean freightInfoBean;
     private String goodsNumber;
    private String words = "";
     private int money;
    private String yH = "";
     private String parentId;
     private FrigthInfo frigthInfo;                         //运费实体类
    private CouponInfo couponInfo;
     private List<CouponInfo> couponInfoList;
    private boolean showCoupon=false;
     private CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean;

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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getyH() {
        return yH;
    }

    public void setyH(String yH) {
        this.yH = yH;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public FrigthInfo getFrigthInfo() {
        return frigthInfo;
    }

    public void setFrigthInfo(FrigthInfo frigthInfo) {
        this.frigthInfo = frigthInfo;
    }

    public CouponInfo getCouponInfo() {
        return couponInfo;
    }

    public void setCouponInfo(CouponInfo couponInfo) {
        this.couponInfo = couponInfo;
    }

    public List<CouponInfo> getCouponInfoList() {
        return couponInfoList;
    }

    public void setCouponInfoList(List<CouponInfo> couponInfoList) {
        this.couponInfoList = couponInfoList;
    }

    public boolean isShowCoupon() {
        return showCoupon;
    }

    public void setShowCoupon(boolean showCoupon) {
        this.showCoupon = showCoupon;
    }

    public CartBaseInfo.GoodsItemsBean.ShopDTOBean getShopDTOBean() {
        return shopDTOBean;
    }

    public void setShopDTOBean(CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean) {
        this.shopDTOBean = shopDTOBean;
    }
}
