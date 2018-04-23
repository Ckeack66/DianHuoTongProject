package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.yanzhenjie.sofia.Bar;
import com.yanzhenjie.sofia.Sofia;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodsActivity extends BaseActivity {
    @BindView(R.id.goods_title)
    TextView textViewTitle;
    @BindView(R.id.goods_price)
    TextView textViewGoodsPrice;
    @BindView(R.id.goods_info_name)
    TextView textViewGoodsName;
    @BindView(R.id.goods_info_guige)
    TextView textViewGoodsGuige;
    @BindView(R.id.goods_info_company)
    TextView textViewGoodsCompany;
    @BindView(R.id.goods_use_time)
    TextView textViewUseTime;
    @BindView(R.id.goods_info_pzwh)
    TextView textViewPzwh;
    private SearchSGoodsBean.ContentBean goodsInfoBase;
    private Bundle bundle;
    private Context mContext;
    private static final String TAG = "GoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mContext = this;
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor("#ffffff").statusBarDarkFont(true).init();
        if (getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            goodsInfoBase = (SearchSGoodsBean.ContentBean) bundle.getSerializable("getgoodsinfo");
            if (goodsInfoBase != null) {
                textViewTitle.setText(goodsInfoBase.getTitle());
                textViewGoodsPrice.setText(String.valueOf(goodsInfoBase.getPrice() / 100));
                textViewGoodsName.setText(goodsInfoBase.getName());
                textViewGoodsCompany.setText(goodsInfoBase.getManufacturer());
                textViewGoodsGuige.setText(goodsInfoBase.getModel());
                textViewPzwh.setText(goodsInfoBase.getApprovalNumber());
                textViewUseTime.setText(goodsInfoBase.getExpiryDate());
            } else {
                ToastUtil.makeText(mContext, "数据解析错误", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
