package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/3.
 */

public class OrderBaseInfo implements Serializable {
    /**
     * content : [{"id":"m180510153909033955","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":2600,"payment":2600,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 15:39:09","items":[{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":1,"retailPrice":2600,"realPrice":2600,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510153603080965","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":5200,"payment":5200,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 15:36:03","items":[{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":2,"retailPrice":5200,"realPrice":2600,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510151824213934","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":4400,"payment":4400,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 15:18:24","items":[{"goodsInfo":{"id":"16","goodsId":"50","name":"布洛芬混悬液(美林)","skuNo":"23","spec":"15ml||100ml||30ml||35ml||20ml","imageUrl":"http://192.168.2.158:9040/20180507\\GOODS\\15256577778408677.jpg,http://192.168.2.158:9040/20180507\\GOODS\\15256577779182139.jpg,http://192.168.2.158:9040/20180507\\GOODS\\15256577779644567.jpg,http://192.168.2.158:9040/20180507\\GOODS\\15256577779807980.jpg","discountInfo":""},"quantity":2,"retailPrice":4400,"realPrice":2200,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510150737108864","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":5200,"payment":5200,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 15:07:37","items":[{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":2,"retailPrice":5200,"realPrice":2600,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510150726594661","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":4400,"payment":4400,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 15:07:27","items":[{"goodsInfo":{"id":"16","goodsId":"50","name":"布洛芬混悬液(美林)","skuNo":"23","spec":"15ml||100ml||30ml||35ml||20ml","imageUrl":"http://192.168.2.158:9040/20180507\\GOODS\\15256577778408677.jpg,http://192.168.2.158:9040/20180507\\GOODS\\15256577779182139.jpg,http://192.168.2.158:9040/20180507\\GOODS\\15256577779644567.jpg,http://192.168.2.158:9040/20180507\\GOODS\\15256577779807980.jpg","discountInfo":""},"quantity":2,"retailPrice":4400,"realPrice":2200,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510150233360867","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":2600,"payment":2600,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 15:02:33","items":[{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":1,"retailPrice":2600,"realPrice":2600,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510145024105450","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":2600,"payment":2600,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 14:50:24","items":[{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":1,"retailPrice":2600,"realPrice":2600,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510144215918841","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":2500,"payment":2500,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 14:42:16","items":[{"goodsInfo":{"id":"13","goodsId":"47","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255134411648276.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255134470772889.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255134504779344.jpg","discountInfo":""},"quantity":1,"retailPrice":2500,"realPrice":2500,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510143906019162","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":4500,"payment":4500,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 14:39:06","items":[{"goodsInfo":{"id":"11","goodsId":"45","name":"五维赖氨酸颗粒(维乐多)","skuNo":"51","spec":"10g*10袋","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255054536824534.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255054572703778.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255054640258080.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255054665363086.jpg","discountInfo":""},"quantity":1,"retailPrice":4500,"realPrice":4500,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true},{"id":"m180510143318575929","buyerInfo":{"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null},"sellerInfo":{"id":"48","name":"111"},"addressInfo":null,"freightInfo":{"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false},"amount":2600,"payment":2600,"source":"MOBILE","invoiced":false,"createTime":"2018-05-10 14:33:19","items":[{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":1,"retailPrice":2600,"realPrice":2600,"expiryTime":null}],"status":"ORDERED","paymentMode":"ONLINE_PAYMENT","remark":null,"valid":true}]
     * last : false
     * totalElements : 18
     * totalPages : 2
     * size : 10
     * number : 0
     * first : true
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * numberOfElements : 10
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int size;
    private int number;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;
    private List<SortBean> sort;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public List<SortBean> getSort() {
        return sort;
    }

    public void setSort(List<SortBean> sort) {
        this.sort = sort;
    }

    public static class ContentBean {
        /**
         * id : m180510153909033955
         * buyerInfo : {"id":"19","name":null,"shopId":"17","regionCode":null,"region":null,"orderMan":null}
         * sellerInfo : {"id":"48","name":"111"}
         * addressInfo : null
         * freightInfo : {"freightType":"MAN_MIAN_YUN_FEI","sendAccount":500,"freight":80,"collectFreight":false}
         * amount : 2600
         * payment : 2600
         * source : MOBILE
         * invoiced : false
         * createTime : 2018-05-10 15:39:09
         * items : [{"goodsInfo":{"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""},"quantity":1,"retailPrice":2600,"realPrice":2600,"expiryTime":null}]
         * status : ORDERED
         * paymentMode : ONLINE_PAYMENT
         * remark : null
         * valid : true
         */

