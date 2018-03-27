package com.ymky.dianhuotong.activity;

import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import com.jaeger.library.StatusBarUtil;
import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.DiaHuiTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.register_titlebar)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        inIt();

//        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
//// 获取状态栏高度
//        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);
//
//        Log.d("测试", "onCreate: "+resourceId+"=====+"+statusBarHeight);
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.mipmap.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView("注册");
//         StatusBarUtil.setTranslucent(this);
//        StatusBarUtil.setColor(this, Color.parseColor("#04c1ab"));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }
    }
}
