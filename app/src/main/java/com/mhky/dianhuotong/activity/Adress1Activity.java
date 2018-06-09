package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Adress1Activity extends BaseActivity implements AdressIF {
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
    private static final String TAG = "Adress1Activity";
    private String location;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adress1);
        ButterKnife.bind(this);
        context = this;
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
    }

    private void inIt() {
        loadingDialog = new LoadingDialog(this);
        loadingDialog.show();
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
        location = getIntent().getExtras() == null ? null : getIntent().getExtras().getString("location");
        textViewLocation.setText(location == null ? "定位失败" : location);
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
                bundle.putString("areaID", list.get(groupPosition).getRegion().get(childPosition).getId() + "");
                CreatShopActivity.returnCity = list.get(groupPosition).getRegion().get(childPosition).getName();
                CreatShopActivity.activities.add(Adress1Activity.this);
                BaseTool.goActivityWithData(context, Adress2Activity.class, bundle);
                return false;
            }
        });
    }

    @OnClick(R.id.adress1_image_city)
    void byLocationGoNext() {
        try {
            if (list != null && list.size() > 0) {
                if (location != null) {
                    here:
                    for (int a = 0; a < list.size(); a++) {
                        for (int b = 0; b < list.get(a).getRegion().size(); b++) {
                            if (list.get(a).getRegion().get(b).getName().contains(location.substring(0, location.length() - 1))) {
                                Bundle bundle = new Bundle();
                                bundle.putString("areaID", list.get(a).getRegion().get(b).getId() + "");
                                CreatShopActivity.returnCity = list.get(a).getRegion().get(b).getName();
                                CreatShopActivity.activities.add(Adress1Activity.this);
                                BaseTool.goActivityWithData(context, Adress2Activity.class, bundle);
                                break here;
                            }
                        }

                    }

                }
            } else {
                ToastUtil.makeText(this, "定位出错", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void getCityInfoSuccess(int code, String result) {
        try {
            if (code == 200 && result != null && result.length() > 0) {
                list = JSON.parseArray(result, AdressBaseInfo.class);
                if (list != null) {
                    BaseTool.logPrint(TAG, "getCityInfoSuccess: ----" + list.size());
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
                    BaseTool.logPrint(TAG, "getCityInfoSuccess: ----" + stringList.size());
                    mIndexString = stringList.toArray(new String[0]);
                    adressPrecenter.getSlidData(mIndexString);
                    if (loadingDialog != null && loadingDialog.isShowing()) {
                        loadingDialog.dismiss();
                    }
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        } finally {
            if (loadingDialog != null && loadingDialog.isShowing()) {
                loadingDialog.dismiss();
            }
        }
    }

    @Override
    public void getCityInfoFailed(int code, String result) {
        BaseTool.logPrint(TAG, "getCityInfoFailed: " + code);
        BaseTool.logPrint(TAG, "getCityInfoFailed: " + result);
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
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
        try {
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
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }
}
