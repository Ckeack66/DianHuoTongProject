package com.mhky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.AppSharePreferenceMgr;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;

public class InitActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_init);
        init();
    }

    private void init() {
        //先获取本地存储的软件版本号->如果获取失败->则是新装软件跳转到引导页->引导结束后将当前软件版本号存起来
        //                          ->获取成功->对比当前软件版本号->如果小于当前软件版本号则是软件升级了->则跳转到引导页
        //                          ->获取成功->对比当前软件版本号->如果等于当前软件版本号则是当前版本->跳转到启动页
        Integer oldVersion = (Integer) AppSharePreferenceMgr.get(this, "app_version", -1);
        BaseTool.logPrint("init------------------------",oldVersion+"");
        if (oldVersion == null || oldVersion == -1) {
            BaseTool.goActivityNoData(this, BootPageActivity.class);
            finish();
        } else {
            Integer newVersion = BaseTool.getLocalVersion(this);
            if (newVersion == -1) {
                BaseTool.goActivityNoData(this, BootPageActivity.class);
                finish();
            } else if (newVersion > oldVersion) {
                BaseTool.goActivityNoData(this, BootPageActivity.class);
                finish();
            } else if (newVersion == oldVersion) {
                BaseTool.goActivityNoData(this, WelcomActivity.class);
                finish();
            }
        }
    }
}
