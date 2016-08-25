package com.looper.ultimate.util;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2016/8/1.
 */
public class VolleyRequestWithAuth extends JsonObjectRequest {

    private Context mContext;
    public VolleyRequestWithAuth(int method, String url, JSONObject jsonRequest, Context context, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
        this.mContext = context;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return HttpHeaderUtil.getHeader(mContext);
    }
}
