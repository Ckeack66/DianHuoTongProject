package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseApplication;
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
import com.mhky.dianhuotong.login.LoginPrecenter;
import com.mhky.dianhuotong.person.pesenter.PersonInfoPrecenter;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 上传店铺的资质，  并创建店铺；
 */

public class CredentialsActivity extends BaseActivity implements CredentialIF, CredentialsDialogIF, CredentialBaseDialog.CredentialBaseDialogListener {
    @BindView(R.id.credentials_title)
    DianHuoTongBaseTitleBar diaHuiTongBaseTitleBar;
    @BindView(R.id.voice_upload_gridview)
    GridView gridView;
    private Context context;
    private VoiceGridviewAdapter voiceGridviewAdapter;
    private ArrayList<QualicationInfo.QualificationListBean> qualicationInfoArrayList;              //资质List集合
    private QualicationInfo.QualificationListBean qulationBaseInfo;
    private QualicationInfo qualicationInfo;                                                        //上个Activity传过来的Qualication,在此页面赋值资质List
    private CredentialPrecenter credentialPrecenter;
    private CredentialsDialogPresenter credentialsDialogPresenter;
    private CredentialBaseDialog credentialBaseDialog;
    private List<CredentialBaseTypeInfo> list;                                                      //资质类型List集合
    private static final String TAG = "CredentialsActivity";
    private int upNumber;

    private PersonInfoPrecenter personInfoPrecenter;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (requestCode == 1002) {

                if (resultCode == 1002) {
                    qulationBaseInfo = (QualicationInfo.QualificationListBean) data.getExtras().getSerializable("qulation");
                    if (qulationBaseInfo != null) {
                        qualicationInfoArrayList.add(qulationBaseInfo);
                        voiceGridviewAdapter.updateData(qualicationInfoArrayList);
                        BaseTool.setListViewHeightBasedOnChildren(gridView);
                        voiceGridviewAdapter.notifyDataSetChanged();

                        if (qualicationInfoArrayList != null) {
                            for (int i = 0; i < qualicationInfoArrayList.size(); i++) {
                                if (list != null) {
                                    for (int k = 0; k < list.size(); k++) {
                                        if (list.get(k).getName().equals(qualicationInfoArrayList.get(i).getName())) {
                                            list.remove(k);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credentials);
        ButterKnife.bind(this);
        context = this;
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

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
        qualicationInfoArrayList = new ArrayList<>();
        voiceGridviewAdapter = new VoiceGridviewAdapter(this, qualicationInfoArrayList);
        credentialPrecenter = new CredentialPrecenter(this);
        credentialsDialogPresenter = new CredentialsDialogPresenter(this);
        qualicationInfo = (QualicationInfo) getIntent().getExtras().getSerializable("qulication");
        gridView.setAdapter(voiceGridviewAdapter);
        BaseTool.setListViewHeightBasedOnChildren(gridView);
        voiceGridviewAdapter.notifyDataSetChanged();
        gridView.deferNotifyDataSetChanged();
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaseTool.logPrint("ck",qualicationInfoArrayList.size() + "----" + position );
                if (qualicationInfoArrayList.size() > 0) {
                    if (position == qualicationInfoArrayList.size()) {
                        if (credentialBaseDialog != null) {
                            credentialBaseDialog.show();
                        }
                    } else {
                        Intent intent = new Intent(CredentialsActivity.this, CredentialUploadActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("credentialinfo", qualicationInfoArrayList.get(position));
                        bundle.putString("state", "-1");
                        intent.putExtras(bundle);
                        startActivityForResult(intent, 1002);

                        qualicationInfoArrayList.remove(position);
                    }
                } else {
                    if (credentialBaseDialog != null) {
                        credentialBaseDialog.show();
                    }
                }

            }
        });
        credentialsDialogPresenter.getCredentialDialogData();
    }

    @OnClick(R.id.credentials_next)
    void goAddShopFinishActivity() {
        try {
            //不再对营业执照进行限制
//            if (list != null) {
//                for (int a = 0; a < list.size(); a++) {
//                    if ("营业执照".equals(list.get(a).getName())) {
//                        ToastUtil.makeText(context, "请上传营业执照", Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//            }
            qualicationInfo.setQualificationList(qualicationInfoArrayList);
            credentialPrecenter.createShop(JSON.toJSONString(qualicationInfo));
//            BaseTool.logPrint(TAG, JSON.toJSONString(qualicationInfo));
            //BaseTool.goActivityNoData(this, AddShop3Activity.class);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    /**
     * 创建店铺成功
     * @param code
     * @param result
     */
    @Override
    public void createShopSucess(int code, String result) {
        try {
            if (code == 200) {
                ShopBaseInfo shopBaseInfo = JSON.parseObject(result, ShopBaseInfo.class);
                ToastUtil.makeText(context, "创建店铺成功" + code, Toast.LENGTH_SHORT).show();
                if (BaseApplication.getInstansApp().getLoginRequestInfo() != null && BaseApplication.getInstansApp().getLoginRequestInfo().getId() != null) {
                    personInfoPrecenter.getPersonInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId());
                }
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shopBaseInfo);
                bundle.putInt("state", 1);
//                BaseTool.goActivityWithData(context, AddShop3Activity.class, bundle);
                BaseActivityManager.getInstance().finishAllActivity();
            } else {
                ToastUtil.makeText(context, "创建店铺失败" + code, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

        BaseTool.logPrint(TAG, "createShopSucess: ----" + code + result);
    }

    @Override
    public void createShopFailed(int code, String result) {
        ToastUtil.makeText(context, "创建店铺失败" + code, Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取资质类型成功
     *
     * @param code
     * @param result
     */
    @Override
    public void getCredentialTypeSucess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    list = JSON.parseArray(result, CredentialBaseTypeInfo.class);
                    CredentialBaseAdapter credentialBaseAdapter = new CredentialBaseAdapter(context, list);
                    credentialBaseDialog = new CredentialBaseDialog(this, this, credentialBaseAdapter, "请选择资质类型", "取消", "资质上传");
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
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
        try {
            if (list != null) {
                upNumber = position;
                BaseTool.logPrint(TAG, "OnClickCredentialBaseDialogListviewItem: " + list.get(position).getName());
                Intent intent = new Intent(context, CredentialUploadActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("credentialtype", list.get(position));
                bundle.putString("state", "");
                intent.putExtras(bundle);
                startActivityForResult(intent, 1002);
                credentialBaseDialog.dismiss();
            } else {
                ToastUtil.makeText(context, "没有可上传的资质信息", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }


    }
}
