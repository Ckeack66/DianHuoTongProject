package com.ymky.dianhuotong.register.precenter;

import android.util.Log;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.ymky.dianhuotong.register.RegisterIF;

import org.json.JSONObject;

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
        OkGo.<String>post("http://192.168.2.158:9088/user").upJson(json).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (registerIF != null) {
                    registerIF.registerSuccess(response);
                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                Log.d(TAG, "onSuccess: " + response.code() + "-----" + response.body() + "----" + response.message());
            }

            @Override
            public void onError(Response<String> response) {
                if (registerIF != null) {
                    registerIF.registerFailed(response);
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
        OkGo.<String>get("http://192.168.2.158:9088/user/sms").params("mobile", "15612345678").execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                registerIF.getSmsSuccess(response);
            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                registerIF.getSmsfailed(response);
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
