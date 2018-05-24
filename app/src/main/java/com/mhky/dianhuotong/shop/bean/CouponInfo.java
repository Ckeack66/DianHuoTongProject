package com.mhky.dianhuotong.shop.bean;

import java.util.List;

public class CouponInfo {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     * promotionItem : {"name":"店铺周年庆优惠券","startDate":"2018-05-20 00:00:00","endDate":"2018-08-30 00:00:00","promotionType":"DIAN_PU_YOU_HUI_QUAN","goodsChannel":"MOBILE","cutName":"second","gradientFullCut":{"fullAmount":2000,"limitNums":2,"cutPrice":200,"discount":0,"circulation":100,"limitReceive":1},"goodsIds":[],"shopId":"48"}
     * companyId : 48
     * shopId : 9
     * grads : second
     * status : false
     * invalidDate : 2018-08-30 00:00:00
     */
    private String id;
    private PromotionItemBean promotionItem;
    private String companyId;
    private String shopId;
    private String grads;
    private boolean status;
    private String invalidDate;

    public PromotionItemBean getPromotionItem() {
        return promotionItem;
    }

    public void setPromotionItem(PromotionItemBean promotionItem) {
        this.promotionItem = promotionItem;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
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

    public String getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(String invalidDate) {
        this.invalidDate = invalidDate;
    }

    public static class PromotionItemBean {
        /**
         * name : 店铺周年庆优惠券
         * startDate : 2018-05-20 00:00:00
         * endDate : 2018-08-30 00:00:00
         * promotionType : DIAN_PU_YOU_HUI_QUAN
         * goodsChannel : MOBILE
         * cutName : second
         * gradientFullCut : {"fullAmount":2000,"limitNums":2,"cutPrice":200,"discount":0,"circulation":100,"limitReceive":1}
         * goodsIds : []
         * shopId : 48
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

            private int fullAmount;
            private int limitNums;
            private int cutPrice;
            private int discount;
            private int circulation;
            private int limitReceive;

            public int getFullAmount() {
                return fullAmount;
            }

            public void setFullAmount(int fullAmount) {
                this.fullAmount = fullAmount;
            }

            public int getLimitNums() {
                return limitNums;
            }

            public void setLimitNums(int limitNums) {
                this.limitNums = limitNums;
            }

            public int getCutPrice() {
                return cutPrice;
            }

            public void setCutPrice(int cutPrice) {
                this.cutPrice = cutPrice;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public int getCirculation() {
                return circulation;
            }

            public void setCirculation(int circulation) {
                this.circulation = circulation;
            }

            public int getLimitReceive() {
                return limitReceive;
            }

            public void setLimitReceive(int limitReceive) {
                this.limitReceive = limitReceive;
            }
        }
    }
}
