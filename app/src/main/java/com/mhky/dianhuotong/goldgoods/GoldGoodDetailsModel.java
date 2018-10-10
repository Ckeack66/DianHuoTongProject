package com.mhky.dianhuotong.goldgoods;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.mhky.dianhuotong.base.BaseCallBack;
import com.mhky.dianhuotong.base.BaseModle;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.bean.GoodsInfo;

/**
 * Created by God_K  on  2018/8/20
 * Describe：黄金单品商品详情    Model层
 */
public class GoldGoodDetailsModel extends BaseModle<GoodsInfo> {
    @Override
    public void execute(final BaseCallBack callback) {
        OkGo.<String>get(BaseUrlTool.getGoodsInfo(s))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        int code = response.code();
                        if (code == 200){
                            callback.onSuccess(response.body());
                        }else {
                            callback.onFailure(response.body());
                        }
                    }

                });
    }
}
