package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator  on  2018/9/9
 * Describe：
 */
public interface HavedCouponIF {

    void getHavedCouponSuccess(int code, String result);
    void getHavedCouponFailed(int code, String result);

}
