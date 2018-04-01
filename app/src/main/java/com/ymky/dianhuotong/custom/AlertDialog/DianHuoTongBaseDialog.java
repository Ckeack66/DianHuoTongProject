package com.ymky.dianhuotong.custom.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.ymky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/1.
 */

public class DianHuoTongBaseDialog extends Dialog implements View.OnClickListener {
    private TextView textViewTitle;
    private TextView textViewBody;
    private TextView textViewLeft;
    private TextView textViewRight;
    private BaseDialogListener baseDialogListener;
    private String tag;

    public DianHuoTongBaseDialog(@NonNull Context context, BaseDialogListener baseDialogListener1) {
        super(context, R.style.custom_dialog);
        this.baseDialogListener = baseDialogListener1;
        View view = View.inflate(context, R.layout.dialog_base, null);
        textViewTitle = view.findViewById(R.id.base_dialog_title);
        textViewBody = view.findViewById(R.id.base_dialog_body);
        textViewLeft = view.findViewById(R.id.base_dialog_button_left);
        textViewRight = view.findViewById(R.id.base_dialog_button_right);
        setContentView(view);
        textViewLeft.setOnClickListener(this);
        textViewRight.setOnClickListener(this);

    }

    public DianHuoTongBaseDialog(@NonNull Context context, BaseDialogListener baseDialogListener1, String title, String body, String left, String right,String mtag) {
        this(context, baseDialogListener1);
        //super(context,R.style.custom_dialog);
        textViewTitle.setText(title);
        textViewBody.setText(body);
        textViewLeft.setText(left);
        textViewRight.setText(right);
        tag=mtag;

    }

    public DianHuoTongBaseDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DianHuoTongBaseDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.base_dialog_button_left:
                if (baseDialogListener != null) {
                    baseDialogListener.onClickBaseDialogLeft(tag);
                }
                break;
            case R.id.base_dialog_button_right:
                if (baseDialogListener != null) {
                    baseDialogListener.onClickBaseDialogRight(tag);
                }
                break;
        }
    }

    public interface BaseDialogListener {
        void onClickBaseDialogLeft(String iTag);

        void onClickBaseDialogRight(String iTag);
    }
}
