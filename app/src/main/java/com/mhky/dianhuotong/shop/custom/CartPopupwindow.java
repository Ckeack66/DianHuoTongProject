package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.Popupwindow3Adapter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.GoodsSkuInfo;
import com.mhky.dianhuotong.shop.precenter.CartOpratePresenter;
import com.mhky.dianhuotong.shop.shopif.CartOprateIF;
import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Administrator on 2018/4/25.
 */

public class CartPopupwindow extends PopupWindow implements View.OnClickListener, CartOprateIF {
    private ImageView imageViewDismiss;
    private TagFlowLayout tagFlowLayout;
    private LayoutInflater layoutInflater;
    private Context mContext;
    private int selectNUmber = -1;
    private int goodsNumber = 0;
    private int goodsNumberMax = 0;
    private ImageView imageViewReduce;
    private ImageView imageViewPlus;
    private ImageView imageViewGoods;
    private TextView textViewNumber;
    private TextView textViewGoodsNumber;
    private TextView textViewOk;
    private TextView textViewPrice;
    private TextView textViewTitleText;
    private CartOpratePresenter cartOpratePresenter;
    private List<GoodsSkuInfo> goodsSkuInfoList;
    private String[] typeName;
    private GoodsInfo goodsInfo;
    private static final String TAG = "CompanyPopupwindow";

    public CartPopupwindow(final Context context, GoodsInfo goodsInfo1) {
        super(context);
        mContext = context;
        goodsInfo = goodsInfo1;
        View view = View.inflate(context, R.layout.popupwindow_cart, null);
        setContentView(view);
        layoutInflater = LayoutInflater.from(context);
        imageViewDismiss = view.findViewById(R.id.cart_popup_dismiss);
        tagFlowLayout = view.findViewById(R.id.cart_popup_type_layout);
        imageViewReduce = view.findViewById(R.id.cart_popup_reduce);
        imageViewPlus = view.findViewById(R.id.cart_popup_plus);
        textViewNumber = view.findViewById(R.id.cart_popup_numbers);
        textViewGoodsNumber = view.findViewById(R.id.cart_popup_goods_number);
        textViewOk = view.findViewById(R.id.cart_popup_ok);
        textViewPrice = view.findViewById(R.id.cart_popup_price);
        textViewTitleText = view.findViewById(R.id.cart_popup_title_txt);
        imageViewGoods = view.findViewById(R.id.cart_popup_img);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(false);
        setTouchable(true);
        setClippingEnabled(false);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(0));
        imageViewReduce.setOnClickListener(this);
        imageViewPlus.setOnClickListener(this);
        textViewOk.setOnClickListener(this);
        imageViewDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cartOpratePresenter = new CartOpratePresenter(this);
        cartOpratePresenter.getSku(String.valueOf(goodsInfo.getId()));
        Log.d(TAG, "CartPopupwindow: ");
        //setTagFlowLayout(new String[]{"到鞥", "aaaa", "bbbbbb", "ssddccfvg", "hisiksjihjsihai", "ssskdjdjiiooo", "到鞥", "aaaa", "bbbbbb", "ssddccfvg", "hisiksjihjsihai", "ssskdjdjiiooo", "到鞥", "aaaa", "bbbbbb", "ssddccfvg", "hisiksjihjsihai", "ssskdjdjiiooo"});
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        textViewGoodsNumber.setText("库存：");
        textViewPrice.setText("￥0.00");
        selectNUmber = -1;
        goodsNumber = 0;
        goodsNumberMax = 0;
        textViewNumber.setText("0");
        textViewTitleText.setText(goodsInfo.getTitle());
        String[] imageDate = goodsInfo.getPicture().split(",");
        if (imageDate != null && imageDate.length > 0) {
            Picasso.with(mContext).load(imageDate[0]).into(imageViewGoods);
        }

    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    private void setTagFlowLayout(String[] strings) {
        TagAdapter<String> tagAdapter = new TagAdapter<String>(strings) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) layoutInflater.inflate(R.layout.view_textview,
                        tagFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        };
        tagFlowLayout.setAdapter(tagAdapter);
        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                goodsNumber = 0;
                textViewNumber.setText(goodsNumber + "");
                textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                textViewNumber.setClickable(false);
                textViewGoodsNumber.setText("库存：");
                textViewPrice.setText("￥0.00");
                if (position == selectNUmber) {
                    selectNUmber = -1;
                } else {
                    selectNUmber = position;
                    goodsNumberMax = goodsSkuInfoList.get(position).getStock();
                    textViewGoodsNumber.setText("库存：" + goodsNumberMax);
                    textViewPrice.setText("￥" + goodsSkuInfoList.get(position).getRetailPrice() / 100);
                }
                //ToastUtil.makeText(mContext, "选择了" + position + "-----" + selectNUmber, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_popup_reduce:
                if (goodsNumber > 0) {
                    goodsNumber--;
                }
                break;
            case R.id.cart_popup_plus:
                if (goodsNumber < goodsNumberMax) {
                    goodsNumber++;
                }
                break;
            case R.id.cart_popup_ok:
                if (BaseApplication.getInstansApp().getLoginRequestInfo()!=null){
                    HttpParams httpParams=new HttpParams();
                    httpParams.put("buyerId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
                    httpParams.put("goodsId",goodsInfo.getId());
                    httpParams.put("skuId","");
                    httpParams.put("amount",10);
                    httpParams.put("checked",true);
                    cartOpratePresenter.addCart(httpParams);
                }
                ToastUtil.makeText(mContext, "进入结算系统", Toast.LENGTH_SHORT).show();
                break;
        }
        if (goodsNumber > 0) {
            textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
            textViewOk.setClickable(true);
        } else {
            textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
            textViewOk.setClickable(false);
        }
        textViewNumber.setText(goodsNumber + "");
    }

    @Override
    public void addCartSucess(int code, String result) {

    }

    @Override
    public void addCartFaild(int code, String result) {

    }

    @Override
    public void deleteCartSucess(int code, String result) {

    }

    @Override
    public void deleteCartFaild(int code, String result) {

    }

    @Override
    public void getCartSucess(int code, String result) {

    }

    @Override
    public void getCartFaild(int code, String result) {

    }

    @Override
    public void alterCartSucess(int code, String result) {

    }

    @Override
    public void alterCartFaild(int code, String result) {

    }

    @Override
    public void getSkuSucess(int code, String result) {
        if (code == 200) {
            goodsSkuInfoList = JSON.parseArray(result, GoodsSkuInfo.class);
            ArrayList<String> arrayList = new ArrayList();
            if (goodsSkuInfoList != null && goodsSkuInfoList.size() > 0) {
                for (int a = 0; a < goodsSkuInfoList.size(); a++) {
                    String s = "";
                    for (int b = 0; b < goodsSkuInfoList.get(a).getSalePropertyOptions().size(); b++) {
                        s = s + goodsSkuInfoList.get(a).getSalePropertyOptions().get(b).getValue();
                    }
                    arrayList.add(s);
                }
                typeName = arrayList.toArray(new String[0]);
                setTagFlowLayout(typeName);
            }
        }
    }

    @Override
    public void getSkuFaild(int code, String result) {

    }
}
