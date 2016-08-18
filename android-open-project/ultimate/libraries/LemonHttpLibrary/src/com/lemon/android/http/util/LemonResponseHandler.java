package com.lemon.android.http.util;

import com.lemon.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

public class LemonResponseHandler extends AsyncHttpResponseHandler {
	private LemonDotnetCallback mLemonDotnetCallback;

	public LemonResponseHandler( LemonDotnetCallback callback){
		super();
		mLemonDotnetCallback = callback;
	}

	@Override
	public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
		mLemonDotnetCallback.onFetchDataError();
	}
	
	@Override
	public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
		String result = new String(responseBody);
		if(result==null){
			mLemonDotnetCallback.onFetchDataError();
		}else {
			mLemonDotnetCallback.onFetchDataSuccess(result);
		}
//		T type = mLemonAbstractLogic.getObjectFromString(result);
//		if (type == null) {
//			mLemonDotnetCallback.onFetchDataError();
//		} else {
//			Log.d("onSuccess",result.toString());
//			mLemonDotnetCallback.onFetchDataSuccess(type);
//		}
	}
	
	/** 
     * 分段打印出较长log文本 
     * @param log        原log文本 
     * @param showCount  规定每段显示的长度（最好不要超过eclipse限制长度） 
     */  
/*    public static void showLogCompletion(String log,int showCount){  
        if(log.length() >showCount){  
            String show = log.substring(0, showCount);  
            Log.e("chinamobile", "--->>> LemonResponseHandler onSuccess"+show);  
            if((log.length() - showCount)>showCount){//剩下的文本还是大于规定长度  
                String partLog = log.substring(showCount,log.length());  
                showLogCompletion(partLog, showCount);  
            }else{  
                String surplusLog = log.substring(showCount, log.length());  
                Log.e("chinamobile", ""+surplusLog);  
            }  
              
        }else{  
            Log.e("chinamobile", ""+log);  
        }  
    } */ 
}
