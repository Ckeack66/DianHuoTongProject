package com.mhky.dianhuotong.shop.shopif;

public interface CounponGetIF {
    void getCouponSuccess(int code, String result);
    void getCouponFailed(int code, String result);
}
