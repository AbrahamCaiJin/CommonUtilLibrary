package com.jingewenku.abrahamcaijin.commonutil;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;



/**
 * 主要功能:获取App应用版本信息
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressWarnings("rawtypes")
public class ApplicationAppMgr {


	/**
	 * 获取本地apk的名称
	 * @param context 上下文
	 * @return String
	 */
	public static String getAppName(Context context) {
		try {
			PackageManager e = context.getPackageManager();
			PackageInfo packageInfo = e.getPackageInfo(context.getPackageName(), 0);
			int labelRes = packageInfo.applicationInfo.labelRes;
			return context.getResources().getString(labelRes);
		} catch (NameNotFoundException var4) {
			var4.printStackTrace();
			return "unKnown";
		}
	}

	/**
	 * 获取本地Apk版本名称
	 * @param context 上下文
	 * @return String
	 */
	public static String getVersionName(Context context) {
		String verName = "";
		try {
			verName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
		} catch (NameNotFoundException e) {
			AppLogMessageMgr.e("ApplicationAppMgr-->>getVerName()", e.getMessage() + "获取本地Apk版本名称失败！");
			e.printStackTrace();
		}
		return verName;
	}

	
	/**
	 * 获取本地Apk版本号
	 * @param context 上下文
	 * @return int
	 */
	public static int getVersionCode(Context context) {
		int verCode = -1;
		try {
			verCode = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
		} catch (NameNotFoundException e) {
			AppLogMessageMgr.e("ApplicationAppMgr-->>getVerCode()", e.getMessage() + "获取本地Apk版本号失败！");
			e.printStackTrace();
		}
		return verCode;
	}

	/**
	 * 根据key获取xml中Meta的值
	 * @param context 上下文
	 * @param key
	 * @return
	 */
	public static String getMetaData(Context context, String key) {
		String value = "";

		try {
			ApplicationInfo e = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
			if(null != e) {
				Bundle metaData = e.metaData;
				if(null != metaData) {
					value = metaData.getString(key);
					if(null == value || value.length() == 0) {
						value = "";
					}
				}
			}
		} catch (NameNotFoundException var5) {
			var5.printStackTrace();
		}

		return value;
	}

	/**
	 * 版本比较
	 *
	 * @param nowVersion    app版本
	 * @param serverVersion 服务器版本
	 * @return
	 */
	public static boolean compareVersion(String nowVersion, String serverVersion) {

		if (nowVersion != null && serverVersion != null) {
			String[] nowVersions = nowVersion.split("\\.");
			String[] serverVersions = serverVersion.split("\\.");
			if (nowVersions != null && serverVersions != null && nowVersions.length > 1 && serverVersions.length > 1) {
				int nowVersionsFirst = Integer.parseInt(nowVersions[0]);
				int serverVersionFirst = Integer.parseInt(serverVersions[0]);
				int nowVersionsSecond = Integer.parseInt(nowVersions[1]);
				int serverVersionSecond = Integer.parseInt(serverVersions[1]);
				if (nowVersionsFirst < serverVersionFirst) {
					return true;
				} else if (nowVersionsFirst == serverVersionFirst && nowVersionsSecond < serverVersionSecond) {
					return true;
				}
			}
		}
		return false;
	}

//	/**
//	 * 获得当前版本信息
//	 * @param keyValues key信息
//	 * @return RequestParams
//	 */
//	public static RequestParams getRequestParams(HashMap<String,String> keyValues){
//		RequestParams params = new RequestParams();
//		Iterator iterator = keyValues.entrySet().iterator();
//        while(iterator.hasNext()){
//            Map.Entry entry = (Map.Entry) iterator.next();
//            Object key = entry.getKey();
//            params.put((String) key, entry.getValue().toString());
//        }
//		return params;
//	}
	
}
