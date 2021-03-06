package com.mhky.dianhuotong.base;

/**
 * Created by Administrator on 2018/4/2.
 */

public class BaseUrlTool {
    /**
     * 主机
     */
//release
//    private final static String HOSTS = "http://116.255.150.39:";//release
//    private final static String HOSTS_CART = "http://116.255.158.91:";//release
//    private final static String HOST_IMAGE_URL = "http://116.255.155.156:9040";//release  Imge
//    private final static String HOSTS_ORDER = "http://116.255.158.91:";
////    private final static String HOSTS_ORDER = "http://192.168.2.237:";
//    private final static String HOST_URL_COUPON = "http://116.255.158.91:" + "9060";
//    private final static String HOST_ACTIVITY = "http://116.255.158.91:9060/promotion/list";

    //debug
    private final static String HOSTS = "http://192.168.2.158:";//debug
    private final static String HOSTS_CART = "http://192.168.2.158:";
    private final static String HOST_IMAGE_URL = HOSTS + "9040";//debug  Imge
    private final static String HOSTS_ORDER = "http://192.168.2.163:";
    private final static String HOST_URL_COUPON = "http://192.168.2.158:" + "9060";
    private final static String HOST_ACTIVITY="http://192.168.2.158:9060/promotion";
    /**
     * 上传图片地址
     */
    public static final String UPLOAD_IMAGE = HOST_IMAGE_URL + "/common/upload";
    private final static String HOST_URL = HOSTS + "9088";
    private final static String HOST_ADRESS_URL = HOSTS + "8018";
    private final static String HOST_GOODS_URL = HOSTS + "9000";
    private final static String HOST_CART_URL = HOSTS_CART + "9050";
    private final static String HOST_BANLANCE_URL = HOSTS_ORDER + "9030";
    private final static String HOST_ADVERT_URL = HOSTS_CART + "9070";
    private final static String HOST_PROMOTE_URL = HOSTS_ORDER + "8889";
//    private final static String HOST_ACTIVITY_URL=HOSTS+"9060";
//    private final static String HOST_ADRESS_URL = "http://192.168.2.235:8088";
    //        private final static String HOST_URL = "http://192.168.2.235:9088";
    /**
     * 获取短信验证码（注册时）
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
//    public static final String LOGIN = HOST_URL + "/user/buyer/login";
    public static final String LOGIN = HOST_URL + "/user/b2b2c/login";
    /**
     * 获取商户个人信息
     */
    private static final String PERSON_INFO = HOST_URL + "/user/buyer/";
    private static final String PERSON_INFO_END = "/info";

    public static String getPersonInfoURL(String UserID) {
        return PERSON_INFO + UserID + PERSON_INFO_END;
    }

    /**
     * 根据id或者手机号获取customer对象信息
     */
    public static final String GET_CUSTOMER_INFO = HOST_URL + "/customer/info";

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
     * 通过手机号修改密码http://192.168.2.158:9088/user/password/
     */
    private static final String ALTER_USER_PWD1 = "/user/password/";

