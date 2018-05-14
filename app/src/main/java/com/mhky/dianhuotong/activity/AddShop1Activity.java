package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.gjiazhe.wavesidebar.WaveSideBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.adapter.AddShopAdapter;
import com.mhky.dianhuotong.addshop.addshopif.AddShopIF;
import com.mhky.dianhuotong.addshop.precenter.AddShopPrecenter;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddShop1Activity extends BaseActivity implements WaveSideBar.OnSelectIndexItemListener, AdapterView.OnItemClickListener ,AddShopIF{
    @BindView(R.id.addshop1_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.addshop1_listview)
    ListView listView;
    private AddShopAdapter addShopAdapter;
    private AddShopPrecenter addShopPrecenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop1);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("加入店铺");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //waveSideBar.setTextColor(getResources().getColor(R.color.color333333));
        //addShopAdapter = new AddShopAdapter(this);
        listView.setAdapter(addShopAdapter);
        listView.setOnItemClickListener(this);
        addShopPrecenter=new AddShopPrecenter(this);
        BaseActivityManager.getInstance().addActivity(this);
        //addShopPrecenter.getShopInfo();
    }

    @Override
    public void onSelectIndexItem(String index) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.makeText(this, "点击了item" + position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getShopInfoSuccess(int code, String result) {

    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }
}
