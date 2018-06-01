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
 * Created by Administrator on 2018/4/15.
 */

public class BaseActivityManager implements Serializable {
    private List<Activity> activityList = new ArrayList<Activity>();
    private volatile static BaseActivityManager baseActivityManager = null;
    private static final String TAG = "BaseActivityManager";

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

    public void addActivity(Activity activity) {
        activityList.add(activity);
        BaseTool.logPrint(TAG, "addActivity:页面管理器添加页面 " + activityList.size());
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity.getComponentName().getClassName());
        BaseTool.logPrint(TAG, "addActivity:页面管理器移除页面 " + activityList.size());
    }

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

    public void clearAllActivity() {
        activityList.clear();
        BaseTool.logPrint(TAG, "clearAllActivity: 页面管理器已清空");
    }
}
