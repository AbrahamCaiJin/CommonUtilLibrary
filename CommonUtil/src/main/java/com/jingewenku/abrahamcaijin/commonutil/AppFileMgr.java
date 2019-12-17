package com.jingewenku.abrahamcaijin.commonutil;


import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;


/**
 * 主要功能：App应用文件管理工具类
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月04日 14:13
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressWarnings("resource")
public class AppFileMgr {

	
	//得到当前外部存储设备的目录
	public static final String CACHE_DIRECTORY =  Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;  
	
	//存储图片目录路径
	public static final String CACHE_IMAGE_PATH = "JinGeWenKu/cache/others/images/";
	
	//存储主页播放图片目录路径
	public static final String CACHE_MAIN_GALLERY_IMAGE_PATH = "JinGeWenKu/cache/gallery/images/";
	
	//存放用户选择头像目录路径
	public static final String CACHE_CHOOSE_HEAD_PATH = "JinGeWenKu/cache/head/images/";
	
	//菜单操作按钮缓存目录路径
	public static final String CACHE_APP_ICON_IMAGES_PATH = "JinGeWenKu/cache/app/icon/images/";
	
	//存储浏览图片目录
	public static final String CACH_BROWSE_PATH = "JinGeWenKu/cache/browse/images/";
	
	//缓存目录位置
	public static final String CACHE_DIR ="";
	
	//是否剪切
	public static boolean CUT_FALG = false;
	
	//是否复制
	public static boolean COPY_FALG = false;
	
	//是否删除
	public static boolean DELETE_FALG = false;
	
	public static final int ERROR = -1;  
	
	
	/**
	 * 删除方法, 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
	 * @param directory
	 * @return void   
	 */
    public static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File file : directory.listFiles()) {
            	if(file.isDirectory())  
            		deleteFilesByDirectory(file);
            		file.delete();
            }
            AppLogMessageMgr.i("AppFileMgr-->>deleteFilesByDirectory", "This directory is not file, execute delete");
        }else{
        	AppLogMessageMgr.i("AppFileMgr-->>deleteFilesByDirectory", "This directory is file, not execute delete");
        }
    }
	
    
    /**
     * 递归取得文件夹大小
     * @param file
     * @return long   
     */
    public static long getFileSize(File file) {
    	long size = 0;
    	if (file != null && file.exists() && file.isDirectory()) {
    		 File files[] = file.listFiles();
 	        for (int i = 0; i < files.length; i++) {
 	            if (files[i].isDirectory()){
 	                size = size + getFileSize(files[i]);
 	            }else {
 	                size = size + files[i].length();
 	            }
 	        }
    	}
    	AppLogMessageMgr.i("AppFileMgr-->>getFileSize", "This file size: " + size);
        return size;
    }
    
    /**
     * 将File写入到指定路径下
     * @param bitmap
     * @param path
     * @return void   
     */
	public static void saveFileToSdcard(Bitmap bitmap, String path){
		File file = new File(path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (!file.exists()) {
			FileOutputStream outputStream;
			byte[] array = null;
			try {
				outputStream = new FileOutputStream(file);
				if (null != bitmap) {
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
					array = os.toByteArray();
					os.close();
				}
				outputStream.write(array);
				outputStream.flush();
				outputStream.close();
				AppLogMessageMgr.i("AppFileMgr-->>saveFileToSdcard-->>bitmap:", bitmap.toString());
				AppLogMessageMgr.i("AppFileMgr-->>saveFileToSdcard-->>path:", path);
				AppLogMessageMgr.i("AppFileMgr-->>saveFileToSdcard:", "将File写入到指定路径下成功！");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				AppLogMessageMgr.e("AppFileMgr-->>saveFileToSdcard:", "将File写入到指定路径下失败！" + e.getMessage());
			} catch (IOException e1) {
				e1.printStackTrace();
				AppLogMessageMgr.e("AppFileMgr-->>saveFileToSdcard:", "将File写入到指定路径下失败！" + e1.getMessage());
			}
		}
	}
	
	/**
	 * 检查文件是否存在（有时间戳）
	 * @param path      路径
	 * @param timestamp 时间戳
	 * @return boolean  返回状态 
	 */
	public static boolean checkFileExists(String path, String timestamp){
		if(timestamp == null){
			if (new File(path).exists()) {
				return true;
			}
		}else{
			File file = new File(path);
			Date fileTime = new Date(file.lastModified());
			long fileTimestamp = fileTime.getTime();
			long newTimestamp = Long.valueOf(timestamp)*1000;
			long error = Long.valueOf(60000000);
			if (new File(path).exists() && fileTimestamp - error>= newTimestamp) {
				return true;
			}
		}
		return false;
	}
	
	 /**
	  * 获取Sdcard指定目录下缓存文件
	  * @param imageUri
	  * @return File  
	  */
	public static File getCacheFile(String imageUri) {
		File cacheFile = null;
		try {
			if (getSdCardIsEnable()) {
				File sdCardDir = Environment.getExternalStorageDirectory();
				String fileName = getFileName(imageUri);
				File dir = new File(sdCardDir.getCanonicalPath() + CACHE_DIR);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				cacheFile = new File(dir, fileName);
			}
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>getCacheFile:","获取Sdcard指定目录下缓存文件失败！" + e.getMessage());
		}
			AppLogMessageMgr.i("AppFileMgr-->>getCacheFile-->>imageUri:", imageUri);
			AppLogMessageMgr.i("AppFileMgr-->>getCacheFile:", "获取Sdcard指定目录下缓存文件成功！");
			return cacheFile;
	}

	/**
	 * 获取文件名称
	 * @param path
	 * @return String   
	 */
	public static String getFileName(String path) {
		int index = path.lastIndexOf("/");
		AppLogMessageMgr.i("AppFileMgr-->>getFileName-->>path:", path);
		return path.substring(index + 1);
	}

	
	/**
	 * 把内容写在SdCard卡上指定目录
	 * @param fileContent
	 * @param fileName    
	 * @return void
	 */
	public static void writeFileToSdCard(String fileContent, String fileName) {
		//判断SDCard是否存在并且可以读写
		String sdCardFlag = Environment.getExternalStorageState();
		if (sdCardFlag != null && sdCardFlag.equals(Environment.MEDIA_MOUNTED)) {
			AppLogMessageMgr.i("AppFileMgr-->>writeFileToSdCard-->>fileContent:",fileContent);
			AppLogMessageMgr.i("AppFileMgr-->>writeFileToSdCard-->>fileName:",fileName);
			File file = new File(CACHE_DIRECTORY + fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			//判断文件是否存在，存在则干掉
			if (file.exists()) {
				file.delete();
			}
			try {
				//创建文件
				file.createNewFile();
				//把String转换为输入流input
				InputStream input = new ByteArrayInputStream(fileContent.getBytes("UTF-8"));
				//开启输出流，准备写入文件
				OutputStream output = new FileOutputStream(file);
				//缓冲区
				byte[] buffer = new byte[1024];
				int numread;
				while ((numread = input.read(buffer)) != -1) {
					output.write(buffer, 0, numread);
				}
				output.flush();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
				AppLogMessageMgr.e("AppFileMgr-->>writeFileToSdCard:","把内容写在SdCard卡上指定目录失败！" + e.getMessage());
			}
		} else {
				AppLogMessageMgr.e("AppFileMgr-->>writeFileToSdCard:","该SdCard不存在或不永许读写操作,写入失败！");
		}
	}

	/**
	 * 将InputStream写入SdCard指定目录下
	 * @param path SdCard下路径
	 * @param fileName 写如文件名称
	 * @param inputStream 输入流
	 * @return File   
	 */
	public File writeInputStreamToSdCard(String path, String fileName, InputStream inputStream){
		File file = null;
		OutputStream output = null;
		try {
			AppLogMessageMgr.i("AppFileMgr-->>writeInputStreamToSdCard-->>path:",path);
			AppLogMessageMgr.i("AppFileMgr-->>writeInputStreamToSdCard-->>fileName:",fileName);
			AppLogMessageMgr.i("AppFileMgr-->>writeInputStreamToSdCard-->>inputStream:",inputStream + "");
			//创建目录和文件
			file = createSDFile( path + fileName);
			//创建输出流 
			output = new FileOutputStream(file);
			//一次读取文件的长度
			byte buffer[] = new byte[4*1024];
			if((inputStream.read(buffer)) != -1){
				output.write(buffer);
			}
			output.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>writeInputStreamToSdCard:","将InputStream写入SdCard指定目录下失败！" + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>writeInputStreamToSdCard:","将InputStream写入SdCard指定目录下失败！" + e.getMessage());
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
				AppLogMessageMgr.e("AppFileMgr-->>writeInputStreamToSdCard:","将InputStream写入SdCard指定目录下失败！" + e.getMessage());
			}
		}
		return file;
	}

	/**
	 * 从SdCard中读取文件内容
	 * @param cacheFileName
	 * @return String   
	 */
	public static String readFileFromSdCard(String cacheFileName) {
		FileReader fr = null;
		String fileContentStr = "";
		try {
			fr = new FileReader(CACHE_DIRECTORY + cacheFileName);
			// 可以换成工程目录下的其他文本文件
			BufferedReader br = new BufferedReader(fr);
			String str = "";
			while ((str = br.readLine()) != null) {
				fileContentStr += str;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>readFileFromSdCard:","从SdCard中读取文件内容失败！" + e.getMessage());
		}
			AppLogMessageMgr.i("AppFileMgr-->>readFileFromSdCard-->>cacheFileName:",cacheFileName);
			AppLogMessageMgr.i("AppFileMgr-->>readFileFromSdCard:","从SdCard中读取文件内容成功！");
			return fileContentStr;
	}

	/**
	 * 创建文件夹(默认首先在SdCard中创建文件夹，如SdCard不存在, 则在手机中创建文件夹)
	 * @param context
	 * @param path
	 * @return String   
	 */
	public static String getRootFilePath(Context context, String path) {
		String file;
		//SdCard已存在
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			file = Environment.getExternalStorageDirectory().getPath() + path;
			AppLogMessageMgr.i("AppFileMgr-->>getRootFilePath","SdCard已存在, 在SdCard中创建文件夹！");
			AppLogMessageMgr.i("AppFileMgr-->>getRootFilePath-->>file", "创建文件夹路径为" + file);
			File files = new File(file);
			if (files == null || !files.exists()) {
				files.mkdir();
			}
			return file;
		}
		//SdCard卡不存在
		else {
			file = Environment.getRootDirectory().getPath() + path;
			AppLogMessageMgr.i("AppFileMgr-->>getRootFilePath","SdCard卡不存在, 在手机中创建文件夹！");
			AppLogMessageMgr.i("AppFileMgr-->>getRootFilePath-->>file:","创建文件夹的路径为" + file);
			File files = new File(file);
			if (files == null || !files.exists()) {
				files.mkdir();
			}
			return file;
		}
	}
    
	/**
	 * 获取SD卡剩余空间的大小(SD卡剩余空间的大小（单位：byte）)
	 * @return long   
	 */
	@SuppressWarnings("deprecation")
	public static long getSdCardSize() {
		String str = Environment.getExternalStorageDirectory().getPath();
		StatFs localStatFs = new StatFs(str);
		long blockSize = localStatFs.getBlockSize();
		return localStatFs.getAvailableBlocks() * blockSize;
	}
	
	
	
	/**
	 * 获取SDCard的AbsolutePath路径
	 * @return String   
	 */
	public static String getSdCardAbsolutePath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;  
	}
	
	
	/**
	 * 获取SdCard的Path路径
	 * @return String   
	 */
	public static String getSdCardPath() {
		return Environment.getExternalStorageDirectory().getPath() +  File.separator;  
	}
	
	
	/**
	 * 获取SDCard卡的剩余容量(单位byte)
	 * @return long  返回大小
	 */
    @SuppressWarnings("deprecation")
	public static long getSdCardEnableSize()  {  
		   //首先判断SdCard是否存在
	       if (getSdCardIsEnable())  {  
	            StatFs stat = new StatFs(getSdCardAbsolutePath());  
	            //获取空闲的数据块的数量  
				long availableBlocks = (long) stat.getAvailableBlocks() - 4;  
	            //获取单个数据块的大小（byte）  
	            long freeBlocks = stat.getAvailableBlocks();  
	            return freeBlocks * availableBlocks;  
	        }  
	        	return 0;  
	}  
	
	
	
	
	/**
	 * 获取SDCard总存储容量大小(单位byte) 
	 * @return long  返回大小
	 */
    @SuppressWarnings("deprecation")
	public long getSdCardAllSize() {  
	      if(getSdCardIsEnable()) {  
	          File path = Environment.getExternalStorageDirectory();  
	          StatFs stat = new StatFs(path.getPath());  
	          long blockSize = stat.getBlockSize();  
	          long totalBlocks = stat.getBlockCount();  
	          return totalBlocks * blockSize;  
	      } 
	      	  return 0;  
	} 

	
	/**
	 * 获取系统AbsolutePath存储路径
	 * @return String   
	 */
    public static String getRootAbsolutePath() {  
       return Environment.getRootDirectory().getAbsolutePath() +  File.separator; 
    }  
    
    
    /**
     * 获取系统Path存储路径
     * @return String   
     */
    public static String getRootPath() {  
        return Environment.getRootDirectory().getPath() +  File.separator; 
    }  
	
    
	/**
	 * 获取手机内存Path存储路径
	 * @return String   
	 */
	public static String getDataPath(){
		return Environment.getDataDirectory().getPath() +  File.separator;  
	}
	
	
	
	/**
	 * 获取手机内存AbsolutePath存储路径
	 * @return String   
	 */
	public static String getDataAbsolutePath(){
		return Environment.getDataDirectory().getAbsolutePath() +  File.separator;  
	}
	
	
	/**
	 * 检查SDCard是否可用，是否存在
	 * @return boolean   
	 */
	public static boolean getSdCardIsEnable() {
		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}
	
	
	/**
	 * 获取可用手机内容容量大小
	 * @return long   
	 */
	@SuppressWarnings("deprecation")
	public static long getMobileEnableRAM(){
		  StatFs stat = new StatFs(getDataPath());  
		  long blockSize = stat.getBlockSize();  
	      long availableBlocks = stat.getAvailableBlocks() - 4;;  
	      return availableBlocks * blockSize;  
	}
	
	
	
	/**
	 * 获取手机内内存总容量大小
	 * @return long   
	 */
	@SuppressWarnings("deprecation")
	public static long getMobileAllRAM(){
	    StatFs stat = new StatFs(getDataPath());  
	    return stat.getBlockSize() * stat.getBlockCount();  
	}

	
	
	/**
	 * 在SdCard中创建文件
	 * @param filename 文件名称
	 * @return File   
	 */
	public static File createSDFile(String filename){
			File file = null;
			try {
				file = new File(getSdCardAbsolutePath() + filename);
				if(!file.exists()){
					file.createNewFile();
				}
			} catch (IOException e) {
				e.printStackTrace();
				AppLogMessageMgr.e("AppFileMgr-->>createSDFile:","创建文件失败！" + e.getMessage());
			}
				return file;
	}
	
	
	/**
	 * 在SdCard中创建目录
	 * @param directory    
	 * @return void   
	 */
	public static void createSDDirectory(String directory){
		File file = new File(getSdCardAbsolutePath() + directory);
		if(!file.exists()){
			file.mkdir();
		}
	}
	
	
	/**
	 * 在SdCard中创建目录
	 * @param directorys
	 */
	public static void createSDDirectorys(String directorys){
		File file = new File(getSdCardAbsolutePath() + directorys);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	
	/**
	 * 判断文件是否在SdCard中存在
	 * @param filename
	 * @return boolean   
	 */
	public static boolean isFileExistSdCard(String filename){
		File file = new File(getSdCardPath() + filename);
		return file.exists();
	}
	
	
	/**
	 * 删除已存在的文件
	 * @param path    
	 * @return void   
	 */
	public static void removeExpiredCache(String path) {  
		if(checkFileExists(path)){
			File file = new File(path);  
			file.delete();  
	    }  
	}
	
	
	/**
	 * 检查文件路径是否存在
	 * @param path
	 */
	public static boolean checkFileExists(String path) {
		if (new File(path).exists()) {
			return true;
		}
			return false;
	}
	

	/**
	 * 一行一行读取文件，解决读取中文字符时出现乱码, 流的关闭顺序：先打开的后关，后打开的先关， 否则有可能出现java.io.IOException: Stream closed异常
	 * @param filePath
	 * @throws IOException    
	 * @return String   
	 */
	public static String readFileByLine(String filePath) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		String arrs = null;
		while ((line = br.readLine()) != null) {
			arrs = line;
		}
		br.close();
		isr.close();
		fis.close();
		return arrs;
	}

	
	/**
	 * 写入文件
	 * @param path
	 * @param value
	 * @return void   
	 */
	public static void writeFile(String path, String value) throws IOException {
		FileOutputStream fos = new FileOutputStream(new File(path));
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(value);
		bw.close();
		osw.close();
		fos.close();
	}
	
	
	/**
	 * 根据地址获取InputStream
	 * @param urlStr
	 * @throws IOException    
	 * @return InputStream   
	 */
	public InputStream getInputStreamByStringURL(String urlStr){
		InputStream inputStream = null;
		try {
			URL url = new URL(urlStr);
			URLConnection urlConnection = url.openConnection();
			inputStream = urlConnection.getInputStream();
		} catch (MalformedURLException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>getInputStreamByStringURL:","根据地址获取InputStream失败！" + e.getMessage());
		} catch (IOException e){
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>getInputStreamByStringURL:","根据地址获取InputStream失败！" + e.getMessage());
		}
		 	return inputStream;
	}
	
	
	/**
	 * 将文件转成base64 字符串
	 * @param path 文件路径 
	 * @return String  base64字符串
	 */
    public static String getEncodeBase64File(String path) {
    	String strings = null;
		try {
			File  file = new File(path);
			FileInputStream inputFile = new FileInputStream(file);
			byte[] buffer = new byte[(int)file.length()];
		    inputFile.read(buffer);
		    inputFile.close();
		    strings = new BASE64Encoder().encode(buffer);
		    AppLogMessageMgr.i("AppFileMgr-->>getEncodeBase64File", "文件转换base64字符串成功！"  + strings);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>getEncodeBase64File", "文件转换base64字符串失败！"  + e.getMessage());
		} catch(IOException e){
			e.printStackTrace();
			AppLogMessageMgr.e("AppFileMgr-->>getEncodeBase64File", "文件转换base64字符串失败！"  + e.getMessage());
		}
			return strings;
    }

    
    /**
     * 将base64字符解码保存文件
     * @param base64Code base64字符串
     * @param targetPath 输出目录 
     * @return void   
     */
    public static void getDecoderBase64File(String base64Code, String targetPath) {
        byte[] buffer;
		try {
			buffer = new BASE64Decoder().decodeBuffer(base64Code);
	        FileOutputStream out = new FileOutputStream(targetPath);
	        out.write(buffer);
	        out.close();
	        AppLogMessageMgr.i("AppFileMgr-->>getDecoderBase64File", "base64字符解码保存文件成功！");
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.i("AppFileMgr-->>getDecoderBase64File", "base64字符解码保存文件失败！" + e.getMessage());
		}
    }
    

	
	/***
	 * 单文件剪切/目录文件剪切功能实现
	 * 单文件剪切操作（1）： 
	 * 					  File src = new File("F://work//s2sh.jpg");  剪切文件路径
	 * 		   			  File desc = new File("F://AAA//");          存放目录路径
	 *  				  falg = CutFile( src, desc, true , true);    返回文件剪切成功与失败状态(测试通过)		
	 * 单文件剪切操作（2）： 
	 * 					  File src = new File("F://work//s2sh.jpg");  剪切文件路径
	 * 					  File src = new File("F://AAA//s2sh.jpg");   存放后全路径
	 * 					  falg = CutFile( src, desc, true , true);    返回文件剪切成功与失败状态(测试通过)	
	 * 文件目录剪切操作(1):
	 * 					  File src = new File("F://testB");    	           源文件所在目录
	 *                    File desc = new File("F://AAA//testB");    文件剪切到目录全路径
	 *                    falg = CutFile( src, desc, true , true);   返回文件剪切成功与失败状态(测试通过)
	 * @param src  源文件夹
	 * @param desc 目标文夹
	 * @param boolCover 如(源/目)文件目录同名
	 * @param boolCut 如是否是剪切操作，
	 * @throws Exception 异常处理
	 * @return falg = true 文件剪切成功。falg = false 文件剪切失败。
	 */
	public static boolean cutFile(File src, File desc, boolean boolCover, boolean boolCut){	
		try {
			//1:单文件剪切操作
			if(src.isFile())    
			{
				if(!desc.isFile() || boolCover)
						//创建新文件
						desc.createNewFile();
						//进行复制操作
						CUT_FALG = copyFile(src, desc);
						//是否是剪切操作
						if(boolCut){ 	src.delete();	}
			}
			//2：多文件剪切操作
			else if(src.isDirectory()) {  
						desc.mkdirs();
						File[] list = src.listFiles();
						//循环向目标目录写如内容
						for(int i = 0; i < list.length; i++){
							String fileName = list[i].getAbsolutePath().substring(src.getAbsolutePath().length(), list[i].getAbsolutePath().length());
							File descFile = new File(desc.getAbsolutePath()+ fileName);
							cutFile(list[i],descFile, boolCover, boolCut);	
						}
						//是否是剪切操作
						if(boolCut)	{  	src.delete();	}
			}
		} catch (Exception e) {
				CUT_FALG = false;
				e.printStackTrace();
				AppLogMessageMgr.e("AppFileMgr-->>cutFile:","文件剪切操作出现异常！" + e.getMessage());
		}	
				return CUT_FALG;
	}
	
	
	/***
	 * 单文件或多文件目录复制操作
	 * 单文件复制形式1：   
	 * 					File src = new File("F://work//s2sh.jpg");  源文件全路径
	 *					File desc = new File("F://AAA//");          需要复制文件路径
	 *					falg = CopeFile(src, desc);					返回复制成功与失败状态(测试通过)
	 * 单文件复制形式2：   
	 * 					File src = new File("F://work//s2sh.jpg");  源文件全路径
	 *					File desc = new File("F://AAA//s2sh.jpg");  需要复制文件路径	
	 *					falg = CopeFile(src, desc);					返回复制成功与失败状态(测试通过)	  
	 * 目录复制形式1：      
	 * 					File src = new File("F://test");     		源文件目录路径
	 *  				File desc = new File("F://AAA//test");		复制目录下全路径
	 *					falg = CopeFile(src, desc);                 返回复制成功与失败状态(测试通过)	 			

	 * @param src  源文件的全路径
	 * @param desc 复制文件路径
	 * @throws Exception 异常处理
	 * @return falg = true 复制操作成功。falg = false 复制操作失败。
	 */
	public static boolean copyFile(File src, File desc){
		//创建字节流对象(输入,输出)
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		//创建文件输入流,输入流对象
		FileInputStream srcInputStream  = null;
		FileOutputStream descOutputStream= null;
		//记录同文件复制数量操作
		int count = 0;
		//是否存在相同文件
		boolean boolCover = false;
		//单文件复制操作实现
		if(src.isFile()){
				try {
					  //获取需要复制下目录列表文件数组
					  File[] list = desc.listFiles();
					  //获取复制文件名     
					  String srcname = src.toString().substring(src.toString().lastIndexOf("\\")+1, src.toString().length()).trim();
					  if(null != list)
					  {
						  if(list.length > 0)
						  {
							    //循环判断复制目录下是否和源文名相同
								for(int i = 0; i < list.length; i++)
								{
									//获取复制目录下文件名
									String descname = list[i].toString().substring(list[i].toString().lastIndexOf("\\")+1, list[i].toString().length()).trim();
									//判定复制文件名和目录文件名相同，记录重复数为1
									if(srcname.equals(descname)){
											count = count + 1;
											boolCover = true;
									}
									if(descname.indexOf("复件") != -1 && descname.indexOf(srcname.substring(srcname.indexOf(")")+1, srcname.length())) != -1){
											count = count + 1;
									}
								}
						  }
					  }
					  //存在重复文件信息
					  if(boolCover)
					  {
						  if(count == 1)
						  {
							  if(desc.toString().indexOf(".") != -1)
							  {
								  //向磁盘中写入： 复件 + 复制文件名称
								  descOutputStream = new FileOutputStream(desc.toString() + "\\复件 " );
							  }else
							  {
								  //向磁盘中写入： 复件 + 复制文件名称
								  descOutputStream = new FileOutputStream(desc.toString() + "\\复件 " + srcname);
							  }
						  }else{
							  if(desc.toString().indexOf(".") != -1)
							  {
								  //向磁盘中写入： 复件(记录数)+ 复制文件名称
								  descOutputStream = new FileOutputStream(desc.toString() + "\\复件 ("+count+") ");
							  }else
							  {
								  //向磁盘中写入： 复件(记录数)+ 复制文件名称
								  descOutputStream = new FileOutputStream(desc.toString() + "\\复件 ("+count+") " + srcname);
							  }
						  }
					  }else{
						  	if(desc.toString().indexOf(".") != -1)
						  	{
						  		descOutputStream = new FileOutputStream(desc.toString() + "\\" );
						  	}else
						  	{
						  		descOutputStream = new FileOutputStream(desc.toString() + "\\" + srcname);
						  	}
					  }
					  byte[] buf = new byte[1];
					  srcInputStream = new FileInputStream(src);  
				      bis = new BufferedInputStream(srcInputStream);
				      bos = new BufferedOutputStream(descOutputStream);
				      while(bis.read(buf) != -1){
				        	  bos.write(buf);
				        	  bos.flush();
				       }
				      COPY_FALG = true;
				} catch (Exception e) {
					  COPY_FALG = false;
					  e.printStackTrace();
					  AppLogMessageMgr.e("AppFileMgr-->>copyFile:","文件复制操作出现异常！" + e.getMessage());
				}finally{
						try {
							if(bis != null){ 	
								bis.close(); 	
							}
							if(bos != null){  	
								bos.close(); 	
							}	
						} catch (IOException e) {
							COPY_FALG = false;
							e.printStackTrace();
							AppLogMessageMgr.e("AppFileMgr-->>copyFile:", "文件复制操作，数据流关闭出现异常！" + e.getMessage());
						}
				}
			}else if(src.isDirectory()){
				//创建目录
				desc.mkdir();
				File[] list = src.listFiles();
				//循环向目标目录写如内容
				for(int i = 0; i < list.length; i++){
					String fileName = list[i].getAbsolutePath().substring(src.getAbsolutePath().length(), list[i].getAbsolutePath().length());
					File descFile = new File(desc.getAbsolutePath()+ fileName);
					copyFile(list[i], descFile);	
				}
			}
				return COPY_FALG;
	}
	
	/***
	 * 用于对文件进行重命名操作
	 * 1：重命名：FileHelper.RenameFile(new File("F:\\AAA\\A.txt"),"AA")  测试通过
	 * @param file  重命名文件对象
	 * @param name  命名文件名称
	 * @return  rename_falg为true重命名成功,为false重命名失败。
	 */
	public static boolean renameFile(File file, String name){
		String path = file.getParent();
		if(!path.endsWith(File.separator)){
			path += File.separator;
		}
		AppLogMessageMgr.i("AppFileMgr-->>renameFile:", "文件重命名操作成功！");
		return file.renameTo(new File(path + name));
	}
	
	
	
	/***
	 * 用于对文件或文件夹进行删除操作
	 * 1：删除文件     FileHelper.DeleteFile(new File("F:\\AAA\\A.txt"))   测试通过
	 * 2：删除目录     FileHelper.DeleteFile(new File("F:\\AAA\\work"))    测试通过
	 * @param file  删除文件对象
	 * @return  delete_falg为true删除文件/目录成功,为false删除文件/目录失败。
	 */
	public static boolean deleteFile(File file){
		try {
			if(file.isFile())
			{
				file.delete();
				DELETE_FALG  = true;
			}
			else if(file.isDirectory())
			{
				File[] list = file.listFiles();
				for(int i=0;i<list.length;i++){
					deleteFile(list[i]);				
				}
				file.delete();
			}
		} catch (Exception e) {
			DELETE_FALG = false;
			e.printStackTrace();
			AppLogMessageMgr.i("AppFileMgr-->>deleteFile", "文件删除出现异常！" + e.getMessage());
		}
			return DELETE_FALG;
	}

	/**
	 *
	 * @param closeables
	 */
	public static void closeIO(Closeable... closeables) {
		if (null == closeables || closeables.length <= 0) {
			return;
		}
		for (Closeable cb : closeables) {
			try {
				if (null == cb) {
					continue;
				}
				cb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
