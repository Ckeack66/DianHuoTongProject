package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class MyWalletMoneyInfo implements Serializable {
    /**
     * id : 25
     * name : 药大仙7319
     * phone : 15665788176
     * weixin : null
     * code : 7683
     * idCard : null
     * source : SHOP
     * image : http://192.168.2.161:9071/extension/image?mobile=15665788176
     * wallet : 0
     * gainMoney : 0
     * loseMoney : 0
     * waitMoney : 0
     * createTime : 2018-05-30 15:09:18
     * lastModifyTime : 2018-05-30 15:09:18
     */

    private int id;
    private String name;
    private String phone;
    private Object weixin;
    private String code;
    private Object idCard;
    private String source;
    private String image;
    private int wallet;
    private int gainMoney;
    private int loseMoney;
    private int waitMoney;
    private String createTime;
    private String lastModifyTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Object getWeixin() {
        return weixin;
    }

    public void setWeixin(Object weixin) {
        this.weixin = weixin;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getIdCard() {
        return idCard;
    }

    public void setIdCard(Object idCard) {
        this.idCard = idCard;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getWallet() {
        return wallet;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getGainMoney() {
        return gainMoney;
    }

    public void setGainMoney(int gainMoney) {
        this.gainMoney = gainMoney;
    }

    public int getLoseMoney() {
        return loseMoney;
    }

    public void setLoseMoney(int loseMoney) {
        this.loseMoney = loseMoney;
    }

    public int getWaitMoney() {
        return waitMoney;
    }

    public void setWaitMoney(int waitMoney) {
        this.waitMoney = waitMoney;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
