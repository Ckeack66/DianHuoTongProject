package com.mhky.dianhuotong.person.personif;

/**
 * Created by Administrator on 2018/4/18.
 */

public interface ChangePhoneIF {
    void changePhoneSuccess(int code, String result);

    void changePhoneFailed(int code, String result);

    void getchangePhoneSmsSuccess(int code, String result);

    void getchangePhoneSmsfailed(int code, String result);

    void checkchangePhoneSmsSuccess(int code, String result);

    void checkchangePhoneSmsFailed(int code, String result);
}
