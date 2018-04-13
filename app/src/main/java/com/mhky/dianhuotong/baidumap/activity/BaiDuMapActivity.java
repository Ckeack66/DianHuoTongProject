package com.mhky.dianhuotong.baidumap.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.cloud.BaseSearchInfo;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.baidumap.mapif.GetLocattionListener;
import com.mhky.dianhuotong.baidumap.mapif.MyLocationListener;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;

@PermissionsRequestSync(permission = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, value = {101, 102, 103, 104})
public class BaiDuMapActivity extends BaseActivity implements BaiduMap.OnMapClickListener, GetLocattionListener {
    @BindView(R.id.baidumap_map)
    TextureMapView textureMapView;
    @BindView(R.id.baidumap_name)
    TextView textViewName;
    @BindView(R.id.baidumap_jingdu)
    TextView textViewJingDu;
    @BindView(R.id.baidumap_weidu)
    TextView textViewWeiDu;
    @BindView(R.id.baidumap_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.baidumap_search_text)
    EditText editTextMap;
    private BaiduMap baiduMap;
    private static final String TAG = "BaiDuMapActivity";
    private BitmapDescriptor bitmapDescriptor;
    public LocationClient locationClient = null;
    private MyLocationListener myLocationListener;
    private boolean a = false;
    private boolean b = false;
    private PoiSearch poiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_du_map);
        ButterKnife.bind(this);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        textureMapView.onDestroy();
        poiSearch.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        textureMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        textureMapView.onResume();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void init() {
        baiduMap = textureMapView.getMap();
        baiduMap.setOnMapClickListener(this);
        bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_location);
        poiSearch = PoiSearch.newInstance();
        poiSearch.setOnGetPoiSearchResultListener(new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                Log.d(TAG, "onGetPoiResult: ----检索返回" + poiResult.getAllAddr());
                Log.d(TAG, "onGetPoiResult: ----检索返回" + poiResult.getAllPoi());
                Log.d(TAG, "onGetPoiResult: ----检索返回" + poiResult.getSuggestCityList());

                if (poiResult.getAllPoi() != null && poiResult.getAllPoi().size() > 0) {
                    for (int a = 0; a < poiResult.getAllPoi().size(); a++) {
                        Log.d(TAG, "onGetPoiResult: ---" + poiResult.getAllPoi().get(a).address);
                        Log.d(TAG, "onGetPoiResult: ---" + poiResult.getAllPoi().get(a).name);
                        if (a == 0) {
                            LatLng newcenpt = new LatLng(poiResult.getAllPoi().get(a).location.latitude, poiResult.getAllPoi().get(a).location.longitude);
                            MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(newcenpt, 16.0f);
                            baiduMap.animateMapStatus(u);
                            OverlayOptions overlayOptions = new MarkerOptions().position(newcenpt).icon(bitmapDescriptor).perspective(true);
                            baiduMap.addOverlay(overlayOptions);
                        }
                    }
                }
                if (poiResult.getAllAddr() != null && poiResult.getAllAddr().size() > 0) {
                    for (int a = 0; a < poiResult.getAllAddr().size(); a++) {
                        Log.d(TAG, "onGetPoiResult: ---" + poiResult.getAllAddr().get(0).address);
                    }
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
//                MyLocationData myLocationData = new MyLocationData.Builder().accuracy(10).direction(100).latitude(poiDetailResult.getLocation().latitude, poiDetailResult.getLocation().longitude).build();
//                baiduMap.setMyLocationData(myLocationData);
                Log.d(TAG, "onGetPoiDetailResult: --检索返回");
                LatLng newcenpt = new LatLng(poiDetailResult.getLocation().latitude, poiDetailResult.getLocation().longitude);
                MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(newcenpt, 16.0f);
                baiduMap.animateMapStatus(u);
                Log.d(TAG, "onGetPoiDetailResult: -----" + poiDetailResult.getLocation().longitude + "----" + poiDetailResult.getLocation().latitude);
            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {
                Log.d(TAG, "onGetPoiIndoorResult: --检索返回");
                if (poiIndoorResult.getmArrayPoiInfo() != null && poiIndoorResult.getmArrayPoiInfo().size() > 0) {
                    Log.d(TAG, "onGetPoiIndoorResult: " + poiIndoorResult.getmArrayPoiInfo().get(0).address);
                }

            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("坐标选择");
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setRightText("搜索");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setRightTextOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Log.d(TAG, "onClick: -----进行搜索");
//                poiSearch.searchInCity((new PoiCitySearchOption())
//                        .city("济南")
//                        .keyword(editTextMap.getText().toString())
//                        .isReturnAddr(true)
//                        .pageNum(1));

                GeoCoder geoCoder = GeoCoder.newInstance();
                geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
                    @Override
                    public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                        Log.d(TAG, "onGetGeoCodeResult: " + geoCodeResult.getAddress());
                        LatLng newcenpt = geoCodeResult.getLocation();
                        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(newcenpt, 16.0f);
                        baiduMap.animateMapStatus(u);
                        OverlayOptions overlayOptions = new MarkerOptions().position(newcenpt).icon(bitmapDescriptor).perspective(true);
                        baiduMap.addOverlay(overlayOptions);
                    }

                    @Override
                    public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {

                    }
                });
                geoCoder.geocode(new GeoCodeOption().city("济南").address(editTextMap.getText().toString()));
            }
        });
        myLocationListener = new MyLocationListener(this);

        Permissions4M.get(this).requestSync();

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
    public void onMapClick(LatLng latLng) {
        baiduMap.clear();
        Log.d(TAG, "onMapClick: 纬度" + latLng.latitude);
        Log.d(TAG, "onMapClick: 经度" + latLng.longitude);
        getLocateinfo(latLng);
        textViewJingDu.setText(latLng.longitude + "");
        textViewWeiDu.setText(latLng.latitude + "");
        OverlayOptions overlayOptions = new MarkerOptions().position(latLng).icon(bitmapDescriptor).perspective(true);
        baiduMap.addOverlay(overlayOptions);
    }

    @Override
    public boolean onMapPoiClick(MapPoi mapPoi) {
        baiduMap.clear();
        getLocateinfo(mapPoi.getPosition());
        Log.d(TAG, "onMapClick: 纬度" + mapPoi.getPosition().latitude);
        Log.d(TAG, "onMapClick: 经度" + mapPoi.getPosition().longitude);
        textViewJingDu.setText(mapPoi.getPosition().longitude + "");
        textViewWeiDu.setText(mapPoi.getPosition().longitude + "");
        OverlayOptions overlayOptions = new MarkerOptions().position(mapPoi.getPosition()).icon(bitmapDescriptor).perspective(true);
        baiduMap.addOverlay(overlayOptions);
        return false;
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

    /**
     * 百度地图定位回调
     *
     * @param location
     */
    @Override
    public void getResultLocation(BDLocation location, String locate) {
        Log.d(TAG, "getResultLocation: ---" + location.getRadius());
        baiduMap.setMyLocationEnabled(true);
        MyLocationData myLocationData = new MyLocationData.Builder().accuracy(location.getRadius()).direction(100).latitude(location.getLatitude()).longitude(location.getLongitude()).build();
        baiduMap.setMyLocationData(myLocationData);

        LatLng newcenpt = new LatLng(location.getLatitude(), location.getLongitude());
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLngZoom(newcenpt, 16.0f);
        baiduMap.animateMapStatus(u);
        textViewName.setText("地点：" + locate);
        textViewJingDu.setText(location.getLatitude() + "");
        textViewWeiDu.setText(location.getLongitude() + "");
    }

    private void getLocateinfo(LatLng latLng) {
        GeoCoder geoCoder = GeoCoder.newInstance();
        Log.d(TAG, "getLocateinfo: --------------");
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            @Override
            public void onGetGeoCodeResult(GeoCodeResult geoCodeResult) {
                Log.d(TAG, "onGetReverseGeoCodeResult: ");

            }

            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult reverseGeoCodeResult) {
                textViewName.setText("地点：" + reverseGeoCodeResult.getAddress() + reverseGeoCodeResult.getSematicDescription());
                Log.d(TAG, "onGetReverseGeoCodeResult: " + reverseGeoCodeResult.getAdcode());
                Log.d(TAG, "onGetReverseGeoCodeResult: " + reverseGeoCodeResult.getAddress() + reverseGeoCodeResult.getSematicDescription());
                Log.d(TAG, "onGetReverseGeoCodeResult: " + reverseGeoCodeResult.getAddressDetail().province + reverseGeoCodeResult.getAddressDetail().city + reverseGeoCodeResult.getAddressDetail().street);

            }
        });
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latLng));
    }

}
