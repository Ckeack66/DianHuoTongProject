package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;

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

public class SelelctAdressActivity extends BaseActivity  implements AdressIF {
    @BindView(R.id.adress1_expandblelistview)
    ExpandableListView expandableListView;
    @BindView(R.id.adress1_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.adress1_waveslidebar)
    WaveSideBar waveSideBar;
    @BindView(R.id.adress1_image_city)
    TextView textViewLocation;
    private List<AdressBaseInfo> list;
    private AdressPrecenter adressPrecenter;
    private AdressExpandbleListviewAdapter adressExpandbleListviewAdapter;
    private String[] mIndexString;
    private ArrayList<String> stringList;
    private HashMap hashMap;
    private Context context;
    private String mLocation;
    private static final String TAG = "Adress1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selelct_adress);
        ButterKnife.bind(this);
        context = this;
        inIt();
    }

    private void inIt() {
        BaseActivityManager.getInstance().addActivity(this);
        mLocation=getIntent().getExtras().getString("area");
        textViewLocation.setText(mLocation);
        Log.d(TAG, "inIt: ------"+mLocation);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("选择城市");
        adressPrecenter = new AdressPrecenter(this);
        list = new ArrayList<AdressBaseInfo>();
        stringList = new ArrayList<>();
        adressPrecenter.getCityInfo();
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
                bundle.putString("cityID", list.get(groupPosition).getRegion().get(childPosition).getId() + "");
                BaseTool.goActivityWithData(context, SelelctAdress1Activity.class, bundle);
                return false;
            }
        });
    }

    @Override
    public void getCityInfoSuccess(int code, String result) {
        if (code == 200 && result != null && result.length() > 0) {
            list = JSON.parseArray(result, AdressBaseInfo.class);
            if (list != null) {
                Log.d(TAG, "getCityInfoSuccess: ----" + list.size());
                adressExpandbleListviewAdapter = new AdressExpandbleListviewAdapter(this, list);
                expandableListView.setAdapter(adressExpandbleListviewAdapter);
                for (int a = 0; a < list.size(); a++) {
                    expandableListView.expandGroup(a);
                    if (a == 0) {
                        stringList.add("#");
                    } else {
                        stringList.add(list.get(a).getFirstName());
                    }

                }
                Log.d(TAG, "getCityInfoSuccess: ----" + stringList.size());
                mIndexString = stringList.toArray(new String[0]);
                adressPrecenter.getSlidData(mIndexString);

            }
        }
    }

    @Override
    public void getCityInfoFailed(int code, String result) {
        Log.d(TAG, "getCityInfoFailed: " + code);
        Log.d(TAG, "getCityInfoFailed: " + result);
    }

    @Override
    public void getAreaInfoSuccess(int code, String result) {

    }

    @Override
    public void getAreaInfoFailed(int code, String result) {

    }

    @Override
    public void getAdressInfoSuccess(int code, String result) {

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
                Log.d(TAG, "onSelectIndexItem: " + hashMap.get(index));
                Log.d(TAG, "onSelectIndexItem: ----" + index);
            }
        });
    }
}
