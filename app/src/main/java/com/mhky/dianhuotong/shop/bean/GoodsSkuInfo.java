package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/26.
 */

public class GoodsSkuInfo implements Serializable {
    /**
     * id : 4
     * skuNo : 123456
     * goodsId : 23
     * batchNo : 123456
     * retailPrice : 2045
     * wholesalePrice : null
     * stock : 100
     * batchNums : 0
     * enable : true
     * salePropertyOptions : [{"name":"规格","value":"400G","url":""},{"name":"疗程","value":"1疗程","url":""}]
     * expirationDate : 2018-04-30 00:00:00
     * areaInfo : {"id":"19244","area":"山东省济南市","regionCode":"370100000000"}
     */

    private int id;
    private String skuNo;
    private int goodsId;
    private String batchNo;
    private double retailPrice;
    private double wholesalePrice;
    private int stock;
    private int batchNums;
    private boolean enable;
    private String expirationDate;
    private AreaInfoBean areaInfo;
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

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getWholesalePrice() {
        return wholesalePrice;
    }

    public void setWholesalePrice(double wholesalePrice) {
        this.wholesalePrice = wholesalePrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public AreaInfoBean getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(AreaInfoBean areaInfo) {
        this.areaInfo = areaInfo;
    }

    public List<SalePropertyOptionsBean> getSalePropertyOptions() {
        return salePropertyOptions;
    }

    public void setSalePropertyOptions(List<SalePropertyOptionsBean> salePropertyOptions) {
        this.salePropertyOptions = salePropertyOptions;
    }

    public static class AreaInfoBean {
        /**
         * id : 19244
         * area : 山东省济南市
         * regionCode : 370100000000
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
         * value : 400G
         * url :
         */

        private String name;
        private String value;
        private String url;

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

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
