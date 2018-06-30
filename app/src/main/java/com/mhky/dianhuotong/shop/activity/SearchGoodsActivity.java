package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.Popuwindow1Info;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.custom.CompanyPopupwindow;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.custom.GoodsTypePopupwindow;
import com.mhky.dianhuotong.shop.custom.SortPopupwindow;
import com.mhky.dianhuotong.shop.precenter.GetAllCompanyPresenter;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.SearchGoodsPresenter;
import com.mhky.dianhuotong.shop.shopif.GetAllCompanyIF;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yyydjk.library.DropDownMenu;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGoodsActivity extends BaseActivity implements SearchGoodsIF, GetAllCompanyIF, GoodsTypePopupwindow.OnClickPopupwindow1ItemListener, SortPopupwindow.OnClickPopupwindow2ItemListener, CompanyPopupwindow.OnClickPopupwindow3ItemListener, GoodsIF {
    @BindView(R.id.search_recyclelistview)
    RecyclerView recyclerView;
    @BindView(R.id.goods_base_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.base_tips)
    RelativeLayout relativeLayoutTips;
    @BindView(R.id.goods_base_choose_txt1)
    TextView textViewChoose1;
    @BindView(R.id.goods_base_choose_txt2)
    TextView textViewChoose2;
    @BindView(R.id.goods_base_choose_txt3)
    TextView textViewChoose3;
    @BindView(R.id.goods_base_choose_txt4)
    TextView textViewChoose4;
    @BindView(R.id.goods_base_choose_img1)
    ImageView imageViewChoose1;
    @BindView(R.id.goods_base_choose_img2)
    ImageView imageViewChoose2;
    @BindView(R.id.goods_base_choose_img3)
    ImageView imageViewChoose3;
    @BindView(R.id.goods_base_choose_img4)
    ImageView imageViewChoose4;
    @BindView(R.id.goods_base_choose_tab1)
    RelativeLayout tabI;
    @BindView(R.id.search_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    private SearchGoodsAdpter searchGoodsAdpter;
    private SearchGoodsPresenter searchGoodsPresenter;
    private String type;
    private Bundle bundle;
    private String type3;
    private String typeSort;
    private List<GoodsBaseInfo> allGoodsBaseInfos;
    private GoodsBaseInfo.ChildrenBeanX childrenBeanX;
    private List<Popuwindow1Info> popuwindow1InfoList;
    private int number = 0;
    private int chooseOldNumber = -1;
    private boolean tabIsOpen = false;
    private GoodsTypePopupwindow goodsTypePopupwindow;
    private SortPopupwindow sortPopupwindow;
    private GetAllCompanyPresenter getAllCompanyPresenter;
    private CompanyPopupwindow companyPopupwindow;
    private AllCompanyInfo allCompanyInfo;
    private SearchSGoodsBean searchSGoodsBean;
    private Context mContext;
    private CartPopupwindow cartPopupwindow;
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private String type102Data;
    private String type105GoodsId;
    private String type106GoodsId;
    private int type106TypeId;
    private String type107Company;
    private String type107GoodsId;
    private String type104GoodName;
    private String type109Brand;

    private static final String TAG = "SearchGoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        mContext = this;
        ButterKnife.bind(this);
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideWindow();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!hideWindow()) {
                return hideWindow();
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void inIt() {
        goodsPrecenter = new GoodsPrecenter(this);
        dianHuoTongShopTitleBar.setActivity(this);
        allGoodsBaseInfos = BaseApplication.getInstansApp().getAllGoodsBaseInfos();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        setRefresh();
        searchGoodsPresenter = new SearchGoodsPresenter(this);
        bundle = getIntent().getExtras();
        type = bundle.getString("type");
        type102Data = bundle.getString("sort_name");
        if (!TextUtils.isEmpty(type102Data)) {
            textViewChoose1.setText(type102Data);
        }
        if (type != null && type.equals("102")) {
            childrenBeanX = (GoodsBaseInfo.ChildrenBeanX) bundle.getSerializable("data");
            if (childrenBeanX != null) {
                getData(getChildId(childrenBeanX.getChildren()), true, 0);
                type106GoodsId = getChildId(childrenBeanX.getChildren());
                type107GoodsId = type106GoodsId;
            }
        } else if (type != null && type.equals("103")) {
            type3 = bundle.getString("data");
            type106GoodsId = type3;
            type107GoodsId = type106GoodsId;
            if (type3 != null && !type3.equals("")) {
                getData(type3, true, 0);
            }
        } else if (type != null && type.equals("104")) {
            //搜索
            type104GoodName = bundle.getString("goodsnm");
            dianHuoTongShopTitleBar.setCenterText(type104GoodName);
            getSrarchData(true, 0);
        } else if (type != null && type.equals("109")) {
            type109Brand = bundle.getString("brandID");
            getData(null, false, 0);
        }
        popuwindow1InfoList = searchGoodsPresenter.getPopupwindowData();
        goodsTypePopupwindow = new GoodsTypePopupwindow(this, popuwindow1InfoList);
        goodsTypePopupwindow.setOutsideTouchable(false);
        goodsTypePopupwindow.setOnClickPopupwindowItemListener(this);
        sortPopupwindow = new SortPopupwindow(this, -1);
        sortPopupwindow.setOutsideTouchable(false);
        sortPopupwindow.setClickPopupwindow2ItemListener(this);
        getAllCompanyPresenter = new GetAllCompanyPresenter(this);
        getAllCompanyPresenter.getAllCompany(new HttpParams(), false);
    }

    private void setRefresh() {
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                number = 0;
                refreshLayout.setEnableLoadMore(true);
                if (type != null && type.equals("102")) {//102是二级列表
                    if (childrenBeanX != null) {
                        getData(getChildId(childrenBeanX.getChildren()), false, 1);
                    }
                } else if (type != null && type.equals("103")) {//三级子类
                    if (type3 != null && !type3.equals("")) {
                        getData(type3, false, 1);
                    }
                } else if (type != null && type.equals("104")) {
                    //搜索
                    getSrarchData(false, 1);
                } else if (type != null && type.equals("105")) {
                    //新的商家分类
                    getTypeData(type105GoodsId, false, 1);
                } else if (type != null && type.equals("106")) {
                    //排序
                    getSortData(type106GoodsId, false, 1, type106TypeId);
                } else if (type != null && type.equals("107")) {
                    //商家选择
                    getCompanyData(type107GoodsId, false, 1, type107Company);
                } else if (type != null && type.equals("108")) {
                    //筛选
                } else if (type != null && type.equals("109")) {
                    getData(null, false, 1);
                }
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (type != null && type.equals("102")) {
                    if (childrenBeanX != null) {
                        getData(getChildId(childrenBeanX.getChildren()), false, 2);
                    }
                } else if (type != null && type.equals("103")) {
                    if (type3 != null && !type3.equals("")) {
                        getData(type3, false, 2);
                    }
                } else if (type != null && type.equals("104")) {
                    //搜索
                    getSrarchData(false, 2);
                } else if (type != null && type.equals("105")) {
                    //新的商家分类
                    getTypeData(type105GoodsId, false, 2);
                } else if (type != null && type.equals("106")) {
                    //排序
                    getSortData(type106GoodsId, false, 2, type106TypeId);
                } else if (type != null && type.equals("107")) {
                    //商家选择
                    getCompanyData(type107GoodsId, false, 2, type107Company);
                } else if (type != null && type.equals("108")) {
                    //筛选
                } else if (type != null && type.equals("109")) {
                    getData(null, false, 2);
                }

            }
        });
    }

    private void getData(String childID, boolean isFirst, int refreshOrLoadmore) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", number);
        if (childID != null && !childID.equals("")) {
            httpParams.put("categoryIds", childID);
        }
        if (type104GoodName != null) {
            httpParams.put("search", type104GoodName);
        }
        if (type109Brand != null) {
            httpParams.put("brandId", type109Brand);
        }
        searchGoodsPresenter.searchGoods(httpParams, isFirst, refreshOrLoadmore);
    }

    private void getTypeData(String childID, boolean isFirst, int refreshOrLoadmore) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", number);
        if (childID != null && !childID.equals("")) {
            httpParams.put("categoryIds", childID);
        }
        if (type104GoodName != null) {
            httpParams.put("search", type104GoodName);
        }
        searchGoodsPresenter.searchGoods(httpParams, isFirst, refreshOrLoadmore);
    }

    private void getSortData(String childID, boolean isFirst, int refreshOrLoadmore, int typeId) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", number);
        if (childID != null && !childID.equals("")) {
            httpParams.put("categoryIds", childID);
        }
        if (typeId == 0) {
            httpParams.put("sort", "name,DESC");
        } else if (typeId == 1) {
            httpParams.put("sort", "createTime,DESC");
        }
        if (type107Company != null) {
            httpParams.put("shopId", type107Company);
        }
        if (type104GoodName != null) {
            httpParams.put("search", type104GoodName);
        }
        searchGoodsPresenter.searchGoods(httpParams, isFirst, refreshOrLoadmore);
    }

    private void getSrarchData(boolean isFirst, int refreshOrLoadmore) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", number);
        if (type104GoodName != null) {
            httpParams.put("search", type104GoodName);
        }
        searchGoodsPresenter.searchGoods(httpParams, isFirst, refreshOrLoadmore);
    }

    private void getCompanyData(String childID, boolean isFirst, int refreshOrLoadmore, String shopid) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", number);
        if (childID != null && !childID.equals("")) {
            httpParams.put("categoryIds", childID);
        }
        if (type106TypeId == 0) {
            httpParams.put("sort", "name,DESC");
        } else if (type106TypeId == 1) {
            httpParams.put("sort", "createTime,DESC");
        }
        if (shopid != null) {
            httpParams.put("shopId", shopid);
        }
        searchGoodsPresenter.searchGoods(httpParams, isFirst, refreshOrLoadmore);
    }

    private String getChildId(List<GoodsBaseInfo.ChildrenBeanX.ChildrenBean> childrenBeans) {
        String childId = "";
        if (childrenBeans != null) {
            for (int a = 0; a < childrenBeans.size(); a++) {
                if (a != childrenBeans.size() - 1) {
                    childId = childId + childrenBeans.get(a).getId() + ",";
                } else {
                    childId = childId + childrenBeans.get(a).getId();
                }

            }
        }
        return childId;
    }

    private void reset() {
        textViewChoose2.setText("默认排序");
        textViewChoose3.setText("全部商家");
        sortPopupwindow = null;
        companyPopupwindow = null;
        type107Company = null;
        type106GoodsId = null;
    }

    @OnClick(R.id.goods_base_choose_tab1)
    void selectTab1() {
        try {
            setTabStateTrue(1);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @OnClick(R.id.goods_base_choose_tab2)
    void selectTab2() {
        try {
            setTabStateTrue(2);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @OnClick(R.id.goods_base_choose_tab3)
    void selectTab3() {
        try {
            setTabStateTrue(3);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @OnClick(R.id.goods_base_choose_tab4)
    void selectTab4() {

    }


    private boolean hideWindow() {
        setTabStateFalse(chooseOldNumber);
        if (goodsTypePopupwindow != null && goodsTypePopupwindow.isShowing()) {
            goodsTypePopupwindow.dismiss();
            return false;
        } else if (sortPopupwindow != null && sortPopupwindow.isShowing()) {
            sortPopupwindow.dismiss();
            return false;
        } else if (companyPopupwindow != null && companyPopupwindow.isShowing()) {
            companyPopupwindow.dismiss();
            return false;
        } else if (cartPopupwindow != null && cartPopupwindow.isShowing()) {
            cartPopupwindow.dismiss();
            return false;
        } else {
            return true;
        }

    }

    private void setTabStateTrue(int newNumber) {

        if (newNumber == chooseOldNumber && tabIsOpen) {
            //setTabStateFalse(newNumber);
            hideWindow();
            return;
        } else if (newNumber == chooseOldNumber && !tabIsOpen) {
            setTabOpen(newNumber);
            return;
        } else if (newNumber != chooseOldNumber && !tabIsOpen) {
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        } else if (newNumber != chooseOldNumber && tabIsOpen) {
            hideWindow();
            //setTabStateFalse(chooseOldNumber);
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        }

    }

    private void setTabOpen(int number) {
        switch (number) {
            case 1:
                try {
                    textViewChoose1.setTextColor(getResources().getColor(R.color.color04c1ab));
                    imageViewChoose1.setImageResource(R.drawable.icon_choose_selecte);
                    if (goodsTypePopupwindow == null) {
                        if (popuwindow1InfoList != null) {
                            goodsTypePopupwindow = new GoodsTypePopupwindow(this, popuwindow1InfoList);
                            goodsTypePopupwindow.setOnClickPopupwindowItemListener(this);
                            PopupWindowCompat.showAsDropDown(goodsTypePopupwindow, tabI, 0, 0, Gravity.LEFT);
                        }
                    } else {
                        PopupWindowCompat.showAsDropDown(goodsTypePopupwindow, tabI, 0, 0, Gravity.LEFT);
                    }
                    tabIsOpen = true;
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(this, e);
                }
                break;
            case 2:
                try {
                    textViewChoose2.setTextColor(getResources().getColor(R.color.color04c1ab));
                    imageViewChoose2.setImageResource(R.drawable.icon_choose_selecte);
                    if (sortPopupwindow == null) {
                        sortPopupwindow = new SortPopupwindow(this, -1);
                        sortPopupwindow.setClickPopupwindow2ItemListener(this);
                        PopupWindowCompat.showAsDropDown(sortPopupwindow, tabI, 0, 0, Gravity.LEFT);
                    } else {
                        PopupWindowCompat.showAsDropDown(sortPopupwindow, tabI, 0, 0, Gravity.LEFT);
                    }

                    tabIsOpen = true;
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(this, e);
                }

                break;
            case 3:
                try {
                    textViewChoose3.setTextColor(getResources().getColor(R.color.color04c1ab));
                    imageViewChoose3.setImageResource(R.drawable.icon_choose_selecte);
                    if (companyPopupwindow == null && allCompanyInfo != null) {
                        companyPopupwindow = new CompanyPopupwindow(this, allCompanyInfo.getContent());
                        companyPopupwindow.setOnClickPopupwindowItemListener(this);
                        PopupWindowCompat.showAsDropDown(companyPopupwindow, tabI, 0, 0, Gravity.LEFT);
                    } else if (allCompanyInfo != null) {
                        PopupWindowCompat.showAsDropDown(companyPopupwindow, tabI, 0, 0, Gravity.LEFT);
                    }
                    tabIsOpen = true;
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(this, e);
                }
                break;
        }
    }

    private void setTabStateFalse(int number) {
        switch (number) {
            case 1:
                textViewChoose1.setTextColor(getResources().getColor(R.color.color333333));
                imageViewChoose1.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
            case 2:
                textViewChoose2.setTextColor(getResources().getColor(R.color.color333333));
                imageViewChoose2.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
            case 3:
                textViewChoose3.setTextColor(getResources().getColor(R.color.color333333));
                imageViewChoose3.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
        }

    }

    /**
     * @param code
     * @param result
     * @param isfirst
     * @param refreshOrLoadmore 0是刚进入页面,1是刷新/新条件查询，2是加载
     */

    @Override
    public void searchGoodsInfoSuccess(int code, String result, boolean isfirst, int refreshOrLoadmore) {
        //BaseTool.logPrint(TAG, "searchGoodsInfoSuccess: " + code);
        try {
            if (code == 200) {
                SearchSGoodsBean searchSGoodsBeans = JSON.parseObject(result, SearchSGoodsBean.class);
                if (refreshOrLoadmore == 0) {
                    if (searchSGoodsBeans != null && searchSGoodsBeans.getContent().size() == 0) {
                        relativeLayoutTips.setVisibility(View.VISIBLE);
                        smartRefreshLayout.setEnableLoadMore(false);
                    } else if (searchSGoodsBeans != null && searchSGoodsBeans.getContent().size() < 10) {
                        relativeLayoutTips.setVisibility(View.GONE);
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                        searchSGoodsBean = searchSGoodsBeans;
                        searchGoodsAdpter = new SearchGoodsAdpter(searchSGoodsBean.getContent(), this);
                        searchGoodsAdpter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        searchGoodsAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                try {
                                    Bundle bundle = new Bundle();
                                    bundle.putSerializable("id", searchSGoodsBean.getContent().get(position).getId() + "");
                                    BaseTool.goActivityWithData(mContext, GoodsActivity.class, bundle);
                                } catch (Exception e) {
                                    PgyCrashManager.reportCaughtException(mContext, e);
                                }

                                //ToastUtil.makeText(mContext, "点击了父控件", Toast.LENGTH_SHORT).show();
                            }
                        });
                        searchGoodsAdpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                try {
                                    BaseTool.logPrint(TAG, "onItemChildClick: ---" + position);
                                    goodsPrecenter.getGoodsInfo(String.valueOf(searchSGoodsBean.getContent().get(position).getId()));
                                } catch (Exception e) {
                                    PgyCrashManager.reportCaughtException(mContext, e);
                                }


                            }
                        });
                        recyclerView.setAdapter(searchGoodsAdpter);
                    } else if (searchSGoodsBeans != null) {
                        number++;
                        relativeLayoutTips.setVisibility(View.GONE);
                        smartRefreshLayout.setEnableLoadMore(true);
                        searchSGoodsBean = searchSGoodsBeans;
                        searchGoodsAdpter = new SearchGoodsAdpter(searchSGoodsBean.getContent(), this);
                        searchGoodsAdpter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_LEFT);
                        searchGoodsAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("id", searchSGoodsBean.getContent().get(position).getId() + "");
                                BaseTool.goActivityWithData(mContext, GoodsActivity.class, bundle);
                                //ToastUtil.makeText(mContext, "点击了父控件", Toast.LENGTH_SHORT).show();
                            }
                        });
                        searchGoodsAdpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                BaseTool.logPrint(TAG, "onItemChildClick: ---" + position);
                                goodsPrecenter.getGoodsInfo(String.valueOf(searchSGoodsBean.getContent().get(position).getId()));

                            }
                        });
                        recyclerView.setAdapter(searchGoodsAdpter);
                    }

                } else if (refreshOrLoadmore == 1) {
                    searchSGoodsBean = searchSGoodsBeans;
                    if (searchSGoodsBean != null && searchSGoodsBean.getContent().size() == 0) {
                        relativeLayoutTips.setVisibility(View.VISIBLE);
                        smartRefreshLayout.setEnableLoadMore(false);
                    } else if (searchSGoodsBean != null && searchSGoodsBean.getContent().size() < 10) {
                        relativeLayoutTips.setVisibility(View.GONE);
                        searchGoodsAdpter.setNewData(searchSGoodsBean.getContent());
                        smartRefreshLayout.finishRefresh(1000, true);
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(this, "刷新成功-没有更多数据了", Toast.LENGTH_SHORT).show();
                    } else {
                        relativeLayoutTips.setVisibility(View.GONE);
                        smartRefreshLayout.setEnableLoadMore(true);
                        searchGoodsAdpter.setNewData(searchSGoodsBean.getContent());
                        smartRefreshLayout.finishRefresh(1000, true);
                        ToastUtil.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
                        number++;
                    }
                } else if (refreshOrLoadmore == 2) {
                    if (searchSGoodsBeans != null && searchSGoodsBeans.getContent().size() == 0) {
                        smartRefreshLayout.finishLoadMore(true);
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                    } else if (searchSGoodsBeans != null && searchSGoodsBeans.getContent().size() < 10) {
                        searchGoodsAdpter.addData(searchSGoodsBeans.getContent());
                        smartRefreshLayout.finishLoadMore(true);
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                    } else {
                        searchGoodsAdpter.addData(searchSGoodsBeans.getContent());
                        smartRefreshLayout.finishLoadMore(1000, true, false);
                        number++;
                        ToastUtil.makeText(this, "加载了更多", Toast.LENGTH_SHORT).show();
                    }
                }

            } else {
                relativeLayoutTips.setVisibility(View.VISIBLE);
                if (isfirst && refreshOrLoadmore == 0) {
                    ToastUtil.makeText(this, "没有获取到数据哦~", Toast.LENGTH_SHORT).show();
                } else if (refreshOrLoadmore == 1) {
                    smartRefreshLayout.finishRefresh(1000, false);
                    ToastUtil.makeText(this, "刷新失败~", Toast.LENGTH_SHORT).show();
                } else if (refreshOrLoadmore == 2) {
                    smartRefreshLayout.finishLoadMore(false);
                    ToastUtil.makeText(this, "无法加载~", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
            ToastUtil.makeText(this, "系统异常~", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void searchGoodsInfoFailed(int code, String result, boolean isfirst, int refreshOrLoadmore) {
        BaseTool.logPrint(TAG, "searchGoodsInfoFailed: ");
        relativeLayoutTips.setVisibility(View.VISIBLE);
        if (refreshOrLoadmore == 1) {
            smartRefreshLayout.finishRefresh(1000, false);
        } else if (refreshOrLoadmore == 2) {
            smartRefreshLayout.finishLoadMore(false);
        }
        ToastUtil.makeText(this, "加载失败~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onclick(Popuwindow1Info popuwindow1Info) {
        reset();
        String text;
        if (popuwindow1Info.isHeader) {
            text = popuwindow1Info.getPopuwindow1ChildInfo().getGoodsBaseInfo().getName();
            StringBuilder stringBuilder = new StringBuilder();
            for (int a = 0; a < popuwindow1Info.getPopuwindow1ChildInfo().getGoodsBaseInfo().getChildren().size(); a++) {
                for (int b = 0; b < popuwindow1Info.getPopuwindow1ChildInfo().getGoodsBaseInfo().getChildren().get(a).getChildren().size(); b++) {
                    stringBuilder.append(String.valueOf(popuwindow1Info.getPopuwindow1ChildInfo().getGoodsBaseInfo().getChildren().get(a).getChildren().get(b).getId()));
                    stringBuilder.append(",");
                }

            }
            type = "105";
            number = 0;
            type105GoodsId = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
            type106GoodsId = type105GoodsId;
            type107GoodsId = type106GoodsId;
            getTypeData(type105GoodsId, false, 1);
            BaseTool.logPrint(TAG, "onclick: ---a-a-a-a-" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));

        } else {
            text = popuwindow1Info.t.getName();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < popuwindow1Info.t.getChildren().size(); i++) {
                stringBuilder.append(popuwindow1Info.t.getChildren().get(i).getId());
                stringBuilder.append(",");
            }
            type = "105";
            number = 0;
            type105GoodsId = stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1);
            type106GoodsId = type105GoodsId;
            type107GoodsId = type106GoodsId;
            getTypeData(type105GoodsId, false, 1);
            BaseTool.logPrint(TAG, "onclick: ---b-b-b-b-" + stringBuilder.toString().substring(0, stringBuilder.toString().length() - 1));
        }
        textViewChoose1.setText(text);
        setTabStateFalse(1);
    }

    @Override
    public void onclick(int number) {
        String text = "";
        sortPopupwindow.setSelectState(number);
        this.number = 0;
        type = "106";
        if (number == 0) {
            text = "默认排序";
            type106TypeId = 0;
        } else if (number == 1) {
            text = "时间排序";
            type106TypeId = 1;
        }
        getSortData(type106GoodsId, false, 1, type106TypeId);
        textViewChoose2.setText(text);
        setTabStateFalse(2);
    }

    @Override
    public void getAllCompanyInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                allCompanyInfo = JSON.parseObject(result, AllCompanyInfo.class);
                companyPopupwindow = new CompanyPopupwindow(this, allCompanyInfo.getContent());
                companyPopupwindow.setOnClickPopupwindowItemListener(this);
                companyPopupwindow.setTouchable(true);
                companyPopupwindow.setOutsideTouchable(false);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getAllCompanyInfoFailed(int code, String result) {

    }

    @Override
    public void onclick(AllCompanyInfo.ContentBean contentBean) {
        //ToastUtil.makeText(this, contentBean.getName(), Toast.LENGTH_SHORT).show();
        textViewChoose3.setText(contentBean.getName());
        setTabStateFalse(3);
        type = "107";
        number = 0;
        type107Company = String.valueOf(contentBean.getId());
        getCompanyData(type107GoodsId, false, 1, type107Company);
    }


    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    hideWindow();
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    cartPopupwindow = new CartPopupwindow(this, goodsInfo);
                    cartPopupwindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    cartPopupwindow.showAtLocation(dianHuoTongShopTitleBar, Gravity.BOTTOM, 0, 0);
                    //ToastUtil.makeText(mContext, searchSGoodsBean.getContent().get(position).getName(), Toast.LENGTH_SHORT).show();
                } else {
                    ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
                }
                //textViewUseTime.setText(goodsInfo.getExpiryDate());
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {
        ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
    }
}
