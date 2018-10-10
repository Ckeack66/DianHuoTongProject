package com.mhky.dianhuotong.goldgoods;

import com.lzy.okgo.model.HttpParams;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BasePresenter;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.DataModel;

/**
 * @author God_K
 * 所有黄金单品 请求 presenter类
 */

public class GoldGoodsPresenter extends BasePresenter<GoldGoodsView>{
    /**
     * 获取网络数据
     * @param params 参数
     */
    public  void getData(HttpParams params, final int isRefreshOrLoadMore){
        BaseTool.logPrint("ck_GoldGoodsPresenter","goldGoodsPresenter" + isViewAttached() );
        if (!isViewAttached()){
            //如果没有View引用就不加载数据
            return;
        }
//        //显示正在加载进度条
        getView().showLoading();
//        // 调用Model请求数据
//        GoldGoodsModel.execute(params, new BaseCallBack<SearchSGoodsBean>() {
////
//            @Override
//            public void onSuccess(SearchSGoodsBean data) {
////                getView().showData(data);
//            }
//
//            @Override
//            public void onFailure(String msg) {
////                getView().showToast(msg);
//            }
//
//            @Override
//            public void onError() {
////                getView().showErr();
//            }
//
//            @Override
//            public void onComplete() {
////                getView().hideLoading();
//            }
//        });
        DataModel
                //设置model
                .request(GoldGoodsModel.class)
                .params(params)
                .execute(new BaseCallBack<String>() {

                    @Override
                    public void onSuccess(String data) {
                        getView().showData(data,isRefreshOrLoadMore);
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
