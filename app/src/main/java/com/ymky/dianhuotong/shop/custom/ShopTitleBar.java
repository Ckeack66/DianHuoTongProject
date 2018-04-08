package com.ymky.dianhuotong.shop.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ymky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/8.
 */

public class ShopTitleBar extends LinearLayout implements View.OnClickListener{
    private Context mContext;
    private ImageView imageViewBack;
    private ImageView imageViewScanCode;
    private ImageView imageVieworder;
    private ImageView imageViewcar;
    private EditText editTextInput;
    private ShopTitleBarOnclickListener shopTitleBarOnclickListener;

    public ShopTitleBar(Context context,ShopTitleBarOnclickListener shopTitleBarOnclickListener1) {
        super(context);
        mContext = context;
        shopTitleBarOnclickListener=shopTitleBarOnclickListener1;
        inIt();
    }

    public ShopTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inIt();
    }

    public ShopTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inIt();
    }

    private void inIt() {
        LayoutInflater.from(mContext).inflate(R.layout.group_widget_title_shop, this);
        imageViewBack = findViewById(R.id.shop_title_left_image);
        imageViewScanCode = findViewById(R.id.shop_scan_code);
        imageVieworder = findViewById(R.id.shop_order);
        imageViewcar = findViewById(R.id.shop_car);
        editTextInput = findViewById(R.id.shop_input);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_title_left_image:
                shopTitleBarOnclickListener.onclickBack();
                break;
            case R.id.shop_scan_code:
                shopTitleBarOnclickListener.onclickScanCode();
                break;
            case R.id.shop_input:
                shopTitleBarOnclickListener.onclickInput();
                break;
            case R.id.shop_order:
                shopTitleBarOnclickListener.onclickOrder();
                break;
            case R.id.shop_car:
                shopTitleBarOnclickListener.onclickCar();
                break;
        }
    }
    public interface ShopTitleBarOnclickListener {
        void onclickBack();

        void onclickScanCode();

        void onclickOrder();

        void onclickCar();

        void onclickInput();
    }
}
