package com.mhky.dianhuotong.promote;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;

public class PromotePresenter {
    public PromotePresenter setPromoteIF(PromoteIF promoteIF) {
        this.promoteIF = promoteIF;
        return this;
    }

    private PromoteIF promoteIF;

    public void getPromoteInfo() {
        if (BaseApplication.getInstansApp().getPersonInfo() != null) {
            OkGo.<String>get(BaseUrlTool.getPromote(BaseApplication.getInstansApp().getPersonInfo().getMobile())).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    if (promoteIF != null) {
                        promoteIF.getPromoteInfoSuccess(response.code(), BaseTool.getResponsBody(response));
                    }
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    if (promoteIF != null) {
                        promoteIF.getPromoteInfoFailed(response.code(), BaseTool.getResponsBody(response));
                    }
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

}
