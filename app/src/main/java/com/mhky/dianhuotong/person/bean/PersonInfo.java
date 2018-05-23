package com.mhky.dianhuotong.person.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/12.
 */

public class PersonInfo implements Serializable{


    /**
     * username : 妙药仙8601
     * mobile : 15012341234
     * enabled : true
     * image : null
     * shopId : null
     * shopName : null
     * id : 11
     * truename : null
     * type : null
     */

    private String username;
    private String mobile;
    private boolean enabled;
    private Object image;
    private Object shopId;
    private Object shopName;
    private String  id;
    private Object truename;
    private Object type;

    public Object getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Object auditStatus) {
        this.auditStatus = auditStatus;
    }

    private Object auditStatus;

    public Object getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(Object salesmanCode) {
        this.salesmanCode = salesmanCode;
    }

    private Object salesmanCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getShopId() {
        return shopId;
    }

    public void setShopId(Object shopId) {
        this.shopId = shopId;
    }

    public Object getShopName() {
        return shopName;
    }

    public void setShopName(Object shopName) {
        this.shopName = shopName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getTruename() {
        return truename;
    }

    public void setTruename(Object truename) {
        this.truename = truename;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }
}
