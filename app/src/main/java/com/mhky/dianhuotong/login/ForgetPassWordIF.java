package com.mhky.dianhuotong.login;

/**
 * Created by Administrator on 2018/4/18.
 */

public interface ForgetPassWordIF {
    void forgetPwdSuccess(int code, String result);

    void forgetPwdFailed(int code, String result);

    void getForgetPwdPhoneSmsSuccess(int code, String result);

    void getForgetPwdPhoneSmsFailed(int code, String result);

    void checkForgetPwdPhoneSmsSuccess(int code, String result);

    void checkForgetPwdPhoneSmsFailed(int code, String result);
}
