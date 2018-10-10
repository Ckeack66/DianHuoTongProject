package com.mhky.dianhuotong.shop.fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.activity.GoodsActivity;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.bean.ShopTypeInfo;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.custom.ShopTypePopupwindow;
import com.mhky.dianhuotong.shop.custom.SortPopupwindow;
import com.mhky.dianhuotong.shop.precenter.CompanyPrecenter;
import com.mhky.dianhuotong.shop.precenter.CouponPresenter;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.SearchGoodsPresenter;
import com.mhky.dianhuotong.shop.precenter.ShopPresenter;
import com.mhky.dianhuotong.shop.precenter.StarShopPrecenter;
import com.mhky.dianhuotong.shop.shopif.CompanyIF;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;
import com.mhky.dianhuotong.shop.shopif.ShopIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopAllGoodsFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 商家全部商品Fragment
 */
public class ShopAllGoodsFragment extends Fragment implements GoodsIF, SearchGoodsIF, SortPopupwindow.OnClickPopupwindow2ItemListener, ShopIF, ShopTypePopupwindow.OnClickShopPopupwindowItemListener {
    @BindView(R.id.shop_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.shop_main_tab_txt1)
    TextView textViewTab1;
    @BindView(R.id.shop_main_tab_txt2)
    TextView textViewTab2;
    @BindView(R.id.shop_main_tab_img1)
    ImageView imageViewTab1;
    @BindView(R.id.shop_main_tab_img2)
    ImageView imageViewTab2;
    @BindView(R.id.shop_main_child_tab1)
    RelativeLayout relativeLayoutTab1;
    @BindView(R.id.shop_main_child_tab2)
    RelativeLayout relativeLayoutTab2;
    @BindView(R.id.shop_main_rv)
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private int chooseOldNumber = -1;
    private boolean tabIsOpen = false;
    private Unbinder unbinder;
    private int number = 0;
    private String type101TypeData;
//    private String type102SortData = "name,DESC";
    private String type102SortData = "";
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private SortPopupwindow sortPopupwindow;
    private CartPopupwindow cartPopupwindow;
    private ShopTypePopupwindow shopTypePopupwindow;
    private SearchGoodsPresenter searchGoodsPresenter;
    private ShopPresenter shopPresenter;
    private SearchSGoodsBean searchSGoodsBean;
    private SearchGoodsAdpter searchGoodsAdpter;
    private List<ShopTypeInfo> shopTransferInfoList;
    private Context mContext;

    public ShopAllGoodsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopAllGoodsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopAllGoodsFragment newInstance(String param1, String param2, Context context) {
        ShopAllGoodsFragment fragment = new ShopAllGoodsFragment();
        fragment.mContext=context;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.shop_main_child_tab1)
    void selectTab1() {
        setTabStateTrue(1);
    }

    @OnClick(R.id.shop_main_child_tab2)
    void selectTab2() {
        setTabStateTrue(2);
    }

    private void setTabStateTrue(int newNumber) {
        if (newNumber == chooseOldNumber && tabIsOpen) {
            setTabStateFalse(newNumber);
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
            setTabStateFalse(chooseOldNumber);
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        }

    }

    private void hideWindow() {
        if (shopTypePopupwindow != null && shopTypePopupwindow.isShowing()) {
            shopTypePopupwindow.dismiss();
        } else if (sortPopupwindow != null && sortPopupwindow.isShowing()) {
            sortPopupwindow.dismiss();
        }
    }

    private void setTabOpen(int number) {
        switch (number) {
            case 1:
                try {
                    textViewTab1.setTextColor(Color.parseColor("#04c1ab"));
                    imageViewTab1.setImageResource(R.drawable.icon_choose_selecte);
                    PopupWindowCompat.showAsDropDown(shopTypePopupwindow, relativeLayoutTab1, 0, 0, Gravity.LEFT);
                    tabIsOpen = true;
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(getActivity(), e);
                }
                break;
            case 2:
                try {
                    textViewTab2.setTextColor(Color.parseColor("#04c1ab"));
                    imageViewTab2.setImageResource(R.drawable.icon_choose_selecte);
                    PopupWindowCompat.showAsDropDown(sortPopupwindow, relativeLayoutTab1, 0, 0, Gravity.LEFT);
                    tabIsOpen = true;
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(getActivity(), e);
                }
                break;
        }
    }

    private void setTabStateFalse(int number) {
        switch (number) {
            case 1:
                textViewTab1.setTextColor(Color.parseColor("#333333"));
                imageViewTab1.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
            case 2:
                textViewTab2.setTextColor(Color.parseColor("#333333"));
                imageViewTab2.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_all_goods, container, false);
        unbinder = ButterKnife.bind(this, view);
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }

