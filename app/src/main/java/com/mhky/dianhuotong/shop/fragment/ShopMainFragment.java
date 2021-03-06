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
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.AlertDialog.DianHuoTongBaseDialog;
import com.mhky.dianhuotong.custom.AlertDialog.LoadingDialog;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.activity.GoodsActivity;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.adapter.ShopCouponAdapter;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.bean.ShopCouponInfo;
import com.mhky.dianhuotong.shop.bean.ShopInfo;
import com.mhky.dianhuotong.shop.bean.ShopTransferInfo;
import com.mhky.dianhuotong.shop.bean.ShopTypeInfo;
import com.mhky.dianhuotong.shop.bean.StarShopInfo;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.custom.CompanyPopupwindow;
import com.mhky.dianhuotong.shop.custom.ShopTypePopupwindow;
import com.mhky.dianhuotong.shop.custom.SortPopupwindow;
import com.mhky.dianhuotong.shop.precenter.CompanyPrecenter;
import com.mhky.dianhuotong.shop.precenter.CouponPresenter;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.SearchGoodsPresenter;
import com.mhky.dianhuotong.shop.precenter.ShopPresenter;
import com.mhky.dianhuotong.shop.precenter.StarGoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.StarShopPrecenter;
import com.mhky.dianhuotong.shop.shopif.CompanyIF;
import com.mhky.dianhuotong.shop.shopif.CounponAddIF;
import com.mhky.dianhuotong.shop.shopif.CounponGetIF;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;
import com.mhky.dianhuotong.shop.shopif.ShopIF;
import com.mhky.dianhuotong.shop.shopif.StarShopAddIF;
import com.mhky.dianhuotong.shop.shopif.StarShopIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import wellijohn.org.scrollviewwithstickheader.ScrollViewWithStickHeader;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShopMainFragment#newInstance} factory method to
 * create an instance of this fragment.
 * 商家首页Fragment
 */
public class ShopMainFragment extends Fragment implements ShopIF, SortPopupwindow.OnClickPopupwindow2ItemListener, ShopTypePopupwindow.OnClickShopPopupwindowItemListener, SearchGoodsIF, CompanyIF, GoodsIF, StarShopIF, DianHuoTongBaseDialog.BaseDialogListener, CounponGetIF, CounponAddIF {
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
    @BindView(R.id.shop_star)
    TextView textViewShopStar;
    @BindView(R.id.shop_transfer)
    TextView textViewTransfer;
    @BindView(R.id.shop_coupon_rlv)
    RecyclerView recyclerViewCoupon;
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
    private CompanyPrecenter companyPrecenter;
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private CartPopupwindow cartPopupwindow;
    private ShopInfo shopInfo;
    private StarShopPrecenter starShopPrecenter;
    private DianHuoTongBaseDialog dianHuoTongBaseDialog;
    private String starID;
    private List<ShopCouponInfo> shopCouponInfoList = new ArrayList<>();                            //店铺优惠券List
    private CouponPresenter couponPresenter;
    private ShopCouponAdapter shopCouponAdapter;
    private LoadingDialog loadingDialog;
    private int number = 0;                                                                         //页码
    private String type101TypeData;
    private String type102SortData = "name,DESC";
    private Context mContext;
    private OnclickTypeOrSort onclickTypeOrSort;

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
    public static ShopMainFragment newInstance(String param1, String param2, Context context) {
        ShopMainFragment fragment = new ShopMainFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shop_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }

