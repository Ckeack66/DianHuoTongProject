package com.mhky.yaolinwang.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：C端客户收货地址实体类
 */
public class CustomerAddressBean implements Serializable {

    /**
     * id : 3
     * address : {"province":"山东省","city":"济南","district":"天桥区","town":"工人新村北村办事处","road":"标山小区16号"}
     * mobile : 15665788175
     * enable : true
     * new : false
     */

    private int id;
    private AddressBean address;
    private String mobile;
    private boolean enable;
    @SerializedName("new")
    private boolean newX;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public static class AddressBean implements Serializable{
        /**
         * province : 山东省
         * city : 济南
         * district : 天桥区
         * town : 工人新村北村办事处
         * road : 标山小区16号
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
