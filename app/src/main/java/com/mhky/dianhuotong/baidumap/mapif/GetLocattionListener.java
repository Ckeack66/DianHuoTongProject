package com.mhky.dianhuotong.baidumap.mapif;

import com.baidu.location.BDLocation;

/**
 * Created by Administrator on 2018/4/11.
 */

public interface GetLocattionListener {
    void getResultLocation(BDLocation location,String locate);
}
