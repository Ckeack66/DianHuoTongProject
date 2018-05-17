package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.OrderOkAdapter;
import com.mhky.dianhuotong.shop.bean.OrderOkBotttomInfo;
import com.mhky.dianhuotong.shop.bean.OrderOkInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OderOkActivity extends BaseActivity implements OrderOkAdapter.GetEditWordsListenner {
    @BindView(R.id.order_ok_rcv)
    RecyclerView recyclerView;
    @BindView(R.id.order_ok_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    private OrderOkAdapter orderOkAdapter;
    private List<OrderOkInfo> orderOkInfoList;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oder_ok);
        mContext = this;
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        dianHuoTongBaseTitleBar.setCenterTextView("确认订单");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        orderOkInfoList = new ArrayList<>();
        OrderOkInfo orderOkInfo1 = new OrderOkInfo(1);
        OrderOkInfo orderOkInfo2 = new OrderOkInfo(2);
        OrderOkInfo orderOkInfo3 = new OrderOkInfo(3);
        OrderOkBotttomInfo orderOkBotttomInfo = new OrderOkBotttomInfo();
        orderOkInfo3.setOrderOkBotttomInfo(orderOkBotttomInfo);
        orderOkInfoList.add(orderOkInfo1);
        orderOkInfoList.add(orderOkInfo2);
        orderOkInfoList.add(orderOkInfo3);
        orderOkInfoList.add(orderOkInfo1);
        orderOkInfoList.add(orderOkInfo2);
        orderOkInfoList.add(orderOkInfo2);
        orderOkInfoList.add(orderOkInfo2);
        orderOkInfoList.add(orderOkInfo3);
        orderOkInfoList.add(orderOkInfo1);
        orderOkInfoList.add(orderOkInfo2);
        orderOkInfoList.add(orderOkInfo2);
        orderOkInfoList.add(orderOkInfo3);
        orderOkAdapter = new OrderOkAdapter(orderOkInfoList, this, this);
        orderOkAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtil.makeText(mContext, "点击了-" + position, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(orderOkAdapter);
    }

    @Override
    public void getEditData(String s, int position) {
        if (orderOkAdapter != null) {
            orderOkInfoList.get(position).getOrderOkBotttomInfo().setWords(s);
//            orderOkAdapter.notifyDataSetChanged();
//            orderOkAdapter.notifyItemChanged(position);
        }
    }
}
