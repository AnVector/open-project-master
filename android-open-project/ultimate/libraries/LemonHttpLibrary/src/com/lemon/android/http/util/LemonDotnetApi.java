package com.lemon.android.http.util;

import android.content.Context;

import com.lemon.android.http.RequestParams;

public class LemonDotnetApi {
	
	public void getBeanByPost(Context context, String urlString, String requestCancelName, RequestParams params,
			LemonDotnetCallback callback) {
		LemonHttpUtil.post(context, urlString, requestCancelName, params, new LemonResponseHandler( callback));
	}
	
	// add by yongxu 
	public void getBeanByPost(LemonHttpAsyncBean asyncBean,LemonDotnetCallback callback) {
		LemonHttpUtil.post(asyncBean, new LemonResponseHandler(callback));
	}

	// add by yongxu 
	public void getBeanByGet(RequestParams params,LemonHttpAsyncBean asyncBean, LemonDotnetCallback callback) {
		LemonHttpUtil.get(params, asyncBean, new LemonResponseHandler(callback));
	}
	
	
//	// add by yongxu 
//		public void getBeanByPost1(LemonHttpAsyncBean asyncBean, LemonAbstractLogic<T> logic, LemonDotnetCallback<T> callback) {
//			LemonHttpUtil.post1(asyncBean, new LemonResponseHandler<T>(logic, callback));
//		}
//		//
}
