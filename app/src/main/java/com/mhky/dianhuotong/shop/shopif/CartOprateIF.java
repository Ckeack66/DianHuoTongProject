package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator on 2018/4/23.
 */

public interface CartOprateIF {
    void addCartSucess(int code, String result);

    void addCartFaild(int code, String result);

    void deleteCartSucess(int code, String result);

    void deleteCartFaild(int code, String result);

    void getCartSucess(int code, String result,int type);

    void getCartFaild(int code, String result,int type);

    void alterCartSucess(int code, String result,int type);

    void alterCartFaild(int code, String result,int type);

    void getSkuSucess(int code, String result);

    void getSkuFaild(int code, String result);
}
