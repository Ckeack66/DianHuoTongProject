package com.mhky.dianhuotong.shop.bean;

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
    private String words;
}
