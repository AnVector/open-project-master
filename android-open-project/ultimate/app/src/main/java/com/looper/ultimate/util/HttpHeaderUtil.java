package com.looper.ultimate.util;

import android.content.Context;
import android.os.Build;
import android.util.Base64;

import org.apache.http.message.BasicHeader;

import java.io.UnsupportedEncodingException;


public class HttpHeaderUtil {

    private HttpHeaderUtil() {
        throw new AssertionError();
    }

    /****
     * 用户登录接口需要的请求头
     *
     * @return
     */
    public static BasicHeader[] getUserLoginHeaders(Context context) {

        BasicHeader[] headers = new BasicHeader[9];
        headers[0] = new BasicHeader("Client-Agent", getClientAgent(context));
        headers[1] = new BasicHeader("APIVersion", "2.7");
        headers[2] = new BasicHeader("Content-Type", "application/json");
        headers[3] = new BasicHeader("ClientHash", encryptClientHash("",context)); // 用户首次登录前userid还没有获取到
        headers[4] = new BasicHeader("terminalUniqueId",
                "3256462645634434564");
        headers[5] = new BasicHeader("user-id", "");
        headers[6] = new BasicHeader("tokenId", "");
        headers[7] = new BasicHeader("X-Channel-Code", "J00140001");
        headers[8] = new BasicHeader("sub_version","ZJJK");

        return headers;
    }

    public static String getClientAgent(Context context) {
        String clientAgent = "V" + AppUtils.getVersionName(context) + "/"
                + ScreenUtils.getScreenWidth(context) + "*"
                + ScreenUtils.getScreenHeight(context) + "/ott_"
                + Build.VERSION.RELEASE; // V1.0.00/240*320/ott_5.0
        return clientAgent;
    }

    public static String getClientHash(Context context) {
        String clientHash = encryptClientHash("",context);
        return clientHash;
    }

    /****
     * 加密形成ClientHash
     */
    public static String encryptClientHash(String userId, Context context) {
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

    /****
     * 加密形成
     */
    public static String encryptPassword(String psw) {
        String base64;
        try {
            base64 = Base64.encodeToString(psw.getBytes("UTF-8"),
                    Base64.DEFAULT);
            base64 = base64.replace("\n", "");
            return base64.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }



}
