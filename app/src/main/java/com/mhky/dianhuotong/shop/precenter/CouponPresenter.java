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
import com.mhky.dianhuotong.shop.shopif.CounponAddIF;
import com.mhky.dianhuotong.shop.shopif.CounponGetIF;
import com.mhky.dianhuotong.shop.shopif.CouponIF;

import java.util.HashMap;
import java.util.Map;

public class CouponPresenter {
    private CouponIF couponIF;
    private CounponGetIF counponGetIF;
    private CounponAddIF counponAddIF;

    public CouponPresenter setCouponIF(CouponIF couponIF) {
        this.couponIF = couponIF;
        return this;
    }

    public CouponPresenter setCounponGetIF(CounponGetIF counponGetIF) {
        this.counponGetIF = counponGetIF;
        return this;
    }

    public CouponPresenter setCounponAddIF(CounponAddIF counponAddIF) {
        this.counponAddIF = counponAddIF;
        return this;
    }

    public void getCoupon() {
        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getShopId() != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("shopId", BaseApplication.getInstansApp().getLoginRequestInfo().getShopId());
            hashMap.put("used", false);
            OkGo.<String>get(BaseUrlTool.GET_COUPON + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    if (couponIF != null) {
                        couponIF.getCouponSuccess(response.code(), BaseTool.getResponsBody(response));
                    } else if (counponGetIF != null) {
                        counponGetIF.getCouponSuccess(response.code(), BaseTool.getResponsBody(response));
                    }

                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    if (couponIF != null) {
                        couponIF.getCouponFailed(response.code(), BaseTool.getResponsBody(response));
                    } else if (counponGetIF != null) {
                        counponGetIF.getCouponFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void getCouponByPlatform(Map map) {
        map.put("promotionTypes", "PING_TAI_YOU_HUI_QUAN");
        map.put("status", true);
        OkGo.<String>get(BaseUrlTool.GET_PLATFORM_COUPON + BaseTool.getUrlParamsByMap(map, false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (counponGetIF != null) {
                    counponGetIF.getCouponSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (counponGetIF != null) {
                    counponGetIF.getCouponFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void getCouponByShop(Map hashMap) {
        hashMap.put("promotionTypes", "DIAN_PU_YOU_HUI_QUAN");
        hashMap.put("status", true);
        OkGo.<String>get(BaseUrlTool.GET_PLATFORM_COUPON + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (counponGetIF != null) {
                    counponGetIF.getCouponSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (counponGetIF != null) {
                    counponGetIF.getCouponFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void bindCouponByPlatform(Map map) {
        map.put("grads","signal");
        OkGo.<String>post(BaseUrlTool.BIND_COUPON_URL+BaseTool.getUrlParamsByMap(map,false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (counponAddIF != null) {
                    counponAddIF.addCouponSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (counponAddIF != null) {
                    counponAddIF.addCouponFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void bindCouponByShop(Map map) {
        map.put("grads","signal");
        OkGo.<String>post(BaseUrlTool.BIND_COUPON_URL+BaseTool.getUrlParamsByMap(map,false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (counponAddIF != null) {
                    counponAddIF.addCouponSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (counponAddIF != null) {
                    counponAddIF.addCouponFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void addCoupon() {

    }
//    http://192.168.2.158:9060/couponRecord?shopId=9&status=true
}
