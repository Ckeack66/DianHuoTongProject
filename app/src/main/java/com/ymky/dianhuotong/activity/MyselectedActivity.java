package com.ymky.dianhuotong.activity;

import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ymky.dianhuotong.R;
import com.ymky.dianhuotong.base.view.BaseActivity;
import com.ymky.dianhuotong.custom.DiaHuiTongBaseTitleBar;
import com.ymky.dianhuotong.fragment.MyselectFragment1;
import com.ymky.dianhuotong.fragment.MyselectFragment2;
import com.ymky.dianhuotong.fragment.MyselectFragment3;
import com.ymky.dianhuotong.fragment.MyselectFragment4;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyselectedActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.myselect_title)
    DiaHuiTongBaseTitleBar diaHuiTongBaseTitleBar;
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
    @BindView(R.id.myselected_fragment)
    FrameLayout frameLayout;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private MyselectFragment1 myselectFragment1;
    private MyselectFragment2 myselectFragment2;
    private MyselectFragment3 myselectFragment3;
    private MyselectFragment4 myselectFragment4;
    private static final String TAG = "MyselectedActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myselected);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        diaHuiTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        diaHuiTongBaseTitleBar.setCenterTextView(getString(R.string.myselect_title));
        diaHuiTongBaseTitleBar.setRightText(getString(R.string.myselected_right));
        radioGroup.setOnCheckedChangeListener(this);
        myselectFragment1 =MyselectFragment1.newInstance("","");
        myselectFragment2 = MyselectFragment2.newInstance("","");
        myselectFragment3 = MyselectFragment3.newInstance("","");
        myselectFragment4 = MyselectFragment4.newInstance("","");
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.myselected_fragment, myselectFragment1);
        fragmentTransaction.add(R.id.myselected_fragment,myselectFragment2);
        fragmentTransaction.add(R.id.myselected_fragment,myselectFragment3);
        fragmentTransaction.add(R.id.myselected_fragment,myselectFragment4);
        showFragment(myselectFragment1);
        fragmentTransaction.commit();
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

    private void showFragment(Fragment fragment){
        if (!myselectFragment1.isHidden()){
            Log.d(TAG, "showFragment: 关闭第一个页面");
            fragmentManager.beginTransaction().hide(myselectFragment1).show(fragment).commit();
        }
        if (!myselectFragment2.isHidden()){
            Log.d(TAG, "showFragment: 关闭第二个页面");
            fragmentManager.beginTransaction().hide(myselectFragment2).show(fragment).commit();
        }
        if (!myselectFragment3.isHidden()){
            Log.d(TAG, "showFragment: 关闭第三个页面");
            fragmentManager.beginTransaction().hide(myselectFragment3).show(fragment).commit();
        }
        if (!myselectFragment4.isHidden()){
            Log.d(TAG, "showFragment: 关闭第四个页面");
            fragmentManager.beginTransaction().hide(myselectFragment4).show(fragment).commit();
        }
    }

}
