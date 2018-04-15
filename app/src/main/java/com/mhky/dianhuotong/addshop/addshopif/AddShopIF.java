package com.mhky.dianhuotong.addshop.addshopif;

/**
 * Created by Administrator on 2018/4/14.
 */

public interface AddShopIF {
    void getShopInfoSuccess(int code, String result);

    void getShopInfoFailed(int code, String result);
}
