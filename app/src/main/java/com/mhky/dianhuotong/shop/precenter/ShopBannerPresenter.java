package com.mhky.dianhuotong.shop.precenter;

import com.mhky.dianhuotong.shop.shopif.ShopBannerIF;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ShopBannerPresenter {
    private ShopBannerIF shopBannerIF;
    private ArrayList<String> arrayListImageUrl = new ArrayList<String>();

    public ShopBannerPresenter(ShopBannerIF shopBannerIF) {
        this.shopBannerIF = shopBannerIF;
    }

    public void getdata() {
        arrayListImageUrl.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        arrayListImageUrl.add("http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        arrayListImageUrl.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
        shopBannerIF.getDataIF(arrayListImageUrl);
    }
}
