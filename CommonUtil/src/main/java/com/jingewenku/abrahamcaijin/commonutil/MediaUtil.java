package com.jingewenku.abrahamcaijin.commonutil;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

/**
 * @Description:主要功能:系统媒体管理工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年06月01日 11:11
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class MediaUtil {

    private static final String TAG = MediaUtil.class.getSimpleName();

    public static final int PHOTO_REQUEST_GALLERY = 1000;
    public static final int PHOTO_REQUEST_CAMERA = 1001;
    public static final int PHOTO_REQUEST_CUT = 1002;

    /**
     * 进入系统拍照
     * @param activity
     * @param outputUri 照片输出路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image.jpg"))
     */
    public static void startActivityForCamera(Activity activity, int requestCode, Uri outputUri) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        // 制定图片保存路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 进入系统拍照 (输出为Bitmap)<br>
     *
     * 获得输出
     * 在 @<code>onActivityResult</code>中<br>
     * 通过@<code>Bitmap bitmap = (Bitmap)intent.data.getExtras().get("data")</code>获取<br>
     *
     * Tips: 返回的Bitmap并非原图的Bitmap而是经过压缩的Bitmap
     * @param activity
     *
     */
    public static void startActivityForCamera(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 进入系统图库<br>
     * 获得输出<br>
     * 在 @<code>onActivityResult</code>中通过@<code>Uri uri = intent.getData()</code>获取<br>
     * Uri返回路径格式为 content://media/external/images/media/32073<br>
     * 需要经过转换才能获得绝对路径
     * @param activity
     */
    public static void startActivityForGallery(Activity activity, int requestCode) {
        // 弹出系统图库
        Intent i = new Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        activity.startActivityForResult(i, requestCode);
    }

    /**
     * 进入系统裁剪
     * @param inputUri 需裁剪的图片路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image.jpg")
     * @param outputUri 裁剪后图片路径 Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/image_cut.jpg")
     * @param width 裁剪后宽度(px)
     * @param height 裁剪后高度(px)
     */
    public static void startActivityForImageCut(Activity activity, int requestCode,
        Uri inputUri, Uri outputUri,
        int width, int height) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(inputUri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true); // 去黑边
        intent.putExtra("scaleUpIfNeeded", true); // 去黑边
        // aspectX aspectY 裁剪框宽高比例
        intent.putExtra("aspectX", width); // 输出是X方向的比例
        intent.putExtra("aspectY", height);
        // outputX outputY 输出图片宽高，切忌不要再改动下列数字，会卡死
        intent.putExtra("outputX", width); // 输出X方向的像素
        intent.putExtra("outputY", height);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, outputUri);
        intent.putExtra("return-data", false); // 设置为不返回数据
        activity.startActivityForResult(intent, requestCode);
    }
}