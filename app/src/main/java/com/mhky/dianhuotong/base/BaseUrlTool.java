package com.mhky.dianhuotong.base;

/**
 * Created by Administrator on 2018/4/2.
 */

public class BaseUrlTool {
    /**
     * 主机
     */
    private final static String HOST_URL = "http://192.168.2.158:9088";
    //        private final static String HOST_URL = "http://192.168.2.235:9088";
    private final static String HOST_IMAGE_URL = "http://192.168.2.158:9040";
    private final static String HOST_ADRESS_URL = "http://192.168.2.158:8088";
    private final static String HOST_GOODS_URL = "http://192.168.2.158:9000";
//    private final static String HOST_ADRESS_URL = "http://192.168.2.235:8088";
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
    private static final String ALTER_USER_PWD = "/password/";

    public static String getAlterPwdURL(String ID) {
        return HOST_URL + "/user/" + ID + ALTER_USER_PWD;
    }

    /**
     * 店铺添加用户
     */
    public static final String ADD_USER_TO_SHOP = HOST_URL + "/user/buyer";
    /**
     * 获取城市列表
     */
    public static final String GET_ADRESS_CITY = HOST_ADRESS_URL + "/regions/city";
    /**
     * 获取区域
     */
    public static final String GET_ADRESS_CITY_AREA = HOST_ADRESS_URL + "/regions/";

    public static String getAreaUrl(String ID) {
        return GET_ADRESS_CITY_AREA + ID + "?type=DISTRICT";
    }

    /**
     * 获取街道
     */
    public static final String GET_ADRESS_CITY_STRESS = HOST_ADRESS_URL + "/regions/";

    public static String getAdressUrl(String ID) {
        return GET_ADRESS_CITY_STRESS + ID + "?type=TOWN";
    }

    /**
     * 创建店铺
     */
    public static final String CREAT_SHOP = HOST_URL + "/user/buyer/shop";

    /**
     * 获取资质类型
     */
    public static final String GET_CREDENTIAL_TYPE = HOST_URL + "/user/qualification/type";

    /**
     * 修改手机号
     */
    private static final String CHANGE_PHONE = HOST_URL + "/user/";

    public static String getChangePhoneUrl(String ID) {
        return CHANGE_PHONE + ID + "/mobile";
    }

    /**
     * 获取全部商品类别
     */
    public static String GET_ALL_GOODS_TYPE = HOST_GOODS_URL + "/category";
    /**
     * 搜索商品返回
     */
    public static String SEARCH_GOODS=HOST_GOODS_URL+"/goods";
}
