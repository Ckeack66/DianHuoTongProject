package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class ShopAdressInfo implements Serializable {
    /**
     * shopname : string
     * mapPoint : string
     * address : {"province":"山东省","city":"济南是","district":"天桥区","town":"标山社区","road":"彪删削去"}
     * shopType : DRUGSTORE
     * status : APPROVED
     * id : 1
     * licenseImg : null
     * ifShopOwner : false
     * createTime : 2018-04-14 09:42:03.0
     */

    private String shopname;
    private String mapPoint;
    private AddressBean address;
    private String shopType;
    private String status;
    private int id;
    private Object licenseImg;
    private boolean ifShopOwner;
    private String createTime;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(Object licenseImg) {
        this.licenseImg = licenseImg;
    }

    public boolean isIfShopOwner() {
        return ifShopOwner;
    }

    public void setIfShopOwner(boolean ifShopOwner) {
        this.ifShopOwner = ifShopOwner;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static class AddressBean {
        /**
         * province : 山东省
         * city : 济南是
         * district : 天桥区
         * town : 标山社区
         * road : 彪删削去
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
