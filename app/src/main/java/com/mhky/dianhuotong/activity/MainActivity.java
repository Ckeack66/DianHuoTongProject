package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.PermissionChecker;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
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
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.advert.AdvertInfo;
import com.mhky.dianhuotong.advert.AdvertMainIF;
import com.mhky.dianhuotong.advert.AdvertMainPresenter;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.main.GlideImageLoader;
import com.mhky.dianhuotong.main.MainIF;
import com.mhky.dianhuotong.main.adpter.DrawerLayoutAdapter;
import com.mhky.dianhuotong.main.adpter.GridViewAdapter;
import com.mhky.dianhuotong.main.presenter.MainActivityPrecenter;
import com.mhky.dianhuotong.receiver.UpdateMainViewIF;
import com.mhky.dianhuotong.receiver.UpdateMainViewReceiver;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview1Adapter;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview2Adapter;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.precenter.AllGoosPrecenter;
import com.mhky.dianhuotong.shop.precenter.ShopInfoPresenter;
import com.mhky.dianhuotong.shop.shopif.AllGoodsIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.pgyersdk.javabean.AppBean;
import com.pgyersdk.update.PgyUpdateManager;
import com.pgyersdk.update.UpdateManagerListener;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;
import de.hdodenhof.circleimageview.CircleImageView;

