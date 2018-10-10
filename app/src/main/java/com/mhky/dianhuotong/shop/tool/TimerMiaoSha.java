package com.mhky.dianhuotong.shop.tool;

import android.os.CountDownTimer;

import com.mhky.dianhuotong.base.BaseTool;

import java.text.DecimalFormat;

/**
 * Created by Administrator on 2018/4/10.
 */

public class TimerMiaoSha extends CountDownTimer {

    private TimerMiaoShaListener timerMiaoShaListener;

    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimerMiaoSha(long millisInFuture, long countDownInterval, TimerMiaoShaListener timerMiaoShaListener1) {
        super(millisInFuture, countDownInterval);
        timerMiaoShaListener = timerMiaoShaListener1;
    }

    @Override
    public void onTick(long millisUntilFinished) {
//        String times[] = BaseTool.simpledateFommet(millisUntilFinished/1000);
        long times = millisUntilFinished / 1000;
        String hh = new DecimalFormat("00").format(times / 3600);
        String mm = new DecimalFormat("00").format(times % 3600 / 60);
        String ss = new DecimalFormat("00").format(times % 60);
        timerMiaoShaListener.onCountdowning(hh, mm, ss);
    }

    @Override
    public void onFinish() {
        timerMiaoShaListener.onCountDownfinish("00", "00", "00");
    }

    public interface TimerMiaoShaListener {
        void onCountdowning(String hh, String mm, String ss);

        void onCountDownfinish(String hh, String mm, String ss);
    }
}
