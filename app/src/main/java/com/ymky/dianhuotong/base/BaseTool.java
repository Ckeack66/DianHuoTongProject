package com.ymky.dianhuotong.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lzy.okgo.model.Response;
import com.ymky.dianhuotong.invoice.VoiceGridviewAdapter;

/**
 * Created by Administrator on 2018/3/31.
 */

public class BaseTool {

    public static void goActivityNoData(Context context, Class cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    public static void goActivityWithData(Context context, Class cls, Bundle data) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        intent.putExtras(data);
        context.startActivity(intent);
    }

    public static String changeUrlParameter(String parameter) {
        return parameter + "/";
    }

    public static String getResponsBody(Response<String> response) {
        String result = "@null";
        try {
            result = response.getRawResponse().body().string();
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 计算gridview高度
     */
    public static void setListViewHeightBasedOnChildren(GridView listView) {
        // 获取listview的adapter
        VoiceGridviewAdapter listAdapter = (VoiceGridviewAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽,有多少列
        int col = 2;// listView.getNumColumns();
        int totalHeight = 0;
        // i每次加4,相当于listAdapter.getCount()小于等于4时 循环一次,计算一次item的高度,
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i += col) {
        // 获取listview的每一个item
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
        // 获取item的高度和
            totalHeight += listItem.getMeasuredHeight();
        }
        // 获取listview的布局参数
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
        // 设置参数
        listView.setLayoutParams(params);
    }

}
