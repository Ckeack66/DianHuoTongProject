package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class ShopInfoByUserID implements Serializable {

    /**
     * id : 57
     * shopname : 济南平民药店
     * mapPoint : 116.902086,36.682308
     * address : {"province":"山东省","city":"济南市","district":"槐荫区","town":"道德街街道办事处","road":"齐州路1850"}
     * shopType : DRUGSTORE
     * status : APPROVED
     * regionCode : 370104003000
     * postalcode : 024000
     * phone : 02188923367
     * buyers : [{"id":76,"username":"药大仙2748","password":"$2a$10$LieH5O9VppLDaP4GNKdBt.Yap43y0TMXIYN/1EeDlOHL2CD91WRDW","mobile":"15012341234","createTime":"2018-06-05 10:07:07","truename":null,"type":1,"image":null,"salesmanCode":null,"auditStatus":"APPROVED","new":false}]
     * createTime : 2018-06-05 10:09:25
     */

    private int id;
    private String shopname;
    private String mapPoint;
    private AddressBean address;
    private String shopType;
    private String status;
    private String regionCode;
    private String postalcode;
    private String phone;
    private String createTime;
    private List<BuyersBean> buyers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getMapPoint() {
        return mapPoint;
    }

    public void setMapPoint(String mapPoint) {
        this.mapPoint = mapPoint;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<BuyersBean> getBuyers() {
        return buyers;
    }

    public void setBuyers(List<BuyersBean> buyers) {
        this.buyers = buyers;
    }

    public static class AddressBean {
        /**
         * province : 山东省
         * city : 济南市
         * district : 槐荫区
         * town : 道德街街道办事处
         * road : 齐州路1850
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

    public static class BuyersBean {
        /**
         * id : 76
         * username : 药大仙2748
         * password : $2a$10$LieH5O9VppLDaP4GNKdBt.Yap43y0TMXIYN/1EeDlOHL2CD91WRDW
         * mobile : 15012341234
         * createTime : 2018-06-05 10:07:07
         * truename : null
         * type : 1
         * image : null
         * salesmanCode : null
         * auditStatus : APPROVED
         * new : false
         */

        private int id;
        private String username;
        private String password;
        private String mobile;
        private String createTime;
        private String truename;
        private int type;
        private String image;
        private String salesmanCode;
        private String auditStatus;
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

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSalesmanCode() {
            return salesmanCode;
        }

        public void setSalesmanCode(String salesmanCode) {
            this.salesmanCode = salesmanCode;
        }

        public String getAuditStatus() {
            return auditStatus;
        }

        public void setAuditStatus(String auditStatus) {
            this.auditStatus = auditStatus;
        }

        public boolean isNewX() {
            return newX;
        }

        public void setNewX(boolean newX) {
            this.newX = newX;
        }
    }
}
