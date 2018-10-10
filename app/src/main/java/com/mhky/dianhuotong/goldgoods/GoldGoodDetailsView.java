package com.mhky.dianhuotong.goldgoods;

import com.mhky.dianhuotong.base.BaseView;

/**
 * Created by Administrator  on  2018/8/20
 * Describe：
 */
public interface GoldGoodDetailsView extends BaseView {

    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void getGoldGoodDetailsSuccess(String data);
}
