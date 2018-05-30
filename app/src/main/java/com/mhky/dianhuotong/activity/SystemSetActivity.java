package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.RelativeLayout;

import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.SwitchView;

public class SystemSetActivity extends BaseActivity {
    @BindView(R.id.system_set_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.system_switch)
    SwitchView switchView;
    @BindView(R.id.system_set_help)
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("系统设置");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switchView.setColor(getResources().getColor(R.color.color04c1ab), getResources().getColor(R.color.colorCDCDCD));
    }

    @OnClick(R.id.system_set_help)
    void goHelpActivity() {
        BaseTool.goActivityNoData(this, HelpActivity.class);
    }

    @OnClick(R.id.system_logout)
    void logoutUser() {
        BaseApplication.getInstansApp().clearToaken();
        BaseTool.goActivityNoData(this, LoginActivity.class);
        sendBroadcast(new Intent(MainActivity.action));
        finish();
    }
    @OnClick(R.id.system_set_up)
    void goFeedBackActivity(){
        Permissions4M.get(this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                // .requestUnderM(false)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.RECORD_AUDIO)
                // 权限码
                .requestCodes(10010)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @PermissionsGranted(10010)
    void granSuccess() {
        BaseTool.goActivityNoData(this, FeedBackBaseActivity.class);
    }
}
