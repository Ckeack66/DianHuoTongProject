package com.mhky.dianhuotong.shop.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/28.
 * 购物车商品实体Title类    III
 */

public class CartTitleInfo {

    private boolean isSelectTitle = false;
    private boolean isViewTab = false;
    private int parentId;
    private CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean;
    private List<CartBaseInfo.GoodsItemsBean> cartItemInfoList;


    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

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


    public List<CartBaseInfo.GoodsItemsBean> getCartItemInfoList() {
        return cartItemInfoList;
    }

    public void setCartItemInfoList(List<CartBaseInfo.GoodsItemsBean> cartItemInfoList) {
        this.cartItemInfoList = cartItemInfoList;
    }
}
