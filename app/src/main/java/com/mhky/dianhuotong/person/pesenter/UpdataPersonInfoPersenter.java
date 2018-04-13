package com.mhky.dianhuotong.person.pesenter;

import android.util.Log;
import android.widget.Toast;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.person.personif.UpdataPersonIF;

import org.json.JSONObject;

import java.io.File;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/12.
 */

public class UpdataPersonInfoPersenter {
    private static final String TAG = "UpdataPersonInfoPersent";
    private UpdataPersonIF updataPersonIF;

    public UpdataPersonInfoPersenter(UpdataPersonIF updataPersonIF) {
        this.updataPersonIF = updataPersonIF;
    }

    public void updatePersonInfo(Map map, String id, final String result) {
//        HttpParams httpParams
        OkGo.<String>put(BaseUrlTool.UPLOAD_PERSON_INFO+id+"?"+BaseTool.getUrlParamsByMap(map,false)).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                if (updataPersonIF != null) {
                    updataPersonIF.updataUserInfoSucess(response.code(), BaseTool.getResponsBody(response),result);
                } else {
                    Log.d(TAG, "onSuccess: --请求成功-回调异常");
                }

            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                updataPersonIF.updataUserinfoFailed(response.code(), BaseTool.getResponsBody(response));
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

    public void uploadeImage(HttpParams httpParams) {
        OkGo.<String>post(BaseUrlTool.UPLOAD_IMAGE).params(httpParams).execute(new Callback<String>() {
            @Override
            public void onStart(Request<String, ? extends Request> request) {

            }

            @Override
            public void onSuccess(Response<String> response) {
                updataPersonIF.updataUserImageSucess(response.code(), BaseTool.getResponsBody(response));
            }

            @Override
            public void onCacheSuccess(Response<String> response) {

            }

            @Override
            public void onError(Response<String> response) {
                updataPersonIF.updataUserImageFailed(response.code(), BaseTool.getResponsBody(response));
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
}
