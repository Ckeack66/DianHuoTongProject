package com.ymky.dianhuotong.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.ymky.dianhuotong.dingdan.adapter.BillActivityListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillActivity extends AppCompatActivity {
    @BindView(R.id.bill_titlebar)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.bill_listview)
    ListView listView;
    private BillActivityListViewAdapter billActivityListViewAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        context = this;
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.bill_title));
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setRightImage(R.drawable.icon_date);
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        diaHuiTongBaseTitleBar.setRightOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了日历", Toast.LENGTH_SHORT);
            }
        });
        billActivityListViewAdapter = new BillActivityListViewAdapter(this, 10);
        listView.setAdapter(billActivityListViewAdapter);
    }
}
