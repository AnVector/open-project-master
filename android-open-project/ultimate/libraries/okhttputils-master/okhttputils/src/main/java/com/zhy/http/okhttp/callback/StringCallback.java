package com.zhy.http.okhttp.callback;

import android.util.Log;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Response;

/**
 * Created by zhy on 15/12/14.
 */
public abstract class StringCallback extends Callback<String>
{
    @Override
    public String parseNetworkResponse(Response response, int id) throws IOException
    {
        Log.d("Response","isSuccessful:"+response.isSuccessful());
        Log.d("Response","isRedirect:"+response.isRedirect());
        Headers headers = response.headers();
        for(int i=0;i<headers.size();++i){
            Log.d("Response",""+headers.name(i).toString()+":"+""+headers.value(i).toString());
        }
        return response.body().string();
    }
}
