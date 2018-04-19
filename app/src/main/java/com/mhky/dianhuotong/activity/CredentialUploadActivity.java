package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.annotation.PermissionsGranted;
import com.joker.api.Permissions4M;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.QualicationInfo;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;
import com.mhky.dianhuotong.credential.bean.QulationBaseInfo;
import com.mhky.dianhuotong.credential.credentialif.UploadCredentialIF;
import com.mhky.dianhuotong.credential.precenter.UploadCredentialPrecenter;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBottomMenuDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.squareup.picasso.Picasso;

import org.devio.takephoto.app.TakePhotoActivity;
import org.devio.takephoto.model.TResult;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CredentialUploadActivity extends TakePhotoActivity implements DianHuoTongBottomMenuDialog.DianHuoTongBottomMenuDialogListener, UploadCredentialIF {
    @BindView(R.id.credential_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.credential_image_group)
    RelativeLayout relativeLayout;
    @BindView(R.id.credential_image)
    ImageView imageViewCredentail;
    @BindView(R.id.upload_credrntial_data1)
    EditText editTextData1;
    @BindView(R.id.upload_credrntial_data2)
    EditText editTextData2;
    @BindView(R.id.upload_credrntial_card_number)
    EditText editTextCardNumber;
    @BindView(R.id.upload_credrntial_body)
    EditText editTextBody;
    @BindView(R.id.upload_credrntial_name)
    EditText editTextNume;
    @BindView(R.id.upload_credrntial_ok)
    TextView textViewOk;
    @BindView(R.id.credential_file_name)
    TextView textViewFile;
    private DianHuoTongBottomMenuDialog dianHuoTongBottomMenuDialog;
    private Uri uri = null;
    private Context mContext;
    private UploadCredentialPrecenter uploadCredentialPrecenter;
    private QualicationInfo.QualificationListBean qulationBaseInfo;
    private String imageUrl;
    private CredentialBaseTypeInfo credentialBaseTypeInfo;
    private static final String TAG = "CredentialUploadActivit";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credential_upload);
        ButterKnife.bind(this);
        mContext = this;
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("资质上传");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        qulationBaseInfo = new QualicationInfo.QualificationListBean();
        credentialBaseTypeInfo = (CredentialBaseTypeInfo) getIntent().getExtras().getSerializable("credentialtype");
        if (credentialBaseTypeInfo != null) {
            textViewFile.setText("*请上传" + credentialBaseTypeInfo.getName());
        }
        dianHuoTongBottomMenuDialog = new DianHuoTongBottomMenuDialog(this, this);
        uploadCredentialPrecenter = new UploadCredentialPrecenter(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @OnClick(R.id.credential_image_group)
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

    @PermissionsGranted(1001)
    void granSuccess() {
        dianHuoTongBottomMenuDialog.show();
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        dianHuoTongBottomMenuDialog.dismiss();
        ToastUtil.makeText(this, "选取成功", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "takeSuccess: " + result.getImages().size());
        Log.d(TAG, "takeSuccess: " + result.getImages().get(0).getOriginalPath());
        HttpParams httpParams = new HttpParams();
        httpParams.put("userName", BaseApplication.getInstansApp().getLoginRequestInfo().getUsername());
        httpParams.put("userId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        httpParams.put("type", "USER");
        httpParams.put("file", new File(result.getImages().get(0).getOriginalPath()));
        uploadCredentialPrecenter.getImageUplaodUrl(httpParams);
        //Picasso.with(this).load("file://" + result.getImages().get(0).getOriginalPath()).into(imageView);
//        if (uri != null) {
//            Picasso.with(this).load("file://" + result.getImages().get(0).getOriginalPath()).into(imageView);
//        } else {
//            Picasso.with(this).load(uri).into(imageView);
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

    @Override
    public void getCamera() {
        uri = BaseTool.createImagePathUri(mContext);
        getTakePhoto().onPickFromCapture(uri);
    }

    @Override
    public void getPhotos() {
        getTakePhoto().onPickFromGallery();
    }

    @Override
    public void updataCredentialImageSucess(int code, String result) {
        if (code == 201) {
            ToastUtil.makeText(this, "上传成功" + code, Toast.LENGTH_SHORT).show();
            imageUrl = result;
            Picasso.with(mContext).load(imageUrl).into(imageViewCredentail);
            Log.d(TAG, "updataCredentialImageSucess: ------" + result);
        }
    }

    @Override
    public void updataCredentialImageFailed(int code, String result) {

    }

    @OnClick(R.id.upload_credrntial_ok)
    void finishCreateCredential() {
        if (imageUrl == null) {
            ToastUtil.makeText(this, "请上传证件", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextData1.getText())) {
            ToastUtil.makeText(this, "请填写营业期限开始时间", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextData2.getText())) {
            ToastUtil.makeText(this, "请填写营业期限结束时间", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextCardNumber.getText())) {
            ToastUtil.makeText(this, "请填写证件编号", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextBody.getText())) {
            ToastUtil.makeText(this, "请填写经营范围", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextNume.getText())) {
            ToastUtil.makeText(this, "请填写法人姓名", Toast.LENGTH_SHORT).show();
            return;
        }
        qulationBaseInfo.setUrl(imageUrl);
        qulationBaseInfo.setStartTime(editTextData1.getText().toString());
        qulationBaseInfo.setEndTime(editTextData2.getText().toString());
        qulationBaseInfo.setNumber(editTextCardNumber.getText().toString());
        qulationBaseInfo.setScope(editTextBody.getText().toString());
        qulationBaseInfo.setCorporation(editTextNume.getText().toString());
        qulationBaseInfo.setId(credentialBaseTypeInfo.getId());
        qulationBaseInfo.setName(credentialBaseTypeInfo.getName());
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("qulation", qulationBaseInfo);
        intent.putExtras(bundle);
        setResult(1002, intent);
        finish();
    }
}
