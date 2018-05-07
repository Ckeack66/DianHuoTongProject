package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/5/4.
 */

public interface ShopIF {
    void getShopInfoSuccess(int code, String result);

    void getShopInfoFailed(int code, String result);

    void getShopTypeSuccess(int code, String result);

    void getShopTypeFailed(int code, String result);
}
