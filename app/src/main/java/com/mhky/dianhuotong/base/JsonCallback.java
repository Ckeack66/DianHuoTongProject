package com.mhky.dianhuotong.base;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * OkGo的JsonCallback的自定义
 * @param <T>
 */

public abstract class JsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> tClass;

    public JsonCallback(Class<T> tClass) {
        this.tClass = tClass;
    }

    public JsonCallback(Type type) {
        this.type = type;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public T convertResponse(Response response) throws Throwable {

        ResponseBody body = response.body();
        if(body == null) return null;

//        T date = null;
//        Gson gson = new Gson();
//        JsonReader jsonReader = new JsonReader(body.charStream());
//        if (type != null) date = gson.fromJson(jsonReader,type);
//        if (tClass != null) date = gson.fromJson(jsonReader,tClass);
        T date = null;
        Gson gson = new Gson();
        if (type != null) date = gson.fromJson(body.string(),type);
        if (tClass != null) date = gson.fromJson(body.string(),tClass);

        return date;
    }

}
