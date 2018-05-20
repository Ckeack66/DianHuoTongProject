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


}
