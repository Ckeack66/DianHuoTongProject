package com.ymky.dianhuotong.custom.tool;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/3/31.
 */

public class TimerMessage extends CountDownTimer {
    private OnTimerListener onTimerListener;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimerMessage(long millisInFuture, long countDownInterval, OnTimerListener onTimerListener1) {
        super(millisInFuture, countDownInterval);
        this.onTimerListener = onTimerListener1;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        if (onTimerListener != null) {
            if (millisUntilFinished != 0) {
                onTimerListener.onTick("已发送"+millisUntilFinished / 1000 + "S");
            }


        }
    }

    @Override
    public void onFinish() {
        if (onTimerListener != null) {
            onTimerListener.onFinish("重新获取");
        }
    }

    public interface OnTimerListener {
        void onTick(String time);

        void onFinish(String fi);
    }
}
