package com.mhky.dianhuotong.goldgoods;

import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.DataModel;

/**
 * Created by God_K  on  2018/8/20
 * Describe：黄金单品 商品详情  presenter类
 */
public class GoldGoodDetailsPresenter extends BasePresenter<GoldGoodDetailsView> {
    /**
     * 获取网络数据
     * @param params 参数
     */
    public  void getData(String params){

        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }

        // 调用Model请求数据
        DataModel
                //设置Model
                .request(GoldGoodDetailsModel.class)
                .params(params)
                .execute(new BaseCallBack<String>() {
                    @Override
                    public void onSuccess(String data) {
                        getView().getGoldGoodDetailsSuccess(data);
                    }

                    @Override
                    public void onFailure(String msg) {
                        getView().showToast(msg);
                    }

                    @Override
                    public void onError() {
                        getView().showErr();
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }
                });
    }

}
