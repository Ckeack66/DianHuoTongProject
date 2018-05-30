package com.mhky.dianhuotong.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gjiazhe.wavesidebar.WaveSideBar;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.addshop.adapter.AddShopAdapter;
import com.mhky.dianhuotong.addshop.adapter.AddShopNewAdapter;
import com.mhky.dianhuotong.addshop.addshopif.AddShopIF;
import com.mhky.dianhuotong.addshop.bean.ShopBaseInfo;
import com.mhky.dianhuotong.addshop.precenter.AddShopPrecenter;
import com.mhky.dianhuotong.base.BaseActivityManager;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddShop1Activity extends BaseActivity implements WaveSideBar.OnSelectIndexItemListener, AddShopIF {
    @BindView(R.id.addshop1_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.addshop1_rv)
    RecyclerView recyclerView;
    @BindView(R.id.add_shop_refresh)
    SmartRefreshLayout smartRefreshLayout;
    private LinearLayoutManager linearLayoutManager;
    private AddShopNewAdapter addShopNewAdapter;
    private AddShopPrecenter addShopPrecenter;
    private String area;
    private List<ShopBaseInfo> shopBaseInfoList;
    private int number=0;
    private static final String TAG = "AddShop1Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shop1);
        ButterKnife.bind(this);
        try {
            inIt();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    private void inIt() {
        setRefresh();
        area = getIntent().getExtras().getString("area");
        BaseTool.logPrint(TAG, "inIt: ------"+area);
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setCenterTextView("选择店铺");
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        addShopPrecenter = new AddShopPrecenter(this);
        HttpParams httpParams = new HttpParams();
        httpParams.put("region", area);
        addShopPrecenter.getShopInfo(httpParams);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        BaseActivityManager.getInstance().addActivity(this);
        //addShopPrecenter.getShopInfo();
    }

    private void setRefresh() {
        smartRefreshLayout.setEnableLoadMore(false);
        smartRefreshLayout.setEnableRefresh(false);
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                number=0;
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    public void onSelectIndexItem(String index) {

    }


    @Override
    public void getShopInfoSuccess(int code, String result) {
        try {
            if (code == 200 && result != null && !result.equals("")) {
                shopBaseInfoList = JSON.parseArray(result, ShopBaseInfo.class);
                if (shopBaseInfoList != null) {
                    addShopNewAdapter = new AddShopNewAdapter(shopBaseInfoList);
                    addShopNewAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("shop", shopBaseInfoList.get(position));
                            bundle.putInt("state", 0);
                            BaseTool.goActivityWithData(AddShop1Activity.this, AddShop3Activity.class, bundle);
                        }
                    });
                    recyclerView.setAdapter(addShopNewAdapter);
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }
}
