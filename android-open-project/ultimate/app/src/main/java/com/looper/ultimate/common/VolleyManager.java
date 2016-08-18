package com.looper.ultimate.common;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Administrator on 2016/7/4.
 */
public class VolleyManager {

    private static volatile VolleyManager instance = null;

    private RequestQueue mRequestQueue = null;

    private VolleyManager(Context context){
        mRequestQueue = getRequestQueue(context);
    }

    public static VolleyManager getInstance(Context context) {
        if (instance == null) {
            synchronized (VolleyManager.class) {
                if (instance == null) {
                    instance = new VolleyManager(context);
                }
            }
        }
        return instance;
    }

    private RequestQueue getRequestQueue(Context context){
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        mRequestQueue.add(request);
    }

    public  void cancelRequest(Object tag){
        if(mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
