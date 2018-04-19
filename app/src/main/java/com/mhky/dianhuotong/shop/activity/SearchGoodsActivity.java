package com.mhky.dianhuotong.shop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.shop.adapter.SearchGoodsAdpter;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;
import com.mhky.dianhuotong.shop.precenter.SearchGoodsPresenter;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchGoodsActivity extends BaseActivity implements SearchGoodsIF {
    @BindView(R.id.search_recyclelistview)
    RecyclerView recyclerView;
    private SearchGoodsAdpter searchGoodsAdpter;
    private SearchGoodsPresenter searchGoodsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_goods);
        ButterKnife.bind(this);
        searchGoodsPresenter = new SearchGoodsPresenter(this);
        HttpParams httpParams = new HttpParams();
        httpParams.put("page", "0");
        httpParams.put("size", 10);
        httpParams.put("categoryIds", "1,2,3,23,24,25");
        httpParams.put("shelves", true);
        httpParams.put("offShelves", false);
        httpParams.put("auditStatus", "APPROVED");
        searchGoodsPresenter.searchGoods(httpParams);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    public void searchGoodsInfoSuccess(int code, String result) {
        if (code == 200) {
            SearchSGoodsBean searchSGoodsBean = JSON.parseObject(result, SearchSGoodsBean.class);
            searchGoodsAdpter = new SearchGoodsAdpter(searchSGoodsBean.getContent(), this);
            recyclerView.setAdapter(searchGoodsAdpter);
        }
    }

    @Override
    public void searchGoodsInfoFailed(int code, String result) {

    }
}
