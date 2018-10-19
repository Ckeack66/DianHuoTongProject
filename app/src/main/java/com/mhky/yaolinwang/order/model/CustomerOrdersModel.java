package com.mhky.yaolinwang.order.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BaseModle;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：获取C 端订单列表Model
 */
public class CustomerOrdersModel extends BaseModle {
    @Override
    public void execute(final BaseCallBack callback) {
        try {
            OkGo.<String>get(BaseUrlTool.GET_CUSTOMER_ORDERS).params(httpParams)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            int code = response.code();
                            if(code == 200){
                                callback.onSuccess(response.body());
                            }else{
                                callback.onFailure("请求失败:" + response.code());
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            callback.onFailure("请求error:" + response.code());
                        }

                        @Override
                        public void onFinish() {
                            super.onFinish();
                            callback.onComplete();
                        }
                    });
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(BaseApplication.getContext(),e);
        }
    }
}
