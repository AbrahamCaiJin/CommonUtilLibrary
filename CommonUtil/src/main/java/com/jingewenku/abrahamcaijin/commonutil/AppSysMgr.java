package com.jingewenku.abrahamcaijin.commonutil;


import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.webkit.WebView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.UUID;


/**
 * 主要功能: 获取App应用系统基本信息
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月04日 14:13
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressWarnings("deprecation")
public class AppSysMgr {


	public static final int DEFAULT_THREAD_POOL_SIZE = getSysDefaultThreadPoolSize();
	
	
	 /**
     * 获得客户端操作系统名称
     * @return
     */
    public static String getSysClientOs() {
        String OsName = android.os.Build.ID;
        return OsName;
    }
	
	/**
	 * 获取当前操作系统的sdk版本
	 * @return String 系统SDK版本
	 */
	public static String getSysSdk() {
	    String sdkVersion = android.os.Build.VERSION.SDK;
	    AppLogMessageMgr.i("AppSysMgr-->>getSysLanguage", sdkVersion);
	    return sdkVersion;
	}
	
	/**
	 * 获取当前操作系统的语言
	 * @return String 系统语言
	 */
	public static String getSysLanguage() {
		String language = Locale.getDefault().getLanguage();
		AppLogMessageMgr.i("AppSysMgr-->>getSysLanguage",  language);
		return language;
	}

	
	/**
	 * 获取手机型号
	 * @return String 手机型号
	 */
	public static String getSysModel() {
		String model = android.os.Build.MODEL;
		AppLogMessageMgr.i("AppSysMgr-->>getSysModel",  model);
		return model;
	}

	
	/**
	 * 获取操作系统的版本号
	 * @return String 系统版本号
	 */
	public static String getSysRelease() {
		String release = android.os.Build.VERSION.RELEASE;
		AppLogMessageMgr.i("AppSysMgr-->>getSysRelease",  release);
		return release;
	}

	
	/**
	 * 读取SIM卡序列号
	 * @param content 上下文
	 * @return String SIM卡序列号
	 */
	public static String getSysSIMSerialNum(Context content) {
		String simSerialNumber = getSysTelephonyManager(content).getSimSerialNumber();
		AppLogMessageMgr.i("AppSysMgr-->>getSysSIMSerialNum",  simSerialNumber);
		return simSerialNumber;
	}


    /**
     * 获取手机CPU序列号
     * @return String cpu序列号(16位) 读取失败为"0000000000000000"  
     */
    public static String getSysCPUSerialNum() {
        String str = "", strCPU = "", cpuSerialNum = "0000000000000000";
        try {
            //读取CPU信息
            Process pp = Runtime.getRuntime().exec("cat/proc/cpuinfo");
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            //查找CPU序列号
            for (int i = 1; i < 100; i++) {
                str = input.readLine();
                if (str != null) {
                    //查找到序列号所在行
                    if (str.indexOf("Serial") > -1) {
                        //提取序列号
                        strCPU = str.substring(str.indexOf(":") + 1,
                        str.length());
                        //去空格
                        cpuSerialNum = strCPU.trim();
                        break;
                    }
                }else{
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            AppLogMessageMgr.e("AppSysMgr-->>getSysCPUSerialNum",  e.getMessage().toString());
        }
        	return cpuSerialNum;
    }
	

	/**
	 * 获得电话管理实例对象
	 * @param content  上下文
	 * @return TelephonyManager 电话管理实例对象
	 */
	private static TelephonyManager getSysTelephonyManager(Context content) {
		TelephonyManager telephonyManager = null;
		telephonyManager = (TelephonyManager) content.getSystemService(Context.TELEPHONY_SERVICE);
		AppLogMessageMgr.i("AppSysMgr-->>getSysTelephonyManager",  telephonyManager + "");
		return telephonyManager;
	}



	/**
	 * 读唯一的设备ID(唯一的设备ID【GSM手机的IMEI】和【CDMA手机的 MEID】,如果获取不到返回一个默认字符串)
	 * @param content 上下文
	 * @return String 获取设备序列号
	 */
	public static String getSysTelephoneSerialNum(Context content) {
		String deviceId = getSysTelephonyManager(content).getDeviceId();
		AppLogMessageMgr.i("AppSysMgr-->>getSysTelephoneSerialNum",  deviceId + "");
		return deviceId;
	}



	/**
	 * 获取运营商信息(三大运营商)
	 * @param content 上下文
	 * @return String 获取运营商名称
	 */
	public static String getSysCarrier(Context content) {
		String moblieType = "";
		TelephonyManager telephonyManager = (TelephonyManager) content.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = telephonyManager.getSubscriberId();
		if (imsi != null && imsi.length() > 0) {
			//因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
			if (imsi.startsWith("46000") || imsi.startsWith("46002")) {
				//中国移动
				moblieType = "China Mobile";
			} else if (imsi.startsWith("46001")) {
				//中国联通
				moblieType = "China Unicom";
			} else if (imsi.startsWith("46003")) {
				//中国电信
				moblieType = "China Telecom";
			}
		}
		AppLogMessageMgr.i("AppSysMgr-->>getSysCarrier",  moblieType);
		return moblieType;
	}


	/**
	 * 获取手机状态(0：无活动 1：响铃 2：待机)
	 * @param  context 上下文
	 * @return Integer 手机状态
	 */
	public static Integer getSysPhoneState(Context context) {
		Integer callState = getSysTelephonyManager(context).getCallState();
		AppLogMessageMgr.i("AppSysMgr-->>getSysPhoneState",  callState + "");
		return callState;
	}

	
	/**
	 * 获得手机方位
	 * @param context 上下文
	 * @return CellLocation 手机方位
	 */
	public static CellLocation getSysPhoneLoaction(Context context) {
		CellLocation cellLocation = getSysTelephonyManager(context).getCellLocation();
		AppLogMessageMgr.i("AppSysMgr-->>getSysPhoneLoaction",  cellLocation + "");
		return cellLocation;
	}

	
	/**
	 * 获得设备的软件版本号(注：the IMEI/SV(software version) for GSM phones 不支持返回“not available”)
	 * @param context 上下文
	 * @return String 设备软件版本号
	 */
	public static String getSysDeviceSoftVersion(Context context) {
		String deviceSoftwareVersion = getSysTelephonyManager(context).getDeviceSoftwareVersion();
		AppLogMessageMgr.i("AppSysMgr-->>getSysDeviceSoftVersion",  deviceSoftwareVersion + "");
		return deviceSoftwareVersion;
	}


	/**
	 * 获得手机号
	 * @param context 上下文
	 * @return String 手机号
	 */
	public static String getSysPhoneNumber(Context context) {
		String phoneNumber = getSysTelephonyManager(context).getLine1Number();
		AppLogMessageMgr.i("AppSysMgr-->>getSysPhoneNumber",  phoneNumber + "");
		return phoneNumber;
	}

	
	/**
	 * 获得SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字。(注：SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断))
	 * @param context 上下文
	 * @return String SIM移动国家编码和移动网络编码
	 */
	public static String getSysSimCode(Context context) {
		String code = "";
		if (getSysTelephonyManager(context).getSimState() == 5) {
			code = getSysTelephonyManager(context).getSimOperator();
		}
		AppLogMessageMgr.i("AppSysMgr-->>getSysSimCode",  code + "");
		return code;
	}


	/**
	 * 服务商名称(注:例如：中国移动、联通SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断)).
	 * @param context 上下文
	 * @return String 服务商名称
	 */
	public static String getSysSimPrivatorName(Context context) {
		String simOperatorName = "";
		if (getSysTelephonyManager(context).getSimState() == 5) {
			simOperatorName = getSysTelephonyManager(context).getSimOperatorName();
		}
		AppLogMessageMgr.i("AppSysMgr-->>getSysSimPrivatorName",  simOperatorName);
		return simOperatorName;
	}



	/**
	 * 唯一的用户ID (注：例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE)
	 * @param context 上下文
	 * @return String 获取国际移动用户GSM识别码
	 */
	public static String getSysUserPhoneId(Context context) {
		String subscriberId = getSysTelephonyManager(context).getSubscriberId();
		AppLogMessageMgr.i("AppSysMgr-->>getSysUserPhoneId",  subscriberId);
		return subscriberId;
	}
	

	/**
	 * 获取WindowManager对象
	 * @param context 上下文对象
	 * @return WindowManager
	 */
	public static WindowManager getWindowManager(Context context){
		return (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	}
	
	
	/**
	 * 获取屏幕管理类
	 * @param activity activity对象
	 * @return DisplayMetrics 屏幕管理实例
	 */
	public static DisplayMetrics getSysDisplayMetrics(Activity activity) {
		DisplayMetrics displayMetrics = null;
		if (displayMetrics == null) {
			displayMetrics = new DisplayMetrics();
		}
		activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		AppLogMessageMgr.i("AppSysMgr-->>getSysDisplayMetrics",  "获取屏幕管理对象为：" + displayMetrics);
		return displayMetrics;
	}
	
	
	/**
	 * 获取屏幕宽度和高度并返回数组
     * @param  context 上下文对象
     * @return int[] 宽和高
	 */
	public static int[] getScreenDispaly(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		//手机屏幕的宽度
		int width = wm.getDefaultDisplay().getWidth();
		//手机屏幕的高度
		int height = wm.getDefaultDisplay().getHeight();
		AppLogMessageMgr.i("AppSysMgr-->>getScreenDispaly-->>width",  "获取屏幕宽度为：" + width);
		AppLogMessageMgr.i("AppSysMgr-->>getScreenDispaly-->>height",  "获取屏幕高度为：" + height);
		int result[] = { width, height };
		return result;
	}
	
	
    
	/**
	 * 获取屏幕宽度和高度并返回数组
     * @param  context 上下文对象
     * @return int[] 宽和高
	 */
    public static int[] getScreenDispaly8(Context context) {
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		//手机屏幕的宽度
		int width = wm.getDefaultDisplay().getWidth() /10 * 8;
		//手机屏幕的高度
		int height = wm.getDefaultDisplay().getHeight() /10 * 8;
		AppLogMessageMgr.i("AppSysMgr-->>getScreenDispaly-->>width",  "获取屏幕宽度为：" + width);
		AppLogMessageMgr.i("AppSysMgr-->>getScreenDispaly-->>height",  "获取屏幕高度为：" + height);
		int result[] = { width, height };
		return result;
	}
	

	/**
	 * 获取屏幕宽度
     * @param  context 上下文对象
     * @return Integer 屏幕宽度
	 */
    public static Integer getSysScreenWidth(Context context) {  
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        DisplayMetrics displayMetrics = new DisplayMetrics();  
        wm.getDefaultDisplay().getMetrics(displayMetrics);  
        AppLogMessageMgr.i("AppSysMgr-->>getSysScreenWidth",  "获取屏幕宽度为：" + displayMetrics.widthPixels);
        return displayMetrics.widthPixels;  
    }  
	
    
	/**
	 * 获取屏幕高度
     * @param  context 上下文对象
     * @return Integer 屏幕高度
	 */
    public static Integer getSysScreenHeight(Context context)  {  
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  
        DisplayMetrics displayMetrics = new DisplayMetrics();  
        wm.getDefaultDisplay().getMetrics(displayMetrics);  
        AppLogMessageMgr.i("AppSysMgr-->>getSysScreenHeight",  "获取屏幕高度为：" + displayMetrics.heightPixels);
        return displayMetrics.heightPixels;  
    }  
    
	

    /**
     * 获取屏幕状态栏目高度
     * @param  context 上下文对象
     * @return Integer 状态栏高度
     */
    public static Integer getSysScreenStatusHeight(Context context)  {  
        int statusHeight = 0;  
        try {  
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");  
            Object object = clazz.newInstance();  
            int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());  
            statusHeight = context.getResources().getDimensionPixelSize(height);  
            AppLogMessageMgr.i("AppSysMgr-->>getSysScreenStatusHeight",  "获取屏幕状态栏高度为：" + statusHeight);
        } catch (Exception e) {  
            e.printStackTrace();  
            AppLogMessageMgr.e("AppSysMgr-->>getSysScreenStatusHeight",  "获取屏幕状态栏高度失败！" + e.getMessage());
        }  
        	return statusHeight;  
    } 
    
    
	/**
	 * 获得系统配置相符的线程池大小
	 * @return Integer 返回系统配置相符合线程大小
	 */
	public static Integer getSysDefaultThreadPoolSize() {
		Integer availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
		availableProcessors = availableProcessors > 8 ? 8 : availableProcessors;
		AppLogMessageMgr.i("AppSysMgr-->>getSysDefaultThreadPoolSize",  availableProcessors + "");
		return availableProcessors;
	}

	
	
	

	/**
	 * 获取当前APP应用的SampleSize大小
	 * @param options BitmapFactory.Options对象
	 * @param minSideLength  计算最小值
	 * @param maxNumOfPixels 计算最大值
	 * @return Integer  返回SampleSize大小
	 */
    public static Integer getSysSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {  
    	Integer initialSize = calculateSysInitialSampleSize(options, minSideLength, maxNumOfPixels);  
    	Integer roundedSize;  
        if (initialSize <= 8 ) {  
            roundedSize = 1;  
            while (roundedSize < initialSize) {  
                roundedSize <<= 1;  
            }  
        }else{  
        		roundedSize = (initialSize + 7) / 8 * 8;  
        }  
        AppLogMessageMgr.i("AppSysMgr-->>getSysSampleSize",  roundedSize + "");
        return roundedSize;  
    }  
      
    
    /**
     * 计算公式
     * @param options BitmapFactory.Options对象
	 * @param minSideLength  计算最小值
	 * @param maxNumOfPixels 计算最大值
     * @return Integer   
     */
    private static Integer calculateSysInitialSampleSize(BitmapFactory.Options options,int minSideLength, int maxNumOfPixels) {  
        double w = options.outWidth;  
        double h = options.outHeight;  
        Integer lowerBound = (maxNumOfPixels == -1) ? 1 :  (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));  
        Integer upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));  
        if (upperBound < lowerBound) {  
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
     * 获取震动器对象
     * @param context 上下文对象
     * @return Vibrator 震动器对象
     */
    public static Vibrator getVibrator(Context context){
    	return (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    
    /**
     * 获取手机IP地址
     * @return String 手机IP地址
     */
    public String getSysLocalIpAddress() {
    	String hostAddress = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                    	hostAddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
        	e.printStackTrace();
        	AppLogMessageMgr.e("AppSysMgr-->>getSysLocalIpAddress",  e.getMessage().toString());
        }
        	AppLogMessageMgr.i("AppSysMgr-->>getSysLocalIpAddress",  hostAddress);
        	return hostAddress;
    }

	/**
	 *获取AndroidID
	 * @param ctx
	 * @return
	 */
	public static String getAndroidID(Context ctx) {
		return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
	}

	/**
	 * 获取设备IMSI码
	 * @param ctx
	 * @return
	 */
	public static String getIMSI(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSubscriberId() != null ? tm.getSubscriberId() : null;
	}

	/**
	 * 获取网络IP地址(优先获取wifi地址)
	 * @param ctx
	 * @return
	 */
	public static String getIP(Context ctx) {
		WifiManager wifiManager = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.isWifiEnabled() ? getWifiIP(wifiManager) : getGPRSIP();
	}

	/**
	 * 获取WIFI连接下的ip地址
	 * @param wifiManager
	 * @return
	 */
	public static String getWifiIP(WifiManager wifiManager) {
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		String ip = intToIp(wifiInfo.getIpAddress());
		return ip != null ? ip : "";
	}

	/**
	 * 获取GPRS连接下的ip地址
	 * @return
	 */
	public static String getGPRSIP() {
		String ip = null;
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
				for (Enumeration<InetAddress> enumIpAddr = en.nextElement().getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						ip = inetAddress.getHostAddress();
					}
				}
			}
		} catch (SocketException e) {
			e.printStackTrace();
			ip = null;
		}
		return ip;
	}

	private static String intToIp(int i) {
		return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
	}

	/**
	 * 获取设备序列号
	 * @return
	 */
	public static String getSerial() {
		return Build.SERIAL;
	}

	/**
	 * 获取SIM序列号
	 * @param ctx
	 * @return
	 */
	public static String getSIMSerial(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getSimSerialNumber();
	}

	/**
	 * 获取网络运营商 46000,46002,46007 中国移动,46001 中国联通,46003 中国电信
	 * @param ctx
	 * @return
	 */
	public static String getMNC(Context ctx) {
		String providersName = "";
		TelephonyManager telephonyManager = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
			providersName = telephonyManager.getSimOperator();
			providersName = providersName == null ? "" : providersName;
		}
		return providersName;
	}

	/**
	 * 获取网络运营商：中国电信,中国移动,中国联通
	 * @param ctx
	 * @return
	 */
	public static String getCarrier(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getNetworkOperatorName().toLowerCase(Locale.getDefault());
	}

	/**
	 * 获取硬件型号
	 * @return
	 */
	public static String getModel() {
		return Build.MODEL;
	}

	/**
	 * 获取编译厂商
	 * @return
	 */
	public static String getBuildBrand() {
		return Build.BRAND;
	}

	/**
	 *获取编译服务器主机
	 * @return
	 */
	public static String getBuildHost() {
		return Build.HOST;
	}

	/**
	 *获取描述Build的标签
	 * @return
	 */
	public static String getBuildTags() {
		return Build.TAGS;
	}

	/**
	 *获取系统编译时间
	 * @return
	 */
	public static long getBuildTime() {
		return Build.TIME;
	}

	/**
	 *获取系统编译作者
	 * @return
	 */
	public static String getBuildUser() {
		return Build.USER;
	}

	/**
	 *获取编译系统版本(5.1)
	 * @return
	 */
	public static String getBuildVersionRelease() {
		return Build.VERSION.RELEASE;
	}

	/**
	 *获取开发代号
	 * @return
	 */
	public static String getBuildVersionCodename() {
		return Build.VERSION.CODENAME;
	}

	/**
	 * 获取源码控制版本号
	 * @return
	 */
	public static String getBuildVersionIncremental() {
		return Build.VERSION.INCREMENTAL;
	}

	/**
	 *获取编译的SDK
	 * @return
	 */
	public static int getBuildVersionSDK() {
		return Build.VERSION.SDK_INT;
	}

	/**
	 *获取修订版本列表(LMY47D)
	 * @return
	 */
	public static String getBuildID() {
		return Build.ID;
	}

	/**
	 *CPU指令集
	 * @return
	 */
	public static String[] getSupportedABIS() {
		String[] result = new String[]{"-"};
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			result = Build.SUPPORTED_ABIS;
		}
		if (result == null || result.length == 0) {
			result = new String[]{"-"};
		}
		return result;
	}

	/**
	 *获取硬件制造厂商
	 * @return
	 */
	public static String getManufacturer() {
		return Build.MANUFACTURER;
	}

	/**
	 *获取系统启动程序版本号
	 * @return
	 */
	public static String getBootloader() {
		return Build.BOOTLOADER;
	}

	/**
	 *
	 * @param ctx
	 * @return
	 */
	public static String getScreenDisplayID(Context ctx) {
		WindowManager wm = (WindowManager) ctx.getSystemService(Context.WINDOW_SERVICE);
		return String.valueOf(wm.getDefaultDisplay().getDisplayId());
	}

	/**
	 * 获取系统版本号
	 * @return
	 */
	public static String getDisplayVersion() {
		return Build.DISPLAY;
	}

	/**
	 *获取语言
	 * @return
	 */
	public static String getLanguage() {
		return Locale.getDefault().getLanguage();
	}

	/**
	 * 获取国家
	 * @param ctx
	 * @return
	 */
	public static String getCountry(Context ctx) {
		TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
		Locale locale = Locale.getDefault();
		return tm.getSimState() == TelephonyManager.SIM_STATE_READY ? tm.getSimCountryIso().toLowerCase(Locale.getDefault()) : locale.getCountry().toLowerCase(locale);
	}

	/**
	 *获取系统版本:5.1.1
	 * @return
	 */
	public static String getOSVersion() {
		return Build.VERSION.RELEASE;
	}

	/**
	 *获取GSF序列号
	 * @param context
	 * @return
	 */
	//<uses-permission android:name="com.google.android.providers.gsf.permission.READ_GSERVICES"/>
	public static String getGSFID(Context context) {
		String result;
		final Uri URI = Uri.parse("content://com.google.android.gsf.gservices");
		final String ID_KEY = "android_id";
		String[] params = {ID_KEY};
		Cursor c = context.getContentResolver().query(URI, null, null, params, null);
		if (c == null || !c.moveToFirst() || c.getColumnCount() < 2) {
			return null;
		} else {
			result = Long.toHexString(Long.parseLong(c.getString(1)));
		}
		c.close();
		return result;
	}

	/**
	 * 获取蓝牙地址
	 * @param context
	 * @return
	 */
	//<uses-permission android:name="android.permission.BLUETOOTH"/>
	@SuppressWarnings("MissingPermission")
	public static String getBluetoothMAC(Context context) {
		String result = null;
		try {
			if (context.checkCallingOrSelfPermission(Manifest.permission.BLUETOOTH)
				== PackageManager.PERMISSION_GRANTED) {
				BluetoothAdapter bta = BluetoothAdapter.getDefaultAdapter();
				result = bta.getAddress();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Android设备物理唯一标识符
	 * @return
	 */
	public static String getPsuedoUniqueID() {
		String devIDShort = "35" + (Build.BOARD.length() % 10) + (Build.BRAND.length() % 10);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			devIDShort += (Build.SUPPORTED_ABIS[0].length() % 10);
		} else {
			devIDShort += (Build.CPU_ABI.length() % 10);
		}
		devIDShort += (Build.DEVICE.length() % 10) + (Build.MANUFACTURER.length() % 10) + (Build.MODEL.length() % 10) + (Build.PRODUCT.length() % 10);
		String serial;
		try {
			serial = Build.class.getField("SERIAL").get(null).toString();
			return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
		} catch (Exception e) {
			serial = "ESYDV000";
		}
		return new UUID(devIDShort.hashCode(), serial.hashCode()).toString();
	}

	/**
	 * 构建标识,包括brand,name,device,version.release,id,version.incremental,type,tags这些信息
	 * @return
	 */
	public static String getFingerprint() {
		return Build.FINGERPRINT;
	}

	/**
	 * 获取硬件信息
	 * @return
	 */
	public static String getHardware() {
		return Build.HARDWARE;
	}

	/**
	 * 获取产品信息
	 * @return
	 */
	public static String getProduct() {
		return Build.PRODUCT;
	}

	/**
	 *  获取设备信息
	 * @return
	 */
	public static String getDevice() {
		return Build.DEVICE;
	}

	/**
	 * 获取主板信息
	 * @return
	 */
	public static String getBoard() {
		return Build.BOARD;
	}

	/**
	 *  获取基带版本(无线电固件版本 Api14以上)
	 * @return
	 */
	public static String getRadioVersion() {
		return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH ? Build.getRadioVersion() : "";
	}

	/**
	 * 获取的浏览器指纹(User-Agent)
	 * @param ctx
	 * @return
	 */
	public static String getUA(Context ctx) {
		final String system_ua = System.getProperty("http.agent");
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
			return new WebView(ctx).getSettings().getDefaultUserAgent(ctx) + "__" + system_ua;
		} else {
			return new WebView(ctx).getSettings().getUserAgentString() + "__" + system_ua;
		}
	}

	/**
	 * 获取得屏幕密度
	 * @param ctx
	 * @return
	 */
	public static String getDensity(Context ctx) {
		String densityStr = null;
		final int density = ctx.getResources().getDisplayMetrics().densityDpi;
		switch (density) {
			case DisplayMetrics.DENSITY_LOW:
				densityStr = "LDPI";
				break;
			case DisplayMetrics.DENSITY_MEDIUM:
				densityStr = "MDPI";
				break;
			case DisplayMetrics.DENSITY_TV:
				densityStr = "TVDPI";
				break;
			case DisplayMetrics.DENSITY_HIGH:
				densityStr = "HDPI";
				break;
			case DisplayMetrics.DENSITY_XHIGH:
				densityStr = "XHDPI";
				break;
			case DisplayMetrics.DENSITY_400:
				densityStr = "XMHDPI";
				break;
			case DisplayMetrics.DENSITY_XXHIGH:
				densityStr = "XXHDPI";
				break;
			case DisplayMetrics.DENSITY_XXXHIGH:
				densityStr = "XXXHDPI";
				break;
		}
		return densityStr;
	}

	/**
	 * 获取google账号
	 * @param ctx
	 * @return
	 */
	//<uses-permission android:name="android.permission.GET_ACCOUNTS"/>
	@SuppressWarnings("MissingPermission")
	public static String[] getGoogleAccounts(Context ctx) {
		if (ctx.checkCallingOrSelfPermission(Manifest.permission.GET_ACCOUNTS) == PackageManager.PERMISSION_GRANTED) {
			Account[] accounts = AccountManager.get(ctx).getAccountsByType("com.google");
			String[] result = new String[accounts.length];
			for (int i = 0; i < accounts.length; i++) {
				result[i] = accounts[i].name;
			}
			return result;
		}
		return null;
	}

}
