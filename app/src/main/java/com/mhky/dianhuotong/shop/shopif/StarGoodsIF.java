package com.mhky.dianhuotong.shop.shopif;

public interface StarGoodsIF extends StarGoodsAddIF,StarGoodsGetIF,StarGoodsDeleteIF{

    @Override
    void addStarGoodsSuccess(int code, String result);

    @Override
    void addStarGoodsFailed(int code, String result);

    @Override
    void deleteStarGoodsSuccess(int code, String result);

    @Override
    void deleteStarGoodsFailed(int code, String result);

    @Override
    void getStarGoodsSuccess(int code, String result);

    @Override
    void getStarGoodsFailed(int code, String result);
}
