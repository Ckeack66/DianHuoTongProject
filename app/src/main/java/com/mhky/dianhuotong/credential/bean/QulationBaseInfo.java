package com.mhky.dianhuotong.credential.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/17.
 */

public class QulationBaseInfo implements Serializable {
    /**
     * compositeid :
     * corporation : string
     * endTime : 2018-04-14T09:15:23.806Z
     * id : 0
     * name : string
     * number : string
     * qualificationFrom : SHOP
     * scope : string
     * startTime : 2018-04-14T09:15:23.806Z
     * url : string
     */

    private String compositeid = "";
    private String corporation;
    private String endTime;
    private String id = "1";
    private String name = "药品经营质量管理规范认证证书（GSP）";
    private String number;
    private String qualificationFrom = "SHOP";
    private String scope;
    private String startTime;
    private String url;

    public String getCompositeid() {
        return compositeid;
    }

    public void setCompositeid(String compositeid) {
        this.compositeid = compositeid;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

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

    public String getQualificationFrom() {
        return qualificationFrom;
    }

    public void setQualificationFrom(String qualificationFrom) {
        this.qualificationFrom = qualificationFrom;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}