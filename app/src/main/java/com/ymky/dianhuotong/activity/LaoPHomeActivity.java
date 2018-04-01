package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LaoPHomeActivity extends AppCompatActivity {
    @BindView(R.id.laop_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lao_phome);
        ButterKnife.bind(this);
        inIt();
    }
    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("老P之家");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
