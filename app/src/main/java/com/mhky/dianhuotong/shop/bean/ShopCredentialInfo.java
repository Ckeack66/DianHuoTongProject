package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2018/5/2.
 */

public class ShopCredentialInfo extends SectionEntity<String> {
    public ShopCredentialBaseInfo getShopCredentialBaseInfo() {
        return shopCredentialBaseInfo;
    }
    private ShopCredentialBaseInfo shopCredentialBaseInfo;

    public ShopCredentialInfo(boolean isHeader, String header, ShopCredentialBaseInfo shopCredentialBaseInfo1) {
        super(isHeader, header);
        shopCredentialBaseInfo = shopCredentialBaseInfo1;
    }

    public ShopCredentialInfo(String s) {
        super(s);
    }
}
