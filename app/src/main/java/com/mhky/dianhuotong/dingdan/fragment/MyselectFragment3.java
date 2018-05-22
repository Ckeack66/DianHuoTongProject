package com.mhky.dianhuotong.dingdan.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.dingdan.adapter.MyselectFragmentAdapter;
import com.mhky.dianhuotong.shop.activity.GoodsActivity;
import com.mhky.dianhuotong.shop.activity.OrderInfoActivity;
import com.mhky.dianhuotong.shop.activity.ShopActivity;
import com.mhky.dianhuotong.shop.adapter.OrderAdapter;
import com.mhky.dianhuotong.shop.bean.OrderBaseInfo;
import com.mhky.dianhuotong.shop.bean.OrderInfo;
import com.mhky.dianhuotong.shop.precenter.OrderDataPresenter;
import com.pgyersdk.crash.PgyCrashManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyselectFragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyselectFragment3 extends Fragment {
    @BindView(R.id.myselected_fragment3_rv)
    RecyclerView recyclerView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Unbinder unbinder;
    private OrderAdapter orderAdapter;
    private OrderDataPresenter orderDataPresenter;
    private List<OrderInfo> orderInfoList;
    private OrderBaseInfo orderBaseInfo;

    public MyselectFragment3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyselectFragment3.
     */
    // TODO: Rename and change types and number of parameters
    public static MyselectFragment3 newInstance(String param1, String param2, OrderBaseInfo orderBaseInfoInit) {
        MyselectFragment3 fragment = new MyselectFragment3();
        fragment.orderInfoList = new ArrayList<>();
        fragment.orderBaseInfo=orderBaseInfoInit;
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
        View view = inflater.inflate(R.layout.fragment_myselect_fragment3, container, false);
        unbinder = ButterKnife.bind(this, view);
        setData();
        return view;
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
        orderDataPresenter = new OrderDataPresenter();
        if (type == 0) {
            if (orderBaseInfo != null) {
                orderInfoList = orderDataPresenter.getOrderListFragment3(orderBaseInfo);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                orderAdapter = new OrderAdapter(orderInfoList, getActivity());
                orderAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                        switch (adapter.getItemViewType(position)) {
//                            case OrderInfo.TOP:
//                                ToastUtil.makeText(getActivity(),"点击了上部"+position, Toast.LENGTH_SHORT).show();
//                                break;
//                            case OrderInfo.BODY:
//                                ToastUtil.makeText(getActivity(),"点击了中部"+position, Toast.LENGTH_SHORT).show();
//                                break;
//                            case OrderInfo.BOTTOM:
//                                ToastUtil.makeText(getActivity(),"点击了下部"+position, Toast.LENGTH_SHORT).show();
//                                break;
//                        }
                    }
                });
                orderAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                    @Override
                    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                        switch (view.getId()) {
                            case R.id.order_head_go:
                                ToastUtil.makeText(getActivity(), "点击了店铺" + position, Toast.LENGTH_SHORT).show();
                                Bundle bundle = new Bundle();
                                bundle.putString("shopid", orderInfoList.get(position).getOrderTopInfo().getShopID());
                                BaseTool.goActivityWithData(getActivity(), ShopActivity.class, bundle);
                                break;
                            case R.id.order_body_goods:
                                try {
                                    Bundle bundle1 = new Bundle();
                                    bundle1.putString("order", orderBaseInfo.getContent().get(orderInfoList.get(position).getParentNumber()).getOrderCirculations().get(0).getOrderCirculationId().getOrderNo());
                                    BaseTool.goActivityWithData(getActivity(), OrderInfoActivity.class, bundle1);
                                }catch (Exception e){
                                    PgyCrashManager.reportCaughtException(getActivity(),e);
                                }
                                break;
                            case R.id.order_info_button:
                                ToastUtil.makeText(getActivity(), "点击了操作" + position, Toast.LENGTH_SHORT).show();
                                switch (orderInfoList.get(position).getOrderBottomInfo().getOrderStatus()) {
                                    case "ORDERED":
                                        ToastUtil.makeText(getActivity(), "待付款" + position, Toast.LENGTH_SHORT).show();
                                        break;
                                    case "PAID":
                                        ToastUtil.makeText(getActivity(), "已付款" + position, Toast.LENGTH_SHORT).show();
                                        break;
                                    case "COMPLETED":
                                        ToastUtil.makeText(getActivity(), "已完成" + position, Toast.LENGTH_SHORT).show();
                                        break;
                                    case "CANCELLED":
                                        ToastUtil.makeText(getActivity(), "已取消" + position, Toast.LENGTH_SHORT).show();
                                        break;
                                }
                                break;
                        }
                        switch (adapter.getItemViewType(position)) {
                            case OrderInfo.TOP:
                                break;
                            case OrderInfo.BODY:
                                break;
                            case OrderInfo.BOTTOM:
                                break;
                        }
                    }
                });
                recyclerView.setAdapter(orderAdapter);
            }

        } else if (type == 1) {
            if (orderBaseInfo != null) {
                orderInfoList = orderDataPresenter.getOrderList(orderBaseInfo);
                orderAdapter.setNewData(orderInfoList);
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
}
