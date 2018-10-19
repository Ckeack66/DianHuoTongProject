package com.mhky.yaolinwang.order.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderBottomInfo;
import com.mhky.dianhuotong.shop.bean.OrderTopInfo;

/**
 * Created by Administrator  on  2018/10/17
 * Describe：C端订单列表Adapter
 */
public class CustomerOrder implements MultiItemEntity {

    public static final int TOP = 1;
    public static final int BODY = 2;
    public static final int BOTTOM = 3;

    private String orderId;
    private int type;

    private CustomerOrderTopInfo customerOrderTopInfo;
    private CustomerOrderBean.ContentBean.OrderDetailSetBean orderDetailSetBean;
    private CustomerOrderBottomInfo customerOrderBottomInfo;

    public CustomerOrder(int type) {
        this.type = type;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public CustomerOrderTopInfo getCustomerOrderTopInfo() {
        return customerOrderTopInfo;
    }

    public void setCustomerOrderTopInfo(CustomerOrderTopInfo customerOrderTopInfo) {
        this.customerOrderTopInfo = customerOrderTopInfo;
    }

    public CustomerOrderBean.ContentBean.OrderDetailSetBean getOrderDetailSetBean() {
        return orderDetailSetBean;
    }

    public void setOrderDetailSetBean(CustomerOrderBean.ContentBean.OrderDetailSetBean orderDetailSetBean) {
        this.orderDetailSetBean = orderDetailSetBean;
    }

    public CustomerOrderBottomInfo getCustomerOrderBottomInfo() {
        return customerOrderBottomInfo;
    }

    public void setCustomerOrderBottomInfo(CustomerOrderBottomInfo customerOrderBottomInfo) {
        this.customerOrderBottomInfo = customerOrderBottomInfo;
    }

    @Override
    public int getItemType() {
        return type;
    }
}
