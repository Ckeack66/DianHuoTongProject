package com.mhky.dianhuotong.base;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2018/4/15.
 */

public class BaseActivityManager {
    private List<Activity> activityList = new ArrayList<Activity>();
    ;
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
        Log.d(TAG, "addActivity:页面管理器添加页面 " + activityList.size());
    }

    public void removeActivity(Activity activity) {
        activityList.remove(activity.getComponentName().getClassName());
        Log.d(TAG, "addActivity:页面管理器移除页面 " + activityList.size());
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

    public void clearAllActivity() {
        activityList.clear();
        Log.d(TAG, "clearAllActivity: 页面管理器已清空");
    }
}
