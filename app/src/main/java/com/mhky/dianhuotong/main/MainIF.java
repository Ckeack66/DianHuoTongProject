package com.mhky.dianhuotong.main;

import java.util.ArrayList;
import java.util.List;

/**
 * 接口   实现侧拉菜单listview数据添加  +  主页面按钮的list添加  +  主界面banner链接的添加
 * Created by Administrator on 2018/3/27.
 */

public interface MainIF {
    void updateListview(int[] array, ArrayList<String> list);

    void updateGridView(int[] array, ArrayList<String> list);

    void getBanerList(List<?> list);
}
