package com.mhky.dianhuotong.shop.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview1Adapter;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview2Adapter;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.precenter.AllGoosPrecenter;
import com.mhky.dianhuotong.shop.shopif.AllGoodsIF;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AllGoodsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AllGoodsIF, AllGoodsListview2Adapter.OnItemGridviewClickListener {
    @BindView(R.id.all_goods_listview1)
    ListView listView1;
    @BindView(R.id.all_goods_listview2)
    ListView listView2;
    @BindView(R.id.all_goods_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    private AllGoodsListview1Adapter all_goods_listview1;
    private AllGoodsListview2Adapter all_goods_listview2;
    private List<GoodsBaseInfo> allGoodsBaseInfos;
    private AllGoosPrecenter allGoosPrecenter;
    private int Itype = 0;
    private int IItype = 0;
    private int IIItype = 0;
    private String II_TYPE = "102";
    private String III_TYPE = "103";
    private static final String TAG = "AllGoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        dianHuoTongShopTitleBar.setActivity(this);
        allGoosPrecenter = new AllGoosPrecenter(this);
        if (BaseApplication.getInstansApp().getAllGoodsBaseInfos() != null) {
            allGoodsBaseInfos = BaseApplication.getInstansApp().getAllGoodsBaseInfos();
            setListData(allGoodsBaseInfos);
        } else {
            allGoosPrecenter.getAllGoodsType();
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Log.d(TAG, "点击了onItemClick: -------" + position);
        switch (parent.getId()) {
            case R.id.all_goods_listview1:
                //ToastUtil.makeText(this, "点击了" + position, Toast.LENGTH_SHORT).show();
                Itype = position;
                all_goods_listview1.setIndexColor(position);
                if (allGoodsBaseInfos.get(position) != null) {
                    all_goods_listview2.updateData(allGoodsBaseInfos.get(position).getChildren());
                } else {
                    all_goods_listview2.updateData(null);
                }
                listView2.setSelection(0);
                break;
            case R.id.all_goods_listview2:
                //ToastUtil.makeText(this, "点击了第二个listview" + position, Toast.LENGTH_SHORT).show();
                IItype = position;
                Bundle bundle = new Bundle();
                bundle.putString("type", II_TYPE);
                bundle.putSerializable("data", allGoodsBaseInfos.get(Itype).getChildren().get(IItype));
                BaseTool.goActivityWithData(this, SearchGoodsActivity.class, bundle);
                break;
            default:
                Log.d(TAG, "onItemClick: " + parent);
        }
    }

    @Override
    public void getAllGoodsInfoSuccess(int code, String result) {
        if (code == 200) {
            allGoodsBaseInfos = JSON.parseArray(result, GoodsBaseInfo.class);
            setListData(allGoodsBaseInfos);
        }
    }

    private void setListData(List<GoodsBaseInfo> goodsBaseInfoList) {
        if (goodsBaseInfoList != null) {
            all_goods_listview1 = new AllGoodsListview1Adapter(goodsBaseInfoList, this);
            listView1.setAdapter(all_goods_listview1);
            listView1.setOnItemClickListener(this);
            all_goods_listview2 = new AllGoodsListview2Adapter(goodsBaseInfoList.get(0).getChildren(), this);
            all_goods_listview2.setOnItemGridviewClickListener(this);
            listView2.setAdapter(all_goods_listview2);
            listView2.setOnItemClickListener(this);
        }
    }

    @Override
    public void getAllGoodsInfoFailed(int code, String result) {

    }

    @Override
    public void onclickItem(int positionParent, int positionChild) {
        Log.d(TAG, "onclickItem: ----" + positionParent + "------" + positionChild);
        IItype = positionParent;
        IIItype = positionChild;
        Bundle bundle = new Bundle();
        bundle.putString("type", III_TYPE);
        bundle.putString("data", allGoodsBaseInfos.get(Itype).getChildren().get(IItype).getChildren().get(IIItype).getId() + "");
        BaseTool.goActivityWithData(this, SearchGoodsActivity.class, bundle);
    }
}
