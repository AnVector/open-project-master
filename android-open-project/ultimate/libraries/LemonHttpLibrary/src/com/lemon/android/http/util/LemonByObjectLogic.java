package com.lemon.android.http.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.lemon.android.json.LemonJSONUtil;

public class LemonByObjectLogic<T> extends LemonAbstractLogic<T> {

	@Override
	protected String getWantedString(String result) {
		try {
			JSONObject jsonRoot = new JSONObject(result);
			if (LemonJSONUtil.isJsonDataEffective(jsonRoot, "data")) {
				JSONObject jsonData = jsonRoot.getJSONObject("data");
				return jsonData.toString();
			} 
		} catch (JSONException e) {
		}
		return null;
	}
	
}
