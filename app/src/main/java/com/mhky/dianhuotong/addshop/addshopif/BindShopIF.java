package com.mhky.dianhuotong.addshop.addshopif;

/**
 * Created by Administrator on 2018/4/15.
 */

public interface BindShopIF {
    void bindShopInfoSuccess(int code, String result);

    void bindShopInfoFailed(int code, String result);
}
