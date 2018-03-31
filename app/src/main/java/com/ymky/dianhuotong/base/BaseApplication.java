package com.ymky.dianhuotong.base;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2018/3/31.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }
}
