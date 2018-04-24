package com.mhky.dianhuotong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.QualicationInfo;
import com.mhky.dianhuotong.baidumap.activity.BaiDuMapActivity;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreatShopActivity extends BaseActivity {
    @BindView(R.id.creatshop_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.create_shop_txt1_2)
    TextView textViewAdress;
    @BindView(R.id.create_shop_txt2_2)
    TextView textViewZuoBiao;
    @BindView(R.id.create_group)
    RadioGroup radioGroup;
    @BindView(R.id.create_shop_type1)
    RadioButton radioButton1;
    @BindView(R.id.create_shop_type2)
    RadioButton radioButton2;
    @BindView(R.id.create_shop_type3)
    RadioButton radioButton3;
    @BindView(R.id.create_shop_compenay)
    EditText editTextCompenay;
    @BindView(R.id.create_shop_compenay_adress)
    EditText editTextCompenayAdress;
    public static final int GET_DATA_RESULT_CODE = 10000;
    private String city1;
    private String area1;
    private String provance1;
    private String road1;
    private static final String TAG = "CreatShopActivity";
    private Bundle bundle;
    private QualicationInfo qualicationInfo;
    public static String returnCity = null;
    public static String returnArea = null;
    public static String returnAdress = null;
    public static ArrayList<Activity> activities;
    private String shopType;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ----回调了" + requestCode);
        if (requestCode == GET_DATA_RESULT_CODE && data != null) {
            Bundle bundle = data.getExtras();
            provance1 = bundle.getString("chooseprovance");
            city1 = bundle.getString("choosecity");
            area1 = bundle.getString("choosearea");
            road1 = bundle.getString("chooseroad");
            textViewZuoBiao.setText(bundle.getString("choosezuobiao"));
            qualicationInfo.getShopDataDTO().setMapPoint(bundle.getString("choosezuobiao"));
            Log.d(TAG, "onActivityResult: " + provance1 + city1 + area1);
            if (city1.indexOf(returnCity) == -1 || !returnArea.equals(area1)) {
                ToastUtil.makeText(this, "您选择的地点不属于" + returnCity + returnArea + returnAdress + "，请重新选择市区街道 ", Toast.LENGTH_SHORT).show();
                textViewAdress.setText(city1 + area1);
                returnCity = city1;
                returnArea = area1;
                returnAdress = "";
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_shop);
        ButterKnife.bind(this);
        inIt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (returnAdress != null || returnCity != null || returnArea != null) {
            Log.d(TAG, "onResume: ----" + returnCity + returnArea + returnAdress);
            textViewAdress.setText(returnCity + returnArea + returnAdress);
        }
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
        activities = new ArrayList<Activity>();
        BaseActivityManager.getInstance().addActivity(this);
        bundle = getIntent().getExtras();
        qualicationInfo = (QualicationInfo) bundle.getSerializable("createInfo");
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioButton1.getId() == checkedId) {
                    shopType = "DRUGSTORE";
                } else if (radioButton2.getId() == checkedId) {
                    shopType = "SUPERMARKET";
                } else if (radioButton3.getId() == checkedId) {
                    shopType = "CLINIC";
                }
            }
        });
    }

    @OnClick(R.id.create_shop_txt1)
    void goAdress1Activity() {
        BaseTool.goActivityNoData(this, Adress1Activity.class);
    }

    @OnClick(R.id.creatshop_next)
    void goInvoiceUpload() {
        Log.d(TAG, "goInvoiceUpload: " + returnCity + returnArea);
        Log.d(TAG, "goInvoiceUpload: " + city1 + area1);
        if (TextUtils.isEmpty(textViewAdress.getText())) {
            ToastUtil.makeText(this, "请选择坐地址", Toast.LENGTH_SHORT).show();
            return;
        } else if (city1 == null && area1 == null) {
            ToastUtil.makeText(this, "请选择坐标点", Toast.LENGTH_SHORT).show();
            return;
        } else if (city1.indexOf(returnCity) == -1 || !returnArea.equals(area1)) {
            ToastUtil.makeText(this, "您选择的地点不属于" + returnCity + returnArea + returnAdress + "，请重新选择市区街道", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextCompenay.getText())) {
            ToastUtil.makeText(this, "请输入公司名称", Toast.LENGTH_SHORT).show();
            return;
        } else if (shopType == null) {
            ToastUtil.makeText(this, "请选择店铺类型", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextCompenayAdress.getText())) {
            ToastUtil.makeText(this, "请输入公司详细地址", Toast.LENGTH_SHORT).show();
            return;
        } else {
            qualicationInfo.getShopDataDTO().getAddress().setCity(city1);
            qualicationInfo.getShopDataDTO().getAddress().setProvince(provance1);
            qualicationInfo.getShopDataDTO().getAddress().setDistrict(area1);
            qualicationInfo.getShopDataDTO().getAddress().setTown(returnAdress);
            qualicationInfo.getShopDataDTO().getAddress().setRoad(editTextCompenayAdress.getText().toString());
            qualicationInfo.getShopDataDTO().setMapPoint(textViewZuoBiao.getText().toString());
            qualicationInfo.getShopDataDTO().setShopType(shopType);
            qualicationInfo.getShopDataDTO().setShopname(editTextCompenay.getText().toString());
            Bundle bundle = new Bundle();
            bundle.putSerializable("qulication", qualicationInfo);
            BaseTool.goActivityWithData(this, CredentialsActivity.class, bundle);
        }

    }

    @OnClick(R.id.create_shop_txt2)
    void goBaiduMapActivity() {
        if (returnCity != null && returnAdress != null) {
            Bundle bundle = new Bundle();
            bundle.putString("city", returnCity);
            bundle.putString("name", returnAdress);
            Intent intent = new Intent(CreatShopActivity.this, BaiDuMapActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, CreatShopActivity.GET_DATA_RESULT_CODE);
        } else {
            ToastUtil.makeText(this, "请选择地址", Toast.LENGTH_SHORT).show();
        }

    }
}

