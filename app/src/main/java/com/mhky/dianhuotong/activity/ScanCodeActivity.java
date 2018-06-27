package com.mhky.dianhuotong.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.activity.ShopActivity;
import com.pgyersdk.crash.PgyCrashManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.ProcessDataTask;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

@PermissionsRequestSync(permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, value = {101, 102, 103})
public class ScanCodeActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.scan_code_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.scan_code_body)
    ZXingView xingView;
    @BindView(R.id.scan_code_flush)
    LinearLayout linearLayoutFlush;
    @BindView(R.id.scan_code_flush_statetext)
    TextView textViewFlash;
    private Context mContext;
    private boolean isUseCamera = false;
    private boolean isReadExtorge = false;
    private boolean isWriteExtorge = false;

    private boolean isShowFlush = false;
    private static final String TAG = "ScanCodeActivity";
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_code);
        mContext = this;
        ButterKnife.bind(this);
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (isUseCamera) {
                xingView.startCamera();
                xingView.startSpotAndShowRect();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    @Override
    protected void onStop() {
        try {
            if (isUseCamera) {
                xingView.stopCamera();
                xingView.stopSpotAndHiddenRect();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        try {
            xingView.onDestroy();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
        super.onDestroy();

    }

    @OnClick(R.id.scan_code_flush)
    void oparateFlash() {
        try {
            if (!isShowFlush) {
                isShowFlush = true;
                xingView.openFlashlight();
                textViewFlash.setText("点击关闭闪光灯");
            } else {
                isShowFlush = false;
                xingView.closeFlashlight();
                textViewFlash.setText("点击打开闪光灯");
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void inIt() {
        Permissions4M.get(this).requestSync();
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("扫码搜索");
        //dianHuoTongBaseTitleBar.setRightText("相册选择");
        dianHuoTongBaseTitleBar.setRightTextOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ToastUtil.makeText(mContext, "正在开发中..", Toast.LENGTH_SHORT).show();
            }
        });
        xingView.setDelegate(this);
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
            linearLayoutFlush.setVisibility(View.GONE);
        }

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @PermissionsGranted({101, 102, 103})
    void getGrantSucess(int code) {
        switch (code) {
            case 101:
                isWriteExtorge = true;
                break;
            case 102:
                isReadExtorge = true;
                break;
            case 103:
                try {
                    BaseTool.logPrint(TAG, "getGrantSucess: " + "授权成功！");
                    isUseCamera = true;
                    xingView.startCamera();
                    xingView.showScanRect();
                    xingView.startSpot();
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(this, e);
                }

//        mQRCodeView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT);

                break;
        }
    }

    @PermissionsDenied({101, 102, 103})
    void getGrantDenied(int code) {
        switch (code) {
            case 101:
                ToastUtil.makeText(this, "请打开文件写权限", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                ToastUtil.makeText(this, "请打开文件读权限", Toast.LENGTH_SHORT).show();
            case 103:
                ToastUtil.makeText(this, "请打开使用相机权限", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

        if (result.length() < 6) {
            xingView.startSpot();//延迟1.5秒进行扫描
        } else if (result.length() > 6 && "MHKYDP".equals(result.substring(0, 6))) {
            //分享出去的店铺
            BaseTool.logPrint(TAG, "onScanQRCodeSuccess: --------" + result.substring(6, result.length()));
            vibrate();
            Bundle bundle = new Bundle();
            bundle.putString("shopid", result.substring(6, result.length()));
            BaseTool.goActivityWithData(this, ShopActivity.class, bundle);
            finish();
        } else if (result.length() > 6 && "MHKYSP".equals(result.substring(0, 6))) {
            //分享出去的商品
            BaseTool.logPrint(TAG, "onScanQRCodeSuccess: --------" + result.substring(6, result.length()));
        } else {
            //搜索商品
            ToastUtil.makeText(this, "扫描结果:" + result, Toast.LENGTH_SHORT).show();
            BaseTool.logPrint(TAG, "onScanQRCodeSuccess: --------" + result);
        }


    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        ToastUtil.makeText(this, "扫描出错！" , Toast.LENGTH_SHORT).show();
    }

    //震动
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }
}
