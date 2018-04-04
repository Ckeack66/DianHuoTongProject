package com.ymky.dianhuotong.main.presenter;

import android.content.Context;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.main.MainIF;

import java.util.ArrayList;

/**
 * Created by Administrator on 2018/3/29.
 */

public class MainActivityPrecenter {
    private MainIF mainIF;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private int[] imageArray = new int[6];
    private ArrayList<String> arrayListGrid = new ArrayList<String>();
    private int[] imageArrayGridView = new int[9];
    private Context context;
    private ArrayList<String> arrayListImageUrl = new ArrayList<String>();

    public MainActivityPrecenter(MainIF mainIF, Context mContext) {
        this.mainIF = mainIF;
        this.context = mContext;
        inItListViewData();
        inItGridViewData();
        initImageList();
    }

    public void getlistData() {
        mainIF.updateListview(imageArray, arrayList);
    }

    public void getGridData() {
        mainIF.updateGridView(imageArrayGridView, arrayListGrid);
    }

    public void getImageUrl() {
        mainIF.getBanerList(arrayListImageUrl);
    }

    private void inItListViewData() {
        arrayList.add(0, context.getString(R.string.drawer_list_caigou));
        arrayList.add(1, context.getString(R.string.drawer_list_info_update));
        arrayList.add(2, context.getString(R.string.drawer_list_kefu));
        arrayList.add(3, context.getString(R.string.drawer_list_mianze));
        arrayList.add(4, context.getString(R.string.drawer_list_about));
        arrayList.add(5, context.getString(R.string.drawer_list_set));
        imageArray[0] = R.drawable.icon_selected;
        imageArray[1] = R.drawable.icon_update_info;
        imageArray[2] = R.drawable.icon_ear;
        imageArray[3] = R.drawable.icon_mianze;
        imageArray[4] = R.drawable.icon_about;
        imageArray[5] = R.drawable.icon_set;
    }

    private void inItGridViewData() {
        arrayListGrid.add(0, context.getString(R.string.main_gridview_1));
        arrayListGrid.add(1, context.getString(R.string.main_gridview_2));
        arrayListGrid.add(2, context.getString(R.string.main_gridview_3));
        arrayListGrid.add(3, context.getString(R.string.main_gridview_4));
        arrayListGrid.add(4, context.getString(R.string.main_gridview_5));
        arrayListGrid.add(5, context.getString(R.string.main_gridview_6));
        arrayListGrid.add(6, context.getString(R.string.main_gridview_7));
        arrayListGrid.add(7, context.getString(R.string.main_gridview_8));
        arrayListGrid.add(8, context.getString(R.string.main_gridview_9));
        imageArrayGridView[0] = R.drawable.icon_grid1;
        imageArrayGridView[1] = R.drawable.icon_grid2;
        imageArrayGridView[2] = R.drawable.icon_grid3;
        imageArrayGridView[3] = R.drawable.icon_grid4;
        imageArrayGridView[4] = R.drawable.icon_grid5;
        imageArrayGridView[5] = R.drawable.icon_grid6;
        imageArrayGridView[6] = R.drawable.icon_grid7;
        imageArrayGridView[7] = R.drawable.icon_grid8;
        imageArrayGridView[8] = R.drawable.icon_grid9;
    }

    private void initImageList() {
        arrayListImageUrl.add("http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        arrayListImageUrl.add("http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        arrayListImageUrl.add("http://cdn3.nflximg.net/images/3093/2043093.jpg");
//        arrayListImageUrl.add("https://api.happy-learn.com/banner/changjilubozi/images/thum.png");
    }

}
