package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator  on  2018/9/5
 * Describe：
 */
public class Popwindow1InfoNew extends SectionEntity<GoodsCategories.ItemsBean> {

    private Popuwindow1ChildInfo popuwindow1ChildInfo;

    public Popwindow1InfoNew(boolean isHeader, String header,Popuwindow1ChildInfo popuwindow1ChildInfo) {
        super(isHeader, header);
        this.popuwindow1ChildInfo = popuwindow1ChildInfo;
    }

    public Popwindow1InfoNew(GoodsCategories.ItemsBean itemsBean) {
        super(itemsBean);
    }

    public Popuwindow1ChildInfo getPopuwindow1ChildInfo() {
        return popuwindow1ChildInfo;
    }
}
