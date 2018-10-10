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
import com.mhky.dianhuotong.shop.shopif.ShopIF;
import com.mhky.dianhuotong.shop.shopif.ShopInfoIF;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/23.
 * 购物车presenter
 */

public class CartOpratePresenter implements ShopInfoIF {

    private CartOprateIF cartOprateIF;
    private ShopInfoPresenter shopInfoPresenter;
    private String goodsID;

    public CartOpratePresenter(CartOprateIF cartOprateIF) {
        this.cartOprateIF = cartOprateIF;
        shopInfoPresenter = new ShopInfoPresenter();
    }

    /**
     * 添加到购物车
     * @param map
     */
    public void addCart(Map map) {
        OkGo.<String>post(BaseUrlTool.ADD_CART + BaseTool.getUrlParamsByMap(map, false))
                .execute(new Callback<String>() {
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

    /**
     * 删除购物车商品
     * @param skuid
     */
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

    /**
     * 获取购物车内商品
     * @param Id                    user  id
     * @param type                  0：还未设置适配器    1：已设置适配器，只更新数据就行
     */
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

    /**
     * 更改购物车商品
     * @param hashMap
     * @param type
     */
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

    /**
     * 获取商品属性信息
     * @param goodsId
     */
    public void getSku(String goodsId) {
        goodsID = goodsId;
        if (BaseApplication.getInstansApp().getShopInfoByUserID() != null
                && BaseApplication.getInstansApp().getShopInfoByUserID().getRegionCode() != null
                && !"".equals(BaseApplication.getInstansApp().getShopInfoByUserID().getRegionCode())) {
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
        } else {
            shopInfoPresenter.getShopInfo();
        }

    }

    /**
     * 通过UserId获取店铺信息成功
     * @param code
     * @param result
     */
    @Override
    public void getShopInfoSuccess(int code, String result) {
        if (code==200){
            if (BaseApplication.getInstansApp().getShopInfoByUserID() != null && BaseApplication.getInstansApp().getShopInfoByUserID().getRegionCode() != null ){
                getSku(goodsID);
            }
        }
    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }
}
