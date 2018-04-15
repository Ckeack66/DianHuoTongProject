package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;
import com.mhky.dianhuotong.addshop.adapter.AddShopAdapter;
import com.mhky.dianhuotong.addshop.addshopif.AddShopIF;
import com.mhky.dianhuotong.addshop.precenter.AddShopPrecenter;
import com.mhky.dianhuotong.baidumap.mapif.GetLocattionListener;
import com.mhky.dianhuotong.baidumap.mapif.MyLocationListener;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@PermissionsRequestSync(permission = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, value = {101, 102, 103, 104})
public class AddShopActivity extends BaseActivity implements AdapterView.OnItemClickListener, AddShopIF, GetLocattionListener {
    @BindView(R.id.addshop_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.addshop_listview)
    ListView listView;
    private AddShopAdapter addShopAdapter;
    private AddShopPrecenter addShopPrecenter;
    private boolean a = false;
    private boolean b = false;
    public LocationClient locationClient = null;
    private MyLocationListener myLocationListener;

    private List<ShopBaseInfo> shopBaseInfoList;
    private static final String TAG = "AddShopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop);
        ButterKnife.bind(this);
        inIt();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
        listView.setOnItemClickListener(this);
        myLocationListener = new MyLocationListener(this);
        addShopPrecenter = new AddShopPrecenter(this);
        BaseActivityManager.getInstance().addActivity(this);
        Permissions4M.get(this).requestSync();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick(R.id.addshop_to_creatshop)
    void goCreatShop() {
        BaseTool.goActivityNoData(this, CreatShopActivity.class);
    }

    @OnClick(R.id.addshop_view_more)
    void goViewShop() {
        BaseTool.goActivityNoData(this, AddShop1Activity.class);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("shop", shopBaseInfoList.get(position));
        BaseTool.goActivityWithData(this, AddShop3Activity.class, bundle);
    }

    @Override
    public void getShopInfoSuccess(int code, String result) {
        if (code == 200 && result != null && !result.equals("")) {
            shopBaseInfoList = JSON.parseArray(result, ShopBaseInfo.class);
            if (shopBaseInfoList != null) {
                Log.d(TAG, "getShopInfoSuccess: --list" + shopBaseInfoList.size());
                addShopAdapter = new AddShopAdapter(this, shopBaseInfoList);
                listView.setAdapter(addShopAdapter);
            }
//            String results = "{\"shopinfo\":" + result + "}";
//            Log.d(TAG, "getShopInfoSuccess: ");
//            ShopInfo shopInfo = JSON.parseObject(results, ShopInfo.class);
//            Log.d(TAG, "getShopInfoSuccess: --" + shopInfo.getShopinfo().size());
//            Log.d(TAG, "getShopInfoSuccess: --");
        }
    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }

    @PermissionsGranted({101, 102, 103, 104})
    void getLocationGrantsucess(int code) {
        switch (code) {
            case 101:
                a = true;
                initLocation();
                break;
            case 102:
                break;
            case 103:
                b = true;
                initLocation();
                break;
            case 104:
                break;
        }
    }

    @PermissionsDenied({101, 102, 103, 104})
    void getLocationGrantFaile(int code) {
        switch (code) {
            case 101:
                ToastUtil.makeText(this, "请打开定位权限", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                break;
            case 103:
                break;
            case 104:
                break;
        }
    }


    private void initLocation() {
        if (a && b) {
            locationClient = new LocationClient(getApplicationContext());
            LocationClientOption option = new LocationClientOption();
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            option.setCoorType("bd09ll");
            option.setIsNeedAddress(true);
            option.setIsNeedLocationDescribe(true);
            //可选，设置定位模式，默认高精度
            //LocationMode.Hight_Accuracy：高精度；
            //LocationMode. Battery_Saving：低功耗；
            //LocationMode. Device_Sensors：仅使用设备；
            option.setScanSpan(0);
            //可选，设置发起定位请求的间隔，int类型，单位ms
            //如果设置为0，则代表单次定位，即仅定位一次，默认为0
            //如果设置非0，需设置1000ms以上才有效
            option.setOpenGps(true);
            //可选，设置是否使用gps，默认false
            //使用高精度和仅用设备两种定位模式的，参数必须设置为true
            option.setLocationNotify(true);
            //可选，设置是否当GPS有效时按照1S/1次频率输出GPS结果，默认false
            option.SetIgnoreCacheException(false);
            //可选，设置是否收集Crash信息，默认收集，即参数为false
            option.setWifiCacheTimeOut(5 * 60 * 1000);
            //可选，7.2版本新增能力
            //如果设置了该接口，首次启动定位时，会先判断当前WiFi是否超出有效期，若超出有效期，会先重新扫描WiFi，然后定位
            option.setEnableSimulateGps(false);
            //可选，设置是否需要过滤GPS仿真结果，默认需要，即参数为false
            locationClient.setLocOption(option);
            //mLocationClient为第二步初始化过的LocationClient对象
            //需将配置好的LocationClientOption对象，通过setLocOption方法传递给LocationClient对象使用
            //更多LocationClientOption的配置，请参照类参考中LocationClientOption类的详细说明
            locationClient.registerLocationListener(myLocationListener);
            locationClient.start();
        }

    }

    @Override
    public void getResultLocation(BDLocation location, String locate) {
        Log.d(TAG, "getResultLocation: 定位结果---" + location.getLocType());
        switch (location.getLocType()) {
            case 62:
                ToastUtil.makeText(this, "定位失败", Toast.LENGTH_SHORT).show();
                break;
            case 63:
                ToastUtil.makeText(this, "网络异常", Toast.LENGTH_SHORT).show();
                break;
        }
        if (location.getLocType() == 61 || location.getLocType() == 66 || location.getLocType() == 161) {
            HttpParams httpParams = new HttpParams();
            httpParams.put("region", location.getDistrict());
            addShopPrecenter.getShopInfo(httpParams);
        } else {
            ToastUtil.makeText(this, "未定位到当前区域", Toast.LENGTH_SHORT).show();
        }
    }
}
