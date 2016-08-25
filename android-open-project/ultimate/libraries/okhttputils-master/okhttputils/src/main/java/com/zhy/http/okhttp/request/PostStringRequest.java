package com.zhy.http.okhttp.request;

import android.util.Log;

import com.zhy.http.okhttp.utils.Exceptions;

import java.io.IOException;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by zhy on 15/12/14.
 */
public class PostStringRequest extends OkHttpRequest
{
    private static MediaType MEDIA_TYPE_PLAIN = MediaType.parse("text/plain;charset=utf-8");

    private String content;
    private MediaType mediaType;


    public PostStringRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers, String content, MediaType mediaType,int id)
    {
        super(url, tag, params, headers,id);
        this.content = content;
        this.mediaType = mediaType;

        if (this.content == null)
        {
            Exceptions.illegalArgument("the content can not be null !");
        }
        if (this.mediaType == null)
        {
            this.mediaType = MEDIA_TYPE_PLAIN;
        }

    }

    @Override
    protected RequestBody buildRequestBody()
    {
        if(!content.isEmpty()){
            Log.d("Request","content:"+content);
        }
        return RequestBody.create(mediaType, content);
    }

    @Override
    protected Request buildRequest( RequestBody requestBody)
    {
        try {
            Log.d("Request","contentLength:"+requestBody.contentLength());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return builder.post(requestBody).build();
    }


}
