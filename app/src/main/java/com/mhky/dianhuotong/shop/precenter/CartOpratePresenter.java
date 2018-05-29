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
import com.mhky.dianhuotong.shop.shopif.CartOprateIF;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/23.
 */

public class CartOpratePresenter {
    private CartOprateIF cartOprateIF;

    public CartOpratePresenter(CartOprateIF cartOprateIF) {
        this.cartOprateIF = cartOprateIF;
    }

    public void addCart(Map map) {
        OkGo.<String>post(BaseUrlTool.ADD_CART + BaseTool.getUrlParamsByMap(map, false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                cartOprateIF.addCartSucess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                cartOprateIF.addCartFaild(response.code(), BaseTool.getResponsBody(response));
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

    public void deleteCart(String skuid) {
        if (BaseApplication.getInstansApp().getLoginRequestInfo() == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("buyerId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        hashMap.put("skuIds", skuid);
        OkGo.<String>delete(BaseUrlTool.DELETE_CART + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                cartOprateIF.deleteCartSucess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                cartOprateIF.deleteCartFaild(response.code(), BaseTool.getResponsBody(response));
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

    public void getCart(String Id, final int type) {
        OkGo.<String>get(BaseUrlTool.getCartInfo(Id)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                cartOprateIF.getCartSucess(response.code(), BaseTool.getResponsBody(response), type);
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                cartOprateIF.getCartFaild(response.code(), BaseTool.getResponsBody(response), type);
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

    public void alterCart(HashMap hashMap, final int type) {
        //buyerId=19&amount=100000000&checked=true&goodsId=23&skuId=5
        //http://192.168.2.158:9050/cart?buyerId=19&skuId=13&amount=15
        hashMap.put("buyerId", BaseApplication.getInstansApp().getLoginRequestInfo().getId());
        OkGo.<String>put(BaseUrlTool.ALTER_CART + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                cartOprateIF.alterCartSucess(response.code(), BaseTool.getResponsBody(response), type);
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                cartOprateIF.alterCartFaild(response.code(), BaseTool.getResponsBody(response), type);
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

    public void getSku(String goodsId) {
        if (BaseApplication.getInstansApp().getShopInfoByUserID().getRegionCode()!=null&&!"".equals(BaseApplication.getInstansApp().getShopInfoByUserID().getRegionCode())){
            HashMap hashMap = new HashMap();
            hashMap.put("regionCode", BaseApplication.getInstansApp().getShopInfoByUserID().getRegionCode());
            OkGo.<String>get(BaseUrlTool.getSkuInfo(goodsId) + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    cartOprateIF.getSkuSucess(response.code(), BaseTool.getResponsBody(response));
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    cartOprateIF.getSkuFaild(response.code(), BaseTool.getResponsBody(response));
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
