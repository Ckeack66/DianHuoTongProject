package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.administrator.viewexplosion.Utils;
import com.gyf.barlibrary.ImmersionBar;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.bean.ShopInfo;
import com.mhky.dianhuotong.shop.bean.ShopTransferInfo;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.precenter.CompanyPrecenter;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.ShopPresenter;
import com.mhky.dianhuotong.shop.shopif.CompanyIF;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.ShopIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

public class GoodsActivity extends BaseActivity implements GoodsIF ,ShopIF,CompanyIF{
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
    @BindView(R.id.goods_info_pzwh)
    TextView textViewPzwh;
    @BindView(R.id.banner_main_accordion)
    BGABanner bgaBanner;
    @BindView(R.id.goods_shop_name)
    TextView textViewShopName;
    @BindView(R.id.goods_base_addcart)
    TextView textViewAddCart;
    @BindView(R.id.goods_use_time)
    TextView textViewExp;
    @BindView(R.id.go_cart)
    TextView textViewGoCart;
    @BindView(R.id.goods_shop_img)
    ImageView imageViewLogo;
    @BindView(R.id.goods_base_transfer)
    TextView textViewTransfer;
    private SearchSGoodsBean.ContentBean goodsInfoBase;
    private GoodsInfo goodsInfo;
    private Bundle bundle;
    private Context mContext;
    private GoodsPrecenter goodsPrecenter;
    private String goodsDes;
    private String goodsIns;
    private CartPopupwindow cartPopupwindow;
    private boolean isFirst = true;
    private String goodsId;
    private ShopPresenter shopPresenter;
    private CompanyPrecenter companyPrecenter;
    private static final String TAG = "GoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods);
        mContext = this;
        ButterKnife.bind(this);
        try {
            inIt();
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (cartPopupwindow!=null&&cartPopupwindow.isShowing()){
            cartPopupwindow.dismiss();
        }
    }

