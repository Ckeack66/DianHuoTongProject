package com.mhky.dianhuotong.shop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview1Adapter;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview2Adapter;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllGoodsActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @BindView(R.id.all_goods_listview1)
    ListView listView1;
    @BindView(R.id.all_goods_listview2)
    ListView listView2;
    private AllGoodsListview1Adapter all_goods_listview1;
    private AllGoodsListview2Adapter all_goods_listview2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        all_goods_listview1 = new AllGoodsListview1Adapter(new Random().nextInt(10), this);
        listView1.setAdapter(all_goods_listview1);
        all_goods_listview2 = new AllGoodsListview2Adapter();
        listView2.setAdapter(all_goods_listview2);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.all_goods_listview1:
                all_goods_listview1.setIndexColor(position);
                all_goods_listview1.notifyDataSetChanged();
                break;
            case R.id.all_goods_listview2:
                break;
        }
    }
}
