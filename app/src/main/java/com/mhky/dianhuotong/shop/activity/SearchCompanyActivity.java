package com.mhky.dianhuotong.shop.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.view.BaseActivity;
import com.mhky.dianhuotong.shop.adapter.SearchCompanyAdapter;
import com.mhky.dianhuotong.shop.bean.SearchCompanyInfo;
import com.mhky.dianhuotong.shop.custom.DianHuoTongShopTitleBar;
import com.mhky.dianhuotong.shop.precenter.SearchCompanyPresenter;
import com.mhky.dianhuotong.shop.shopif.SearchCompanyIF;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCompanyActivity extends BaseActivity implements SearchCompanyIF {
    @BindView(R.id.search_company_title)
    DianHuoTongShopTitleBar dianHuoTongShopTitleBar;
    @BindView(R.id.search_company_rv)
    RecyclerView recyclerView;
    private SearchCompanyAdapter searchCompanyAdapter;
    private int type = 0;
    private int pageNumber=0;
    private int isNew = -1;
    private SearchCompanyPresenter searchCompanyPresenter;
    private SearchCompanyInfo searchCompanyInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_company);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        searchCompanyPresenter = new SearchCompanyPresenter(this);
        dianHuoTongShopTitleBar.setActivity(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }
    private void getInfo(){
        HttpParams httpParams=new HttpParams();

        searchCompanyPresenter.getCompany(httpParams);
    }

    @Override
    public void getCompanyListSuccess(int code, String result) {
        if (code == 200) {
            SearchCompanyInfo searchCompanyInfo1 = JSON.parseObject(result, SearchCompanyInfo.class);
            if (isNew == -1) {
                searchCompanyInfo = searchCompanyInfo1;
                searchCompanyAdapter = new SearchCompanyAdapter(searchCompanyInfo.getContent());
                recyclerView.setAdapter(searchCompanyAdapter);
            } else if (isNew == 0) {
                searchCompanyInfo = searchCompanyInfo1;
                searchCompanyAdapter.setNewData(searchCompanyInfo.getContent());
            } else if (isNew == 1) {
                if (searchCompanyInfo1.getContent().size() == 0) {

                } else if (searchCompanyInfo1.getContent().size() < 10) {

                }else{

                }
            }


        }
    }

    @Override
    public void getCompanyListFailed(int code, String result) {

    }
}
