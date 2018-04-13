package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.person.custom.AvatarScanHelperDialog;
import com.mhky.dianhuotong.person.personif.PersonIF;
import com.mhky.dianhuotong.person.pesenter.PersonInfoPrecenter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonInfoActivity extends BaseActivity implements PersonIF {
    @BindView(R.id.personinfo_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.person_info_image)
    CircleImageView imageView;
    @BindView(R.id.person_info_realname)
    TextView textViewrealname;
    @BindView(R.id.person_info_username)
    TextView textViewUsername;
    @BindView(R.id.person_info_shopname)
    TextView textViewShopName;
    @BindView(R.id.person_info_work)
    TextView textViewWork;
    @BindView(R.id.person_info_phone)
    TextView textViewPhone;
    private PersonInfoPrecenter personInfoPrecenter;
    private AvatarScanHelperDialog avatarScanHelperDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.person_title));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        personInfoPrecenter = new PersonInfoPrecenter(this);
        if (BaseApplication.getInstansApp().getLoginRequestInfo() != null && BaseApplication.getInstansApp().getLoginRequestInfo().getId() != null) {
            personInfoPrecenter.getPersonInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        }

    }

    @OnClick(R.id.person_info_image)
    void showBigImage() {
        if (BaseApplication.getInstansApp().getLoginRequestInfo().getImage() != null) {
            avatarScanHelperDialog = new AvatarScanHelperDialog(this, BaseApplication.getInstansApp().getLoginRequestInfo().getImage().toString());
            avatarScanHelperDialog.show();
        }
    }

    @Override
    public void getUserInfoSucess(PersonInfo personInfo) {
        if (personInfo != null) {
            if (personInfo.getImage() != null) {
                Picasso.with(this).load(personInfo.getImage().toString()).into(imageView);

            }
            if (personInfo.getTruename() != null) {
                textViewrealname.setText(personInfo.getTruename().toString());
            }
            if (personInfo.getUsername() != null) {
                textViewUsername.setText(personInfo.getUsername().toString());
            }
            if (personInfo.getShopName() != null) {
                textViewShopName.setText(personInfo.getShopName().toString());
            }
            //0店员1店长
            if (personInfo.getMobile() != null) {
                textViewPhone.setText(personInfo.getMobile());
            }
            if (personInfo.getType() != null) {
                if (personInfo.getType().toString().equals("0")) {
                    textViewWork.setText("店员");
                } else if (personInfo.getType().toString().equals("1")) {
                    textViewWork.setText("店长");
                }
            }
        }
    }

    @Override
    public void getUserinfoFailed(int code, String result) {

    }
}
