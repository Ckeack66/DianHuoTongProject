package com.mhky.dianhuotong.person;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;

/**
 * Created by Administrator on 2018/4/13.
 */

public class AlterPWDPersenter {

    private AlterPwdIF alterPwdIF;

    public AlterPWDPersenter(AlterPwdIF alterPwdIF) {
        this.alterPwdIF = alterPwdIF;
    }

    public void alterPwd(String pwdJson) {
        OkGo.<String>put(BaseUrlTool.getAlterPwdURL(BaseApplication.getInstansApp().getLoginRequestInfo().getId())).upJson(pwdJson).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                alterPwdIF.alterPwdSucess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                alterPwdIF.alterPwdFailed(response.code(), BaseTool.getResponsBody(response));
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
