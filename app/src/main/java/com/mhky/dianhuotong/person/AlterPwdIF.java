package com.mhky.dianhuotong.person;

/**
 * Created by Administrator on 2018/4/13.
 */

public interface AlterPwdIF {
    void alterPwdSucess(int code, String result);
    void alterPwdFailed(int code, String result);
}
