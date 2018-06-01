package com.mhky.dianhuotong.register;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;

/**
 * Created by Administrator on 2018/3/31.
 */

public class RegisterPrecenter {
    private static final String TAG = "RegisterPrecenter";
    private RegisterIF registerIF;

    public RegisterPrecenter(RegisterIF registerIF) {
        this.registerIF = registerIF;
    }

    public void register(String json) {
        OkGo.<String>post(BaseUrlTool.REGISTER).upJson(json).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (registerIF != null) {
                    registerIF.registerSuccess(response.code(), BaseTool.getResponsBody(response));
                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                BaseTool.logPrint(TAG, "onSuccess: " + response.code() + "-----" + response.body() + "----" + response.message());
            }

            @Override
            public void onError(Response<String> response) {
                if (registerIF != null) {
                    registerIF.registerFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void getMsm(String number) {
        OkGo.<String>get(BaseUrlTool.GET_SMS + number).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {

                registerIF.getSmsSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                registerIF.getSmsfailed(response.code(), BaseTool.getResponsBody(response));
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
                registerIF.checkSmsSuccess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                registerIF.checkSmsFailed(response.code(), BaseTool.getResponsBody(response));
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
