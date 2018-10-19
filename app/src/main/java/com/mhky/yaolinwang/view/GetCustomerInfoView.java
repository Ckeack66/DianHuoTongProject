package com.mhky.yaolinwang.view;

import com.mhky.dianhuotong.base.BaseView;

/**
 * Created by Administrator  on  2018/10/15
 * Describe：获取Customer详情实体类
 */
public interface GetCustomerInfoView extends BaseView {
    void getCustomerInfoSuccess(String data);
    void getCustomerInfoFailed(String data);

    void getDefaultAdressSuccess(String data);
    void getDefaultAdressFailed(String data);
}
