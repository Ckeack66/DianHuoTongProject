package com.mhky.dianhuotong.login;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;

/**
 * Created by Administrator on 2018/4/18.
 */

public class ForgetPasswordPrecenter {
    private ForgetPassWordIF forgetPassWordIF;

    public ForgetPasswordPrecenter(ForgetPassWordIF forgetPassWordIF) {
        this.forgetPassWordIF = forgetPassWordIF;
    }

    public void forGetPwd(String id, String phone) {
        OkGo.<String>put(BaseUrlTool.getChangePhoneUrl(id)).upString(phone).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                forgetPassWordIF.forgetPwdSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                forgetPassWordIF.forgetPwdFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void getMsm(String number) {
        OkGo.<String>get(BaseUrlTool.GET_SMS + number).retryCount(0).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {

                forgetPassWordIF.getForgetPwdPhoneSmsSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                forgetPassWordIF.getForgetPwdPhoneSmsFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void checkSMS(String phone, String code) {
        OkGo.<String>get(BaseUrlTool.CHECK_SMS + BaseTool.changeUrlParameter(phone) + BaseTool.changeUrlParameter(code)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                forgetPassWordIF.checkForgetPwdPhoneSmsSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                forgetPassWordIF.checkForgetPwdPhoneSmsFailed(response.code(), BaseTool.getResponsBody(response));
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
