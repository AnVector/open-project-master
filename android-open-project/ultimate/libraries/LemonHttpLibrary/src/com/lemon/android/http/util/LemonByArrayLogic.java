package com.lemon.android.http.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.lemon.android.json.LemonJSONUtil;

public class LemonByArrayLogic<T> extends LemonAbstractLogic<T> {

	@Override
	protected String getWantedString(String result) {
		try {
			JSONObject jsonRoot = new JSONObject(result);
	        if (LemonJSONUtil.isJsonArrayDataEffective(jsonRoot, "video")) {
	        	JSONArray videoJsonArray = jsonRoot.getJSONArray("video");
	        	JSONObject viewItemJson = videoJsonArray.getJSONObject(0);
	        	return viewItemJson.toString();
	        } 
		} catch (JSONException e) {
		}
		return null;
	}
	
}
