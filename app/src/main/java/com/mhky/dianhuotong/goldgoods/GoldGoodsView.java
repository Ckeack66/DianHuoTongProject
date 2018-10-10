package com.mhky.dianhuotong.goldgoods;

import com.mhky.dianhuotong.base.BaseView;

/**
 * 所有黄金单品   View层
 */

public interface GoldGoodsView extends BaseView{

    /**
     * 当数据请求成功后，调用此接口显示数据
     * @param data 数据源
     */
    void showData(String data,int isRefreshOrLoadMore);

}
