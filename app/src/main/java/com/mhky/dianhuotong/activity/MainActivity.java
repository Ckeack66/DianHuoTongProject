package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.advert.AdvertInfo;
import com.mhky.dianhuotong.advert.AdvertMainIF;
import com.mhky.dianhuotong.advert.AdvertMainPresenter;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.login.LoginIF;
import com.mhky.dianhuotong.login.LoginPrecenter;
import com.mhky.dianhuotong.main.MainIF;
import com.mhky.dianhuotong.main.adpter.DrawerLayoutAdapter;
import com.mhky.dianhuotong.main.adpter.GridViewAdapter;
import com.mhky.dianhuotong.main.presenter.MainActivityPrecenter;
import com.mhky.dianhuotong.receiver.UpdateMainViewIF;
import com.mhky.dianhuotong.receiver.UpdateMainViewReceiver;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsCategories;
import com.mhky.dianhuotong.shop.precenter.AllGoosPrecenter;
import com.mhky.dianhuotong.shop.precenter.ShopInfoPresenter;
import com.mhky.dianhuotong.shop.shopif.AllGoodsIF;
import com.mhky.dianhuotong.shop.shopif.GoodsCategoriesIF;
import com.mhky.yaolinwang.activity.PersonInfoForCActivity;
import com.mhky.yaolinwang.activity.YaoLinWangActivity;
import com.mhky.yaolinwang.order.activity.CustomerOrdersActivity;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * App主页面
 */

public class MainActivity extends BaseActivity implements MainIF, DrawerLayout.DrawerListener, AdapterView.OnItemClickListener,
        DianHuoTongBaseDialog.BaseDialogListener, AllGoodsIF, AdvertMainIF, UpdateMainViewIF, LoginIF ,GoodsCategoriesIF{
    @BindView(R.id.drawer_listview)
    ListView listView;
    @BindView(R.id.mian_titlebar)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.mian_drawer)
    DrawerLayout drawerLayout;
    //    @BindView(R.id.main_gridview)
