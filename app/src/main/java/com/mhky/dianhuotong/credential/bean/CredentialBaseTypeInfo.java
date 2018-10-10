package com.mhky.dianhuotong.credential.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/18.
 * 资质名称实体类
 */

public class CredentialBaseTypeInfo implements Serializable {
    /**
     * id : 1
     * name : 药品经营质量管理规范认证证书（GSP）
     * new : false
     */

    private String id;
    private String name;
    private boolean newX;

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

    public boolean isNewX() {
        return newX;
    }

    public void setNewX(boolean newX) {
        this.newX = newX;
    }
}
