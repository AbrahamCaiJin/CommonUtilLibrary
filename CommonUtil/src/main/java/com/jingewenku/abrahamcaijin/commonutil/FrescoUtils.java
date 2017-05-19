package com.jingewenku.abrahamcaijin.commonutil;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.common.util.ByteConstants;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest.RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * @Description:主要功能:Fresco图片加载工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月19日 14:38
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class FrescoUtils {

    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();//分配的可用内存
    public static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;//使用的缓存数量

    public static final int MAX_SMALL_DISK_VERYLOW_CACHE_SIZE = 5 * ByteConstants.MB;//小图极低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_SMALL_DISK_LOW_CACHE_SIZE 	= 10 * ByteConstants.MB;//小图低磁盘空间缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）
    public static final int MAX_SMALL_DISK_CACHE_SIZE 		= 20 * ByteConstants.MB;//小图磁盘缓存的最大值（特性：可将大量的小图放到额外放在另一个磁盘空间防止大图占用磁盘空间而删除了大量的小图）

    public static final int MAX_DISK_CACHE_VERYLOW_SIZE 		= 10 * ByteConstants.MB;//默认图极低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_LOW_SIZE 			= 30 * ByteConstants.MB;//默认图低磁盘空间缓存的最大值
    public static final int MAX_DISK_CACHE_SIZE 				= 50 * ByteConstants.MB;//默认图磁盘缓存的最大值

    private static final String IMAGE_PIPELINE_SMALL_CACHE_DIR = "imagepipeline_cache";//小图所放路径的文件夹名
    private static final String IMAGE_PIPELINE_CACHE_DIR 		 = "imagepipeline_cache";//默认图所放路径的文件夹名

    private static ImagePipelineConfig sImagePipelineConfig;

    private FrescoUtils(){

    }

    /**
     *  初始化配置，单例
     *  FrescoUtils.initConfig(context);
     *
     *  初始化配置的两种方式：
     * 在 Application 初始化时，在应用调用 setContentView() 之前，进行初始化：
     * Fresco.initialize(context);
     *
     * @param context
     * @return
     */
    public static void initConfig(Context context) {
        ImagePipelineConfig initImagePipelineConfig = initImagePipelineConfig(context);
        Fresco.initialize(context, initImagePipelineConfig);
    }

    public static ImagePipelineConfig initImagePipelineConfig(Context context) {
        if (sImagePipelineConfig == null) {
            sImagePipelineConfig = configureCaches(context);
        }
        return sImagePipelineConfig;
    }


    // 默认加载图片和失败图片
    public static Drawable sPlaceholderDrawable;
    public static Drawable sErrorDrawable;

    public static void init(final Resources resources) {
        if (sPlaceholderDrawable == null) {
            sPlaceholderDrawable = resources.getDrawable(R.color.transparency);
        }
        if (sErrorDrawable == null) {
            sErrorDrawable = resources.getDrawable(R.color.gray);
        }
    }

    /**
     * 初始化配置
     * @param context
     * @return
     */
    private static ImagePipelineConfig configureCaches(Context context) {
        // 内存配置
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
            FrescoUtils.MAX_MEMORY_CACHE_SIZE, // 内存缓存中总图片的最大大小,以字节为单位。
            Integer.MAX_VALUE, // 内存缓存中图片的最大数量。
            FrescoUtils.MAX_MEMORY_CACHE_SIZE, // 内存缓存中准备清除但尚未被删除的总图片的最大大小,以字节为单位。
            Integer.MAX_VALUE, // 内存缓存中准备清除的总图片的最大数量。
            Integer.MAX_VALUE); // 内存缓存中单个图片的最大大小。

        // 修改内存图片缓存数量，空间策略（这个方式有点恶心）
        Supplier<MemoryCacheParams> mSupplierMemoryCacheParams = new Supplier<MemoryCacheParams>() {
            @Override
            public MemoryCacheParams get() {
                return bitmapCacheParams;
            }
        };

        // 小图片的磁盘配置
        DiskCacheConfig diskSmallCacheConfig = DiskCacheConfig
            .newBuilder(context)
            .setBaseDirectoryPath(context.getApplicationContext().getCacheDir())// 缓存图片基路径
            .setBaseDirectoryName(IMAGE_PIPELINE_SMALL_CACHE_DIR)// 文件夹名
            // .setCacheErrorLogger(cacheErrorLogger)//日志记录器用于日志错误的缓存。
            // .setCacheEventListener(cacheEventListener)//缓存事件侦听器。
            // .setDiskTrimmableRegistry(diskTrimmableRegistry)//类将包含一个注册表的缓存减少磁盘空间的环境。
            .setMaxCacheSize(FrescoUtils.MAX_DISK_CACHE_SIZE)// 默认缓存的最大大小。
            .setMaxCacheSizeOnLowDiskSpace(MAX_SMALL_DISK_LOW_CACHE_SIZE)// 缓存的最大大小,使用设备时低磁盘空间。
            .setMaxCacheSizeOnVeryLowDiskSpace(MAX_SMALL_DISK_VERYLOW_CACHE_SIZE)// 缓存的最大大小,当设备极低磁盘空间
            // .setVersion(version)
            .build();

        // 默认图片的磁盘配置
        DiskCacheConfig diskCacheConfig = DiskCacheConfig
            .newBuilder(context)
            .setBaseDirectoryPath(Environment.getExternalStorageDirectory().getAbsoluteFile())// 缓存图片基路径
            .setBaseDirectoryName(IMAGE_PIPELINE_CACHE_DIR)// 文件夹名
            // .setCacheErrorLogger(cacheErrorLogger)//日志记录器用于日志错误的缓存。
            // .setCacheEventListener(cacheEventListener)//缓存事件侦听器。
            // .setDiskTrimmableRegistry(diskTrimmableRegistry)//类将包含一个注册表的缓存减少磁盘空间的环境。
            .setMaxCacheSize(FrescoUtils.MAX_DISK_CACHE_SIZE)// 默认缓存的最大大小。
            .setMaxCacheSizeOnLowDiskSpace(MAX_DISK_CACHE_LOW_SIZE)// 缓存的最大大小,使用设备时低磁盘空间。
            .setMaxCacheSizeOnVeryLowDiskSpace(MAX_DISK_CACHE_VERYLOW_SIZE)// 缓存的最大大小,当设备极低磁盘空间
            // .setVersion(version)
            .build();

        // 缓存图片配置
        ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig
            .newBuilder(context)
            // .setAnimatedImageFactory(AnimatedImageFactory
            // animatedImageFactory)//图片加载动画
            .setBitmapMemoryCacheParamsSupplier(mSupplierMemoryCacheParams)// 内存缓存配置（一级缓存，已解码的图片）
            // .setCacheKeyFactory(cacheKeyFactory)//缓存Key工厂
            // .setEncodedMemoryCacheParamsSupplier(encodedCacheParamsSupplier)//内存缓存和未解码的内存缓存的配置（二级缓存）
            // .setExecutorSupplier(executorSupplier)//线程池配置
            // .setImageCacheStatsTracker(imageCacheStatsTracker)//统计缓存的命中率
            // .setImageDecoder(ImageDecoder imageDecoder) //图片解码器配置
            // .setIsPrefetchEnabledSupplier(Supplier<Boolean>
            // isPrefetchEnabledSupplier)//图片预览（缩略图，预加载图等）预加载到文件缓存
            .setMainDiskCacheConfig(diskCacheConfig)// 磁盘缓存配置（总，三级缓存）
            // .setMemoryTrimmableRegistry(memoryTrimmableRegistry)
            // //内存用量的缩减,有时我们可能会想缩小内存用量。比如应用中有其他数据需要占用内存，不得不把图片缓存清除或者减小
            // 或者我们想检查看看手机是否已经内存不够了。
            // .setNetworkFetchProducer(networkFetchProducer)//自定的网络层配置：如OkHttp，Volley
            // .setPoolFactory(poolFactory)//线程池工厂配置
            // .setProgressiveJpegConfig(progressiveJpegConfig)//渐进式JPEG图
            // .setRequestListeners(requestListeners)//图片请求监听
            // .setResizeAndRotateEnabledForNetwork(boolean
            // resizeAndRotateEnabledForNetwork)//调整和旋转是否支持网络图片
            .setSmallImageDiskCacheConfig(diskSmallCacheConfig)// 磁盘缓存配置（小图片，可选～三级缓存的小图优化缓存）
            ;
        return configBuilder.build();
    }


    /**
     * 在 Application 初始化时，在应用调用 setContentView() 之前，进行初始化：
     * Fresco.initialize(context);
     *
     * 在xml布局文件中, 加入命名空间：
     *
     * !-- 其他元素 -->
     LinearLayout
     xmlns:android="http://schemas.android.com/apk/res/android"
     xmlns:fresco="http://schemas.android.com/apk/res-auto">

     * 	加入SimpleDraweeView:
     <com.facebook.drawee.view.SimpleDraweeView
     android:id="@+id/my_image_view"
     android:layout_width="20dp"
     android:layout_height="20dp"
     fresco:placeholderImage="@drawable/my_drawable"
     />
     *
     SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
     *
     *
     <com.facebook.drawee.view.SimpleDraweeView
     android:id="@+id/my_image_view"
     android:layout_width="20dp"   // 不支持wrap_content 如果要设置宽高比, 需要在Java代码中指定setAspectRatio(1.33f);
     android:layout_height="20dp"    // 不支持wrap_content
     fresco:fadeDuration="300"

     fresco:actualImageScaleType="focusCrop"// 设置图片缩放. 通常使用focusCrop,该属性值会通过算法把人头像放在中间
     fresco:placeholderImage="@color/wait_color"// 下载成功之前显示的图片

     fresco:placeholderImageScaleType="fitCenter"
     fresco:failureImage="@drawable/error"// 加载失败的时候显示的图片

     fresco:failureImageScaleType="centerInside"
     fresco:retryImage="@drawable/retrying"// 加载失败,提示用户点击重新加载的图片(会覆盖failureImage的图片)

     fresco:retryImageScaleType="centerCrop"
     fresco:progressBarImage="@drawable/progress_bar"// 提示用户正在加载,和加载进度无关

     fresco:progressBarImageScaleType="centerInside"
     fresco:progressBarAutoRotateInterval="1000"
     fresco:backgroundImage="@color/blue"
     fresco:overlayImage="@drawable/watermark"
     fresco:pressedStateOverlayImage="@color/red"

     fresco:roundAsCircle="false"// 是不是设置圆圈

     fresco:roundedCornerRadius="1dp"// 圆角角度,180的时候会变成圆形图片

     fresco:roundTopLeft="true"
     fresco:roundTopRight="false"
     fresco:roundBottomLeft="false"
     fresco:roundBottomRight="true"
     fresco:roundWithOverlayColor="@color/corner_color"
     fresco:roundingBorderWidth="2dp"
     fresco:roundingBorderColor="@color/border_color"
     />
     *
     *
     * 特别注意：Fresco 不支持 相对路径的URI. 所有的URI都必须是绝对路径，并且带上该URI的scheme。
     如下：
     类型	Scheme	示例
     远程图片	http://, https://	HttpURLConnection 或者参考 使用其他网络加载方案
     本地文件	file://	FileInputStream
     Content provider	content://	ContentResolver
     asset目录下的资源	asset://	AssetManager
     res目录下的资源	res://	Resources.openRawResource
     res 示例:
     Uri uri = Uri.parse("res://包名(实际可以是任何字符串甚至留空)/" + R.drawable.ic_launcher);
     *
     */



    /**
     * 开始加载图片
     * @param draweeView
     * @param uriString
     */
    public static void setImageURI(SimpleDraweeView draweeView , String uriString) {
        Uri uri = Uri.parse(uriString);
        draweeView.setImageURI(uri);
    }

    /**
     * 自定义图片尺寸
     * @param draweeView 显示的控件
     * @param uriString 地址
     * @param width 50
     * @param height 50
     */
    public static void changeImgSize(SimpleDraweeView draweeView , String uriString , int width , int height) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uriString))
                                                  .setResizeOptions(new ResizeOptions(width, height))
                                                  .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setImageRequest(request)
                                            .setAutoPlayAnimations(true)// other setters
                                            .build();
        draweeView.setController(controller);
    }

    /**
     * 加载gif图
     * @param draweeView
     * @param uriString
     */
    public static void loadGif(SimpleDraweeView draweeView , String uriString) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(Uri.parse(uriString)).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setImageRequest(request)
                                            .setAutoPlayAnimations(true)
                                            .build();
        draweeView.setController(controller);
    }

    /**
     * 多图请求需 自定义ImageRequest(图片预览)
     * @param draweeView
     * @param lowResUri
     * @param highResUri
     */
    public static void moreImgRequst(SimpleDraweeView draweeView , String lowResUri , String highResUri ) {
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setLowResImageRequest(ImageRequest.fromUri(Uri.parse(lowResUri)))//低分辨率的缩略图
                                            .setImageRequest(ImageRequest.fromUri(Uri.parse(highResUri)))//高分辨率的图
                                            .setOldController(draweeView.getController())
                                            .build();
        draweeView.setController(controller);

    }

    /**
     * 缩略图预览(仅支持本地图片,并且是JPEG图片格式)
     * @param draweeView
     * @param uri
     */
    public static void localImg(SimpleDraweeView draweeView , Uri uri ) {
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                                                  .setLocalThumbnailPreviewsEnabled(true)
                                                  .build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setImageRequest(request)
                                            .setOldController(draweeView.getController())
                                            .build();
        draweeView.setController(controller);
    }

    /**
     * 请求图片
     * @param uri - 唯一的必选的成员. 参考 支持的URIs
     * @param width
     * @param height
     * autoRotateEnabled - 是否支持自动旋转.
     * progressiveEnabled - 是否支持渐进式加载.
     * postprocessor - 后处理器(postprocess).
     * resizeOptions - 图片缩放选项，用前请先阅读缩放和旋转
     */
    public static void loadImage(SimpleDraweeView draweeView , String uri , int width , int height) {
        ImageRequest request = ImageRequestBuilder
            .newBuilderWithSource(Uri.parse(uri))
            .setAutoRotateEnabled(true)// 是否支持自动旋转.
            .setImageDecodeOptions(getImageDecodeOptions())//  图片解码库
            //.setImageType(ImageType.SMALL)//图片类型，设置后可调整图片放入小图磁盘空间还是默认图片磁盘空间
            //.setLocalThumbnailPreviewsEnabled(true)//缩略图预览，影响图片显示速度（轻微）
            .setLowestPermittedRequestLevel(RequestLevel.FULL_FETCH)//请求经过缓存级别  BITMAP_MEMORY_CACHE，ENCODED_MEMORY_CACHE，DISK_CACHE，FULL_FETCH
            .setProgressiveRenderingEnabled(false)//渐进加载，主要用于渐进式的JPEG图，影响图片显示速度（普通）
            .setResizeOptions(new ResizeOptions(width, height))
            .build();

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setImageRequest(request)
                                            .setOldController(draweeView.getController())
                                            .build();
        draweeView.setController(controller);
    }

    /**
     * 图片解码
     * @return
     */
    public static ImageDecodeOptions getImageDecodeOptions() {
        ImageDecodeOptions decodeOptions = ImageDecodeOptions.newBuilder()
                                                             // .setBackgroundColor(Color.TRANSPARENT)//图片的背景颜色
                                                             // .setDecodeAllFrames(decodeAllFrames)//解码所有帧
                                                             // .setDecodePreviewFrame(decodePreviewFrame)//解码预览框
                                                             // .setForceOldAnimationCode(forceOldAnimationCode)//使用以前动画
                                                             // .setFrom(options)//使用已经存在的图像解码
                                                             // .setMinDecodeIntervalMs(intervalMs)//最小解码间隔（分位单位）
                                                             .setUseLastFrameForPreview(true)// 使用最后一帧进行预览
                                                             .build();
        return decodeOptions;
    }
}
