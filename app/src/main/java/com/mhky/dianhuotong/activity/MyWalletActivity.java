package com.mhky.dianhuotong.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.bean.MyWalletMoneyInfo;
import com.mhky.dianhuotong.shop.precenter.MyWalletPresenter;
import com.mhky.dianhuotong.shop.shopif.MyWalletMoneyIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWalletActivity extends BaseActivity implements MyWalletMoneyIF {
    @BindView(R.id.my_wallet_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.my_wallet_money)
    TextView textViewMoney;
    private MyWalletPresenter myWalletPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wallet);
        ButterKnife.bind(this);
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
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
        myWalletPresenter = new MyWalletPresenter().setWalletMoneyIF(this);
        myWalletPresenter.getMyWallet();
    }

    @Override
    public void getMyWalletMoneySuccess(int code, String result) {
        if (code == 200) {
            MyWalletMoneyInfo myWalletMoneyInfo = JSON.parseObject(result, MyWalletMoneyInfo.class);
            textViewMoney.setText("￥"+String.valueOf(new BigDecimal(myWalletMoneyInfo.getWaitMoney()).setScale(2,BigDecimal.ROUND_HALF_DOWN)));
        }
    }

    @Override
    public void getMyWalletMoneyFailed(int code, String result) {

    }
}
