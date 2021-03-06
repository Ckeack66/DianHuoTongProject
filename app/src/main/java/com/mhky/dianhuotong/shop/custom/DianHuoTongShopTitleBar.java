package com.mhky.dianhuotong.shop.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
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
import com.mhky.dianhuotong.shop.activity.SearchActivity;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by Administrator on 2018/4/24.
 * 典货通  searchbar
 */

public class DianHuoTongShopTitleBar extends RelativeLayout implements View.OnClickListener {

    private TextView textViewCenter;
    private FrameLayout frameLayoutBack;
    private FrameLayout frameLayoutOrder;
    private FrameLayout frameLayoutCart;
    private ImageView imageViewScanCode;
    private RelativeLayout rl_title_shop;
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
        imageViewScanCode = findViewById(R.id.shop_scan_code);
        frameLayoutBack=findViewById(R.id.shop_title_back);
        frameLayoutOrder=findViewById(R.id.shop_order_fl);
        frameLayoutCart=findViewById(R.id.shop_car_fl);
        rl_title_shop = findViewById(R.id.rl_group_widget_title_shop);
        textViewCenter.setOnClickListener(this);
        imageViewScanCode.setOnClickListener(this);
        frameLayoutBack.setOnClickListener(this);
        frameLayoutOrder.setOnClickListener(this);
        frameLayoutCart.setOnClickListener(this);
    }
    public void setCenterText(String text){
        textViewCenter.setText(text);
    }

    public void setbg(int color){
        rl_title_shop.setBackground(new ColorDrawable(getResources().getColor(color)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_title_back:                      //返回按钮
                activityThis.finish();
                break;
            case R.id.shop_scan_code:                      //二维码扫描按钮
                try {
                    BaseTool.goActivityNoData(mContext, ScanCodeActivity.class);
                }catch (Exception e){
                    PgyCrashManager.reportCaughtException(activityThis,e);
                }
                break;
            case R.id.shop_input:                         //搜索框
                //ToastUtil.makeText(mContext, "跳转到搜索界面", Toast.LENGTH_SHORT).show();
                try {
                    BaseTool.goActivityNoData(mContext, SearchActivity.class);
                }catch (Exception e){
                    PgyCrashManager.reportCaughtException(activityThis,e);
                }
                break;
            case R.id.shop_car_fl:                       //跳转购物车界面
                //ToastUtil.makeText(mContext, "跳转到购物车界面", Toast.LENGTH_SHORT).show();
                try {
                    BaseTool.goActivityNoData(mContext, CartActivity.class);
                }catch (Exception e){
                    PgyCrashManager.reportCaughtException(activityThis,e);
                }
                break;
            case R.id.shop_order_fl:
                try {
                    BaseTool.goActivityNoData(mContext, MyselectedActivity.class);
                }catch (Exception e){
                    PgyCrashManager.reportCaughtException(activityThis,e);
                }
                break;
        }
    }
}
