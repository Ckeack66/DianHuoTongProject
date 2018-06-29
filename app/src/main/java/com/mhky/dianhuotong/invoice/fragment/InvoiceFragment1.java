package com.mhky.dianhuotong.invoice.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.activity.CredentialUploadActivity;
import com.mhky.dianhuotong.addshop.bean.QualicationInfo;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.credential.adapter.CredentialBaseAdapter;
import com.mhky.dianhuotong.credential.bean.CredentialBaseTypeInfo;
import com.mhky.dianhuotong.credential.credentialif.CredentialsDialogIF;
import com.mhky.dianhuotong.credential.precenter.CredentialsDialogPresenter;
import com.mhky.dianhuotong.custom.AlertDialog.CredentialBaseDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.CredentialRecycleView1Adapter;
import com.mhky.dianhuotong.shop.adapter.CredentialRecycleViewAdapter;
import com.mhky.dianhuotong.shop.bean.CredentialUpdateInfo;
import com.mhky.dianhuotong.shop.bean.ShopCredentialBaseInfo;
import com.mhky.dianhuotong.shop.bean.ShopCredentialInfo;
import com.mhky.dianhuotong.shop.precenter.BillPresenter;
import com.mhky.dianhuotong.shop.precenter.ShopCredentialPresenter;
import com.mhky.dianhuotong.shop.shopif.BillIF;
import com.mhky.dianhuotong.shop.shopif.ShopCredentialIF;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvoiceFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvoiceFragment1 extends Fragment implements ShopCredentialIF, CredentialsDialogIF, CredentialBaseDialog.CredentialBaseDialogListener,BillIF {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    @BindView(R.id.voice_gridview)
    RecyclerView gridView;
    private ShopCredentialPresenter shopCredentialPresenter;
    private List<ShopCredentialBaseInfo> shopCredentialBaseInfoList;
    //    private List<ShopCredentialInfo> shopCredentialInfoList;
    private CredentialRecycleView1Adapter credentialRecycleView1Adapter;
    private boolean isFirst = false;
    private ShopCredentialBaseInfo shopCredentialBaseInfo;
    private CredentialsDialogPresenter credentialsDialogPresenter;
    private CredentialBaseDialog credentialBaseDialog;
    private List<CredentialBaseTypeInfo> credentialBaseTypeInfoList;
    private int upNumber;
    private BillPresenter billPresenter;
    private static final String TAG = "InvoiceFragment1";
    private Unbinder unbinder;

    public InvoiceFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvoiceFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static InvoiceFragment1 newInstance(String param1, String param2) {
        InvoiceFragment1 fragment = new InvoiceFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_invoice_fragment1, container, false);
        unbinder = ButterKnife.bind(this, view);
//        shopCredentialInfoList = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        gridView.setLayoutManager(gridLayoutManager);
        shopCredentialPresenter = new ShopCredentialPresenter(this);
        credentialsDialogPresenter = new CredentialsDialogPresenter(this);
        shopCredentialBaseInfo = new ShopCredentialBaseInfo();
        shopCredentialBaseInfo.setItemType(2);
        credentialsDialogPresenter.getCredentialDialogData();
        billPresenter=new BillPresenter(this);
        billPresenter.getBill();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1002) {
            if (resultCode == 1002) {
                QualicationInfo.QualificationListBean qulationBaseInfo = (QualicationInfo.QualificationListBean) data.getExtras().getSerializable("qulation");
                String state = data.getExtras().getString("state");
                shopCredentialPresenter.getCredential();
//                if (state.equals("0")) {
//                    shopCredentialPresenter.getCredential();
//                } else if (state.equals("1")) {
//                    //添加新的资质
//                    if (qulationBaseInfo != null) {
//                        shopCredentialPresenter.getCredential();//重新获取当前资质列表
//                        credentialBaseTypeInfoList.remove(upNumber);
//                    }
//                }
//
            }
        }

    }

    @Override
    public void getShopCredentialSucess(int code, String result) {
        try {
            if (code == 200) {
                if (shopCredentialBaseInfoList != null) {
                    shopCredentialBaseInfoList.clear();
                }
                shopCredentialBaseInfoList = JSON.parseArray(result, ShopCredentialBaseInfo.class);
                upDateShopCredentialList();
                if (!isFirst) {
                    shopCredentialBaseInfoList.add(shopCredentialBaseInfo);
                    credentialRecycleView1Adapter = new CredentialRecycleView1Adapter(shopCredentialBaseInfoList, getActivity());
                    credentialRecycleView1Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            if (shopCredentialBaseInfoList.get(position).getItemType() == 2) {
                                if (credentialBaseDialog != null) {
                                    credentialBaseDialog.show();
                                }
                            } else if (shopCredentialBaseInfoList.get(position).getItemType() == 1) {
                                if ("营业执照".equals(shopCredentialBaseInfoList.get(position).getName())){
                                    ToastUtil.makeText(getActivity(), "暂不支持修改营业执照~", Toast.LENGTH_SHORT).show();
                                }else {
                                    Intent intent = new Intent(getActivity(), CredentialUploadActivity.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("credentialinfo", shopCredentialBaseInfoList.get(position));
                                    bundle.putString("state", "0");
                                    intent.putExtras(bundle);
                                    startActivityForResult(intent, 1002);
                                }

                            }
                        }
                    });
                    gridView.setAdapter(credentialRecycleView1Adapter);
                    isFirst = true;
                } else {
                    shopCredentialBaseInfoList.add(shopCredentialBaseInfo);
                    credentialRecycleView1Adapter.setNewData(shopCredentialBaseInfoList);
                }
                CredentialBaseAdapter credentialBaseAdapter = new CredentialBaseAdapter(getActivity(), credentialBaseTypeInfoList);
                credentialBaseDialog = new CredentialBaseDialog(getActivity(), this, credentialBaseAdapter, "请选择资质类型", "取消", "资质上传");

            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(getActivity(),e);
        }

    }

    @Override
    public void getShopCredentialFaild(int code, String result) {

    }

    @Override
    public void updateShopCredentialSucess(int code, String result) {

    }

    @Override
    public void updateShopCredentialFaild(int code, String result) {

    }

    @Override
    public void uploadShopCredentialSucess(int code, String result) {

    }

    @Override
    public void uploadShopCredentialFaild(int code, String result) {

    }

    private void upDateShopCredentialList() {
        if (shopCredentialBaseInfoList != null) {
            for (int a = 0; a < shopCredentialBaseInfoList.size(); a++) {
                if (credentialBaseTypeInfoList != null) {
                    for (int b = 0; b < credentialBaseTypeInfoList.size(); b++) {
                        if (shopCredentialBaseInfoList.get(a).getName().equals(credentialBaseTypeInfoList.get(b).getName())) {
                            credentialBaseTypeInfoList.remove(b);
                            break;
                        }
                    }
                }
            }
        }

    }

    @Override
    public void getCredentialTypeSucess(int code, String result) {
        if (code == 200) {
            if (result != null && !result.equals("")) {
                credentialBaseTypeInfoList = JSON.parseArray(result, CredentialBaseTypeInfo.class);
                shopCredentialPresenter.getCredential();
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
        if (credentialBaseTypeInfoList != null) {
            upNumber = position;
            BaseTool.logPrint(TAG, "OnClickCredentialBaseDialogListviewItem: " + credentialBaseTypeInfoList.get(position).getName());
            Intent intent = new Intent(getActivity(), CredentialUploadActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("credentialtype", credentialBaseTypeInfoList.get(position));
            bundle.putString("state", "1");
            intent.putExtras(bundle);
            startActivityForResult(intent, 1002);
            credentialBaseDialog.dismiss();
        } else {
            ToastUtil.makeText(getActivity(), "没有可上传的资质信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getBillSuccess(int code, String result) {

    }

    @Override
    public void getBillFailed(int code, String result) {

    }

    @Override
    public void alterBillSuccess(int code, String result) {

    }

    @Override
    public void alterBillFailed(int code, String result) {

    }

    @Override
    public void addBillSuccess(int code, String result) {

    }

    @Override
    public void addBillFailed(int code, String result) {

    }
}
