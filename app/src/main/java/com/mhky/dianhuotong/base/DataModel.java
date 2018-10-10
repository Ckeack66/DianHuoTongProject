package com.mhky.dianhuotong.base;

/**
 * Model层数据请求的分发
 */

public class DataModel {

    public static BaseModle request(Class clazz) {
        // 声明一个空的BaseModel
        BaseModle model = null;
        // 判断class对象是不是BaseModel的实例
        try {
            model = (BaseModle) clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return model;
    }
}

