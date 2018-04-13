package com.mhky.dianhuotong.person.personif;

import com.mhky.dianhuotong.person.bean.PersonInfo;

/**
 * Created by Administrator on 2018/4/12.
 */

public interface PersonIF {
    void getUserInfoSucess(PersonInfo userInfo);

    void getUserinfoFailed(int code, String result);
}
