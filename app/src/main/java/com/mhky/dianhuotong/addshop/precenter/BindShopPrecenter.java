package com.mhky.dianhuotong.addshop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.addshop.addshopif.BindShopIF;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.precenter.ShopInfoPresenter;

/**
 * Created by Administrator on 2018/4/15.
 */

public class BindShopPrecenter {
    private BindShopIF bindShopIF;
    private ShopInfoPresenter shopInfoPresenter;
    public BindShopPrecenter(BindShopIF bindShopIF) {
        this.bindShopIF = bindShopIF;
        shopInfoPresenter=new ShopInfoPresenter();
    }

    public void binShop(String json) {
        OkGo.<String>post(BaseUrlTool.ADD_USER_TO_SHOP).upJson(json).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                shopInfoPresenter.getShopInfo();
                bindShopIF.bindShopInfoSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                bindShopIF.bindShopInfoFailed(response.code(), BaseTool.getResponsBody(response));
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
