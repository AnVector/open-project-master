package com.lemon.android.http.util;


public class LemonDefaultLogic<T> extends LemonAbstractLogic<T> {

	@Override
	protected String getWantedString(String result) {
		return result;
	} 

}
