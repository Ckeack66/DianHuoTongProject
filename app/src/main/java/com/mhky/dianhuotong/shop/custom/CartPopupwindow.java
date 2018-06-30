package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.Popupwindow3Adapter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.GoodsSkuInfo;
import com.mhky.dianhuotong.shop.precenter.CartOpratePresenter;
import com.mhky.dianhuotong.shop.shopif.CartOprateIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private EditText editNumber;
    private TextView textViewGoodsNumber;
    private TextView textViewOk;
    private TextView textViewPrice;
    private TextView textViewTitleText;
    private TextView textViewNumberTitle;
    private TextView textViewFloght;
    private TextView textViewTips;
    private CartOpratePresenter cartOpratePresenter;
    private TextView textViewLine;
    private TextView textViewLine1;
    private List<GoodsSkuInfo> goodsSkuInfoList;
    private String[] typeName;
    private GoodsInfo goodsInfo;
    private boolean isEditListener = false;
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
        editNumber = view.findViewById(R.id.cart_popup_numbers);
        textViewGoodsNumber = view.findViewById(R.id.cart_popup_goods_number);
        textViewOk = view.findViewById(R.id.cart_popup_ok);
        textViewPrice = view.findViewById(R.id.cart_popup_price);
        textViewTitleText = view.findViewById(R.id.cart_popup_title_txt);
        imageViewGoods = view.findViewById(R.id.cart_popup_img);
        textViewNumberTitle = view.findViewById(R.id.cart_popup_number_title);
        textViewFloght = view.findViewById(R.id.cart_popup_type);
        textViewTips = view.findViewById(R.id.cart_popup_tip);
        textViewLine = view.findViewById(R.id.cart_popup_line);
        textViewLine1 = view.findViewById(R.id.cart_popup_line1);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setTouchable(true);
