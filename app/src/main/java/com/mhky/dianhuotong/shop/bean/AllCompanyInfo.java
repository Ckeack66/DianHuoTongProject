package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class AllCompanyInfo implements Serializable {
    /**
     * content : [{"id":1,"name":"大鹏旗舰店","licenseImg":"string","createTime":null,"auditStatus":"APPROVED","phone":"string","level":null,"type":"FACTORY","address":{"province":"山东省","city":"济南是","district":"天桥区","town":"标山社区","road":"彪删削去"}},{"id":19,"name":"山东抽象文化有限公司","licenseImg":"http://192.168.2.158:9040/20180415\\null\\15237619947665209.jpg","createTime":"2018-04-17 16:23:44.0","auditStatus":"APPROVED","phone":"89719464","level":1,"type":"WHOLESALE","address":{"province":"江苏省","city":"无锡市","district":"宜兴市","town":"宜兴经济开发区","road":"详细地址"}}]
     * last : true
     * totalElements : 2
     * totalPages : 1
     * size : 10
     * number : 0
     * sort : null
     * first : true
     * numberOfElements : 2
     */

    private boolean last;
    private int totalElements;
    private int totalPages;
    private int size;
    private int number;
    private Object sort;
    private boolean first;
    private int numberOfElements;
    private List<ContentBean> content;

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public int getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(int totalElements) {
        this.totalElements = totalElements;
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

    public Object getSort() {
        return sort;
    }

    public void setSort(Object sort) {
        this.sort = sort;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
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

    public static class ContentBean implements Serializable {
        /**
         * id : 1
         * name : 大鹏旗舰店
         * licenseImg : string
         * createTime : null
         * auditStatus : APPROVED
         * phone : string
         * level : null
         * type : FACTORY
         * address : {"province":"山东省","city":"济南是","district":"天桥区","town":"标山社区","road":"彪删削去"}
         */

        private int id;
        private String name;
        private String licenseImg;
        private Object createTime;
        private String auditStatus;
        private String phone;
        private Object level;
        private String type;
        private AddressBean address;

        public boolean isSelect() {
            return isSelect;
        }

        public void setSelect(boolean select) {
            isSelect = select;
        }

        private boolean isSelect = false;

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

        public Object getLevel() {
            return level;
        }

        public void setLevel(Object level) {
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
