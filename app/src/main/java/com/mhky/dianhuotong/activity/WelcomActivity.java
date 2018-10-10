package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.gyf.barlibrary.BarHide;
import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.stone.countdownprogress.CountDownProgress;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 冷启动页
 */

public class WelcomActivity extends BaseActivity {
    @BindView(R.id.welcom_countdown)
    CountDownProgress countDownProgress;
    @BindView(R.id.welcom_img)
    ImageView imageView;
    private Context context;
    private boolean aBoolean = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);
        ButterKnife.bind(this);
        ImmersionBar.with(this).hideBar(BarHide.FLAG_HIDE_BAR).init();
        context = this;
        inIt();
    }

    private void inIt() {
        countDownProgress.startCountDownTime();
        countDownProgress.setFinishListener(new CountDownProgress.OnFinishListener() {
            @Override
            public void onFinish() {
                finishs(1);
            }
        });
        countDownProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishs(2);
            }
        });
    }

    private void finishs(int a) {
        if (!aBoolean) {
            return;
        }
        if (a == 1) {
            aBoolean = false;
            BaseTool.goActivityNoData(context, MainActivity.class);
            finish();
        } else if (a == 2) {
            aBoolean = false;
            countDownProgress.cancel();
            BaseTool.goActivityNoData(context, MainActivity.class);
            finish();
        } else if (a == 3) {
//            aBoolean = false;
//            countDownProgress.cancel();
//            BaseTool.goActivityNoData(this, TransferPostActivity.class);
////           // BaseTool.goActivityNoData(this, OderOkActivity.class);
//            finish();
        }

    }

    @OnClick(R.id.welcom_img)
    void goCouponActivity() {
        finishs(3);
    }

}
