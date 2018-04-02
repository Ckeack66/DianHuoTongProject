package com.ymky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.addshop.adapter.AddShopAdapter;
import com.ymky.dianhuotong.base.BaseTool;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShopActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.addshop_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.addshop_listview)
    ListView listView;
    private AddShopAdapter addShopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("新增店铺");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addShopAdapter = new AddShopAdapter(this);
        listView.setAdapter(addShopAdapter);
        listView.setOnItemClickListener(this);
    }

    @OnClick(R.id.addshop_to_creatshop)
    void goCreatShop() {
        BaseTool.goActivityNoData(this, CreatShopActivity.class);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
