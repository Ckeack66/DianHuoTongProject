package com.mhky.dianhuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.BaiduMap;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.baidumap.activity.BaiDuMapActivity;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

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
    public static final int GET_DATA_RESULT_CODE = 10000;
    private String city;
    private String area;
    private String name;
    private String city1;
    private String area1;
    private static final String TAG = "CreatShopActivity";

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: ----回调了"+requestCode);
        if (requestCode == GET_DATA_RESULT_CODE && data != null) {
            Bundle bundle = data.getExtras();
            city1 = bundle.getString("choosecity");
            area1 = bundle.getString("choosearea");
            textViewZuoBiao.setText(bundle.getString("choosezuobiao"));
            if (!(city.equals(city1) && area.equals(area1))) {
                ToastUtil.makeText(this, "您选择的地点不属于" + city + area + name + "，请重新选择市区街道 ", Toast.LENGTH_SHORT).show();
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

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("新增店铺");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        city = "济南市";
        name = "五里沟街道";
        area = "槐荫区";
        BaseActivityManager.getInstance().addActivity(this);
    }

    @OnClick(R.id.creatshop_next)
    void goInvoiceUpload() {
        if (city1 != null && area1 != null) {
            if (!(city.equals(city1) && area.equals(area1))) {
                ToastUtil.makeText(this, "您选择的地点不属于" + city + area + name + "，请重新选择市区街道", Toast.LENGTH_SHORT).show();
                return;
            } else {
                BaseTool.goActivityNoData(this, CredentialsActivity.class);
            }
        } else {
            ToastUtil.makeText(this, "请选择坐标点", Toast.LENGTH_SHORT).show();
        }


    }

    @OnClick(R.id.create_shop_txt2)
    void goBaiduMapActivity() {
        if (city != null && name != null) {
            Bundle bundle = new Bundle();
            bundle.putString("city", city);
            bundle.putString("name", name);
            Intent intent = new Intent(CreatShopActivity.this,BaiDuMapActivity.class);
            intent.putExtras(bundle);
            startActivityForResult(intent, CreatShopActivity.GET_DATA_RESULT_CODE);
        }

    }
}

