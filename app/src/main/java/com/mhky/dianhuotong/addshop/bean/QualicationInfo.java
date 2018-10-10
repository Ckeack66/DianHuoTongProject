package com.mhky.dianhuotong.addshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/15.
 */

public class QualicationInfo implements Serializable {
    /**
     * buyerDTO : {"id":"0"}
     * qualificationList : [{"compositeid":"0","corporation":"string","endTime":"2018-04-14T09:15:23.806Z","id":"0","name":"string","number":"string","qualificationFrom":"SHOP","scope":"string","startTime":"2018-04-14T09:15:23.806Z","url":"string"}]
     * shopDataDTO : {"address":{"city":"string","district":"string","province":"string","road":"string","town":"string"},"createTime":"string","id":"","ifShopOwner":true,"licenseImg":"string","mapPoint":"string","shopType":"DRUGSTORE","shopname":"string","status":"APPROVED"}
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

    public static class BuyerDTOBean implements Serializable {
        /**
         * id : 0
         */

        private String id;
        private String image;
        private String salesmanCode;
        private String shop_id;
        private String truename;
        private String type;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getShop_id() {
            return shop_id;
        }

        public void setShop_id(String shop_id) {
            this.shop_id = shop_id;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class ShopDataDTOBean implements Serializable {
        /**
         * address : {"city":"string","district":"string","province":"string","road":"string","town":"string"}
         * createTime : string
         * id :
         * ifShopOwner : true
         * licenseImg : string
         * mapPoint : string
         * shopType : DRUGSTORE
         * shopname : string
         * status : APPROVED
         */

        private AddressBean address;
        private String createTime;
        private String id;
        private boolean ifShopOwner;
        private String licenseImg;
        private String mapPoint;
        private String phone;
        private String postalcode;
        private String regionCode;
        private String salesmanAuditStatus;
        private String salesmanCode;
        private String shopType;
        private String shopname;
        private String status;

        public String getId() {
            return id;
        }

        public void setId(String id) {
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

        public String getSalesmanAuditStatus() {
            return salesmanAuditStatus;
        }

        public void setSalesmanAuditStatus(String salesmanAuditStatus) {
            this.salesmanAuditStatus = salesmanAuditStatus;
        }

        public String getSalesmanCode() {
            return salesmanCode;
        }

        public void setSalesmanCode(String salesmanCode) {
            this.salesmanCode = salesmanCode;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPostalcode() {
            return postalcode;
        }

        public void setPostalcode(String postalcode) {
            this.postalcode = postalcode;
        }

        public String getRegionCode() {
            return regionCode;
        }

        public void setRegionCode(String regionCode) {
            this.regionCode = regionCode;
        }

        public AddressBean getAddress() {
            return address;
        }

        public void setAddress(AddressBean address) {
            this.address = address;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
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

        public static class AddressBean implements Serializable {
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
            private String road = "";
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

    public static class QualificationListBean implements Serializable {
        /**
         * compositeid : 0
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
}
