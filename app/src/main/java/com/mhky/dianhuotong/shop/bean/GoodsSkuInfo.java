package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 * 单个商品属性信息
 */

public class GoodsSkuInfo implements Serializable {

    /**
     * id : 8291387
     * skuNo : aaa0209c5220031706191
     * goodsId : 8257928
     * batchNo : 170619
     * retailPrice : null
     * wholesalePrice : 871
     * huayuanPrice : 822
     * stock : 775
     * dongshengStock : 0
     * huayuanStock : 0
     * identification : HUAYUAN
     * batchNums : 25
     * enable : true
     * salePropertyOptions : [{"name":"规格","value":"10g","url":null}]
     * expirationDate : 1588262400000
     * areaInfo : {"id":"1","area":"中国","regionCode":"100000000000"}
     * isRetail : 1
     */

    private int id;
    private String skuNo;
    private int goodsId;
    private String batchNo;
    private int retailPrice;
    private int wholesalePrice;
    private int huayuanPrice;
    private int stock;
    private int dongshengStock;
    private int huayuanStock;
    private String identification;
    private int batchNums;
    private boolean enable;
    private long expirationDate;
    private AreaInfoBean areaInfo;
    private int isRetail;
    private List<SalePropertyOptionsBean> salePropertyOptions;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(int retailPrice) {
        this.retailPrice = retailPrice;
    }

    public int getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(int wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public int getHuayuanPrice() {
        return huayuanPrice;
    }

    public void setHuayuanPrice(int huayuanPrice) {
        this.huayuanPrice = huayuanPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getDongshengStock() {
        return dongshengStock;
    }

    public void setDongshengStock(int dongshengStock) {
        this.dongshengStock = dongshengStock;
    }

    public int getHuayuanStock() {
        return huayuanStock;
    }

    public void setHuayuanStock(int huayuanStock) {
        this.huayuanStock = huayuanStock;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public int getBatchNums() {
        return batchNums;
    }

    public void setBatchNums(int batchNums) {
        this.batchNums = batchNums;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public long getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AreaInfoBean getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfoBean areaInfo) {
        this.areaInfo = areaInfo;
    }

    public int getIsRetail() {
        return isRetail;
    }

    public void setIsRetail(int isRetail) {
        this.isRetail = isRetail;
    }

    public List<SalePropertyOptionsBean> getSalePropertyOptions() {
        return salePropertyOptions;
    }

    public void setSalePropertyOptions(List<SalePropertyOptionsBean> salePropertyOptions) {
        this.salePropertyOptions = salePropertyOptions;
    }

    public static class AreaInfoBean {
        /**
         * id : 1
         * area : 中国
         * regionCode : 100000000000
         */

        private String id;
        private String area;
        private String regionCode;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }
    }

    public static class SalePropertyOptionsBean {
        /**
         * name : 规格
         * value : 10g
         * url : null
         */

        private String name;
        private String value;
        private Object url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Object getUrl() {
            return url;
        }

        public void setUrl(Object url) {
            this.url = url;
        }
    }
}
