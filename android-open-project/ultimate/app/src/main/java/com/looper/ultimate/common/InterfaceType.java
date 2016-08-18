package com.looper.ultimate.common;

/**
 * Created by Administrator on 2016/8/1.
 */
public enum InterfaceType {
    getBootScreen,//开机画面接口
    getContentClass,//课程分类接口，根据分类ID获取分类ID和课程
    getContentClass2,//课程分类接口，根据分类ID获取分类ID
    getContentListByClass,//课程分类接口，根据分类ID获取分类课程
    getContentListByCatalog,//专区内容查询接口
    getCatalogList,//专区列表查询接口
    getContentDetailInfo,//课程详情接口
    getReadURL,//视频播放接口
    getRecommendBooksByContent,//相关推荐接口
    getContentNavigation,//内容（目录）列表接口
    getVerifyCode,//获取验证码接口
    bindPayMsisdn,//绑定支付手机接口
    unbindPayMsisdn,//解绑支付手机接口
    submitOrder,//提交订单接口
    purchase,//支付接口
    getConsumeRecords,//订购记录接口
    userRegister,//用户注册接口
    getCatalogInfo,//获取专题、排行榜专区大图
    checkUpdate,//在线升级检测接口
    getHotKeywords,//搜索热词接口
    searchCourse,//课程搜索接口
    TNGOU //天狗API
}
