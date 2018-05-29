package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11.
 */

public class ShopInfoByUserID implements Serializable {
    /**
     * id : 17
     * shopname : 惠民大药房
     * mapPoint : 116.901971,36.682196
     * address : {"province":"山东省","city":"济南市","district":"槐荫区","town":"腊山街道办事处","road":"大柏树235号"}
     * shopType : DRUGSTORE
     * status : UNAUDITED
     * buyers : [{"id":45,"username":"药大仙7398","password":"$2a$10$i5RMCbc5xuXmWLUJjemjrehqFDw8zRSILdZwe6bEFn5v2ZBV3aNFm","mobile":"15043214322","createTime":"2018-05-11 14:34:12","truename":null,"type":1,"image":null,"new":false},{"id":19,"username":"孙大香","password":"$2a$10$O4DSr5thmt1hJpbXtZnpOuio7TpUAthKQuBS3oxSX7sUI6jut./ea","mobile":"15043214321","createTime":"2018-04-14 16:23:52","truename":"大张伟","type":1,"image":"http://192.168.2.158:9040/20180415\\USER\\15237715444861528.jpg","new":false}]
     * createTime : null
     */

    private int id;
    private String shopname;
    private String mapPoint;
    private AddressBean address;
    private String shopType;
    private String status;
    private Object createTime;

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    private String regionCode;
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

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Object createTime) {
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
         * town : 腊山街道办事处
         * road : 大柏树235号
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
         * id : 45
         * username : 药大仙7398
         * password : $2a$10$i5RMCbc5xuXmWLUJjemjrehqFDw8zRSILdZwe6bEFn5v2ZBV3aNFm
         * mobile : 15043214322
         * createTime : 2018-05-11 14:34:12
         * truename : null
         * type : 1
         * image : null
         * new : false
         */

        private int id;
        private String username;
        private String password;
        private String mobile;
        private String createTime;
        private Object truename;
        private int type;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
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
}
