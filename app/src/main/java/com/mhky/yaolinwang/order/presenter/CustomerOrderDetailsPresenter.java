package com.mhky.yaolinwang.order.presenter;

import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.DataModel;
import com.mhky.yaolinwang.order.model.CustomerOrderDetailsModel;
import com.mhky.yaolinwang.order.view.CustomerOrderDetailsView;
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by Administrator  on  2018/10/19
 * Describe：获取C 端订单详情Presenterw
 */
public class CustomerOrderDetailsPresenter extends BasePresenter<CustomerOrderDetailsView> {

    public void getCustomerOrderDetails(HttpParams httpParams){
        try {
            if (!isViewAttached()){
                return;
            }
            getView().showLoading();
            DataModel.request(CustomerOrderDetailsModel.class).params(httpParams)
                    .execute(new BaseCallBack<String>() {
                        @Override
                        public void onSuccess(String data) {
                            getView().getCustomerOrderDetailsSuccess(data);
                        }

                        @Override
                        public void onFailure(String msg) {
                            getView().getCustomerOrderDetailsFailed(msg);
                        }

                        @Override
                        public void onError() {

                        }

                        @Override
                        public void onComplete() {
                            getView().hideLoading();
                        }
                    });
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(BaseApplication.getContext(),e);
        }
    }
}
