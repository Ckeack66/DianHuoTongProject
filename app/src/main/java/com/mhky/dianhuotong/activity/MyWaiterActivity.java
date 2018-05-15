package com.mhky.dianhuotong.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.bean.SaleManInfo;
import com.mhky.dianhuotong.shop.precenter.SaleManPresenter;
import com.mhky.dianhuotong.shop.shopif.SaleManIF;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyWaiterActivity extends BaseActivity implements SaleManIF {
    @BindView(R.id.mywaiter_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.sale_name)
    TextView textViewSaleName;
    @BindView(R.id.sale_phone)
    TextView textViewSalePhone;
    @BindView(R.id.sale_area)
    TextView textViewSaleArea;
    @BindView(R.id.sale_manager)
    TextView textViewSaleManager;
    @BindView(R.id.sale_manager_phone)
    TextView textViewSaleManagerPhone;
    private SaleManPresenter saleManPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_waiter);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        saleManPresenter = new SaleManPresenter(this);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("服务专员");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getSalesmanCode() != null && !"".equals(BaseApplication.getInstansApp().getPersonInfo().getSalesmanCode().toString())) {
            saleManPresenter.getSaleMan(BaseApplication.getInstansApp().getPersonInfo().getSalesmanCode().toString());
        }
    }

    @Override
    public void getSaleManSuccess(int code, String result) {
        if (code == 200) {
            SaleManInfo saleManInfo = JSON.parseObject(result, SaleManInfo.class);
            if (saleManInfo != null) {
                textViewSaleName.setText(saleManInfo.getName());
                textViewSalePhone.setText(saleManInfo.getPhone());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(saleManInfo.getAddress().getProvince()).append(saleManInfo.getAddress().getCity()).append(saleManInfo.getAddress().getDistrict()).append(saleManInfo.getAddress().getTown()).append(saleManInfo.getAddress().getRoad());
                textViewSaleArea.setText(stringBuilder.toString());
                if ("EMPLOYEES".equals(saleManInfo.getType())) {
                    if (saleManInfo.getParent() != null) {
                        textViewSaleManager.setText(saleManInfo.getParent().getName());
                        textViewSaleManagerPhone.setText(saleManInfo.getParent().getPhone());
                    }

                }
            }

        }

    }

    @Override
    public void getSaleManFailed(int code, String result) {

    }
}
