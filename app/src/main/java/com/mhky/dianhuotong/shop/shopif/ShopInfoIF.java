package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/5/11.
 */

public interface ShopInfoIF {
    void getShopInfoSuccess(int code, String result);
    void getShopInfoFailed(int code, String result);
}
