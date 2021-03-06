package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.person.personif.PersonIF;
import com.mhky.dianhuotong.person.personif.UpdataPersonIF;
import com.mhky.dianhuotong.person.pesenter.PersonInfoPrecenter;
import com.mhky.dianhuotong.person.pesenter.UpdataPersonInfoPersenter;
import com.squareup.picasso.Picasso;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBottomMenuDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.TResult;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class PersonInfoUpdateActivity extends TakePhotoActivity implements DianHuoTongBottomMenuDialog.DianHuoTongBottomMenuDialogListener, DianHuoTongBaseDialog.BaseDialogListener, UpdataPersonIF, PersonIF, View.OnTouchListener {
    @BindView(R.id.person_info_update_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.person_info_update_go_add_shop)
    RelativeLayout relativeLayoutGoAddShop;
    @BindView(R.id.person_info_update_select_message)
    RelativeLayout relativeLayoutSelect;
    @BindView(R.id.person_info_update_photo)
    CircleImageView imageView;
    @BindView(R.id.person_info_updata_realname)
    EditText editTextRealName;
    @BindView(R.id.person_info_updata_username)
    EditText editTextUserName;
    @BindView(R.id.person_info_update_boss)
    RadioButton radioButtonBoss;
    @BindView(R.id.person_info_update_woker)
    RadioButton radioButtonWorker;
    private Context mContext;
    private Uri uri = null;
    private LoadingDialog loadingDialog;
    private DianHuoTongBottomMenuDialog dianHuoTongBottomMenuDialog;
    private UpdataPersonInfoPersenter updataPersonInfoPersenter;
    private DianHuoTongBaseDialog dianHuoTongBaseDialogAddShop;
    private static final String TAG = "PersonInfoUpdateActivit";
    private PersonInfoPrecenter personInfoPrecenter;
    private String tag1 = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info_update);
        mContext = this;
        ButterKnife.bind(this);
        inIt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (BaseApplication.getInstansApp().getPersonInfo() != null) {
            if (BaseApplication.getInstansApp().getPersonInfo().getTruename() != null) {
                editTextRealName.setText(BaseApplication.getInstansApp().getPersonInfo().getTruename().toString());
            }
            if (BaseApplication.getInstansApp().getPersonInfo().getUsername() != null) {
                editTextUserName.setText(BaseApplication.getInstansApp().getPersonInfo().getUsername().toString());
            }
            if (BaseApplication.getInstansApp().getPersonInfo().getType() != null) {
                radioButtonBoss.setClickable(true);
                radioButtonWorker.setClickable(true);
                if (BaseApplication.getInstansApp().getPersonInfo().getType().toString().equals("1")) {
                    radioButtonBoss.setChecked(true);
                } else if (BaseApplication.getInstansApp().getPersonInfo().getType().toString().equals("0")) {
                    radioButtonWorker.setChecked(true);
                }
            } else {
                radioButtonBoss.setClickable(false);
                radioButtonWorker.setClickable(false);
                radioButtonBoss.setOnTouchListener(this);
                radioButtonWorker.setOnTouchListener(this);
            }
            if (BaseApplication.getInstansApp().getPersonInfo().getImage() != null) {
                Picasso.get().load(BaseApplication.getInstansApp().getPersonInfo().getImage().toString()).into(imageView);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BaseActivityManager.getInstance().clearAllActivity();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        dianHuoTongBottomMenuDialog.dismiss();
        ToastUtil.makeText(this, "选取成功", Toast.LENGTH_SHORT).show();
        BaseTool.logPrint(TAG, "takeSuccess: " + result.getImages().size());
        BaseTool.logPrint(TAG, "takeSuccess: " + result.getImages().get(0).getOriginalPath());
        HttpParams httpParams = new HttpParams();
        httpParams.put("userName", BaseApplication.getInstansApp().getLoginRequestInfo().getUsername());
        httpParams.put("userId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        httpParams.put("type", "USER");
        httpParams.put("file", new File(result.getImages().get(0).getCompressPath()));
        loadingDialog.show();
        updataPersonInfoPersenter.uploadeImage(httpParams);
        // Picasso.get().load("file://" + result.getImages().get(0).getOriginalPath()).into(imageView);
//        if (uri != null) {
//             Picasso.get().load("file://" + result.getImages().get(0).getOriginalPath()).into(imageView);
//        } else {
//             Picasso.get().load(uri).into(imageView);
//        }

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
        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getType() != null && "1".equals(BaseApplication.getInstansApp().getPersonInfo().getType().toString())) {
            ToastUtil.makeText(this, "店长暂不支持修改店铺,请联系客服~", Toast.LENGTH_SHORT).show();
        } else if ("UNAUDITED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
            ToastUtil.makeText(this, "正在审核中~", Toast.LENGTH_SHORT).show();
        } else if ("UNSANCTIONED".equals(BaseApplication.getInstansApp().getPersonInfo().getAuditStatus().toString())) {
            ToastUtil.makeText(this, "店铺审核失败，请重新加入~", Toast.LENGTH_SHORT).show();
            dianHuoTongBaseDialogAddShop.show();
        } else {
            BaseTool.goActivityNoData(this, AddShopActivity.class);
        }

    }

    @OnClick(R.id.person_info_update_go_bind_phone)
    void goBindPhone() {
        BaseTool.goActivityNoData(this, ChangePhoneActivity.class);
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
                .requestForce(true)
                // 是否支持 5.0 权限申请，默认为 false
                .requestUnderM(true)
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
        dianHuoTongBaseDialogAddShop = new DianHuoTongBaseDialog(this, this, "温馨提示", "加入店铺查看更多精彩内容~", "稍后再说", "立刻加入", tag1);
        loadingDialog = new LoadingDialog(this);
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
                Map map = new HashMap();
                if (!TextUtils.isEmpty(editTextRealName.getText().toString().trim())) {
                    map.put("trueName", editTextRealName.getText().toString().trim());
                }
                if (!TextUtils.isEmpty(editTextUserName.getText())) {
                    map.put("userName", editTextUserName.getText().toString().trim());
                }
                if (TextUtils.isEmpty(editTextRealName.getText().toString().trim()) && TextUtils.isEmpty(editTextUserName.getText())) {
                    ToastUtil.makeText(mContext, "更新成功", Toast.LENGTH_SHORT).show();
                } else {
                    updataPersonInfoPersenter.updatePersonInfo(map, BaseApplication.getInstansApp().getLoginRequestInfo().getId(), null);
                }

            }
        });
        dianHuoTongBottomMenuDialog = new DianHuoTongBottomMenuDialog(this, this);
        updataPersonInfoPersenter = new UpdataPersonInfoPersenter(this);
        personInfoPrecenter = new PersonInfoPrecenter(this);
        personInfoPrecenter.getPersonInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
    }

    @PermissionsGranted(1001)
    void granSuccess() {
        dianHuoTongBottomMenuDialog.show();
    }

    @Override
    public void getCamera() {
        uri = BaseTool.createImagePathUri(mContext);
        TakePhoto takePhoto = getTakePhoto();
        CompressConfig compressConfig = CompressConfig.ofDefaultConfig();
        compressConfig.setMaxSize(1000 * 1024);
        takePhoto.onEnableCompress(compressConfig, true);
        takePhoto.onPickFromCapture(uri);
    }

    @Override
    public void getPhotos() {
        TakePhoto takePhoto = getTakePhoto();
        CompressConfig compressConfig = CompressConfig.ofDefaultConfig();
        compressConfig.setMaxSize(1000 * 1024);
        takePhoto.onEnableCompress(compressConfig, true);
        takePhoto.onPickFromGallery();
    }

    @Override
    public void updataUserImageSucess(int code, String result) {
        if (code == 201) {
            //ToastUtil.makeText(this, "上传成功" + code, Toast.LENGTH_SHORT).show();
            Map map = new HashMap();
            map.put("image", result);
//            HttpParams httpParams = new HttpParams();
//            httpParams.put("image", result);
//            httpParams.put("id", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
            updataPersonInfoPersenter.updatePersonInfo(map, BaseApplication.getInstansApp().getLoginRequestInfo().getId(), result);
        }

    }

    @Override
    public void updataUserImageFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "上传失败" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updataUserInfoSucess(int code, String result, String result1) {
        if (code == 200) {
            personInfoPrecenter.getPersonInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
            BaseApplication.getInstansApp().setUpdata(true);
            ToastUtil.makeText(this, "更新成功", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void updataUserinfoFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "更新失败" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getUserInfoSucess(PersonInfo personInfo) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        if (personInfo != null) {
            if (personInfo.getTruename() != null) {
                editTextRealName.setText(personInfo.getTruename().toString());
            }
            if (personInfo.getUsername() != null) {
                editTextUserName.setText(personInfo.getUsername().toString());
            }
            if (personInfo.getType() != null) {
                radioButtonBoss.setEnabled(false);
                radioButtonWorker.setEnabled(false);
                if (personInfo.getType().toString().equals("1")) {
                    radioButtonBoss.setChecked(true);
                } else if (personInfo.getType().toString().equals("0")) {
                    radioButtonWorker.setChecked(true);
                }
            } else {
                radioButtonBoss.setEnabled(false);
                radioButtonWorker.setEnabled(false);
//                radioButtonBoss.setOnTouchListener(this);
//                radioButtonWorker.setOnTouchListener(this);
            }
            if (personInfo.getImage() != null) {
                Picasso.get().load(personInfo.getImage().toString()).into(imageView);
            }
        }
    }

    @Override
    public void getUserinfoFailed(int code, String result) {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
        }
        ToastUtil.makeText(this, "没有获取到信息" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ToastUtil.makeText(this, "您还没有加入店铺呦~", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClickBaseDialogLeft(String iTag) {
        if (tag1.equals(iTag) && dianHuoTongBaseDialogAddShop.isShowing()) {
            dianHuoTongBaseDialogAddShop.dismiss();
        }
    }

    @Override
    public void onClickBaseDialogRight(String iTag) {
        if (tag1.equals(iTag) && dianHuoTongBaseDialogAddShop.isShowing()) {
            dianHuoTongBaseDialogAddShop.dismiss();
            BaseTool.goActivityNoData(this, AddShopActivity.class);
        }
    }
}
