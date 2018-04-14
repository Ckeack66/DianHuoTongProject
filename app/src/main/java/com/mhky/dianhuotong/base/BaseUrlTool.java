package com.mhky.dianhuotong.base;

/**
 * Created by Administrator on 2018/4/2.
 */

public class BaseUrlTool {
    /**
     * 主机
     */
    private final static String HOST_URL = "http://192.168.2.158:9088";
    private final static String HOST_IMAGE_URL = "http://192.168.2.158:9040";
    /**
     * 获取短信验证码
     */
    public static final String GET_SMS = HOST_URL + "/user/sms/msg/";
    /**
     * 验证验证码
     */
    public static final String CHECK_SMS = HOST_URL + "/user/sms/";
    /**
     * 注册
     */
    public static final String REGISTER = HOST_URL + "/user";
    /**
     * 登陆
     */
    public static final String LOGIN = HOST_URL + "/user/buyer/login";
    /**
     * 获取商户个人信息
     */
    private static final String PERSON_INFO = HOST_URL + "/user/buyer/";
    private static final String PERSON_INFO_END = "/info";

    public static String getPersonInfoURL(String UserID) {
        return PERSON_INFO + UserID + PERSON_INFO_END;
    }

    /**
     * 上传图片地址
     */
    public static final String UPLOAD_IMAGE = HOST_IMAGE_URL + "/upload";
    /**
     * 修改商户个人信息
     */
    public static final String UPLOAD_PERSON_INFO = HOST_URL + "/user/";
    /**
     * 根据区域查询店铺
     */
    public static final String SEARCH_AREA_SHOP = HOST_URL + "/user/buyer/shop/region";
    /**
     * 修改用户密码
     */
    private static final String ALTER_USER_PWD = "/password";

    public static String getAlterPwdURL(String ID) {
        return HOST_URL + "/user/" + ID + ALTER_USER_PWD;
    }
}