//    GridViewLine gridView;
    @BindView(R.id.main_gridview)
    GridView gridView;
    @BindView(R.id.drawer_leftlayout)
    RelativeLayout relativeLayoutLeft;
    @BindView(R.id.drawer_bodyayout)
    RelativeLayout relativeLayoutBody;
    @BindView(R.id.main_title_banner)
    BGABanner banner;
    @BindView(R.id.main_user_image)
    CircleImageView imageViewUser;
    @BindView(R.id.drawer_text_name)
    TextView textViewUserName;
    @BindView(R.id.drawer_text_phone)
    TextView textViewPhone;
    @BindView(R.id.drawer_app_code)
    TextView textViewAppCode;
    boolean isOpenDrawer = false;
    @BindView(R.id.drawer_title)
    RelativeLayout drawerTitle;
    private MainActivityPrecenter mainActivityPrecenter;
    private DrawerLayoutAdapter drawerLayoutAdapter;
    private GridViewAdapter gridViewAdapter;
    private static final String TAG = "MainActivity";
    private DisplayMetrics display;
    private DianHuoTongBaseDialog dianHuoTongBaseDialog;
    private String main1 = "mian1";
    private DianHuoTongBaseDialog dianHuoTongBaseDialogBack;
    private String main2 = "main2";
    private DianHuoTongBaseDialog dianHuoTongBaseDialogAddShop;
    private String main3 = "mian3";
    private Context mContext;
    private AllGoosPrecenter allGoosPrecenter;
    private ShopInfoPresenter shopInfoPresenter;
    private boolean isShowUpdate = false;
    private List<AdvertInfo> advertInfoList;
    private AdvertMainPresenter advertMainPresenter;
    private UpdateMainViewReceiver updateMainViewReceiver;
    private LoginPrecenter loginPrecenter;
    private LoadingDialog loadingDialog;
    public static String action = "com.mhky.dianhuotong.activity.update";
    private int role = -1;

    public static List<GoodsBaseInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        PgyCrashManager.register(this);
        try {
            inIt();
            initData();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onRestart() {
        super.onRestart();


    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PgyCrashManager.unregister();
        unregisterReceiver(updateMainViewReceiver);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDrawer();

        Permissions4M.get(this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                // .requestUnderM(false)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 权限码
                .requestCodes(10010)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionsGranted(10010)
    void granSuccess() {
        /**
         * 蒲公英  三方更新
         */
        if (!isShowUpdate) {
            PgyUpdateManager.setIsForced(false);
            PgyUpdateManager.register(this, new UpdateManagerListener() {
                @Override
                public void onNoUpdateAvailable() {

                }

                @Override
                public void onUpdateAvailable(String s) {
                    ConnectivityManager connectionManager = (ConnectivityManager)
                            getSystemService(CONNECTIVITY_SERVICE);
                    //获取网络的状态信息，有下面三种方式
                    NetworkInfo networkInfo = connectionManager.getActiveNetworkInfo();
                    if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                        ToastUtil.makeText(mContext, "当前是移动网络哦，注意流量消耗~", Toast.LENGTH_SHORT).show();
                    }
                    final AppBean appBean = getAppBeanFromString(s);
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("有新版本更新啦~")
                            .setMessage("修复了一些bug,增加用户体验")
                            .setNegativeButton(
                                    "确定",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            BaseTool.logPrint("pgyer",appBean.getDownloadURL());
                                            startDownloadTask(
                                                    MainActivity.this,
                                                    appBean.getDownloadURL());
                                        }
                                    }).show();
                }
            });
            isShowUpdate = true;
        }

        if (BaseApplication.getInstansApp().getLoginRequestInfo() != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            shopInfoPresenter.getShopInfo();
        } else {
            if (isOpenDrawer) {
                drawerLayout.closeDrawers();
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isOpenDrawer) {
                drawerLayout.closeDrawers();
            } else {
                dianHuoTongBaseDialogBack.show();
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private void inIt() {
        role = BaseApplication.getInstansApp().getUserRole();
        loadingDialog = new LoadingDialog(this);
        loginPrecenter = new LoginPrecenter(this);
        String u = BaseApplication.getInstansApp().getUserPhone();
        String p = BaseApplication.getInstansApp().getUserPwd();
        if (u != null && p != null) {
            loadingDialog.show();
            loginPrecenter.Login(u, p);
        }

        updateMainViewReceiver = new UpdateMainViewReceiver(this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(action);
        registerReceiver(updateMainViewReceiver, intentFilter);

        advertMainPresenter = new AdvertMainPresenter(this);
        advertMainPresenter.getAdvertMain();
        if (BaseApplication.getInstansApp().getGoodsType() == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        dianHuoTongBaseDialog = new DianHuoTongBaseDialog(this, this, "温馨提示", "登录之后会有更多精彩哦~", "稍后再说", "马上登陆", main1);
        dianHuoTongBaseDialogBack = new DianHuoTongBaseDialog(this, this, "温馨提示", "您确定要退出系统吗", "取消", "退出", main2);
        dianHuoTongBaseDialogAddShop = new DianHuoTongBaseDialog(this, this, "温馨提示", "加入店铺查看更多精彩内容~", "稍后再说", "立刻加入", main3);

        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_go_personal);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.main_title));
        //diaHuiTongBaseTitleBar.setRightImage(R.drawable.icon_main_message);
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.getInstansApp().getLoginRequestInfo() == null) {
                    dianHuoTongBaseDialog.show();
                } else {
                    if (!isOpenDrawer) {
                        drawerLayout.openDrawer(Gravity.START);
                    } else {
                        drawerLayout.closeDrawers();
                    }
                }
            }
        });
        diaHuiTongBaseTitleBar.setRightOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });

        mainActivityPrecenter = new MainActivityPrecenter(this, this);
        mainActivityPrecenter.getlistData();
        mainActivityPrecenter.getGridData();
        mainActivityPrecenter.getImageUrl();
        display = new DisplayMetrics();

        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(this);
        gridView.setOnItemClickListener(this);
        listView.setOnItemClickListener(this);
        //Permissions4M.get(this).requestSync();