    public static String getAlterPwdURL1(String ID) {
        return HOST_URL + ALTER_USER_PWD1 + ID;
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
     * 获取全部商品类别(新)
     * http://116.255.158.91:9070/advert/menu/all
     */
    public static String GET_GOODS_CATEGORIES = HOST_ADVERT_URL + "/advert/menu/all";

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
            return SKU_INFO + goodsId + "/skus?";
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

    public static String getShopInfoUrl(String id, String user) {
        return GET_UP_SHOP_INFO + id + "/" + user;
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
     * 订单服务http://192.168.2.158:9030/order/add
     */
    public static final String DO_BANLANCE_URL = HOST_BANLANCE_URL + "/order/add";

    /**
     * 获取订单信息
     */
    /**
     * 订单服务
     */
    public static final String GET_ORDERINFO = HOST_BANLANCE_URL + "/order";

    /**
     * 获取支付宝订单信息/获取微信订单信息
     * 下方为本地测试用
     */

    public static final String GET_ALIPAY_CODE = HOST_BANLANCE_URL + "/order/payCode?";
//    public static final String GET_ALIPAY_CODE = "http://192.168.2.237:9030" + "/order/payCode?";

    /**
     * 根据userID进行查询店铺信息
     */

    private static final String GET_SHOP_INFO = HOST_URL + "/user/buyer/";

    public static String getShopInfo(String ID) {
        return GET_SHOP_INFO + ID + "/shop";
    }

    /**
     * 通过店铺ID获取商铺信息
     */
    public static final String GET_SHOP_ADRESS = HOST_URL + "/user/buyer/shop/";

    /**
     * 获取发票信息
     */
    public static final String GET_SHOP_BILL = HOST_URL + "/user/buyer/shop/ticket/";
    /**
     * 添加发票信息
     */
    public static final String ADD_SHOP_BILL = HOST_URL + "/user/buyer/shop/ticket";
    /**
     * 获取服务专员
     */
    private static String GET_SALE_MAN = HOST_PROMOTE_URL + "/extension/salesman/";

    public static String getSaleMan(String code) {
        return GET_SALE_MAN + code + "/code";
    }

    /**
     * 批量获取运费
     */
    public static final String GET_FRIGHT = HOST_URL + "/user/seller/freight?";
    /**
     * 获取优惠券
     */
    public static final String GET_COUPON = HOST_URL_COUPON + "/couponRecord?";
    /**
     * 获取订单信息
     */
    public static final String GET_ORDER_INFO = HOST_BANLANCE_URL + "/order?";
    /**
     * 收藏新店铺
     */
    public static final String ADD_STAR_SHOP = HOST_URL + "/user/buyer/shop/followupstream?";
    /**
     * 删除收藏店铺
     */
    public static final String DELETE_STAR_SHOP = HOST_URL + "/user/buyer/shop/followupstream/";
    /**
     * 获取关注商家ID列表
     */
    public static final String GET_STAR_SHOP_ID_LIST = HOST_URL + "/user/buyer/shop/followupstream/";
    /**
     * 获取首页广告
     */
    public static final String GET_ADVERT_MAIN = HOST_ADVERT_URL + "/advert?";
    /**
     * 查询上游b列表
     */
    public static final String GET_COMPANY_LIST = HOST_URL + "/user/seller/company/list";
    /**
     * 获取推广
     */
    private static final String GET_PROMOTE = HOST_PROMOTE_URL + "/extension/user/";

    public static String getPromote(String phone) {
        return GET_PROMOTE + phone + "/mobile";
    }

    /**
     * 分页获取品牌信息
     */
    public static final String GET_BRAND = HOST_GOODS_URL + "/brand";
    /**
     * 获取商铺首页推荐商品
     * http://192.168.2.158:9000/goods/recommend?startDate=2018-06-19&endDate=2018-06-19
     */
    public static final String GET_RECOMMEND_GOODS = HOST_GOODS_URL + "/goods/recommend";
    /**
     * 获取限时限折http://192.168.2.158:9060/promotion?promotionTypes=XIAN_SHI_XIAN_LIANG_ZHE_KOU&status=true
     */

    public static final String GET_LAST_MINUTE = HOST_ACTIVITY;

    /**
     * 通过手机号查询钱包余额http://192.168.2.158:8889/extension/user/15665788176/mobile
     */
    public static String getWalletMoney(String phone) {
        return HOST_PROMOTE_URL + "/extension/user/" + phone + "/mobile";
    }

    /**
     * 获取优惠券列表
     * http://192.168.2.158:9060/promotion?promotionTypes=PING_TAI_YOU_HUI_QUAN&status=true
     */
    public static final String GET_PLATFORM_COUPON = HOST_ACTIVITY+"?";

    /**
     * 领取优惠券
     * http://192.168.2.158:9060/couponRecord?promotionId=1&companyId=1&shopId=1&grads=signal
     */
    public static final String BIND_COUPON_URL=HOST_URL_COUPON+"/couponRecord?";

    /**
     * 买家回执单
     */
    public static String uploadBuyersReturnOrders(String orderNo) {
        return HOST_BANLANCE_URL + "/order/" + orderNo + "/receipt ";
    }

    /********************************************   Customer端接口   *******************************************/

    /**
     * 获取默认收货地址
     */
    public static final String GET_DEFAULT_ADDRESS = HOST_URL + "/customer/address/info";

    /**
     * 获取订单列表
     */
    public static final String GET_CUSTOMER_ORDERS = HOST_BANLANCE_URL + "/b2b/order/list";

    /**
     * 获取订单列表详情
     */
    public static final String GET_CUSTOMER_ORDER_DETAILS = HOST_BANLANCE_URL + "/b2b/order/";
}
