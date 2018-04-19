package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.addshop.bean.QualicationInfo;
import com.mhky.dianhuotong.credential.adapter.CredentialBaseAdapter;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;
import com.mhky.dianhuotong.credential.credentialif.CredentialIF;
import com.mhky.dianhuotong.credential.credentialif.CredentialsDialogIF;
import com.mhky.dianhuotong.credential.precenter.CredentialPrecenter;
import com.mhky.dianhuotong.credential.precenter.CredentialsDialogPresenter;
import com.mhky.dianhuotong.custom.AlertDialog.CredentialBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.invoice.VoiceGridviewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CredentialsActivity extends BaseActivity implements CredentialIF, CredentialsDialogIF, CredentialBaseDialog.CredentialBaseDialogListener {
    @BindView(R.id.credentials_title)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.voice_upload_gridview)
    GridView gridView;
    private Context context;
    private VoiceGridviewAdapter voiceGridviewAdapter;
    private ArrayList<QualicationInfo.QualificationListBean> qualicationInfoArrayList;
    private QualicationInfo.QualificationListBean qulationBaseInfo;
    private QualicationInfo qualicationInfo;
    private CredentialPrecenter credentialPrecenter;
    private CredentialsDialogPresenter credentialsDialogPresenter;
    private CredentialBaseDialog credentialBaseDialog;
    private List<CredentialBaseTypeInfo> list;
    private static final String TAG = "CredentialsActivity";
    private int upNumber;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002) {
            if (resultCode == 1002) {
                qulationBaseInfo = (QualicationInfo.QualificationListBean) data.getExtras().getSerializable("qulation");
                if (qulationBaseInfo != null) {
                    qualicationInfoArrayList.add(qulationBaseInfo);
                    voiceGridviewAdapter.updateData(qualicationInfoArrayList);
                    BaseTool.setListViewHeightBasedOnChildren(gridView);
                    list.remove(upNumber);
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        ButterKnife.bind(this);
        context = this;
        inIt();
    }


    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.credentials_title));
        BaseActivityManager.getInstance().addActivity(this);
        diaHuiTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        voiceGridviewAdapter = new VoiceGridviewAdapter(this, qualicationInfoArrayList);
        qualicationInfoArrayList = new ArrayList<>();
        credentialPrecenter = new CredentialPrecenter(this);
        credentialsDialogPresenter = new CredentialsDialogPresenter(this);
        qualicationInfo = (QualicationInfo) getIntent().getExtras().getSerializable("qulication");
        gridView.setAdapter(voiceGridviewAdapter);
        BaseTool.setListViewHeightBasedOnChildren(gridView);
        gridView.deferNotifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (credentialBaseDialog != null) {
                    credentialBaseDialog.show();
                }
            }
        });
        credentialsDialogPresenter.getCredentialDialogData();
    }

    @OnClick(R.id.credentials_next)
    void goAddShopFinishActivity() {
        qualicationInfo.setQualificationList(qualicationInfoArrayList);
        credentialPrecenter.createShop(JSON.toJSONString(qualicationInfo));
        //BaseTool.goActivityNoData(this, AddShop3Activity.class);
    }

    @Override
    public void createShopSucess(int code, String result) {
        if (code == 200) {
            ShopBaseInfo shopBaseInfo = JSON.parseObject(result, ShopBaseInfo.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("shop", shopBaseInfo);
            BaseTool.goActivityWithData(context, AddShop3Activity.class, bundle);
            BaseActivityManager.getInstance().finishAllActivity();
        }
        Log.d(TAG, "createShopSucess: ----" + code + result);
    }

    @Override
    public void createShopFailed(int code, String result) {

    }

    @Override
    public void getCredentialTypeSucess(int code, String result) {
        if (code == 200) {
            if (result != null && !result.equals("")) {
                list = JSON.parseArray(result, CredentialBaseTypeInfo.class);
                CredentialBaseAdapter credentialBaseAdapter = new CredentialBaseAdapter(context, list);
                credentialBaseDialog = new CredentialBaseDialog(this, this, credentialBaseAdapter, "请选择资质类型", "取消", "资质上传");
            }

        }
    }

    @Override
    public void getCredentialTypeFailed(int code, String result) {

    }

    @Override
    public void onClickCredentialBaseDialogLeft(String iTag) {
        credentialBaseDialog.dismiss();
    }

    @Override
    public void OnClickCredentialBaseDialogListviewItem(int position) {
        if (list != null) {
            upNumber = position;
            Log.d(TAG, "OnClickCredentialBaseDialogListviewItem: " + list.get(position).getName());
            Intent intent = new Intent(context, CredentialUploadActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("credentialtype", list.get(position));
            intent.putExtras(bundle);
            startActivityForResult(intent, 1002);
            credentialBaseDialog.dismiss();
        } else {
            ToastUtil.makeText(context, "没有可上传的资质信息", Toast.LENGTH_SHORT).show();
        }

    }
}
