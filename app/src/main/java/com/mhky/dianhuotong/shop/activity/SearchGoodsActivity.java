package com.mhky.dianhuotong.shop.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.precenter.SearchGoodsPresenter;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchGoodsActivity extends BaseActivity implements SearchGoodsIF {
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
    private SearchGoodsAdpter searchGoodsAdpter;
    private SearchGoodsPresenter searchGoodsPresenter;
    private String type;
    private Bundle bundle;
    private String type3;
    private GoodsBaseInfo.ChildrenBeanX childrenBeanX;
    private int number = 0;
    private int chooseOldNumber = -1;
    private boolean tabIsOpen = false;
    private static final String TAG = "SearchGoodsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        ButterKnife.bind(this);
        inIt();
    }

    private void inIt() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                number = 0;
                refreshLayout.setEnableLoadMore(true);
                if (type != null && type.equals("102")) {
                    if (childrenBeanX != null) {
                        getData(getChildId(childrenBeanX.getChildren()), false, 1);
                    }
                } else if (type != null && type.equals("103")) {
                    if (type3 != null && !type3.equals("")) {
                        getData(type3, false, 1);
                    }
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
                }

            }
        });
        searchGoodsPresenter = new SearchGoodsPresenter(this);
        bundle = getIntent().getExtras();
        type = bundle.getString("type");
        if (type != null && type.equals("102")) {
            childrenBeanX = (GoodsBaseInfo.ChildrenBeanX) bundle.getSerializable("data");
            if (childrenBeanX != null) {
                getData(getChildId(childrenBeanX.getChildren()), true, 0);
            }
        } else if (type != null && type.equals("103")) {
            type3 = bundle.getString("data");
            if (type3 != null && !type3.equals("")) {
                getData(type3, true, 0);
            }
        }

    }

    private void getData(String childID, boolean isFirst, int refreshOrLoadmore) {
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", number);
        httpParams.put("size", 10);
        if (childID != null && !childID.equals("")) {
            httpParams.put("categoryIds", childID);
        }
        httpParams.put("shelves", true);
        httpParams.put("offShelves", false);
        httpParams.put("auditStatus", "APPROVED");
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

    @OnClick(R.id.goods_base_choose_tab1)
    void selectTab1() {
        setTabStateTrue(1);


    }

    @OnClick(R.id.goods_base_choose_tab2)
    void selectTab2() {
        setTabStateTrue(2);
    }

    @OnClick(R.id.goods_base_choose_tab3)
    void selectTab3() {
        setTabStateTrue(3);
    }

    @OnClick(R.id.goods_base_choose_tab4)
    void selectTab4() {

    }

    private void setTabStateTrue(int newNumber) {

        if (newNumber == chooseOldNumber && tabIsOpen) {
            setTabStateFalse(newNumber);
            return;
        } else if (newNumber == chooseOldNumber && !tabIsOpen) {
            setTabOpen(newNumber);
            return;
        } else if (newNumber != chooseOldNumber && !tabIsOpen) {
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        } else if (newNumber != chooseOldNumber && tabIsOpen) {
            setTabStateFalse(chooseOldNumber);
            setTabOpen(newNumber);
            chooseOldNumber = newNumber;
        }

    }

    private void setTabOpen(int number) {
        switch (number) {
            case 1:
                textViewChoose1.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewChoose1.setImageResource(R.drawable.icon_choose_selecte);
                tabIsOpen = true;
                break;
            case 2:
                textViewChoose2.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewChoose2.setImageResource(R.drawable.icon_choose_selecte);
                tabIsOpen = true;
                break;
            case 3:
                textViewChoose3.setTextColor(getResources().getColor(R.color.color04c1ab));
                imageViewChoose3.setImageResource(R.drawable.icon_choose_selecte);
                tabIsOpen = true;
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

    @Override
    public void searchGoodsInfoSuccess(int code, String result, boolean isfirst, int refreshOrLoadmore) {
        Log.d(TAG, "searchGoodsInfoSuccess: " + code);
        if (code == 200) {
            SearchSGoodsBean searchSGoodsBean = JSON.parseObject(result, SearchSGoodsBean.class);
            if (searchSGoodsBean != null && searchSGoodsBean.getContent().size() <= 0) {
                relativeLayoutTips.setVisibility(View.VISIBLE);
            }
            if (isfirst && refreshOrLoadmore == 0) {
                searchGoodsAdpter = new SearchGoodsAdpter(searchSGoodsBean.getContent(), this);
                searchGoodsAdpter.openLoadAnimation();
                recyclerView.setAdapter(searchGoodsAdpter);
            } else if (refreshOrLoadmore == 1) {
                searchGoodsAdpter.setNewData(searchSGoodsBean.getContent());
                smartRefreshLayout.finishRefresh(1000, true);
                ToastUtil.makeText(this, "刷新成功", Toast.LENGTH_SHORT).show();
            } else if (refreshOrLoadmore == 2) {
                number++;
                if (searchSGoodsBean.getContent().size() == 0) {
                    smartRefreshLayout.finishLoadMore(true);
                    smartRefreshLayout.setEnableLoadMore(false);
                    ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                } else if (searchSGoodsBean.getContent().size() < 10) {
                    searchGoodsAdpter.addData(searchSGoodsBean.getContent());
                    smartRefreshLayout.finishLoadMore(true);
                    smartRefreshLayout.setEnableLoadMore(false);
                    ToastUtil.makeText(this, "已加载全部数据", Toast.LENGTH_SHORT).show();
                } else {
                    searchGoodsAdpter.addData(searchSGoodsBean.getContent());
                    smartRefreshLayout.finishLoadMore(1000, true, false);
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
    }

    @Override
    public void searchGoodsInfoFailed(int code, String result, boolean isfirst, int refreshOrLoadmore) {
        Log.d(TAG, "searchGoodsInfoFailed: ");
        relativeLayoutTips.setVisibility(View.VISIBLE);
        if (refreshOrLoadmore == 1) {
            smartRefreshLayout.finishRefresh(1000, false);
        } else if (refreshOrLoadmore == 2) {
            smartRefreshLayout.finishLoadMore(false);
        }
        ToastUtil.makeText(this, "加载失败~", Toast.LENGTH_SHORT).show();
    }
}
