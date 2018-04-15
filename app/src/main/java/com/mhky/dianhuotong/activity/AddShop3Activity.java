package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.addshopif.BindShopIF;
import com.mhky.dianhuotong.addshop.bean.BindShopInfo;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;
import com.mhky.dianhuotong.addshop.precenter.BindShopPrecenter;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShop3Activity extends BaseActivity implements BindShopIF {
    @BindView(R.id.addshop3_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.shop3_shopname)
    TextView textViewShopName;
    @BindView(R.id.shop3_username)
    EditText editTextUsername;
    @BindView(R.id.shop3_myweiter_phone)
    EditText editTextPhone;
    @BindView(R.id.add_shop_boss)
    RadioButton radioButtonBoss;
    @BindView(R.id.add_shop_worker)
    RadioButton radioButtonWorker;
    @BindView(R.id.shop3_group)
    RadioGroup radioGroup;
    private ShopBaseInfo shopBaseInfo;
    private BindShopPrecenter bindShopPrecenter;
    private String typeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop3);
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
        shopBaseInfo = (ShopBaseInfo) getIntent().getExtras().getSerializable("shop");
        if (shopBaseInfo != null) {
            textViewShopName.setText("正在加入：" + shopBaseInfo.getShopname());
        }
        bindShopPrecenter = new BindShopPrecenter(this);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonBoss.getId()) {
                    typeId = "1";
                } else if (checkedId == radioButtonWorker.getId()) {
                    typeId = "0";
                }
            }
        });
        BaseActivityManager.getInstance().addActivity(this);
    }

    @OnClick(R.id.shop3_add)
    void addShop() {
        if (TextUtils.isEmpty(editTextUsername.getText().toString().trim())) {
            ToastUtil.makeText(this, "请输入姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!radioButtonBoss.isChecked() && !radioButtonWorker.isChecked()) {
            ToastUtil.makeText(this, "请选择职位", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!(shopBaseInfo != null && shopBaseInfo.getId() != null)) {
            ToastUtil.makeText(this, "请求信息有误", Toast.LENGTH_SHORT).show();
            return;
        }
        BindShopInfo bindShopInfo = new BindShopInfo();
        bindShopInfo.setId(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        bindShopInfo.setShop_id(shopBaseInfo.getId());
        bindShopInfo.setType(typeId);
        bindShopPrecenter.binShop(JSON.toJSONString(bindShopInfo));
    }

    @Override
    public void bindShopInfoSuccess(int code, String result) {
        if (code == 200) {
            ToastUtil.makeText(this, "加入请求发送成功，信息待审核", Toast.LENGTH_SHORT).show();
            BaseActivityManager.getInstance().finishAllActivity();
        }
    }

    @Override
    public void bindShopInfoFailed(int code, String result) {

    }
}
