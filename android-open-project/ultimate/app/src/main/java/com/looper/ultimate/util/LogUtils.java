package com.looper.ultimate.util;

import android.util.Log;

import com.looper.ultimate.BuildConfig;

/**
 * @author 作者 kmh
 * @version 创建时间：2016-4-18 上午10:24:31
 *          类说明
 */
public class LogUtils {

    private static boolean isDebug = true;
    private static boolean debugMode = BuildConfig.DEBUG;

    public static void setDebugMode(boolean debugMode) {
        isDebug = debugMode;
    }

    /**
     * @param arg log信息
     */
    public static void d(String arg) {
        if (debugMode && isDebug) {
            Log.d(getTag(), buildLogString(false, arg));
        }
    }

    /**
     * @param arg log信息
     */
    public static void v(String arg) {
        if (debugMode && isDebug) {
            Log.v(getTag(), buildLogString(false, arg));
        }
    }

    /**
     * @param arg log信息
     */
    public static void e(String arg) {
        if (debugMode && isDebug) {
            Log.e(getTag(), buildLogString(false, arg));
        }
    }

    /**
     * @param tag Tag
     * @param arg log信息
     */
    public static void d(String tag, String arg) {
        if (debugMode && isDebug) {
            Log.d(tag, buildLogString(true, arg));
        }
    }

    /**
     * @param tag Tag
     * @param arg log信息
     */
    public static void v(String tag, String arg) {
        if (debugMode && isDebug) {
            Log.v(tag, buildLogString(true, arg));
        }
    }

    /**
     * @param tag Tag
     * @param arg log信息
     */
    public static void e(String tag, String arg) {
        if (debugMode && isDebug) {
            Log.e(tag, buildLogString(true, arg));
        }
    }

    /**
     * @param tag      Tag
     * @param arg      log信息
     * @param showInfo 是否显示log所在方法名
     */
    public static void d(String tag, String arg, boolean showInfo) {
        if (debugMode && isDebug) {
            Log.d(tag, buildLogString(arg, showInfo));
        }
    }

    /**
     * @param tag      Tag
     * @param arg      log信息
     * @param showInfo 是否显示log所在方法名
     */
    public static void v(String tag, String arg, boolean showInfo) {
        if (debugMode && isDebug) {
            Log.v(tag, buildLogString(arg, showInfo));
        }
    }

    /**
     * @param tag      Tag
     * @param arg      log信息
     * @param showInfo 是否显示log所在方法名
     */
    public static void e(String tag, String arg, boolean showInfo) {
        if (debugMode && isDebug) {
            Log.e(tag, buildLogString(arg, showInfo));
        }
    }

    /**
     * 类名+所在行数
     */
    private static String getTag() {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        return caller.getFileName() + ":" + caller.getLineNumber();
    }

    private static String buildLogString(boolean showClass, String str) {

        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        StringBuilder stringBuilder = new StringBuilder();

        if (showClass) {
            stringBuilder
                    .append("(")
                    .append(caller.getFileName())
                    .append(":")
                    .append(caller.getLineNumber())
                    .append(").")
                    .append(caller.getMethodName())
                    .append("():")
                    .append(str);
        } else {
            stringBuilder.append(caller.getMethodName())
                    .append("():")
                    .append(str);
        }
        return stringBuilder.toString();

    }

    /**
     * 对外提供的控制是否显示详细信息
     */
    private static String buildLogString(String str, boolean showInfo) {
        StackTraceElement caller = new Throwable().fillInStackTrace().getStackTrace()[2];
        StringBuilder stringBuilder = new StringBuilder();
        if (showInfo) {
            stringBuilder
                    .append("(")
                    .append(caller.getFileName())
                    .append(":")
                    .append(caller.getLineNumber())
                    .append(").")
                    .append(caller.getMethodName())
                    .append("():")
                    .append(str);
        } else {
            stringBuilder.append(str);
        }
        return stringBuilder.toString();
    }
}
 