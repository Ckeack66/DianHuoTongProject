package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/5/2.
 */

public interface ShopCredentialIF {
    void getShopCredentialSucess(int code, String result);

    void getShopCredentialFaild(int code, String result);

    void updateShopCredentialSucess(int code, String result);

    void updateShopCredentialFaild(int code, String result);

    void uploadShopCredentialSucess(int code, String result);

    void uploadShopCredentialFaild(int code, String result);
}
