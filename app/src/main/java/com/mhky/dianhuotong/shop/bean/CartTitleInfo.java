package com.mhky.dianhuotong.shop.bean;

/**
 * Created by Administrator on 2018/4/28.
 */

public class CartTitleInfo {
    private boolean isSelectTitle = false;
    private boolean isViewTab = false;

    public boolean isSelectTitle() {
        return isSelectTitle;
    }

    public void setSelectTitle(boolean selectTitle) {
        isSelectTitle = selectTitle;
    }

    public boolean isViewTab() {
        return isViewTab;
    }

    public void setViewTab(boolean viewTab) {
        isViewTab = viewTab;
    }
}
