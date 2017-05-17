package com.jingewenku.abrahamcaijin.commonutil;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.ByteArrayOutputStream;

/**
 * 主要功能:JNI图片压缩工具类
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:30
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AppNativeImageMgr {

//ThumbnailUtils系统工具类的使用
/*创建一张视频的缩略图。如果视频已损坏或者格式不支持可能返回null。
filePath：视频文件路径
kind：文件种类，可以是 MINI_KIND 或 MICRO_KIND

Bitmap createVideoThumbnail(String filePath, int kind)
创建所需尺寸居中缩放的位图。
source： 需要被创造缩略图的源位图对象
width：　生成目标的宽度
height： 生成目标的高度
options：在缩略图抽取时提供的选项

Bitmap extractThumbnail(Bitmap source, int width, int height, int options)
创建所需尺寸居中缩放的位图。
source： 需要被创造缩略图的源位图对象
width：　生成目标的宽度
height： 生成目标的高度

Bitmap extractThumbnail(Bitmap source, int width, int height)
最后当然要奉上源码了，源码中封装了参考网上的拍照和选取图片工具类，有问题可以指出，共同进步!

原文链接：http://www.jianshu.com/p/e9e1db845c21
著作权归作者所有，转载请联系作者获得授权，并标注“简书作者”。*/

    private static int DEFAULT_QUALITY = 95;

    /**
     * @Description: JNI基本压缩
     * @param bit
     *            bitmap对象
     * @param fileName
     *            指定保存目录名
     * @param optimize
     *            是否采用哈弗曼表数据计算 品质相差5-10倍
     * @author XiaoSai
     * @date 2016年3月23日 下午6:32:49
     * @version V1.0.0
     */
    public static void compressBitmap(Bitmap bit, String fileName, boolean optimize) {
        saveBitmap(bit, DEFAULT_QUALITY, fileName, optimize);
    }

    /**
     * @Description: 通过JNI图片压缩把Bitmap保存到指定目录
     * @param image
     *            bitmap对象
     * @param filePath
     *            要保存的指定目录
     * @author XiaoSai
     * @date 2016年3月23日 下午6:28:15
     * @version V1.0.0
     */
    public static void compressBitmap(Bitmap image, String filePath) {
        // 最大图片大小 100KB
        int maxSize = 100;
        // 获取尺寸压缩倍数
        int ratio = AppNativeImageMgr.getRatioSize(image.getWidth(), image.getHeight());
        // 压缩Bitmap到对应尺寸
        Bitmap result = Bitmap.createBitmap(image.getWidth() / ratio, image.getHeight() / ratio, Config.ARGB_8888);
        Canvas canvas = new Canvas(result);
        Rect rect = new Rect(0, 0, image.getWidth() / ratio, image.getHeight() / ratio);
        canvas.drawBitmap(image, null, rect, null);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        result.compress(Bitmap.CompressFormat.JPEG, options, baos);
        // 循环判断如果压缩后图片是否大于100kb,大于继续压缩
        while (baos.toByteArray().length / 1024 > maxSize) {
            // 重置baos即清空baos
            baos.reset();
            // 每次都减少10
            options -= 10;
            // 这里压缩options%，把压缩后的数据存放到baos中
            result.compress(Bitmap.CompressFormat.JPEG, options, baos);
        }
        // JNI调用保存图片到SD卡 这个关键
        AppNativeImageMgr.saveBitmap(result, options, filePath, true);
        // 释放Bitmap
        if (result != null && !result.isRecycled()) {
            result.recycle();
            result = null;
        }
    }

    /**
     * 计算缩放比
     *
     * @Description:函数描述
     * @param bitWidth
     *            当前图片宽度
     * @param bitHeight
     *            当前图片高度
     * @return
     * @author XiaoSai
     * @date 2016年3月21日 下午3:03:38
     * @version V1.0.0
     */
    public static int getRatioSize(int bitWidth, int bitHeight) {
        // 图片最大分辨率
        int imageHeight = 1280;
        int imageWidth = 960;
        // 缩放比
        int ratio = 1;
        // 缩放比,由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        if (bitWidth > bitHeight && bitWidth > imageWidth) {
            // 如果图片宽度比高度大,以宽度为基准
            ratio = bitWidth / imageWidth;
        } else if (bitWidth < bitHeight && bitHeight > imageHeight) {
            // 如果图片高度比宽度大，以高度为基准
            ratio = bitHeight / imageHeight;
        }
        // 最小比率为1
        if (ratio <= 0)
            ratio = 1;
        return ratio;
    }

    /**
     * 调用native方法
     * @Description:函数描述
     * @param bit
     * @param quality
     * @param fileName
     * @param optimize
     * @author XiaoSai
     * @date 2016年3月23日 下午6:36:46
     * @version V1.0.0
     */
    private static void saveBitmap(Bitmap bit, int quality, String fileName, boolean optimize) {
        compressBitmap(bit, bit.getWidth(), bit.getHeight(), quality, fileName.getBytes(), optimize);
    }

    /**
     * 调用底层 bitherlibjni.c中的方法
     * @Description:函数描述
     * @param bit
     * @param w
     * @param h
     * @param quality
     * @param fileNameBytes
     * @param optimize
     * @return
     * @author XiaoSai
     * @date 2016年3月23日 下午6:35:53
     * @version V1.0.0
     */
    private static native String compressBitmap(Bitmap bit, int w, int h, int quality, byte[] fileNameBytes,
        boolean optimize);
    /**
     * 加载lib下两个so文件
     */
    static {
        System.loadLibrary("jpegbither");
        System.loadLibrary("bitherjni");
    }
}
