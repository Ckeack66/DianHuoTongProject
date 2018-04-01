package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PersonInfoUpdateActivity extends AppCompatActivity {
    @BindView(R.id.person_info_update_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_update);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.person_update_title));
        dianHuoTongBaseTitleBar.setRightText(getString(R.string.person_update_save));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
