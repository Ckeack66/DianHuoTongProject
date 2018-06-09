package com.mhky.dianhuotong.shop.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.joker.annotation.PermissionsDenied;
import com.joker.annotation.PermissionsGranted;
import com.joker.annotation.PermissionsRequestSync;
import com.joker.api.Permissions4M;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.fragment.GoodsInfoDetialsFragment;
import com.mhky.dianhuotong.shop.fragment.GoodsInfoInstructionFragment;
import com.pgyersdk.crash.PgyCrashManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
@PermissionsRequestSync(permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.READ_PHONE_STATE}, value = {101, 102,103})
public class GoodsInfoActivity extends BaseActivity {
    @BindView(R.id.goods_info_left)
    TextView textViewLeft;
    @BindView(R.id.goods_info_right)
    TextView textViewRight;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private GoodsInfoDetialsFragment goodsInfoDetialsFragment;
    private GoodsInfoInstructionFragment goodsInfoInstructionFragment;
    private String goodsDes;
    private String goodsIns;
    private boolean a;
    private boolean b;
    private boolean c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        Permissions4M.get(this).requestSync();

    }

    private void init() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            goodsDes = bundle.getString("des");
            goodsIns = bundle.getString("ins");
        }
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        goodsInfoDetialsFragment = GoodsInfoDetialsFragment.newInstance(goodsDes, "");
        goodsInfoInstructionFragment = GoodsInfoInstructionFragment.newInstance(goodsIns, "");
        fragmentTransaction.add(R.id.goods_info_fragment, goodsInfoInstructionFragment);
        fragmentTransaction.add(R.id.goods_info_fragment, goodsInfoDetialsFragment);
        fragmentTransaction.show(goodsInfoDetialsFragment).commit();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Permissions4M.onRequestPermissionsResult(this, requestCode, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
    @OnClick(R.id.goodsinfo_back)
    void back() {
        finish();
    }

    @OnClick(R.id.goods_info_left)
    void chooseLeft() {
        textViewLeft.setEnabled(false);
        textViewRight.setEnabled(true);
        fragmentManager.beginTransaction().hide(goodsInfoInstructionFragment).show(goodsInfoDetialsFragment).commit();
    }

    @OnClick(R.id.goods_info_right)
    void chooseRight() {
        textViewRight.setEnabled(false);
        textViewLeft.setEnabled(true);
        fragmentManager.beginTransaction().hide(goodsInfoDetialsFragment).show(goodsInfoInstructionFragment).commit();
    }

    @PermissionsGranted({101, 102,103})
    void getLocationGrantsucess(int code) {
        switch (code) {
            case 101:
                a = true;
                if (b&&c) {
                    try {
                        init();
                    } catch (Exception e) {
                        PgyCrashManager.reportCaughtException(this, e);
                    }
                }
                break;
            case 102:
                b = true;
                if (a&&c) {
                    try {
                        init();
                    } catch (Exception e) {
                        PgyCrashManager.reportCaughtException(this, e);
                    }
                }
                break;
            case 103:
                c = true;
                if (a&&b) {
                    try {
                        init();
                    } catch (Exception e) {
                        PgyCrashManager.reportCaughtException(this, e);
                    }
                }
                break;
        }
    }

    @PermissionsDenied({101, 102,103})
    void getLocationGrantFaile(int code) {
        switch (code) {
            case 101:
                ToastUtil.makeText(this, "请打开存储权限", Toast.LENGTH_SHORT).show();
                break;
            case 102:
                ToastUtil.makeText(this, "请打开存储权限", Toast.LENGTH_SHORT).show();
                break;
            case 103:
                ToastUtil.makeText(this, "请打开存储权限", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
