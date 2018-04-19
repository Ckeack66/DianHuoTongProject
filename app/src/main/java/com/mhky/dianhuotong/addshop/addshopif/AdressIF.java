package com.mhky.dianhuotong.addshop.addshopif;

import java.util.HashMap;

/**
 * Created by Administrator on 2018/4/16.
 */

public interface AdressIF {
    void getCityInfoSuccess(int code, String result);

    void getCityInfoFailed(int code, String result);

    void getAreaInfoSuccess(int code, String result);

    void getAreaInfoFailed(int code, String result);

    void getAdressInfoSuccess(int code, String result);

    void getAdressInfoFailed(int code, String result);

    void getSliderInfo(HashMap hashMap);
}
