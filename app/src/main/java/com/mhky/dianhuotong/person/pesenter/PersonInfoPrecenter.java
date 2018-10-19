package com.mhky.dianhuotong.person.pesenter;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.person.bean.PersonInfo;
import com.mhky.dianhuotong.person.bean.UserInfo;
import com.mhky.dianhuotong.person.personif.PersonIF;
import com.mhky.yaolinwang.bean.CustomerInfoBean;
import com.mhky.yaolinwang.view.GetCustomerInfoView;

/**
 * Created by Administrator on 2018/4/12.
 */

public class PersonInfoPrecenter {

    private PersonIF personIF;
    private GetCustomerInfoView getCustomerInfoView;

    public PersonInfoPrecenter(PersonIF personIF) {
        this.personIF = personIF;
    }

    public PersonInfoPrecenter(GetCustomerInfoView getCustomerInfoView) {
        this.getCustomerInfoView = getCustomerInfoView;
    }

    public PersonInfoPrecenter() {
    }

    /**
     * 获取下游B的账号信息实体
     * @param userID
     */
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

    /**
     * 获取Customer实体类  并存储sp
     * @param id
     */
    public void getCustomerInfo(String id){
        HttpParams httpParams = new HttpParams();
        httpParams.put("mixname",id);
        OkGo.<String>get(BaseUrlTool.GET_CUSTOMER_INFO).params(httpParams)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        int code = response.code();
                        if(code == 200){
                            CustomerInfoBean customerInfoBean = JSON.parseObject(response.body(),CustomerInfoBean.class);
                            BaseApplication.getInstansApp().setCustomerInfo(customerInfoBean);
                            BaseTool.logPrint("bodyck",response.body());
                            if (getCustomerInfoView != null){
                                getCustomerInfoView.getCustomerInfoSuccess(response.body());
                            }
                        }else {
                            if (getCustomerInfoView != null){
                                getCustomerInfoView.getCustomerInfoFailed("获取个人信息失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
                        getCustomerInfoView.getCustomerInfoFailed("获取个人信息Error：" + response.code());
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }
                });
    }
}
