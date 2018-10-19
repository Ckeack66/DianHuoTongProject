package com.mhky.dianhuotong.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseActivityCK;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SelectRoleForRegisterActivity extends BaseActivityCK {

    @BindView(R.id.select_register_title_bar)
    DianHuoTongBaseTitleBar selectRegisterTitleBar;
    @BindView(R.id.tv_role_select1)
    TextView tvRoleSelect1;
    @BindView(R.id.tv_role_select2)
    TextView tvRoleSelect2;
    private List<BasePresenter> presenterList = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 8){
            Bundle bundle = data.getExtras();
            Intent intent = new Intent();
            intent.putExtras(bundle);
            setResult(7,intent);
            finish();
        }
    }

    @Override
    public List<BasePresenter> getPresenter() {
        return presenterList;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_role_for_register);
        ButterKnife.bind(this);

        selectRegisterTitleBar.setLeftImage(R.drawable.icon_back);
        selectRegisterTitleBar.setCenterTextView("选择身份");
        selectRegisterTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @OnClick({R.id.tv_role_select1, R.id.tv_role_select2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_role_select1:
                Bundle b1 = new Bundle();
                b1.putString("authorities",2 + "");
                Intent intent1 = new Intent(this,RegisterActivity.class);
                intent1.putExtras(b1);
                startActivityForResult(intent1,6);
                break;
            case R.id.tv_role_select2:
                Bundle b2 = new Bundle();
                b2.putString("authorities",4 + "");
                Intent intent2 = new Intent(this,RegisterActivity.class);
                intent2.putExtras(b2);
                startActivityForResult(intent2,6);
                break;
        }
    }
}
