package com.mhky.dianhuotong.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.mhky.dianhuotong.login.LoginRequestInfo;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.ShopInfoByUserID;
import com.tencent.smtt.sdk.QbSdk;

import java.security.NoSuchAlgorithmException;
import java.util.List;

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

    public String getMypswsds() {
        return mypswsds;
    }

    public void setMypswsds(String mypswsds) {
        this.mypswsds = mypswsds;
    }

    private String mypswsds;

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    private PersonInfo personInfo;

    public boolean isUpdata() {
        return isUpdata;
    }

    public void setUpdata(boolean updata) {
        isUpdata = updata;
    }

    private boolean isUpdata = true;

    public boolean isAddShop() {
        return isAddShop;
    }

    public void setAddShop(boolean addShop) {
        isAddShop = addShop;
    }

    private boolean isAddShop = true;

    public LoginRequestInfo getLoginRequestInfo() {
        return loginRequestInfo;
    }

    public void setLoginRequestInfo(LoginRequestInfo loginRequestInfo) {
        this.loginRequestInfo = loginRequestInfo;
    }

    private LoginRequestInfo loginRequestInfo;

    public List<GoodsBaseInfo> getAllGoodsBaseInfos() {
        return allGoodsBaseInfos;
    }

    public void setAllGoodsBaseInfos(List<GoodsBaseInfo> allGoodsBaseInfos) {
        this.allGoodsBaseInfos = allGoodsBaseInfos;
    }

    private List<GoodsBaseInfo> allGoodsBaseInfos;

    public boolean isUpdateCart() {
        return isUpdateCart;
    }

    public void setUpdateCart(boolean updateCart) {
        isUpdateCart = updateCart;
    }

    private boolean isUpdateCart = false;

    public ShopInfoByUserID getShopInfoByUserID() {
        return shopInfoByUserID;
    }

    public void setShopInfoByUserID(ShopInfoByUserID shopInfoByUserID) {
        this.shopInfoByUserID = shopInfoByUserID;
    }

    private ShopInfoByUserID shopInfoByUserID;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().setRetryCount(3).init(this);
        mContext = this;
        mSharedPreferences = this.getSharedPreferences(MY_SHARE_NAME, MODE_PRIVATE);
        Fresco.initialize(this);
        SDKInitializer.initialize(getApplicationContext());
        BaseActivityManager.getInstance();
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d(TAG, "onCoreInitFinished: -----腾讯x5内核初始化完成");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.d(TAG, "onCoreInitFinished: -----腾讯x5内核初始化" + b);
            }
        });
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
        BaseApplication.getInstansApp().setLoginRequestInfo(null);
        BaseApplication.getInstansApp().setPersonInfo(null);
        BaseApplication.getInstansApp().setShopInfoByUserID(null);
        BaseApplication.getInstansApp().setUpdateCart(false);
        BaseApplication.getInstansApp().setUpdata(false);
        return mSharedPreferences.edit().clear().commit();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