        return view;
    }

    private void init() {
        loadingDialog = new LoadingDialog(getActivity());

        recyclerView.setNestedScrollingEnabled(false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        linearLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        goodsPrecenter = new GoodsPrecenter(this);
        shopPresenter = new ShopPresenter(this);
        couponPresenter = new CouponPresenter().setCounponGetIF(this).setCounponAddIF(this);
        Map map = new HashMap();
        BaseTool.logPrint("param1ck",mParam1 + "--" + mParam2);
        map.put("shopId", mParam1);
        couponPresenter.getCouponByShop(map);

        LinearLayoutManager linearLayoutManagerHorizongtal = new LinearLayoutManager(getActivity());
        linearLayoutManagerHorizongtal.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewCoupon.setLayoutManager(linearLayoutManagerHorizongtal);

        shopTransferInfoList = new ArrayList<>();
        sortPopupwindow = new SortPopupwindow(getActivity(), -1);
        sortPopupwindow.setClickPopupwindow2ItemListener(this);

        shopPresenter.getShopInfo(mParam1);
        shopPresenter.getShopType(mParam1);

        searchGoodsPresenter = new SearchGoodsPresenter(this);
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        searchGoodsPresenter.searchGoods(httpParams, true, -1);

        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setEnableLoadMore(false);

        companyPrecenter = new CompanyPrecenter(this);
        companyPrecenter.getCompanyTansferInfo(mParam1);

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                hideWindow();
                setTabStateFalse(chooseOldNumber);
            }
        });

        starShopPrecenter = new StarShopPrecenter();
        starShopPrecenter.setStarShopIF(this);
        dianHuoTongBaseDialog = new DianHuoTongBaseDialog(getActivity(), this, "温馨提示", "请问客官确定要取消收藏店铺吗？", "取消", "确定", "fg");
//        setRefresh();
        linearLayoutHead.setFocusableInTouchMode(true);
        linearLayoutHead.requestFocus();
    }

    /**
     * 暂时用不到
     * 不用给它设置刷新与加载
     */
