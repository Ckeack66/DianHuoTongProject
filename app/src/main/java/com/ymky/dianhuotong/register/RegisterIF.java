package com.ymky.dianhuotong.register;

import com.lzy.okgo.model.Response;

/**
 * Created by Administrator on 2018/3/31.
 */

public interface RegisterIF {
    void registerSuccess(Response<String> response);

    void registerFailed(Response<String> response);

    void getSmsSuccess(Response<String> response);

    void getSmsfailed(Response<String> response);
}
