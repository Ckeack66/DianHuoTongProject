package com.mhky.dianhuotong.shop.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mhky.dianhuotong.base.BaseApplication;

public class BanlanceReciver extends BroadcastReceiver {
    public BanlanceReciver setBanlanceReciverIF(BanlanceReciverIF banlanceReciverIF) {
        this.banlanceReciverIF = banlanceReciverIF;
        return this;
    }

    private BanlanceReciverIF banlanceReciverIF;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (BaseApplication.wxAction.equals(intent.getAction())){
            int resultCode = intent.getIntExtra("result", 0);
            if (banlanceReciverIF != null) {
                banlanceReciverIF.doBanlance(resultCode);
            }
        }
//        if (resultCode == 0) {
//            //支付失败
//            if (banlanceReciverIF != null) {
//
//            }
//        } else if (resultCode == 1) {
//            //支付成功
//            if (banlanceReciverIF != null) {
//
//            }
//        }
    }
}
