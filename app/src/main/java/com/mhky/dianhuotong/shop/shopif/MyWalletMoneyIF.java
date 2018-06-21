package com.mhky.dianhuotong.shop.shopif;

public interface MyWalletMoneyIF {
    void getMyWalletMoneySuccess(int code, String result);

    void getMyWalletMoneyFailed(int code, String result);
}
