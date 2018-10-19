package com.mhky.yaolinwang.presenter;

import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.DataModel;
import com.mhky.yaolinwang.model.GetDefaultAddressModel;
import com.mhky.yaolinwang.view.GetCustomerInfoView;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by Administrator  on  2018/10/16
 * Describe：获取C端默认收获地址Presenter
 */
public class GetCustomerDefaultAddressPresenter extends BasePresenter<GetCustomerInfoView> {
    public void getCustomerDefaultAddress(HttpParams httpParams){
        try {
            if(!isViewAttached()){
                return;
            }
            DataModel.request(GetDefaultAddressModel.class).params(httpParams)
                    .execute(new BaseCallBack<String>() {
                        @Override
                        public void onSuccess(String data) {
                            getView().getDefaultAdressSuccess(data);
                        }

                        @Override
                        public void onFailure(String msg) {
                            getView().getDefaultAdressFailed(msg);
                        }

                        @Override
                        public void onError() {

                        }

                        @Override
                        public void onComplete() {
                        }
                    });
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(BaseApplication.getContext(),e);
        }
    }
}
