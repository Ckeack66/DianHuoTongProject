package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OrderOkNewInfo implements Serializable {
    /**
     * buyerId : string
     * couponIds : ["string"]
     * invoiced : true
     * remark : ll
     * skuIds : ["string"]
     * source : PC
     */

    private String buyerId;
    private boolean invoiced;
    private Map<String,String> remark;
    private String source;
    private List<String> couponIds;
    private List<String> skuIds;

    public String getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(String buyerId) {
        this.buyerId = buyerId;
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public Map<String,String> getRemark() {
        return remark;
    }

    public void setRemark(Map<String,String> remark) {
        this.remark = remark;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public List<String> getCouponIds() {
        return couponIds;
    }

    public void setCouponIds(List<String> couponIds) {
        this.couponIds = couponIds;
    }

    public List<String> getSkuIds() {
        return skuIds;
    }

    public void setSkuIds(List<String> skuIds) {
        this.skuIds = skuIds;
    }
}
