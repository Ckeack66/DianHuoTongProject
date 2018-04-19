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
    private List<RegionBean> region;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    }
}
