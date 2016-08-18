package com.lemon.android.http.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lemon.android.json.LemonJSONUtil;

public class LemonDotnetLogic<T> extends TypeToken<T>{
	
	private String mName;
	
	public LemonDotnetLogic(String name) {
		this.mName = name;
	}
	
	private String getWantedString(String result) {
		try {
			JSONObject jsonRoot = new JSONObject(result);
			if (LemonJSONUtil.isJsonDataEffective(jsonRoot, mName)) {
				JSONObject jsonData = jsonRoot.getJSONObject(mName);
				return jsonData.toString();
			} 
		} catch (JSONException e) {
			
		}
		return null;
	}
	
	public T getObjectFromString(String result) {
		String content = getWantedString(result);
		if (content != null) {
			Gson gson = new Gson();
			return (T) gson.fromJson(content, this.getType());
		} else {
			return null;
		}
	}
}
