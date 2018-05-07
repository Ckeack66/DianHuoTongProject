package com.mhky.dianhuotong.shop.custom;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.adapter.Popupwindow3Adapter;
import com.mhky.dianhuotong.shop.adapter.ShopTypePopupwindowAdapter;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.mhky.dianhuotong.shop.bean.ShopTransferInfo;
import com.mhky.dianhuotong.shop.bean.ShopTypeInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class ShopTypePopupwindow extends PopupWindow {
    private List<ShopTypeInfo> contentBeanList;
    private RecyclerView recyclerView;
    private ShopTypePopupwindowAdapter shopTypePopupwindowAdapter;
    private static final String TAG = "CompanyPopupwindow";
    private OnClickShopPopupwindowItemListener onClickPopupwindow3ItemListener;

    public ShopTypePopupwindow(final Context context, List<ShopTypeInfo> contentBeanList1) {
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
        shopTypePopupwindowAdapter = new ShopTypePopupwindowAdapter(contentBeanList);
        shopTypePopupwindowAdapter.openLoadAnimation();
        recyclerView.setAdapter(shopTypePopupwindowAdapter);
        shopTypePopupwindowAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (onClickPopupwindow3ItemListener != null) {
                    onClickPopupwindow3ItemListener.onclickType(contentBeanList.get(position));
                }
                List<ShopTypeInfo> data = (List<ShopTypeInfo>) adapter.getData();
                for (int a = 0; a < data.size(); a++) {
                    data.get(a).setSelect(false);
                }
                data.get(position).setSelect(true);
                shopTypePopupwindowAdapter.setNewData(data);
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

    public void setOnClickPopupwindowItemListener(OnClickShopPopupwindowItemListener onClickPopupwindow3ItemListener1) {
        onClickPopupwindow3ItemListener = onClickPopupwindow3ItemListener1;
    }

    public interface OnClickShopPopupwindowItemListener {
        void onclickType(ShopTypeInfo contentBean);
    }
}
