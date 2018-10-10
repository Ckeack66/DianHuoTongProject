package com.mhky.dianhuotong.shop.shopif;

/**
 * Created by Administrator  on  2018/9/17
 * Describe：上传买家回执单
 */
public interface UploadBuyersReturnOrdersIF {

    void uploadBuyerReturnOrdersSuccess(int code, String result);

    void uploadBuyerReturnOrdersFailed(int code, String result);
}
