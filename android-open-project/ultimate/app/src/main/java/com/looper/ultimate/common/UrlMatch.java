package com.looper.ultimate.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/1.
 */
public class UrlMatch {

    private static final Map<InterfaceType,String> urlMap = new HashMap<>();
    private static final String PREFIX_URL = "http://m.miguxue.com/client/interface/";

    static {
        urlMap.put(InterfaceType.getCatalogInfo,PREFIX_URL+InterfaceType.getCatalogInfo);
        urlMap.put(InterfaceType.getBootScreen,PREFIX_URL+InterfaceType.getBootScreen);
        urlMap.put(InterfaceType.getContentClass,PREFIX_URL+InterfaceType.getContentClass);
        urlMap.put(InterfaceType.getContentClass2,PREFIX_URL+InterfaceType.getContentClass2);
        urlMap.put(InterfaceType.getContentListByClass,PREFIX_URL+InterfaceType.getContentListByClass);
        urlMap.put(InterfaceType.getContentListByCatalog,PREFIX_URL+InterfaceType.getContentListByCatalog);
        urlMap.put(InterfaceType.getCatalogList,PREFIX_URL+InterfaceType.getCatalogList);
        urlMap.put(InterfaceType.getConsumeRecords,PREFIX_URL+InterfaceType.getConsumeRecords);
        urlMap.put(InterfaceType.getContentDetailInfo,PREFIX_URL+InterfaceType.getContentDetailInfo);
        urlMap.put(InterfaceType.getContentNavigation,PREFIX_URL+InterfaceType.getContentNavigation);
        urlMap.put(InterfaceType.getHotKeywords,PREFIX_URL+InterfaceType.getHotKeywords);
        urlMap.put(InterfaceType.getReadURL,PREFIX_URL+InterfaceType.getReadURL);
        urlMap.put(InterfaceType.getRecommendBooksByContent,PREFIX_URL+InterfaceType.getRecommendBooksByContent);
        urlMap.put(InterfaceType.getVerifyCode,PREFIX_URL+InterfaceType.getVerifyCode);
        urlMap.put(InterfaceType.bindPayMsisdn,PREFIX_URL+InterfaceType.bindPayMsisdn);
        urlMap.put(InterfaceType.unbindPayMsisdn,PREFIX_URL+InterfaceType.unbindPayMsisdn);
        urlMap.put(InterfaceType.submitOrder,PREFIX_URL+InterfaceType.submitOrder);
        urlMap.put(InterfaceType.purchase,PREFIX_URL+InterfaceType.purchase);
        urlMap.put(InterfaceType.userRegister,PREFIX_URL+InterfaceType.userRegister);
        urlMap.put(InterfaceType.checkUpdate,PREFIX_URL+InterfaceType.checkUpdate);
        urlMap.put(InterfaceType.searchCourse,PREFIX_URL+InterfaceType.searchCourse);
        urlMap.put(InterfaceType.TNGOU,"http://www.tngou.net/tnfs/api/list?");

    }

    public static String getInterfaceUrl(InterfaceType interfaceType){

        if(urlMap.containsKey(interfaceType)){
            return urlMap.get(interfaceType);
        }
        return "";
    }
}
