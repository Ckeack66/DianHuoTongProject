package com.mhky.dianhuotong.invoice.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.bean.BillInfo;
import com.mhky.dianhuotong.shop.precenter.BillPresenter;
import com.mhky.dianhuotong.shop.shopif.BillIF;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InvoiceFragment2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InvoiceFragment2 extends Fragment implements BillIF {
    @BindView(R.id.bill_type1)
    RadioButton radioButton1;
    @BindView(R.id.bill_type2)
    RadioButton radioButton2;
    @BindView(R.id.bill_number)
    EditText editTextBillNumber;
    @BindView(R.id.bill_bank)
    EditText editTextBillBank;
    @BindView(R.id.bill_bank_number)
    EditText editTextBillBankNumber;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private BillPresenter billPresenter;
    private BillInfo billInfo;
    private boolean isGetBillInfo = false;
    private Unbinder unbinder;

    public InvoiceFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InvoiceFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static InvoiceFragment2 newInstance(String param1, String param2) {
        InvoiceFragment2 fragment = new InvoiceFragment2();
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
        View view = inflater.inflate(R.layout.fragment_invoice_fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        billPresenter = new BillPresenter(this);
        billPresenter.getBill();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    public void saveInfo() {
        if (!radioButton1.isChecked() && !radioButton2.isChecked()) {
            ToastUtil.makeText(getActivity(), "请选择发票类型", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextBillNumber.getText().toString())) {
            ToastUtil.makeText(getActivity(), "请输入税号", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextBillBank.getText().toString())) {
            ToastUtil.makeText(getActivity(), "请输入开户银行", Toast.LENGTH_SHORT).show();
            return;
        } else if (TextUtils.isEmpty(editTextBillBankNumber.getText().toString())) {
            ToastUtil.makeText(getActivity(), "请输入银行卡号", Toast.LENGTH_SHORT).show();
            return;
        }
        if (billInfo != null) {
            billInfo.setAccountNumber(editTextBillBankNumber.getText().toString());
            billInfo.setBankName(editTextBillBank.getText().toString());
            billInfo.setTaxNumber(editTextBillNumber.getText().toString());
            billInfo.setCompositeid(Integer.valueOf(BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString()));
            if (radioButton1.isChecked()) {
                billInfo.setTicketType("UNIVERSAL");
            } else if (radioButton2.isChecked()) {
                billInfo.setTicketType("SPECIAL");
            }
            if (!isGetBillInfo) {
                //post
                billPresenter.addBill(JSON.toJSONString(billInfo));
            } else {
                //put
//                HttpParams httpParams = new HttpParams();
//                httpParams.put("ticketInformationDTO", JSON.toJSONString(billInfo));
                billPresenter.updateBill(JSON.toJSONString(billInfo));
            }
        }else {
            billInfo = new BillInfo();
            billInfo.setAccountNumber(editTextBillBankNumber.getText().toString());
            billInfo.setBankName(editTextBillBank.getText().toString());
            billInfo.setTaxNumber(editTextBillNumber.getText().toString());
            billInfo.setCompositeid(Integer.valueOf(BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString()));
            if (radioButton1.isChecked()) {
                billInfo.setTicketType("UNIVERSAL");
            } else if (radioButton2.isChecked()) {
                billInfo.setTicketType("SPECIAL");
            }
            if (!isGetBillInfo) {
                //post
                billPresenter.addBill(JSON.toJSONString(billInfo));
            } else {
                //put
//                HttpParams httpParams = new HttpParams();
//                httpParams.put("ticketInformationDTO", JSON.toJSONString(billInfo));
                billPresenter.updateBill(JSON.toJSONString(billInfo));
            }
        }

    }

    @Override
    public void getBillSuccess(int code, String result) {
        if (code == 200) {
            billInfo = JSON.parseObject(result, BillInfo.class);
            if (billInfo!=null){
                isGetBillInfo = true;
                if ("UNIVERSAL".equals(billInfo.getTicketType())) {
                    radioButton1.setChecked(true);
                } else if ("SPECIAL".equals(billInfo.getTicketType())) {
                    radioButton2.setChecked(true);
                }
                editTextBillNumber.setText(billInfo.getTaxNumber());
                editTextBillBank.setText(billInfo.getBankName());
                editTextBillBankNumber.setText(billInfo.getAccountNumber());
            }else {
                isGetBillInfo = false;
                billInfo = new BillInfo();
            }
        } else {
            isGetBillInfo = false;
            billInfo = new BillInfo();
        }
    }

    @Override
    public void getBillFailed(int code, String result) {

    }

    @Override
    public void alterBillSuccess(int code, String result) {
        ToastUtil.makeText(getActivity(), "已修改发票信息", Toast.LENGTH_SHORT).show();
        billPresenter.getBill();
    }

    @Override
    public void alterBillFailed(int code, String result) {

    }

    @Override
    public void addBillSuccess(int code, String result) {
        ToastUtil.makeText(getActivity(), "已新增发票信息", Toast.LENGTH_SHORT).show();
        billPresenter.getBill();
    }

    @Override
    public void addBillFailed(int code, String result) {

    }
}
