package com.mhky.dianhuotong.credential.credentialif;

/**
 * Created by Administrator on 2018/4/18.
 */

public interface CredentialsDialogIF {
    void getCredentialTypeSucess(int code, String result);

    void getCredentialTypeFailed(int code, String result);
}
