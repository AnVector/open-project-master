package com.lemon.android.json;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.http.util.EncodingUtils;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class LemonSerializer<T> extends TypeToken<T> {
	
	private static void writeFile(Context context, String fileName, String content) {
		try {
			OutputStream outStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			byte[] buffer = content.getBytes();
			outStream.write(buffer);
			outStream.flush();
			outStream.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		} 
	}
	
	private static String readFile(Context context, String fileName) {
		try {
			FileInputStream inputStream = context.openFileInput(fileName);
			int length = inputStream.available();
			byte[] buffer = new byte[length];
			inputStream.read(buffer);
			inputStream.close();
			
			String content = EncodingUtils.getString(buffer, "UTF-8");
			return content;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static void writeObjectToFile(Context context, String fileName, Object object) {
		Gson gson = new Gson();
		String content = gson.toJson(object);
		writeFile(context, fileName, content);
	}
	
	public T readFileToObject(Context context, String fileName) {
		String content = readFile(context, fileName);
		if (content != null) {
			Gson gson = new Gson();
			return (T) gson.fromJson(content, this.getType());
		} else {
			return null;
		}
	}
}
