package com.mhky.yaolinwang.model;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BaseModle;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.yaolinwang.bean.CustomerAddressBean;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：获取默认收货地址
 */
public class GetDefaultAddressModel extends BaseModle<CustomerAddressBean> {
    @Override
    public void execute(final BaseCallBack callback) {
        try {
            OkGo.<String>get(BaseUrlTool.GET_DEFAULT_ADDRESS).params(httpParams)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            int code = response.code();
                            if (code == 200){
                                callback.onSuccess(response.body());
                            }else {
                                callback.onFailure("请求失败：" + code);
                            }
                        }

                        @Override
                        public void onError(Response<String> response) {
                            super.onError(response);
                            callback.onFailure("请求Error：" + response.code());
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
