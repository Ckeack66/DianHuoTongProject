package com.mhky.dianhuotong.shop.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.GetAllCompanyIF;

/**
 * Created by Administrator on 2018/4/21.
 */

public class GetAllCompanyPresenter {
    private GetAllCompanyIF getAllCompanyIF;

    public GetAllCompanyPresenter(GetAllCompanyIF getAllCompanyIF) {
        this.getAllCompanyIF = getAllCompanyIF;
    }

    public void getAllCompany(HttpParams httpParams, boolean isSet) {
        if (!isSet) {
            httpParams.put("size", "100");
            httpParams.put("page", "0");
        }
        httpParams.put("sort", "name,DESC");
        httpParams.put("auditStatus", "APPROVED");
        OkGo.<String>get(BaseUrlTool.GET_ALL_COMPANY).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                getAllCompanyIF.getAllCompanyInfoSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                getAllCompanyIF.getAllCompanyInfoFailed(response.code(), BaseTool.getResponsBody(response));
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
}
