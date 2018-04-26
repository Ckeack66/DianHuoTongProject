package com.mhky.dianhuotong.shop.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.shop.fragment.GoodsInfoDetialsFragment;
import com.mhky.dianhuotong.shop.fragment.GoodsInfoInstructionFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoodsInfoActivity extends BaseActivity {
    @BindView(R.id.goods_info_left)
    TextView textViewLeft;
    @BindView(R.id.goods_info_right)
    TextView textViewRight;
    private Bundle bundle;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private GoodsInfoDetialsFragment goodsInfoDetialsFragment;
    private GoodsInfoInstructionFragment goodsInfoInstructionFragment;
    private String goodsDes;
    private String goodsIns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        bundle = getIntent().getExtras();
        if (bundle != null) {
            goodsDes = bundle.getString("des");
            goodsIns = bundle.getString("ins");
        }
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        goodsInfoDetialsFragment = GoodsInfoDetialsFragment.newInstance(goodsDes, "");
        goodsInfoInstructionFragment = GoodsInfoInstructionFragment.newInstance(goodsIns, "");
        fragmentTransaction.add(R.id.goods_info_fragment, goodsInfoInstructionFragment);
        fragmentTransaction.add(R.id.goods_info_fragment, goodsInfoDetialsFragment);
        fragmentTransaction.show(goodsInfoDetialsFragment).commit();
    }

    @OnClick(R.id.goods_info_left)
    void chooseLeft() {
        textViewLeft.setEnabled(false);
        textViewRight.setEnabled(true);
        fragmentManager.beginTransaction().hide(goodsInfoInstructionFragment).show(goodsInfoDetialsFragment).commit();
    }

    @OnClick(R.id.goods_info_right)
    void chooseRight() {
        textViewRight.setEnabled(false);
        textViewLeft.setEnabled(true);
        fragmentManager.beginTransaction().hide(goodsInfoDetialsFragment).show(goodsInfoInstructionFragment).commit();
    }
}
