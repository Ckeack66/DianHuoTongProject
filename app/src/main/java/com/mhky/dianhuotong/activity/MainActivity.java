package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
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

import com.mhky.dianhuotong.R;
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
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity implements MainIF, DrawerLayout.DrawerListener, AdapterView.OnItemClickListener, DianHuoTongBaseDialog.BaseDialogListener {
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
    Banner banner;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = this;
        BaseApplication.getInstansApp().clearToaken();
        inIt();
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
    protected void onResume() {
        super.onResume();
        if (BaseApplication.getInstansApp().getToakens() != null) {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            if (isOpenDrawer) {
                drawerLayout.closeDrawers();
            }
        }
        if (BaseApplication.getInstansApp().isUpdata()) {
            updateDrawer();
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
        diaHuiTongBaseTitleBar.setRightImage(R.drawable.icon_main_message);
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

        diaHuiTongBaseTitleBar.setRightOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
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

    /**
     * 初始化轮播图
     */

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
        initImageBaner(list);
    }

    /**
     * 抽屉滑动距离
     *
     * @param drawerView
     * @param slideOffset
     */
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        Log.d(TAG, "onDrawerSlide: " + slideOffset);
        getWindowManager().getDefaultDisplay().getMetrics(display);
        relativeLayoutBody.layout(relativeLayoutLeft.getRight(), 0, relativeLayoutLeft.getRight() + display.widthPixels, display.heightPixels);
    }

    /**
     * 抽屉打开监听
     *
     * @param drawerView
     */
    @Override
    public void onDrawerOpened(View drawerView) {
        isOpenDrawer = true;
        banner.stopAutoPlay();
    }

    /**
     * 抽屉关闭监听
     *
     * @param drawerView
     */
    @Override
    public void onDrawerClosed(View drawerView) {
        isOpenDrawer = false;
        banner.startAutoPlay();
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
        if (BaseApplication.getInstansApp().getToakens() == null) {
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
//                        if (BaseApplication.getInstansApp().isAddShop()) {
//                            dianHuoTongBaseDialogAddShop.show();
//                        } else {
                        BaseTool.goActivityNoData(this, DianHuoTongShopActivity.class);
//                        }
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
                //Log.d(TAG, "updateDrawer: --------" + BaseApplication.getInstansApp().getLoginRequestInfo().getImage().toString());
                Picasso.with(this).load(BaseApplication.getInstansApp().getPersonInfo().getImage().toString()).into(imageViewUser);
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
        if (BaseApplication.getInstansApp().getLoginRequestInfo() != null) {
            if (BaseApplication.getInstansApp().getLoginRequestInfo().getImage() != null) {
                Log.d(TAG, "updateDrawer: --------" + BaseApplication.getInstansApp().getLoginRequestInfo().getImage().toString());
                Picasso.with(this).load(BaseApplication.getInstansApp().getLoginRequestInfo().getImage().toString()).into(imageViewUser);
            }
            if (BaseApplication.getInstansApp().getLoginRequestInfo().getUsername() != null) {
                textViewUserName.setText(BaseApplication.getInstansApp().getLoginRequestInfo().getUsername());
            }
            if (BaseApplication.getInstansApp().getLoginRequestInfo().getMobile() != null) {
                textViewPhone.setText(BaseApplication.getInstansApp().getLoginRequestInfo().getMobile());
            }
            BaseApplication.getInstansApp().setUpdata(false);
        }
    }


}
