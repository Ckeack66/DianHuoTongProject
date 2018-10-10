package com.mhky.dianhuotong.login;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.liqi.utils.encoding.MD5Util;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.person.personif.PersonIF;
import com.mhky.dianhuotong.person.pesenter.PersonInfoPrecenter;
import com.mhky.dianhuotong.shop.precenter.ShopInfoPresenter;
import com.mhky.dianhuotong.shop.shopif.ShopInfoIF;

/**
 * Created by Administrator on 2018/4/2.
 */

public class LoginPrecenter implements PersonIF, ShopInfoIF {
    private LoginIF loginIF;
    private PersonInfoPrecenter personInfoPrecenter;
    private static final String TAG = "LoginPrecenter";

    public LoginPrecenter(LoginIF loginIF) {
        this.loginIF = loginIF;
        personInfoPrecenter = new PersonInfoPrecenter(this);
    }

    public void Login(String phone, String pwd) {
        BaseTool.logPrint("login_ck",pwd);
        OkGo.<String>get(BaseUrlTool.LOGIN).params("value", phone).params("password", pwd).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                String redult = BaseTool.getResponsBody(response);
                if (response.code()==200){
                    if (loginIF != null) {
                        LoginRequestInfo loginRequestInfo = JSON.parseObject(redult, LoginRequestInfo.class);
                        BaseApplication.getInstansApp().setLoginRequestInfo(loginRequestInfo);
                        if (loginRequestInfo != null) {
                            personInfoPrecenter.getPersonInfo(loginRequestInfo.getId());
                        }
                    }

                }
                loginIF.LoginSucess(response.code(), redult);

            }

            @Override
            public void onCacheSuccess(Response<String> response) {
                BaseTool.logPrint(TAG, "onSuccess: " + response.code() + "-----" + response.body() + "----" + response.message());
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


    @Override
    public void getUserInfoSucess(PersonInfo userInfo) {

    }

    @Override
    public void getUserinfoFailed(int code, String result) {

    }

    @Override
    public void getShopInfoSuccess(int code, String result) {

    }

    @Override
    public void getShopInfoFailed(int code, String result) {

    }
}
