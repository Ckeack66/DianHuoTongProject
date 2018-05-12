package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/5/8.
 */

public interface BanlanceIF {
    void doBanlanceSucess(int code, String result);
    void doBanlanceFaild(int code, String result);
    void getPayCodeSucess(int code, String result);
    void getPayCodeFaild(int code, String result);
}
