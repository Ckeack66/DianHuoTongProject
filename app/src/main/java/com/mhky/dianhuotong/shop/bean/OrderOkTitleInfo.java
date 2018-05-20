package com.mhky.dianhuotong.shop.bean;

public class OrderOkTitleInfo {
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    private String shopName;
    private String shopId;

    public CartBaseInfo.GoodsItemsBean.ShopDTOBean getShopDTOBean() {
        return shopDTOBean;
    }

    public void setShopDTOBean(CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean) {
        this.shopDTOBean = shopDTOBean;
    }

    private CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean;
}
