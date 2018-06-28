package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class StarCompanyInfo implements Serializable {
    /**
     * id : 48
     * name : 济南生产厂商
     * licenseImg : null
     * createTime : 2018-04-26 17:07:18.0
     * auditStatus : APPROVED
     * phone : 111212121
     * level : 2
     * type : FACTORY
     * address : {"province":"辽宁省","city":"铁岭市","district":"开原市","town":"黄旗寨满族镇","road":"111"}
     * logo : http://116.255.155.156:9040/20180426\COMPANY\15247333860467115.jpg
     * sendAccount : 500
     * freight : 80
     * followupstreamId : 11
     */

    private int id;
    private String name;
    private Object licenseImg;
    private String createTime;
    private String auditStatus;
    private String phone;
    private int level;
    private String type;
    private AddressBean address;
    private String logo;
    private int sendAccount;
    private int freight;
    private int followupstreamId;

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

    public Object getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(Object licenseImg) {
        this.licenseImg = licenseImg;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(int sendAccount) {
        this.sendAccount = sendAccount;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }

    public int getFollowupstreamId() {
        return followupstreamId;
    }

    public void setFollowupstreamId(int followupstreamId) {
        this.followupstreamId = followupstreamId;
    }

    public static class AddressBean {
        /**
         * province : 辽宁省
         * city : 铁岭市
         * district : 开原市
         * town : 黄旗寨满族镇
         * road : 111
         */

        private String province;
        private String city;
        private String district;
        private String town;
        private String road;

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }

        public String getTown() {
            return town;
        }

        public void setTown(String town) {
            this.town = town;
        }

        public String getRoad() {
            return road;
        }

        public void setRoad(String road) {
            this.road = road;
        }
    }
}
