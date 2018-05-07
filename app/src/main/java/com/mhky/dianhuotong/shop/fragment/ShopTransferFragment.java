package com.mhky.dianhuotong.shop.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.activity.CredentialUploadActivity;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.activity.CardActivity;
import com.mhky.dianhuotong.shop.adapter.CredentialRecycleView1Adapter;
import com.mhky.dianhuotong.shop.bean.ShopCredentialBaseInfo;
import com.mhky.dianhuotong.shop.bean.ShopTransferInfo;
import com.mhky.dianhuotong.shop.precenter.CompanyPrecenter;
import com.mhky.dianhuotong.shop.shopif.CompanyIF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopTransferFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopTransferFragment extends Fragment implements CompanyIF {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder unbinder;
    private CompanyPrecenter companyPrecenter;
    @BindView(R.id.shop_transfer_intruduce)
    TextView textViewIntruduce;
    @BindView(R.id.shop_transfer_transport)
    TextView textViewTransport;
    @BindView(R.id.shop_transfer_openexplain)
    TextView textViewOpenAccount;
    @BindView(R.id.shop_transfer_openexplain_body)
    TextView textViewAccountBody;
    @BindView(R.id.shop_transfer_sel)
    TextView textViewSel;
    @BindView(R.id.shop_transfer_card)
    RecyclerView recyclerView;
    private List<ShopCredentialBaseInfo> shopCredentialBaseInfoList;
    private CredentialRecycleView1Adapter credentialRecycleView1Adapter;

    public ShopTransferFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopTransferFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopTransferFragment newInstance(String param1, String param2) {
        ShopTransferFragment fragment = new ShopTransferFragment();
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
        View view = inflater.inflate(R.layout.fragment_shop_transfer, container, false);
        unbinder = ButterKnife.bind(this, view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        companyPrecenter = new CompanyPrecenter(this);
        shopCredentialBaseInfoList = new ArrayList<>();
        companyPrecenter.getCompanyTansferInfo(mParam1);
        companyPrecenter.getCompenayCredential(mParam1);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.shop_transfer_gocard)
    void goCard() {
        BaseTool.goActivityNoData(getActivity(), CardActivity.class);
    }

    @Override
    public void getCompanyCredentialSucess(int code, String result) {
        if (code == 200) {
            shopCredentialBaseInfoList = JSON.parseArray(result, ShopCredentialBaseInfo.class);
            credentialRecycleView1Adapter = new CredentialRecycleView1Adapter(shopCredentialBaseInfoList, getActivity());
            recyclerView.setAdapter(credentialRecycleView1Adapter);
        }
    }

    @Override
    public void getCompanyCredentialFaild(int code, String result) {

    }

    @Override
    public void getCompanyTansferSucess(int code, String result) {
        if (code == 200) {
            ShopTransferInfo shopTransferInfo = JSON.parseObject(result, ShopTransferInfo.class);
            if (shopTransferInfo != null) {
                if (shopTransferInfo.getIntroduce() != null) {
                    textViewIntruduce.setText(shopTransferInfo.getIntroduce().toString());
                }
                if (shopTransferInfo.getShippingMethod() != null) {
                    textViewTransport.setText(shopTransferInfo.getShippingMethod().toString());
                }
                if (shopTransferInfo.getOpenProcess() != null) {
                    textViewOpenAccount.setText(shopTransferInfo.getOpenProcess().toString());
                }
                if (shopTransferInfo.getOpenExplain() != null) {
                    textViewAccountBody.setText(shopTransferInfo.getOpenExplain().toString());
                }
                if (shopTransferInfo.getAfterSaleService() != null) {
                    textViewSel.setText(shopTransferInfo.getAfterSaleService().toString());
                }
            }
        }

    }

    @Override
    public void getCompanyTansferFaild(int code, String result) {

    }
}
