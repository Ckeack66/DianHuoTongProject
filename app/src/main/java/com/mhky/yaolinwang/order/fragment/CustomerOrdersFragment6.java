package com.mhky.yaolinwang.order.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseFragment;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.yaolinwang.adapter.CustomerOrderAdapter;
import com.mhky.yaolinwang.order.bean.CustomerOrder;
import com.mhky.yaolinwang.order.bean.CustomerOrderBean;
import com.mhky.yaolinwang.order.presenter.CustomerOrdersPresenter;
import com.mhky.yaolinwang.order.view.CustomerOrdersView;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 */
public class CustomerOrdersFragment6 extends BaseFragment implements CustomerOrdersView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @BindView(R.id.rv_customer_order)
    RecyclerView rvCustomerOrder;
    @BindView(R.id.srl_customer_order)
    SmartRefreshLayout srlCustomerOrder;
    Unbinder unbinder;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private CustomerOrdersPresenter customerOrdersPresenter;
    private List<BasePresenter> presenterList = new ArrayList<>();
    private List<CustomerOrder> list_customer_order = new ArrayList<>();
    private CustomerOrderAdapter customerOrderAdapter;
    private int page = 0;
    private boolean isFirst = true;

    public CustomerOrdersFragment6() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CustomerOrdersFragment6.
     */
    // TODO: Rename and change types and number of parameters
    public static CustomerOrdersFragment6 newInstance(String param1, String param2) {
        CustomerOrdersFragment6 fragment = new CustomerOrdersFragment6();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public List<BasePresenter> getPresenter() {
        return presenterList;
    }

    @Override
    public void initPresenter() {
        customerOrdersPresenter = new CustomerOrdersPresenter();
        presenterList.add(customerOrdersPresenter);
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
        View view = inflater.inflate(R.layout.fragment_customer_orders_fragment6, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initListener();
        initData();
        return view;
    }

    private void initView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerOrder.setLayoutManager(linearLayoutManager);
        customerOrderAdapter = new CustomerOrderAdapter(list_customer_order,getContext());
        customerOrderAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        customerOrderAdapter.setEmptyView(R.layout.empty_view,rvCustomerOrder);
        rvCustomerOrder.setAdapter(customerOrderAdapter);
        srlCustomerOrder.setRefreshHeader(new BezierRadarHeader(getContext()).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        srlCustomerOrder.setRefreshFooter(new BallPulseFooter(getContext()).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
    }

    private void initListener() {
        srlCustomerOrder.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                isFirst = false;
                srlCustomerOrder.setEnableRefresh(false);
                initData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                isFirst = false;
                srlCustomerOrder.setEnableLoadMore(false);
                initData();
            }
        });

        customerOrderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()){
                    case R.id.rl_order_top:
                        BaseTool.logPrint("rl_order_top",((CustomerOrder)adapter.getData().get(position)).getCustomerOrderTopInfo().getShopId());
                        break;
                    case R.id.rl_customer_order_body:
                        BaseTool.logPrint("rl_customer_order_body",((CustomerOrder)adapter.getData().get(position)).getOrderId());
                        break;
                    case R.id.btn_1:
                        switch (((TextView)view).getText().toString()){
                            case "去付款":
                                BaseTool.logPrint("btn_1",((TextView)view).getText().toString());
                                break;
                            case "申请退款":
                                BaseTool.logPrint("btn_1",((TextView)view).getText().toString());
                                break;
                            case "确认收货":
                                BaseTool.logPrint("btn_1",((TextView)view).getText().toString());
                                break;
                            case "去评价":
                                BaseTool.logPrint("btn_1",((TextView)view).getText().toString());
                                break;
                            case "查看进度":
                                BaseTool.logPrint("btn_1",((TextView)view).getText().toString());
                                break;
                        }
                        break;
                    case R.id.btn_2:
                        switch (((TextView)view).getText().toString()){
                            case "申请退款":
                                BaseTool.logPrint("btn_2",((TextView)view).getText().toString());
                                break;
                            case "查看配送":
                                BaseTool.logPrint("btn_2",((TextView)view).getText().toString());
                                break;
                        }
                        break;
                    case R.id.btn_3:
                        BaseTool.logPrint("btn_3",((TextView)view).getText().toString());
                        break;
                }
            }
        });
    }

    private void initData() {
        try {
            HttpParams httpParams = new HttpParams();
            httpParams.put("page",page);
            httpParams.put("size",10);
            httpParams.put("status","COMPLETED");
            customerOrdersPresenter.getCustomerOrders(httpParams);
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(getContext(),e);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void getCustomerOrdersSuccess(String data) {
        CustomerOrderBean customerOrderBean_temp = JSON.parseObject(data,CustomerOrderBean.class);
        int size = customerOrderBean_temp.getContent().size();
        if (customerOrderBean_temp != null){
            if (page == 0){
                if(size == 0){
                    srlCustomerOrder.setEnableLoadMore(false);
                    srlCustomerOrder.finishRefresh();
                    srlCustomerOrder.finishLoadMoreWithNoMoreData();
                }else if (size < 10){
                    srlCustomerOrder.setEnableLoadMore(false);
                    srlCustomerOrder.finishRefresh();
                    srlCustomerOrder.finishLoadMoreWithNoMoreData();
                    customerOrderAdapter.setNewData(customerOrdersPresenter.getCustomerOrderList(customerOrderBean_temp));
                }else if (size == 10){
                    srlCustomerOrder.setEnableLoadMore(true);
                    srlCustomerOrder.finishRefresh();
                    customerOrderAdapter.setNewData(customerOrdersPresenter.getCustomerOrderList(customerOrderBean_temp));
                }
                if(!isFirst){
                    ToastUtil.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
                }
            }else {
                if(size == 0){
                    srlCustomerOrder.setEnableRefresh(true);
                    srlCustomerOrder.setEnableLoadMore(false);
                    srlCustomerOrder.finishLoadMoreWithNoMoreData();
                    ToastUtil.makeText(getContext(), "已加载全部数据", Toast.LENGTH_SHORT).show();
                }else if (size < 10){
                    srlCustomerOrder.setEnableRefresh(true);
                    srlCustomerOrder.setEnableLoadMore(false);
                    srlCustomerOrder.finishLoadMoreWithNoMoreData();
                    customerOrderAdapter.addData(customerOrdersPresenter.getCustomerOrderList(customerOrderBean_temp));
                    ToastUtil.makeText(getContext(), "已加载全部数据", Toast.LENGTH_SHORT).show();
                }else if (size == 10){
                    srlCustomerOrder.finishLoadMore();
                    srlCustomerOrder.setEnableRefresh(true);
                    customerOrderAdapter.addData(customerOrdersPresenter.getCustomerOrderList(customerOrderBean_temp));
                    ToastUtil.makeText(getContext(), "加载了更多", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void getCustomerOrdersFailed(String data) {
        ToastUtil.makeText(getContext(),data, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
