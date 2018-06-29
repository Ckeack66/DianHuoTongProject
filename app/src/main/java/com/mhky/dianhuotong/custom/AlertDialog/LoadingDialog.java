package com.mhky.dianhuotong.custom.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.mhky.dianhuotong.R;

public class LoadingDialog extends Dialog {

    private Context context;
    private ImageView ivProgress;

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        ivProgress =  findViewById(R.id.ivProgress);
    }

    public LoadingDialog(@NonNull Context context) {
        super(context,R.style.LoadingDialog);
        this.context = context;
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.rote_logding);
            ivProgress.startAnimation(animation);
        }
    }
}
