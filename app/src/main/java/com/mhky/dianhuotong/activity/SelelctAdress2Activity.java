package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;

import com.alibaba.fastjson.JSON;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.adapter.AdressExpandbleListviewAdapter;
import com.mhky.dianhuotong.addshop.addshopif.AdressIF;
import com.mhky.dianhuotong.addshop.bean.AdressBaseInfo;
import com.mhky.dianhuotong.addshop.precenter.AdressPrecenter;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelelctAdress2Activity extends BaseActivity implements AdressIF {
    @BindView(R.id.adress3_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.addshop3_expandblelistview)
    ExpandableListView expandableListView;
    @BindView(R.id.addshop3_waveslidebar)
    WaveSideBar waveSideBar;
    private List<AdressBaseInfo> list;
    private AdressPrecenter adressPrecenter;
    private AdressExpandbleListviewAdapter adressExpandbleListviewAdapter;
    private String[] mIndexString;
    private ArrayList<String> stringList;
    private HashMap hashMap;
    private String areaID;
    private Context context;
    private static final String TAG = "Adress3Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress3);
        ButterKnife.bind(this);
        context = this;
        inIt();
    }

    private void inIt() {
        BaseActivityManager.getInstance().addActivity(this);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("选择街道");
        areaID = getIntent().getExtras().getString("areaID");
        adressPrecenter = new AdressPrecenter(this);
        list = new ArrayList<AdressBaseInfo>();
        stringList = new ArrayList<>();
        if (areaID != null) {
            adressPrecenter.getStressInfo(areaID);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Bundle bundle = new Bundle();
                bundle.putString("area", list.get(groupPosition).getRegion().get(childPosition).getName() + "");
                BaseTool.goActivityWithData(context, AddShop1Activity.class, bundle);
                return false;
            }
        });
    }

    @Override
    public void getCityInfoSuccess(int code, String result) {

    }

    @Override
    public void getCityInfoFailed(int code, String result) {

    }

    @Override
    public void getAreaInfoSuccess(int code, String result) {

    }

    @Override
    public void getAreaInfoFailed(int code, String result) {

    }

    @Override
    public void getAdressInfoSuccess(int code, String result) {
        if (code == 200 && result != null && result.length() > 0) {
            list = JSON.parseArray(result, AdressBaseInfo.class);
            if (list != null) {
                BaseTool.logPrint(TAG, "getCityInfoSuccess: ----" + list.size());
                adressExpandbleListviewAdapter = new AdressExpandbleListviewAdapter(this, list);
                expandableListView.setAdapter(adressExpandbleListviewAdapter);
                for (int a = 0; a < list.size(); a++) {
                    expandableListView.expandGroup(a);
                    stringList.add(list.get(a).getFirstName());

                }
                BaseTool.logPrint(TAG, "getAdressInfoSuccess: ===" + stringList.size());
                mIndexString = stringList.toArray(new String[0]);
                adressPrecenter.getSlidData(mIndexString);

            }
        }
    }

    @Override
    public void getAdressInfoFailed(int code, String result) {

    }

    @Override
    public void getSliderInfo(HashMap hashMaps) {
        hashMap = hashMaps;
        waveSideBar.setIndexItems(mIndexString);
        waveSideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                expandableListView.setSelectedGroup(Integer.parseInt(hashMap.get(index).toString()));
                BaseTool.logPrint(TAG, "onSelectIndexItem: " + hashMap.get(index));
                BaseTool.logPrint(TAG, "onSelectIndexItem: ----" + index);
            }
        });
    }
}
