package com.mhky.dianhuotong.shop.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.custom.viewgroup.DianHuoTongBaseTitleBar;
import com.mhky.dianhuotong.shop.adapter.RecommendGoodsAdapter;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;
import com.mhky.dianhuotong.shop.bean.RecommendBean;
import com.mhky.dianhuotong.shop.custom.CartPopupwindow;
import com.mhky.dianhuotong.shop.precenter.GoodsPrecenter;
import com.mhky.dianhuotong.shop.precenter.RecommentGoodsPrecenter;
import com.mhky.dianhuotong.shop.shopif.GoodsIF;
import com.mhky.dianhuotong.shop.shopif.RecommentIF;
import com.pgyersdk.crash.PgyCrashManager;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.header.BezierRadarHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendActivity extends BaseActivity implements RecommentIF, GoodsIF {
    @BindView(R.id.recommend_title)
    DianHuoTongBaseTitleBar dianHuoTongBaseTitleBar;
    @BindView(R.id.recommend_rlv)
    RecyclerView recyclerViewRecommend;
    @BindView(R.id.recommend_srl)
    SmartRefreshLayout smartRefreshLayout;
    private SimpleDateFormat simpleDateFormat;
    private RecommentGoodsPrecenter recommentGoodsPrecenter;
    private RecommendGoodsAdapter recommendGoodsAdapter;
    private RecommendBean recommendBean;
    private GoodsPrecenter goodsPrecenter;
    private GoodsInfo goodsInfo;
    private CartPopupwindow cartPopupwindow;
    private Context mContext;
    private String dateNew;
    //条件筛选基础字段
    private int state = -1;//当前页面获取数据状态，-1代表初始状态，0代表刷新，1代表加载
    private String type1;
    private int page;
    private static final String TAG = "RecommendActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recomment);
        ButterKnife.bind(this);
        mContext = this;
        try {
            init();
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    private void init() {
        goodsPrecenter = new GoodsPrecenter(this);
        dianHuoTongBaseTitleBar.setCenterTextView("全部推荐");
        dianHuoTongBaseTitleBar.setLeftImage(R.drawable.icon_back);
        dianHuoTongBaseTitleBar.setLeftOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewRecommend.setLayoutManager(linearLayoutManager1);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        dateNew = simpleDateFormat.format(date);

        recommentGoodsPrecenter = new RecommentGoodsPrecenter().setRecommentIF(this);
        getData();
        setRefresh();
    }

    private void setRefresh() {
        smartRefreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true).setPrimaryColor(getResources().getColor(R.color.color04c1ab)).setAccentColor(getResources().getColor(R.color.colorWhite)));
        smartRefreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale).setAnimatingColor(getResources().getColor(R.color.color04c1ab)).setNormalColor(getResources().getColor(R.color.color04c1ab)));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                page = 0;
                state = 0;
                refreshLayout.setEnableLoadMore(true);
                getData();

            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                state = 1;
                getData();
            }
        });
    }

    private void getData() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("startDate", dateNew);
        httpParams.put("endDate", dateNew);
        httpParams.put("page", page);
        httpParams.put("size", 10);
        recommentGoodsPrecenter.getRecommentGoods(httpParams);
    }

    @Override
    public void getGoodsInfoSuccess(int code, String result) {
        try {
            if (code == 200) {
                if (result != null && !result.equals("")) {
                    if (cartPopupwindow != null && cartPopupwindow.isShowing()) {
                        cartPopupwindow.dismiss();
                    }
                    goodsInfo = JSON.parseObject(result, GoodsInfo.class);
                    cartPopupwindow = new CartPopupwindow(this, goodsInfo);
                    cartPopupwindow.showAtLocation(dianHuoTongBaseTitleBar, Gravity.BOTTOM, 0, 0);
                    //ToastUtil.makeText(mContext, searchSGoodsBean.getContent().get(position).getName(), Toast.LENGTH_SHORT).show();
                } else {
                    ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }

    }

    @Override
    public void getGoodsInfoFailed(int code, String result) {
        ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getRecommentSucess(int code, String result) {

        try {
            if (code == 200) {
                //                if (state == -1) {
//
//                } else if (state == 0) {
//
//                } else if (state == 1) {
//
//                }
                RecommendBean recommendBean1 = JSON.parseObject(result, RecommendBean.class);
                if (state == -1) {
                    recommendBean = recommendBean1;
                    recommendGoodsAdapter = new RecommendGoodsAdapter(mContext, recommendBean.getContent());
                    recommendGoodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                            try {
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("id", recommendBean.getContent().get(position).getId() + "");
                                BaseTool.goActivityWithData(mContext, GoodsActivity.class, bundle);
                            } catch (Exception e) {
                                PgyCrashManager.reportCaughtException(mContext, e);
                            }

                            //ToastUtil.makeText(mContext, "点击了父控件", Toast.LENGTH_SHORT).show();
                        }
                    });
                    recommendGoodsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                        @Override
                        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                            try {
                                BaseTool.logPrint(TAG, "onItemChildClick: ---" + position);
                                goodsPrecenter.getGoodsInfo(String.valueOf(recommendBean.getContent().get(position).getId()));
                            } catch (Exception e) {
                                PgyCrashManager.reportCaughtException(mContext, e);
                            }


                        }
                    });
                    recyclerViewRecommend.setAdapter(recommendGoodsAdapter);
                    recyclerViewRecommend.setHasFixedSize(true);
                    recyclerViewRecommend.setNestedScrollingEnabled(false);
                    page++;
                } else if (state == 0) {
                    recommendBean = recommendBean1;
                    recommendGoodsAdapter.setNewData(recommendBean1.getContent());
                    smartRefreshLayout.finishRefresh();
                    page++;
                } else if (state == 1) {
                    recommendBean.getContent().addAll(recommendBean1.getContent());
                    recommendGoodsAdapter.addData(recommendBean1.getContent());
                    smartRefreshLayout.finishLoadMore();
                    if (recommendBean1.getContent().size() < 10) {
                        smartRefreshLayout.setEnableLoadMore(false);
                        ToastUtil.makeText(mContext, "已加载全部数据了哦~", Toast.LENGTH_SHORT).show();
                    }
                    page++;
                }

            }
        } catch (Exception e) {
            PgyCrashManager.reportCaughtException(this, e);
        }
    }

    @Override
    public void getRecommentFaild(int code, String result) {
        ToastUtil.makeText(mContext, "获取信息失败！", Toast.LENGTH_SHORT).show();
    }
}
