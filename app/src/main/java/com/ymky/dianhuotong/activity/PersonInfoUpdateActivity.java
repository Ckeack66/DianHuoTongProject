package com.ymky.dianhuotong.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.joker.api.wrapper.Wrapper;
import com.squareup.picasso.Picasso;
import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.BaseTool;
import com.ymky.dianhuotong.custom.AlertDialog.DianHuoTongBottomMenuDialog;
import com.ymky.dianhuotong.custom.ToastUtil;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TResult;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonInfoUpdateActivity extends TakePhotoActivity implements DianHuoTongBottomMenuDialog.DianHuoTongBottomMenuDialogListener {
    @BindView(R.id.person_info_update_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.person_info_update_go_add_shop)
    RelativeLayout relativeLayoutGoAddShop;
    @BindView(R.id.person_info_update_select_message)
    RelativeLayout relativeLayoutSelect;
    @BindView(R.id.person_info_update_photo)
    ImageView imageView;
    private Context mContext;
    private Uri uri;
    private DianHuoTongBottomMenuDialog dianHuoTongBottomMenuDialog;
    private static final String TAG = "PersonInfoUpdateActivit";

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
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        ToastUtil.makeText(this, "选取成功" + result.getImage(), Toast.LENGTH_SHORT).show();
        Log.d(TAG, "takeSuccess: " + result.getImage().getCompressPath());
        Log.d(TAG, "takeSuccess: " + result.getImages().size());
        if (uri == null) {
            Picasso.with(this).load(result.getImage().getOriginalPath()).into(imageView);
        } else {
            Picasso.with(this).load(uri).into(imageView);
        }

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
        Permissions4M.get(this)
                // 是否强制弹出权限申请对话框，建议设置为 true，默认为 true
                // .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                // .requestUnderM(false)
                // 权限，单权限申请仅只能填入一个
                .requestPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 权限码
                .requestCodes(1001)
                // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
                // 返回的 intent 是跳转至**系统设置页面**
                // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                // 返回的 intent 是跳转至**手机管家页面**
                // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
                .request();
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
        dianHuoTongBottomMenuDialog = new DianHuoTongBottomMenuDialog(this, this);
    }

    @PermissionsGranted(1001)
    void granSuccess() {
        dianHuoTongBottomMenuDialog.show();
    }

    @Override
    public void getCamera() {
        uri = BaseTool.createImagePathUri(mContext);
        getTakePhoto().onPickFromCapture(uri);
    }

    @Override
    public void getPhotos() {
        getTakePhoto().onPickFromGallery();
    }
}
