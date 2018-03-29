package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.DiaHuiTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyselectedActivity extends AppCompatActivity {
    @BindView(R.id.myselect_title)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myselected);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.myselect_title));
        diaHuiTongBaseTitleBar.setRightText(getString(R.string.myselected_right));
    }
}
