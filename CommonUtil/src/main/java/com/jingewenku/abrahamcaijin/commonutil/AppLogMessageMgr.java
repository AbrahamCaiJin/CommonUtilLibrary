package com.jingewenku.abrahamcaijin.commonutil;

import android.util.Log;


/**
 * 主要功能： 系统日志输出工具类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月04日 14:13
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
public class AppLogMessageMgr {
    //是否输出
    private static boolean isDebug = true;

    /*
     * 设置debug模式(true:打印日志  false：不打印)
     */
    public static void isEnableDebug(boolean isDebug){
        AppLogMessageMgr.isDebug = isDebug;
    }
    
    /**
     * 
     * @param tag
     * @param msg
     */
    public static void i(String tag,String msg){
        if(isDebug){
            Log.i(tag, msg != null ? msg : "");
        }
    }
    public static void i(Object object,String msg){
        if(isDebug){
            Log.i(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void i(String msg){
        if(isDebug){
            Log.i(" [INFO] --- ", msg != null ? msg : "");
        }
    }

    /**
     * 
     * @param tag
     * @param msg
     */
    public static void d(String tag,String msg){
        if(isDebug){
            Log.d(tag, msg != null ? msg : "");
        }
    }

    public static void d(Object object,String msg){
        if(isDebug){
            Log.d(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void d(String msg){
        if(isDebug){
            Log.d(" [DEBUG] --- ", msg != null ? msg : "");
        }
    }

    /**
     * 
     * @param tag
     * @param msg
     */
    public static void w(String tag,String msg){
        if(isDebug){
            Log.w(tag, msg != null ? msg : "");
        }
    }

    public static void w(Object object,String msg){
        if(isDebug){
            Log.w(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void w(String msg){
        if(isDebug){
            Log.w(" [WARN] --- ", msg != null ? msg : "");
        }
    }

    /**
     * 
     * @param tag
     * @param msg
     */
    public static void e(String tag,String msg){
        if(isDebug){
            Log.e(tag, msg !=null ? msg : "");
        }
    }

    public static void e(Object object,String msg){
        if(isDebug){
            Log.e(object.getClass().getSimpleName(), msg !=null ? msg : "");
        }
    }

    public static void e(String msg){
        if(isDebug){
            Log.e(" [ERROR] --- ", msg !=null ? msg : "");
        }
    }

    /**
     * 
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg){
        if(isDebug){
            Log.v(tag, msg != null ? msg : "");
        }
    }

    public static void v(Object object, String msg){
        if(isDebug){
            Log.v(object.getClass().getSimpleName(), msg != null ? msg : "");
        }
    }

    public static void v( String msg){
        if(isDebug){
            Log.v(" [VERBOSE] --- ", msg != null ? msg : "");
        }
    }
}