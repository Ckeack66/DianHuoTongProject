package com.mhky.dianhuotong.shop.shopif;

public interface OrderInfoIF {
    void getOrderInfoSuccess(int code, String result);
    void getOrderInfoFailed(int code, String result);
}
