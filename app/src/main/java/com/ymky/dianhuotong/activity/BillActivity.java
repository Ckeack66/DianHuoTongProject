package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.custom.viewgroup.DiaHuiTongBaseTitleBar;
import com.ymky.dianhuotong.dingdan.adapter.BillActivityListViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BillActivity extends AppCompatActivity {
    @BindView(R.id.bill_titlebar)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.bill_listview)
    ListView listView;
    private BillActivityListViewAdapter billActivityListViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        ButterKnife.bind(this);
        inIt();
    }
    private void inIt(){
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.bill_title));
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setRightImage(R.drawable.icon_date);
        billActivityListViewAdapter=new BillActivityListViewAdapter(this,10);
        listView.setAdapter(billActivityListViewAdapter);
    }
}
