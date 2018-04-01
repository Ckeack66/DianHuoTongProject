package com.ymky.dianhuotong.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

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

}
