package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

public class LastMinuteInfo implements Serializable {
    /**
     * id : 39
     * name : 限时折扣6.29
     * startDate : 2018-06-29 00:00:00
     * endDate : 2018-08-30 00:00:00
     * promotionType : XIAN_SHI_XIAN_LIANG_ZHE_KOU
     * goodsChannel : FULL_SITE
     * fullCutType : GRADS
     * gradientFullCut : {"signal":{"fullAmount":null,"limitNums":3,"cutPrice":null,"discount":0.75,"circulation":0,"limitReceive":1}}
     * shopId : 60
     * goodsIds : ["42"]
     * operator : 20
     * status : true
     */

    private int id;
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
    private List<String> goodsIds;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public List<String> getGoodsIds() {
        return goodsIds;
    }

    public void setGoodsIds(List<String> goodsIds) {
        this.goodsIds = goodsIds;
    }

    public static class GradientFullCutBean {
        /**
         * signal : {"fullAmount":null,"limitNums":3,"cutPrice":null,"discount":0.75,"circulation":0,"limitReceive":1}
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
             * fullAmount : null
             * limitNums : 3
             * cutPrice : null
             * discount : 0.75
             * circulation : 0
             * limitReceive : 1
             */

            private Object fullAmount;
            private int limitNums;
            private Object cutPrice;
            private double discount;
            private int circulation;
            private int limitReceive;

            public Object getFullAmount() {
                return fullAmount;
            }

            public void setFullAmount(Object fullAmount) {
                this.fullAmount = fullAmount;
            }

            public int getLimitNums() {
                return limitNums;
            }

            public void setLimitNums(int limitNums) {
                this.limitNums = limitNums;
            }

            public Object getCutPrice() {
                return cutPrice;
            }

            public void setCutPrice(Object cutPrice) {
                this.cutPrice = cutPrice;
            }

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
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
    /**
     * content : [{"id":24,"name":"限时折扣6.14","startDate":"2018-06-13 00:00:00","endDate":"2018-06-15 00:00:00","promotionType":"XIAN_SHI_XIAN_LIANG_ZHE_KOU","goodsChannel":"MOBILE","fullCutType":"GRADS","gradientFullCut":{"signal":{"fullAmount":null,"limitNums":2,"cutPrice":null,"discount":0.8,"circulation":0,"limitReceive":1}},"goodsIds":["30"],"shopId":"53","operator":"20","status":true,"createTime":"2018-06-13 15:30:18","lastModifyTime":"2018-06-13 15:30:18","new":false}]
     * totalElements : 1
     * totalPages : 1
     * last : true
     * size : 10
     * number : 0
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * first : true
     * numberOfElements : 1
     */


}
