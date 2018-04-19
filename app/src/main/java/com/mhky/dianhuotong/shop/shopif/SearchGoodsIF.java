package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/4/19.
 */

public interface SearchGoodsIF {
    void searchGoodsInfoSuccess(int code,String result);

    void searchGoodsInfoFailed(int code,String result);
}
