package com.looper.ultimate.util;

import android.content.Context;
import android.os.Build;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class HttpHeaderUtil {

    private HttpHeaderUtil() {
        throw new AssertionError();
    }


    public static Map<String,String> getHeader(Context context){
        Map<String, String> mHeader = new HashMap<>();
        mHeader.put("Client-Agent", HttpHeaderUtil.getClientAgent(context));
        mHeader.put("APIVersion", "2.7");
        mHeader.put("Content-Type", "application/json;charset=utf-8");
        mHeader.put("ClientHash", HttpHeaderUtil.encryptClientHash("", context));
        mHeader.put("terminalUniqueId", "3256462645634434564");
        mHeader.put("user-id", "");
        mHeader.put("tokenId", "");
        mHeader.put("X-Channel-Code", "J00140001");
        mHeader.put("sub_version", "ZJJK");
        return mHeader;
    }

    private static String getClientAgent(Context context) {
        String clientAgent = "V" + AppUtils.getVersionName(context) + "/"
                + ScreenUtils.getScreenWidth(context) + "*"
                + ScreenUtils.getScreenHeight(context) + "/ott_"
                + Build.VERSION.RELEASE; // V1.0.00/240*320/ott_5.0
        return clientAgent;
    }

    /****
     * 加密形成ClientHash
     */
    private static String encryptClientHash(String userId, Context context) {
        String clientVersion = "V" + AppUtils.getVersionName(context); // apk版本号
        String pwd = "SDF213$#@$SAas1="; // 客户端内置密码
        String md5Str = MD5.Md5(clientVersion + userId + pwd);
        String base64;
        try {
            base64 = Base64.encodeToString(md5Str.getBytes("UTF-8"),
                    Base64.DEFAULT);
            base64 = base64.replace("\n", "");
            return base64.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