    private void inIt() {
        ImmersionBar.with(this).fitsSystemWindows(true).statusBarColor("#ffffff").statusBarDarkFont(true).init();
        goodsPrecenter = new GoodsPrecenter(this);
        shopPresenter=new ShopPresenter(this);
        companyPrecenter = new CompanyPrecenter(this);
        if (getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            goodsId = bundle.getString("id");
            //goodsInfoBase = (SearchSGoodsBean.ContentBean) bundle.getSerializable("getgoodsinfo");
            if (goodsId != null) {
                goodsPrecenter.getGoodsInfo(goodsId);
//                textViewTitle.setText(goodsInfoBase.getTitle());
//                textViewGoodsPrice.setText(String.valueOf(goodsInfoBase.getPrice() / 100));
//                textViewGoodsName.setText(goodsInfoBase.getName());
//                textViewGoodsCompany.setText(goodsInfoBase.getManufacturer());
//                textViewGoodsGuige.setText(goodsInfoBase.getModel());
//                textViewPzwh.setText(goodsInfoBase.getApprovalNumber());
//                textViewUseTime.setText(goodsInfoBase.getExpiryDate());
//                textViewShopName.setText("");
            } else {
                ToastUtil.makeText(mContext, "数据解析错误", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick(R.id.goods_base_back)
    void back() {
        finish();
    }

    @OnClick(R.id.goods_base_go_more)
    void goGoodsMore() {
        try {
            if (goodsDes != null || goodsInfo != null) {
                Bundle bundle = new Bundle();
                bundle.putString("des", goodsDes);
                bundle.putString("ins", goodsIns);
                BaseTool.goActivityWithData(this, GoodsInfoActivity.class, bundle);
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }


    }

    @OnClick(R.id.goods_base_go_shop)
    void goGoodsShop() {
        try {
            if (goodsInfo != null && goodsInfo.getShopInfo().getId() != null) {
                Bundle bundle = new Bundle();
                bundle.putString("shopid", goodsInfo.getShopInfo().getId());
                BaseTool.goActivityWithData(this, ShopActivity.class, bundle);
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @OnClick(R.id.goods_base_addcart)
    void addCart() {
        try {
            if (goodsId != null) {
                goodsPrecenter.getGoodsInfo(goodsId + "");
            } else {
                ToastUtil.makeText(mContext, "数据解析错误", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @OnClick(R.id.go_cart_ll)
    void goCart() {
        BaseTool.goActivityNoData(this,CartActivity.class);
    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    if (goodsInfo.getAppDescription() != null) {
                        goodsDes = goodsInfo.getAppDescription();
                    }
                    if (goodsInfo.getInstruction() != null) {
                        goodsIns = goodsInfo.getInstruction();
                    }
                    if (goodsInfo.getTitle() != null) {
                        textViewTitle.setText(goodsInfo.getTitle());
                    }
                    textViewGoodsPrice.setText(String.valueOf(goodsInfo.getPrice() / 100));
                    if (goodsInfo.getName() != null) {
                        textViewGoodsName.setText(goodsInfo.getName());
                    }
                    if (goodsInfo.getManufacturer() != null) {
                        textViewGoodsCompany.setText(goodsInfo.getManufacturer());
                    }
                    if (goodsInfo.getModel() != null) {
                        textViewGoodsGuige.setText(goodsInfo.getModel());
                    }
                    if (goodsInfo.getApprovalNumber() != null) {
                        textViewPzwh.setText(goodsInfo.getApprovalNumber());
                    }
                    if (goodsInfo.getShopInfo() != null && goodsInfo.getShopInfo().getShopName() != null) {
                        textViewShopName.setText(goodsInfo.getShopInfo().getShopName());
                    }
                    if (goodsInfo.getPicture() != null) {
                        String[] imageDate = goodsInfo.getPicture().split(",");
                        initImageBaner(Arrays.asList(imageDate));
                    }
                    if (goodsInfo.getExpiryDate() != null) {
                        textViewExp.setText(goodsInfo.getExpiryDate().toString());
                    }
                    if (goodsInfo.getShopInfo()!=null&&!TextUtils.isEmpty(goodsInfo.getShopInfo().getId())){
                        companyPrecenter.getCompanyTansferInfo(goodsInfo.getShopInfo().getId());
                    }
                    if (!isFirst) {
                        cartPopupwindow = new CartPopupwindow(this, goodsInfo);
                        cartPopupwindow.showAtLocation(textViewAddCart, Gravity.BOTTOM, 0, 0);
                    } else {
                        isFirst = false;
                    }

                }
              shopPresenter.getShopInfo(goodsInfo.getShopInfo().getId());
            }
        } catch (Exception e) {
            ToastUtil.makeText(this, "数据解析错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {

    }

    //初始化轮播图
    private void initImageBaner(List<?> list) {
        try {
            if (list!=null&&list.size()>0){
                bgaBanner.setAdapter(new BGABanner.Adapter() {
                    @Override
                    public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                        Uri uri = Uri.parse((String) model);
                        Picasso.get().load(uri).fit().into((ImageView) itemView);
                    }
                });

                bgaBanner.setAutoPlayAble(true);
                bgaBanner.setData(list, new ArrayList<String>());
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @Override
    public void getShopInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                ShopInfo shopInfo = JSON.parseObject(result, ShopInfo.class);
                if (shopInfo.getLogo() != null) {
                    Picasso.get().load(shopInfo.getLogo()).resize(Utils.dp2Px(40),Utils.dp2Px(40)).into(imageViewLogo);
                }
                textViewShopName.setText(shopInfo.getName());
            }
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }

    }

    @Override
    public void getShopInfoFailed(int code, String result) {
        try {

        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    @Override
    public void getShopTypeSuccess(int code, String result) {
        try {

        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    @Override
    public void getShopTypeFailed(int code, String result) {
        try {

        }catch (Exception e){
            PgyCrashManager.reportCaughtException(this,e);
        }
    }

    @Override
    public void getCompanyCredentialSucess(int code, String result) {

    }

    @Override
    public void getCompanyCredentialFaild(int code, String result) {

    }

    @Override
    public void getCompanyTansferSucess(int code, String result) {
        try {
            if (code == 200) {
                ShopTransferInfo shopTransferInfo = JSON.parseObject(result, ShopTransferInfo.class);
                if (shopTransferInfo != null) {
                    if (shopTransferInfo.getSendAccount() == 0) {
                        textViewTransfer.setText("快递配送，全场免邮。");
                    } else {
                        BigDecimal bigDecimal=new BigDecimal(String.valueOf(shopTransferInfo.getSendAccount()));
                        BigDecimal bigDecimal1=new BigDecimal(String.valueOf(shopTransferInfo.getFreight()));
                        BigDecimal bigDecimal2=new BigDecimal("100");
                        textViewTransfer.setText("快递配送，全国（除港澳台）满" + bigDecimal.divide(bigDecimal2).doubleValue() + "元免邮，不足将支付"+bigDecimal1.divide(bigDecimal2).doubleValue()+"元运费");
                    }
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void getCompanyTansferFaild(int code, String result) {

    }
}
