package com.mhky.dianhuotong.shop.shopif;

public interface StarGoodsGetIF {
    void getStarGoodsSuccess(int code, String result);
    void getStarGoodsFailed(int code, String result);
}
