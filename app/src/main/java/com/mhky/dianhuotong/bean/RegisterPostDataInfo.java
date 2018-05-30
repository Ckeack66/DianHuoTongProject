package com.mhky.dianhuotong.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/3/31.
 */

public class RegisterPostDataInfo implements Serializable {

    /**
     * authorities : 0
     * enabled : true
     * mobile : string
     * password : string
     * username : string
     */

    private String authorities = "2";
    private boolean enabled;
    private String mobile;
    private String password;
    private String username = "";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
