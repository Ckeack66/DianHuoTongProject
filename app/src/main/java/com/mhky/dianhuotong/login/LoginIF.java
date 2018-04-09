package com.mhky.dianhuotong.login;

/**
 * Created by Administrator on 2018/3/27.
 */

public interface LoginIF {
    void LoginSucess(int code, String result);

    void LoginFailed(int code, String result);
}
