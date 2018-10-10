package com.mhky.dianhuotong.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.alibaba.fastjson.JSON;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.lzy.okgo.OkGo;
import com.mhky.dianhuotong.login.LoginRequestInfo;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsCategories;
import com.mhky.dianhuotong.shop.bean.ShopInfoByUserID;
import com.pgyersdk.crash.PgyCrashManager;
import com.tencent.smtt.sdk.QbSdk;

import org.greenrobot.greendao.query.QueryBuilder;
import org.litepal.LitePalApplication;

import java.util.List;

/**
 * Created by Administrator on 2018/3/31.
 */

public class BaseApplication extends LitePalApplication {
    private static final String TAG = "BaseApplication";
    private volatile static BaseApplication baseApplication;
    private static final String MY_SHARE_NAME = "mydata";
    private static final String MY_TOAKEN = "mytoaken";
    private static final String MY_USER_NAME="myusername";
    private static final String MY_USER_PWD="mypwd";
    private static final String DAO_SESSION="search-data";
    private static final String ALL_GOODS_TYPE_NEW = "ALL_GOODS_TYPE_NEW";                          //商品类目(全的)
    private static final String ALL_GOODS_TYPE_OLD = "ALL_GOODS_TYPE_OLD";                          //商品类目(仅仅是商品的)
    public static final String wxAction="com.mhky.dianhuotong.balance";
    private static SharedPreferences mSharedPreferences;
    private Context mContext;
    private String userPhone;
    private String userPwd;

    private String mypswsds;

    public String getMypswsds() {
        return mypswsds;
    }

    public void setMypswsds(String mypswsds) {
        this.mypswsds = mypswsds;
    }

    private PersonInfo personInfo;

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    private boolean isUpdata = true;

    public boolean isUpdata() {
        return isUpdata;
    }

    public void setUpdata(boolean updata) {
        isUpdata = updata;
    }

    private LoginRequestInfo loginRequestInfo;

    public LoginRequestInfo getLoginRequestInfo() {
        return loginRequestInfo;
    }

    public void setLoginRequestInfo(LoginRequestInfo loginRequestInfo) {
        this.loginRequestInfo = loginRequestInfo;
    }

    private List<GoodsBaseInfo> allGoodsBaseInfos;

    public List<GoodsBaseInfo> getAllGoodsBaseInfos() {
        return allGoodsBaseInfos;
    }

    public void setAllGoodsBaseInfos(List<GoodsBaseInfo> allGoodsBaseInfos) {
        this.allGoodsBaseInfos = allGoodsBaseInfos;
    }

    /**
     * 更新购物车
     */
    private boolean isUpdateCart = false;

    public boolean isUpdateCart() {
        return isUpdateCart;
    }

    public void setUpdateCart(boolean updateCart) {
        isUpdateCart = updateCart;
    }

    private ShopInfoByUserID shopInfoByUserID;

    public ShopInfoByUserID getShopInfoByUserID() {
        return shopInfoByUserID;
    }

    public void setShopInfoByUserID(ShopInfoByUserID shopInfoByUserID) {
        this.shopInfoByUserID = shopInfoByUserID;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化OkGo     setRetryCount()  设置请求失败之后的重新请求次数
        OkGo.getInstance().setRetryCount(1).init(this);
        mContext = this;
        mSharedPreferences = this.getSharedPreferences(MY_SHARE_NAME, MODE_PRIVATE);
        //Fresco图片加载框架初始化
        Fresco.initialize(this);
        //百度地图初始化
        SDKInitializer.initialize(getApplicationContext());
        //activity管理器初始化
        BaseActivityManager.getInstance();
        //腾讯TBS5核浏览器初始化
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                BaseTool.logPrint(TAG, "onCoreInitFinished: -----腾讯x5内核初始化完成");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                BaseTool.logPrint(TAG, "onCoreInitFinished: -----腾讯x5内核初始化" + b);
            }
        });
        //蒲公英框架的注册初始化
        PgyCrashManager.register(this);
//        PgyCrashManager.register();
        //GreenDao数据库操作框架
        QueryBuilder.LOG_SQL=true;
        QueryBuilder.LOG_SQL=true;
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

    public void setUserLoginInfo(String phone,String pwd){
        mSharedPreferences.edit().putString(MY_USER_NAME, phone).apply();
        mSharedPreferences.edit().putString(MY_USER_PWD, pwd).apply();
    }

    public String getUserPhone(){
        return mSharedPreferences.getString(MY_USER_NAME, null);
    }
    public String getUserPwd(){
        return mSharedPreferences.getString(MY_USER_PWD, null);
    }

    /*   设置全部的allGoodsType   */
    public void setGoodsCategories(List<GoodsCategories> goodsCategories){
        String s = JSON.toJSONString(goodsCategories);
        mSharedPreferences.edit().putString(ALL_GOODS_TYPE_NEW,s).apply();
    }

    public List<GoodsCategories> getGoodsCategories(){
        if (mSharedPreferences.getString(ALL_GOODS_TYPE_NEW,null) != null){
            List<GoodsCategories> list = JSON.parseArray(mSharedPreferences.getString(ALL_GOODS_TYPE_NEW,null),GoodsCategories.class);
            return list;
        }else {
            return null;
        }
    }

    /*   设置单单商品的allGoodsType   */
    public void setGoodsType(List<GoodsBaseInfo> goodsBaseInfoList){
        String s = JSON.toJSONString(goodsBaseInfoList);
        mSharedPreferences.edit().putString(ALL_GOODS_TYPE_OLD,s).apply();
    }

    public List<GoodsBaseInfo> getGoodsType(){
        if (mSharedPreferences.getString(ALL_GOODS_TYPE_NEW,null) != null){
            List<GoodsBaseInfo> list = JSON.parseArray(mSharedPreferences.getString(ALL_GOODS_TYPE_OLD,null),GoodsBaseInfo.class);
            return list;
        }else {
            return null;
        }
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
