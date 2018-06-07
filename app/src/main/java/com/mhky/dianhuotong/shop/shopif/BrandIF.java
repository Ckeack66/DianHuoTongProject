package com.mhky.dianhuotong.shop.shopif;

public interface BrandIF {
    void getBrandSuccess(int code, String result);

    void getBrandFailed(int code, String result);
}
