package com.mhky.dianhuotong.shop.bean;

import java.util.List;

public class CouponInfo {
    /**
     * promotionItem : {"name":"平台优惠券","startDate":"2018-05-21 00:00:00","endDate":"2018-08-30 00:00:00","promotionType":"PING_TAI_YOU_HUI_QUAN","goodsChannel":"MOBILE","cutName":"second","gradientFullCut":{"fullAmount":2000,"limitNums":2,"cutPrice":200,"discount":0,"circulation":100,"limitReceive":1},"goodsIds":[],"shopId":""}
     * companyId : null
     * shopId : 9
     * grads : second
     * status : true
     * invalidDate : 1535558400000
     */

    private PromotionItemBean promotionItem;
    private Object companyId;
    private String shopId;
    private String grads;
    private boolean status;
    private long invalidDate;

    public PromotionItemBean getPromotionItem() {
        return promotionItem;
    }

    public void setPromotionItem(PromotionItemBean promotionItem) {
        this.promotionItem = promotionItem;
    }

    public Object getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Object companyId) {
        this.companyId = companyId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getGrads() {
        return grads;
    }

    public void setGrads(String grads) {
        this.grads = grads;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(long invalidDate) {
        this.invalidDate = invalidDate;
    }

    public static class PromotionItemBean {
        /**
         * name : 平台优惠券
         * startDate : 2018-05-21 00:00:00
         * endDate : 2018-08-30 00:00:00
         * promotionType : PING_TAI_YOU_HUI_QUAN
         * goodsChannel : MOBILE
         * cutName : second
         * gradientFullCut : {"fullAmount":2000,"limitNums":2,"cutPrice":200,"discount":0,"circulation":100,"limitReceive":1}
         * goodsIds : []
         * shopId :
         */

        private String name;
        private String startDate;
        private String endDate;
        private String promotionType;
        private String goodsChannel;
        private String cutName;
        private GradientFullCutBean gradientFullCut;
        private String shopId;
        private List<?> goodsIds;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getPromotionType() {
            return promotionType;
        }

        public void setPromotionType(String promotionType) {
            this.promotionType = promotionType;
        }

        public String getGoodsChannel() {
            return goodsChannel;
        }

        public void setGoodsChannel(String goodsChannel) {
            this.goodsChannel = goodsChannel;
        }

        public String getCutName() {
            return cutName;
        }

        public void setCutName(String cutName) {
            this.cutName = cutName;
        }

        public GradientFullCutBean getGradientFullCut() {
            return gradientFullCut;
        }

        public void setGradientFullCut(GradientFullCutBean gradientFullCut) {
            this.gradientFullCut = gradientFullCut;
        }

        public String getShopId() {
            return shopId;
        }

        public void setShopId(String shopId) {
            this.shopId = shopId;
        }

        public List<?> getGoodsIds() {
            return goodsIds;
        }

        public void setGoodsIds(List<?> goodsIds) {
            this.goodsIds = goodsIds;
        }

        public static class GradientFullCutBean {
            /**
             * fullAmount : 2000
             * limitNums : 2
             * cutPrice : 200
             * discount : 0
             * circulation : 100
             * limitReceive : 1
             */

            private double fullAmount;
            private double limitNums;
            private double cutPrice;
            private double discount;
            private double circulation;
            private double limitReceive;

            public double getFullAmount() {
                return fullAmount;
            }

            public void setFullAmount(double fullAmount) {
                this.fullAmount = fullAmount;
            }

            public double getLimitNums() {
                return limitNums;
            }

            public void setLimitNums(double limitNums) {
                this.limitNums = limitNums;
            }

            public double getCutPrice() {
                return cutPrice;
            }

            public void setCutPrice(double cutPrice) {
                this.cutPrice = cutPrice;
            }

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
                this.discount = discount;
            }

            public double getCirculation() {
                return circulation;
            }

            public void setCirculation(double circulation) {
                this.circulation = circulation;
            }

            public double getLimitReceive() {
                return limitReceive;
            }

            public void setLimitReceive(double limitReceive) {
                this.limitReceive = limitReceive;
            }
        }
    }
}
