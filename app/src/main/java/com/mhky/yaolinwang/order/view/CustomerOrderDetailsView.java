package com.mhky.yaolinwang.order.view;

import com.mhky.dianhuotong.base.BaseView;

/**
 * Created by Administrator  on  2018/10/19
 * Describe：
 */
public interface CustomerOrderDetailsView extends BaseView {

    /**
     * 获取C端订单信息详情
     * @param data
     */
    void getCustomerOrderDetailsSuccess(String data);
    void getCustomerOrderDetailsFailed(String data);
}
