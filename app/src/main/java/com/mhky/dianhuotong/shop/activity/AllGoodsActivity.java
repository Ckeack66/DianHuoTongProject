package com.mhky.dianhuotong.shop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
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
        listView1.setOnItemClickListener(this);
        all_goods_listview2 = new AllGoodsListview2Adapter(new Random().nextInt(10), 10, this);
        listView2.setAdapter(all_goods_listview2);
        listView2.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.all_goods_listview1:
                ToastUtil.makeText(this, "点击了" + position, Toast.LENGTH_SHORT).show();
                all_goods_listview1.setIndexColor(position);
                break;
            case R.id.all_goods_listview2:
                ToastUtil.makeText(this, "点击了第二个listview", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
