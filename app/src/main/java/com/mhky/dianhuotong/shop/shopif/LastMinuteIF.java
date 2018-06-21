package com.mhky.dianhuotong.shop.shopif;

public interface LastMinuteIF {
    void getLastMinuteSuccess(int code, String result);
    void getLastMinuteFailed(int code, String result);
}
