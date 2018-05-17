package com.mhky.dianhuotong.shop.shopif;

public interface StarGoodsDeleteIF {
    void deleteStarGoodsSuccess(int code, String result);
    void deleteStarGoodsFailed(int code, String result);
}