        return view;

    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        goodsPrecenter = new GoodsPrecenter(this);
        sortPopupwindow = new SortPopupwindow(getActivity(), -1);
        sortPopupwindow.setClickPopupwindow2ItemListener(this);
        searchGoodsPresenter = new SearchGoodsPresenter(this);
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        searchGoodsPresenter.searchGoods(httpParams, true, -1);
        shopPresenter = new ShopPresenter(this);
        shopPresenter.getShopType(mParam1);
        BaseTool.logPrint("店铺id",mParam1);
        setRefresh();
    }

    private void setRefresh() {
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //smartRefreshLayout.setEnableLoadMore(false);
                getInitData(1);
            }
        });
    }

    private void getInitData(int state) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        if (!BaseTool.isEmpty(type102SortData)) {
            httpParams.put("sort", type102SortData);
        }
        if (type101TypeData != null) {
            httpParams.put("goodsIds", type101TypeData);
        }
        searchGoodsPresenter.searchGoods(httpParams, false, state);
    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    cartPopupwindow = new CartPopupwindow(getActivity(), goodsInfo,6);
                    cartPopupwindow.showAtLocation(relativeLayoutTab1, Gravity.BOTTOM, 0, 0);
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }
    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {

    }

    @Override
    public void searchGoodsInfoSuccess(int code, String result, boolean isfirst, int refreshOrLoadmore) {
        try {
            if (code == 200) {
                SearchSGoodsBean searchSGoodsBeans = JSON.parseObject(result, SearchSGoodsBean.class);
                if (refreshOrLoadmore == -1) {
                    //初始页面
                    searchSGoodsBean = searchSGoodsBeans;
                    if (searchSGoodsBeans.getContent() != null) {
                        searchGoodsAdpter = new SearchGoodsAdpter(searchSGoodsBean.getContent(), getActivity());
                        searchGoodsAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("id", searchSGoodsBean.getContent().get(position).getId() + "");
                                BaseTool.goActivityWithData(getActivity(), GoodsActivity.class, bundle);
                            }
                        });
                        searchGoodsAdpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                            @Override
                            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                                goodsPrecenter.getGoodsInfo(String.valueOf(searchSGoodsBean.getContent().get(position).getId()));
                            }
                        });
                        searchGoodsAdpter.openLoadAnimation();
                        recyclerView.setAdapter(searchGoodsAdpter);
                    }
                    number++;
                } else if (refreshOrLoadmore == 0) {
                    searchSGoodsBean = searchSGoodsBeans;
                    searchGoodsAdpter.setNewData(searchSGoodsBeans.getContent());
                    number++;
                } else if (refreshOrLoadmore == 1) {
                    number++;

                    if (searchSGoodsBeans != null && searchSGoodsBeans.getContent().size() == 0) {
                        smartRefreshLayout.finishLoadMore(true);
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    } else if (searchSGoodsBeans != null && searchSGoodsBeans.getContent().size() < 10) {
                        searchGoodsAdpter.addData(searchSGoodsBeans.getContent());
                        smartRefreshLayout.finishLoadMore(true);
                        smartRefreshLayout.setEnableLoadMore(false);
                    } else {
                        searchGoodsAdpter.addData(searchSGoodsBeans.getContent());
                        smartRefreshLayout.finishLoadMore(666, true, false);
                        number++;
                    }
                }
            }

        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }
    }

    @Override
    public void searchGoodsInfoFailed(int code, String result, boolean isfirst, int refreshOrLoadmore) {

    }

    @Override
    public void onclick(int numbers) {
        String text = "";
        sortPopupwindow.setSelectState(numbers);
        number = 0;
        if (numbers == 0) {
            text = "默认排序";
            type102SortData = "name,DESC";
        } else if (numbers == 1) {
            text = "时间排序";
            type102SortData = "createTime,DESC";
        }
        smartRefreshLayout.setEnableLoadMore(true);
        textViewTab2.setText(text);
        setTabStateFalse(2);
        getInitData(0);
    }

    @Override
    public void getShopInfoSuccess(int code, String result) {

    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }


    @Override
    public void getShopTypeSuccess(int code, String result) {
        try {
            if (code == 200) {
                shopTransferInfoList = JSON.parseArray(result, ShopTypeInfo.class);
                shopTypePopupwindow = new ShopTypePopupwindow(getActivity(), shopTransferInfoList);
                shopTypePopupwindow.setOnClickPopupwindowItemListener(this);
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }
    }

    @Override
    public void getShopTypeFailed(int code, String result) {

    }

    @Override
    public void onclickType(ShopTypeInfo contentBean) {
        number = 0;
        StringBuilder stringBuilder = new StringBuilder();
        BaseTool.logPrint("日志打印",contentBean.getGoodsIds().size()+"");
        for (int a = 0; a < contentBean.getGoodsIds().size(); a++) {
            stringBuilder.append(contentBean.getGoodsIds().get(a));
            stringBuilder.append(",");
            BaseTool.logPrint("aaaaaaaaaa",contentBean.getGoodsIds().get(a));
        }
        if (stringBuilder.length() > 0) {
            type101TypeData = stringBuilder.substring(0, stringBuilder.length() - 1);
            BaseTool.logPrint("aaaaaaaaaa",type101TypeData);
        }
        textViewTab1.setText(contentBean.getName());
        smartRefreshLayout.setEnableLoadMore(true);
        setTabStateFalse(1);
        getInitData(0);
    }
}