//        setClippingEnabled(false);
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
        editNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                BaseTool.logPrint("aaaa", "执行----s" + selectNUmber);
                try {
                    if (isEditListener) {
                        isEditListener = false;
                        if (selectNUmber != -1) {
                            if (!TextUtils.isEmpty(s) && Integer.valueOf(s.toString()) > goodsNumberMax) {
                                textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
                                textViewOk.setClickable(true);
                                goodsNumber = goodsNumberMax;
                                editNumber.setText(goodsNumberMax + "");
                                BaseTool.logPrint("aaaa", "执行----1" + s.toString());
                            } else if (!TextUtils.isEmpty(s) && Integer.valueOf(s.toString()) > 0) {
                                textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
                                textViewOk.setClickable(true);
                                goodsNumber = Integer.valueOf(s.toString());
                                editNumber.setText(String.valueOf(goodsNumber));
                                BaseTool.logPrint("aaaa", "执行----2" + s.toString());
                            } else {
                                textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                                textViewOk.setClickable(false);
                                goodsNumber = 0;
                                BaseTool.logPrint("aaaa", "执行----3" + s.toString());
                            }
                            editNumber.requestFocus();
                            editNumber.setSelection(editNumber.getText().length());
                            isEditListener = true;
                            BaseTool.logPrint("aaaa", "执行----4" + s.toString());
                        } else if (Integer.valueOf(s.toString()) != 0) {
                            BaseTool.logPrint("aaaa", "执行----5" + s.toString());
                            textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                            textViewOk.setClickable(false);
                            editNumber.setText(String.valueOf(0));
                            isEditListener = true;
                        } else {
                            BaseTool.logPrint("aaaa", "执行----6" + s.toString());
                            textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                            textViewOk.setClickable(false);
                            editNumber.setText(String.valueOf(0));
                            isEditListener = false;
                        }
                    }else {
                        BaseTool.logPrint("aaaa", "执行----7" + s.toString());
                    }

                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(mContext, e);
                }
            }
        });
        setEditState(editNumber,false);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        textViewGoodsNumber.setText("库存：");
        textViewPrice.setText("￥0.00");
        selectNUmber = -1;
        goodsNumber = 0;
        goodsNumberMax = 0;
        editNumber.setText("0");
        textViewTitleText.setText(goodsInfo.getTitle());
        String[] imageDate = goodsInfo.getPicture().split(",");
        if (imageDate != null && imageDate.length > 0) {
            Picasso.get().load(imageDate[0]).into(imageViewGoods);
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
                BaseTool.logPrint("aaaa", "执行----8" + position+"------------"+selectNUmber);
                if (position == selectNUmber) {
                    setEditState(editNumber,false);
                    selectNUmber = -1;
                    editNumber.setText(goodsNumber + "");
                    textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                    textViewGoodsNumber.setText("库存：");
                    textViewPrice.setText("￥0.00");
                } else {
                    setEditState(editNumber,true);
                    editNumber.requestFocus();
                    editNumber.setSelection(editNumber.getText().length());
                    isEditListener = true;
                    selectNUmber = position;
                    BaseTool.logPrint("aaaa", "执行----9" + position+"------------"+selectNUmber);
                    goodsNumberMax = goodsSkuInfoList.get(position).getStock();
                    textViewGoodsNumber.setText("库存：" + goodsNumberMax);
                    if (goodsNumber > goodsSkuInfoList.get(selectNUmber).getBatchNums()) {
                        textViewPrice.setText("￥" + goodsSkuInfoList.get(selectNUmber).getWholesalePrice() / 100);
                    } else {
                        textViewPrice.setText("￥" + goodsSkuInfoList.get(selectNUmber).getRetailPrice() / 100);
                    }
                }
                //ToastUtil.makeText(mContext, "选择了" + position + "-----" + selectNUmber, Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    private void setEditState(EditText editText, boolean editable){
        if (!editable) { // disable editing password
            editText.setFocusable(false);
            editText.setFocusableInTouchMode(false); // user touches widget on phone with touch screen
            editText.setClickable(false); // user navigates with wheel and selects widget
        } else { // enable editing of password
            editText.setFocusable(true);
            editText.setFocusableInTouchMode(true);
            editText.setClickable(true);
        }
    }
    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {
                case R.id.cart_popup_reduce:
                    if (goodsNumber > 1 && selectNUmber != -1) {
                        goodsNumber--;
                    } else {
                        return;
                    }
                    if (goodsNumber > goodsSkuInfoList.get(selectNUmber).getBatchNums()) {
                        textViewPrice.setText("￥" + goodsSkuInfoList.get(selectNUmber).getWholesalePrice() / 100);
                    } else {
                        textViewPrice.setText("￥" + goodsSkuInfoList.get(selectNUmber).getRetailPrice() / 100);
                        textViewTips.setVisibility(View.GONE);
                    }
                    if (goodsNumber > 0) {
                        textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
                        textViewOk.setClickable(true);
                    } else {
                        textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                        textViewOk.setClickable(false);
                    }
                    break;
                case R.id.cart_popup_plus:
                    if (goodsNumber < goodsNumberMax && selectNUmber != -1) {
                        goodsNumber++;
                    } else {
                        return;
                    }
                    if (goodsNumber > goodsSkuInfoList.get(selectNUmber).getBatchNums()) {
                        textViewPrice.setText("￥" + goodsSkuInfoList.get(selectNUmber).getWholesalePrice() / 100);
                        textViewTips.setVisibility(View.VISIBLE);
                        textViewTips.setText("当前已达到批发数量" + goodsSkuInfoList.get(selectNUmber).getBatchNums() + ",将以批发价" + "￥" + goodsSkuInfoList.get(selectNUmber).getWholesalePrice() / 100 + "进行结算");
                    } else {
                        textViewPrice.setText("￥" + goodsSkuInfoList.get(selectNUmber).getRetailPrice() / 100);
                    }
                    if (goodsNumber > 0) {
                        textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
                        textViewOk.setClickable(true);
                    } else {
                        textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                        textViewOk.setClickable(false);
                    }
                    break;
                case R.id.cart_popup_ok:
                    if (BaseApplication.getInstansApp().getLoginRequestInfo() != null) {
                        if (goodsNumber > 0 && selectNUmber != -1) {
                            Map map = new HashMap();
                            map.put("buyerId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
                            map.put("goodsId", goodsInfo.getId());
                            map.put("skuId", goodsSkuInfoList.get(selectNUmber).getId());
                            map.put("amount", goodsNumber);
                            map.put("checked", true);
                            cartOpratePresenter.addCart(map);
                            textViewOk.setBackgroundColor(Color.parseColor("#BFBFBF"));
                            textViewOk.setClickable(false);
                        } else {
                            ToastUtil.makeText(mContext, "请选择要加购的商品", Toast.LENGTH_SHORT).show();
                        }

                    }
                    break;
            }
            editNumber.setText(goodsNumber + "");
            editNumber.setSelection(editNumber.getText().length());
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        }

    }

    @Override
    public void addCartSucess(int code, String result) {
        try {
            if (code == 204) {
                ToastUtil.makeText(mContext, "加购成功！", Toast.LENGTH_SHORT).show();
                BaseApplication.getInstansApp().setUpdateCart(true);
                dismiss();
            } else {
                ToastUtil.makeText(mContext, "加购失败-" + code, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        } finally {
            textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
            textViewOk.setClickable(true);
        }

    }

    @Override
    public void addCartFaild(int code, String result) {
        ToastUtil.makeText(mContext, "加购失败-" + code, Toast.LENGTH_SHORT).show();
        textViewOk.setBackgroundColor(Color.parseColor("#04c1ab"));
        textViewOk.setClickable(true);
    }

    @Override
    public void deleteCartSucess(int code, String result) {

    }

    @Override
    public void deleteCartFaild(int code, String result) {

    }

    @Override
    public void getCartSucess(int code, String result, int type) {

    }

    @Override
    public void getCartFaild(int code, String result, int type) {

    }

    @Override
    public void alterCartSucess(int code, String result, int type) {

    }

    @Override
    public void alterCartFaild(int code, String result, int type) {

    }

    @Override
    public void getSkuSucess(int code, String result) {
        try {
            if (code == 200) {
                BaseTool.logPrint(TAG, "getSkuSucess: ------" + result);
                if (result != null && !"".equals(result)) {
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
                    } else {
                        textViewOk.setText("此商品暂不支持您所在地区购买~");
                        textViewPrice.setVisibility(View.GONE);
                        textViewGoodsNumber.setVisibility(View.GONE);
                        tagFlowLayout.setVisibility(View.GONE);
                        textViewNumberTitle.setVisibility(View.GONE);
                        imageViewReduce.setVisibility(View.GONE);
                        imageViewPlus.setVisibility(View.GONE);
                        editNumber.setVisibility(View.GONE);
                        textViewFloght.setVisibility(View.GONE);
                        textViewLine.setVisibility(View.GONE);
                        textViewLine1.setVisibility(View.GONE);
                    }
                } else {
                    ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
                }

            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(mContext, e);
        }

    }

    @Override
    public void getSkuFaild(int code, String result) {

    }
}
