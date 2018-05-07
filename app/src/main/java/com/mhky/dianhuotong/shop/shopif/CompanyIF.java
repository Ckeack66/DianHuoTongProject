package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/5/5.
 */

public interface CompanyIF {
    void getCompanyCredentialSucess(int code, String result);

    void getCompanyCredentialFaild(int code, String result);
    void getCompanyTansferSucess(int code, String result);

    void getCompanyTansferFaild(int code, String result);

}
