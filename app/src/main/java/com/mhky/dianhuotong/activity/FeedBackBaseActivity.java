package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.pgyersdk.feedback.PgyFeedbackShakeManager;

public class FeedBackBaseActivity extends BaseActivity {
    private static final String TAG = "FeedBackBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        Permissions4M.get(this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                // .requestUnderM(false)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.RECORD_AUDIO)
                // 权限码
                .requestCodes(1001)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        PgyFeedbackShakeManager.unregister();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 自定义摇一摇的灵敏度，默认为950，数值越小灵敏度越高。
        PgyFeedbackShakeManager.setShakingThreshold(800);

        // 以对话框的形式弹出，对话框只支持竖屏
        PgyFeedbackShakeManager.register(this);

        // 以Activity的形式打开，这种情况下必须在AndroidManifest.xml配置FeedbackActivity
        // 打开沉浸式,默认为false
        // FeedbackActivity.setBarImmersive(true);
        //PgyFeedbackShakeManager.register(MainActivity.this, true); 相当于使用Dialog的方式；
        PgyFeedbackShakeManager.register(this, true);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
