package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.shopif.StarGoodsAddIF;
import com.mhky.dianhuotong.shop.shopif.StarGoodsDeleteIF;
import com.mhky.dianhuotong.shop.shopif.StarGoodsGetIF;
import com.mhky.dianhuotong.shop.shopif.StarGoodsIF;

public class StarGoodsPrecenter {
    private StarGoodsGetIF starGoodsGetIF;
    private StarGoodsAddIF starGoodsAddIF;
    private StarGoodsDeleteIF starGoodsDeleteIF;
    private StarGoodsIF starGoodsIF;

    public StarGoodsPrecenter setStarGoodsGetIF(StarGoodsGetIF starGoodsGetIF) {
        this.starGoodsGetIF = starGoodsGetIF;
        return this;
    }

    public StarGoodsPrecenter setStarGoodsAddIF(StarGoodsAddIF starGoodsAddIF) {
        this.starGoodsAddIF = starGoodsAddIF;
        return this;
    }

    public StarGoodsPrecenter setStarGoodsDeleteIF(StarGoodsDeleteIF starGoodsDeleteIF) {
        this.starGoodsDeleteIF = starGoodsDeleteIF;
        return this;
    }

    public StarGoodsPrecenter setStarGoodsIF(StarGoodsIF starGoodsIF) {
        this.starGoodsIF = starGoodsIF;
        return this;
    }

    private void getStarGoods() {
        OkGo.<String>get("").execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (starGoodsIF != null) {
                    starGoodsIF.getStarGoodsSuccess(response.code(), BaseTool.getResponsBody(response));
                } else if (starGoodsGetIF != null) {
                    starGoodsGetIF.getStarGoodsSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (starGoodsIF != null) {
                    starGoodsIF.getStarGoodsFailed(response.code(), BaseTool.getResponsBody(response));
                } else if (starGoodsGetIF != null) {
                    starGoodsGetIF.getStarGoodsFailed(response.code(), BaseTool.getResponsBody(response));
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

    private void addStarGoods() {
        OkGo.<String>post("").execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (starGoodsIF != null) {
                    starGoodsIF.addStarGoodsSuccess(response.code(), BaseTool.getResponsBody(response));
                } else if (starGoodsAddIF != null) {
                    starGoodsAddIF.addStarGoodsSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (starGoodsIF != null) {
                    starGoodsIF.addStarGoodsFailed(response.code(), BaseTool.getResponsBody(response));
                } else if (starGoodsAddIF != null) {
                    starGoodsAddIF.addStarGoodsFailed(response.code(), BaseTool.getResponsBody(response));
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

    private void deleteStarGoods() {
        OkGo.<String>delete("").execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (starGoodsIF != null) {
                    starGoodsIF.deleteStarGoodsSuccess(response.code(), BaseTool.getResponsBody(response));
                } else if (starGoodsDeleteIF != null) {
                    starGoodsDeleteIF.deleteStarGoodsSuccess(response.code(), BaseTool.getResponsBody(response));
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (starGoodsIF != null) {
                    starGoodsIF.deleteStarGoodsFailed(response.code(), BaseTool.getResponsBody(response));
                } else if (starGoodsDeleteIF != null) {
                    starGoodsDeleteIF.deleteStarGoodsFailed(response.code(), BaseTool.getResponsBody(response));
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
