package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.adapter.Popupwindow1Adapter;
import com.mhky.dianhuotong.shop.adapter.Popupwindow3Adapter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.mhky.dianhuotong.shop.bean.Popuwindow1Info;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class CompanyPopupwindow extends PopupWindow {
    private List<AllCompanyInfo.ContentBean> contentBeanList;
    private RecyclerView recyclerView;
    private Popupwindow3Adapter popupwindow3Adapter;
    private static final String TAG = "CompanyPopupwindow";
    private OnClickPopupwindow3ItemListener onClickPopupwindow3ItemListener;

    public CompanyPopupwindow(final Context context, List<AllCompanyInfo.ContentBean> contentBeanList1) {
        super(context);
        contentBeanList = contentBeanList1;
        View view = View.inflate(context, R.layout.popupwindow_goods3, null);
        setContentView(view);
        recyclerView = view.findViewById(R.id.popupwindow_goods3_recycleview);
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(false);
        setTouchable(true);
        setClippingEnabled(false);
        setOutsideTouchable(false);
        setBackgroundDrawable(new ColorDrawable(0));
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        popupwindow3Adapter = new Popupwindow3Adapter(contentBeanList);
        popupwindow3Adapter.openLoadAnimation();
        recyclerView.setAdapter(popupwindow3Adapter);
        popupwindow3Adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onClickPopupwindow3ItemListener != null) {
                    onClickPopupwindow3ItemListener.onclick(contentBeanList.get(position));
                }
                List<AllCompanyInfo.ContentBean> data = (List<AllCompanyInfo.ContentBean>) adapter.getData();
                for (int a = 0; a < data.size(); a++) {
                    data.get(a).setSelect(false);
                }
                data.get(position).setSelect(true);
                popupwindow3Adapter.setNewData(data);
                dismiss();
            }
        });
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

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (Build.VERSION.SDK_INT >= 24) {
            Rect visibleFrame = new Rect();
            anchor.getGlobalVisibleRect(visibleFrame);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - visibleFrame.bottom;
            setHeight(height);
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    public void setOnClickPopupwindowItemListener(OnClickPopupwindow3ItemListener onClickPopupwindow3ItemListener1) {
        onClickPopupwindow3ItemListener = onClickPopupwindow3ItemListener1;
    }

    public interface OnClickPopupwindow3ItemListener {
        void onclick(AllCompanyInfo.ContentBean contentBean);
    }
}
