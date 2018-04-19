package com.mhky.dianhuotong.credential.credentialif;

/**
 * Created by Administrator on 2018/4/17.
 */

public interface UploadCredentialIF {
    void updataCredentialImageSucess(int code, String result);

    void updataCredentialImageFailed(int code, String result);
}
