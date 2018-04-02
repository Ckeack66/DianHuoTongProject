package com.ymky.dianhuotong.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.lzy.okgo.OkGo;

/**
 * Created by Administrator on 2018/3/31.
 */

public class BaseApplication extends Application {
    private static final String TAG = "BaseApplication";
    private volatile static BaseApplication baseApplication;
    private static final String MY_SHARE_NAME = "mydata";
    private static final String MY_TOAKEN = "mytoaken";
    private static SharedPreferences mSharedPreferences;
    private Context mContext;

    public boolean isAddShop() {
        return isAddShop;
    }

    public void setAddShop(boolean addShop) {
        isAddShop = addShop;
    }

    private boolean isAddShop = true;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().setRetryCount(3).init(this);
        mContext = this;
        mSharedPreferences = this.getSharedPreferences(MY_SHARE_NAME, MODE_PRIVATE);
    }

    public static BaseApplication getInstansApp() {
        if (baseApplication == null) {
            synchronized (BaseApplication.class) {
                if (baseApplication == null) {
                    baseApplication = new BaseApplication();
                }
            }
        }
        return baseApplication;
    }


    public String getToakens() {
        return mSharedPreferences.getString(MY_TOAKEN, null);
    }

    public boolean setToakens(String toaken) {
        return mSharedPreferences.edit().putString(MY_TOAKEN, toaken).commit();
    }

    public boolean clearToaken() {
        return mSharedPreferences.edit().clear().commit();
    }
}
