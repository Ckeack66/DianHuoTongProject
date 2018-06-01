package com.mhky.dianhuotong.shop.precenter;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.Popuwindow1ChildInfo;
import com.mhky.dianhuotong.shop.bean.Popuwindow1Info;
import com.mhky.dianhuotong.shop.shopif.SearchGoodsIF;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/19.
 */

public class SearchGoodsPresenter {
    private SearchGoodsIF searchGoodsIF;
    private static final String TAG = "SearchGoodsPresenter";

    public SearchGoodsPresenter(SearchGoodsIF searchGoodsIF) {
        this.searchGoodsIF = searchGoodsIF;
    }

    public void searchGoods(HttpParams httpParams, final boolean isfirst, final int refreshOrLoadmore) {
        httpParams.put("size", 10);
        httpParams.put("shelves", true);
        httpParams.put("offShelves", false);
        httpParams.put("auditStatus", "APPROVED");
        OkGo.<String>get(BaseUrlTool.SEARCH_GOODS).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                searchGoodsIF.searchGoodsInfoSuccess(response.code(), BaseTool.getResponsBody(response), isfirst, refreshOrLoadmore);
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                BaseTool.logPrint(TAG, "onError: ---------");
                searchGoodsIF.searchGoodsInfoFailed(response.code(), BaseTool.getResponsBody(response), isfirst, refreshOrLoadmore);
            }

            @Override
            public void onFinish() {
                BaseTool.logPrint(TAG, "onFinish: ");
            }

            @Override
            public void uploadProgress(Progress progress) {

            }

            @Override
            public void downloadProgress(Progress progress) {

            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {
                BaseTool.logPrint(TAG, "convertResponse: ");
                return response.message();
            }
        });
    }

    public List<Popuwindow1Info> getPopupwindowData() {
        List<Popuwindow1Info> popuwindow1Infos = new ArrayList<>();
        List<GoodsBaseInfo> childrenBeanXList = BaseApplication.getInstansApp().getAllGoodsBaseInfos();
        if (childrenBeanXList != null) {
            for (int a = 0; a < childrenBeanXList.size(); a++) {
                Popuwindow1ChildInfo popuwindow1ChildInfo = new Popuwindow1ChildInfo();
                popuwindow1ChildInfo.setNumber(childrenBeanXList.get(a).getChildren().size() + "种");
                popuwindow1ChildInfo.setGoodsBaseInfo(childrenBeanXList.get(a));
                Popuwindow1Info popuwindow1Info = new Popuwindow1Info(true, childrenBeanXList.get(a).getName(), popuwindow1ChildInfo);
                popuwindow1Infos.add(popuwindow1Info);
                for (int b = 0; b < childrenBeanXList.get(a).getChildren().size(); b++) {
                    Popuwindow1Info popuwindow1Info1 = new Popuwindow1Info(childrenBeanXList.get(a).getChildren().get(b));
                    popuwindow1Infos.add(popuwindow1Info1);
                }
            }


        }
        return popuwindow1Infos;
    }

}
