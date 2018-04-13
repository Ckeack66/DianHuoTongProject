package com.mhky.dianhuotong.login;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/11.
 */

public class LoginRequestInfo implements Serializable {

    /**
     * username : 妙药仙8601
     * password : $2a$10$kJHtAPJFPCN79OW2mQ4wiusRutwxJa1hfLAo97wOH5.TtOwlOryVK
     * mobile : 15012341234
     * enabled : true
     * image : null
     * shopId : null
     * id : 11
     * truename : null
     * type : null
     */

    private String username;
    private String password;
    private String mobile;
    private boolean enabled;
    private Object image;
    private Object shopId;
    private String id;
    private Object truename;
    private Object type;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
