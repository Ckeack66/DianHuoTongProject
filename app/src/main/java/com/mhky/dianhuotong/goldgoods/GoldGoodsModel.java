package com.mhky.dianhuotong.goldgoods;

import com.lzy.okgo.OkGo;


import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BaseModle;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.bean.SearchSGoodsBean;

/**
 * @author God_K
 * 所有黄金单品  Model层
 */

public class GoldGoodsModel  extends BaseModle<SearchSGoodsBean>{

    @Override
    public  void execute(final BaseCallBack callback) {
        OkGo.<String>get(BaseUrlTool.SEARCH_GOODS).params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
//                        BaseTool.logPrint("body_ckck",response.body());
                        int code = response.code();
                        if(code == 200){
                            callback.onSuccess(response.body());
                        }else {
                            callback.onFailure(response.body());
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                    }
                });
    }

//    public static void execute(final HttpParams httpParams,final BaseCallBack callback) {
//        OkGo.<String>get(BaseUrlTool.SEARCH_GOODS).params(httpParams)
//                .execute(new Callback<String>() {
//                    @Override
//                    public void onStart(Request<String, ? extends Request> request) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        BaseTool.logPrint("ck_body","body =" + response.body());
//                    }
//
//                    @Override
//                    public void onCacheSuccess(Response<String> response) {
//
//                    }
//
//                    @Override
//                    public void onError(Response<String> response) {
//
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//
//                    @Override
//                    public void uploadProgress(Progress progress) {
//
//                    }
//
//                    @Override
//                    public void downloadProgress(Progress progress) {
//
//                    }
//
//                    @Override
//                    public String convertResponse(okhttp3.Response response) throws Throwable {
//                        return null;
//                    }
//                });


//        new JsonCallback<SearchSGoodsBean>(SearchSGoodsBean.class) {
//            @Override
//            public void onSuccess(Response<SearchSGoodsBean> response) {
//                int code = response.getRawResponse().code();
//                if(code == 200){
//                    callback.onSuccess(response);
//                }else {
//
//                }
//            }
//
//            @Override
//            public void onError(Response<SearchSGoodsBean> response) {
//                super.onError(response);
//            }
//        }
//    }

}
