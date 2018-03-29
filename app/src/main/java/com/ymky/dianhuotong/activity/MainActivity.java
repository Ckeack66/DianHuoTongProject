package com.ymky.dianhuotong.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.DiaHuiTongBaseTitleBar;
import com.ymky.dianhuotong.main.MainIF;
import com.ymky.dianhuotong.main.adpter.DrawerLayoutAdapter;
import com.ymky.dianhuotong.main.presenter.MainActivityPrecenter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainIF,DrawerLayout.DrawerListener{
    @BindView(R.id.drawer_listview)
    ListView listView;
    @BindView(R.id.mian_titlebar)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.mian_drawer)
    DrawerLayout drawerLayout;
    @BindView(R.id.main_relativelayout_root)
    RelativeLayout relativeLayout;
    @BindView(R.id.drawer_linearlayout)
    RelativeLayout linearLayout;
    boolean isOpenDrawer = false;
    private MainActivityPrecenter mainActivityPrecenter;
    private DrawerLayoutAdapter drawerLayoutAdapter;
    private WindowManager windowManager;
    private Display display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt(){
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_go_personal);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.app_name));
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isOpenDrawer){
                    drawerLayout.openDrawer(Gravity.START);
                }else {
                    drawerLayout.closeDrawers();
                }

            }
        });
        mainActivityPrecenter=new MainActivityPrecenter(this,this);
        mainActivityPrecenter.getlistData();
        windowManager=(WindowManager) getSystemService(Context.WINDOW_SERVICE);
        display=windowManager.getDefaultDisplay();
        drawerLayout.setScrimColor(Color.TRANSPARENT);
    }

    @Override
    public void updateListview(int[] array, ArrayList<String> list) {
        drawerLayoutAdapter=new DrawerLayoutAdapter(list,array,this);
        listView.setAdapter(drawerLayoutAdapter);
    }

    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
                //relativeLayout.layout();
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
