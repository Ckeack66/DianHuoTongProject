package com.mhky.dianhuotong.base;



import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.HttpParams;

/**
 * BaseModle      （M层）
 */

public abstract class BaseModle<T> {

    //数据请求参数   HttpParams类参数
    protected HttpParams httpParams;

    //数据请求参数   String类参数
    protected String s;

    /**
     * 设置数据请求参数
     * @param httpParams 参数数组
     */
    public BaseModle params(HttpParams httpParams){
        this.httpParams = httpParams;
        return this;
    }

    /**
     * 设置数据请求参数
     * @param s 参数数组
     */
    public BaseModle params(String s){
        this.s = s;
        return this;
    }

    // 添加Callback并执行数据请求
    // 具体的数据请求由子类实现
    public abstract void execute(BaseCallBack callback);

    // 执行Get网络请求，此类看需求由自己选择写与不写
    protected void requestGetApi(String url,Callback<T> callback){

    }

    // 执行Post网络请求，此类看需求由自己选择写与不写
    protected void requestPostApi(String url,Callback<T> callback){

    }
}
