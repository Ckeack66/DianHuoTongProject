package com.mhky.dianhuotong.person.custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/12.
 */

public class AvatarScanHelperDialog extends Dialog {
    private String avatarUrl;
    private Context mContext;
    private SimpleDraweeView mSimpleDraweeView;


    public AvatarScanHelperDialog(Context context, String avatarUrl) {
        // 设置自定义样式
        super(context, R.style.CustomDialog_fill);
        this.mContext = context;
        this.avatarUrl = avatarUrl;
        initImageView(avatarUrl);
    }


    //直接使用imageview展示头像图片
    private void initImageView(String avatarUrl) {
        //重点在于用setContentView()加载自定义布局
        setContentView(R.layout.dialog_avatar_scan);
        mSimpleDraweeView = findViewById(R.id.simple_image);
        //fresco加载图片
        mSimpleDraweeView.setImageURI(Uri.parse(avatarUrl));
        //点击取消对话框
        mSimpleDraweeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        //todo:长按图片保存图片到相册
        setParams();
    }

    //设置对话框的宽高适应全屏
    private void setParams() {
        ViewGroup.LayoutParams lay = this.getWindow().getAttributes();
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindow().getWindowManager().getDefaultDisplay().getMetrics(dm);
        Rect rect = new Rect();
        View view = getWindow().getDecorView();
        view.getWindowVisibleDisplayFrame(rect);
        lay.height = dm.heightPixels - rect.top;
        lay.width = dm.widthPixels;

    }
}
