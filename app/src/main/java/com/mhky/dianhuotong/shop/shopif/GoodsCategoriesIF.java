package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator  on  2018/9/5
 * Describe：
 */
public interface GoodsCategoriesIF {

    void getGoodsCategoriesSuccess(int code,String result);

    void getGoodsCategoriesFailed(int code,String result);

}
