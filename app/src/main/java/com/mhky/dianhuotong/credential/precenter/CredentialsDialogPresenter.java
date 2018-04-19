package com.mhky.dianhuotong.credential.precenter;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.credential.credentialif.CredentialsDialogIF;

/**
 * Created by Administrator on 2018/4/18.
 */

public class CredentialsDialogPresenter {
    private CredentialsDialogIF credentialsDialogIF;

    public CredentialsDialogPresenter(CredentialsDialogIF credentialsDialogIF) {
        this.credentialsDialogIF = credentialsDialogIF;
    }

    public void getCredentialDialogData() {
        OkGo.<String>get(BaseUrlTool.GET_CREDENTIAL_TYPE).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                credentialsDialogIF.getCredentialTypeSucess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                credentialsDialogIF.getCredentialTypeFailed(response.code(), BaseTool.getResponsBody(response));
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
