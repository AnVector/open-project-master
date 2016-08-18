package com.looper.ultimate.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.looper.ultimate.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class GsonUtils {

    private static Gson gson =null;

    public static  <T extends BaseBean> T getBeanFromJson(String json, Class<T> cls) {

        if(gson == null){
            gson = new Gson();
        }
        try {
            return gson.fromJson(json, cls);
        } catch (Exception e) {
            LogUtils.e(e.toString());
            return null;
        }
    }

    public static <T extends BaseBean> List<T> toList(String json, Class<T> clazz) {
        if(gson == null){
            gson = new Gson();
        }
        List<T> list = new ArrayList<>();
        try{
            list = gson.fromJson(json,new TypeToken<List<T>>(){}.getType());
        }catch (Exception e){
            LogUtils.e(e.toString());
        }
        return list;
    }

    /**
     * fromJson
     * @param json json
     * @param c c
     * @param <T> <T>
     * @return T
     */
    public static <T extends BaseBean> T fromJson(String json, Class<T> c){
        if(gson == null){
            gson = new Gson();
        }
        return gson.fromJson(json, c);
    }

    /**
     * toJson
     * @param src src
     * @return String
     */
    public static String toJson(Object src) {
        if(gson == null){
            gson = new Gson();
        }
        return gson.toJson(src);
    }
}
