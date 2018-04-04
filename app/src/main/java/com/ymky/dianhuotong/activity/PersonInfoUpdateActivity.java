package com.ymky.dianhuotong.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.BaseTool;
import com.ymky.dianhuotong.custom.ToastUtil;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInfoUpdateActivity extends TakePhotoActivity {
    @BindView(R.id.person_info_update_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.person_info_update_go_add_shop)
    RelativeLayout relativeLayoutGoAddShop;
    @BindView(R.id.person_info_update_select_message)
    RelativeLayout relativeLayoutSelect;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_update);
        mContext = this;
        ButterKnife.bind(this);
        inIt();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ToastUtil.makeText(this, "选取成功" + result.getImage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void takeFail(TResult result, String msg) {
        super.takeFail(result, msg);
        ToastUtil.makeText(this, "选取失败" + msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void takeCancel() {
        super.takeCancel();
        ToastUtil.makeText(this, "选取取消", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.person_info_update_go_add_shop)
    void goAddShop() {
        BaseTool.goActivityNoData(this, AddShopActivity.class);
    }

    @OnClick(R.id.person_info_update_go_bind_phone)
    void goBindPhone() {
        BaseTool.goActivityNoData(this, BindPhoneActivity.class);
    }

    @OnClick(R.id.person_info_update_go_alter_pwd)
    void goAlterPwd() {
        BaseTool.goActivityNoData(this, AlterPasswordActivity.class);
    }

    @OnClick(R.id.person_info_update_select_message)
    void goSelectPhoto() {
        //getTakePhoto().onPickFromGallery();
        getTakePhoto().onPickFromCapture(BaseTool.createImagePathUri(this));
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.person_update_title));
        dianHuoTongBaseTitleBar.setRightText(getString(R.string.person_update_save));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setRightTextOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtil.makeText(mContext, "已保存", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
