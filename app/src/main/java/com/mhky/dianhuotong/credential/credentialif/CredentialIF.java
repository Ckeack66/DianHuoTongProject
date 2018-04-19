package com.mhky.dianhuotong.credential.credentialif;

/**
 * Created by Administrator on 2018/4/17.
 */

public interface CredentialIF {
    void createShopSucess(int code, String result);

    void createShopFailed(int code, String result);
}
