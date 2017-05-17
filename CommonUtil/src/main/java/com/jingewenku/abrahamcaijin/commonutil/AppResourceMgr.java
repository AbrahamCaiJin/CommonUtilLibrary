package com.jingewenku.abrahamcaijin.commonutil;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * 主要功能：该工具类用于获取本地指定资源信息
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
public class AppResourceMgr {

	
	/**
	 * 根据本地Assets目录下资源名称，获取String数据信息
	 * @param context  上下文对象
	 * @param fileName 文件名称
	 * @return String  返回数据
	 */
	public static String getStringByAssets(Context context, String fileName) {
		if (context == null || AppValidationMgr.isEmpty(fileName)) {
			return null;
		}
		try {
			StringBuilder s = new StringBuilder("");
			InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				s.append(line);
			}
			return s.toString();
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppResourceMgr-->>getStringByAssets", "根据本地Assets目录下资源名称，获取String数据信息失败！" + e.getMessage());
			return null;
		}
	}
	
	
	/**
	 * 根据本地Assets目录下资源名称，获取List集合信息
	 * @param context  上下文对象
	 * @param fileName 文件名称
	 * @return List<String>  返回集合
	 */
	public static List<String> getListByAssets(Context context, String fileName) {
		if (context == null || AppValidationMgr.isEmpty(fileName)) {
			return null;
		}
		List<String> fileContent = new ArrayList<String>();
		try {
			InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				fileContent.add(line);
			}
			br.close();
			return fileContent;
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppResourceMgr-->>getListByAssets", "根据本地Assets目录下资源名称，获取List集合信息失败！" + e.getMessage());
			return null;
		}
	}

	
	/**
	 * 根据本地Raw目录下资源标识，获取String数据信息
	 * @param context 上下文对象
	 * @param resId   资源标识
	 * @return String 返回数据
	 */
	public static String getStringByRaw(Context context, int resId) {
		if (context == null) {
			return null;
		}
		try {
			StringBuilder s = new StringBuilder();
			InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				s.append(line);
			}
			return s.toString();
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppResourceMgr-->>getStringByRaw", "根据本地Raw目录下资源标识，获取String数据信息失败！" + e.getMessage());
			return null;
		}
	}



	/**
	 * 根据本地Raw目录下资源标识，获取List集合信息
	 * @param context 上下文对象
	 * @param resId   资源标识
	 * @return List<String> 返回集合   
	 */
	public static List<String> getListByRaw(Context context, int resId) {
		if (context == null) {
			return null;
		}
		List<String> fileContent = new ArrayList<String>();
		BufferedReader reader = null;
		try {
			InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
			reader = new BufferedReader(in);
			String line = null;
			while ((line = reader.readLine()) != null) {
				fileContent.add(line);
			}
			reader.close();
			return fileContent;
		} catch (IOException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppResourceMgr-->>getListByRaw", "根据本地Raw目录下资源标识，获取List集合信息失败！" + e.getMessage());
			return null;
		}
	}

	
}