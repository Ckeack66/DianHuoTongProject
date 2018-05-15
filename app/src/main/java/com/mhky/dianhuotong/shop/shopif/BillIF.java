package com.mhky.dianhuotong.shop.shopif;

public interface BillIF {
    void getBillSuccess(int code, String result);
    void getBillFailed(int code, String result);
    void alterBillSuccess(int code, String result);
    void alterBillFailed(int code, String result);
    void addBillSuccess(int code, String result);
    void addBillFailed(int code, String result);
}
