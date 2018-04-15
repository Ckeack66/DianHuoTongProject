package com.mhky.dianhuotong.addshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/15.
 */

public class BindShopInfo implements Serializable {
    /**
     * id : 0
     * shop_id : 0
     * type : 0
     */

    private String id;
    private String shop_id;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
