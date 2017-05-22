package com.jingewenku.abrahamcaijin.commonutil;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.graphics.*;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import java.io.*;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Description:主要功能:图片管理工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月22日 15:52
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressLint("NewApi")
public class AppImageMgr {
    private WeakHashMap<Integer, WeakReference<Bitmap>> mBitmaps;
    private WeakHashMap<Integer, WeakReference<Drawable>> mDrawables;
    private Context mContext;
    // 下载图片，最大边长
    public static int MIN_SIDE_LENGTH = 256;
    // 是否重新计算压缩比
    public static boolean isComputeSampleSize = false;

    private static final long POLY64REV = 0x95AC9329AC4BC9B5L;
    private static final long INITIALCRC = 0xFFFFFFFFFFFFFFFFL;

    private static long[] sCrcTable = new long[256];

    public AppImageMgr(Context context) {
        mContext = context.getApplicationContext();
        mBitmaps = new WeakHashMap<Integer, WeakReference<Bitmap>>();
        mDrawables = new WeakHashMap<Integer, WeakReference<Drawable>>();
    }


    /**
     * 根据drawable id获取Bitmap
     *
     * @param resource
     * @return
     */
    public Bitmap getBitmap(int resource) {
        if (!mBitmaps.containsKey(resource) && mContext != null) {
            mBitmaps.put(resource, new WeakReference<Bitmap>(
                readDrawableBitmap(mContext, resource)));
        }
        return ((WeakReference<Bitmap>) mBitmaps.get(resource)).get();
    }

