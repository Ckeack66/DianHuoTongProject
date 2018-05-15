package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.BillIF;

import java.util.HashMap;

public class BillPresenter {
    private BillIF billIF;

    public BillPresenter(BillIF billIF) {
        this.billIF = billIF;
    }

    public void getBill() {
        if (BaseApplication.getInstansApp().getLoginRequestInfo().getShopId() != null) {
            OkGo.<String>get(BaseUrlTool.GET_SHOP_BILL + BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString()).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    billIF.getBillSuccess(response.code(), BaseTool.getResponsBody(response));
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    billIF.getBillFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void addBill(String json) {
        OkGo.<String>post(BaseUrlTool.ADD_SHOP_BILL).upJson(json).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                billIF.addBillSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                billIF.addBillFailed(response.code(), BaseTool.getResponsBody(response));
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


    public void updateBill(String json) {
        if (BaseApplication.getInstansApp().getLoginRequestInfo() != null) {
//            hashMap.put("shopid", BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString());
            OkGo.<String>put(BaseUrlTool.GET_SHOP_BILL + BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString()).upJson(json).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    billIF.alterBillSuccess(response.code(), BaseTool.getResponsBody(response));
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    billIF.alterBillFailed(response.code(), BaseTool.getResponsBody(response));
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

}
