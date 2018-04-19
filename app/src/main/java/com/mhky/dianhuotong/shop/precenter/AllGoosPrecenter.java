package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.AllGoodsIF;

/**
 * Created by Administrator on 2018/4/19.
 */

public class AllGoosPrecenter {
    private AllGoodsIF allGoodsIF;

    public AllGoosPrecenter(AllGoodsIF allGoodsIF) {
        this.allGoodsIF = allGoodsIF;
    }

    public void getAllGoodsType() {
        OkGo.<String>get(BaseUrlTool.GET_ALL_GOODS_TYPE).execute(new Callback<String>() {
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
}
