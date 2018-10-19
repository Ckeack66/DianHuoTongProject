package com.mhky.yaolinwang.order.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：C端订单实体类
 */
public class CustomerOrderBean implements Serializable {

    /**
     * content : [{"id":"m181015183740817927","buyerInfo":{"id":"130","name":"下游B测试专用"},"sellerInfo":{"id":"81","name":"山东特利尔营销策划有限公司医药分公司"},"addressInfo":{"consignee":"刘洋","province":"山东省","city":"济南市","area":"槐荫区","street":"青岛路","postalCode":"250000","detailedAddress":"青岛路恒大雅苑2-3-3-2","phone":"12345678976"},"paymentInfo":{"paymentNo":"130","paymentType":"下游B测试专用","payTime":"2018-05-21 15:13:41"},"orderDetailSet":[{"productId":"15","productName":"通用名","productPrice":10000,"productQuantity":2,"productIcon":"http://116.255.155.156:9040/20180926/GOODS/15379252480628406.jpg"},{"productId":"16","productName":"测试分类三","productPrice":2,"productQuantity":1,"productIcon":"http://116.255.155.156:9040/20180926/GOODS/15379251956872808.jpg"}],"coupon":null,"paymentMode":"ONLINE_PAYMENT","orderAmount":20002,"orderPayment":null,"deliveryMethod":"DELIVER","status":"ORDERED","payStatus":"WAIT","invoiced":false,"invoicedInfo":null,"valid":true,"remark":"","source":null,"createTime":"2018-10-15 18:37:41","orderCirculations":[{"createTime":"2018-10-15 18:37:41","orderCirculationId":{"orderId":"m181015183740817927","orderStatus":"ORDERED"}}]}]
     * last : true
     * totalElements : 2
     * totalPages : 1
     * number : 0
     * size : 10
     * sort : [{"direction":"DESC","property":"createTime","ignoreCase":false,"nullHandling":"NATIVE","ascending":false,"descending":true}]
     * first : true
     * numberOfElements : 2
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int number;
    private int size;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
         * id : m181015183740817927
         * buyerInfo : {"id":"130","name":"下游B测试专用"}
         * sellerInfo : {"id":"81","name":"山东特利尔营销策划有限公司医药分公司"}
         * addressInfo : {"consignee":"刘洋","province":"山东省","city":"济南市","area":"槐荫区","street":"青岛路","postalCode":"250000","detailedAddress":"青岛路恒大雅苑2-3-3-2","phone":"12345678976"}
         * paymentInfo : {"paymentNo":"130","paymentType":"下游B测试专用","payTime":"2018-05-21 15:13:41"}
         * orderDetailSet : [{"productId":"15","productName":"通用名","productPrice":10000,"productQuantity":2,"productIcon":"http://116.255.155.156:9040/20180926/GOODS/15379252480628406.jpg"},{"productId":"16","productName":"测试分类三","productPrice":2,"productQuantity":1,"productIcon":"http://116.255.155.156:9040/20180926/GOODS/15379251956872808.jpg"}]
         * coupon : null
         * paymentMode : ONLINE_PAYMENT
         * orderAmount : 20002
         * orderPayment : null
         * deliveryMethod : DELIVER
         * status : ORDERED
         * payStatus : WAIT
         * invoiced : false
         * invoicedInfo : null
         * valid : true
         * remark :
         * source : null
         * createTime : 2018-10-15 18:37:41
         * orderCirculations : [{"createTime":"2018-10-15 18:37:41","orderCirculationId":{"orderId":"m181015183740817927","orderStatus":"ORDERED"}}]
         */

        private String id;
        private BuyerInfoBean buyerInfo;
        private SellerInfoBean sellerInfo;
        private AddressInfoBean addressInfo;
        private PaymentInfoBean paymentInfo;
        private CouponBean coupon;
        private String paymentMode;                                         //DELIVERY_CASH 线下现金,ONLINE_PAYMENT线上
        private int orderAmount;
        private int orderPayment;                                        //实际支付金额
        private String deliveryMethod;                                      //取货方式 DELIVER,//物流SELFLIFTING;//自提
        private String status;
        private String payStatus;
        private boolean invoiced;
        private Object invoicedInfo;
        private boolean valid;
        private String remark;
        private Object source;                                              //订单来源 PC,MOBILE;
        private String createTime;
        private List<OrderDetailSetBean> orderDetailSet;
        private List<OrderCirculationsBean> orderCirculations;

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

