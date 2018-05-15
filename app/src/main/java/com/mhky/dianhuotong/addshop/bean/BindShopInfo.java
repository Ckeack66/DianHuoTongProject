package com.mhky.dianhuotong.addshop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/15.
 */

public class BindShopInfo implements Serializable {

    /**
     * id : 0
     * image : string
     * salesmanCode : string
     * shop_id : 0
     * truename : string
     * type : 0
     */

    private String id;
    private String image;
    private String salesmanCode;
    private String shop_id;
    private String truename;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(String salesmanCode) {
        this.salesmanCode = salesmanCode;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
