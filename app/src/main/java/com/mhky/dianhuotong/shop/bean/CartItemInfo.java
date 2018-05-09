package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */

public class CartItemInfo implements Serializable {
    /**
     * goodsId : 46
     * title : 丁桂儿脐贴(丁桂)
     * goodsNo : C12000150027
     * model : 1.6g*3贴||1.6g*2贴
     * manufacturer : 亚宝药业集团股份有限公司
     * approvalNumber : 国药准字B20020882
     * superviseCode : 国药准字B20020882
     * type : otc
     * barCode : 6923888899143
     * picture : http://192.168.2.158:9040/20180505\GOODS\15255129842555982.jpg,http://192.168.2.158:9040/20180505\GOODS\15255129876406289.jpg,http://192.168.2.158:9040/20180505\GOODS\15255129926635127.jpg,http://192.168.2.158:9040/20180505\GOODS\15255129965633075.jpg
     * expiryDate : null
     * skuDTO : {"id":12,"skuNo":"123","batchNo":"12","retailPrice":2600,"wholesalePrice":null,"stock":300,"batchNums":0,"enable":true,"salePropertyOptions":[{"name":"规格","value":"1.6g*2贴","url":""}],"expirationDate":"2018-05-10 00:00:00"}
     * shopDTO : {"id":"48","shopName":"111","address":"{\"province\":\"辽宁省\",\"town\":\"黄旗寨满族镇\",\"city\":\"铁岭市\",\"road\":\"111\",\"district\":\"开原市\"}"}
     * shelves : true
     * offShelves : false
     * auditStatus : APPROVED
     * expirationTime : null
     * promotionDTO : null
     * amount : 7
     * inPrice : 18200
     * checked : true
     * enable : false
     */

    private String goodsId;
    private String title;
    private String goodsNo;
    private String model;
    private String manufacturer;
    private String approvalNumber;
    private String superviseCode;
    private String type;
    private String barCode;
    private String picture;
    private Object expiryDate;
    private SkuDTOBean skuDTO;
    private ShopDTOBean shopDTO;
    private boolean shelves;
    private boolean offShelves;
    private String auditStatus;
    private Object expirationTime;
    private Object promotionDTO;
    private int amount;
    private int inPrice;
    private boolean checked;
    private boolean enable;

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getApprovalNumber() {
        return approvalNumber;
    }

    public void setApprovalNumber(String approvalNumber) {
        this.approvalNumber = approvalNumber;
    }

    public String getSuperviseCode() {
        return superviseCode;
    }

    public void setSuperviseCode(String superviseCode) {
        this.superviseCode = superviseCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Object getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Object expiryDate) {
        this.expiryDate = expiryDate;
    }

    public SkuDTOBean getSkuDTO() {
        return skuDTO;
    }

    public void setSkuDTO(SkuDTOBean skuDTO) {
        this.skuDTO = skuDTO;
    }

    public ShopDTOBean getShopDTO() {
        return shopDTO;
    }

    public void setShopDTO(ShopDTOBean shopDTO) {
        this.shopDTO = shopDTO;
    }

    public boolean isShelves() {
        return shelves;
    }

    public void setShelves(boolean shelves) {
        this.shelves = shelves;
    }

    public boolean isOffShelves() {
        return offShelves;
    }

    public void setOffShelves(boolean offShelves) {
        this.offShelves = offShelves;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Object getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Object expirationTime) {
        this.expirationTime = expirationTime;
    }

    public Object getPromotionDTO() {
        return promotionDTO;
    }

    public void setPromotionDTO(Object promotionDTO) {
        this.promotionDTO = promotionDTO;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getInPrice() {
        return inPrice;
    }

    public void setInPrice(int inPrice) {
        this.inPrice = inPrice;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public static class SkuDTOBean {
        /**
         * id : 12
         * skuNo : 123
         * batchNo : 12
         * retailPrice : 2600
         * wholesalePrice : null
         * stock : 300
         * batchNums : 0
         * enable : true
         * salePropertyOptions : [{"name":"规格","value":"1.6g*2贴","url":""}]
         * expirationDate : 2018-05-10 00:00:00
         */

        private int id;
        private String skuNo;
        private String batchNo;
        private int retailPrice;
        private Object wholesalePrice;
        private int stock;
        private int batchNums;
        private boolean enable;
        private String expirationDate;
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

        public Object getWholesalePrice() {
            return wholesalePrice;
        }

        public void setWholesalePrice(Object wholesalePrice) {
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

        public List<SalePropertyOptionsBean> getSalePropertyOptions() {
            return salePropertyOptions;
        }

        public void setSalePropertyOptions(List<SalePropertyOptionsBean> salePropertyOptions) {
            this.salePropertyOptions = salePropertyOptions;
        }

        public static class SalePropertyOptionsBean {
            /**
             * name : 规格
             * value : 1.6g*2贴
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

    public static class ShopDTOBean {
        /**
         * id : 48
         * shopName : 111
         * address : {"province":"辽宁省","town":"黄旗寨满族镇","city":"铁岭市","road":"111","district":"开原市"}
         */

        private String id;
        private String shopName;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getShopName() {
            return shopName;
        }

        public void setShopName(String shopName) {
            this.shopName = shopName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
