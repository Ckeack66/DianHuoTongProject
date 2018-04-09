package com.mhky.dianhuotong.base;

/**
 * Created by Administrator on 2018/4/2.
 */

public class BaseUrlTool {
    private final static String HOST_URL = "http://192.168.2.158:9088";
    public static final String GET_SMS = HOST_URL + "/user/sms/msg/";
    public static final String CHECK_SMS = HOST_URL + "/user/sms/";
    public static final String REGISTER = HOST_URL + "/user";
}
