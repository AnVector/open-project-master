package com.looper.ultimate.util;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
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

        Map<String, String> mHeader = new HashMap<>();
        mHeader.put("Client-Agent", HttpHeaderUtil.getClientAgent(mContext));
        mHeader.put("APIVersion", "2.7");
        mHeader.put("Content-Type", "application/json;charset=utf-8");
        mHeader.put("ClientHash", HttpHeaderUtil.encryptClientHash("",mContext));
        mHeader.put("terminalUniqueId","3256462645634434564");
        mHeader.put("user-id", "");
        mHeader.put("tokenId", "");
        mHeader.put("X-Channel-Code", "J00140001");
        mHeader.put("sub_version","ZJJK");
        return mHeader;
    }
}
