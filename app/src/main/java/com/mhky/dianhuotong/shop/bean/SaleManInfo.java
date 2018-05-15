package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

public class SaleManInfo implements Serializable {


    /**
     * id : 16
     * name : 哦牛批
     * phone : 123
     * address : {"province":"辽宁省","city":"辽阳市","district":"宏伟区","town":"新村街道办事处","road":""}
     * parentId : null
     * parent : {"id":13,"name":"秀就完事了奥","phone":"123","address":{"province":"上海市","city":"市辖区","district":"虹口区","town":"曲阳路街道","road":""},"parentId":null,"parent":null,"type":"MANAGER","avatar":"http://192.168.2.158:9040/20180509/COMPANY/15258524570319395.jpg","code":"8201"}
     * type : EMPLOYEES
     * avatar : http://192.168.2.158:9040/20180430/COMPANY/15250525804029639.jpg
     * code : 8805
     */

    private int id;
    private String name;
    private String phone;
    private AddressBean address;
    private Object parentId;
    private ParentBean parent;
    private String type;
    private String avatar;
    private String code;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressBean getAddress() {
        return address;
    }

    public void setAddress(AddressBean address) {
        this.address = address;
    }

    public Object getParentId() {
        return parentId;
    }

    public void setParentId(Object parentId) {
        this.parentId = parentId;
    }

    public ParentBean getParent() {
        return parent;
    }

    public void setParent(ParentBean parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static class AddressBean {
        /**
         * province : 辽宁省
         * city : 辽阳市
         * district : 宏伟区
         * town : 新村街道办事处
         * road :
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

    public static class ParentBean {
        /**
         * id : 13
         * name : 秀就完事了奥
         * phone : 123
         * address : {"province":"上海市","city":"市辖区","district":"虹口区","town":"曲阳路街道","road":""}
         * parentId : null
         * parent : null
         * type : MANAGER
         * avatar : http://192.168.2.158:9040/20180509/COMPANY/15258524570319395.jpg
         * code : 8201
         */

        private int id;
        private String name;
        private String phone;
        private AddressBeanX address;
        private Object parentId;
        private Object parent;
        private String type;
        private String avatar;
        private String code;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public AddressBeanX getAddress() {
            return address;
        }

        public void setAddress(AddressBeanX address) {
            this.address = address;
        }

        public Object getParentId() {
            return parentId;
        }

        public void setParentId(Object parentId) {
            this.parentId = parentId;
        }

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public static class AddressBeanX {
            /**
             * province : 上海市
             * city : 市辖区
             * district : 虹口区
             * town : 曲阳路街道
             * road :
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
