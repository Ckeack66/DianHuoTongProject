package com.mhky.dianhuotong.person.personif;

/**
 * Created by Administrator on 2018/4/12.
 */

public interface UpdataPersonIF {
    void updataUserImageSucess(int code, String result);
    void updataUserImageFailed(int code, String result);
    void updataUserInfoSucess(int code, String result,String result1);
    void updataUserinfoFailed(int code, String result);
}
