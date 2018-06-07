package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class StarShopInfo implements Serializable{


    /**
     * id : 53
     * name : 上游B测试公司
     * licenseImg : null
     * createTime : 2018-05-21 07:27:38.0
     * auditStatus : APPROVED
     * phone : 05318772630
     * level : 1
     * type : FACTORY
     * address : {"province":"山东省","city":"济南市","district":"槐荫区","town":"腊山街道办事处","road":"中建锦绣城2期7号楼1802室"}
     * logo : http://116.255.155.156:9040/20180521/COMPANY/15268828598126815.png
     * sendAccount : 200
     * freight : 0
     * followupstreamId : 17
     */

    private String id;
    private String name;
    private Object licenseImg;
    private String createTime;
    private String auditStatus;
    private String phone;
    private String level;
    private String type;
    private AddressBean address;
    private String logo;
    private String sendAccount;
    private String freight;
    private String followupstreamId;

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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
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

    public String getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(String sendAccount) {
        this.sendAccount = sendAccount;
    }

    public String getFreight() {
        return freight;
    }

    public void setFreight(String freight) {
        this.freight = freight;
    }

    public String getFollowupstreamId() {
        return followupstreamId;
    }

    public void setFollowupstreamId(String followupstreamId) {
        this.followupstreamId = followupstreamId;
    }

    public static class AddressBean {
        /**
         * province : 山东省
         * city : 济南市
         * district : 槐荫区
         * town : 腊山街道办事处
         * road : 中建锦绣城2期7号楼1802室
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
