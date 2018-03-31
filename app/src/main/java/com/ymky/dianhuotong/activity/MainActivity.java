package com.ymky.dianhuotong.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.jake.library.GridViewLine;
import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DiaHuiTongBaseTitleBar;
import com.ymky.dianhuotong.main.MainIF;
import com.ymky.dianhuotong.main.adpter.DrawerLayoutAdapter;
import com.ymky.dianhuotong.main.adpter.GridViewAdapter;
import com.ymky.dianhuotong.main.presenter.MainActivityPrecenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainIF, DrawerLayout.DrawerListener {
    @BindView(R.id.drawer_listview)
    ListView listView;
    @BindView(R.id.mian_titlebar)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.mian_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_gridview)
    GridViewLine gridView;
    @BindView(R.id.drawer_leftlayout)
    RelativeLayout relativeLayoutLeft;
    @BindView(R.id.drawer_bodyayout)
    RelativeLayout relativeLayoutBody;
    boolean isOpenDrawer = false;
    private MainActivityPrecenter mainActivityPrecenter;
    private DrawerLayoutAdapter drawerLayoutAdapter;
    private GridViewAdapter gridViewAdapter;
    private WindowManager windowManager;
    private Display display;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_go_personal);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.main_title));
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpenDrawer) {
                    drawerLayout.openDrawer(Gravity.START);
                } else {
                    drawerLayout.closeDrawers();
                }

            }
        });
        mainActivityPrecenter = new MainActivityPrecenter(this, this);
        mainActivityPrecenter.getlistData();
        mainActivityPrecenter.getGridData();
        windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.addDrawerListener(this);
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
    public void onDrawerSlide(View drawerView, float slideOffset) {
        //relativeLayout.layout();
        Log.d(TAG, "onDrawerSlide: "+slideOffset);
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
        relativeLayoutBody.layout(relativeLayoutLeft.getRight(), 0, relativeLayoutLeft.getRight() + display.getWidth(), display.getHeight());
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
}
