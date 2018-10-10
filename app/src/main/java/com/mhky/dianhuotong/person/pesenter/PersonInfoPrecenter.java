package com.mhky.dianhuotong.person.pesenter;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.person.bean.UserInfo;
import com.mhky.dianhuotong.person.personif.PersonIF;

/**
 * Created by Administrator on 2018/4/12.
 */

public class PersonInfoPrecenter {

    private PersonIF personIF;

    public PersonInfoPrecenter(PersonIF personIF) {
        this.personIF = personIF;
    }

    public PersonInfoPrecenter() {
    }

    public void getPersonInfo(String userID) {
        OkGo.<String>get(BaseUrlTool.getPersonInfoURL(userID)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (personIF != null) {
                    if (response.code() == 200) {
                        PersonInfo personInfo = JSON.parseObject(BaseTool.getResponsBody(response), PersonInfo.class);
                        BaseApplication.getInstansApp().setPersonInfo(personInfo);
                        personIF.getUserInfoSucess(personInfo);
                    }
                }else {
                    if (response.code() == 200) {
                        PersonInfo personInfo = JSON.parseObject(BaseTool.getResponsBody(response), PersonInfo.class);
                        BaseApplication.getInstansApp().setPersonInfo(personInfo);
                    }
                }
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                if (personIF != null) {
                    personIF.getUserinfoFailed(response.code(), BaseTool.getResponsBody(response));
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
