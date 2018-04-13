package com.mhky.dianhuotong.register.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/11.
 */

public class RegisterInfo implements Serializable {
    /**
     * id : 12
     * username : 妙药仙6972
     * password : $2a$10$pt/jqGeSXCD.FKpqb10Xz.7uMT6MOvq1SpfKvGb.tGZn3C8I4ue5S
     * mobile : 15012342345
     * createTime : 2018-04-11 19:38:27
     * truename : null
     * type : null
     * image : null
     * new : false
     */

    private int id;
    private String username;
    private String password;
    private String mobile;
    private String createTime;
    private Object truename;
    private Object type;
    private Object image;
    private boolean newX;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }
}
