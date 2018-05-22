package com.mhky.dianhuotong.base;

/**
 * Created by Administrator on 2018/4/2.
 */

public class BaseUrlTool {
    /**
     * 主机
     */
//
    private final static String HOSTS = "http://116.255.150.39:";//release
    private final static String HOSTS_CART = "http://116.255.158.91:";//release
    private final static String HOST_IMAGE_URL = "http://116.255.155.156:9040";//release  Imge
    private final static String HOSTS_ORDER="http://116.255.158.91:";
    //debug
//    private final static String HOSTS = "http://192.168.2.158:";//debug
//    private final static String HOSTS_CART="http://192.168.2.158:";
//    private final static String HOST_IMAGE_URL = HOSTS + "9040";//debug  Imge
//    private final static String HOSTS_ORDER="http://192.168.2.158:";

    /**
     * 上传图片地址
     */
    public static final String UPLOAD_IMAGE = HOST_IMAGE_URL + "/common/upload";
    private final static String HOST_URL = HOSTS + "9088";
    private final static String HOST_ADRESS_URL = HOSTS + "8018";
    private final static String HOST_GOODS_URL = HOSTS + "9000";
    private final static String HOST_CART_URL = HOSTS_CART + "9050";
    private final static String HOST_BANLANCE_URL = HOSTS_ORDER + "9030";
//    private final static String HOST_ACTIVITY_URL=HOSTS+"9060";
//    private final static String HOST_BANLANCE_URL = "http://192.168.2.237:" + "9030";
//    private final static String HOST_ADRESS_URL = "http://192.168.2.235:8088";
    //        private final static String HOST_URL = "http://192.168.2.235:9088";
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
    public static String SEARCH_GOODS = HOST_GOODS_URL + "/goods";
    /**
     * 查询所有公司
     */
    public static String GET_ALL_COMPANY = HOST_URL + "/user/seller/company/list";
    /**
     * 添加到购物车
     */
    public static String ADD_CART = HOST_CART_URL + "/cart?";

    /**
     * 删除购物车商品
     */
    public static String DELETE_CART = HOST_CART_URL + "/cart?";

    /**
     * 查询购物车
     */
    private static String GET_CART = HOST_CART_URL + "/cart?buyerId=";

    public static String getCartInfo(String ID) {
        return GET_CART + ID;
    }

    /**
     * 更改购物车商品
     */
    public static String ALTER_CART = HOST_CART_URL + "/cart?";
    /**
     * 获取商品属性信息
     */
    private static String SKU_INFO = HOST_GOODS_URL + "/goods/";

    public static String getSkuInfo(String goodsId) {
        return SKU_INFO + goodsId + "/skus";
    }

    /**
     * 获取单个商品详细信息
     */
    private static String GET_GOODS_INFO = HOST_GOODS_URL + "/goods/";

    public static String getGoodsInfo(String Id) {
        return GET_GOODS_INFO + Id;
    }

    /**
     * 查询上游B商铺信息
     */
    private static String GET_UP_SHOP_INFO = HOST_URL + "/user/seller/company/";

    public static String getShopInfoUrl(String id) {
        return GET_UP_SHOP_INFO + id;
    }

    /**
     * 查询店铺资质信息
     */
    private static final String GET_SHOP_CREDENTIAL = HOST_URL + "/user/qualification/";

    public static String getShopCredentialUrl(String Id) {
        return GET_SHOP_CREDENTIAL + Id;
    }

    /**
     * 修改店铺资质
     */
    private static final String UPDATE_CREDENTIAL = HOST_URL + "/user/qualification/";

    public static String getUpdateCredentialUrl(String ID) {
        return UPDATE_CREDENTIAL + ID;
    }

    /**
     * 添加单个资质
     */
    public static final String UPLOAD_CREDENTIAL = HOST_URL + "/user/qualification";

    /**
     * 获取店铺分类
     */
    private static final String GET_SHOP_TYPE = HOST_GOODS_URL + "/shopGoodsClassify/list?shopId=";

    public static String getShopType(String ID) {
        return GET_SHOP_TYPE + ID;
    }

    /**
     * 获取公司信息
     */
    private static final String GET_COMPANY_INFO = HOST_URL + "/user/seller/";

    public static String getCompanyInfoUrl(String comId) {
        return GET_COMPANY_INFO + comId + "/company";
    }

    /**
     * 订单服务
     */
    public static final String DO_BANLANCE_URL = HOST_BANLANCE_URL + "/order?";

    /**
     * 获取订单信息
     */
    /**
     * 订单服务
     */
    public static final String GET_ORDERINFO = HOST_BANLANCE_URL + "/order?buyerId=";
    /**
     * 获取支付宝订单信息
     */

    public static final String GET_ALIPAY_CODE = HOST_BANLANCE_URL + "/order/payCode?";

    /**
     * 根据userID进行查询店铺信息
     */

    private static final String GET_SHOP_INFO=HOST_URL+"/user/buyer/";
    public static String getShopInfo(String ID){
        return GET_SHOP_INFO+ID+"/shop";
    }

    /**
     * 通过店铺ID获取商铺信息
     */
    public static final String GET_SHOP_ADRESS=HOST_URL+"/user/buyer/shop/";

    /**
     * 获取发票信息
     */
    public static final String GET_SHOP_BILL=HOST_URL+"/user/buyer/shop/ticket/";
    /**
     * 添加发票信息
     */
    public static final String ADD_SHOP_BILL=HOST_URL+"/user/buyer/shop/ticket";
    /**
     * 获取服务专员手机号
     */
    private static String GET_SALE_MAN=HOST_URL+"/user/salesman/";
    public static String getSaleMan(String code){
        return GET_SALE_MAN+code+"/code";
    }

    /**
     * 批量获取运费
     */
    public static final String GET_FRIGHT=HOST_URL+"/user/seller/freight?";
    /**
     * 获取优惠券
     */
    public static final String GET_COUPON=HOST_URL+"/couponRecord?";
    /**
     * 获取订单信息
     */
    public static final String GET_ORDER_INFO=HOST_BANLANCE_URL+"/order?";
}
