package com.mhky.yaolinwang.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator  on  2018/10/15
 * Describe：C端对象实体类
 */
public class CustomerInfoBean implements Serializable {

    /**
     * id : 87
     * username : string
     * password : $2a$10$Mym8NQVkpDOleEs8AvpB1OfvB1XxqZrNbAtcO9Xwle6NnAPB7k1K2
     * mobile : 13465418656
     * enabled : true
     * authorities : {"id":4,"authority":"ROLE_CUSTOMER","description":"终端用户","resources":[],"company":null}
     * createTime : 2018-10-15 10:37:14
     * image : null
     * new : false
     */

    private int id;
    private String username;
    private String password;
    private String mobile;
    private boolean enabled;
    private AuthoritiesBean authorities;
    private String createTime;
    private String image;
    @SerializedName("new")
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

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AuthoritiesBean getAuthorities() {
        return authorities;
    }

    public void setAuthorities(AuthoritiesBean authorities) {
        this.authorities = authorities;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }

    public static class AuthoritiesBean implements Serializable{
        /**
         * id : 4
         * authority : ROLE_CUSTOMER
         * description : 终端用户
         * resources : []
         * company : null
         */

        private int id;
        private String authority;
        private String description;
        private Object company;
        private List<?> resources;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAuthority() {
            return authority;
        }

        public void setAuthority(String authority) {
            this.authority = authority;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public List<?> getResources() {
            return resources;
        }

        public void setResources(List<?> resources) {
            this.resources = resources;
        }
    }
}
