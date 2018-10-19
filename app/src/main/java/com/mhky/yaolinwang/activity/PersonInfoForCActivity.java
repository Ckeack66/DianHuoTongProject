package com.mhky.yaolinwang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseActivityCK;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.person.custom.AvatarScanHelperDialog;
import com.mhky.dianhuotong.person.pesenter.PersonInfoPrecenter;
import com.mhky.yaolinwang.bean.CustomerAddressBean;
import com.mhky.yaolinwang.bean.CustomerInfoBean;
import com.mhky.yaolinwang.presenter.GetCustomerDefaultAddressPresenter;
import com.mhky.yaolinwang.view.GetCustomerInfoView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonInfoForCActivity extends BaseActivityCK implements GetCustomerInfoView {

    @BindView(R.id.personinfo_for_c_title)
    DianHuoTongBaseTitleBar personinfoForCTitle;
    @BindView(R.id.person_info_image)
    CircleImageView personInfoImage;
    @BindView(R.id.person_info_realname)
    TextView personInfoRealname;
    @BindView(R.id.person_info_username)
    TextView personInfoUsername;
    @BindView(R.id.person_info_address)
    TextView personInfoAddress;

    private List<BasePresenter> list = new ArrayList<>();
    private PersonInfoPrecenter personInfoPrecenter;
    private CustomerInfoBean customerInfoBean;
    private GetCustomerDefaultAddressPresenter getCustomerDefaultAddressPresenter;
    private AvatarScanHelperDialog avatarScanHelperDialog;

    @Override
    public List<BasePresenter> getPresenter() {
        return list;
    }

    @Override
    public void initPresenter() {
        getCustomerDefaultAddressPresenter = new GetCustomerDefaultAddressPresenter();
        list.add(getCustomerDefaultAddressPresenter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_for_c);
        ButterKnife.bind(this);

        personinfoForCTitle.setLeftImage(R.drawable.icon_back);
        personinfoForCTitle.setCenterTextView("个人信息");
        personinfoForCTitle.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (BaseApplication.getInstansApp().getCustomerInfo() != null) {
            initView();
        } else {
            personInfoPrecenter = new PersonInfoPrecenter(this);
            if (!BaseTool.isEmpty(BaseApplication.getInstansApp().getLoginRequestInfo().getId())) {
                personInfoPrecenter.getCustomerInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
            } else {
                ToastUtil.makeText(this, "请重新登陆~", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initView() {
        customerInfoBean = BaseApplication.getInstansApp().getCustomerInfo();
        if (!BaseTool.isEmpty(customerInfoBean.getImage())) {
            Picasso.get().load(customerInfoBean.getImage()).into(personInfoImage);
        }
        if (!BaseTool.isEmpty(customerInfoBean.getMobile())) {
            personInfoUsername.setText(customerInfoBean.getMobile());
        } else {
            personInfoUsername.setText("未设置");
        }
        if (!BaseTool.isEmpty(customerInfoBean.getUsername()) && !customerInfoBean.getUsername().trim().equals("string")) {
            personInfoRealname.setText(customerInfoBean.getUsername());
        } else {
            personInfoRealname.setText("未设置");
        }
        HttpParams httpParams = new HttpParams();
        httpParams.put("customerId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
//        httpParams.put("customerId",86);
        getCustomerDefaultAddressPresenter.getCustomerDefaultAddress(httpParams);
    }

    @OnClick(R.id.person_info_image)
    public void onViewClicked() {
        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getImage() != null) {
            avatarScanHelperDialog = new AvatarScanHelperDialog(this, BaseApplication.getInstansApp().getPersonInfo().getImage().toString());
            avatarScanHelperDialog.show();
        }
    }

    /**
     * 获取个人信息
     *
     * @param data
     */
    @Override
    public void getCustomerInfoSuccess(String data) {
        initView();
    }

    @Override
    public void getCustomerInfoFailed(String data) {
        ToastUtil.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取默认地址
     *
     * @param data
     */
    @Override
    public void getDefaultAdressSuccess(String data) {
        CustomerAddressBean customerAddressBean = JSON.parseObject(data, CustomerAddressBean.class);
        if (customerAddressBean != null) {
            CustomerAddressBean.AddressBean addressBean = customerAddressBean.getAddress();
            personInfoAddress.setText(addressBean.getProvince() + addressBean.getCity() + addressBean.getDistrict() +
                    addressBean.getTown() + addressBean.getRoad());
        }
    }

    @Override
    public void getDefaultAdressFailed(String data) {
        ToastUtil.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

}
