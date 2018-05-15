package com.mhky.dianhuotong.shop.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.PopupWindowCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.activity.GoodsActivity;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.bean.ShopInfo;
import com.mhky.dianhuotong.shop.bean.ShopTransferInfo;
import com.mhky.dianhuotong.shop.bean.ShopTypeInfo;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.custom.CompanyPopupwindow;
import com.mhky.dianhuotong.shop.custom.ShopTypePopupwindow;
import com.mhky.dianhuotong.shop.custom.SortPopupwindow;
import com.mhky.dianhuotong.shop.precenter.CompanyPrecenter;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.SearchGoodsPresenter;
import com.mhky.dianhuotong.shop.precenter.ShopPresenter;
import com.mhky.dianhuotong.shop.shopif.CompanyIF;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;
import com.mhky.dianhuotong.shop.shopif.ShopIF;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wellijohn.org.scrollviewwithstickheader.ScrollViewWithStickHeader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ShopMainFragment extends Fragment implements ShopIF, SortPopupwindow.OnClickPopupwindow2ItemListener, ShopTypePopupwindow.OnClickShopPopupwindowItemListener, SearchGoodsIF, CompanyIF, GoodsIF {
    @BindView(R.id.shop_img)
    ImageView imageViewLogo;
    @BindView(R.id.shop_main_child_tab1)
    RelativeLayout relativeLayoutTab1;
    @BindView(R.id.shop_main_child_tab2)
    RelativeLayout relativeLayoutTab2;
    @BindView(R.id.shop_main_tab_txt1)
    TextView textViewTab1;
    @BindView(R.id.shop_main_tab_txt2)
    TextView textViewTab2;
    @BindView(R.id.shop_main_tab_img1)
    ImageView imageViewTab1;
    @BindView(R.id.shop_main_tab_img2)
    ImageView imageViewTab2;
    @BindView(R.id.shop_main_rv)
    RecyclerView recyclerView;
    @BindView(R.id.shop_refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.ll_head)
    LinearLayout linearLayoutHead;
    @BindView(R.id.ll_body)
    LinearLayout linearLayoutBody;
    @BindView(R.id.shop_name)
    TextView textViewShopName;
    @BindView(R.id.shop_notoice)
    TextView textViewNotice;
    @BindView(R.id.shop_scollview)
    NestedScrollView nestedScrollView;
    //    @BindView(R.id.shop_scollview)
//    ScrollViewWithStickHeader scrollViewWithStickHeader;
    private int chooseOldNumber = -1;
    private boolean tabIsOpen = false;
    private Unbinder unbinder;
    private List<ShopTypeInfo> shopTransferInfoList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ShopPresenter shopPresenter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ShopTypePopupwindow shopTypePopupwindow;
    private SortPopupwindow sortPopupwindow;
    private SearchGoodsPresenter searchGoodsPresenter;
    private SearchSGoodsBean searchSGoodsBean;
    private SearchGoodsAdpter searchGoodsAdpter;
    private int number = 0;
    private int type = 0;
    private String childId;
    private int sortId = 0;
    private CompanyPrecenter companyPrecenter;
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private CartPopupwindow cartPopupwindow;
    private static final String TAG = "ShopMainFragment";


    public ShopMainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ShopMainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ShopMainFragment newInstance(String param1, String param2) {
        ShopMainFragment fragment = new ShopMainFragment();
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
//        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(getActivity()).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
    }

    private void setRefresh() {
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //smartRefreshLayout.setEnableLoadMore(false);
                smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
                if (type == 0) {
                    //初始数据
                    getInitData();
                } else if (type == 1) {
                    //分类查询
                    getTypeData();
                } else if (type == 2) {
                    //排序
                    getSortData();
                }

            }
        });
    }

    private void getInitData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        searchGoodsPresenter.searchGoods(httpParams, false, 1);
    }

    private void getTypeData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        if (childId != null && !childId.equals("")) {
            httpParams.put("categoryIds", childId);
        }
        searchGoodsPresenter.searchGoods(httpParams, true, 0);
    }

    private void getSortData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        if (sortId == 0) {
            httpParams.put("sort", "name,DESC");
        } else {
            httpParams.put("sort", "createTime,DESC");
        }
        searchGoodsPresenter.searchGoods(httpParams, true, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.requestLayout();
        goodsPrecenter = new GoodsPrecenter(this);
        shopPresenter = new ShopPresenter(this);
        shopTransferInfoList = new ArrayList<>();
        sortPopupwindow = new SortPopupwindow(getActivity(), -1);
        sortPopupwindow.setClickPopupwindow2ItemListener(this);
        shopPresenter.getShopInfo(mParam1);
        shopPresenter.getShopType(mParam1);
        searchGoodsPresenter = new SearchGoodsPresenter(this);
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        searchGoodsPresenter.searchGoods(httpParams, true, 0);
        smartRefreshLayout.setEnableRefresh(false);
        companyPrecenter = new CompanyPrecenter(this);
        companyPrecenter.getCompanyTansferInfo(mParam1);
        //smartRefreshLayout.setEnableLoadMore(false);
//        scrollViewWithStickHeader.setContentView(linearLayoutHead);
//        scrollViewWithStickHeader.setSuspensionView(linearLayoutBody);
        //setRefresh();
        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                hideWindow();
                setTabStateFalse(chooseOldNumber);
            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.shop_main_child_tab1)
    void selectTab1() {
        setTabStateTrue(1);
    }

    @OnClick(R.id.shop_main_child_tab2)
    void selectTab2() {
        setTabStateTrue(2);
    }


    private void hideWindow() {
//        nestedScrollView.setFocusable(true);
//        nestedScrollView.setNestedScrollingEnabled(false);
        if (shopTypePopupwindow!=null&&shopTypePopupwindow.isShowing()) {
            shopTypePopupwindow.dismiss();
        } else if (sortPopupwindow!=null&&sortPopupwindow.isShowing()) {
            sortPopupwindow.dismiss();
        }
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

    private void setTabOpen(int number) {
        switch (number) {
            case 1:
                textViewTab1.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewTab1.setImageResource(R.drawable.icon_choose_selecte);
                PopupWindowCompat.showAsDropDown(shopTypePopupwindow, relativeLayoutTab1, 0, 0, Gravity.LEFT);
                tabIsOpen = true;
//                nestedScrollView.setFocusable(false);
//                nestedScrollView.setNestedScrollingEnabled(false);
                nestedScrollView.setEnabled(false);
                break;
            case 2:
                textViewTab2.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewTab2.setImageResource(R.drawable.icon_choose_selecte);
                PopupWindowCompat.showAsDropDown(sortPopupwindow, relativeLayoutTab1, 0, 0, Gravity.LEFT);
                tabIsOpen = true;
//                nestedScrollView.setFocusable(false);
//                nestedScrollView.setNestedScrollingEnabled(false);
                nestedScrollView.setEnabled(false);
                break;
        }
    }

    private void setTabStateFalse(int number) {
        switch (number) {
            case 1:
                textViewTab1.setTextColor(getResources().getColor(R.color.color333333));
                imageViewTab1.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
            case 2:
                textViewTab2.setTextColor(getResources().getColor(R.color.color333333));
                imageViewTab2.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
        }

    }

    @Override
    public void getShopInfoSuccess(int code, String result) {
        if (code == 200) {
            ShopInfo shopInfo = JSON.parseObject(result, ShopInfo.class);
            if (shopInfo.getLogo() != null) {
                Picasso.with(getActivity()).load(shopInfo.getLogo()).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).into(imageViewLogo);
            }
            textViewShopName.setText(shopInfo.getName());

        }
    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }

    @Override
    public void getShopTypeSuccess(int code, String result) {
        if (code == 200) {
            shopTransferInfoList = JSON.parseArray(result, ShopTypeInfo.class);
            shopTypePopupwindow = new ShopTypePopupwindow(getActivity(), shopTransferInfoList);
            shopTypePopupwindow.setOnClickPopupwindowItemListener(this);
        }
    }

    @Override
    public void getShopTypeFailed(int code, String result) {

    }

    @Override
    public void onclick(int number) {
        String text = "";
        sortPopupwindow.setSelectState(number);
        if (number == 0) {
            text = "默认排序";
            ToastUtil.makeText(getActivity(), "默认排序", Toast.LENGTH_SHORT).show();
        } else if (number == 1) {
            text = "价格排序";
            ToastUtil.makeText(getActivity(), "价格排序", Toast.LENGTH_SHORT).show();
        }
        textViewTab2.setText(text);
        setTabStateFalse(2);
        ToastUtil.makeText(getActivity(), "点击---" + number, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onclickType(ShopTypeInfo contentBean) {
        setTabStateFalse(1);
        ToastUtil.makeText(getActivity(), "点击---" + contentBean.getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchGoodsInfoSuccess(int code, String result, boolean isfirst, int refreshOrLoadmore) {
        if (code == 200) {
            SearchSGoodsBean searchSGoodsBeans = JSON.parseObject(result, SearchSGoodsBean.class);
            if (isfirst) {
                //初始页面
                searchSGoodsBean = searchSGoodsBeans;
                Log.d(TAG, "searchGoodsInfoSuccess1: " + searchSGoodsBean.getContent().size());
                Log.d(TAG, "searchGoodsInfoSuccess2: " + searchSGoodsBeans.getContent().size());
                if (searchSGoodsBeans.getContent() != null) {
                    searchGoodsAdpter = new SearchGoodsAdpter(searchSGoodsBean.getContent(), getActivity());
                    searchGoodsAdpter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("id", searchSGoodsBean.getContent().get(position).getId()+"");
                            BaseTool.goActivityWithData(getActivity(), GoodsActivity.class, bundle);
                        }
                    });
                    searchGoodsAdpter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            goodsPrecenter.getGoodsInfo(String.valueOf(searchSGoodsBean.getContent().get(position).getId()));
                        }
                    });
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    linearLayoutManager.setAutoMeasureEnabled(true);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    searchGoodsAdpter.openLoadAnimation();
                    recyclerView.setAdapter(searchGoodsAdpter);
                }


            } else {
                //刷新或者加载更多界面
                if (refreshOrLoadmore == 0) {
                    searchSGoodsBean = null;
                    searchSGoodsBean = searchSGoodsBeans;
                } else if (refreshOrLoadmore == 1) {
                    number++;
                    smartRefreshLayout.finishLoadMore(true);
                    if (searchSGoodsBeans.getContent().size() < 10) {
                        searchSGoodsBean.getContent().addAll(searchSGoodsBeans.getContent());
                        searchGoodsAdpter.addData(searchSGoodsBeans.getContent());
                    } else if (searchSGoodsBeans.getContent().size() == 0) {
                        ToastUtil.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }

    }

    @Override
    public void searchGoodsInfoFailed(int code, String result, boolean isfirst, int refreshOrLoadmore) {

    }

    @Override
    public void getCompanyCredentialSucess(int code, String result) {

    }

    @Override
    public void getCompanyCredentialFaild(int code, String result) {

    }

    @Override
    public void getCompanyTansferSucess(int code, String result) {
        if (code == 200) {
            ShopTransferInfo shopTransferInfo = JSON.parseObject(result, ShopTransferInfo.class);
            if (shopTransferInfo != null) {
                if (shopTransferInfo.getNotice() != null) {
                    textViewNotice.setText(shopTransferInfo.getNotice().toString());
                }
            }
        }
    }

    @Override
    public void getCompanyTansferFaild(int code, String result) {

    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        if (code == 200) {
            if (result != null && !result.equals("")) {
                goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                cartPopupwindow = new CartPopupwindow(getActivity(), goodsInfo);
                cartPopupwindow.showAtLocation(linearLayoutHead, Gravity.BOTTOM, 0, 0);
                //ToastUtil.makeText(mContext, searchSGoodsBean.getContent().get(position).getName(), Toast.LENGTH_SHORT).show();
            }
            //textViewUseTime.setText(goodsInfo.getExpiryDate());
        }
    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {

    }
}
