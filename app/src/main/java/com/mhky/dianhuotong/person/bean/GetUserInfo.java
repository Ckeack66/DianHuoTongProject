package com.mhky.dianhuotong.person.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class GetUserInfo implements Serializable {
    /**
     * username : 妙药仙8601
     * password : $2a$10$kJHtAPJFPCN79OW2mQ4wiusRutwxJa1hfLAo97wOH5.TtOwlOryVK
     * mobile : 15012341234
     * enabled : true
     * authorities : {"id":2,"authority":"ROLE_BUYER","description":"买家","parent":null,"children":[],"resources":[],"company":null}
     */

    private String username;
    private String password;
    private String mobile;
    private boolean enabled;
    private AuthoritiesBean authorities;

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

    public static class AuthoritiesBean {
        /**
         * id : 2
         * authority : ROLE_BUYER
         * description : 买家
         * parent : null
         * children : []
         * resources : []
         * company : null
         */

        private int id;
        private String authority;
        private String description;
        private Object parent;
        private Object company;
        private List<?> children;
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

        public Object getParent() {
            return parent;
        }

        public void setParent(Object parent) {
            this.parent = parent;
        }

        public Object getCompany() {
            return company;
        }

        public void setCompany(Object company) {
            this.company = company;
        }

        public List<?> getChildren() {
            return children;
        }

        public void setChildren(List<?> children) {
            this.children = children;
        }

        public List<?> getResources() {
            return resources;
        }

        public void setResources(List<?> resources) {
            this.resources = resources;
        }
    }
}
