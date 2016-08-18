package com.looper.ultimate.util;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;

import java.io.File;

/**
 * Created by Administrator on 2016/7/11.
 */
public class DownloadManager {

    private volatile static DownloadManager INSTANCE = null;

    private DownloadManager() {

    }

    public static DownloadManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DownloadManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DownloadManager();
                }
            }
        }
        return INSTANCE;
    }

    public static void downloadFile() {

        FinalHttp fh = new FinalHttp();

        fh.download("http://211.140.7.182:9100/client/file/apkfile/OTT_V1.1.1_J0020066_ZJJK.apk", "/mnt/sdcard/testapk.apk", true, new AjaxCallBack<File>() {
            @Override
            public void onLoading(long count, long current) {
                super.onLoading(count, current);
                LogUtils.d("download","下载进度："+current+"/"+count);
            }

            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
            }
        });
    }
}
