package com.mhky.dianhuotong.shop.shopif;

public interface OrderOkIF {
    void getOrderFrightSucess(int code, String result);

    void getOrderFrightFaild(int code, String result);
}
