package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

public class BaseCouponInfo  implements Serializable {
    /**
     * content : [{"id":23,"name":"平台15000优惠券(可用)","startDate":"2018-05-20 00:00:00","endDate":"2018-08-30 00:00:00","promotionType":"PING_TAI_YOU_HUI_QUAN","goodsChannel":"MOBILE","fullCutType":"GRADS","gradientFullCut":{"signal":{"fullAmount":1500000,"limitNums":2,"cutPrice":10000,"discount":0,"circulation":100,"limitReceive":1}},"goodsIds":[],"shopId":"","operator":"20","status":true,"createTime":"2018-06-12 14:02:17","lastModifyTime":"2018-06-12 14:02:17","new":false},{"id":22,"name":"平台优惠券(10-1)","startDate":"2018-05-20 00:00:00","endDate":"2018-08-30 00:00:00","promotionType":"PING_TAI_YOU_HUI_QUAN","goodsChannel":"MOBILE","fullCutType":"GRADS","gradientFullCut":{"signal":{"fullAmount":1000,"limitNums":2,"cutPrice":100,"discount":0,"circulation":100,"limitReceive":1}},"goodsIds":[],"shopId":"","operator":"20","status":true,"createTime":"2018-06-12 13:59:16","lastModifyTime":"2018-06-12 13:59:16","new":false}]
     * last : true
     * totalElements : 2
     * totalPages : 1
     * size : 10
     * number : 0
     * first : true
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","descending":true,"ascending":false}]
     * numberOfElements : 2
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
         * id : 23
         * name : 平台15000优惠券(可用)
         * startDate : 2018-05-20 00:00:00
         * endDate : 2018-08-30 00:00:00
         * promotionType : PING_TAI_YOU_HUI_QUAN
         * goodsChannel : MOBILE
         * fullCutType : GRADS
         * gradientFullCut : {"signal":{"fullAmount":1500000,"limitNums":2,"cutPrice":10000,"discount":0,"circulation":100,"limitReceive":1}}
         * goodsIds : []
         * shopId :
         * operator : 20
         * status : true
         * createTime : 2018-06-12 14:02:17
         * lastModifyTime : 2018-06-12 14:02:17
         * new : false
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
        private String createTime;
        private String lastModifyTime;
        private boolean newX;
        private List<?> goodsIds;

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

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getLastModifyTime() {
            return lastModifyTime;
        }

        public void setLastModifyTime(String lastModifyTime) {
            this.lastModifyTime = lastModifyTime;
        }

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
        }

        public List<?> getGoodsIds() {
            return goodsIds;
        }

        public void setGoodsIds(List<?> goodsIds) {
            this.goodsIds = goodsIds;
        }

        public static class GradientFullCutBean {
            /**
             * signal : {"fullAmount":1500000,"limitNums":2,"cutPrice":10000,"discount":0,"circulation":100,"limitReceive":1}
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
                 * fullAmount : 1500000
                 * limitNums : 2
                 * cutPrice : 10000
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

    public static class SortBean {
        /**
         * direction : DESC
         * property : createTime
         * ignoreCase : false
         * nullHandling : NATIVE
         * descending : true
         * ascending : false
         */

        private String direction;
        private String property;
        private boolean ignoreCase;
        private String nullHandling;
        private boolean descending;
        private boolean ascending;

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

        public boolean isDescending() {
            return descending;
        }

        public void setDescending(boolean descending) {
            this.descending = descending;
        }

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }
    }
}
