package com.mhky.dianhuotong.shop.precenter;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.bean.LastMinuteInfo;
import com.mhky.dianhuotong.shop.shopif.LastMinuteIF;

public class LastMinutePresenter {

    public LastMinutePresenter setLastMinuteIF(LastMinuteIF lastMinuteIF) {
        this.lastMinuteIF = lastMinuteIF;
        return this;
    }

    private LastMinuteIF lastMinuteIF;
    private static final String TAG = "LastMinutePresenter";

    public void getLastMinute(HttpParams httpParams) {
        httpParams.put("promotionTypes", "XIAN_SHI_XIAN_LIANG_ZHE_KOU");
        httpParams.put("status", true);
        OkGo.<String>get(BaseUrlTool.GET_LAST_MINUTE).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                try {
                    if (response.code() == 200) {
                        LastMinuteInfo lastMinuteInfo = JSON.parseObject(BaseTool.getResponsBody(response), LastMinuteInfo.class);
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int a = 0; a < lastMinuteInfo.getContent().size(); a++) {
                            for (int b = 0; b < lastMinuteInfo.getContent().get(a).getGoodsIds().size(); b++) {
                                stringBuilder.append(lastMinuteInfo.getContent().get(a).getGoodsIds().get(b));
                                stringBuilder.append(",");
                            }
                        }
                        if (stringBuilder.length() > 1) {
                            String s = stringBuilder.substring(0, stringBuilder.length() - 1);
                            HttpParams httpParams = new HttpParams();
                            httpParams.put("goodsIds", s);
                            searchGoods(httpParams);
                        }


                    }
                } catch (Exception e) {

                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {

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

    private void searchGoods(HttpParams httpParams) {
//        httpParams.put("size", 10);
        httpParams.put("shelves", true);
        httpParams.put("offShelves", false);
        httpParams.put("auditStatus", "APPROVED");
        OkGo.<String>get(BaseUrlTool.SEARCH_GOODS).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (lastMinuteIF != null) {
                    lastMinuteIF.getLastMinuteSuccess(response.code(), BaseTool.getResponsBody(response));
                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                BaseTool.logPrint(TAG, "onError: ---------");
                if (lastMinuteIF != null) {
                    lastMinuteIF.getLastMinuteFailed(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onFinish() {
                BaseTool.logPrint(TAG, "onFinish: ");
            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                BaseTool.logPrint(TAG, "convertResponse: ");
                return response.message();
            }
        });
    }

}
