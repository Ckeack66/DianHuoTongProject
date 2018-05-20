package com.mhky.dianhuotong.shop.precenter;


import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.OrderOkIF;

import java.util.HashMap;

public class OrderOkPresenter {
    private OrderOkIF orderOkIF;

    public OrderOkPresenter(OrderOkIF orderOkIF) {
        this.orderOkIF = orderOkIF;
    }

    public void getFright(String id){
        HashMap<String,String> hashMap=new HashMap();
        hashMap.put("ids",id);
        OkGo.<String>get(BaseUrlTool.GET_FRIGHT+ BaseTool.getUrlParamsByMap(hashMap,false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                orderOkIF.getOrderFrightSucess(response.code(),BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                orderOkIF.getOrderFrightFaild(response.code(),BaseTool.getResponsBody(response));
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
