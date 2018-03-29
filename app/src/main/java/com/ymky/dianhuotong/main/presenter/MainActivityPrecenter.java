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
    private Context context;

    public MainActivityPrecenter(MainIF mainIF, Context mContext) {
        this.mainIF = mainIF;
        this.context = mContext;
        initDate();
    }

    public void getlistData() {
        mainIF.updateListview(imageArray, arrayList);
    }

    private void initDate() {
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


}
