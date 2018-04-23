package com.mhky.dianhuotong.shop.adapter;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mhky.dianhuotong.R;
import com.mhky.dianhuotong.shop.bean.GoodsBaseInfo;
import com.mhky.dianhuotong.shop.bean.Popuwindow1Info;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21.
 */

public class Popupwindow1Adapter extends BaseSectionQuickAdapter<Popuwindow1Info, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public Popupwindow1Adapter(int layoutResId, int sectionHeadResId, List<Popuwindow1Info> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, Popuwindow1Info item) {
        helper.setText(R.id.popupwindow_goods_type_parent_title, item.header);
        helper.setText(R.id.popupwindow_goods_type_parent_number, item.getPopuwindow1ChildInfo().getNumber());
        helper.addOnClickListener(R.id.popupwindow_goods1_title);
    }

    @Override
    protected void convert(BaseViewHolder helper, Popuwindow1Info item) {
        GoodsBaseInfo.ChildrenBeanX childrenBeanX = item.t;
        helper.setText(R.id.popupwindow_goods_type_child_title, childrenBeanX.getName());
        helper.setText(R.id.popupwindow_goods_type_child_number, childrenBeanX.getChildren().size() + "");
        helper.addOnClickListener(R.id.popupwindow_goods1_child_title);
    }

}
