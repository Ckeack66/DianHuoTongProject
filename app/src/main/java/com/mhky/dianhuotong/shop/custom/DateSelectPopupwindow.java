package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhky.dianhuotong.R;

/**
 * Created by Administrator on 2018/4/28.
 */

public class DateSelectPopupwindow extends PopupWindow {
    private int selectNumber;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private TextView textView1;
    private TextView textView2;
    private ImageView imageView1;
    private ImageView imageView2;
    private Context mContext;
    private static final String TAG = "SortPopupwindow";
    private SortPopupwindow.OnClickPopupwindow2ItemListener onClickPopupwindow2ItemListener;

    public DateSelectPopupwindow(final Context context, int selectNumber1) {
        super(context);
        mContext = context;
        View view = View.inflate(context, R.layout.popupwindow_goods2, null);
        setContentView(view);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(false);
        setTouchable(true);
        setClippingEnabled(false);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(0));
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    public void setOnClickPopupwindowItemListener(SortPopupwindow.OnClickPopupwindow2ItemListener onClickPopupwindow2ItemListener1) {
        onClickPopupwindow2ItemListener = onClickPopupwindow2ItemListener1;
    }

    public interface OnClickPopupwindow2ItemListener {
        void onclick(int number);
    }
}