        private String id;
        private BuyerInfoBean buyerInfo;
        private SellerInfoBean sellerInfo;
        private Object addressInfo;
        private FreightInfoBean freightInfo;
        private int amount;
        private int payment;
        private String source;
        private boolean invoiced;
        private String createTime;
        private String status;
        private String paymentMode;
        private Object remark;
        private boolean valid;
        private List<ItemsBean> items;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public BuyerInfoBean getBuyerInfo() {
            return buyerInfo;
        }

        public void setBuyerInfo(BuyerInfoBean buyerInfo) {
            this.buyerInfo = buyerInfo;
        }

        public SellerInfoBean getSellerInfo() {
            return sellerInfo;
        }

        public void setSellerInfo(SellerInfoBean sellerInfo) {
            this.sellerInfo = sellerInfo;
        }

        public Object getAddressInfo() {
            return addressInfo;
        }

        public void setAddressInfo(Object addressInfo) {
            this.addressInfo = addressInfo;
        }

        public FreightInfoBean getFreightInfo() {
            return freightInfo;
        }

        public void setFreightInfo(FreightInfoBean freightInfo) {
            this.freightInfo = freightInfo;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public int getPayment() {
            return payment;
        }

        public void setPayment(int payment) {
            this.payment = payment;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public boolean isInvoiced() {
            return invoiced;
        }

        public void setInvoiced(boolean invoiced) {
            this.invoiced = invoiced;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public List<ItemsBean> getItems() {
            return items;
        }

        public void setItems(List<ItemsBean> items) {
            this.items = items;
        }

        public static class BuyerInfoBean {
            /**
             * id : 19
             * name : null
             * shopId : 17
             * regionCode : null
             * region : null
             * orderMan : null
             */

            private String id;
            private Object name;
            private String shopId;
            private Object regionCode;
            private Object region;
            private Object orderMan;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public Object getName() {
                return name;
            }

            public void setName(Object name) {
                this.name = name;
            }

            public String getShopId() {
                return shopId;
            }

            public void setShopId(String shopId) {
                this.shopId = shopId;
            }

            public Object getRegionCode() {
                return regionCode;
            }

            public void setRegionCode(Object regionCode) {
                this.regionCode = regionCode;
            }

            public Object getRegion() {
                return region;
            }

            public void setRegion(Object region) {
                this.region = region;
            }

            public Object getOrderMan() {
                return orderMan;
            }

            public void setOrderMan(Object orderMan) {
                this.orderMan = orderMan;
            }
        }

        public static class SellerInfoBean {
            /**
             * id : 48
             * name : 111
             */

            private String id;
            private String name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class FreightInfoBean {
            /**
             * freightType : MAN_MIAN_YUN_FEI
             * sendAccount : 500
             * freight : 80
             * collectFreight : false
             */

            private String freightType;
            private int sendAccount;
            private int freight;
            private boolean collectFreight;

            public String getFreightType() {
                return freightType;
            }

            public void setFreightType(String freightType) {
                this.freightType = freightType;
            }

            public int getSendAccount() {
                return sendAccount;
            }

            public void setSendAccount(int sendAccount) {
                this.sendAccount = sendAccount;
            }

            public int getFreight() {
                return freight;
            }

            public void setFreight(int freight) {
                this.freight = freight;
            }

            public boolean isCollectFreight() {
                return collectFreight;
            }

            public void setCollectFreight(boolean collectFreight) {
                this.collectFreight = collectFreight;
            }
        }

        public static class ItemsBean {
            /**
             * goodsInfo : {"id":"12","goodsId":"46","name":"丁桂儿脐贴(丁桂)","skuNo":"123","spec":"1.6g*3贴||1.6g*2贴","imageUrl":"http://192.168.2.158:9040/20180505\\GOODS\\15255129842555982.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129876406289.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129926635127.jpg,http://192.168.2.158:9040/20180505\\GOODS\\15255129965633075.jpg","discountInfo":""}
             * quantity : 1
             * retailPrice : 2600
             * realPrice : 2600
             * expiryTime : null
             */

            private GoodsInfoBean goodsInfo;
            private int quantity;
            private int retailPrice;
            private int realPrice;
            private Object expiryTime;

            public GoodsInfoBean getGoodsInfo() {
                return goodsInfo;
            }

            public void setGoodsInfo(GoodsInfoBean goodsInfo) {
                this.goodsInfo = goodsInfo;
            }

            public int getQuantity() {
                return quantity;
            }

            public void setQuantity(int quantity) {
                this.quantity = quantity;
            }

            public int getRetailPrice() {
                return retailPrice;
            }

            public void setRetailPrice(int retailPrice) {
                this.retailPrice = retailPrice;
            }

            public int getRealPrice() {
                return realPrice;
            }

            public void setRealPrice(int realPrice) {
                this.realPrice = realPrice;
            }

            public Object getExpiryTime() {
                return expiryTime;
            }

            public void setExpiryTime(Object expiryTime) {
                this.expiryTime = expiryTime;
            }

            public static class GoodsInfoBean {
                /**
                 * id : 12
                 * goodsId : 46
                 * name : 丁桂儿脐贴(丁桂)
                 * skuNo : 123
                 * spec : 1.6g*3贴||1.6g*2贴
                 * imageUrl : http://192.168.2.158:9040/20180505\GOODS\15255129842555982.jpg,http://192.168.2.158:9040/20180505\GOODS\15255129876406289.jpg,http://192.168.2.158:9040/20180505\GOODS\15255129926635127.jpg,http://192.168.2.158:9040/20180505\GOODS\15255129965633075.jpg
                 * discountInfo :
                 */

                private String id;
                private String goodsId;
                private String name;
                private String skuNo;
                private String spec;
                private String imageUrl;
                private String discountInfo;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getGoodsId() {
                    return goodsId;
                }

                public void setGoodsId(String goodsId) {
                    this.goodsId = goodsId;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getSkuNo() {
                    return skuNo;
                }

                public void setSkuNo(String skuNo) {
                    this.skuNo = skuNo;
                }

                public String getSpec() {
                    return spec;
                }

                public void setSpec(String spec) {
                    this.spec = spec;
                }

                public String getImageUrl() {
                    return imageUrl;
                }

                public void setImageUrl(String imageUrl) {
                    this.imageUrl = imageUrl;
                }

                public String getDiscountInfo() {
                    return discountInfo;
                }

                public void setDiscountInfo(String discountInfo) {
                    this.discountInfo = discountInfo;
                }
            }
        }
    }

    public static class SortBean {
        /**
         * direction : DESC
         * property : createTime
         * ignoreCase : false
         * nullHandling : NATIVE
         * ascending : false
         * descending : true
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean ascending;
        private boolean descending;

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }

        public String getProperty() {
            return property;
        }

        public void setProperty(String property) {
            this.property = property;
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        public void setIgnoreCase(boolean ignoreCase) {
            this.ignoreCase = ignoreCase;
        }

        public String getNullHandling() {
            return nullHandling;
        }

        public void setNullHandling(String nullHandling) {
            this.nullHandling = nullHandling;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }
    }
}
