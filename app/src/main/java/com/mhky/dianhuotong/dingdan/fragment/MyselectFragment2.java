package com.mhky.dianhuotong.dingdan.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.activity.BalanceActivity;
import com.mhky.dianhuotong.shop.activity.OrderInfoActivity;
import com.mhky.dianhuotong.shop.activity.ShopActivity;
import com.mhky.dianhuotong.shop.adapter.OrderAdapter;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.precenter.OrderDataPresenter;
import com.mhky.dianhuotong.shop.precenter.OrderPrecenter;
import com.mhky.dianhuotong.shop.shopif.OrderIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyselectFragment2#newInstance} factory method to
 * create an instance of this fragment.
 * 待付款采购单  Fragment
 */
public class MyselectFragment2 extends Fragment implements OrderIF {
    @BindView(R.id.myselected_fragment2_rv)
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.srl_order_form)
    SmartRefreshLayout srlOrderForm;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder unbinder;
    private OrderAdapter orderAdapter;
    private OrderDataPresenter orderDataPresenter;
    private List<OrderInfo> orderInfoList;
    private OrderBaseInfo orderBaseInfo;
    private int page = 0;
    private OrderPrecenter orderPrecenter;

    public MyselectFragment2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyselectFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static MyselectFragment2 newInstance(String param1, String param2, OrderBaseInfo orderBaseInfoInit) {
        MyselectFragment2 fragment = new MyselectFragment2();
        fragment.orderInfoList = new ArrayList<>();
        fragment.orderBaseInfo = orderBaseInfoInit;
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
        View view = inflater.inflate(R.layout.fragment_myselect_fragment2, container, false);
        unbinder = ButterKnife.bind(this, view);
        try {
            orderPrecenter = new OrderPrecenter(this);
            orderDataPresenter = new OrderDataPresenter();
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            orderAdapter = new OrderAdapter(orderInfoList, getActivity());
            orderAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
            srlOrderForm.setEnableRefresh(false);
            srlOrderForm.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
            recyclerView.setAdapter(orderAdapter);
            initListener();
            setData();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(getContext(), e);
        }
        return view;
    }

    private void initListener() {
        orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.rl_order_top:
                        try {
                            Bundle bundle = new Bundle();
                            bundle.putString("shopid", orderInfoList.get(position).getOrderTopInfo().getShopID());
                            BaseTool.goActivityWithData(getActivity(), ShopActivity.class, bundle);
                        } catch (Exception e) {
                            PgyCrashManager.reportCaughtException(getActivity(), e);
                        }
                        break;
                    case R.id.order_body_goods:
                        try {
                            Bundle bundle1 = new Bundle();
//                            bundle1.putString("order", orderBaseInfo.getContent().get(Integer.valueOf(orderInfoList.get(position).getParentNumber())).getId());
                            bundle1.putString("order", orderAdapter.getData().get(position).getParentNumber());
                            BaseTool.goActivityWithData(getActivity(), OrderInfoActivity.class, bundle1);
                        } catch (Exception e) {
                            PgyCrashManager.reportCaughtException(getActivity(), e);
                        }
                        break;
                    case R.id.order_info_button:
                        switch (orderAdapter.getData().get(position).getOrderBottomInfo().getOrderStatus()) {
                            case "ORDERED":
                                BaseTool.logPrint("orderlistck", "list = " + orderAdapter.getData().get(position).getOrderBottomInfo().getContentBean().getId());
                                try {
                                    Bundle bundle2 = new Bundle();
                                    bundle2.putString("order", orderAdapter.getData().get(position).getOrderBottomInfo().getContentBean().getId());
                                    double a = orderAdapter.getData().get(position).getOrderBottomInfo().getContentBean().getPayment();
                                    bundle2.putString("money", String.valueOf(a / 100));
                                    bundle2.putInt("state", 1);
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), BalanceActivity.class);
                                    intent.putExtras(bundle2);
                                    startActivityForResult(intent, 10001);
                                } catch (Exception e) {
                                    PgyCrashManager.reportCaughtException(getActivity(), e);
                                }
                                break;
                            case "PAID":
//                                        ToastUtil.makeText(getActivity(), "已付款" + position, Toast.LENGTH_SHORT).show();
                                break;
                            case "COMPLETED":
//                                        ToastUtil.makeText(getActivity(), "已完成" + position, Toast.LENGTH_SHORT).show();
                                break;
                            case "CANCELLED":
//                                        ToastUtil.makeText(getActivity(), "已取消" + position, Toast.LENGTH_SHORT).show();
                                break;
                        }
                        break;
                }
            }
        });

        srlOrderForm.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                HttpParams httpParams = new HttpParams();
                httpParams.put("buyerId", BaseApplication.getInstansApp().getPersonInfo().getId().toString());
                httpParams.put("page",page);
                httpParams.put("size","10");
                orderPrecenter.getOrder(httpParams);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    private void setData() {
        doDate(0);
    }

    public void upData(OrderBaseInfo orderBaseInfoNew) {
        orderBaseInfo = orderBaseInfoNew;
        doDate(1);
    }

    private void doDate(int type) {
        if (type == 0) {
            if (orderBaseInfo != null) {
                orderInfoList = orderDataPresenter.getOrderListFragment2(orderBaseInfo);
                orderAdapter.setNewData(orderInfoList);
                if (orderBaseInfo.getNumberOfElements() < 10){
                    srlOrderForm.setEnableLoadMore(false);
                }
            }

        } else if (type == 1) {
            if (orderBaseInfo != null) {
                orderInfoList = orderDataPresenter.getOrderListFragment2(orderBaseInfo);
                orderAdapter.setNewData(orderInfoList);
            }
            if (orderBaseInfo.getNumberOfElements() < 10){
                srlOrderForm.setEnableLoadMore(false);
            }
        }
    }

    /**
     * 获取订单成功
     *
     * @param code
     * @param result
     */
    @Override
    public void getOrderSucess(int code, String result) {
        OrderBaseInfo orderBaseInfo_temp = JSON.parseObject(result, OrderBaseInfo.class);
        if (orderBaseInfo_temp.getNumberOfElements() == 0){
            srlOrderForm.finishLoadMore(true);
            srlOrderForm.setEnableLoadMore(false);
            ToastUtil.makeText(getContext(), "已加载全部数据", Toast.LENGTH_SHORT).show();
        }else if(orderBaseInfo_temp.getNumberOfElements() < 10){
            orderBaseInfo.getContent().addAll(orderBaseInfo_temp.getContent());
            srlOrderForm.finishLoadMore(true);
            orderAdapter.addData(orderDataPresenter.getOrderListFragment2(orderBaseInfo_temp));
            srlOrderForm.setEnableLoadMore(false);
            ToastUtil.makeText(getContext(), "已加载全部数据", Toast.LENGTH_SHORT).show();
        }else {
            srlOrderForm.finishLoadMore(666, true, false);
            orderAdapter.addData(orderDataPresenter.getOrderListFragment2(orderBaseInfo_temp));
            ToastUtil.makeText(getContext(), "加载了更多", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getOrderFaild(int code, String result) {

    }
}
