package com.mhky.dianhuotong.person.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/12.
 */

public class UserInfo implements Serializable {

    /**
     * buyer_id : 11
     * enabled : true
     * image : http://192.168.2.158:9040/20180412\USER\15235266299543752.jpg
     * truename :
     * username :
     */

    private String buyer_id = "";
    private boolean enabled =true;
    private String image="";
    private String truename="";
    private String username="";

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyer_id) {
        this.buyer_id = buyer_id;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
