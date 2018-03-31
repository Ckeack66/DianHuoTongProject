package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DiaHuiTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonInfoUpdateActivity extends AppCompatActivity {
    @BindView(R.id.person_info_update_title)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_update);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.person_update_title));
        diaHuiTongBaseTitleBar.setRightText(getString(R.string.person_update_save));
    }
}
