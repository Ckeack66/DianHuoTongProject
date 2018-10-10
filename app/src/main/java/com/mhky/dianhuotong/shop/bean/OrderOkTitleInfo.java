package com.mhky.dianhuotong.shop.bean;

public class OrderOkTitleInfo {

    private String shopName;                //暂时没用到，直接用下方那个实体类就都有了
    private String shopId;                  //暂时没用到，直接用下方那个实体类就都有了
    private CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean;


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

    public CartBaseInfo.GoodsItemsBean.ShopDTOBean getShopDTOBean() {
        return shopDTOBean;
    }

    public void setShopDTOBean(CartBaseInfo.GoodsItemsBean.ShopDTOBean shopDTOBean) {
        this.shopDTOBean = shopDTOBean;
    }
}
