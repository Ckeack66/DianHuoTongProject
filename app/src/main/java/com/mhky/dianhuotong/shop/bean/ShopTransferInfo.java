package com.mhky.dianhuotong.shop.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/5/4.
 */

public class ShopTransferInfo implements Serializable {
    /**
     * notice : null
     * introduce : null
     * shippingMethod : null
     * openProcess : null
     * openExplain : null
     * afterSaleService : null
     * sendAccount : 50000
     * freight : 1000
     */

    private Object notice;
    private Object introduce;
    private Object shippingMethod;
    private Object openProcess;
    private Object openExplain;
    private Object afterSaleService;
    private int sendAccount;
    private int freight;

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    private boolean isSelect = false;

    public Object getNotice() {
        return notice;
    }

    public void setNotice(Object notice) {
        this.notice = notice;
    }

    public Object getIntroduce() {
        return introduce;
    }

    public void setIntroduce(Object introduce) {
        this.introduce = introduce;
    }

    public Object getShippingMethod() {
        return shippingMethod;
    }

    public void setShippingMethod(Object shippingMethod) {
        this.shippingMethod = shippingMethod;
    }

    public Object getOpenProcess() {
        return openProcess;
    }

    public void setOpenProcess(Object openProcess) {
        this.openProcess = openProcess;
    }

    public Object getOpenExplain() {
        return openExplain;
    }

    public void setOpenExplain(Object openExplain) {
        this.openExplain = openExplain;
    }

    public Object getAfterSaleService() {
        return afterSaleService;
    }

    public void setAfterSaleService(Object afterSaleService) {
        this.afterSaleService = afterSaleService;
    }

    public int getSendAccount() {
        return sendAccount;
    }

    public void setSendAccount(int sendAccount) {
        this.sendAccount = sendAccount;
    }

    public int getFreight() {
        return freight;
    }

    public void setFreight(int freight) {
        this.freight = freight;
    }
}
