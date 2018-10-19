package com.mhky.yaolinwang.order.view;

import com.mhky.dianhuotong.base.BaseView;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：获取C 端订单列表View
 */
public interface CustomerOrdersView extends BaseView {
    /**
     *
     * @param data
     */
    void getCustomerOrdersSuccess(String data);
    void getCustomerOrdersFailed(String data);
}
