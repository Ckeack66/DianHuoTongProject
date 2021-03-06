package com.mhky.dianhuotong.login;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2018/4/11.
 */

public class LoginRequestInfo implements Serializable {

    /**
     * username : 妙药仙8601
     * password : $2a$10$kJHtAPJFPCN79OW2mQ4wiusRutwxJa1hfLAo97wOH5.TtOwlOryVK
     * mobile : 15012341234
     * enabled : true
     * image : null
     * shopId : null
     * id : 11
     * truename : null
     * type : null
     */

    private String username;                                    //用户名称
    private String password;                                    //用户密码（暂没有）
    private String mobile;                                      //用户手机号（账号）
    private boolean enabled;                                    //是否可以登录
    private Object image;                                       //头像URL
    private Object shopId;                                      //下游药店ID
    private String shopName;                                    //店铺名称
    private String id;                                          //账号ID
    private Object truename;                                    //真实姓名
    private Object type;                                        //0：店员    1.店长
    private String auditStatus;                                 //账号审核状态：APPROVED：审核已过           待审核         审核未通过
    private String salesmanCode;                                //业务员推荐码
    private AuthoritiesBean authorities;                        //权限类

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

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Object getShopId() {
        return shopId;
    }

    public void setShopId(Object shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public AuthoritiesBean getAuthorities() {
        return authorities;
    }

    public void setAuthorities(AuthoritiesBean authorities) {
        this.authorities = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getTruename() {
        return truename;
    }

    public void setTruename(Object truename) {
        this.truename = truename;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getSalesmanCode() {
        return salesmanCode;
    }

    public void setSalesmanCode(String salesmanCode) {
        this.salesmanCode = salesmanCode;
    }

    public static class AuthoritiesBean implements Serializable {

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
