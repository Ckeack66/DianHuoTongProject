package com.mhky.dianhuotong.pay;

import java.io.Serializable;

public class WXPayInfo implements Serializable {
    /**
     * nonce_str : yLZ16QZsOCJ4iFEt
     * package : Sign=WXPay
     * appid : wx1aa6ee31b052846b
     * sign : BA8B608FE44810466600481B8C5BC257
     * mch_id : 1505834111
     * prepay_id : wx0518224469780431871522730475498531
     * timestamp : 1528194165
     */

    private String nonce_str;
    private String packageX;
    private String appid;
    private String sign;
    private String mch_id;
    private String prepay_id;
    private String timestamp;

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getPackageX() {
        return packageX;
    }

    public void setPackageX(String packageX) {
        this.packageX = packageX;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getPrepay_id() {
        return prepay_id;
    }

    public void setPrepay_id(String prepay_id) {
        this.prepay_id = prepay_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
