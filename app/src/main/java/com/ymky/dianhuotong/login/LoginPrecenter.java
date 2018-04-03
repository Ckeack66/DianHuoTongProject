package com.ymky.dianhuotong.login;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.ymky.dianhuotong.base.BaseTool;
import com.ymky.dianhuotong.base.BaseUrlTool;

/**
 * Created by Administrator on 2018/4/2.
 */

public class LoginPrecenter {
    private LoginIF loginIF;
    private static final String TAG = "LoginPrecenter";

    public LoginPrecenter(LoginIF loginIF) {
        this.loginIF = loginIF;
    }

    private void Login(String phone, String pwd) {
        OkGo.<String>post(BaseUrlTool.REGISTER).upJson("").execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (loginIF != null) {
                    loginIF.LoginSucess(response.code(), BaseTool.getResponsBody(response));
                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: " + response.code() + "-----" + response.body() + "----" + response.message());
            }

            @Override
            public void onError(Response<String> response) {
                if (loginIF != null) {
                    loginIF.LoginFailed(response.code(), BaseTool.getResponsBody(response));
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