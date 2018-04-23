package com.mhky.dianhuotong.shop.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Administrator on 2018/4/21.
 */

public class Popuwindow1Info extends SectionEntity<GoodsBaseInfo.ChildrenBeanX> {
    public Popuwindow1Info(boolean isHeader, String header, Popuwindow1ChildInfo popuwindow1ChildInfo1) {
        super(isHeader, header);
        popuwindow1ChildInfo = popuwindow1ChildInfo1;
    }

    public Popuwindow1Info(GoodsBaseInfo.ChildrenBeanX childrenBeanX) {
        super(childrenBeanX);
    }

    public Popuwindow1ChildInfo getPopuwindow1ChildInfo() {
        return popuwindow1ChildInfo;
    }

    public void setPopuwindow1ChildInfo(Popuwindow1ChildInfo popuwindow1ChildInfo) {
        this.popuwindow1ChildInfo = popuwindow1ChildInfo;
    }

    private Popuwindow1ChildInfo popuwindow1ChildInfo;
}