        public AddressInfoBean getAddressInfo() {
            return addressInfo;
        }

        public void setAddressInfo(AddressInfoBean addressInfo) {
            this.addressInfo = addressInfo;
        }

        public PaymentInfoBean getPaymentInfo() {
            return paymentInfo;
        }

        public void setPaymentInfo(PaymentInfoBean paymentInfo) {
            this.paymentInfo = paymentInfo;
        }

        public CouponBean getCoupon() {
            return coupon;
        }

        public void setCoupon(CouponBean coupon) {
            this.coupon = coupon;
        }

        public String getPaymentMode() {
            return paymentMode;
        }

        public void setPaymentMode(String paymentMode) {
            this.paymentMode = paymentMode;
        }

        public int getOrderAmount() {
            return orderAmount;
        }

        public void setOrderAmount(int orderAmount) {
            this.orderAmount = orderAmount;
        }

        public int getOrderPayment() {
            return orderPayment;
        }

        public void setOrderPayment(int orderPayment) {
            this.orderPayment = orderPayment;
        }

        public String getDeliveryMethod() {
            return deliveryMethod;
        }

        public void setDeliveryMethod(String deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayStatus() {
            return payStatus;
        }

        public void setPayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public boolean isInvoiced() {
            return invoiced;
        }

        public void setInvoiced(boolean invoiced) {
            this.invoiced = invoiced;
        }

        public Object getInvoicedInfo() {
            return invoicedInfo;
        }

        public void setInvoicedInfo(Object invoicedInfo) {
            this.invoicedInfo = invoicedInfo;
        }

        public boolean isValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getSource() {
            return source;
        }

        public void setSource(Object source) {
            this.source = source;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<OrderDetailSetBean> getOrderDetailSet() {
            return orderDetailSet;
        }

        public void setOrderDetailSet(List<OrderDetailSetBean> orderDetailSet) {
            this.orderDetailSet = orderDetailSet;
        }

        public List<OrderCirculationsBean> getOrderCirculations() {
            return orderCirculations;
        }

        public void setOrderCirculations(List<OrderCirculationsBean> orderCirculations) {
            this.orderCirculations = orderCirculations;
        }

        public static class BuyerInfoBean {
            /**
             * id : 130
             * name : 下游B测试专用
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

        public static class SellerInfoBean {
            /**
             * id : 81
             * name : 山东特利尔营销策划有限公司医药分公司
             */

            private String id;
            private String name;
            private int freight;

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

            public int getFreight() {
                return freight;
            }

            public void setFreight(int freight) {
                this.freight = freight;
            }
        }

        public static class AddressInfoBean {
            /**
             * consignee : 刘洋
             * province : 山东省
             * city : 济南市
             * area : 槐荫区
             * street : 青岛路
             * postalCode : 250000
             * detailedAddress : 青岛路恒大雅苑2-3-3-2
             * phone : 12345678976
             */

            private String consignee;
            private String province;
            private String city;
            private String area;
            private String street;
            private String postalCode;
            private String detailedAddress;
            private String phone;

            public String getConsignee() {
                return consignee;
            }

            public void setConsignee(String consignee) {
                this.consignee = consignee;
            }

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getStreet() {
                return street;
            }

            public void setStreet(String street) {
                this.street = street;
            }

            public String getPostalCode() {
                return postalCode;
            }

            public void setPostalCode(String postalCode) {
                this.postalCode = postalCode;
            }

            public String getDetailedAddress() {
                return detailedAddress;
            }

            public void setDetailedAddress(String detailedAddress) {
                this.detailedAddress = detailedAddress;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }
        }

        public static class PaymentInfoBean implements Serializable{
            /**
             * paymentNo : 130
             * paymentType : 下游B测试专用
             * payTime : 2018-05-21 15:13:41
             */

            private String paymentNo;
            private String paymentType;
            private String payTime;

            public String getPaymentNo() {
                return paymentNo;
            }

            public void setPaymentNo(String paymentNo) {
                this.paymentNo = paymentNo;
            }

            public String getPaymentType() {
                return paymentType;
            }

            public void setPaymentType(String paymentType) {
                this.paymentType = paymentType;
            }

            public String getPayTime() {
                return payTime;
            }

            public void setPayTime(String payTime) {
                this.payTime = payTime;
            }
        }

        public static class CouponBean {

            /**
             * couponId :
             * couponType : 优惠类型
             * couponContent : 优惠内容
             * couponAmount : 优惠金额
             */

            private String couponId;
            private String couponType;
            private String couponContent;
            private String couponAmount;

            public String getCouponId() {
                return couponId;
            }

            public void setCouponId(String couponId) {
                this.couponId = couponId;
            }

            public String getCouponType() {
                return couponType;
            }

            public void setCouponType(String couponType) {
                this.couponType = couponType;
            }

            public String getCouponContent() {
                return couponContent;
            }

            public void setCouponContent(String couponContent) {
                this.couponContent = couponContent;
            }

            public String getCouponAmount() {
                return couponAmount;
            }

            public void setCouponAmount(String couponAmount) {
                this.couponAmount = couponAmount;
            }
        }

        public static class OrderDetailSetBean implements Serializable{
            /**
             * productId : 15
             * productName : 通用名
             * productPrice : 10000
             * productQuantity : 2
             * productIcon : http://116.255.155.156:9040/20180926/GOODS/15379252480628406.jpg
             * discountInfo :
             * productNo :
             * spec :
             * unit :
             * manufacturer :
             */
            private String productId;
            private String productName;
            private int productPrice;
            private int productQuantity;
            private String productIcon;
            private String discountInfo;
            private String productNo;
            private String spec;
            private String unit;
            private String manufacturer;

            public String getProductId() {
                return productId;
            }

            public void setProductId(String productId) {
                this.productId = productId;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }

            public int getProductPrice() {
                return productPrice;
            }

            public void setProductPrice(int productPrice) {
                this.productPrice = productPrice;
            }

            public int getProductQuantity() {
                return productQuantity;
            }

            public void setProductQuantity(int productQuantity) {
                this.productQuantity = productQuantity;
            }

            public String getProductIcon() {
                return productIcon;
            }

            public void setProductIcon(String productIcon) {
                this.productIcon = productIcon;
            }

            public String getDiscountInfo() {
                return discountInfo;
            }

            public void setDiscountInfo(String discountInfo) {
                this.discountInfo = discountInfo;
            }

            public String getProductNo() {
                return productNo;
            }

            public void setProductNo(String productNo) {
                this.productNo = productNo;
            }

            public String getSpec() {
                return spec;
            }

            public void setSpec(String spec) {
                this.spec = spec;
            }

            public String getUnit() {
                return unit;
            }

            public void setUnit(String unit) {
                this.unit = unit;
            }

            public String getManufacturer() {
                return manufacturer;
            }

            public void setManufacturer(String manufacturer) {
                this.manufacturer = manufacturer;
            }
        }

        public static class OrderCirculationsBean {
            /**
             * createTime : 2018-10-15 18:37:41
             * orderCirculationId : {"orderId":"m181015183740817927","orderStatus":"ORDERED"}
             */

            private String createTime;
            private OrderCirculationIdBean orderCirculationId;

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public OrderCirculationIdBean getOrderCirculationId() {
                return orderCirculationId;
            }

            public void setOrderCirculationId(OrderCirculationIdBean orderCirculationId) {
                this.orderCirculationId = orderCirculationId;
            }

            public static class OrderCirculationIdBean {
                /**
                 * orderId : m181015183740817927
                 * orderStatus : ORDERED
                 */

                private String orderId;
                private String orderStatus;

                public String getOrderId() {
                    return orderId;
                }

                public void setOrderId(String orderId) {
                    this.orderId = orderId;
                }

                public String getOrderStatus() {
                    return orderStatus;
                }

                public void setOrderStatus(String orderStatus) {
                    this.orderStatus = orderStatus;
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
