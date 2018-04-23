package com.mhky.dianhuotong.shop.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.AllCompanyInfo;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class Popupwindow3Adapter extends BaseQuickAdapter<AllCompanyInfo.ContentBean, BaseViewHolder> {
    public Popupwindow3Adapter(@Nullable List<AllCompanyInfo.ContentBean> data) {
        super(R.layout.item_popuwindow3_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllCompanyInfo.ContentBean item) {
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
