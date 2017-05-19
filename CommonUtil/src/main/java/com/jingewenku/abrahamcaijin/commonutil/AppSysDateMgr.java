package com.jingewenku.abrahamcaijin.commonutil;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 主要功能：该工具用于App时间管理通用类
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月04日 14:13
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressLint("SimpleDateFormat")
public class AppSysDateMgr {
	
	
	private static SimpleDateFormat yyyyMMddFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat hhmmssFormat = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat yyyyMMddHHmmssFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	


    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };


	private final static ThreadLocal<SimpleDateFormat> dateFormaterFull = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };


	/**
	 * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd HH:mm:ss:SSS)
	 * @return String 当前系统时间
	 */
    public static String getSysDateByAll(){
    	return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS") .format(new Date());
    }


	/**
	 * 系统时间转换成指定格式(格式：yyyy年MM月dd日 HH时mm分ss秒SSS毫秒)
	 * @return String 当前系统时间
	 */
    public static String getSysDateByAllFormat(){
    	return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒") .format(new Date());
    }


	/**
	 * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd HH:mm:ss)
	 * @return String 当前系统时间
	 */
	public static String getSysDateByFull(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}


	/**
	 * 系统时间转换成指定格式(格式：yyyy年MM月dd日 HH时mm分ss秒)
	 * @return String 当前系统时间
	 */
	public static String getSysDateByFullFormat(){
		return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
	}


	/**
	 * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd HH:mm)
	 * @return String 当前系统时间
	 */
	public static String getSysDateByMinute(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
	}


	/**
	 * 系统时间转换成指定格式(格式：yyyy年MM月dd日 HH时mm分)
	 * @return String 当前系统时间
	 */
	public static String getSysDateByMinuteFormat(){
		return new SimpleDateFormat("yyyy年MM月dd日  HH时mm分").format(new Date());
	}

	/**
	 * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd HH)
	 * @return String 当前系统时间
	 */
	public static String getSysDateByHour(){
		return new SimpleDateFormat("yyyy-MM-dd HH").format(new Date());
	}


	/**
	 * 系统时间转换成指定格式(格式：yyy年MM月dd日 HH时)
	 * @return String  当前系统时间
	 */
	public static String getSysDateByHourFormat(){
		return new SimpleDateFormat("yyyy年MM月dd日  HH时").format(new Date());
	}


	/**
	 * 获得当前系统时间并转换成字符串(格式：yyyy-MM-dd)
	 * @return String 当前系统时间
	 */
	public static String getSysDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	}


	/**
	 * 系统时间转换成指定格式(格式：yyy年MM月dd日)
	 * @return String  当前系统时间
	 */
	public static String getSysDateFormat(){
		return new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
	}


	/**
	 * 通过时间字符串转换成指定格式
	 * @param date 时间值
	 * @param dataFormat 格式为(yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss)
	 * @return String 返回格式化时间值
	 */
	public static Date getFormatDateByString(String date, String dataFormat) {
		try {
			return new SimpleDateFormat(dataFormat).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getFormatDateByString", e.getMessage().toString());
			return null;
		}
	}


	/**
	 * 通过时间字符串转换成指定格式
	 * @param date 时间值
	 * @param dataFormat 格式为(yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss)
	 * @param chianDataFormat 格式可为(yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss) 或 (yyyy年MM月dd日 或 yyyy年MM月dd日 HH时mm分ss秒)
	 * @return String 返回格式化时间值
	 */
	public static String getFormatDateByString(String date, String dataFormat, String chianDataFormat) {
		try {
			return new SimpleDateFormat(chianDataFormat).format(new SimpleDateFormat(dataFormat).parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getFormatDateByString", e.getMessage().toString());
			return null;
		}
	}




	/**
	 * 通过时间字符串转换成指定格式(格式：yyyy年MM月dd日)
	 * @param date 时间值
	 * @return String 返回格式化时间值
	 */
	public static String getFormatDateByString(String date){
		try {
			return new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getFormatDateByString", e.getMessage().toString());
			return null;
		}
	}


	/**
	 * 通过时间字符串转换成指定格式(格式：yyyy年MM月dd日 HH时mm分ss秒)
	 * @param date 时间值
	 * @return String 返回格式化时间值
	 */
	public static String getFormatDateFullByString(String date){
		try {
			return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getFormatDateFullByString", e.getMessage().toString());
			return null;
		}
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmss)
	 * @return String 返回时间
	 */
	public static String getNowTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(Calendar.getInstance().getTime());
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmss)
	 * @return String 返回时间
	 */
	public static String getStringTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(new Date());
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmssSSS)
	 * @return String 返回时间
	 */
	public static String getStringTimeFull() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return df.format(new Date());
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmssSSS) + 四位随机码  = 共19位随机码
	 * @return String 返回时间
	 */
	public static String getStringTimeFullRandom2(){
		return getStringTimeFull() + new Random().nextInt(100);
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmssSSS) + 四位随机码  = 共21位随机码
	 * @return String 返回时间
	 */
	public static String getStringTimeFullRandom4(){
		return getStringTimeFull() + new Random().nextInt(10000);
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmss) + 四位随机码  = 共12位随机码
	 * @return String 返回时间
	 */
	public static String getStringTimeRandom2(){
		return getStringTime() + new Random().nextInt(100);
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmss) + 四位随机码  = 共14位随机码
	 * @return String 返回时间
	 */
	public static String getStringTimeRandom4(){
		return getStringTime() + new Random().nextInt(10000);
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmss) + 六位随机码   = 共16位随机码
	 * @return String 返回时间
	 */
	public static String getStringTimeRandom6(){
		return getStringTime() + new Random().nextInt(1000000);
	}

	/**
	 * 获取系统时间(格式：yyyyMMddHHmmss) + 八位随机码    = 共18位随机码
	 * @return String 返回时间
	 */
	public static String getStringTimeRandom8(){
		return getStringTime() + new Random().nextInt(100000000);
	}

	/**
	 * 获取参数时间的年
	 * @param date 时间值
	 * @return String 返回年
	 */
    public static String getSysYear(Date date){
       return new SimpleDateFormat("yyyy").format(date).toString();
    }

    /**
	 * 获取参数时间的年
	 * @param date 时间值
	 * @return String 返回年
	 */
    public static String getSysYear(String date){
        return new SimpleDateFormat("yyyy").format(date).toString();
     }

    /**
     * 获取参数时间的月
     * @param date 时间值
     * @return String 返回月
     */
    public static String getSysMonth(Date date){
        return new SimpleDateFormat("MM").format(date).toString();
    }

    /**
     * 获取参数时间的月
     * @param date 时间值
     * @return String 返回月
     */
    public static String getSysMonth(String date){
        return new SimpleDateFormat("MM").format(date).toString();
    }

    /**
     * 获取参数时间的天
     * @param date  时间值
     * @return String 返回天
     */
    public static String getSysDay(Date date){
        return new SimpleDateFormat("dd").format(date).toString();
    }

    /**
     * 获取参数时间的天
     * @param date 时间值
     * @return String 返回天
     */
    public static String getSysDay(String date){
        return new SimpleDateFormat("dd").format(date).toString();
    }

	/**
	 * 获取当前时间(格式：yyyy-MM-dd)
	 * @return String 返回时间
	 */
	public static String getCalendarToday() {
		int year = 0;
		int moth = 0;
		int day = 0;
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		moth = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH);
		return year + "-" + moth + "-" + day;
	}

	/**
	 * 获取当前时间的下个月份(格式：yyyy-MM-dd)
	 * @return String 当前时间下个月份
	 */
	public static String getCalendarTodayNextMonth() {
		int year = 0;
		int moth = 0;
		int day = 0;
		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		moth = c.get(Calendar.MONTH) + 1;
		day = c.get(Calendar.DAY_OF_MONTH) + 2;
		return year + "-" + moth + "-" + day;
	}

	/**
	 * 判断日期是否属于今天日期(精确到天)
	 * @param sDate 日期值
	 * @return boolean 返回true表示是，false表示不是
	 */
    public static boolean getSysIsToday(String sDate) {
        boolean falg = false;
		try {
			Date date = null;
			date = dateFormaterFull.get().parse(sDate);
			Date today = new Date();
	        if (date != null) {
	            String nowDate = dateFormater.get().format(today);
	            String timeDate = dateFormater.get().format(date);
	            if (nowDate.equals(timeDate)) {
	            	falg = true;
	            }
	        }
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getSysIsToday", e.getMessage().toString());
		}
        	return falg;
    }

    /**
     * 检查日期是否有效
     * @param year 年
     * @param month 月
     * @param day 日
     * @return boolean
     */
	public static boolean getDateIsTrue(String year, String month, String day){
		try {
			String data = year + month + day;
			SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
			simpledateformat.setLenient(false);
			simpledateformat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getDateIsTrue", e.getMessage().toString());
			return false;
		}
			return true;
	}

	/**
	 * 判断两个字符串日期的前后
	 * @param strdate1  字符串时间1
	 * @param strdate2  字符串时间2
	 * @return boolean
	 * 日期与时间
	 */
	public static boolean getDateIsBefore(String strdate1, String strdate2){
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			AppLogMessageMgr.i("AppSysDateMgr-->>getDateIsBefore-->>strdate1: ", strdate1);
			AppLogMessageMgr.i("AppSysDateMgr-->>getDateIsBefore-->>strdate2: ", strdate2);
			return df.parse(strdate1).before(df.parse(strdate2));
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
			return false;
		}
	}
	/**
	 * 判断两个字符串日期的前后
	 * @param strdate1  字符串时间1
	 * @param strdate2  字符串时间2
	 * @return boolean
	 */
	public static boolean getDateIsEqual(String strdate1, String strdate2){
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.parse(strdate1).equals(df.parse(strdate2));
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
			return false;
		}
	}

	/**
	 * 判断两个字符串日期的前后
	 * @param Longdate1  字符串时间1
	 * @param Longdate2  字符串时间2
	 * @return boolean
	 */
	public static boolean getDateIsBefore(Long Longdate1, Long Longdate2){
		try {
			AppLogMessageMgr.i("AppSysDateMgr-->>getDateIsBefore-->>strdate1: ", Longdate1 + "");
			AppLogMessageMgr.i("AppSysDateMgr-->>getDateIsBefore-->>strdate2: ", Longdate2 + "");
			Longdate1 = (null == Longdate1) ? 0 : Longdate1;
			Longdate2 = (null == Longdate2) ? 0 : Longdate2;
			return  Longdate1 > Longdate2 ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
			return false;
		}
	}

	/**
	 * 判断两个时间日期的前后
	 * @param date1  日期1
	 * @param date2  日期2
	 * @return boolean
	 */
	public static boolean getDateIsBefore(Date date1, Date date2) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return getDateIsBefore(df.format(date1), df.format(date2));
	}
	
