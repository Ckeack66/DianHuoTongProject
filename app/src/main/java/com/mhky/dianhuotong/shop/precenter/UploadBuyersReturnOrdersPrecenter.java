package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.UploadBuyersReturnOrdersIF;

/**
 * Created by Administrator  on  2018/9/18
 * Describe：上传回执单    Precenter
 */
public class UploadBuyersReturnOrdersPrecenter {
    private UploadBuyersReturnOrdersIF uploadBuyersReturnOrdersIF;
    private static final String TAG = "UploadBuyersReturnOrdersPrecenter";

    public UploadBuyersReturnOrdersPrecenter(UploadBuyersReturnOrdersIF uploadBuyersReturnOrdersIF) {
        this.uploadBuyersReturnOrdersIF = uploadBuyersReturnOrdersIF;
    }

    public void uploadBuyersReturnOrders(String orderNo, HttpParams httpParams){
        OkGo.<String>post(BaseUrlTool.uploadBuyersReturnOrders(orderNo))
                .params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        uploadBuyersReturnOrdersIF.uploadBuyerReturnOrdersSuccess(response.code(),response.body());
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        uploadBuyersReturnOrdersIF.uploadBuyerReturnOrdersFailed(response.code(),response.body());
                    }
                });
    }
}
