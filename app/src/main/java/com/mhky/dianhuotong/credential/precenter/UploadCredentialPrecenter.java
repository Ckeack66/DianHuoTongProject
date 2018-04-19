package com.mhky.dianhuotong.credential.precenter;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.credential.credentialif.UploadCredentialIF;

/**
 * Created by Administrator on 2018/4/17.
 */

public class UploadCredentialPrecenter {
    private UploadCredentialIF uploadCredentialIF;
    private static final String TAG = "UploadCredentialPrecent";

    public UploadCredentialPrecenter(UploadCredentialIF uploadCredentialIF) {
        this.uploadCredentialIF = uploadCredentialIF;
    }

    public void getImageUplaodUrl(HttpParams httpParams) {
        OkGo.<String>post(BaseUrlTool.UPLOAD_IMAGE).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                uploadCredentialIF.updataCredentialImageSucess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                uploadCredentialIF.updataCredentialImageFailed(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onFinish() {

            }

            @Override
            public void uploadProgress(Progress progress) {
                Log.d(TAG, "uploadProgress: --");
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

    private void uploadCredential(String jsons){
        OkGo.<String>post("").upJson(jsons).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {

            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {

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
