package com.mhky.dianhuotong.shop.precenter;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SearchGoodsPresenter {
    private SearchGoodsIF searchGoodsIF;
    private static final String TAG = "SearchGoodsPresenter";

    public SearchGoodsPresenter(SearchGoodsIF searchGoodsIF) {
        this.searchGoodsIF = searchGoodsIF;
    }

    public void searchGoods(HttpParams httpParams, final boolean isfirst, final int refreshOrLoadmore) {
        OkGo.<String>get(BaseUrlTool.SEARCH_GOODS).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                searchGoodsIF.searchGoodsInfoSuccess(response.code(), BaseTool.getResponsBody(response), isfirst, refreshOrLoadmore);
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                Log.d(TAG, "onError: ---------");
                searchGoodsIF.searchGoodsInfoFailed(response.code(), BaseTool.getResponsBody(response), isfirst, refreshOrLoadmore);
            }

            @Override
            public void onFinish() {
                Log.d(TAG, "onFinish: ");
            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                Log.d(TAG, "convertResponse: ");
                return response.message();
            }
        });
    }
}
