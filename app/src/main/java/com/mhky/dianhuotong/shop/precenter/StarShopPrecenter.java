package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.shopif.StarShopAddIF;
import com.mhky.dianhuotong.shop.shopif.StarShopDeleteIF;
import com.mhky.dianhuotong.shop.shopif.StarShopGetIF;
import com.mhky.dianhuotong.shop.shopif.StarShopIF;
import com.squareup.picasso.Picasso;

public class StarShopPrecenter {
    private StarShopIF starShopIF;
    private StarShopGetIF starShopGetIF;
    private StarShopAddIF starShopAddIF;
    private StarShopDeleteIF starShopDeleteIF;

    public StarShopPrecenter(StarShopAddIF starShopAddIF) {
        this.starShopAddIF = starShopAddIF;
    }

    public StarShopPrecenter(StarShopDeleteIF starShopDeleteIF) {
        this.starShopDeleteIF = starShopDeleteIF;
    }

    public StarShopPrecenter(StarShopGetIF starShopGetIF) {
        this.starShopGetIF = starShopGetIF;
    }

    public StarShopPrecenter(StarShopIF starShopIF) {
        this.starShopIF = starShopIF;
    }

    public void getStarShop() {
        OkGo.<String>get("").execute(new Callback<String>() {
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

    public void addStarShop() {
        OkGo.<String>post("").execute(new Callback<String>() {
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

    public void delete() {
        OkGo.<String>delete("").execute(new Callback<String>() {
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
