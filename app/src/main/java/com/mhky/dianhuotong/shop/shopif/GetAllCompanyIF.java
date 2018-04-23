package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/4/21.
 */

public interface GetAllCompanyIF {
    void getAllCompanyInfoSuccess(int code, String result);

    void getAllCompanyInfoFailed(int code, String result);
}