@PermissionsRequestSync(permission = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, value = {101, 102, 103, 104})
public class MainActivity extends BaseActivity implements MainIF, DrawerLayout.DrawerListener, AdapterView.OnItemClickListener, DianHuoTongBaseDialog.BaseDialogListener, AllGoodsIF ,AdvertMainIF,UpdateMainViewIF{
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
    boolean isOpenDrawer = false;
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
    public static String action="com.mhky.dianhuotong.activity.update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        inIt();
        initData();
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
        PgyUpdateManager.unregister();
        unregisterReceiver(updateMainViewReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDrawer();
        if (!isShowUpdate) {
            PgyUpdateManager.setIsForced(false);
            PgyUpdateManager.register(this, new UpdateManagerListener() {
                @Override
                public void onNoUpdateAvailable() {

                }

                @Override
                public void onUpdateAvailable(String s) {
                    final AppBean appBean = getAppBeanFromString(s);
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("有新版本更新啦~")
                            .setMessage("修复了一些bug,增加用户体验")
                            .setNegativeButton(
                                    "确定",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @PermissionsGranted({101, 102, 103, 104})
    void getLocationGrantsucess(int code) {
        switch (code) {
            case 101:
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
        }
    }

    @PermissionsDenied({101, 102, 103, 104})
    void getLocationGrantFaile(int code) {
        switch (code) {
            case 101:
                ToastUtil.makeText(this, "请打开定位权限", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
        }
    }

    private void inIt() {
        updateMainViewReceiver=new UpdateMainViewReceiver(this);
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction(action);
        registerReceiver(updateMainViewReceiver,intentFilter);
        advertMainPresenter=new AdvertMainPresenter(this);
        advertMainPresenter.getAdvertMain();
        if (BaseApplication.getInstansApp().getAllGoodsBaseInfos() == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        dianHuoTongBaseDialog = new DianHuoTongBaseDialog(this, this, "温馨提示", "登录之后会有更多精彩哦~", "稍后再说", "马上登陆", main1);
        dianHuoTongBaseDialogBack = new DianHuoTongBaseDialog(this, this, "温馨提示", "您确定要退出系统吗", "取消", "退出", main2);
        dianHuoTongBaseDialogAddShop = new DianHuoTongBaseDialog(this, this, "温馨提示", "加入店铺查看更多精彩内容~", "稍后再说", "立刻加入", main3);
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_go_personal);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.main_title));
        diaHuiTongBaseTitleBar.setRightImage(R.drawable.icon_main_message);
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
            banner.setAdapter(new BGABanner.Adapter() {
                @Override
                public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                    AdvertInfo advertInfo=(AdvertInfo) model;
                    Uri uri = Uri.parse(advertInfo.getImage());
                    Picasso.get().load(uri).into((ImageView) itemView);
                }
            });

            banner.setAutoPlayAble(true);
            banner.setData(list, new ArrayList<String>());
        }catch (Exception e){
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
        if (BaseApplication.getInstansApp().getLoginRequestInfo() == null) {
            dianHuoTongBaseDialog.show();
            return;
        }
        switch (parent.getId()) {
            case R.id.main_gridview:
                switch (position) {
//                    case 0:
//                        BaseTool.goActivityNoData(this, ShiYaoQianYanActivity.class);
//                        break;
                    case 1:
                        if (BaseApplication.getInstansApp().getPersonInfo().getAuditStatus() == null) {
                            dianHuoTongBaseDialogAddShop.show();
                        } else if ("UNAUDITED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
                            ToastUtil.makeText(this, "正在审核中~", Toast.LENGTH_SHORT).show();
                        } else if ("APPROVED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
                            BaseTool.goActivityNoData(this, DianHuoTongShopActivity.class);
                        }
                        break;
//                    case 2:
//                        BaseTool.goActivityNoData(this, YaoLinWangActivity.class);
//                        break;
//                    case 3:
//                        BaseTool.goActivityNoData(this, MingHuiFuActivity.class);
//                        break;
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
                        BaseTool.goActivityNoData(this, MyselectedActivity.class);
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
        if (main1.equals(mTag) && dianHuoTongBaseDialog.isShowing()) {
            dianHuoTongBaseDialog.dismiss();
        } else if (main2.equals(mTag) && dianHuoTongBaseDialogBack.isShowing()) {
            dianHuoTongBaseDialogBack.dismiss();
        } else if (main3.equals(mTag) && dianHuoTongBaseDialogAddShop.isShowing()) {
            dianHuoTongBaseDialogAddShop.dismiss();
        }
    }

    /**
     * 弹窗点击右键监听
     *
     * @param mTag 同一页面多个弹窗区分
     */
    @Override
    public void onClickBaseDialogRight(String mTag) {

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

    }

    /**
     * 点击头像进入个人中心界面
     */
    @OnClick(R.id.main_user_image)
    void goPersonInfoEditActivity() {
        BaseTool.goActivityNoData(this, PersonInfoActivity.class);
    }

    /**
     * 更新抽屉界面信息
     */
    private void updateDrawer() {
        if (BaseApplication.getInstansApp().getPersonInfo() != null) {
            if (BaseApplication.getInstansApp().getPersonInfo().getImage() != null) {
                //BaseTool.logPrint(TAG, "updateDrawer: --------" + BaseApplication.getInstansApp().getLoginRequestInfo().getImage().toString());
                Picasso.get().load(BaseApplication.getInstansApp().getPersonInfo().getImage().toString()).into(imageViewUser);
                BaseTool.logPrint(TAG,"更新图片");
            }else {
                imageViewUser.setImageDrawable(getResources().getDrawable(R.drawable.icon_header_big));
                BaseTool.logPrint(TAG,"设置成默认图片");
            }
            if (BaseApplication.getInstansApp().getPersonInfo().getUsername() != null) {
                textViewUserName.setText(BaseApplication.getInstansApp().getPersonInfo().getUsername());
            }
            if (BaseApplication.getInstansApp().getPersonInfo().getMobile() != null) {
                textViewPhone.setText(BaseApplication.getInstansApp().getPersonInfo().getMobile());
            }
            BaseApplication.getInstansApp().setUpdata(false);
            return;
        }
    }


    private void initData() {
        allGoosPrecenter = new AllGoosPrecenter(this);
        allGoosPrecenter.getAllGoodsType();
        shopInfoPresenter = new ShopInfoPresenter();
    }

    @Override
    public void getAllGoodsInfoSuccess(int code, String result) {
        if (code == 200) {
            BaseApplication.getInstansApp().setAllGoodsBaseInfos(JSON.parseArray(result, GoodsBaseInfo.class));
        }
    }

    @Override
    public void getAllGoodsInfoFailed(int code, String result) {

    }

    @Override
    public void getAdvertMainSuccess(int code, String result) {
        if (code==200){
            advertInfoList=JSON.parseArray(result,AdvertInfo.class);
            initImageBaner(advertInfoList);
        }
    }

    @Override
    public void getAdvertMainFailed(int code, String result) {

    }

    @Override
    public void updateview() {
        drawerLayout.closeDrawers();
    }
}
