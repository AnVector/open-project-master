package com.looper.ultimate.util;

import android.annotation.SuppressLint;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class PictureUtils {

    public static final CharSequence[] SetPhotoItems = {"拍照", "选择本地相册"};

    public static final String IMAGE_UNSPECIFIED = "image/*";

    private PictureUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 将
     *
     * @param filename     文件名，全路径
     * @param jpgGetWidth  照片宽
     * @param jpgGetHeight 照片高
     * @return
     */
    public static Bitmap decodeFile(String filename, int jpgGetWidth, int jpgGetHeight) {
        Bitmap b = null;
        try {
            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filename, op);

            int jpgWidth = op.outWidth;
            int jpgHeight = op.outHeight;

            int wSendRatio = (int) Math.ceil(jpgWidth / Double.valueOf(jpgGetWidth));
            int hSendRatio = (int) Math.ceil(jpgHeight / Double.valueOf(jpgGetHeight));
            if (wSendRatio > 1 && hSendRatio > 1) {
                op.inSampleSize = wSendRatio > hSendRatio ? wSendRatio : hSendRatio;
            }
            op.inJustDecodeBounds = false;
            b = BitmapFactory.decodeFile(filename, op);

        } catch (Exception e) {
        }
        return b;
    }

    /**
     * 将图片存储至SD卡，需判断是否装有SD卡、是否可读写、是否有空间，否则提示出错
     *
     * @param ctx      上下文
     * @param jpeg     要存储的照片
     * @param quality  压缩照片的质量，0至100，100最佳，一般80-90
     * @param filePath 存储的路径
     * @param filename 照片的名称
     * @return
     */
    public static boolean save_picture(Context ctx, Bitmap bitmap, int quality, String filePath, String filename) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        byte[] data = baos.toByteArray();

        if (!SDCardUtils.checkSDStatus(data.length / 1024 / 1024)) {
            Toast.makeText(ctx, "您的储存卡有错误", Toast.LENGTH_SHORT).show();
            return false;
        }

        try {
            File destDir = new File(filePath);
            if (!destDir.exists())
                destDir.mkdirs();

            String path = filePath + "/" + filename;
            File file = new File(path);
            if (!file.exists())
                file.createNewFile();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(data);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     * 获得圆角图片的方法
     *
     * @param bitmap  需处理的图片
     * @param roundPx 圆角的弧率
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, float roundPx) {

        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    /**
     * 图片中绘入GPS和时间等文字
     *
     * @param bitmap   需处理的图片
     * @param datetime 时间
     * @param lat      经度
     * @param lng      纬度
     * @return
     */
    public static Bitmap getGpsBitmap(Bitmap bitmap, String datetime, String lat, String lng) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
        /* 把位图写进画布canvas类 */
        Canvas canvas = new Canvas(output);
		/* 画布的区域 */
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		/* 喷漆Paint类 */
        Paint paint = new Paint();
        paint.setAntiAlias(true);//消除锯齿
        paint.setColor(Color.RED);//着色
        paint.setTextSize(16);//字体大小
        canvas.drawText("经度:" + lng, 10, 20, paint);
        canvas.drawText("纬度:" + lat, 10, 38, paint);
        canvas.drawText("时间:" + datetime, 10, 56, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.DST_ATOP));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 裁切图片
     *
     * @param originFile 源文件
     * @param TargetFile 目标文件
     * @param aspect     宽高比例，如果为null，则不限制
     * @param output     输出分辨率
     * @return
     */
    public static Intent ZoomPhoto(File originFile, File TargetFile, int[] aspect, int[] output) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(Uri.fromFile(originFile), IMAGE_UNSPECIFIED);
        intent.putExtra("crop", "true");
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", false);

        if (null != output) {
            BitmapFactory.Options op = new BitmapFactory.Options();
            op.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(originFile.getPath(), op);

            int jpgWidth = op.outWidth;
            int jpgHeight = op.outHeight;

            if (jpgWidth > output[0] && jpgHeight > output[1]) {
                intent.putExtra("outputX", output[0]);
                intent.putExtra("outputY", output[1]);
            }
        }

        if (null != aspect) {
            intent.putExtra("aspectX", aspect[0]);
            intent.putExtra("aspectY", aspect[1]);
        }

        if (!TargetFile.exists())
            try {
                TargetFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(TargetFile));
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        return intent;
    }

    /**
     * 从相册中选择一张照片之后，获取该照片的绝对路径
     *
     * @param ctx
     * @param photoUri
     * @return
     */
    public static String getPickPhotoPath(Context ctx, Uri photoUri) {
        Cursor cursor = null;
        try {
            cursor = ctx.getContentResolver().query(photoUri, null, null, null, null);
            cursor.moveToFirst();
            String imgPath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));

            return imgPath;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        } finally {
            if (null != cursor)
                cursor.close();
        }
    }

    public static File getPickPhotoFile(Context ctx, Uri photoUri) {
        String imgPath = getPickPhotoPath(ctx, photoUri);
        if (!TextUtils.isEmpty(imgPath))
            return new File(imgPath);
        else
            return null;
    }

    /**
     * 获取联系人头像
     *
     * @param ctx           上下文
     * @param photo_id      头像ID，须大于0
     * @param contacts_id   用户ID
     * @param preferHighres 是否显示高清头像，仅支持API14以上
     * @return
     */
    @SuppressLint("NewApi")
    public static Bitmap getContactHead(Context ctx, long photo_id, long contacts_id, boolean preferHighres) {
        if (photo_id <= 0)
            return null;

        Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contacts_id);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(ctx.getContentResolver(), uri, preferHighres);
            return BitmapFactory.decodeStream(input);
        } else {
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(ctx.getContentResolver(), uri);
            return BitmapFactory.decodeStream(input);
        }
    }

    /**
     * 压缩图片大小，避免图片过大，保持比例不变，宽或高不超过XX个像素
     *
     * @param newName    新的文件名称
     * @param filePath   原文件全路径，包含文件名
     * @param attachPath 处理过后，文件存放的位置
     * @param String     新的文件全路径
     */
    public static String compressPixelPhotos(final Context ctx, final String newName, final String filePath,
                                             final String attachPath) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, op);

        int jpgWidth = op.outWidth;
        int jpgHeight = op.outHeight;

        if (jpgWidth > 800 || jpgHeight > 800) {
            int wSendRatio = (int) Math.ceil(jpgWidth / 800.0f);
            int hSendRatio = (int) Math.ceil(jpgHeight / 800.0f);
            if (wSendRatio > 1 && hSendRatio > 1) {
                op.inSampleSize = wSendRatio > hSendRatio ? wSendRatio : hSendRatio;
            }
            op.inJustDecodeBounds = false;
            Bitmap b = BitmapFactory.decodeFile(filePath, op);

            if (!save_picture(ctx, b, 90, attachPath, newName)) {
                SDCardUtils.copyFileToFile(filePath, attachPath + File.separator + newName);
            }

            if (b != null && !b.isRecycled())
                b.recycle();

        } else {
            SDCardUtils.copyFileToFile(filePath, attachPath + File.separator + newName);
        }

        return attachPath + File.separator + newName;
    }

    /**
     * 检查图片分辨率大小，是否需要压缩
     *
     * @param ctx
     * @param filePath
     * @return
     */
    public static boolean compressPixelPhotosCheck(final Context ctx, final String filePath) {
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, op);

        return op.outWidth > 800 || op.outHeight > 800;
    }
}
