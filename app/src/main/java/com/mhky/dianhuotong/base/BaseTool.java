package com.mhky.dianhuotong.base;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ListView;

import com.lzy.okgo.model.Response;
import com.mhky.dianhuotong.BuildConfig;
import com.mhky.dianhuotong.activity.CreatShopActivity;
import com.mhky.dianhuotong.addshop.adapter.AddShopAdapter;
import com.mhky.dianhuotong.invoice.VoiceGridviewAdapter;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListViewGridviewAdpter;
import com.mhky.dianhuotong.shop.adapter.ShopListviewAdapter;

import org.litepal.util.LogUtil;

import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by Administrator on 2018/3/31.
 * 工具类
 */

public class BaseTool {
    private static final String TAG = "BaseTool";

    //StartActivity   不带参数
    public static void goActivityNoData(Context context, Class cls) {
        Intent intent = new Intent();
        intent.setClass(context, cls);
        context.startActivity(intent);
    }

    //StartActivity   带参数
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

    /**
     * 计算gridview高度
     */
    public static void setListViewHeightBasedOnChildren(GridView listView, int number) {
        // 获取listview的adapter
        AllGoodsListViewGridviewAdpter listAdapter = (AllGoodsListViewGridviewAdpter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        // 固定列宽,有多少列
        int col = number;// listView.getNumColumns();
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

    /**
     * 计算listview高度
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取listview的adapter
        ShopListviewAdapter listAdapter = (ShopListviewAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        // i每次加4,相当于listAdapter.getCount()小于等于4时 循环一次,计算一次item的高度,
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i++) {
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

    /**
     * 计算listview高度
     */
    public static void setListViewHeightBasedOnChildrenCustom(ListView listView) {
        // 获取listview的adapter
        AddShopAdapter listAdapter = (AddShopAdapter) listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        // i每次加4,相当于listAdapter.getCount()小于等于4时 循环一次,计算一次item的高度,
        // listAdapter.getCount()小于等于8时计算两次高度相加
        for (int i = 0; i < listAdapter.getCount(); i++) {
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

    /**
     * 创建一条图片地址uri,用于保存拍照后的照片
     *
     * @param context
     * @return 图片的uri
     */
    public static Uri createImagePathUri(Context context) {
        Uri imageFilePath = null;
        String status = Environment.getExternalStorageState();
        SimpleDateFormat timeFormatter = new SimpleDateFormat(
                "yyyyMMdd_HHmmss", Locale.CHINA);
        long time = System.currentTimeMillis();
        String imageName = timeFormatter.format(new Date(time));
        // ContentValues是我们希望这条记录被创建时包含的数据信息
        ContentValues values = new ContentValues(3);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, imageName);
        values.put(MediaStore.Images.Media.DATE_TAKEN, time);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg");
        if (status.equals(Environment.MEDIA_MOUNTED)) {// 判断是否有SD卡,优先使用SD卡存储,当没有SD卡时使用手机存储
            imageFilePath = context.getContentResolver().insert(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        } else {
            imageFilePath = context.getContentResolver().insert(
                    MediaStore.Images.Media.INTERNAL_CONTENT_URI, values);
        }
        BaseTool.logPrint(TAG, "生成的照片输出路径：" + imageFilePath.toString());
        return imageFilePath;
    }


    /**
     * 时间转换
     */
    public static String[] simpledateFommet(long times) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = simpleDateFormat.format(times);
        BaseTool.logPrint(TAG, "simpledateFommet: ---" + time);
        String time1[] = time.split(":");
        return time1;
    }

    /**
     * haseMap转换成parameters
     *
     */

    /**
     * 将map转换成url
     *
     * @param map
     * @return
     */
    public static String getUrlParamsByMap(Map map, boolean isSort) {
        if (map == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        List keys = new ArrayList(map.keySet());
        if (isSort) {
            Collections.sort(keys);
        }
        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = map.get(key).toString();
            String value1 = Uri.encode(value);
            sb.append(key + "=" + value1);
            sb.append("&");
        }
        String s = sb.toString();
        if (s.endsWith("&")) {
            s = s.substring(0, s.lastIndexOf("&"));
        }
        /*
         * for (Map.Entry entry : map.entrySet()) {
         * sb.append(entry.getKey() +"="+ entry.getValue()); sb.append("&;"); }
         * String s = sb.toString(); if (s.endsWith("&;")) { //s =
         * StringUtils.substringBeforeLast(s,"&;"); s = s.substring(0,
         * s.lastIndexOf("&;")); }
         */
        return s;
    }

    /**
     * 根据手机分辨率从DP转成PX
     *
     * @param context
     * @param dpValue
     * @return
     */
    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 将sp值转换为px值，保证文字大小不变
     *
     * @param spValue
     * @return
     */
    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 根据手机的分辨率PX(像素)转成DP
     *
     * @param context
     * @param pxValue
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 将px值转换为sp值，保证文字大小不变
     *
     * @param pxValue
     * @return
     */

    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }

    //比较两个日期前后  str1 > str2 (true)             str1 < str2 (false)
    public static boolean isDateOneBigger(String str1, String str2) {
        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = true;
        } else if (dt1.getTime() < dt2.getTime()) {
            isBigger = false;
        }
        return isBigger;
    }

    public static void logPrint(String ar1, String ar2) {
//        if (BuildConfig.LOG_DEBUG){
        Log.e("log开始打印->", "**************************************************************");
        Log.e(ar1, ar2);
//        Log.e("log打印结束->", "**************************************************************");
//        }
    }

    public static Integer getLocalVersion(Context ctx) {
        int localVersion = -1;
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionCode;
            logPrint("TAG", "本软件的版本号----" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**
     * 获取本地软件版本号名称
     */
    public static String getLocalVersionName(Context ctx) {
        String localVersion = "";
        try {
            PackageInfo packageInfo = ctx.getApplicationContext()
                    .getPackageManager()
                    .getPackageInfo(ctx.getPackageName(), 0);
            localVersion = packageInfo.versionName;
            logPrint("TAG", "本软件的版本号。。" + localVersion);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return localVersion;
    }

    /**空String判断*/
    public static boolean isEmpty(CharSequence str) {
        if (TextUtils.isEmpty(str) || str.equals("null") || str == null) {//后台可能会返回“null”
            return true;
        } else {
            return false;
        }
    }
}
