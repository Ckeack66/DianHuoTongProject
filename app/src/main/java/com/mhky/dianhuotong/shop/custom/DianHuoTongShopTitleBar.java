package com.mhky.dianhuotong.shop.custom;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.activity.MyselectedActivity;
import com.mhky.dianhuotong.activity.ScanCodeActivity;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.activity.CartActivity;

/**
 * Created by Administrator on 2018/4/24.
 */

public class DianHuoTongShopTitleBar extends RelativeLayout implements View.OnClickListener {
    private TextView textViewCenter;
    private ImageView imageViewBack;
    private ImageView imageViewScanCode;
    private ImageView imageViewShopOrder;
    private ImageView imageViewShopCart;
    private Context mContext;
    private Activity activityThis;

    public DianHuoTongShopTitleBar(Context context) {
        super(context);
        mContext = context;
        inIt();
    }

    public DianHuoTongShopTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inIt();
    }

    public DianHuoTongShopTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inIt();
    }

    public void setActivity(Activity activity) {
        activityThis = activity;
    }

    private void inIt() {
        LayoutInflater.from(mContext).inflate(R.layout.group_widget_title_shop, this);
        textViewCenter = findViewById(R.id.shop_input);
        imageViewBack = findViewById(R.id.shop_title_left_image);
        imageViewScanCode = findViewById(R.id.shop_scan_code);
        imageViewShopOrder = findViewById(R.id.shop_order);
        imageViewShopCart = findViewById(R.id.shop_car);
        textViewCenter.setOnClickListener(this);
        imageViewBack.setOnClickListener(this);
        imageViewScanCode.setOnClickListener(this);
        imageViewShopOrder.setOnClickListener(this);
        imageViewShopCart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_title_left_image:
                activityThis.finish();
                break;
            case R.id.shop_scan_code:
                BaseTool.goActivityNoData(mContext, ScanCodeActivity.class);
                break;
            case R.id.shop_input:
                ToastUtil.makeText(mContext, "跳转到搜索界面", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shop_car:
                ToastUtil.makeText(mContext, "跳转到购物车界面", Toast.LENGTH_SHORT).show();
                BaseTool.goActivityNoData(mContext, CartActivity.class);
                break;
            case R.id.shop_order:
                BaseTool.goActivityNoData(mContext, MyselectedActivity.class);
                break;
        }
    }
}
