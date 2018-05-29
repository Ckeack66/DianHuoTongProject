package com.mhky.dianhuotong.shop.shopif;

public interface SearchCompanyIF {
    void getCompanyListSuccess(int code, String result);
    void getCompanyListFailed(int code, String result);
}
