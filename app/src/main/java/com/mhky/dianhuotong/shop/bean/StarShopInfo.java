package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class StarShopInfo implements Serializable{
    /**
     * id : 1
     * shopId : 9
     * companyId : 48
     * new : false
     */

    private String id;
    private String shopId;
    private String companyId;
    private boolean newX;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }
}
