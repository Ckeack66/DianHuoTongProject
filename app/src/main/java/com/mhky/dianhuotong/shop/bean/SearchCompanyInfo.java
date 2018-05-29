package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

public class SearchCompanyInfo implements Serializable {
    /**
     * content : [{"id":1,"name":"大鹏旗舰店","licenseImg":"string","createTime":null,"auditStatus":"APPROVED","phone":"string","level":2,"type":"FACTORY","address":{"province":"山东省","city":"济南是","district":"天桥区","town":"标山社区","road":"彪删削去"},"logo":"string","sendAccount":50000,"freight":1000},{"id":12,"name":"哈哈哈哈哈哈","licenseImg":null,"createTime":null,"auditStatus":"APPROVED","phone":"123456","level":2,"type":"WHOLESALE","address":{"province":"string","city":"string","district":"string","town":"string","road":"string"},"logo":"string","sendAccount":0,"freight":0},{"id":13,"name":"哈哈哈哈哈哈","licenseImg":null,"createTime":null,"auditStatus":"APPROVED","phone":"12312423","level":2,"type":"OTHER","address":{"province":"string","city":"string","district":"string","town":"string","road":"string"},"logo":"string","sendAccount":0,"freight":0},{"id":14,"name":"123","licenseImg":null,"createTime":null,"auditStatus":"APPROVED","phone":"123","level":2,"type":"WHOLESALE","address":{"province":"8905","city":"10644","district":"10674","town":"","road":"123"},"logo":"http://192.168.2.158:9040/20180409\\COMPANY\\15232530033078941.png","sendAccount":null,"freight":null},{"id":15,"name":"string","licenseImg":null,"createTime":null,"auditStatus":null,"phone":null,"level":null,"type":null,"address":{"province":"string","city":"string","district":"string","town":"string","road":"string"},"logo":"www.baidu.com","sendAccount":0,"freight":0},{"id":16,"name":"string","licenseImg":null,"createTime":null,"auditStatus":null,"phone":null,"level":null,"type":null,"address":{"province":"string","city":"string","district":"string","town":"string","road":"string"},"logo":"string","sendAccount":0,"freight":0},{"id":17,"name":"抽象文化有限公司","licenseImg":null,"createTime":null,"auditStatus":"APPROVED","phone":"89716453","level":1,"type":"WHOLESALE","address":{"province":"4729","city":"5162","district":"5242","town":"","road":"xx街道xx楼"},"logo":"http://192.168.2.158:9040/20180415\\GOODS\\15237715122109624.png","sendAccount":0,"freight":100},{"id":19,"name":"山东抽象文化有限公司","licenseImg":null,"createTime":"2018-04-17 16:23:44.0","auditStatus":"APPROVED","phone":"89719464","level":1,"type":"WHOLESALE","address":{"province":"江苏省","city":"无锡市","district":"宜兴市","town":"宜兴经济开发区","road":"详细地址"},"logo":"http://192.168.2.158:9040/20180415\\GOODS\\15237715122109624.png","sendAccount":null,"freight":null},{"id":20,"name":"21632","licenseImg":"http://192.168.2.158:9040/20180419\\COMPANY\\15241306420792994.jpg","createTime":"2018-04-19 17:42:40.0","auditStatus":"APPROVED","phone":"1561213","level":2,"type":"WHOLESALE","address":{"province":"内蒙古自治区","city":"乌兰察布市","district":"丰镇市","town":"南城区街道办事处","road":"a6516a11"},"logo":"http://192.168.2.158:9040/20180419\\COMPANY\\15241306420792994.jpg","sendAccount":null,"freight":null},{"id":21,"name":"123115","licenseImg":"http://192.168.2.158:9040/20180415\\null\\15237619947665209.jpg","createTime":"2018-04-21 10:19:28.0","auditStatus":"APPROVED","phone":"123213","level":2,"type":"WHOLESALE","address":{"province":"广东省","city":"清远市","district":"清新区","town":"禾云镇","road":"45465"},"logo":"http://192.168.2.158:9040/20180415\\null\\15237619947665209.jpg","sendAccount":null,"freight":null}]
     * totalElements : 50
     * last : false
     * totalPages : 5
     * size : 10
     * number : 0
     * first : true
     * sort : null
     * numberOfElements : 10
     */

    private int totalElements;
    private boolean last;
    private int totalPages;
    private int size;
    private int number;
    private boolean first;
    private Object sort;
    private int numberOfElements;
    private List<ContentBean> content;

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<ContentBean> getContent() {
        return content;
    }

    public void setContent(List<ContentBean> content) {
        this.content = content;
    }

    public static class ContentBean {
        /**
         * id : 1
         * name : 大鹏旗舰店
         * licenseImg : string
         * createTime : null
         * auditStatus : APPROVED
         * phone : string
         * level : 2
         * type : FACTORY
         * address : {"province":"山东省","city":"济南是","district":"天桥区","town":"标山社区","road":"彪删削去"}
         * logo : string
         * sendAccount : 50000
         * freight : 1000
         */

        private int id;
        private String name;
        private String licenseImg;
        private Object createTime;
        private String auditStatus;
        private String phone;
        private int level;
        private String type;
        private AddressBean address;
        private String logo;
        private int sendAccount;
        private int freight;

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

        public String getLicenseImg() {
            return licenseImg;
        }

        public void setLicenseImg(String licenseImg) {
            this.licenseImg = licenseImg;
        }

        public Object getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Object createTime) {
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
}
