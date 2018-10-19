package com.mhky.yaolinwang.order.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseActivityCK;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment1;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment2;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment3;
import com.mhky.dianhuotong.dingdan.fragment.MyselectFragment4;
import com.mhky.yaolinwang.order.fragment.CustomerOrdersFragment1;
import com.mhky.yaolinwang.order.fragment.CustomerOrdersFragment2;
import com.mhky.yaolinwang.order.fragment.CustomerOrdersFragment3;
import com.mhky.yaolinwang.order.fragment.CustomerOrdersFragment4;
import com.mhky.yaolinwang.order.fragment.CustomerOrdersFragment5;
import com.mhky.yaolinwang.order.fragment.CustomerOrdersFragment6;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * C 端订单列表Activity
 */

public class CustomerOrdersActivity extends BaseActivityCK implements RadioGroup.OnCheckedChangeListener{

    @BindView(R.id.customer_order_title_bar)
    DianHuoTongBaseTitleBar customerOrderTitleBar;
    @BindView(R.id.myselected_tab_1)
    RadioButton myselectedTab1;
    @BindView(R.id.myselected_tab_2)
    RadioButton myselectedTab2;
    @BindView(R.id.myselected_tab_3)
    RadioButton myselectedTab3;
    @BindView(R.id.myselected_tab_4)
    RadioButton myselectedTab4;
    @BindView(R.id.myselected_tab_5)
    RadioButton myselectedTab5;
    @BindView(R.id.myselected_tab_6)
    RadioButton myselectedTab6;
    @BindView(R.id.my_selected_tab)
    RadioGroup mySelectedTab;
    @BindView(R.id.myselected_tab_view1)
    View myselectedTabView1;
    @BindView(R.id.myselected_tab_view2)
    View myselectedTabView2;
    @BindView(R.id.myselected_tab_view3)
    View myselectedTabView3;
    @BindView(R.id.myselected_tab_view4)
    View myselectedTabView4;
    @BindView(R.id.myselected_tab_view5)
    View myselectedTabView5;
    @BindView(R.id.myselected_tab_view6)
    View myselectedTabView6;
    @BindView(R.id.my_selected_tab_line)
    LinearLayout mySelectedTabLine;
    @BindView(R.id.fl_fragment)
    FrameLayout flFragment;

    private List<BasePresenter> presenterList = new ArrayList<>();
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private CustomerOrdersFragment1 customerOrdersFragment1;
    private CustomerOrdersFragment2 customerOrdersFragment2;
    private CustomerOrdersFragment3 customerOrdersFragment3;
    private CustomerOrdersFragment4 customerOrdersFragment4;
    private CustomerOrdersFragment5 customerOrdersFragment5;
    private CustomerOrdersFragment6 customerOrdersFragment6;
    private boolean isFirst = false;                                        //是否是首次进入页面

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
        setContentView(R.layout.activity_customer_orders);
        ButterKnife.bind(this);
        try {
            initView();
            initListener();
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    private void initView() {
        customerOrderTitleBar.setLeftImage(R.drawable.icon_back);
        customerOrderTitleBar.setCenterTextView("我的订单");
        customerOrdersFragment1 = new CustomerOrdersFragment1();
        customerOrdersFragment2 = new CustomerOrdersFragment2();
        customerOrdersFragment3 = new CustomerOrdersFragment3();
        customerOrdersFragment4 = new CustomerOrdersFragment4();
        customerOrdersFragment5 = new CustomerOrdersFragment5();
        customerOrdersFragment6 = new CustomerOrdersFragment6();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fl_fragment, customerOrdersFragment1);
        fragmentTransaction.add(R.id.fl_fragment, customerOrdersFragment2);
        fragmentTransaction.add(R.id.fl_fragment, customerOrdersFragment3);
        fragmentTransaction.add(R.id.fl_fragment, customerOrdersFragment4);
        fragmentTransaction.add(R.id.fl_fragment, customerOrdersFragment5);
        fragmentTransaction.add(R.id.fl_fragment, customerOrdersFragment6);
        showFragment(customerOrdersFragment1);
        fragmentTransaction.commit();
    }

