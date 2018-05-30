package com.mhky.dianhuotong.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment1;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment2;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment3;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment4;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.precenter.OrderPrecenter;
import com.mhky.dianhuotong.shop.shopif.OrderIF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyselectedActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener, OrderIF {
    @BindView(R.id.myselect_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.myselected_tab)
    RadioGroup radioGroup;
    @BindView(R.id.myselected_tab_1)
    RadioButton radioButton1;
    @BindView(R.id.myselected_tab_2)
    RadioButton radioButton2;
    @BindView(R.id.myselected_tab_3)
    RadioButton radioButton3;
    @BindView(R.id.myselected_tab_4)
    RadioButton radioButton4;
    @BindView(R.id.myselected_tab_view1)
    View view1;
    @BindView(R.id.myselected_tab_view2)
    View view2;
    @BindView(R.id.myselected_tab_view3)
    View view3;
    @BindView(R.id.myselected_tab_view4)
    View view4;
    @BindView(R.id.myselected_tab_view11)
    View view11;
    @BindView(R.id.myselected_tab_view21)
    View view21;
    @BindView(R.id.myselected_tab_view31)
    View view31;
    @BindView(R.id.myselected_tab_view41)
    View view41;
    @BindView(R.id.myselect_shop_name)
    TextView textViewName;
    @BindView(R.id.myselected_fragment)
    FrameLayout frameLayout;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MyselectFragment1 myselectFragment1;
    private MyselectFragment2 myselectFragment2;
    private MyselectFragment3 myselectFragment3;
    private MyselectFragment4 myselectFragment4;

    private OrderPrecenter orderPrecenter;
    private OrderBaseInfo orderBaseInfo;
    private Context mContext;
    private boolean isFirst = false;
    private static final String TAG = "MyselectedActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myselected);
        ButterKnife.bind(this);
        mContext = this;
        inIt();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFirst){
            if (BaseApplication.getInstansApp().getPersonInfo().getShopId()!=null){
                orderPrecenter.getOrder(BaseApplication.getInstansApp().getPersonInfo().getShopId().toString());
            }

        }
    }

    private void inIt() {
        orderPrecenter = new OrderPrecenter(this);
        if (BaseApplication.getInstansApp().getPersonInfo().getShopId()!=null){
            orderPrecenter.getOrder(BaseApplication.getInstansApp().getPersonInfo().getShopId().toString());
        }
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView(getString(R.string.myselect_title));
        if (BaseApplication.getInstansApp().getPersonInfo()!=null&&BaseApplication.getInstansApp().getPersonInfo().getShopName()!=null){
            textViewName.setText(BaseApplication.getInstansApp().getPersonInfo().getShopName().toString());
        }

//        dianHuoTongBaseTitleBar.setRightText(getString(R.string.myselected_right));
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        dianHuoTongBaseTitleBar.setRightTextOnclickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BaseTool.goActivityNoData(mContext, BillActivity.class);
//            }
//        });
        radioGroup.setOnCheckedChangeListener(this);

    }

    @OnClick(R.id.myselect_zizhi)
    void goMyZiZhi() {
        BaseTool.goActivityNoData(this, InvoiceActivity.class);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.myselected_tab_1:
                //Toast.makeText(this, "选择了1", Toast.LENGTH_SHORT).show();
                changeState(radioButton1, view1, view11);
                showFragment(myselectFragment1);
                break;
            case R.id.myselected_tab_2:
                //Toast.makeText(this, "选择了2", Toast.LENGTH_SHORT).show();
                changeState(radioButton2, view2, view21);
                showFragment(myselectFragment2);
                break;
            case R.id.myselected_tab_3:
                //Toast.makeText(this, "选择了3", Toast.LENGTH_SHORT).show();
                changeState(radioButton3, view3, view31);
                showFragment(myselectFragment3);
                break;
            case R.id.myselected_tab_4:
                //Toast.makeText(this, "选择了4", Toast.LENGTH_SHORT).show();
                changeState(radioButton4, view4, view41);
                showFragment(myselectFragment4);
                break;
        }
    }

    private void changeState(RadioButton radioButton, View mView, View mView1) {
        radioButton1.setTextColor(Color.parseColor("#333333"));
        radioButton2.setTextColor(Color.parseColor("#333333"));
        radioButton3.setTextColor(Color.parseColor("#333333"));
        radioButton4.setTextColor(Color.parseColor("#333333"));
        view1.setBackgroundColor(Color.parseColor("#00ffffff"));
        view2.setBackgroundColor(Color.parseColor("#00ffffff"));
        view3.setBackgroundColor(Color.parseColor("#00ffffff"));
        view4.setBackgroundColor(Color.parseColor("#00ffffff"));
        view11.setBackgroundColor(Color.parseColor("#BFBFBF"));
        view21.setBackgroundColor(Color.parseColor("#BFBFBF"));
        view31.setBackgroundColor(Color.parseColor("#BFBFBF"));
        view41.setBackgroundColor(Color.parseColor("#BFBFBF"));
        radioButton.setTextColor(Color.parseColor("#04c1ab"));
        mView.setBackgroundColor(Color.parseColor("#04c1ab"));
        mView1.setBackgroundColor(Color.parseColor("#04c1ab"));
    }

    private void showFragment(Fragment fragment) {
        if (myselectFragment1 != null && !myselectFragment1.isHidden()) {
            BaseTool.logPrint(TAG, "showFragment: 关闭第一个页面");
            fragmentManager.beginTransaction().hide(myselectFragment1).show(fragment).commit();
        }
        if (myselectFragment2 != null && !myselectFragment2.isHidden()) {
            BaseTool.logPrint(TAG, "showFragment: 关闭第二个页面");
            fragmentManager.beginTransaction().hide(myselectFragment2).show(fragment).commit();
        }
        if (myselectFragment3 != null && !myselectFragment3.isHidden()) {
            BaseTool.logPrint(TAG, "showFragment: 关闭第三个页面");
            fragmentManager.beginTransaction().hide(myselectFragment3).show(fragment).commit();
        }
        if (myselectFragment4 != null && !myselectFragment4.isHidden()) {
            BaseTool.logPrint(TAG, "showFragment: 关闭第四个页面");
            fragmentManager.beginTransaction().hide(myselectFragment4).show(fragment).commit();
        }
    }

    @Override
    public void getOrderSucess(int code, String result) {
        if (code == 200) {
            orderBaseInfo = JSON.parseObject(result, OrderBaseInfo.class);
            if (!isFirst) {
                myselectFragment1 = MyselectFragment1.newInstance("", "", orderBaseInfo);
                myselectFragment2 = MyselectFragment2.newInstance("", "", orderBaseInfo);
                myselectFragment3 = MyselectFragment3.newInstance("", "", orderBaseInfo);
                myselectFragment4 = MyselectFragment4.newInstance("", "", orderBaseInfo);
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.myselected_fragment, myselectFragment1);
                fragmentTransaction.add(R.id.myselected_fragment, myselectFragment2);
                fragmentTransaction.add(R.id.myselected_fragment, myselectFragment3);
                fragmentTransaction.add(R.id.myselected_fragment, myselectFragment4);
                showFragment(myselectFragment1);
                fragmentTransaction.commit();
                isFirst = true;
            } else {
                myselectFragment1.upData(orderBaseInfo);
                myselectFragment2.upData(orderBaseInfo);
                myselectFragment3.upData(orderBaseInfo);
                myselectFragment4.upData(orderBaseInfo);
            }
        }
    }

    @Override
    public void getOrderFaild(int code, String result) {

    }
}
