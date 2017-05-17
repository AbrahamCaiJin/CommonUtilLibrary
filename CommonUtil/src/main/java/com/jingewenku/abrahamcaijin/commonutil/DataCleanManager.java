package com.jingewenku.abrahamcaijin.commonutil;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * @Description:主要功能:app缓存清理文件清理管理器
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月15日 11:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class DataCleanManager {/**
 * 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
 *
 * @param context
 * */
public static void cleanInternalCache(Context context) {
    deleteFilesByDirectory(context.getCacheDir());
//    ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
}

    /**
     * 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     *
     * @param context
     * */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
            + context.getPackageName() + "/databases"));
    }

    /**
     * * 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     *
     * @param context
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File("/data/data/"
            + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * * 按名字清除本应用数据库 *
     * * @param context *
     *
     * @param dbName
     * */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * * 清除/data/data/com.xxx.xxx/files下的内容 *
     *
     * @param context
     *
     **/
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * * 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     * * @param
     * context
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
            Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * * 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     * * @param filePath
     * */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * * 清除本应用所有的数据 *
     * * @param context *
     * *@param filepath
     * */
    public static void cleanApplicationData(Context context, String... filepath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String filePath : filepath) {
            cleanCustomCache(filePath);
        }
    }
    /**
     * * 清除本应用数据 *
     * * @param context *
     * *@param filepath
     * */
    public static void cleanApplicationData(Context context) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
//        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
    }

    /**
     * * 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理 *
     * * @param directory
     * */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            File files[]=directory.listFiles();
            if(files!=null){
                int length=files.length;
                if(length>0){
                    for (File item : files) {
                        item.delete();
                    }
                }

            }

        }
    }
    /**
     * 获取总缓存大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(
            Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }


    // 获取文件
    //Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
    //Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        if(file!=null){
            try {
                File[] fileList = file.listFiles();
                if(fileList!=null){
                    int length=fileList.length;
                    if(length>0){
                        for (int i = 0; i < length; i++) {
                            // 如果下面还有文件
                            if (fileList[i].isDirectory()) {
                                size = size + getFolderSize(fileList[i]);
                            } else {
                                size = size + fileList[i].length();
                            }
                        }
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return size;
    }
    /**
     * 格式化单位
     *
     * @param size
     * @return
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                          .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                          .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                          .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
            + "TB";
    }

}
