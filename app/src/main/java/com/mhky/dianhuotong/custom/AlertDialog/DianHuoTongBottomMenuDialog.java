package com.mhky.dianhuotong.custom.AlertDialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/8.
 */

public class DianHuoTongBottomMenuDialog extends Dialog implements View.OnClickListener {

    private DianHuoTongBottomMenuDialogListener dianHuoTongBottomMenuDialogListener;

    private TextView textViewCamera;
    private TextView textViewPhoto;
    private TextView textViewCancel;

    public DianHuoTongBottomMenuDialog(@NonNull Context context, DianHuoTongBottomMenuDialogListener dianHuoTongBottomMenuDialogListener1) {
        super(context, R.style.custom_dialog);
        this.dianHuoTongBottomMenuDialogListener = dianHuoTongBottomMenuDialogListener1;
        View view = View.inflate(context, R.layout.dialog_bottom_menu, null);
        textViewCamera = view.findViewById(R.id.bottom_menu_dialog_camera);
        textViewPhoto = view.findViewById(R.id.bottom_menu_dialog_photos);
        textViewCancel = view.findViewById(R.id.bottom_menu_dialog_cancel);
        setContentView(view);
        textViewCamera.setOnClickListener(this);
        textViewPhoto.setOnClickListener(this);
        textViewCancel.setOnClickListener(this);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public DianHuoTongBottomMenuDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected DianHuoTongBottomMenuDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bottom_menu_dialog_camera:
                dianHuoTongBottomMenuDialogListener.getCamera();
                break;
            case R.id.bottom_menu_dialog_photos:
                dianHuoTongBottomMenuDialogListener.getPhotos();
                break;
            case R.id.bottom_menu_dialog_cancel:
                dismiss();
                break;
        }
    }

    public interface DianHuoTongBottomMenuDialogListener {
        void getCamera();

        void getPhotos();
    }
}
