package com.mhky.dianhuotong.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 */

public class CartTitleInfo {
    private boolean isSelectTitle = false;
    private boolean isViewTab = false;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    private int parentId;

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

    public CartBaseInfo.GoodsItemsBean.ShopDTOBean getShopDTOBean() {
        return shopDTOBean;
    }

    public void setShopDTOBean(CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean) {
        this.shopDTOBean = shopDTOBean;
    }

    private CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean;

    public List<CartBaseInfo.GoodsItemsBean> getCartItemInfoList() {
        return cartItemInfoList;
    }

    public void setCartItemInfoList(List<CartBaseInfo.GoodsItemsBean> cartItemInfoList) {
        this.cartItemInfoList = cartItemInfoList;
    }

    private List<CartBaseInfo.GoodsItemsBean> cartItemInfoList;
}
