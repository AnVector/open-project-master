package com.lemon.android.json;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class LemonJSONUtil {
	
	
	public static boolean isJsonDataEffective(JSONObject json, String name) {
		try {
			return json.has(name) && json.get(name) != null && !"".equals(json.getString(name))
					&& !"null".equals(json.getString(name));
		} catch (JSONException e) {
			return false;
		}
	}
	
	public static boolean isJsonArrayDataEffective(JSONObject json, String name) {
		try {
			return json.has(name) && json.getJSONArray(name) != null && json.getJSONArray(name).length() > 0;
		} catch (JSONException e) {
			return false;
		}
	}
}
