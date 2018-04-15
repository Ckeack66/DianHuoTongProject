package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.liqi.utils.encoding.MD5Util;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.person.AlterPWDPersenter;
import com.mhky.dianhuotong.person.AlterPwdIF;
import com.mhky.dianhuotong.person.AlterPwdInfo;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlterPasswordActivity extends BaseActivity implements AlterPwdIF {
    @BindView(R.id.alterpwd_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;

    @BindView(R.id.alterpwd_edite1)
    EditText editTextOldPwd;
    @BindView(R.id.alterpwd_edite2)
    EditText editTextNewPwd;
    @BindView(R.id.alterpwd_edite3)
    EditText editTextNewPwd1;
    private AlterPWDPersenter alterPWDPersenter;
    private static final String TAG = "AlterPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.alterpwd_title));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        alterPWDPersenter = new AlterPWDPersenter(this);
    }

    @OnClick(R.id.alterpwd_ok)
    void sureAlterPwd() {
        String oldPwd = MD5Util.md5(editTextOldPwd.getText().toString().trim());
        if (TextUtils.isEmpty(editTextOldPwd.getText().toString().trim())) {
            ToastUtil.makeText(this, "请输入旧密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (!oldPwd.equals(BaseApplication.getInstansApp().getMypswsds())) {
            ToastUtil.makeText(this, "旧密码未验证通过", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextNewPwd.getText().toString().trim())) {
            ToastUtil.makeText(this, "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextNewPwd1.getText().toString().trim())) {
            ToastUtil.makeText(this, "请输入确认密码", Toast.LENGTH_SHORT).show();
            return;
        } else if (!editTextNewPwd.getText().toString().trim().equals(editTextNewPwd1.getText().toString().trim())) {
            ToastUtil.makeText(this, "新密码不一致", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "sureAlterPwd: ---修改密码验证通过");
            AlterPwdInfo alterPwdInfo = new AlterPwdInfo();
            alterPwdInfo.setPassword(MD5Util.md5(editTextNewPwd1.getText().toString().trim()));
            alterPWDPersenter.alterPwd(JSON.toJSONString(alterPwdInfo));

        }
    }

    @Override
    public void alterPwdSucess(int code, String result) {
        Log.d(TAG, "alterPwdSucess: ---" + code);
        Log.d(TAG, "alterPwdSucess: ---" + result);
        if (code == 200) {
            BaseApplication.getInstansApp().setMypswsds(MD5Util.md5(editTextNewPwd1.getText().toString().trim()));
            ToastUtil.makeText(this, "修改成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public void alterPwdFailed(int code, String result) {
        Log.d(TAG, "alterPwdFailed: " + code);
        Log.d(TAG, "alterPwdFailed: " + result);
    }
}