//	public static int getDateIsBefore(String DATE1, String DATE2) {
//
//		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			Date dt1 = df.parse(DATE1);
//			Date dt2 = df.parse(DATE2);
////			if (dt1.getTime() > dt2.getTime()) {
////				return 1;
////			} else if (dt1.getTime() < dt2.getTime()) {
////				return -1;
////			} else {
////				return 0;
////			}
////
//
//			if(getDateIsBefore(dt1 , dt2)){
//				return -1;
//			}else{
//				return 1;
//			}
//		} catch (Exception exception) {
//			exception.printStackTrace();
//		}
//		return 0;
//	}
    
	
	/**
	 * 判断两个字符串日期的前后
	 * @param strdate1  字符串时间1
	 * @param strdate2  字符串时间2
	 * @return boolean   
	 * 日期比较
	 * 
	 * create by huangcheng
	 */
	public static boolean getDateIsBeforeYYMMDD(String strdate1, String strdate2){
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			AppLogMessageMgr.i("AppSysDateMgr-->>getDateIsBefore-->>strdate1: ", strdate1);
			AppLogMessageMgr.i("AppSysDateMgr-->>getDateIsBefore-->>strdate2: ", strdate2);
			return df.parse(strdate1).before(df.parse(strdate2));
		} catch (ParseException e) {
			e.printStackTrace();
			AppLogMessageMgr.e("AppSysDateMgr-->>getDateIsBefore", e.getMessage().toString());
			return false;
		}
	}
	
	/**
     *  日期格式字符串转换成时间戳
     *  
     *  create by fuxiaosong
     * @return
     */
	public static long date2TimeStamp(String date , SimpleDateFormat dateFormat){
        try {
            return dateFormat.parse(date).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

	/**
	 * 计算两个日期之间相差的分钟
	 * 
	 * create by fuxiaosong
	 */
	public static long minuteBetweenTwoDate(String dateBegin , String dateEnd){
        long millisBegin = date2TimeStamp(dateBegin , yyyyMMddHHmmssFormat);
        long millisEnd = date2TimeStamp(dateEnd , yyyyMMddHHmmssFormat);
        return (millisEnd - millisBegin) / 60;
    }

	/**
	 * 
	 * @Title: getStringByDateDefault 
	 * @Description: 将"Tue Apr 18 15:41:37 CST 2017"转成"2017-04-18 15:41:37"
	 * @param @param dateDefault
	 * @param @return    设定文件 
	 * @return String    返回类型 
	 * @throws
	 */
	public static String getStringByDateDefault(String dateDefault) {
    	    try {
    	        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.US);
    	        Date d = sdf.parse(dateDefault);
    	        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") .format(d);
    	    } catch (ParseException e) {
    	        // TODO: handle exception
    	        e.printStackTrace();
    	    }
    	    
    	    return null;
//	       SimpleDateFormat sdf1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
//                try {
//                    Date date = sdf1.parse(dateDefault);
//                    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                   return sdf.format(date);
//                } catch (ParseException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
	}

	/**
	 * @Description:2017年5月5日15时41分将年月日时分秒数字单独分割出来：2017 5 5 15 41
	 * @param time 2017年5月5日15时41分
	 * @return String
	 */
	public static String getAloneTime(String time){
		String regex = "(\\d{4})年(\\d{1,2})月(\\d{1,2})日(\\d{1,2})时(\\d{1,2})";
		Matcher m = Pattern.compile(regex).matcher(time);
		if (m.find()) {
			return m.group(1) + "," + m.group(2) + "," + m.group(3) + "," + m.group(4) + "," + m.group(5);
		}
		return null;
	}
	
}
