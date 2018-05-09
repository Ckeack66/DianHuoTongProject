package com.mhky.dianhuotong.shop.shopif;

import com.mhky.dianhuotong.shop.bean.CartInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */

public interface CartDataIF {
    void getCartData(List<CartInfo> cartInfoList);
}
