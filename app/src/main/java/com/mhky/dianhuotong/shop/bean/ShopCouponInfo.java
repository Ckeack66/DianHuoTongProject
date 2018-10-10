package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

public class ShopCouponInfo implements Serializable {
    /**
     * id : 31
     * name : 满20减10
     * startDate : 2018-06-24 00:00:00
     * endDate : 2018-07-31 00:00:00
     * promotionType : DIAN_PU_YOU_HUI_QUAN
     * goodsChannel : FULL_SITE
     * fullCutType : GRADS
     * gradientFullCut : {"signal":{"fullAmount":2000,"limitNums":10,"cutPrice":1000,"discount":0,"circulation":10,"limitReceive":1}}
     * shopId : 53
     * goodsIds : []
     * operator : 40
     * status : true
     */

    private String id;
    private String name;
    private String startDate;
    private String endDate;
    private String promotionType;
    private String goodsChannel;
    private String fullCutType;
    private GradientFullCutBean gradientFullCut;
    private String shopId;
    private String operator;
    private boolean status;
    private List<?> goodsIds;
    private boolean haved;

    public boolean isHaved() {
        return haved;
    }

    public void setHaved(boolean haved) {
        this.haved = haved;
    }

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

    public String getFullCutType() {
        return fullCutType;
    }

    public void setFullCutType(String fullCutType) {
        this.fullCutType = fullCutType;
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

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<?> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<?> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public static class GradientFullCutBean {
        /**
         * signal : {"fullAmount":2000,"limitNums":10,"cutPrice":1000,"discount":0,"circulation":10,"limitReceive":1}
         */

        private SignalBean signal;

        public SignalBean getSignal() {
            return signal;
        }

        public void setSignal(SignalBean signal) {
            this.signal = signal;
        }

        public static class SignalBean {
            /**
             * fullAmount : 2000
             * limitNums : 10
             * cutPrice : 1000
             * discount : 0
             * circulation : 10
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
