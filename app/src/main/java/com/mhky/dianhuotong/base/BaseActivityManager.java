package com.mhky.dianhuotong.base;

import android.app.Activity;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 项目基类  为了管理activity
 * Created by Administrator on 2018/4/15.
 */

public class BaseActivityManager implements Serializable {
    private List<Activity> activityList = new ArrayList<Activity>();
    private volatile static BaseActivityManager baseActivityManager = null;
    private static final String TAG = "BaseActivityManager";

    private BaseActivityManager() { };

    //采用单例模式初始化ActivityManager，使只初始化一次
    public static BaseActivityManager getInstance() {
        if (baseActivityManager == null) {
            synchronized (BaseActivityManager.class) {
                if (baseActivityManager == null) {
                    baseActivityManager = new BaseActivityManager();
                }
            }
        }
        return baseActivityManager;
    }

    //添加activity
    public void addActivity(Activity activity) {
        activityList.add(activity);
        BaseTool.logPrint(TAG, "addActivity:页面管理器添加页面 " + activityList.size());
    }

    //移除activity
    public void removeActivity(Activity activity) {
        activityList.remove(activity.getComponentName().getClassName());
        BaseTool.logPrint(TAG, "addActivity:页面管理器移除页面 " + activityList.size());
    }

//    将activity全部关闭掉
    public void finishAllActivity() {
        if (activityList != null) {
            for (int a = 0; a < activityList.size(); a++) {
                if (!activityList.get(a).isFinishing()) {
                    activityList.get(a).finish();
                }
            }
        }
    }


    public void finnishArrayActivity(ArrayList<Activity> activityLists) {
        if (activityLists != null) {
            for (int a = 0; a < activityLists.size(); a++) {
                if (!activityLists.get(a).isFinishing()) {
                    activityLists.get(a).finish();
                }
            }
        }
    }

    //清除activityList集合
    public void clearAllActivity() {
        activityList.clear();
        BaseTool.logPrint(TAG, "clearAllActivity: 页面管理器已清空");
    }
}
