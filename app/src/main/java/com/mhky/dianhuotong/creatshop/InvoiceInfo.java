package com.mhky.dianhuotong.creatshop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/14.
 */

public class InvoiceInfo implements Serializable {
    /**
     * buyerDTO : {"id":0,"image":"string","shop_id":0,"truename":"string","type":0}
     * qualificationList : [{"compositeid":0,"corporation":"string","endTime":"2018-04-13T07:26:28.358Z","id":0,"name":"string","number":"string","qualificationFrom":"SHOP","scope":"string","startTime":"2018-04-13T07:26:28.360Z","url":"string"}]
     * shopDataDTO : {"address":{"city":"string","district":"string","province":"string","road":"string","town":"string"},"id":0,"ifShopOwner":true,"licenseImg":"string","mapPoint":"string","shopType":"DRUGSTORE","shopname":"string","status":"APPROVED"}
     */

    private BuyerDTOBean buyerDTO;
    private ShopDataDTOBean shopDataDTO;
    private List<QualificationListBean> qualificationList;

    public BuyerDTOBean getBuyerDTO() {
        return buyerDTO;
    }

    public void setBuyerDTO(BuyerDTOBean buyerDTO) {
        this.buyerDTO = buyerDTO;
    }

    public ShopDataDTOBean getShopDataDTO() {
        return shopDataDTO;
    }

    public void setShopDataDTO(ShopDataDTOBean shopDataDTO) {
        this.shopDataDTO = shopDataDTO;
    }

    public List<QualificationListBean> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<QualificationListBean> qualificationList) {
        this.qualificationList = qualificationList;
    }

    public static class BuyerDTOBean {
        /**
         * id : 0
         * image : string
         * shop_id : 0
         * truename : string
         * type : 0
         */

        private int id;
        private String image;
        private int shop_id;
        private String truename;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getShop_id() {
            return shop_id;
        }

        public void setShop_id(int shop_id) {
            this.shop_id = shop_id;
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
    }

    public static class ShopDataDTOBean {
        /**
         * address : {"city":"string","district":"string","province":"string","road":"string","town":"string"}
         * id : 0
         * ifShopOwner : true
         * licenseImg : string
         * mapPoint : string
         * shopType : DRUGSTORE
         * shopname : string
         * status : APPROVED
         */

        private AddressBean address;
        private int id;
        private boolean ifShopOwner;
        private String licenseImg;
        private String mapPoint;
        private String shopType;
        private String shopname;
        private String status;

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isIfShopOwner() {
            return ifShopOwner;
        }

        public void setIfShopOwner(boolean ifShopOwner) {
            this.ifShopOwner = ifShopOwner;
        }

        public String getLicenseImg() {
            return licenseImg;
        }

        public void setLicenseImg(String licenseImg) {
            this.licenseImg = licenseImg;
        }

        public String getMapPoint() {
            return mapPoint;
        }

        public void setMapPoint(String mapPoint) {
            this.mapPoint = mapPoint;
        }

        public String getShopType() {
            return shopType;
        }

        public void setShopType(String shopType) {
            this.shopType = shopType;
        }

        public String getShopname() {
            return shopname;
        }

        public void setShopname(String shopname) {
            this.shopname = shopname;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public static class AddressBean {
            /**
             * city : string
             * district : string
             * province : string
             * road : string
             * town : string
             */

            private String city;
            private String district;
            private String province;
            private String road;
            private String town;

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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getRoad() {
                return road;
            }

            public void setRoad(String road) {
                this.road = road;
            }

            public String getTown() {
                return town;
            }

            public void setTown(String town) {
                this.town = town;
            }
        }
    }

    public static class QualificationListBean {
        /**
         * compositeid : 0
         * corporation : string
         * endTime : 2018-04-13T07:26:28.358Z
         * id : 0
         * name : string
         * number : string
         * qualificationFrom : SHOP
         * scope : string
         * startTime : 2018-04-13T07:26:28.360Z
         * url : string
         */

        private int compositeid;
        private String corporation;
        private String endTime;
        private int id;
        private String name;
        private String number;
        private String qualificationFrom;
        private String scope;
        private String startTime;
        private String url;

        public int getCompositeid() {
            return compositeid;
        }

        public void setCompositeid(int compositeid) {
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
}