//    private void setRefresh() {
//        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(getActivity()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
//        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                //smartRefreshLayout.setEnableLoadMore(false);
//                getInitData(1);
//            }
//        });
//    }

    private void getInitData(int state) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("shopId", mParam1);
        httpParams.put("page", number);
        if (type102SortData != null) {
            httpParams.put("sort", type102SortData);
        }
        if (type101TypeData != null) {
            httpParams.put("goodsIds", type101TypeData);
        }
        searchGoodsPresenter.searchGoods(httpParams, false, state);
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
//        setTabStateTrue(1);
        onclickTypeOrSort.onclickTypeOrSort();
    }

    @OnClick(R.id.shop_main_child_tab2)
    void selectTab2() {
//        setTabStateTrue(2);
        onclickTypeOrSort.onclickTypeOrSort();
    }


    @OnClick(R.id.shop_star)
    void startShop() {
        try {
            if (shopInfo != null) {
                if (shopInfo.isFollowStatus()) {
                    //取消关注
                    starShopPrecenter.getStarShop();
                } else {
                    //关注店铺
                    starShopPrecenter.addStarShop(shopInfo.getId());
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }

    }

    private void hideWindow() {
        if (shopTypePopupwindow != null && shopTypePopupwindow.isShowing()) {
            shopTypePopupwindow.dismiss();
        } else if (sortPopupwindow != null && sortPopupwindow.isShowing()) {
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
                try {
                    textViewTab1.setTextColor(Color.parseColor("#04c1ab"));
                    imageViewTab1.setImageResource(R.drawable.icon_choose_selecte);
                    PopupWindowCompat.showAsDropDown(shopTypePopupwindow, relativeLayoutTab1, 0, 0, Gravity.LEFT);
                    tabIsOpen = true;
                    nestedScrollView.setEnabled(false);
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
                    nestedScrollView.setEnabled(false);
                } catch (Exception e) {
                    PgyCrashManager.reportCaughtException(getActivity(), e);
                }

                break;
        }
    }

    /**
     * 改变 默认分类   或   默认排序的    被选中颜色改变
     * @param number
     */
    private void setTabStateFalse(int number) {
        switch (number) {
            case 1://默认分类
                textViewTab1.setTextColor(Color.parseColor("#333333"));
                imageViewTab1.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
            case 2://默认排序
                textViewTab2.setTextColor(Color.parseColor("#333333"));
                imageViewTab2.setImageResource(R.drawable.icon_choose_unselecte);
                tabIsOpen = false;
                break;
        }

    }

    @Override
    public void getShopInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                shopInfo = JSON.parseObject(result, ShopInfo.class);
                if (shopInfo != null) {
                    if (shopInfo.getLogo() != null) {
                        Picasso.get().load(shopInfo.getLogo()).into(imageViewLogo);
                    }
                    textViewShopName.setText(shopInfo.getName());
                    if (shopInfo.isFollowStatus()) {
                        textViewShopStar.setText("已关注");
                    } else {
                        textViewShopStar.setText("关注");
                    }
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }

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
        smartRefreshLayout.setEnableLoadMore(false);
        textViewTab2.setText(text);
        setTabStateFalse(2);
        getInitData(0);
    }

    @Override
    public void onclickType(ShopTypeInfo contentBean) {
        number = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int a = 0; a < contentBean.getGoodsIds().size(); a++) {
            stringBuilder.append(contentBean.getGoodsIds().get(a));
            stringBuilder.append(",");
        }
        if (stringBuilder.length() > 0) {
            type101TypeData = stringBuilder.substring(0, stringBuilder.length() - 1);
        }
        textViewTab1.setText(contentBean.getName());
        smartRefreshLayout.setEnableLoadMore(false);
        setTabStateFalse(1);
        getInitData(0);
    }

    /**
     * 获取商品成功
     * @param code
     * @param result
     * @param isfirst
     * @param refreshOrLoadmore    -1   此处固定写死
     */
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
                    smartRefreshLayout.finishLoadMore(true);
                    if (searchSGoodsBeans.getContent().size() == 0) {
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(getActivity(), "没有更多数据了", Toast.LENGTH_SHORT).show();
                    } else if (searchSGoodsBeans.getContent().size() < 10) {
                        smartRefreshLayout.setEnableLoadMore(false);
                        searchGoodsAdpter.addData(searchSGoodsBeans.getContent());
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
    public void getCompanyCredentialSucess(int code, String result) {

    }

    @Override
    public void getCompanyCredentialFaild(int code, String result) {

    }

    /**
     * 获取配送信息成功
     * @param code
     * @param result
     */
    @Override
    public void getCompanyTansferSucess(int code, String result) {
        try {
            if (code == 200) {
                ShopTransferInfo shopTransferInfo = JSON.parseObject(result, ShopTransferInfo.class);
                if (shopTransferInfo != null) {
                    if (shopTransferInfo.getNotice() != null) {
                        textViewNotice.setText(shopTransferInfo.getNotice().toString());
                    } else {
                        textViewNotice.setText("暂无公告！");
                    }
                    if (shopTransferInfo.getSendAccount() == 0) {
                        textViewTransfer.setText("全场免邮");
                    } else {
                        textViewTransfer.setText("满" + shopTransferInfo.getSendAccount() / 100 + "元免邮");
                    }
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }

    }

    @Override
    public void getCompanyTansferFaild(int code, String result) {

    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    cartPopupwindow = new CartPopupwindow(getActivity(), goodsInfo,7);
                    cartPopupwindow.showAtLocation(linearLayoutHead, Gravity.BOTTOM, 0, 0);
                    //ToastUtil.makeText(mContext, searchSGoodsBean.getContent().get(position).getName(), Toast.LENGTH_SHORT).show();
                }
                //textViewUseTime.setText(goodsInfo.getExpiryDate());
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }

    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {

    }


    @Override
    public void getStarShopSuccess(int code, String result) {
        try {
            if (code == 200) {
                List<StarShopInfo> starShopInfoList = JSON.parseArray(result, StarShopInfo.class);
                for (int a = 0; a < starShopInfoList.size(); a++) {
                    if (shopInfo.getId().equals(starShopInfoList.get(a).getId())) {
                        starID = starShopInfoList.get(a).getFollowupstreamId();
                        break;
                    }
                }
                if (starID != null && !starID.equals("")) {
                    dianHuoTongBaseDialog.show();
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }
    }

    @Override
    public void getStarShopInfoFailed(int code, String result) {

    }

    @Override
    public void addStarShopSuccess(int code, String result) {
        if (code == 200) {
            shopInfo.setFollowStatus(true);
            textViewShopStar.setText("已关注");
        } else {
            ToastUtil.makeText(getActivity(), "关注失败！", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addStarShopInfoFailed(int code, String result) {

    }

    @Override
    public void deleteStarShopSuccess(int code, String result) {
        if (code == 200) {
            shopInfo.setFollowStatus(false);
            textViewShopStar.setText("关注");
            if (dianHuoTongBaseDialog != null) {
                dianHuoTongBaseDialog.dismiss();
            }
        } else {
            ToastUtil.makeText(getActivity(), "取消失败！", Toast.LENGTH_SHORT).show();
            if (dianHuoTongBaseDialog != null) {
                dianHuoTongBaseDialog.dismiss();
            }
        }
    }

    @Override
    public void deleteStarShopInfoFailed(int code, String result) {
        if (dianHuoTongBaseDialog != null) {
            ToastUtil.makeText(getActivity(), "取消失败！", Toast.LENGTH_SHORT).show();
            dianHuoTongBaseDialog.dismiss();
        }
    }

    @Override
    public void onClickBaseDialogLeft(String iTag) {
        if (dianHuoTongBaseDialog != null) {
            dianHuoTongBaseDialog.dismiss();
        }
    }

    @Override
    public void onClickBaseDialogRight(String iTag) {
        try {
            starShopPrecenter.delete(starID);
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }
    }

    /**
     * 获取店铺优惠券成功
     * @param code
     * @param result
     */
    @Override
    public void getCouponSuccess(int code, String result) {
        try {
            if (code == 200) {
                shopCouponInfoList = JSON.parseArray(result, ShopCouponInfo.class);
                if (shopCouponAdapter == null) {
                    shopCouponAdapter = new ShopCouponAdapter(shopCouponInfoList, getActivity());
                    shopCouponAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
                    shopCouponAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            switch (view.getId()) {
                                case R.id.shop_coupon:
                                    //领取优惠券
                                    try {
                                        loadingDialog.show();
                                        Map map = new HashMap();
                                        map.put("promotionId", shopCouponInfoList.get(position).getId());
                                        map.put("shopId", BaseApplication.getInstansApp().getPersonInfo().getShopId());
                                        map.put("companyId", mParam1);
                                        couponPresenter.bindCouponByShop(map);
                                    } catch (Exception e) {
                                        PgyCrashManager.reportCaughtException(getActivity(), e);
                                    }
                                    break;
                            }
                        }
                    });
                    recyclerViewCoupon.setAdapter(shopCouponAdapter);
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        }
    }

    @Override
    public void getCouponFailed(int code, String result) {

    }

    /**
     * 领取优惠券成功
     * @param code
     * @param result
     */
    @Override
    public void addCouponSuccess(int code, String result) {
        try {
            if (code == 201) {
                ToastUtil.makeText(getActivity(), "领取成功！", Toast.LENGTH_SHORT).show();
            } else {
                ToastUtil.makeText(getActivity(), "领取失败!" + code + result, Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getActivity(), e);
        } finally {
            if (loadingDialog != null) {
                loadingDialog.dismiss();
            }
        }
    }

    @Override
    public void addCouponFailed(int code, String result) {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    /**
     * 定义接口   点击筛选条件跳转第二个Fragment
     */
    public interface OnclickTypeOrSort{
        void onclickTypeOrSort();
    }

    public void setOnclickTypeOrSort(OnclickTypeOrSort onclickTypeOrSort){
        this.onclickTypeOrSort = onclickTypeOrSort;
    }
}
