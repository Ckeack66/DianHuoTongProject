package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.BaseTool;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ch.ielse.view.SwitchView;

public class SystemSetActivity extends AppCompatActivity {
    @BindView(R.id.system_set_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.system_switch)
    SwitchView switchView;
    @BindView(R.id.system_set_help)
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_set);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("系统设置");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        switchView.setColor(getResources().getColor(R.color.color04c1ab), getResources().getColor(R.color.colorCDCDCD));
    }

    @OnClick(R.id.system_set_help)
    void goHelpActivity() {
        BaseTool.goActivityNoData(this, HelpActivity.class);
    }
}
