package com.mhky.dianhuotong.shop.shopif;

public interface StarShopIF extends StarShopGetIF,StarShopAddIF,StarShopDeleteIF{
    void getStarShopSuccess(int code, String result);
    void getStarShopInfoFailed(int code, String result);
    void addStarShopSuccess(int code, String result);
    void addStarShopInfoFailed(int code, String result);
    void deleteStarShopSuccess(int code, String result);
    void deleteStarShopInfoFailed(int code, String result);
}
