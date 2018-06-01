package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.base.BaseTool;
import com.mhky.dianhuotong.shop.adapter.BasePopupwindowAdapter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;

import java.util.List;

public class BasePopupwindow extends PopupWindow {
    private List<String> stringList;
    private RecyclerView recyclerView;
//    private Popupwindow3Adapter popupwindow3Adapter;
    private BasePopupwindowAdapter basePopupwindowAdapter;
    private static final String TAG = "CompanyPopupwindow";
    private BasePopupwindow.OnClickBaseItemListener OnClickBaseItemListener;

    public BasePopupwindow(final Context context, List<String> contentBeanList1) {
        super(context);
        stringList = contentBeanList1;
        View view = View.inflate(context, R.layout.popupwindow_base, null);
        setContentView(view);
        recyclerView = view.findViewById(R.id.popupwindow_base_recycleview);
        setWidth(RadioGroup.LayoutParams.WRAP_CONTENT);
        setHeight(50);
//        setFocusable(false);
        setTouchable(true);
        setClippingEnabled(false);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        basePopupwindowAdapter=new BasePopupwindowAdapter(stringList);
        basePopupwindowAdapter.openLoadAnimation();
        recyclerView.setAdapter(basePopupwindowAdapter);
        basePopupwindowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (OnClickBaseItemListener != null) {
                    OnClickBaseItemListener.onclick(stringList.get(position),position);
                }
                dismiss();
            }
        });
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (Build.VERSION.SDK_INT == 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    public void setOnClickPopupwindowItemListener(BasePopupwindow.OnClickBaseItemListener OnClickBaseItemListener1) {
        OnClickBaseItemListener = OnClickBaseItemListener1;
    }

    public interface OnClickBaseItemListener {
        void onclick(String s,int position);
    }
}
