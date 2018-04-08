package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.ToastUtil;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.ymky.dianhuotong.shop.custom.ShopTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DianHuoTongShopActivity extends BaseActivity implements ShopTitleBar.ShopTitleBarOnclickListener {
    @BindView(R.id.shop_title_bar)
    ShopTitleBar shopTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_huo_tong_shop);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        shopTitleBar = new ShopTitleBar(this, this);
    }

    @Override
    public void onclickBack() {
        finish();
    }

    @Override
    public void onclickScanCode() {
        ToastUtil.makeText(this, "进入扫码界面", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onclickOrder() {
        ToastUtil.makeText(this, "进入订单界面", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onclickCar() {
        ToastUtil.makeText(this, "进入购物车界面", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onclickInput() {
        ToastUtil.makeText(this, "进入搜索界面", Toast.LENGTH_SHORT).show();
    }
}
