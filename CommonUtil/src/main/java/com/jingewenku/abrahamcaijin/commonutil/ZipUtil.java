package com.jingewenku.abrahamcaijin.commonutil;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
/**
 * @Description:主要功能: 实现的Zip工具
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月24日 18:21
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class ZipUtil{
    private static final int BUFF_SIZE = 1024 * 1024; // 1M Byte
    private static boolean stopZipFlag;

    public static boolean isStopZipFlag() {
        return stopZipFlag;
    }

    public static void setStopZipFlag(boolean stopZipFlag) {
        ZipUtil.stopZipFlag = stopZipFlag;
    }

    /**
     * 批量压缩文件（夹）
     *
     * @param resFileList 要压缩的文件（夹）列表
     * @param zipFile 生成的压缩文件
     * @param zipListener     zipListener
     */
    public static void zipFiles(Collection<File> resFileList, File zipFile,ZipListener zipListener)  {

        ZipOutputStream zipout = null;
        try {
            zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(
                zipFile), BUFF_SIZE));
            for (File resFile : resFileList) {
                if(stopZipFlag){
                    break;
                }
                zipFile(resFile, zipout, "",zipListener);
            }
            zipout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 批量压缩文件（夹）
     *
     * @param resFileList 要压缩的文件（夹）列表
     * @param zipFile 生成的压缩文件
     * @param comment 压缩文件的注释
     * @param zipListener    zipListener
     */
    public static void zipFiles(Collection<File> resFileList, File zipFile, String comment,ZipListener zipListener)
    {
        ZipOutputStream zipout = null;
        try {
            zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), BUFF_SIZE));
            for (File resFile : resFileList) {
                zipFile(resFile, zipout, "",zipListener);
            }
            zipout.setComment(comment);
            zipout.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解压缩一个文件
     *
     * @param zipFile 压缩文件
     * @param folderPath 解压缩的目标目录
     */
    public static void upZipFile(File zipFile, String folderPath) {
        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdirs();
        }
        ZipFile zf = null;
        try {
            zf = new ZipFile(zipFile);
            for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
                ZipEntry entry = ((ZipEntry)entries.nextElement());
                InputStream in = zf.getInputStream(entry);
                String str = folderPath + File.separator + entry.getName();
                str = new String(str.getBytes("8859_1"), "GB2312");
                File desFile = new File(str);
                if (!desFile.exists()) {
                    File fileParentDir = desFile.getParentFile();
                    if (!fileParentDir.exists()) {
                        fileParentDir.mkdirs();
                    }
                    desFile.createNewFile();
                }
                OutputStream out = new FileOutputStream(desFile);
                byte buffer[] = new byte[BUFF_SIZE];
                int realLength;
                while ((realLength = in.read(buffer)) > 0) {
                    out.write(buffer, 0, realLength);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 解压文件名包含传入文字的文件
     *
     * @param zipFile 压缩文件
     * @param folderPath 目标文件夹
     * @param nameContains 传入的文件匹配名
     * @return   返回的集合
     */
    public static ArrayList<File> upZipSelectedFile(File zipFile, String folderPath,
        String nameContains) {

        ArrayList<File> fileList = new ArrayList<File>();

        File desDir = new File(folderPath);
        if (!desDir.exists()) {
            desDir.mkdir();
        }

        ZipFile zf = null;
        try {
            zf = new ZipFile(zipFile);
            for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
                ZipEntry entry = ((ZipEntry)entries.nextElement());
                if (entry.getName().contains(nameContains)) {
                    InputStream in = zf.getInputStream(entry);
                    String str = folderPath + File.separator + entry.getName();
                    str = new String(str.getBytes("8859_1"), "GB2312");
                    // str.getBytes("GB2312"),"8859_1" 输出
                    // str.getBytes("8859_1"),"GB2312" 输入
                    File desFile = new File(str);
                    if (!desFile.exists()) {
                        File fileParentDir = desFile.getParentFile();
                        if (!fileParentDir.exists()) {
                            fileParentDir.mkdirs();
                        }
                        desFile.createNewFile();
                    }
                    OutputStream out = new FileOutputStream(desFile);
                    byte buffer[] = new byte[BUFF_SIZE];
                    int realLength;
                    while ((realLength = in.read(buffer)) > 0) {
                        out.write(buffer, 0, realLength);
                    }
                    in.close();
                    out.close();
                    fileList.add(desFile);
                }
            }
            return fileList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得压缩文件内文件列表
     *
     * @param zipFile 压缩文件
     * @return 压缩文件内文件名称
     */
    public static ArrayList<String> getEntriesNames(File zipFile) {

        ArrayList<String> entryNames = new ArrayList<String>();
        Enumeration<?> entries = null;
        try {
            entries = getEntriesEnumeration(zipFile);
            while (entries.hasMoreElements()) {
                ZipEntry entry = ((ZipEntry)entries.nextElement());
                entryNames.add(new String(getEntryName(entry).getBytes("GB2312"), "8859_1"));
            }
            return entryNames;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得压缩文件内压缩文件对象以取得其属性
     *
     * @param zipFile 压缩文件
     * @return 返回一个压缩文件列表
     */
    public static Enumeration<?> getEntriesEnumeration(File zipFile) {
        ZipFile zf = null;
        try {
            zf = new ZipFile(zipFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zf.entries();

    }

    /**
     * 取得压缩文件对象的注释
     *
     * @param entry 压缩文件对象
     * @return 压缩文件对象的注释
     */
    public static String getEntryComment(ZipEntry entry)  {
        try {
            return new String(entry.getComment().getBytes("GB2312"), "8859_1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 取得压缩文件对象的名称
     *
     * @param entry 压缩文件对象
     * @return 压缩文件对象的名称
     */
    public static String getEntryName(ZipEntry entry)  {
        try {
            return new String(entry.getName().getBytes("GB2312"), "8859_1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 压缩文件
     *
     * @param resFile 需要压缩的文件（夹）
     * @param zipout 压缩的目的文件
     * @param rootpath 压缩的文件路径
     */
    private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath,ZipListener zipListener)
    {
        try {
            rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator)
                + resFile.getName();
            rootpath = new String(rootpath.getBytes("8859_1"), "GB2312");
            if (resFile.isDirectory()) {
                File[] fileList = resFile.listFiles();
                int length=fileList.length;
                // Log.e("zipprogress", (int)((1 / (float) (length+1))*100)+"%");
                zipListener.zipProgress((int)((1 / (float) (length+1))*100));
                for (int i=0;i<length;i++) {
                    if(stopZipFlag){
                        break;
                    }
                    File file=fileList[i];
                    zipFile(file, zipout, rootpath,zipListener);
                    // Log.e("zipprogress", (int)(((i+2) / (float) (length+1))*100)+"%");
                    zipListener.zipProgress((int)(((i+2) / (float) (length+1))*100));
                }
            } else {
                byte buffer[] = new byte[BUFF_SIZE];
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile),
                    BUFF_SIZE);
                zipout.putNextEntry(new ZipEntry(rootpath));
                int realLength;
                while ((realLength = in.read(buffer)) != -1) {
                    if(stopZipFlag){
                        break;
                    }
                    zipout.write(buffer, 0, realLength);
                }
                in.close();
                zipout.flush();
                zipout.closeEntry();
            }
        }catch (Exception e){

        }

    }
    public interface ZipListener{
        void zipProgress(int zipProgress);
    }
}