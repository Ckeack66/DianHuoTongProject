package com.mhky.yaolinwang.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cb.ratingbar.CBRatingBar;
import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseActivityCK;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 药邻网Acitivtyz主界面
 */

public class YaoLinWangActivity extends BaseActivityCK {

    @BindView(R.id.yaolinwang_top_title)
    DianHuoTongShopTitleBar yaolinwangTopTitle;
    @BindView(R.id.yaolinwang_main_banner)
    BGABanner yaolinwangMainBanner;
    @BindView(R.id.rl_yaolinwang_top)
    RelativeLayout rlYaolinwangTop;
    @BindView(R.id.status_bar_v)
    View statusBarV;
    @BindView(R.id.tv_drug_zone)
    TextView tvDrugZone;
    @BindView(R.id.tv_medical_machine)
    TextView tvMedicalMachine;
    @BindView(R.id.tv_adult_products)
    TextView tvAdultProducts;
    @BindView(R.id.tv_health_food)
    TextView tvHealthFood;
    @BindView(R.id.tv_ch_medical_drink)
    TextView tvChMedicalDrink;
    @BindView(R.id.ll_yaolinwang_main_btn)
    LinearLayout llYaolinwangMainBtn;
    @BindView(R.id.tv_title1)
    TextView tvTitle1;
    @BindView(R.id.tv_favorable_title1)
    TextView tvFavorableTitle1;
    @BindView(R.id.tv_favorable_new_price1)
    TextView tvFavorableNewPrice1;
    @BindView(R.id.tv_favorable_old_price1)
    TextView tvFavorableOldPrice1;
    @BindView(R.id.ll_favorable_zone)
    LinearLayout llFavorableZone;
    @BindView(R.id.nsv_dianhuotong)
    NestedScrollView nsvDianhuotong;
    @BindView(R.id.iv_favorable_1)
    ImageView ivFavorable1;
    @BindView(R.id.tv_favorable_title2)
    TextView tvFavorableTitle2;
    @BindView(R.id.tv_favorable_new_price2)
    TextView tvFavorableNewPrice2;
    @BindView(R.id.tv_favorable_old_price2)
    TextView tvFavorableOldPrice2;
    @BindView(R.id.rl_favorable_2)
    RelativeLayout rlFavorable2;
    @BindView(R.id.iv_favorable_2)
    ImageView ivFavorable2;
    @BindView(R.id.tv_favorable_title3)
    TextView tvFavorableTitle3;
    @BindView(R.id.tv_favorable_new_price3)
    TextView tvFavorableNewPrice3;
    @BindView(R.id.tv_favorable_old_price3)
    TextView tvFavorableOldPrice3;
    @BindView(R.id.rl_favorable_3)
    RelativeLayout rlFavorable3;
    @BindView(R.id.iv_favorable_3)
    ImageView ivFavorable3;
    @BindView(R.id.tv_title2)
    TextView tvTitle2;
    @BindView(R.id.shop_listview)
    RecyclerView shopListview;
    @BindView(R.id.srl_shop)
    SmartRefreshLayout srlShop;

    private String[] mVals = new String[]
            {"Hello", "Android", "Weclome Hi ", "Button", "TextView", "Hello",
                    "Android"};

    @Override
    public List<BasePresenter> getPresenter() {
        return null;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yao_lin_wang2);
        ButterKnife.bind(this);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        ImmersionBar.with(this).statusBarView(statusBarV).init();
        yaolinwangTopTitle.setbg(R.color.colorTransp);
        yaolinwangTopTitle.setActivity(this);
        tvFavorableOldPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvFavorableOldPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        tvFavorableOldPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
    }

    private void initListener() {

    }

    private void initData() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImmersionBar.with(this).destroy();
    }
}
