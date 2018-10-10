package com.mhky.dianhuotong.addshop.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/16.
 */

public class AdressBaseInfo implements Serializable {
    /**
     * firstName : 热门城市
     * region : [{"id":19244,"name":"济南","firstName":null}]
     */

    private String firstName;
    private String code;
    private String type;
    private String namepath;
    private List<RegionBean> region;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNamepath() {
        return namepath;
    }

    public void setNamepath(String namepath) {
        this.namepath = namepath;
    }

    public List<RegionBean> getRegion() {
        return region;
    }

    public void setRegion(List<RegionBean> region) {
        this.region = region;
    }

    public static class RegionBean {
        /**
         * id : 19244
         * name : 济南
         * firstName : null
         */

        private int id;
        private String name;
        private Object firstName;
        private Object type;
        private Object namepath;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

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

        public Object getFirstName() {
            return firstName;
        }

        public void setFirstName(Object firstName) {
            this.firstName = firstName;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getNamepath() {
            return namepath;
        }

        public void setNamepath(Object namepath) {
            this.namepath = namepath;
        }
    }
}
