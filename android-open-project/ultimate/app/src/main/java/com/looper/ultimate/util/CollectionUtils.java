package com.looper.ultimate.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 集合工具类
 *
 * @author lt
 */
public class CollectionUtils {

    private CollectionUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isNotNull(Collection<?> collection) {
        return collection != null && collection.size() > 0;
    }

    /**
     * list转map
     * 以对象的hashCode字符串为key
     *
     * @return Map<String,T>
     */
    public static <T extends Object> Map<String, T> list2map(List<T> list) {
        Map<String, T> map = new HashMap<String, T>();
        for (T t : list) {
            map.put(t.hashCode() + "", t);
        }
        return map;
    }


    /**
     * map转list
     *
     * @return List<T>
     * @Title: map2list
     */
    public static <T extends Object> List<T> map2list(Map<String, T> maps) {
        List<T> list = new ArrayList<T>();
        Iterator<Entry<String, T>> iterator = maps.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, T> entry = iterator.next();
            list.add(entry.getValue());
        }
        return list;
    }
}
