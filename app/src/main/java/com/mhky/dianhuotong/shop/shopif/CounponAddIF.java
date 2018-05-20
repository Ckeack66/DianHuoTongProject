package com.mhky.dianhuotong.shop.shopif;

public interface CounponAddIF {
    void addCouponSuccess(int code, String result);
    void addCouponFailed(int code, String result);
}
