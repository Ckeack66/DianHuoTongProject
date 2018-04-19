package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/4/19.
 */

public interface AllGoodsIF {
    void getAllGoodsInfoSuccess(int code,String result);

    void getAllGoodsInfoFailed(int code,String result);

}
