package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.ShopAdressIF;

/**
 * 通过店铺ID获取商铺信息
 */

public class ShopAdressPresenter {
    private ShopAdressIF shopAdressIF;

    public ShopAdressPresenter(ShopAdressIF shopAdressIF) {
        this.shopAdressIF = shopAdressIF;
    }

    public void getShopAdress() {
        if (BaseApplication.getInstansApp().getLoginRequestInfo().getShopId() != null) {
            OkGo.<String>get(BaseUrlTool.GET_SHOP_ADRESS+BaseApplication.getInstansApp().getLoginRequestInfo().getShopId().toString()).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    shopAdressIF.getShopAdressSuccess(response.code(), BaseTool.getResponsBody(response));
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

    }
}
