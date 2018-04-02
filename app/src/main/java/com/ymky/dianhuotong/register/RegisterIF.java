package com.ymky.dianhuotong.register;

import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/3/31.
 */

public interface RegisterIF {
    void registerSuccess(int code,String result);

    void registerFailed(int code,String result);

    void getSmsSuccess(int code,String result);

    void getSmsfailed(int code,String result);

    void checkSmsSuccess(int code,String result);

    void checkSmsFailed(int code,String result);
}
