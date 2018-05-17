package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.text.Html;
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
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.person.personif.PersonIF;
import com.mhky.dianhuotong.person.pesenter.PersonInfoPrecenter;
import com.mhky.dianhuotong.shop.bean.SaleManInfo;
import com.mhky.dianhuotong.shop.precenter.SaleManPresenter;
import com.mhky.dianhuotong.shop.shopif.SaleManIF;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddShop3Activity extends BaseActivity implements BindShopIF, SaleManIF, DianHuoTongBaseDialog.BaseDialogListener, PersonIF {
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
    private SaleManPresenter saleManPresenter;
    private SaleManInfo saleManInfo;
    private String typeId;
    private DianHuoTongBaseDialog dianHuoTongBaseDialog;
    private PersonInfoPrecenter personInfoPrecenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop3);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        personInfoPrecenter = new PersonInfoPrecenter(this);
        saleManPresenter = new SaleManPresenter(this);
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
        if (TextUtils.isEmpty(editTextPhone.getText().toString().trim())) {
            BindShopInfo bindShopInfo = new BindShopInfo();
            bindShopInfo.setId(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
            bindShopInfo.setShop_id(shopBaseInfo.getId());
            bindShopInfo.setType(typeId);
            bindShopPrecenter.binShop(JSON.toJSONString(bindShopInfo));
        } else {
            if (editTextPhone.getText().toString().trim().length() != 4) {
                ToastUtil.makeText(this, "请输入四位服务专员工号", Toast.LENGTH_SHORT).show();
                return;
            } else {
                saleManPresenter.getSaleMan(editTextPhone.getText().toString().trim());
            }
        }

    }

    @Override
    public void bindShopInfoSuccess(int code, String result) {
        if (code == 200) {
            personInfoPrecenter.getPersonInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        } else {
            ToastUtil.makeText(this, "加入请求发送成功，信息待审核", Toast.LENGTH_SHORT).show();
            BaseActivityManager.getInstance().finishAllActivity();
        }

    }

    @Override
    public void bindShopInfoFailed(int code, String result) {

    }

    @Override
    public void getSaleManSuccess(int code, String result) {
        if (code == 200) {
            saleManInfo = JSON.parseObject(result, SaleManInfo.class);
            dianHuoTongBaseDialog = new DianHuoTongBaseDialog(this, this, "温馨提示", "您确定要让服务专员:" + saleManInfo.getName() + "为您服务吗？", "取消", "确定", "add");
            dianHuoTongBaseDialog.show();
        } else {
            ToastUtil.makeText(this, "没有查询到该服务专员哦~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSaleManFailed(int code, String result) {

    }

    @Override
    public void onClickBaseDialogLeft(String iTag) {
        if (dianHuoTongBaseDialog != null) {
            dianHuoTongBaseDialog.dismiss();
        }
    }

    @Override
    public void onClickBaseDialogRight(String iTag) {
        BindShopInfo bindShopInfo = new BindShopInfo();
        bindShopInfo.setId(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        bindShopInfo.setShop_id(shopBaseInfo.getId());
        bindShopInfo.setType(typeId);
        bindShopInfo.setSalesmanCode(editTextPhone.getText().toString().trim());
        dianHuoTongBaseDialog.dismiss();
        bindShopPrecenter.binShop(JSON.toJSONString(bindShopInfo));
    }

    @Override
    public void getUserInfoSucess(PersonInfo userInfo) {
        if (userInfo != null) {
            ToastUtil.makeText(this, "加入请求发送成功，信息待审核", Toast.LENGTH_SHORT).show();
            BaseActivityManager.getInstance().finishAllActivity();
        }
    }

    @Override
    public void getUserinfoFailed(int code, String result) {
        BaseActivityManager.getInstance().finishAllActivity();
    }
}
