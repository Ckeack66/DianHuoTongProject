package com.mhky.dianhuotong.shop.precenter;

import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.mhky.dianhuotong.base.BaseApplication;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.base.BaseUrlTool;
import com.mhky.dianhuotong.shop.shopif.MyWalletMoneyIF;

public class MyWalletPresenter {
    private MyWalletMoneyIF walletMoneyIF;

    public MyWalletPresenter setWalletMoneyIF(MyWalletMoneyIF walletMoneyIF) {
        this.walletMoneyIF = walletMoneyIF;
        return this;
    }

    public void getMyWallet() {
        BaseTool.logPrint("wallet",String.valueOf(BaseApplication.getInstansApp().getPersonInfo()));
        BaseTool.logPrint("wallet1",BaseApplication.getInstansApp().getPersonInfo().getMobile());
        BaseTool.logPrint("wallet2",String.valueOf(TextUtils.isEmpty(BaseApplication.getInstansApp().getPersonInfo().getMobile())));
        if (BaseApplication.getInstansApp().getPersonInfo() != null && !TextUtils.isEmpty(BaseApplication.getInstansApp().getPersonInfo().getMobile())) {
            BaseTool.logPrint("wallet3","执行");
            OkGo.<String>get(BaseUrlTool.getWalletMoney(BaseApplication.getInstansApp().getPersonInfo().getMobile())).execute(new Callback<String>() {
                @Override
                public void onStart(Request<String, ? extends Request> request) {

                }

                @Override
                public void onSuccess(Response<String> response) {
                    if (walletMoneyIF != null) {
                        walletMoneyIF.getMyWalletMoneySuccess(response.code(),BaseTool.getResponsBody(response));
                    }
                }

                @Override
                public void onCacheSuccess(Response<String> response) {

                }

                @Override
                public void onError(Response<String> response) {
                    if (walletMoneyIF != null) {
                        walletMoneyIF.getMyWalletMoneyFailed(response.code(),BaseTool.getResponsBody(response));
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


}
