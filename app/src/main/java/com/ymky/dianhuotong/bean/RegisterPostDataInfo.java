package com.ymky.dianhuotong.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */

public class RegisterPostDataInfo implements Serializable {
    /**
     * authorities : ["1"]
     * enabled : true
     * mobile : 13212341111
     * password : 123456
     * username : 123456
     */

    private boolean enabled;
    private String mobile;
    private String password;
    private String username;
    private List<String> authorities;

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

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
}
