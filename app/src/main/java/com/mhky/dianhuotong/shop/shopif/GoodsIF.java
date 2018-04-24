package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/4/23.
 */

public interface GoodsIF {
    void getGoodsInfoSuccess(int code, String result);

    void getGoodsInfoFailed(int code, String result);
}
