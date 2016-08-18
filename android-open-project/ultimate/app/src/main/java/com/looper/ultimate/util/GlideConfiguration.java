package com.looper.ultimate.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Administrator on 2016/6/28.
 */
public class GlideConfiguration implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {

        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        //获取默认的内存缓存大小
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        //获取默认的bitmap缓存池大小
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();
        //设置内存缓存方式和大小，Glide的用来保存在内存中的内存缓存资源,使其马上可用,而不必执行I / O。
        builder.setMemoryCache(new LruResourceCache((int)(1.2*defaultMemoryCacheSize)));//GlideLruResourceCache类的默认实现。
        //设置位图的大小和实现池
        builder.setBitmapPool(new LruBitmapPool((int)(1.2*defaultBitmapPoolSize)));//LruBitmapPool类的是Glide的默认实现

        //设置磁盘高速缓存的实现方式和大小,默认情况下Glide使用InternalCacheDiskCacheFactory类建立磁盘缓存。
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context,1048576 * 20));
        //设置Glide BitMap规格，默认情况下Glide使用RGB_565,因为它只需要两个字节,
        // 比使用高质量和系统默认ARGB_8888节省一半的内存。然而RGB_565在某些图像条带会有问题，也不支持透明度。
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

    }

    @Override
    public void registerComponents(Context context, Glide glide) {

//        glide.setMemoryCategory(MemoryCategory.HIGH);
    }
}
