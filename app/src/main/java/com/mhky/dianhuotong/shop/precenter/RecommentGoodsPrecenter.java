package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.RecommentIF;

public class RecommentGoodsPrecenter {
    private RecommentIF recommentIF;

    public RecommentGoodsPrecenter setRecommentIF(RecommentIF recommentIF) {
        this.recommentIF = recommentIF;
        return this;
    }

    public void getRecommentGoods(HttpParams httpParams) {
        OkGo.<String>get(BaseUrlTool.GET_RECOMMEND_GOODS).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (recommentIF != null) {
                    recommentIF.getRecommentSucess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (recommentIF != null) {
                    recommentIF.getRecommentFaild(response.code(), BaseTool.getResponsBody(response));
                }
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
