package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.StarShopAddIF;
import com.mhky.dianhuotong.shop.shopif.StarShopDeleteIF;
import com.mhky.dianhuotong.shop.shopif.StarShopGetIF;
import com.mhky.dianhuotong.shop.shopif.StarShopIF;

import java.util.HashMap;

public class StarShopPrecenter {
    public void setStarShopIF(StarShopIF starShopIF) {
        this.starShopIF = starShopIF;
    }

    public StarShopPrecenter setStarShopGetIF(StarShopGetIF starShopGetIF) {
        this.starShopGetIF = starShopGetIF;
        return this;
    }

    public StarShopPrecenter setStarShopAddIF(StarShopAddIF starShopAddIF) {
        this.starShopAddIF = starShopAddIF;
        return this;
    }

    public StarShopPrecenter setStarShopDeleteIF(StarShopDeleteIF starShopDeleteIF) {
        this.starShopDeleteIF = starShopDeleteIF;
        return this;
    }

    private StarShopIF starShopIF;
    private StarShopGetIF starShopGetIF;
    private StarShopAddIF starShopAddIF;
    private StarShopDeleteIF starShopDeleteIF;



    public void getStarShop() {
        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getShopId() != null) {
            OkGo.<String>get(BaseUrlTool.GET_STAR_SHOP_ID_LIST + BaseApplication.getInstansApp().getPersonInfo().getShopId().toString()).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    if (starShopIF != null) {
                        starShopIF.getStarShopSuccess(response.code(), BaseTool.getResponsBody(response));
                    } else if (starShopGetIF != null) {
                        starShopGetIF.getStarShopSuccess(response.code(), BaseTool.getResponsBody(response));
                    }
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    if (starShopIF != null) {
                        starShopIF.getStarShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
                    } else if (starShopGetIF != null) {
                        starShopGetIF.getStarShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void addStarShop(String companyID) {
        if (BaseApplication.getInstansApp().getPersonInfo() != null && BaseApplication.getInstansApp().getPersonInfo().getShopId() != null) {
            HashMap hashMap = new HashMap();
            hashMap.put("shopId", BaseApplication.getInstansApp().getPersonInfo().getShopId().toString());
            hashMap.put("companyId", companyID);
            OkGo.<String>post(BaseUrlTool.ADD_STAR_SHOP + BaseTool.getUrlParamsByMap(hashMap, false)).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    if (starShopIF != null) {
                        starShopIF.addStarShopSuccess(response.code(), BaseTool.getResponsBody(response));
                    } else if (starShopAddIF != null) {
                        starShopAddIF.addStarShopSuccess(response.code(), BaseTool.getResponsBody(response));
                    }
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    if (starShopIF != null) {
                        starShopIF.addStarShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
                    } else if (starShopAddIF != null) {
                        starShopAddIF.addStarShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void delete(String id) {
        OkGo.<String>delete(BaseUrlTool.DELETE_STAR_SHOP + id).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (starShopIF != null) {
                    starShopIF.deleteStarShopSuccess(response.code(), BaseTool.getResponsBody(response));
                } else if (starShopDeleteIF != null) {
                    starShopDeleteIF.deleteStarShopSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (starShopIF != null) {
                    starShopIF.deleteStarShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
                } else if (starShopDeleteIF != null) {
                    starShopDeleteIF.deleteStarShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
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
