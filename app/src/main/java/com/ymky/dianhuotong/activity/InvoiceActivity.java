package com.ymky.dianhuotong.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.ymky.dianhuotong.invoice.fragment.InvoiceFragment1;
import com.ymky.dianhuotong.invoice.fragment.InvoiceFragment2;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InvoiceActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @BindView(R.id.invoice_tab)
    RadioGroup radioGroup;
    @BindView(R.id.invoice_tab_1)
    RadioButton radioButton1;
    @BindView(R.id.invoice_tab_2)
    RadioButton radioButton2;
    @BindView(R.id.invoice_tab_view1)
    View view1;
    @BindView(R.id.invoice_tab_view2)
    View view2;
    @BindView(R.id.invoice_fragment)
    FrameLayout frameLayout;
    @BindView(R.id.invoice_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    private FragmentManager fragmentManager;
    private InvoiceFragment1 invoiceFragment1;
    private InvoiceFragment2 invoiceFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.invoice_title));
        dianHuoTongBaseTitleBar.setRightText(getString(R.string.invoice_save));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        radioGroup.setOnCheckedChangeListener(this);
        invoiceFragment1 = InvoiceFragment1.newInstance("", "");
        invoiceFragment2 = InvoiceFragment2.newInstance("", "");
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.invoice_fragment, invoiceFragment2).add(R.id.invoice_fragment, invoiceFragment1).show(invoiceFragment1).commit();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.invoice_tab_1:
                showRadioButtonColor(radioButton1, view1);
                showFragment(invoiceFragment1);
                break;
            case R.id.invoice_tab_2:
                showRadioButtonColor(radioButton2, view2);
                showFragment(invoiceFragment2);
                break;
        }
    }


    private void showRadioButtonColor(RadioButton radioButton, View view11) {
        radioButton1.setTextColor(getResources().getColor(R.color.color333333));
        radioButton2.setTextColor(getResources().getColor(R.color.color333333));
        view1.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        view2.setBackgroundColor(getResources().getColor(R.color.colorWhite));
        radioButton.setTextColor(getResources().getColor(R.color.color04c1ab));
        view11.setBackgroundColor(getResources().getColor(R.color.color04c1ab));
    }

    private void showFragment(Fragment fragment) {
        if (!invoiceFragment1.isHidden()) {
            fragmentManager.beginTransaction().hide(invoiceFragment1).show(fragment).commit();
        } else if (!invoiceFragment2.isHidden()) {
            fragmentManager.beginTransaction().hide(invoiceFragment2).show(fragment).commit();
        }
    }
}
