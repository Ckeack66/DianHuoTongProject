package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/2.
 * 资质证明  实体类
 */

public class ShopCredentialBaseInfo implements Serializable ,MultiItemEntity{
    /**
     * id : 106
     * name : 药品经营质量管理规范认证证书（GSP）
     * number : 150333256698866666
     * startTime : 2018-01-02
     * endTime : 2019-01-02
     * scope : 药品
     * corporation : 波斯猫
     * qualificationFrom : SHOP
     * url : http://192.168.2.158:9040/20180418\USER\15240330906844262.jpeg
     * compositeid : 16
     */
    public static final int CREDETIAL = 1;
    public static final int CREDENTIAL_ADD = 2;

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    private int itemType=1;
    private String id;
    private String name;
    private String number;
    private String startTime;
    private String endTime;
    private String scope;
    private String corporation;
    private String qualificationFrom;
    private String url;
    private String compositeid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getQualificationFrom() {
        return qualificationFrom;
    }

    public void setQualificationFrom(String qualificationFrom) {
        this.qualificationFrom = qualificationFrom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCompositeid() {
        return compositeid;
    }

    public void setCompositeid(String compositeid) {
        this.compositeid = compositeid;
    }
}
