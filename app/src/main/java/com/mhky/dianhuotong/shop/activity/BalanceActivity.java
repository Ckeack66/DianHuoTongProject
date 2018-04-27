package com.mhky.dianhuotong.shop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BalanceActivity extends AppCompatActivity {
    @BindView(R.id.balance_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.balance_pay11)
    CheckBox checkBox1;
    @BindView(R.id.balance_pay21)
    CheckBox checkBox2;
    @BindView(R.id.balance_pay31)
    CheckBox checkBox3;
    @BindView(R.id.balance_pay3_lilayout)
    LinearLayout linearLayout;
    private int payType = -1;
    private static final String TAG = "BalanceActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("结算");
    }

    @OnClick(R.id.balance_pay1)
    void choosePay1() {
        setChoosePayState(checkBox1);
        payType = 1;
    }

    @OnClick(R.id.balance_pay2)
    void choosePay2() {
        setChoosePayState(checkBox2);
        payType = 2;
    }

    @OnClick(R.id.balance_pay3)
    void choosePay3() {
        setChoosePayState(checkBox3);
        linearLayout.setVisibility(View.VISIBLE);
        payType = 3;
    }

    @OnClick(R.id.balance_pay)
    void doPay() {
        if (payType == -1) {
            ToastUtil.makeText(this, "请选择支付方式", Toast.LENGTH_SHORT).show();
            return;
        } else if (payType == 1) {
            ToastUtil.makeText(this, "支付宝结账中...请等待", Toast.LENGTH_SHORT).show();
        } else if (payType == 2) {
            ToastUtil.makeText(this, "微信结账中...请等待", Toast.LENGTH_SHORT).show();
        } else if (payType == 3) {
            ToastUtil.makeText(this, "此订单将进行线下结账...请仔细核对商家信息", Toast.LENGTH_SHORT).show();
        }

    }

    private void setChoosePayState(CheckBox choosePayState) {
        checkBox1.setChecked(false);
        checkBox2.setChecked(false);
        checkBox3.setChecked(false);
        linearLayout.setVisibility(View.GONE);
        choosePayState.setChecked(true);
        Log.d(TAG, "setChoosePayState: " + payType);

    }
}
