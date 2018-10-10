package com.mhky.dianhuotong.shop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview1Adapter;
import com.mhky.dianhuotong.shop.adapter.AllGoodsListview2Adapter;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsCategories;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.precenter.AllGoosPrecenter;
import com.mhky.dianhuotong.shop.shopif.GoodsCategoriesIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * 所有商品Activity
 */

public class AllGoodsActivity extends BaseActivity implements AdapterView.OnItemClickListener, AllGoodsListview2Adapter.OnItemGridviewClickListener ,GoodsCategoriesIF{

    @BindView(R.id.all_goods_listview1)
    ListView listView1;
    @BindView(R.id.all_goods_listview2)
    ListView listView2;
    @BindView(R.id.all_goods_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    private BGABanner bgaBanner_i;

    private List<GoodsCategories.MenuAdBean> bannerList = new ArrayList<>();                        //轮播图需要加载的List
    private AllGoodsListview1Adapter all_goods_listview1;
    private AllGoodsListview2Adapter all_goods_listview2;
    private List<GoodsBaseInfo> allGoodsBaseInfos;                                                  //老的
    private List<GoodsCategories> goodsCategoriesList = new ArrayList<>();                          //新的
    private AllGoosPrecenter allGoosPrecenter;
    private int Itype = 0;                                      //一级类目被点击的位置
    private int IItype = 0;                                     //二级类目被点击的位置
    private int IIItype = 0;                                    //三级类目被点击的位置
    private String II_TYPE = "102";
    private String III_TYPE = "103";
    private String BANNER = "104";
    private static final String TAG = "AllGoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_goods);
        bgaBanner_i = findViewById(R.id.bgabanner_i);
        ButterKnife.bind(this);
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        dianHuoTongShopTitleBar.setActivity(this);

        allGoosPrecenter = new AllGoosPrecenter(this);
        if (BaseApplication.getInstansApp().getGoodsCategories() != null) {
            goodsCategoriesList = BaseApplication.getInstansApp().getGoodsCategories();
            bannerList = goodsCategoriesList.get(0).getMenuAd();
            setListData(goodsCategoriesList);
            initBanner(bannerList);
        } else {
            HttpParams httpParams = new HttpParams();
            httpParams.put("type","MOBILE");
            allGoosPrecenter.getGoodsCategeries(httpParams);
        }

    }

