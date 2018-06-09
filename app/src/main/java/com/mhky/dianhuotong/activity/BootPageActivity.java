package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.AppSharePreferenceMgr;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.main.adpter.BootPageAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.bgabanner.BGALocalImageSize;
import cn.bingoogolapple.bgabanner.BGAViewPager;

public class BootPageActivity extends AppCompatActivity {
    @BindView(R.id.boot_page_vp)
    BGABanner bgaBanner;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot_page);
        ButterKnife.bind(this);
        context = this;
        init();
    }

    private void init() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor("#ffffff").statusBarDarkFont(true).init();
        bgaBanner.setEnterSkipViewIdAndDelegate(R.id.boot_page_go, R.id.boot_page_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                Integer integer= BaseTool.getLocalVersion(context);
                BaseTool.logPrint("TAG",integer+"");
                AppSharePreferenceMgr.put(context, "app_version",integer);
                BaseTool.goActivityNoData(context,MainActivity.class);
                finish();
            }
        });
        BGALocalImageSize localImageSize = new BGALocalImageSize(720, 1280, 320, 640);
        bgaBanner.setData(localImageSize, ImageView.ScaleType.CENTER_CROP, R.drawable.img_boot_page1, R.drawable.img_boot_page2, R.drawable.img_boot_page3);
    }
}
