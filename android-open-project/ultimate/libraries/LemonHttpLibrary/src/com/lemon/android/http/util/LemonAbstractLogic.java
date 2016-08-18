package com.lemon.android.http.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public abstract class LemonAbstractLogic<T> extends TypeToken<T> {
	
	protected abstract String getWantedString(String result);
	
	public T getObjectFromString(String result) {
		String content = getWantedString(result);
		if (content != null) {
			Gson gson = new Gson();
//			Gson gson = new GsonBuilder().serializeNulls().create();
			return (T) gson.fromJson(content, this.getType());
		} else {
			return null;
		}
	}
}
