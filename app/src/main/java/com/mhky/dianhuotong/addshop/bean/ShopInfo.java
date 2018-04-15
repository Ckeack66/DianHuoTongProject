package com.mhky.dianhuotong.addshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */

public class ShopInfo implements Serializable {

    private List<ShopinfoBean> shopinfo;

    public List<ShopinfoBean> getShopinfo() {
        return shopinfo;
    }

    public void setShopinfo(List<ShopinfoBean> shopinfo) {
        this.shopinfo = shopinfo;
    }

    public static class ShopinfoBean {
        /**
         * shopname : 抽象药业有限公司
         * mapPoint : string
         * address : {"province":"山东省","city":"济南市","district":"槐荫区","town":"段店镇","road":"泰安路恒大雅苑"}
         * shopType : DRUGSTORE
         * status : APPROVED
         * id : 9
         * licenseImg : null
         * ifShopOwner : false
         * createTime : null
         */

        private String shopname;
        private String mapPoint;
        private AddressBean address;
        private String shopType;
        private String status;
        private int id;
        private Object licenseImg;
        private boolean ifShopOwner;
        private Object createTime;

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

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
            this.createTime = createTime;
        }

        public static class AddressBean {
            /**
             * province : 山东省
             * city : 济南市
             * district : 槐荫区
             * town : 段店镇
             * road : 泰安路恒大雅苑
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
}