    private void initBanner(List<GoodsCategories.MenuAdBean> list){
        if (list != null && list.size() > 0){
            bgaBanner_i.setAutoPlayAble(list.size() > 1);
            bgaBanner_i.setData(list,new ArrayList<String>());
            bgaBanner_i.setAdapter(new BGABanner.Adapter() {
                @Override
                public void fillBannerItem(BGABanner banner, View itemView, @Nullable Object model, int position) {
                    GoodsCategories.MenuAdBean menuAdBean1 = (GoodsCategories.MenuAdBean)model;
                    Picasso.get().load(menuAdBean1.getImage()).error(R.drawable.default_pill_case).into((ImageView) itemView);
                }
            });
            bgaBanner_i.setDelegate(new BGABanner.Delegate() {
                @Override
                public void onBannerItemClick(BGABanner banner, View itemView, @Nullable Object model, int position) {
                    GoodsCategories.MenuAdBean menuAdBean2 = (GoodsCategories.MenuAdBean)model;
                    skipByType(menuAdBean2.getType(),BANNER);
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //BaseTool.logPrint(TAG, "点击了onItemClick: -------" + position);
        switch (parent.getId()) {
            case R.id.all_goods_listview1:
                try {
                    Itype = position;
                    all_goods_listview1.setIndexColor(position);
                    if (goodsCategoriesList.get(position) != null) {
                        all_goods_listview2.updateData(goodsCategoriesList.get(position).getItems());
                    } else {
                        all_goods_listview2.updateData(null);
                    }
                    initBanner(goodsCategoriesList.get(position).getMenuAd());
                    listView2.setSelection(0);
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(this, e);
                }
                break;
            case R.id.all_goods_listview2:
                try {
                    IItype = position;
                    skipByType(goodsCategoriesList.get(Itype).getItems().get(IItype).getItemData().getType(),II_TYPE);
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(this, e);
                }
                break;
            default:
                BaseTool.logPrint(TAG, "onItemClick: " + parent);
        }
    }

    /**
     * 获取商品类目成功
     * @param code
     * @param result
     */
    @Override
    public void getGoodsCategoriesSuccess(int code, String result) {
        if (code == 200){
            goodsCategoriesList = JSON.parseArray(result, GoodsCategories.class);
            BaseApplication.getInstansApp().setGoodsCategories(goodsCategoriesList);
            setListData(goodsCategoriesList);
        }
    }

    @Override
    public void getGoodsCategoriesFailed(int code, String result) {

    }

    private void setListData(List<GoodsCategories> goodsCategoriesList) {
        try {
            if (goodsCategoriesList != null) {
                all_goods_listview1 = new AllGoodsListview1Adapter(goodsCategoriesList, this);
                listView1.setAdapter(all_goods_listview1);
                listView1.setOnItemClickListener(this);

                all_goods_listview2 = new AllGoodsListview2Adapter(goodsCategoriesList.get(0).getItems(), this);
                all_goods_listview2.setOnItemGridviewClickListener(this);
                listView2.setAdapter(all_goods_listview2);
                listView2.setOnItemClickListener(this);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void onclickItem(int positionParent, int positionChild) {
        try {
            BaseTool.logPrint(TAG, "onclickItem: ----" + positionParent + "------" + positionChild);
            IItype = positionParent;
            IIItype = positionChild;
            skipByType(goodsCategoriesList.get(Itype).getItems().get(IItype).getSubitemData().get(IIItype).getType(),III_TYPE);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    /**
     * 根据type  判断跳转类型--1
     * @param type
     */
    private void skipByType(String type , String categeriesType){
        switch (type) {
            case "none"://无操作
                break;
            case "url"://链接地址

                break;
            case "keyword"://关键字
                Bundle bundleKeyword = new Bundle();
                bundleKeyword.putString("type", "104");
                if (categeriesType.equals(BANNER)){
                    bundleKeyword.putString("goodsnm", bannerList.get(bgaBanner_i.getCurrentItem()).getLink());
                }else if(categeriesType.equals(II_TYPE)){
                    bundleKeyword.putString("goodsnm", goodsCategoriesList.get(Itype).getItems().get(IItype).getItemData().getData());
                }else if (categeriesType.equals(III_TYPE)){
                    bundleKeyword.putString("goodsnm", goodsCategoriesList.get(Itype).getItems().get(IItype).getSubitemData().get(IIItype).getLink());
                }
                BaseTool.goActivityWithData(this, SearchGoodsActivity.class, bundleKeyword);
                break;
            case "special"://专题编号

                break;
            case "theme"://商城活动编号

                break;
            case "goods"://商品编号
                Bundle bundleGoods = new Bundle();
                if (categeriesType.equals(BANNER)){
                    bundleGoods.putSerializable("id", bannerList.get(bgaBanner_i.getCurrentItem()).getLink());
                }else if(categeriesType.equals(II_TYPE)){
                    bundleGoods.putSerializable("id", goodsCategoriesList.get(Itype).getItems().get(IItype).getItemData().getData());
                }else if (categeriesType.equals(III_TYPE)){
                    bundleGoods.putSerializable("id", goodsCategoriesList.get(Itype).getItems().get(IItype).getSubitemData().get(IIItype).getLink());
                }
                BaseTool.goActivityWithData(this, GoodsActivity.class, bundleGoods);
                break;
            case "store"://店铺编号
                Bundle bundleStore = new Bundle();
                if (categeriesType.equals(BANNER)){
                    bundleStore.putString("shopid", bannerList.get(bgaBanner_i.getCurrentItem()).getLink());
                }else if(categeriesType.equals(II_TYPE)){
                    bundleStore.putString("shopid", goodsCategoriesList.get(Itype).getItems().get(IItype).getItemData().getData());
                }else if (categeriesType.equals(III_TYPE)){
                    bundleStore.putString("shopid", goodsCategoriesList.get(Itype).getItems().get(IItype).getSubitemData().get(IIItype).getLink());
                }
                BaseTool.goActivityWithData(this, ShopActivity.class, bundleStore);
                break;
            case "category"://商品分类
                if(categeriesType.equals(II_TYPE)){
                    Bundle bundle = new Bundle();
                    bundle.putString("type", II_TYPE);
                    bundle.putSerializable("data", goodsCategoriesList.get(Itype).getItems().get(IItype));
                    bundle.putString("sort_name",goodsCategoriesList.get(Itype).getItems().get(IItype).getName());
                    BaseTool.goActivityWithData(this, SearchGoodsActivity.class, bundle);
                }else if (categeriesType.equals(III_TYPE)){
                    Bundle bundle = new Bundle();
                    bundle.putString("type", III_TYPE);
                    bundle.putString("data", goodsCategoriesList.get(Itype).getItems().get(IItype).getSubitemData().get(IIItype).getLink());
                    bundle.putString("sort_name",goodsCategoriesList.get(Itype).getItems().get(IItype).getSubitemData().get(IIItype).getTitle());
                    BaseTool.goActivityWithData(this, SearchGoodsActivity.class, bundle);
                }else if (categeriesType.equals(BANNER)){

                }
                break;
            case "channel"://频道编号

                break;
            case "couponGifts"://平台券礼包编号

                break;
            case "brandList"://品牌列表

                break;
            case "pointsCenter"://积分中心

                break;
            case "voucherCenter"://领券中心
                BaseTool.goActivityNoData(this, CouponActivity.class);
                break;
            case "seckillList"://秒杀列表

                break;
        }
    }

}
