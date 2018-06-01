package com.mhky.dianhuotong.shop.precenter;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.bean.CartBaseInfo;
import com.mhky.dianhuotong.shop.bean.CartBodyInfo;
import com.mhky.dianhuotong.shop.bean.CartInfo;
import com.mhky.dianhuotong.shop.bean.CartItemInfo;
import com.mhky.dianhuotong.shop.bean.CartTitleInfo;
import com.mhky.dianhuotong.shop.shopif.CartDataIF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/7.
 */

public class CartDataPresenter {
    private CartDataIF cartDataIF;
    private List<CartTitleInfo> cartTitleInfoList;
    private CartBaseInfo cartBaseInfo;
    private static final String TAG = "CartDataPresenter";

    public CartDataPresenter(CartDataIF cartDataIF) {
        this.cartDataIF = cartDataIF;
        cartTitleInfoList = new ArrayList<>();
    }

    private void getCartData(String cartdata) {
        cartBaseInfo = JSON.parseObject(cartdata, CartBaseInfo.class);
        BaseTool.logPrint(TAG, "getCartData: -------" + cartBaseInfo.getGoodsItems().size());
        for (int a = 0; a < cartBaseInfo.getGoodsItems().size(); a++) {
            CartTitleInfo cartTitleInfo = new CartTitleInfo();
            List<CartBaseInfo.GoodsItemsBean> cartItemInfoList = new ArrayList<>();
            cartTitleInfo.setCartItemInfoList(cartItemInfoList);
            if (a == 0) {
                cartTitleInfo.setShopDTOBean(cartBaseInfo.getGoodsItems().get(a).getShopDTO());
                cartTitleInfo.setViewTab(true);//设置只显示第一个分割符
                cartTitleInfoList.add(cartTitleInfo);
            } else {
                for (int b = 0; b < cartTitleInfoList.size(); b++) {
                    if (cartBaseInfo.getGoodsItems().get(a).getShopDTO().getId().equals(cartTitleInfoList.get(b).getShopDTOBean().getId())) {
                        break;
                    } else {
                        if (b == cartTitleInfoList.size() - 1) {
                            cartTitleInfo.setShopDTOBean(cartBaseInfo.getGoodsItems().get(a).getShopDTO());
                            cartTitleInfoList.add(cartTitleInfo);
                        }

                    }
                }

            }

        }
        for (int a = 0; a < cartTitleInfoList.size(); a++) {
            cartTitleInfoList.get(a).setParentId(a);
            for (int b = 0; b < cartBaseInfo.getGoodsItems().size(); b++) {
                if (cartTitleInfoList.get(a).getShopDTOBean().getId().equals(cartBaseInfo.getGoodsItems().get(b).getShopDTO().getId())) {
                    cartTitleInfoList.get(a).getCartItemInfoList().add(cartBaseInfo.getGoodsItems().get(b));
                }
            }
        }
    }
    public List<CartInfo>  getCartInfoList(String cartdata){
        getCartData(cartdata);
        List<CartInfo> cartInfoList=new ArrayList<>();
        for (int a = 0; a < cartTitleInfoList.size(); a++) {
            CartInfo cartInfoTitle = new CartInfo(true, "", cartTitleInfoList.get(a));
            cartInfoList.add(cartInfoTitle);
            for (int b = 0; b < cartTitleInfoList.get(a).getCartItemInfoList().size(); b++) {
                CartBodyInfo cartBodyInfo = new CartBodyInfo();
                cartBodyInfo.setParentNumber(a);
                cartBodyInfo.setChildNumber(cartTitleInfoList.get(a).getCartItemInfoList().size());
                cartBodyInfo.setChildIndex(b);
                cartBodyInfo.setGoodsItemsBean(cartTitleInfoList.get(a).getCartItemInfoList().get(b));
                CartInfo cartInfoBody = new CartInfo(cartBodyInfo);
                cartInfoList.add(cartInfoBody);
            }
        }
        return cartInfoList;
    }


}
