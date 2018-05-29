package com.mhky.dianhuotong.advert;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;

import java.util.HashMap;

public class AdvertMainPresenter {
    private AdvertMainIF advertMainIF;

    public AdvertMainPresenter(AdvertMainIF advertMainIF) {
        this.advertMainIF = advertMainIF;
    }

    public void getAdvertMain() {
        HashMap hashMap = new HashMap();
        hashMap.put("key", "APP_FIRST_BANNER");
        OkGo.<String>get(BaseUrlTool.GET_ADVERT_MAIN + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                advertMainIF.getAdvertMainSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                advertMainIF.getAdvertMainFailed(response.code(), BaseTool.getResponsBody(response));
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
