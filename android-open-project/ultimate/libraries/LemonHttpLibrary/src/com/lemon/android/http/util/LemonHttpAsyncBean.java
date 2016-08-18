package com.lemon.android.http.util;

import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import android.content.Context;

public class LemonHttpAsyncBean {
	
	Context context;
	String url;
	Header[] headers;
	HttpEntity entity;
	String requestCancelName;
	Map<String, String> headerMap;
	String clientHash;
	String userId;
	String tokenID;
	String ottTerminalUniqueId;
	
	public String getOttTerminalUniqueId() {
		return ottTerminalUniqueId;
	}
	public void setOttTerminalUniqueId(String ottTerminalUniqueId) {
		this.ottTerminalUniqueId = ottTerminalUniqueId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTokenID() {
		return tokenID;
	}
	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}
	public String getClientHash() {
		return clientHash;
	}
	public void setClientHash(String clientHash) {
		this.clientHash = clientHash;
		this.clientHash = this.clientHash.replace("\n", "");
	}
	public Map<String, String> getHeaderMap() {
		return headerMap;
	}
	public void setHeaderMap(Map<String, String> headerMap) {
		this.headerMap = headerMap;
	}
	public Context getContext() {
		return context;
	}
	public void setContext(Context context) {
		this.context = context;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Header[] getHeaders() {
		return headers;
	}
	public void setHeaders(Header[] headers) {
		this.headers = headers;
	}
	public HttpEntity getEntity() {
		return entity;
	}
	public void setEntity(HttpEntity entity) {
		this.entity = entity;
	}
	public String getRequestCancelName() {
		return requestCancelName;
	}
	public void setRequestCancelName(String requestCancelName) {
		this.requestCancelName = requestCancelName;
	}
	
}
