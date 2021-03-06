package com.mhky.dianhuotong.custom.viewgroup;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhky.dianhuotong.R;


/**
 * Created by Administrator on 2017/7/14.
 */

public class DianHuoTongBaseTitleBar extends LinearLayout {
    private ImageView imageViewLeft;
    private TextView textView;
    private ImageView imageViewRight;
    private RelativeLayout relativeLayout;
    private TextView rightTextView;
    private Context mContext;

    public DianHuoTongBaseTitleBar(Context context) {
        super(context);
        mContext = context;
        inIt();

    }

    public DianHuoTongBaseTitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        inIt();
    }

    public DianHuoTongBaseTitleBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        inIt();
    }

    private void inIt() {
        LayoutInflater.from(mContext).inflate(R.layout.group_widget_title, this);
        imageViewLeft = findViewById(R.id.imageView_left);
        textView = findViewById(R.id.textView_center);
        imageViewRight = findViewById(R.id.imageView_right);
        rightTextView = findViewById(R.id.textView_right);
        relativeLayout = findViewById(R.id.titleBarRelativeLayoutRoot);
    }

    public void setLeftImage(int leftImageId) {
        imageViewLeft.setImageResource(leftImageId);
    }

    public void setCenterTextView(String txt) {
        textView.setText(txt);
    }

    public void setRightImage(int rightImageId) {
        rightTextView.setVisibility(GONE);
        imageViewRight.setImageResource(rightImageId);
    }

    public void setRightText(String text) {
        imageViewRight.setVisibility(GONE);
        rightTextView.setText(text);
    }

    public void setBackGround(int colorId) {
        relativeLayout.setBackgroundColor(colorId);
    }

    public void setLeftOnclickListener(OnClickListener onclickListenerL) {
        imageViewLeft.setOnClickListener(onclickListenerL);
    }

    public void setRightOnclickListener(OnClickListener onclickListenerR) {
        imageViewRight.setOnClickListener(onclickListenerR);
    }

    public void setRightTextOnclickListener(OnClickListener onclickListenerR) {
        rightTextView.setOnClickListener(onclickListenerR);
    }

    public void setRightTextViewListener(OnClickListener onClickListener) {
        rightTextView.setOnClickListener(onClickListener);
    }

}
