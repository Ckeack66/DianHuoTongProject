package com.mhky.dianhuotong.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWalletActivity extends AppCompatActivity {
    @BindView(R.id.my_wallet_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        ImmersionBar.with(this).titleBar(dianHuoTongBaseTitleBar).transparentStatusBar().init();
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("我的钱包");
        dianHuoTongBaseTitleBar.setBackGround(Color.parseColor("#00ffffff"));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
