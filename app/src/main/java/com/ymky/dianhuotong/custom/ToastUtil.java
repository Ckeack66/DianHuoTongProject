package com.ymky.dianhuotong.custom;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ymky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/2.
 */

public class ToastUtil {
    private Toast mToast;

    private ToastUtil(Context context, CharSequence text, int duration) {
        View v = LayoutInflater.from(context).inflate(R.layout.toast_custom, null);
        TextView textView = (TextView) v.findViewById(R.id.toast_text);
        textView.setText(text);
        mToast = new Toast(context);
        mToast.setDuration(duration);
        mToast.setView(v);
//        ExplosionField explosionField = new ExplosionField(context, new FallingParticleFactory());
//        explosionField.addListener(textView);
//        explosionField.explode(textView);
//        textView.performLongClick();
    }

    public static ToastUtil makeText(Context context, CharSequence text, int duration) {
        return new ToastUtil(context, text, duration);
    }

    public void show() {
        if (mToast != null) {
            mToast.show();
        }
    }

    public void setGravity(int gravity, int xOffset, int yOffset) {
        if (mToast != null) {
            mToast.setGravity(gravity, xOffset, yOffset);
        }
    }

}
