package com.mhky.dianhuotong.shop.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;
import com.mhky.dianhuotong.shop.bean.ShopTransferInfo;
import com.mhky.dianhuotong.shop.bean.ShopTypeInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/5/4.
 */

public class ShopTypePopupwindowAdapter extends BaseQuickAdapter<ShopTypeInfo, BaseViewHolder> {

    public ShopTypePopupwindowAdapter(@Nullable List<ShopTypeInfo> data) {
        super(R.layout.item_popuwindow3_item,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ShopTypeInfo item) {
        helper.setText(R.id.popupwindow_goods3_text, item.getName());
        if (item.isSelect()) {
            helper.getView(R.id.popupwindow_goods3_img).setVisibility(View.VISIBLE);
            helper.setTextColor(R.id.popupwindow_goods3_text, Color.parseColor("#04c1ab"));
        }else {
            helper.getView(R.id.popupwindow_goods3_img).setVisibility(View.GONE);
            helper.setTextColor(R.id.popupwindow_goods3_text, Color.parseColor("#333333"));
        }
    }
}
