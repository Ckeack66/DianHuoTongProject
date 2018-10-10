package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.BrandIF;

public class BrandPresenter {
    public BrandPresenter setBrandIF(BrandIF brandIF) {
        this.brandIF = brandIF;
        return this;
    }

    private BrandIF brandIF;

    public void getBrandInfo(HttpParams httpParams) {
        OkGo.<String>get(BaseUrlTool.GET_BRAND)
                .params(httpParams)
                .execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (brandIF!=null){
                    brandIF.getBrandSuccess(response.code(), BaseTool.getResponsBody(response));
                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (brandIF!=null){
                    brandIF.getBrandFailed(response.code(), BaseTool.getResponsBody(response));
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