    private void initListener() {
        customerOrderTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mySelectedTab.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        try{
            switch (checkedId) {
                case R.id.myselected_tab_1:
                    changeState(myselectedTab1, myselectedTabView1);
                    showFragment(customerOrdersFragment1);
                    break;
                case R.id.myselected_tab_2:
                    //Toast.makeText(this, "选择了2", Toast.LENGTH_SHORT).show();
                    changeState(myselectedTab2, myselectedTabView2);
                    showFragment(customerOrdersFragment2);
                    break;
                case R.id.myselected_tab_3:
                    //Toast.makeText(this, "选择了3", Toast.LENGTH_SHORT).show();
                    changeState(myselectedTab3, myselectedTabView3);
                    showFragment(customerOrdersFragment3);
                    break;
                case R.id.myselected_tab_4:
                    changeState(myselectedTab4, myselectedTabView4);
                    showFragment(customerOrdersFragment4);
                    break;
                case R.id.myselected_tab_5:
                    changeState(myselectedTab5, myselectedTabView5);
                    showFragment(customerOrdersFragment5);
                    break;
                case R.id.myselected_tab_6:
                    changeState(myselectedTab6, myselectedTabView6);
                    showFragment(customerOrdersFragment6);
                    break;
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    private void changeState(RadioButton radioButton, View mView) {
        try {
            myselectedTab1.setTextColor(Color.parseColor("#333333"));
            myselectedTab2.setTextColor(Color.parseColor("#333333"));
            myselectedTab3.setTextColor(Color.parseColor("#333333"));
            myselectedTab4.setTextColor(Color.parseColor("#333333"));
            myselectedTab5.setTextColor(Color.parseColor("#333333"));
            myselectedTab6.setTextColor(Color.parseColor("#333333"));
            myselectedTabView1.setBackgroundColor(Color.parseColor("#00ffffff"));
            myselectedTabView2.setBackgroundColor(Color.parseColor("#00ffffff"));
            myselectedTabView3.setBackgroundColor(Color.parseColor("#00ffffff"));
            myselectedTabView4.setBackgroundColor(Color.parseColor("#00ffffff"));
            myselectedTabView5.setBackgroundColor(Color.parseColor("#00ffffff"));
            myselectedTabView6.setBackgroundColor(Color.parseColor("#00ffffff"));
            radioButton.setTextColor(Color.parseColor("#04c1ab"));
            mView.setBackgroundColor(Color.parseColor("#04c1ab"));
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    private void showFragment(Fragment fragment) {
        try {
            if (customerOrdersFragment1 != null && !customerOrdersFragment1.isHidden()) {
                BaseTool.logPrint("页面", "showFragment: 关闭第一个页面");
                fragmentManager.beginTransaction().hide(customerOrdersFragment1).show(fragment).commit();
            }
            if (customerOrdersFragment2 != null && !customerOrdersFragment2.isHidden()) {
                BaseTool.logPrint("页面", "showFragment: 关闭第二个页面");
                fragmentManager.beginTransaction().hide(customerOrdersFragment2).show(fragment).commit();
            }
            if (customerOrdersFragment3 != null && !customerOrdersFragment3.isHidden()) {
                fragmentManager.beginTransaction().hide(customerOrdersFragment3).show(fragment).commit();
            }
            if (customerOrdersFragment4 != null && !customerOrdersFragment4.isHidden()) {
                fragmentManager.beginTransaction().hide(customerOrdersFragment4).show(fragment).commit();
            }
            if (customerOrdersFragment5 != null && !customerOrdersFragment5.isHidden()) {
                fragmentManager.beginTransaction().hide(customerOrdersFragment5).show(fragment).commit();
            }
            if (customerOrdersFragment6 != null && !customerOrdersFragment6.isHidden()) {
                fragmentManager.beginTransaction().hide(customerOrdersFragment6).show(fragment).commit();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this,e);
        }
    }
}
