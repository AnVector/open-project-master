package com.lemon.android.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LemonJSON <T> extends TypeToken<T> {
	
	public T objectFromJson(String json) {
		Gson gson = new Gson();
		return (T) gson.fromJson(json, this.getRawType());
	}
	
	public T listFromJson(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, this.getType());
	}
}
