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
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.custom.ToastUtil;
import com.mhky.dianhuotong.shop.adapter.Popupwindow1Adapter;
import com.mhky.dianhuotong.shop.bean.Popuwindow1Info;

import java.util.List;

/**
 * Created by Administrator on 2018/4/20.
 */

public class GoodsTypePopupwindow extends PopupWindow {
    private List<Popuwindow1Info> popuwindow1InfoList;
    private RecyclerView recyclerView;
    private Popupwindow1Adapter popupwindow1Adapter;
    private static final String TAG = "GoodsTypePopupwindow";
    private OnClickPopupwindow1ItemListener onClickPopupwindow1ItemListener;

    public GoodsTypePopupwindow(final Context context, List<Popuwindow1Info> popuwindow1InfoList1) {
        super(context);
        popuwindow1InfoList = popuwindow1InfoList1;
        View view = View.inflate(context, R.layout.popupwindow_goods1, null);
        setContentView(view);
        recyclerView = view.findViewById(R.id.popupwindow_goods1_recycleview);
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
        popupwindow1Adapter = new Popupwindow1Adapter(R.layout.item_popupwindow_goods_type_listview_child, R.layout.item_popupwindow_goods_type_listview_parent, popuwindow1InfoList);
        popupwindow1Adapter.openLoadAnimation();
        recyclerView.setAdapter(popupwindow1Adapter);
        popupwindow1Adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (popuwindow1InfoList.get(position).isHeader) {
                    Log.d(TAG, "onItemChildClick: 点击了头部" + position);
                } else {
                    Log.d(TAG, "onItemChildClick: 点击了item" + position);
                }
                if (onClickPopupwindow1ItemListener != null) {
                    onClickPopupwindow1ItemListener.onclick(popuwindow1InfoList.get(position));
                }
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

    public void setOnClickPopupwindowItemListener(OnClickPopupwindow1ItemListener onClickPopupwindow1ItemListener1) {
        onClickPopupwindow1ItemListener = onClickPopupwindow1ItemListener1;
    }

    public interface OnClickPopupwindow1ItemListener {
        void onclick(Popuwindow1Info popuwindow1Info);
    }
}