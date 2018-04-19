package com.mhky.dianhuotong.shop.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.yanzhenjie.sofia.Bar;
import com.yanzhenjie.sofia.Sofia;

public class GoodsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        inIt();
    }

    private void inIt() {
        ImmersionBar.with(this).statusBarColor("#ffffff").statusBarDarkFont(true).init();
    }
}
