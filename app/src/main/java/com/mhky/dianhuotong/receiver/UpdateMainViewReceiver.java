package com.mhky.dianhuotong.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.mhky.dianhuotong.activity.MainActivity;

public class UpdateMainViewReceiver extends BroadcastReceiver {
    private UpdateMainViewIF updateMainViewIF;

    public UpdateMainViewReceiver(UpdateMainViewIF updateMainViewIF) {
        this.updateMainViewIF = updateMainViewIF;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MainActivity.action.equals(intent.getAction())){
            updateMainViewIF.updateview();
        }

    }
}
