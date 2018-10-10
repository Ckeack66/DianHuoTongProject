package com.mhky.dianhuotong.base;

import android.content.Context;

/**
 * MVP    V层
 */

public interface BaseView {

    //显示正在加载Vview
    void showLoading();

    //隐藏正在加载View
    void hideLoading();

    //弹出toast
    void showToast(String msd);

    //显示请求错误提示
    void showErr();

    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();
}
