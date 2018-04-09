package com.mhky.dianhuotong.main;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/27.
 */

public interface MainIF {
    void updateListview(int[] array, ArrayList<String> list);

    void updateGridView(int[] array, ArrayList<String> list);

    void getBanerList(List<?> list);
}
