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
import com.pgyersdk.crash.PgyCrashManager;

/**
 * Created by God_K  on  2018/9/3
 * Describe：整零库区   Popwindow
 */

public class IsRetailPopwindow extends PopupWindow implements View.OnClickListener{

    private int selectNumber;
    private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
    private TextView textView1;
    private TextView textView2;
    private ImageView imageView1;
    private ImageView imageView2;
    private Context mContext;
    private static final String TAG = "SortPopupwindow";
    private OnClickIsRetailPopupwindowItemListener onClickIsRetailPopupwindowItemListener;

    public IsRetailPopwindow(final Context context, int selectNumber1) {
        super(context);
        mContext = context;
        selectNumber = selectNumber1;
        View view = View.inflate(context, R.layout.popupwindow_goods2, null);
        setContentView(view);

        relativeLayout1 = view.findViewById(R.id.popupwindow_goods2_item1);
        textView1 = view.findViewById(R.id.popupwindow_goods2_item1_txt);
        imageView1 = view.findViewById(R.id.popupwindow_goods2_item1_img);
        relativeLayout2 = view.findViewById(R.id.popupwindow_goods2_item2);
        textView2 = view.findViewById(R.id.popupwindow_goods2_item2_txt);
        imageView2 = view.findViewById(R.id.popupwindow_goods2_item2_img);
        textView1.setText("整库区");
        textView2.setText("零库区");
        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(false);
        setTouchable(true);
        setClippingEnabled(false);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(0));
        try {
            showItemView(selectNumber1);
        }catch (Exception e){
            PgyCrashManager.reportCaughtException(context,e);
        }
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

    public void setSelectState(int selectNumber1) {
        selectNumber = selectNumber1;
    }

    private void showItemView(int selectNumbers) {
        if (selectNumbers == -1) {
            relativeLayout1.setClickable(true);
            relativeLayout2.setClickable(true);
            textView1.setTextColor(mContext.getResources().getColor(R.color.color333333));
            imageView1.setVisibility(View.GONE);
            return;
        }
        if (selectNumbers == 0) {
            hideAllView();
            textView1.setTextColor(mContext.getResources().getColor(R.color.color04c1ab));
            imageView1.setVisibility(View.VISIBLE);
            relativeLayout1.setClickable(false);
            relativeLayout2.setClickable(true);
        } else if (selectNumbers == 1) {
            hideAllView();
            textView2.setTextColor(mContext.getResources().getColor(R.color.color04c1ab));
            imageView2.setVisibility(View.VISIBLE);
            relativeLayout1.setClickable(true);
            relativeLayout2.setClickable(false);
        }
        onClickIsRetailPopupwindowItemListener.onclickIsRetail(selectNumbers);
        dismiss();
    }

    private void hideAllView() {
        textView1.setTextColor(mContext.getResources().getColor(R.color.color333333));
        textView2.setTextColor(mContext.getResources().getColor(R.color.color333333));
        imageView1.setVisibility(View.GONE);
        imageView2.setVisibility(View.GONE);
    }

    public void setClickIsRetailPopupwindowItemListener(OnClickIsRetailPopupwindowItemListener onClickIsRetailPopupwindowItemListener) {
        this.onClickIsRetailPopupwindowItemListener = onClickIsRetailPopupwindowItemListener;
    }


    public interface OnClickIsRetailPopupwindowItemListener {
        void onclickIsRetail(int number);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popupwindow_goods2_item1:
                showItemView(0);
                break;
            case R.id.popupwindow_goods2_item2:
                showItemView(1);
                break;
        }
    }
}
