package com.mhky.dianhuotong.person;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/13.
 */

public class AlterPwdInfo implements Serializable{
    /**
     * password :
     */

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
