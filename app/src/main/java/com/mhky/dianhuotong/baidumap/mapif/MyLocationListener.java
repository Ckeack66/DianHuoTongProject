package com.mhky.dianhuotong.baidumap.mapif;

import android.util.Log;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

/**
 * Created by Administrator on 2018/4/11.
 */

public class MyLocationListener extends BDAbstractLocationListener {
    private static final String TAG = "MyLocationListener";
    private GetLocattionListener getLocattionListener;

    public MyLocationListener(GetLocattionListener getLocattionListener) {
        this.getLocattionListener = getLocattionListener;
    }


    @Override
    public void onReceiveLocation(BDLocation location) {
        StringBuffer stringBuffer = new StringBuffer();
        String locate = "";
        if (location.getCountry() != null) {
            stringBuffer.append(location.getCountry());
        }
        if (location.getProvince() != null) {
            stringBuffer.append(location.getProvince());
        }
        if (location.getCity() != null) {
            stringBuffer.append(location.getCity());
        }
        if (location.getStreet() != null) {
            stringBuffer.append(location.getStreet());
        }
        if (location.getLocationDescribe() != null) {
            stringBuffer.append("-"+location.getLocationDescribe());
        }
        getLocattionListener.getResultLocation(location, stringBuffer.toString());
        Log.d(TAG, "onReceiveLocation: ----------------" + location.getProvince());
        Log.d(TAG, "onReceiveLocation: ----------------" + location.getCity());
        Log.d(TAG, "onReceiveLocation: ----------------" + location.getCountry());
        Log.d(TAG, "onReceiveLocation: ----------------" + location.getStreet());
        Log.d(TAG, "onReceiveLocation: ----------------" + location.getLocationDescribe());

    }
}
