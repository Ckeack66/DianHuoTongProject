package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.AllGoodsIF;
import com.mhky.dianhuotong.shop.shopif.GoodsCategoriesIF;

import java.io.IOException;

/**
 * Created by Administrator on 2018/4/19.
 */

public class AllGoosPrecenter {

    private AllGoodsIF allGoodsIF;

    private GoodsCategoriesIF goodsCategoriesIF;

    public AllGoosPrecenter(GoodsCategoriesIF goodsCategoriesIF) {
        this.goodsCategoriesIF = goodsCategoriesIF;
    }

    public AllGoosPrecenter(AllGoodsIF allGoodsIF, GoodsCategoriesIF goodsCategoriesIF) {
        this.allGoodsIF = allGoodsIF;
        this.goodsCategoriesIF = goodsCategoriesIF;
    }

    /**
     * 以前的获取类目的方法
     */
    public void getAllGoodsType() {
        OkGo.<String>get(BaseUrlTool.GET_ALL_GOODS_TYPE).
                execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                allGoodsIF.getAllGoodsInfoSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                allGoodsIF.getAllGoodsInfoFailed(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                return null;
            }
        });
    }

    /**
     * 新的获取类目的方法
     */
    public void getGoodsCategeries(HttpParams httpParams){
        OkGo.<String>get(BaseUrlTool.GET_GOODS_CATEGORIES)
                .params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        goodsCategoriesIF.getGoodsCategoriesSuccess(response.code(),response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        goodsCategoriesIF.getGoodsCategoriesFailed(response.code(),response.body());
                    }
                });
    }
}
