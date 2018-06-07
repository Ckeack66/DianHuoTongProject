package com.mhky.dianhuotong.shop.precenter;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.bean.ShopInfoByUserID;
import com.mhky.dianhuotong.shop.shopif.ShopInfoIF;

/**
 * Created by Administrator on 2018/5/11.
 */

public class ShopInfoPresenter {
    private ShopInfoIF shopInfoIF;


    public ShopInfoPresenter(ShopInfoIF shopInfoIF) {
        this.shopInfoIF = shopInfoIF;
    }

    public ShopInfoPresenter() {
    }

    public void getShopInfo() {
        if (BaseApplication.getInstansApp().getLoginRequestInfo()!=null){
            OkGo.<String>get(BaseUrlTool.getShopInfo(BaseApplication.getInstansApp().getLoginRequestInfo().getId())).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    if (response.code() == 200) {
                        ShopInfoByUserID shopInfoByUserID = JSON.parseObject(BaseTool.getResponsBody(response), ShopInfoByUserID.class);
                        BaseApplication.getInstansApp().setShopInfoByUserID(shopInfoByUserID);
                        if (shopInfoIF!=null){
                            shopInfoIF.getShopInfoSuccess(response.code(),BaseTool.getResponsBody(response));
                        }
                    }else {
                        BaseApplication.getInstansApp().setShopInfoByUserID(null);
                        if (shopInfoIF!=null){
                            shopInfoIF.getShopInfoFailed(response.code(),BaseTool.getResponsBody(response));
                        }
                    }
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    BaseApplication.getInstansApp().setShopInfoByUserID(null);
                    if (shopInfoIF!=null){
                        shopInfoIF.getShopInfoFailed(response.code(),BaseTool.getResponsBody(response));
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
}
