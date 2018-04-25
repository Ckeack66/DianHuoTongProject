package com.mhky.dianhuotong.shop.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.fragment.ShopAllGoodsFragment;
import com.mhky.dianhuotong.shop.fragment.ShopMainFragment;
import com.mhky.dianhuotong.shop.fragment.ShopTransferFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShopActivity extends AppCompatActivity {
    @BindView(R.id.shop_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    @BindView(R.id.shop_main_tab1)
    TextView textViewTab1;
    @BindView(R.id.shop_main_tab2)
    TextView textViewTab2;
    @BindView(R.id.shop_main_tab3)
    TextView textViewTab3;
    @BindView(R.id.shop_main_tab11)
    View viewTab11;
    @BindView(R.id.shop_main_tab21)
    View viewTab21;
    @BindView(R.id.shop_main_tab31)
    View viewTab31;
    @BindView(R.id.shop_tab1)
    RelativeLayout relativeLayoutTab1;
    @BindView(R.id.shop_tab2)
    RelativeLayout relativeLayoutTab2;
    @BindView(R.id.shop_tab3)
    RelativeLayout relativeLayoutTab3;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private ShopAllGoodsFragment shopAllGoodsFragment;
    private ShopMainFragment shopMainFragment;
    private ShopTransferFragment shopTransferFragment;
    private static final String TAG = "ShopActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongShopTitleBar.setActivity(this);
        relativeLayoutTab1.setClickable(false);
        shopAllGoodsFragment = ShopAllGoodsFragment.newInstance("", "");
        shopMainFragment = ShopMainFragment.newInstance("", "");
        shopTransferFragment = ShopTransferFragment.newInstance("", "");
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.shop_fragment, shopTransferFragment);
        fragmentTransaction.add(R.id.shop_fragment, shopAllGoodsFragment);
        fragmentTransaction.add(R.id.shop_fragment, shopMainFragment);
        fragmentTransaction.show(shopMainFragment);
        fragmentTransaction.commit();


    }

    @OnClick(R.id.shop_tab1)
    void setTab1() {
        setState(textViewTab1, viewTab11, relativeLayoutTab1);
        showFragment(shopMainFragment);
    }

    @OnClick(R.id.shop_tab2)
    void setTab2() {
        setState(textViewTab2, viewTab21, relativeLayoutTab2);
        showFragment(shopAllGoodsFragment);
    }

    @OnClick(R.id.shop_tab3)
    void setTab3() {
        showFragment(shopTransferFragment);
        setState(textViewTab3, viewTab31, relativeLayoutTab3);
    }

    private void setState(TextView textView, View view, RelativeLayout relativeLayout) {
        clearAllState();
        textView.setTextColor(getResources().getColor(R.color.color04c1ab));
        view.setVisibility(View.VISIBLE);
        relativeLayout.setClickable(false);
    }

    private void clearAllState() {
        textViewTab1.setTextColor(getResources().getColor(R.color.color333333));
        textViewTab2.setTextColor(getResources().getColor(R.color.color333333));
        textViewTab3.setTextColor(getResources().getColor(R.color.color333333));
        viewTab11.setVisibility(View.GONE);
        viewTab21.setVisibility(View.GONE);
        viewTab31.setVisibility(View.GONE);
        relativeLayoutTab1.setClickable(true);
        relativeLayoutTab2.setClickable(true);
        relativeLayoutTab3.setClickable(true);
    }

    private void showFragment(Fragment fragment) {
        if (!shopMainFragment.isHidden()) {
            Log.d(TAG, "showFragment: 关闭第一个页面");
            fragmentManager.beginTransaction().hide(shopMainFragment).show(fragment).commit();
        }
        if (!shopAllGoodsFragment.isHidden()) {
            Log.d(TAG, "showFragment: 关闭第二个页面");
            fragmentManager.beginTransaction().hide(shopAllGoodsFragment).show(fragment).commit();
        }
        if (!shopTransferFragment.isHidden()) {
            Log.d(TAG, "showFragment: 关闭第三个页面");
            fragmentManager.beginTransaction().hide(shopTransferFragment).show(fragment).commit();
        }

    }
}
