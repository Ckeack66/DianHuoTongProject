package com.mhky.dianhuotong.shop.shopif;

public interface CouponIF extends CounponGetIF,CounponAddIF {
    @Override
    void addCouponSuccess(int code, String result);

    @Override
    void addCouponFailed(int code, String result);

    @Override
    void getCouponSuccess(int code, String result);

    @Override
    void getCouponFailed(int code, String result);
}
