package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/5.
 */

public class CartBaseInfo implements Serializable {

    /**
     * id : 17
     * goodsItems : [{"goodsId":"23","title":"同仁堂 六味地黄丸（浓缩丸）120丸*12件","goodsNo":"lwdhw 123465","model":"200丸*1瓶/盒","manufacturer":"北京同仁堂科技发展股份有限公司制药厂","approvalNumber":"国药准字Z19993068","superviseCode":"国药准字Z19993068","type":"非处方药","barCode":"63497979798","picture":"http://116.255.155.156:9040/20180420\\GOODS\\15242143572658316.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143570312864.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143572654596.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143569844068.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143571567313.jpg","expiryDate":null,"skuDTO":{"id":5,"skuNo":"123456","batchNo":"123456","retailPrice":2045,"wholesalePrice":null,"stock":100,"batchNums":0,"enable":true,"salePropertyOptions":[{"name":"规格","value":"200G","url":""},{"name":"疗程","value":"2疗程","url":""}],"expirationDate":"2018-04-30 08:00:00"},"shopDTO":{"id":"1","shopName":"大鹏旗舰店","address":"济南槐荫区齐州路"},"shelves":true,"offShelves":false,"auditStatus":"APPROVED","expirationTime":null,"promotionDTO":null,"amount":24,"inPrice":49080,"checked":true,"enable":false},{"goodsId":"23","title":"同仁堂 六味地黄丸（浓缩丸）120丸*12件","goodsNo":"lwdhw 123465","model":"200丸*1瓶/盒","manufacturer":"北京同仁堂科技发展股份有限公司制药厂","approvalNumber":"国药准字Z19993068","superviseCode":"国药准字Z19993068","type":"非处方药","barCode":"63497979798","picture":"http://116.255.155.156:9040/20180420\\GOODS\\15242143572658316.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143570312864.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143572654596.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143569844068.jpg,http://116.255.155.156:9040/20180420\\GOODS\\15242143571567313.jpg","expiryDate":null,"skuDTO":{"id":4,"skuNo":"123456","batchNo":"123456","retailPrice":2045,"wholesalePrice":null,"stock":100,"batchNums":0,"enable":true,"salePropertyOptions":[{"name":"疗程","value":"1疗程","url":""},{"name":"规格","value":"400G","url":""}],"expirationDate":"2018-04-30 08:00:00"},"shopDTO":{"id":"1","shopName":"大鹏旗舰店","address":"济南槐荫区齐州路"},"shelves":true,"offShelves":false,"auditStatus":"APPROVED","expirationTime":null,"promotionDTO":null,"amount":10,"inPrice":20450,"checked":true,"enable":false}]
     */

    private String id;
    private List<GoodsItemsBean> goodsItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<GoodsItemsBean> getGoodsItems() {
        return goodsItems;
    }

    public void setGoodsItems(List<GoodsItemsBean> goodsItems) {
        this.goodsItems = goodsItems;
    }

    public static class GoodsItemsBean {
        /**
         * goodsId : 23
         * title : 同仁堂 六味地黄丸（浓缩丸）120丸*12件
         * goodsNo : lwdhw 123465
         * model : 200丸*1瓶/盒
         * manufacturer : 北京同仁堂科技发展股份有限公司制药厂
         * approvalNumber : 国药准字Z19993068
         * superviseCode : 国药准字Z19993068
         * type : 非处方药
         * barCode : 63497979798
         * picture : http://116.255.155.156:9040/20180420\GOODS\15242143572658316.jpg,http://116.255.155.156:9040/20180420\GOODS\15242143570312864.jpg,http://116.255.155.156:9040/20180420\GOODS\15242143572654596.jpg,http://116.255.155.156:9040/20180420\GOODS\15242143569844068.jpg,http://116.255.155.156:9040/20180420\GOODS\15242143571567313.jpg
         * expiryDate : null
         * skuDTO : {"id":5,"skuNo":"123456","batchNo":"123456","retailPrice":2045,"wholesalePrice":null,"stock":100,"batchNums":0,"enable":true,"salePropertyOptions":[{"name":"规格","value":"200G","url":""},{"name":"疗程","value":"2疗程","url":""}],"expirationDate":"2018-04-30 08:00:00"}
         * shopDTO : {"id":"1","shopName":"大鹏旗舰店","address":"济南槐荫区齐州路"}
         * shelves : true
         * offShelves : false
         * auditStatus : APPROVED
         * expirationTime : null
         * promotionDTO : null
         * amount : 24
         * inPrice : 49080
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
             * id : 5
             * skuNo : 123456
             * batchNo : 123456
             * retailPrice : 2045
             * wholesalePrice : null
             * stock : 100
             * batchNums : 0
             * enable : true
             * salePropertyOptions : [{"name":"规格","value":"200G","url":""},{"name":"疗程","value":"2疗程","url":""}]
             * expirationDate : 2018-04-30 08:00:00
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
                 * value : 200G
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
             * id : 1
             * shopName : 大鹏旗舰店
             * address : 济南槐荫区齐州路
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
}
