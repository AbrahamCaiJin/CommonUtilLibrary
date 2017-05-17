package com.jingewenku.abrahamcaijin.commonutil;


import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.telephony.CellLocation;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Locale;


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
	
}