    /**
     * 根据drawable id获取Drawable
     *
     * @param resource
     * @return
     */
    public Drawable getDrawable(int resource) {
        try {
            if (!mDrawables.containsKey(resource) && mContext != null) {
                try {
                    mDrawables.put(resource, new WeakReference<Drawable>(mContext
                        .getResources().getDrawable(resource)));
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
            return ((WeakReference<Drawable>) mDrawables.get(resource)).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return readBitmapResIdToDrawable(mContext, resource);
    }

    /**
     *bitmap转drawable
     * @param uri
     * @param mcontext
     * @return
     */
    public static Drawable bitmapToDrawble(Uri uri,Context mcontext){
        Drawable drawable = new BitmapDrawable(mcontext.getResources(), getBitmapFromUri(mcontext, uri));
        return drawable;
    }


    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void recycleBitmaps() {
        final Iterator itr = mBitmaps.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry e = (Map.Entry) itr.next();
            if (e != null) {
                final Bitmap bitmap = ((WeakReference<Bitmap>) e.getValue()).get();
                if (bitmap != null) {
                    bitmap.recycle();
                }
            }
        }
        mBitmaps.clear();
    }

    /**
     *
     * @param context
     * @param resId
     * @return
     */
    public static Drawable readBitmapResIdToDrawable(Context context, int resId) {
        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        final InputStream is = context.getResources().openRawResource(resId);
        final Bitmap btm = BitmapFactory.decodeStream(is, null, opt);
        if (btm != null) {
            final BitmapDrawable bd = new BitmapDrawable(btm);
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bd;
        }
        return null;
    }

    /**
     * 以最省内存的方式读取本地资源的图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readDrawableBitmap(Context context, int resId) {
        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        final InputStream is = context.getResources().openRawResource(resId);
        final Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);

        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    /**
     *
     * @param filename
     * @return
     */
    public static Bitmap readBitmap565FromFile(String filename) {
        Bitmap bitmap = null;
        File file = new File(filename);
        if (file.exists()) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            options.inPurgeable = true;
            options.inInputShareable = true;
            try {
                bitmap = BitmapFactory.decodeFile(filename, options);
                if (bitmap == null) {
                    file.delete();
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();

                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
                System.gc();
            }
        }

        return bitmap;
    }

    /**
     * 读取本地drawable中较大的资源图片
     *
     * @param context
     * @param resId
     * @return
     */
    public static Bitmap readDrawableBigBitmap(Context context, int resId) {
        final InputStream is = context.getResources().openRawResource(resId);
        // final BitmapFactory.Options options=new BitmapFactory.Options();
        // options.inJustDecodeBounds = true;
        // // 如果该
        // 值设为true那么将不返回实际的bitmap，也不给其分配内存空间这样就避免内存溢出了。但是允许我们查询图片的信息这其中就包括图片大小信息（
        // // options.outHeight (图片原始高度)和option.outWidth(图片原始宽度)）。
        // BitmapFactory.decodeStream(is, null, options);
        // options.inSampleSize = computeSampleSize(options, -1, 256*256);
        // options.inJustDecodeBounds = false;
        // return BitmapFactory.decodeStream(is, null, options);

        final Bitmap bitmap = getBitmapFromStream(is, 256, 256);
        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    //从文件得到BitMap
    public static Bitmap getBitmapFromFile(String path, int width, int height) {
        if (!TextUtils.isEmpty(path)) {
            final File file = new File(path);
            return getBitmapFromFile(file, width, height);
        }
        return null;
    }

    //从文件得到BitMap
    public static Bitmap getBitmapFromFile(File dst, int width, int height) {
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);
                // 计算图片缩放比例
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength,
                    width * height);
                opts.inPreferredConfig = Bitmap.Config.RGB_565;
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
                System.gc();
            }
        }
        return null;
    }

    /**
     * 从数组得到Bitmap
     * @param data
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmapByteArray(byte[] data, int width, int height) {
        BitmapFactory.Options opts = null;
        if (width > 0 && height > 0) {
            opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeByteArray(data, 0, data.length, opts);
            // 计算图片缩放比例
            final int minSideLength = Math.min(width, height);
            opts.inSampleSize = computeSampleSize(opts, minSideLength, width
                * height);
            opts.inJustDecodeBounds = false;
            opts.inInputShareable = true;
            // 使得内存可以被回收
            opts.inPurgeable = true;
            opts.inPreferredConfig = Bitmap.Config.RGB_565;
        }

        try {
            return BitmapFactory.decodeByteArray(data, 0, data.length, opts);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }

    /**
     * 从流中得到Bitmap
     * @param is
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitmapFromStream(InputStream is, int width,
        int height) {
        BitmapFactory.Options opts = null;
        if (width > 0 && height > 0) {
            opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(is, null, opts);
            // 计算图片缩放比例
            final int minSideLength = Math.min(width, height);
            opts.inSampleSize = computeSampleSize(opts, minSideLength, width
                * height);
            opts.inJustDecodeBounds = false;
            opts.inInputShareable = true;
            // 使得内存可以被回收
            opts.inPurgeable = true;
            opts.inPreferredConfig = Bitmap.Config.RGB_565;
        }

        try {
            return BitmapFactory.decodeStream(is, null, opts);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return null;
    }


    /**
     *
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    private static int computeInitialSampleSize(BitmapFactory.Options options,
        int minSideLength, int maxNumOfPixels) {

        final double w = options.outWidth;
        final double h = options.outHeight;

        final int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
            .sqrt(w * h / maxNumOfPixels));

        final int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
            Math.floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    /**
     * 图片透明度处理
     *
     * @param sourceImg
     * 原始图片
     * @param number
     * 透明度
     * @return
     */
    public static Bitmap setAlpha(Bitmap sourceImg, int number) {
        try {
            int[] argb = new int[sourceImg.getWidth() * sourceImg.getHeight()];
            sourceImg.getPixels(argb, 0, sourceImg.getWidth(), 0, 0,
                sourceImg.getWidth(), sourceImg.getHeight());// 获得图片的ARGB值
            number = number * 255 / 100;
            for (int i = 0; i < argb.length; i++) {
                if ((argb[i] & 0xff000000) != 0x00000000) {// 透明色不做处理
                    argb[i] = (number << 24) | (argb[i] & 0xFFFFFF);// 修改最高2位的值
                }
            }
            sourceImg = Bitmap.createBitmap(argb, sourceImg.getWidth(),
                sourceImg.getHeight(), Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return sourceImg;
    }

    /**
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {

        Bitmap bitmap = null;
        try {
            bitmap = Bitmap
                .createBitmap(
                    drawable.getIntrinsicWidth(),
                    drawable.getIntrinsicHeight(),
                    drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
            final Canvas canvas = new Canvas(bitmap);
            // canvas.setBitmap(bitmap);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());
            drawable.draw(canvas);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
                bitmap = null;
            }
            System.gc();
        }

        return bitmap;
    }

    /**
     * 获取源图片的BITMAP，压缩，本地图片
     *
     * @param sImagePath
     * @return
     */
    public static Bitmap getImgCacheFromLocal2Bitmap(String sImagePath) {
        if (!TextUtils.isEmpty(sImagePath)) {
            Bitmap bitmap = null;
            try {
                final File f = new File(sImagePath);
                if (!f.exists()) {
                    return null;
                }
                final FileInputStream fis = new FileInputStream(f);
                // bitmap = BitmapFactory.decodeStream(fis);
                final BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = false;
                options.inSampleSize = 1; // width，hight设为原来的十分一
                options.inPreferredConfig = Bitmap.Config.RGB_565;
                options.inPurgeable = true;
                options.inInputShareable = true;
                bitmap = BitmapFactory.decodeStream(fis, null, options);
                fis.close();
                return bitmap;
            } catch (Exception ex) {
                ex.printStackTrace();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
                System.gc();
                return null;
            } catch (OutOfMemoryError ex) {
                ex.printStackTrace();
                if (bitmap != null && !bitmap.isRecycled()) {
                    bitmap.recycle();
                    bitmap = null;
                }
                System.gc();
                return null;
            }
        }
        return null;
    }

    /**
     *
     * @param sImagePath
     * @return
     */
    public static byte[] getImgCacheFromLocal2Byte(String sImagePath) {
        if (!TextUtils.isEmpty(sImagePath)) {
            try {
                final File f = new File(sImagePath);
                if (!f.exists()) {
                    return null;
                }
                final FileInputStream fis = new FileInputStream(f);
                final int length = fis.available();
                final byte[] buffer = new byte[length];
                fis.read(buffer);
                fis.close();
                return buffer;
            } catch (Exception ex) {
                ex.printStackTrace();
                System.gc();
                return null;
            }
        }
        return null;
    }

    /**
     * bitmap转byte[]
     *
     * @param bitmap
     * @return
     */
    public static byte[] getBitmap2Byte(Bitmap bitmap) {
        if (bitmap != null) {
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            final byte[] data = baos.toByteArray();
            try {
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        return null;
    }

    /**
     * 获取缩略图
     *
     * @param bitmap
     * 是否转成缩略图
     * @return
     */
    public static Bitmap decodeBitmapToThumbnail(Bitmap bitmap) {
        return decodeBitmapToThumbnail(bitmap, true);
    }

    /**
     *
     * @param bitmap
     * @param isThumbnail
     * @return
     */
    public static Bitmap decodeBitmapToThumbnail(Bitmap bitmap,
        boolean isThumbnail) {
        if (isThumbnail) {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;

            final float realWidth = options.outWidth;
            final float realHeight = options.outHeight;

            // 计算缩放比
            int scale = (int) ((realHeight > realWidth ? realHeight : realWidth) / 100);
            if (scale <= 0) {
                scale = 1;
            }
            options.inSampleSize = scale;
            options.inJustDecodeBounds = false;
            // 注意这次要把options.inJustDecodeBounds 设为 false,这次图片是要读取出来的。
            final byte[] data = AppImageMgr.getBitmap2Byte(bitmap);
            if (data != null) {
                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length,
                    options);
            }

        }
        return bitmap;
    }

    /**
     * 保存图片
     *
     * @param oldbitmap
     * @param sNewImagePath
     * @return
     */
    public static boolean saveImage(Bitmap oldbitmap, String sNewImagePath) {

        try {
            final FileOutputStream fileout = new FileOutputStream(sNewImagePath);
            oldbitmap.compress(CompressFormat.JPEG, 80, fileout);
            fileout.flush();
            fileout.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
            return false;
        }
    }

    /**
     *
     * @param oldbitmap
     * @param sNewImagePath
     * @return
     */
    public static boolean saveImage(byte[] oldbitmap, String sNewImagePath) {
        try {
            File file = new File(sNewImagePath);
            if (file != null && !file.exists()) {
                file.createNewFile();
            }
            final FileOutputStream fileout = new FileOutputStream(sNewImagePath);
            fileout.write(oldbitmap);
            fileout.flush();
            fileout.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
            return false;
        }
    }

    /**
     *
     * @param b
     * @return
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b != null && b.length != 0) {
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
            return bitmap;
        } else {
            return null;
        }
    }

    /**
     * 光晕效果
     *
     * @param bmp
     * @param x
     * 光晕中心点在bmp中的x坐标
     * @param y
     * 光晕中心点在bmp中的y坐标
     * @param r
     * 光晕的半径
     * @return
     */
    public static Bitmap grayMasking(Bitmap bmp, int x, int y, float r) {
        // 高斯矩阵
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height,
            Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;

        int delta = 18; // 值越小图片会越亮，越大则越暗

        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++) {
            for (int k = 1, len = width - 1; k < len; k++) {
                idx = 0;
                int distance = (int) (Math.pow(k - x, 2) + Math.pow(i - y, 2));
                // 不是中心区域的点做模糊处理
                if (distance > r * r) {
                    for (int m = -1; m <= 1; m++) {
                        for (int n = -1; n <= 1; n++) {
                            pixColor = pixels[(i + m) * width + k + n];
                            pixR = Color.red(pixColor);
                            pixG = Color.green(pixColor);
                            pixB = Color.blue(pixColor);

                            newR = newR + (int) (pixR * gauss[idx]);
                            newG = newG + (int) (pixG * gauss[idx]);
                            newB = newB + (int) (pixB * gauss[idx]);
                            idx++;
                        }
                    }

                    newR /= delta;
                    newG /= delta;
                    newB /= delta;

                    newR = Math.min(255, Math.max(0, newR));
                    newG = Math.min(255, Math.max(0, newG));
                    newB = Math.min(255, Math.max(0, newB));

                    pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                    newR = 0;
                    newG = 0;
                    newB = 0;
                }
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    /**
     * 获取bitmap的字节大小
     * @param bitmap
     * @return
     */
    public static int getBitmapSize(Bitmap bitmap) {

        // if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){ //API 19
        // return bitmap.getAllocationByteCount();
        // }
        //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1){//API 12
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight(); //earlier version
    }

    public static byte[] getBytes(String in) {
        byte[] result = new byte[in.length() * 2];
        int output = 0;
        for (char ch : in.toCharArray()) {
            result[output++] = (byte) (ch & 0xFF);
            result[output++] = (byte) (ch >> 8);
        }
        return result;
    }

    public static boolean isSameKey(byte[] key, byte[] buffer) {
        int n = key.length;
        if (buffer.length < n) {
            return false;
        }
        for (int i = 0; i < n; ++i) {
            if (key[i] != buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public static byte[] copyOfRange(byte[] original, int from, int to) {
        int newLength = to - from;
        if (newLength < 0)
            throw new IllegalArgumentException(from + " > " + to);
        byte[] copy = new byte[newLength];
        System.arraycopy(original, from, copy, 0,Math.min(original.length - from, newLength));
        return copy;
    }



    static {
        //参考 http://bioinf.cs.ucl.ac.uk/downloads/crc64/crc64.c
        long part;
        for (int i = 0; i < 256; i++) {
            part = i;
            for (int j = 0; j < 8; j++) {
                long x = ((int) part & 1) != 0 ? POLY64REV : 0;
                part = (part >> 1) ^ x;
            }
            sCrcTable[i] = part;
        }
    }

    public static byte[] makeKey(String httpUrl) {
        return getBytes(httpUrl);
    }

    /**
     * A function thats returns a 64-bit crc for string
     *
     * @param in input string
     * @return a 64-bit crc value
     */
    public static final long crc64Long(String in) {
        if (in == null || in.length() == 0) {
            return 0;
        }
        return crc64Long(getBytes(in));
    }

    public static final long crc64Long(byte[] buffer) {
        long crc = INITIALCRC;
        for (int k = 0, n = buffer.length; k < n; ++k) {
            crc = sCrcTable[(((int) crc) ^ buffer[k]) & 0xff] ^ (crc >> 8);
        }
        return crc;
    }

    /**
     * 将彩色图转换为黑白图
     *
     * @return 返回转换好的位图
     */
    public static Bitmap convertToBlackWhite(Bitmap bmp) {
        int width = bmp.getWidth(); // 获取位图的宽
        int height = bmp.getHeight(); // 获取位图的高

        int[] pixels = new int[width * height]; // 通过位图的大小创建像素点数组

        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        int alpha = 0xFF << 24;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];

                int red = ((grey & 0x00FF0000) >> 16);
                int green = ((grey & 0x0000FF00) >> 8);
                int blue = (grey & 0x000000FF);

                grey = (int) (red * 0.3 + green * 0.59 + blue * 0.11);
                grey = alpha | (grey << 16) | (grey << 8) | grey;
                pixels[width * i + j] = grey;
            }
        }
        Bitmap newBmp = Bitmap.createBitmap(width, height, Config.RGB_565);
        newBmp.setPixels(pixels, 0, width, 0, 0, width, height);
        return newBmp;
    }

    /**
     * 转换成圆角
     *
     * @param bmp
     * @param roundPx
     * @return
     */
    public static Bitmap convertToRoundedCorner(Bitmap bmp, float roundPx) {
        Bitmap newBmp = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(),
            Config.ARGB_8888);
        // 得到画布
        Canvas canvas = new Canvas(newBmp);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // 第二个和第三个参数一样则画的是正圆的一角，否则是椭圆的一角
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);

        return newBmp;
    }

    /** 水平方向模糊度 */
    private static float hRadius = 2;
    /** 竖直方向模糊度 */
    private static float vRadius = 2;
    /** 模糊迭代度 */
    private static int iterations = 7;
    /**
     * 高斯模糊
     */
    public static Bitmap BoxBlurFilter(Bitmap bmp) {
        long start = System.currentTimeMillis();

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        int[] inPixels = new int[width * height];
        int[] outPixels = new int[width * height];
        Bitmap bitmap = Bitmap.createBitmap(width, height,Bitmap.Config.ARGB_8888);
        bmp.getPixels(inPixels, 0, width, 0, 0, width, height);
        for (int i = 0; i < iterations; i++) {
            blur(inPixels, outPixels, width, height, hRadius);
            blur(outPixels, inPixels, height, width, vRadius);
        }
        blurFractional(inPixels, outPixels, width, height, hRadius);
        blurFractional(outPixels, inPixels, height, width, vRadius);
        bitmap.setPixels(inPixels, 0, width, 0, 0, width, height);

        long end = System.currentTimeMillis();

        return bitmap;
    }

    public static void blur(int[] in, int[] out, int width, int height,
        float radius) {
        int widthMinus1 = width - 1;
        int r = (int) radius;
        int tableSize = 2 * r + 1;
        int divide[] = new int[256 * tableSize];

        for (int i = 0; i < 256 * tableSize; i++)
            divide[i] = i / tableSize;

        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;
            int ta = 0, tr = 0, tg = 0, tb = 0;

            for (int i = -r; i <= r; i++) {
                int rgb = in[inIndex + clamp(i, 0, width - 1)];
                ta += (rgb >> 24) & 0xff;
                tr += (rgb >> 16) & 0xff;
                tg += (rgb >> 8) & 0xff;
                tb += rgb & 0xff;
            }

            for (int x = 0; x < width; x++) {
                out[outIndex] = (divide[ta] << 24) | (divide[tr] << 16)
                    | (divide[tg] << 8) | divide[tb];

                int i1 = x + r + 1;
                if (i1 > widthMinus1)
                    i1 = widthMinus1;
                int i2 = x - r;
                if (i2 < 0)
                    i2 = 0;
                int rgb1 = in[inIndex + i1];
                int rgb2 = in[inIndex + i2];

                ta += ((rgb1 >> 24) & 0xff) - ((rgb2 >> 24) & 0xff);
                tr += ((rgb1 & 0xff0000) - (rgb2 & 0xff0000)) >> 16;
                tg += ((rgb1 & 0xff00) - (rgb2 & 0xff00)) >> 8;
                tb += (rgb1 & 0xff) - (rgb2 & 0xff);
                outIndex += height;
            }
            inIndex += width;
        }
    }

    private static void blurFractional(int[] in, int[] out, int width,
        int height, float radius) {
        radius -= (int) radius;
        float f = 1.0f / (1 + 2 * radius);
        int inIndex = 0;

        for (int y = 0; y < height; y++) {
            int outIndex = y;

            out[outIndex] = in[0];
            outIndex += height;
            for (int x = 1; x < width - 1; x++) {
                int i = inIndex + x;
                int rgb1 = in[i - 1];
                int rgb2 = in[i];
                int rgb3 = in[i + 1];

                int a1 = (rgb1 >> 24) & 0xff;
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = rgb1 & 0xff;
                int a2 = (rgb2 >> 24) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = rgb2 & 0xff;
                int a3 = (rgb3 >> 24) & 0xff;
                int r3 = (rgb3 >> 16) & 0xff;
                int g3 = (rgb3 >> 8) & 0xff;
                int b3 = rgb3 & 0xff;
                a1 = a2 + (int) ((a1 + a3) * radius);
                r1 = r2 + (int) ((r1 + r3) * radius);
                g1 = g2 + (int) ((g1 + g3) * radius);
                b1 = b2 + (int) ((b1 + b3) * radius);
                a1 *= f;
                r1 *= f;
                g1 *= f;
                b1 *= f;
                out[outIndex] = (a1 << 24) | (r1 << 16) | (g1 << 8) | b1;
                outIndex += height;
            }
            out[outIndex] = in[width - 1];
            inIndex += width;
        }
    }

    public static int clamp(int x, int a, int b) {
        return (x < a) ? a : (x > b) ? b : x;
    }

    /**
     * 圆形图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap getCircleBitmap(Bitmap bitmap){
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float roundPx;
        float left, top, right, bottom, dst_left, dst_top, dst_right, dst_bottom;
        if (width <= height) {
            roundPx = width / 2;
            top = 0;
            bottom = width;
            left = 0;
            right = width;
            height = width;
            dst_left = 0;
            dst_top = 0;
            dst_right = width;
            dst_bottom = width;
        } else {
            roundPx = height / 2;
            float clip = (width - height) / 2;
            left = clip;
            right = width - clip;
            top = 0;
            bottom = height;
            width = height;
            dst_left = 0;
            dst_top = 0;
            dst_right = height;
            dst_bottom = height;
        }

        Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect src = new Rect((int) left, (int) top, (int) right,
            (int) bottom);
        final Rect dst = new Rect((int) dst_left, (int) dst_top,
            (int) dst_right, (int) dst_bottom);
        final RectF rectF = new RectF(dst);

        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, src, dst, paint);
        return output;
    }

    /**
     * 获取圆角
     *
     * @param bitmap
     * @param pixels 角度
     * @return
     */
    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap, int pixels) {
        if (bitmap != null) {
            final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_8888);
            if (output != null) {
                final Canvas canvas = new Canvas(output);
                final int color = 0xff424242;
                final Paint paint = new Paint();
                final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
                final RectF rectF = new RectF(rect);
                final float roundPx = pixels;
                paint.setAntiAlias(true);
                canvas.drawARGB(0, 0, 0, 0);
                paint.setColor(color);
                canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
                paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
                canvas.drawBitmap(bitmap, rect, rect, paint);
                return output;
            }
        }

        return null;
    }

    /**
     *
     * 从Assets中读取图片
     * @param filepath 相对路径
     * @return Bitmap
     */
    public static Bitmap getImageFromAssetsFile(String filepath, Context context) {
        Bitmap image = null;
        InputStream is = null;
        AssetManager am = context.getResources().getAssets();
        try {
            is = am.open(filepath);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null)
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return image;
    }

    public static InputStream getImageFromAssetsFileInputStream(String filepath, Context context) {
        InputStream is = null;
        AssetManager am = context.getResources().getAssets();
        try {
            is = am.open(filepath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 按比例缩小图片（单位像素） lessen the bitmap
     *
     * @param resId
     * bitmap
     * @param destWidth
     * the dest bitmap width
     * @param destHeigth
     * @return new bitmap if successful ,oherwise null
     */
    public static Bitmap lessenBitmap(Context context, int resId, int destWidth, int destHeigth) {

        final BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        opt.inPurgeable = true;
        opt.inInputShareable = true;
        // 获取资源图片
        final InputStream is = context.getResources().openRawResource(resId);
        final Bitmap bitmap = BitmapFactory.decodeStream(is, null, opt);

        try {
            if (is != null) {
                is.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        final int w = bitmap.getWidth();// 源文件的大小
        final int h = bitmap.getHeight();
        float scaleWidth = ((float) destWidth) / w;// 宽度缩小比例
        float scaleHeight = ((float) destHeigth) / h;// 高度缩小比例
        final Matrix m = new Matrix();// 矩阵
        m.postScale(scaleWidth, scaleHeight);// 设置矩阵比例
        final Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, w, h, m, true);// 直接按照矩阵的比例把源文件画入进

        if (!bitmap.isRecycled()) {
            bitmap.recycle();
        }

        return resizedBitmap;
    }

    /**
     * 判断图片旋转情况
     *
     * @param path
     * @return
     */
    public static int readPictureDegree(String path) {
        int degree = 0;
        try {
            final ExifInterface exifInterface = new ExifInterface(path);
            final int orientation =
                exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /**
     * 旋转图片
     * @param path 图片路径
     * @param bitmap 原图
     * @return
     */
    public static Bitmap rotaingImageView(String path, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        final int angle = readPictureDegree(path);
        if (angle != 0) {
            matrix.postRotate(angle);
        }
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }

    /**
     * 图片旋转
     *
     * @param bm
     * 图片资源Bitmap
     * @param curDegrees
     * //当前旋转度数
     */
    public static Bitmap rotateBitmap(Bitmap bm, float curDegrees) {
        return rotateBitmap(bm, curDegrees, true);
    }

    public static Bitmap rotateBitmap(Bitmap bm, float curDegrees, boolean isRecycled) {
        if (bm == null) {
            return null;
        }
        final int bmpW = bm.getWidth();
        final int bmpH = bm.getHeight();
        // 注意这个Matirx是android.graphics底下的那个
        final Matrix mt = new Matrix();
        mt.reset();
        mt.setRotate(curDegrees);
        final Bitmap bitmap = Bitmap.createBitmap(bm, 0, 0, bmpW, bmpH, mt,
            true);

        if (isRecycled && !bm.isRecycled()) {
            bm.recycle();
        }
        return bitmap;
    }

    /**
     * 根据uri获取图片
     * @param context
     * @param uri
     * @return
     */
    public static Bitmap getBitmapFromUri(Context context, Uri uri) {
        try {
            // 读取uri所在的图片
            final Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                context.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    /**
     * 图片uri转path
     * //
     *
     * @param uri
     * @param activity
     * @return
     */
    public static String getPicPathFromUri(Uri uri, Activity activity) {
        String value = uri.getPath();

        try {
            if (value.startsWith("/external")) {
                String[] proj = {MediaStore.Images.Media.DATA};
                Cursor cursor = activity.managedQuery(uri, proj, null, null, null);
                // Cursor cursor = activity.getContentResolver().query(uri, proj, null, null, null);
                if (cursor != null && cursor.getCount() > 0) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    final String picUri = cursor.getString(column_index);
                    //4.0以上的版本会自动关闭 (4.0--14;; 4.0.3--15)
                    if(Integer.parseInt(Build.VERSION.SDK) < 14)
                    {
                        if (cursor != null) {
                            cursor.close();
                        }
                    }
                    return picUri;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        return value;
    }

    public static Bitmap getPathToBitmap(String filePath) {
        Bitmap bm = BitmapFactory.decodeFile(filePath);

        // 处理某些手机拍照角度旋转的问题
        final int degree = readPictureDegree(filePath);
        if (degree != 0) {// 旋转照片角度
            bm = rotateBitmap(bm, degree);
        }
        return bm;
    }

    /**
     * 根据路径获得突破并压缩返回bitmap用于显示
     *
     * @param filePath
     * @return
     */
    public static Bitmap getSmallBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, 480, 800);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;


        Bitmap bm = BitmapFactory.decodeFile(filePath, options);

        // 处理某些手机拍照角度旋转的问题
        final int degree = readPictureDegree(filePath);
        if (degree != 0) {// 旋转照片角度
            bm = rotateBitmap(bm, degree);
        }
        return bm;
    }

    public static int calculateInSampleSize(BitmapFactory.Options options,
        int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

    /**
     * 从文件中获取图片
     *
     * @param path
     * @return
     */
    public static byte[] decodeBitmap(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;// 设置成了true,不占用内存，只获取bitmap宽高
        BitmapFactory.decodeFile(path, opts);
        opts.inSampleSize = computeSampleSize(opts, -1, 1024 * 800);
        opts.inJustDecodeBounds = false;// 这里一定要将其设置回false，因为之前我们将其设置成了true
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        opts.inDither = false;
        opts.inPurgeable = true;
        opts.inTempStorage = new byte[16 * 1024];
        FileInputStream is = null;
        Bitmap bmp = null;
        ByteArrayOutputStream baos = null;
        try {
            is = new FileInputStream(path);
            bmp = BitmapFactory.decodeFileDescriptor(is.getFD(), null, opts);
            double scale = getScaling(opts.outWidth * opts.outHeight,
                1024 * 600);
            Bitmap bmp2 = Bitmap.createScaledBitmap(bmp,
                (int) (opts.outWidth * scale),
                (int) (opts.outHeight * scale), true);
            bmp.recycle();
            baos = new ByteArrayOutputStream();
            bmp2.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bmp2.recycle();
            return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.gc();
        }
        return null;
    }

    private static double getScaling(int src, int des) {
        /**
         * 48 目标尺寸÷原尺寸 sqrt开方，得出宽高百分比 49
         */
        double scale = Math.sqrt((double) des / (double) src);
        return scale;
    }

    /**
     * 使用该算法，就可动态计算出图片的inSampleSize。
     *
     * @param options
     * @param minSideLength
     * 调整后图片最小的宽或高值
     * @param maxNumOfPixels
     * 最大分辨率
     * @return
     */
    private static int computeSampleSize(BitmapFactory.Options options,
        int minSideLength, int maxNumOfPixels) {
        final int initialSize = computeInitialSampleSize(options, minSideLength,
            maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    /**
     * 长度单位转换
     * @param context
     * @param unit
     * @param value
     * @return
     */
    public static float applyDimension(Context context,int unit, float value){
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(unit, value, metrics);
    }
}
