package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/5/3.
 */

public interface OrderIF {
    void getOrderSucess(int code, String result);

    void getOrderFaild(int code, String result);
}
