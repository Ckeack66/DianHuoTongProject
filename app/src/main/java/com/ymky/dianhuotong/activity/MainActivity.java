package com.ymky.dianhuotong.activity;

import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.jake.library.GridViewLine;
import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.BaseApplication;
import com.ymky.dianhuotong.base.BaseTool;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.ymky.dianhuotong.custom.ToastUtil;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.ymky.dianhuotong.main.GlideImageLoader;
import com.ymky.dianhuotong.main.MainIF;
import com.ymky.dianhuotong.main.adpter.DrawerLayoutAdapter;
import com.ymky.dianhuotong.main.adpter.GridViewAdapter;
import com.ymky.dianhuotong.main.presenter.MainActivityPrecenter;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity implements MainIF, DrawerLayout.DrawerListener, AdapterView.OnItemClickListener, DianHuoTongBaseDialog.BaseDialogListener {
    @BindView(R.id.drawer_listview)
    ListView listView;
    @BindView(R.id.mian_titlebar)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.mian_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_gridview)
    GridViewLine gridView;
    @BindView(R.id.drawer_leftlayout)
    RelativeLayout relativeLayoutLeft;
    @BindView(R.id.drawer_bodyayout)
    RelativeLayout relativeLayoutBody;
    @BindView(R.id.main_title_banner)
    Banner banner;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        BaseApplication.getInstansApp().clearToaken();
        inIt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BaseApplication.getInstansApp().getToakens() != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
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
        if (BaseApplication.getInstansApp().getToakens() == null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
        dianHuoTongBaseDialog = new DianHuoTongBaseDialog(this, this, "温馨提示", "登录之后会有更多精彩哦~", "稍后再说", "马上登陆", main1);
        dianHuoTongBaseDialogBack = new DianHuoTongBaseDialog(this, this, "温馨提示", "您确定要退出系统吗", "取消", "退出", main2);
        dianHuoTongBaseDialogAddShop = new DianHuoTongBaseDialog(this, this, "温馨提示", "加入店铺查看更多精彩内容~", "稍后再说", "立刻加入", main3);
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_go_personal);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.main_title));
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseApplication.getInstansApp().getToakens() == null) {
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
        mainActivityPrecenter = new MainActivityPrecenter(this, this);
        mainActivityPrecenter.getlistData();
        mainActivityPrecenter.getGridData();
        mainActivityPrecenter.getImageUrl();
        display = new DisplayMetrics();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(this);
        gridView.setOnItemClickListener(this);
        listView.setOnItemClickListener(this);
    }

    //初始化轮播图
    private void initImageBaner(List<?> list) {
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(list);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //banner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(1500);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    @Override
    public void updateListview(int[] array, ArrayList<String> list) {
        drawerLayoutAdapter = new DrawerLayoutAdapter(list, array, this);
        listView.setAdapter(drawerLayoutAdapter);
    }

    @Override
    public void updateGridView(int[] array, ArrayList<String> list) {
        gridViewAdapter = new GridViewAdapter(this, list, array);
        gridView.setAdapter(gridViewAdapter);
    }

    @Override
    public void getBanerList(List<?> list) {
        initImageBaner(list);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Log.d(TAG, "onDrawerSlide: " + slideOffset);
        getWindowManager().getDefaultDisplay().getMetrics(display);
        relativeLayoutBody.layout(relativeLayoutLeft.getRight(), 0, relativeLayoutLeft.getRight() + display.widthPixels, display.heightPixels);
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        isOpenDrawer = true;
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        isOpenDrawer = false;
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (BaseApplication.getInstansApp().getToakens() == null) {
            dianHuoTongBaseDialog.show();
            return;
        }
        switch (parent.getId()) {
            case R.id.main_gridview:
                switch (position) {
                    case 0:
                        BaseTool.goActivityNoData(this, ShiYaoQianYanActivity.class);
                        break;
                    case 1:
                        if (BaseApplication.getInstansApp().isAddShop()) {
                            dianHuoTongBaseDialogAddShop.show();
                        } else {
                            BaseTool.goActivityNoData(this, DianHuoTongShopActivity.class);
                        }
                        break;
                    case 2:
                        BaseTool.goActivityNoData(this, YaoLinWangActivity.class);
                        break;
                    case 3:
                        BaseTool.goActivityNoData(this, MingHuiFuActivity.class);
                        break;
                    case 4:
                        BaseTool.goActivityNoData(this, JianKang121Activity.class);
                        break;
                    case 5:
                        BaseTool.goActivityNoData(this, GuoYaoDianActivity.class);
                        break;
                    case 6:
                        BaseTool.goActivityNoData(this, LaoPHomeActivity.class);
                        break;
                    case 7:
                        BaseTool.goActivityNoData(this, DaKaOnlineActivity.class);
                        break;
                    case 8:
                        BaseTool.goActivityNoData(this, ConnectOurActivity.class);
                        break;
                }
                break;
            case R.id.drawer_listview:
                ToastUtil.makeText(this, "点击了listview" + position, Toast.LENGTH_SHORT).show();
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
                        BaseTool.goActivityNoData(this, MianZeShowActivity.class);
                        break;
                    case 4:
                        BaseTool.goActivityNoData(this, AboutOurActivity.class);
                        break;
                    case 5:
                        BaseTool.goActivityNoData(this, SystemSetActivity.class);
                        break;
                }
                break;
        }
    }

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

    @Override
    public void onClickBaseDialogRight(String mTag) {

        if (main1.equals(mTag) && dianHuoTongBaseDialog.isShowing()) {
            BaseTool.goActivityNoData(this, LoginActivity.class);
            dianHuoTongBaseDialog.dismiss();
        } else if (main2.equals(mTag) && dianHuoTongBaseDialogBack.isShowing()) {
            dianHuoTongBaseDialogBack.dismiss();
            finish();
        } else if (main3.equals(mTag) && dianHuoTongBaseDialogAddShop.isShowing()) {
            dianHuoTongBaseDialogAddShop.dismiss();
            BaseTool.goActivityNoData(this, AddShopActivity.class);
        }

    }

    @OnClick(R.id.main_user_image)
    void goPersonInfoEditActivity() {
        BaseTool.goActivityNoData(this, PersonInfoActivity.class);
    }
}
