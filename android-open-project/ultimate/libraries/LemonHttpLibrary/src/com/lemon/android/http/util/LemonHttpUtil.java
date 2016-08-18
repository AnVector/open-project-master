package com.lemon.android.http.util;

import java.util.Map;

import android.content.Context;
import android.util.Log;

import com.lemon.android.http.AsyncHttpClient;
import com.lemon.android.http.AsyncHttpResponseHandler;
import com.lemon.android.http.RequestHandle;
import com.lemon.android.http.RequestParams;

public abstract class LemonHttpUtil {
	private static AsyncHttpClient asyncClient;
	
	static {
		asyncClient = new AsyncHttpClient();//...异步获取网络数据...
//		asyncClient.addHeader("WD_UUID", PeopleVideoApp.getInstance().getUUID());
	}
	
	public static void get(Context context, String url, String requestCancelName, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		RequestHandle requestHandle = asyncClient.get(context, url, params, responseHandler);
		requestHandle.setRequestCancelName(requestCancelName);
	}
	
	public static void post(Context context, String url, String requestCancelName, RequestParams params, AsyncHttpResponseHandler responseHandler) {
		RequestHandle requestHandle = asyncClient.post(context, url, params, responseHandler);
		requestHandle.setRequestCancelName(requestCancelName);
	}
	
	public static void get(RequestParams params,LemonHttpAsyncBean asyncBean, AsyncHttpResponseHandler responseHandler) {
		
		RequestHandle requestHandle = asyncClient.get(asyncBean.getContext(), asyncBean.getUrl(), asyncBean.getHeaders(), 
				params, responseHandler);
		requestHandle.setRequestCancelName(asyncBean.getRequestCancelName());
	}
	
	// add by yongxu
	public static void post(LemonHttpAsyncBean asyncBean, AsyncHttpResponseHandler responseHandler) {
				
//		Map<String, String> httpHeaderMap = asyncBean.getHeaderMap();
//		if (httpHeaderMap != null && httpHeaderMap.size() != 0) {
//			for (Map.Entry<String, String> entry : httpHeaderMap.entrySet()) {
//				asyncClient.addHeader(entry.getKey(), entry.getValue());
//			}
//		}
		
//		asyncClient.addHeader("Client-Agent", "1.0.0/1280*720/OtherInfomation");
//		asyncClient.addHeader("APIVersion", "1.0.0");
//		asyncClient.addHeader("Content-Type", "application/json");
//		asyncClient.addHeader("ClientHash", asyncBean.getClientHash());
//		asyncClient.addHeader("ottTerminalUniqueId", asyncBean.getOttTerminalUniqueId());
//		asyncClient.addHeader("user-id", asyncBean.getUserId());
//		asyncClient.addHeader("tokenId", asyncBean.getTokenID());
		
		
		RequestHandle requestHandle = asyncClient.post(asyncBean.getContext(), asyncBean.getUrl(), asyncBean.getHeaders(), 
				asyncBean.getEntity(), null, responseHandler);
		requestHandle.setRequestCancelName(asyncBean.getRequestCancelName());
	}
	
	// add by yongxu
//	public static void post1(LemonHttpAsyncBean asyncBean, AsyncHttpResponseHandler responseHandler) {
//				
////		Map<String, String> httpHeaderMap = asyncBean.getHeaderMap();
////		if (httpHeaderMap != null && httpHeaderMap.size() != 0) {
////			for (Map.Entry<String, String> entry : httpHeaderMap.entrySet()) {
////				asyncClient.addHeader(entry.getKey(), entry.getValue());
////			}
////		}
//		
//		asyncClient.addHeader("Client-Agent", "1.0.0/1280*720/OtherInfomation");
//		asyncClient.addHeader("APIVersion", "1.0.0");
//		asyncClient.addHeader("Content-Type", "application/json");
//		asyncClient.addHeader("ClientHash", asyncBean.getClientHash());
//		asyncClient.addHeader("ottTerminalUniqueId", asyncBean.getOttTerminalUniqueId());
//		asyncClient.addHeader("user-id", asyncBean.getUserId());
//		asyncClient.addHeader("tokenId", asyncBean.getTokenID());
//		
//		
//		RequestHandle requestHandle = asyncClient.post(asyncBean.getContext(), asyncBean.getUrl(), asyncBean.getHeaders(), 
//				asyncBean.getEntity(), null, responseHandler);
//		requestHandle.setRequestCancelName(asyncBean.getRequestCancelName());
//	}
	//
}
