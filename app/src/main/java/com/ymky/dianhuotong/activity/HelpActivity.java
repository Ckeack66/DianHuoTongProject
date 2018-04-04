package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.ymky.dianhuotong.help.adapter.HelpExpandableListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {
    @BindView(R.id.help_expandable_listview)
    ExpandableListView expandableListView;
    @BindView(R.id.help_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;

    private HelpExpandableListViewAdapter helpExpandableListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("帮助");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        helpExpandableListViewAdapter = new HelpExpandableListViewAdapter(this);
        expandableListView.setAdapter(helpExpandableListViewAdapter);
    }
}