//        PermissionChecker.checkSelfPermission(this,"");//检查权限
    }

    /**
     * 初始化轮播图
     */

    private void initImageBaner(List<?> list) {
        try {
            if (list != null && list.size() > 0) {
                banner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        AdvertInfo advertInfo = (AdvertInfo) model;
                        Uri uri = Uri.parse(advertInfo.getImage());
                        Picasso.get().load(uri).into((ImageView) itemView);
                    }
                });

                banner.setAutoPlayAble(true);
                banner.setData(list, new ArrayList<String>());
            }

        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    /**
     * 获取抽屉列表数据回调
     *
     * @param array
     * @param list
     */
    @Override
    public void updateListview(int[] array, ArrayList<String> list) {
        drawerLayoutAdapter = new DrawerLayoutAdapter(list, array, this);
        listView.setAdapter(drawerLayoutAdapter);
    }

    /**
     * 获取首页九宫格数据回调
     *
     * @param array
     * @param list
     */
    @Override
    public void updateGridView(int[] array, ArrayList<String> list) {
        gridViewAdapter = new GridViewAdapter(this, list, array);
        gridView.setAdapter(gridViewAdapter);
    }

    /**
     * 获取banner数据
     *
     * @param list
     */
    @Override
    public void getBanerList(List<?> list) {
        //initImageBaner(list);
    }

    /**
     * 抽屉滑动距离
     *
     * @param drawerView
     * @param slideOffset
     */
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
//        BaseTool.logPrint(TAG, "onDrawerSlide: " + slideOffset);
//        getWindowManager().getDefaultDisplay().getMetrics(display);
//        relativeLayoutBody.layout(relativeLayoutLeft.getRight(), 0, relativeLayoutLeft.getRight() + display.widthPixels, display.heightPixels);
    }

    /**
     * 抽屉打开监听
     *
     * @param drawerView
     */
    @Override
    public void onDrawerOpened(View drawerView) {
        isOpenDrawer = true;
        updateDrawer();
    }

    /**
     * 抽屉关闭监听
     *
     * @param drawerView
     */
    @Override
    public void onDrawerClosed(View drawerView) {
        isOpenDrawer = false;
        // banner.startAutoPlay();
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    /**
     * 首页和抽屉listview和gridview点击监听
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (BaseApplication.getInstansApp().getLoginRequestInfo() == null) {
//            dianHuoTongBaseDialog.show();
//            return;
//        }
        switch (parent.getId()) {
            case R.id.main_gridview:
                switch (position) {
//                    case 0:
//                        BaseTool.goActivityNoData(this, ShiYaoQianYanActivity.class);
//                        break;
                    case 1:
                        if (BaseApplication.getInstansApp().getUserRole() != 2){
                            ToastUtil.makeText(this, "终端消费者无法进入~", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (BaseApplication.getInstansApp().getPersonInfo() != null &&
                                BaseApplication.getInstansApp().getPersonInfo().getAuditStatus() == null) {  //账号已登录，但是店铺未绑定店铺
                            dianHuoTongBaseDialogAddShop.show();
                        } else if ("UNAUDITED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
                            ToastUtil.makeText(this, "正在审核中~", Toast.LENGTH_SHORT).show();
                        } else if ("APPROVED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
                            BaseTool.goActivityNoData(this, DianHuoTongShopActivity.class);
                        } else if ("UNSANCTIONED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
                            ToastUtil.makeText(this, "店铺审核失败，请重新加入~", Toast.LENGTH_SHORT).show();
                            dianHuoTongBaseDialogAddShop.show();
                        }
                        break;
                    case 2:
                        BaseTool.goActivityNoData(this, YaoLinWangActivity.class);
                        break;
                    case 3:
//                        BaseTool.goActivityNoData(this, MingHuiFuActivity.class);
                        BaseTool.goActivityNoData(this, CustomerOrdersActivity.class);
                        break;
//                    case 4:
//                        BaseTool.goActivityNoData(this, JianKang121Activity.class);
//                        break;
//                    case 5:
//                        BaseTool.goActivityNoData(this, GuoYiGuanActivity.class);
//                        break;
//                    case 6:
//                        BaseTool.goActivityNoData(this, LaoPHomeActivity.class);
//                        break;
//                    case 7:
//                        BaseTool.goActivityNoData(this, DaKaOnlineActivity.class);
//                        break;
//                    case 8:
//                        BaseTool.goActivityNoData(this, ConnectOurActivity.class);
//                        break;
                    default:
                        BaseTool.goActivityNoData(this, BanKuaiCreatingActivity.class);
                        break;
                }
                break;
            case R.id.drawer_listview:
                switch (position) {
                    case 0:
                        if(BaseApplication.getInstansApp().getUserRole() == 2){
                            BaseTool.goActivityNoData(this, MyselectedActivity.class);
                        }else if(BaseApplication.getInstansApp().getUserRole() == 4){
                            BaseTool.goActivityNoData(this, CustomerOrdersActivity.class);
                        }
                        break;
                    case 1:
                        BaseTool.goActivityNoData(this, PersonInfoUpdateActivity.class);
                        break;
                    case 2:
                        BaseTool.goActivityNoData(this, MyWaiterActivity.class);
                        break;
                    case 3:
                        BaseTool.goActivityNoData(this, MyWalletActivity.class);
                        break;
                    case 4:
                        BaseTool.goActivityNoData(this, MianZeShowActivity.class);
                        break;
                    case 5:
                        BaseTool.goActivityNoData(this, AboutOurActivity.class);
                        break;
                    case 6:
                        BaseTool.goActivityNoData(this, SystemSetActivity.class);
                        break;
                }
                break;
        }
    }

    /**
     * 弹窗点击左键监听
     *
     * @param mTag 同一页面多个弹窗区分
     */
    @Override
    public void onClickBaseDialogLeft(String mTag) {
        try {
            if (main1.equals(mTag) && dianHuoTongBaseDialog.isShowing()) {
                dianHuoTongBaseDialog.dismiss();
            } else if (main2.equals(mTag) && dianHuoTongBaseDialogBack.isShowing()) {
                dianHuoTongBaseDialogBack.dismiss();
            } else if (main3.equals(mTag) && dianHuoTongBaseDialogAddShop.isShowing()) {
                dianHuoTongBaseDialogAddShop.dismiss();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    /**
     * 弹窗点击右键监听
     *
     * @param mTag 同一页面多个弹窗区分
     */
    @Override
    public void onClickBaseDialogRight(String mTag) {
        try {
            if (main1.equals(mTag) && dianHuoTongBaseDialog.isShowing()) {
                BaseTool.goActivityNoData(this, LoginActivity.class);
                dianHuoTongBaseDialog.dismiss();
            } else if (main2.equals(mTag) && dianHuoTongBaseDialogBack.isShowing()) {
                dianHuoTongBaseDialogBack.dismiss();
                System.exit(0);
            } else if (main3.equals(mTag) && dianHuoTongBaseDialogAddShop.isShowing()) {
                dianHuoTongBaseDialogAddShop.dismiss();
                BaseTool.goActivityNoData(this, AddShopActivity.class);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    /**
     * 点击头像进入个人中心界面
     */
    @OnClick(R.id.main_user_image)
    void goPersonInfoEditActivity() {
        switch (role){
            case 2:
                BaseTool.goActivityNoData(this, PersonInfoActivity.class);
                break;
            case 4:
                BaseTool.goActivityNoData(this, PersonInfoForCActivity.class);
                break;
            default:
                ToastUtil.makeText(this,"账号有误，请重新登陆~",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 更新抽屉界面信息
     */
    private void updateDrawer() {
        try {
            if (BaseApplication.getInstansApp().getPersonInfo() != null) {
                if (BaseApplication.getInstansApp().getPersonInfo().getImage() != null) {
                    //BaseTool.logPrint(TAG, "updateDrawer: --------" + BaseApplication.getInstansApp().getLoginRequestInfo().getImage().toString());
                    Picasso.get().load(BaseApplication.getInstansApp().getPersonInfo().getImage().toString()).into(imageViewUser);
                    BaseTool.logPrint(TAG, "更新图片");
                } else {
                    imageViewUser.setImageDrawable(getResources().getDrawable(R.drawable.icon_header_big));
                    BaseTool.logPrint(TAG, "设置成默认图片");
                }
                if (BaseApplication.getInstansApp().getPersonInfo().getUsername() != null) {
                    textViewUserName.setText(BaseApplication.getInstansApp().getPersonInfo().getUsername());
                }
                if (BaseApplication.getInstansApp().getPersonInfo().getMobile() != null) {
                    textViewPhone.setText(BaseApplication.getInstansApp().getPersonInfo().getMobile());
                }
                textViewAppCode.setText(BaseTool.getLocalVersionName(this));
                BaseApplication.getInstansApp().setUpdata(false);
                return;
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    /**
     * 获取药品类目树  +  获取本账号绑定的药店信息
     */
    private void initData() {
        allGoosPrecenter = new AllGoosPrecenter(this,this);
        allGoosPrecenter.getAllGoodsType();
        HttpParams httpParams = new HttpParams();
        httpParams.put("type","MOBILE");
        allGoosPrecenter.getGoodsCategeries(httpParams);
        shopInfoPresenter = new ShopInfoPresenter();
        shopInfoPresenter.getShopInfo();
    }

    @Override
    public void getAllGoodsInfoSuccess(int code, String result) {
        if (code == 200) {
            BaseApplication.getInstansApp().setAllGoodsBaseInfos(JSON.parseArray(result, GoodsBaseInfo.class));
            BaseApplication.getInstansApp().setGoodsType(JSON.parseArray(result, GoodsBaseInfo.class));
        }
    }

    @Override
    public void getAllGoodsInfoFailed(int code, String result) {

    }

    /**
     * 获取商品类目成功
     * @param code
     * @param result
     */
    @Override
    public void getGoodsCategoriesSuccess(int code, String result) {
        if (code == 200){
            BaseApplication.getInstansApp().setGoodsCategories(JSON.parseArray(result, GoodsCategories.class));
        }
    }
    @Override
    public void getGoodsCategoriesFailed(int code, String result) {

    }

    @Override
    public void getAdvertMainSuccess(int code, String result) {
        try {
            if (code == 200) {
                advertInfoList = JSON.parseArray(result, AdvertInfo.class);
                if (advertInfoList != null && advertInfoList.size() > 0) {
                    initImageBaner(advertInfoList);
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getAdvertMainFailed(int code, String result) {

    }

    @Override
    public void updateview() {
        drawerLayout.closeDrawers();
    }

    @Override
    public void LoginSucess(int code, String result) {
        try {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
            if (code == 200) {
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                shopInfoPresenter.getShopInfo();
                BaseApplication.getInstansApp().setMypswsds(BaseApplication.getInstansApp().getUserPwd());
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void LoginFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

}
