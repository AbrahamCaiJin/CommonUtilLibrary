package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年12月20日 16:25
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/**
 * @author liuxmi
 * 日期相关函数类
 */
public class DateFunc {
    /**
     * AM/PM
     */
    public static final String AM_PM = "a";
    /**
     * 一个月里第几天
     */
    public static final String DAY_IN_MONTH = "dd";
    /**
     * 一年里第几天
     */
    public static final String DAY_IN_YEAR = "DD";
    /**
     * 一周里第几天(Sunday,...)
     */
    public static final String DAY_OF_WEEK = "EEEE";
    /**
     * 以天为单位
     */
    public static final int DIFF_DAY = Calendar.DAY_OF_MONTH;
    /**
     * 以小时为单位
     */
    public static final int DIFF_HOUR = Calendar.HOUR_OF_DAY;
    /**
     * 以毫秒为单位
     */
    public static final int DIFF_MILLSECOND = Calendar.MILLISECOND;
    /**
     * 以分钟为单位
     */
    public static final int DIFF_MINUTE = Calendar.MINUTE;
    /**
     * 以月份为单位，按照每月30天计算
     */
    public static final int DIFF_MONTH = Calendar.MONTH;
    /**
     * 以秒为单位
     */
    public static final int DIFF_SECOND = Calendar.SECOND;
    /**
     * 以星期为单位，按照每星期7天计算
     */
    public static final int DIFF_WEEK = Calendar.WEEK_OF_MONTH;
    /**
     * 以年为单位，按照每年365天计算
     */
    public static final int DIFF_YEAR = Calendar.YEAR;
    /**
     * 半天内小时(0-11)
     */
    public static final String HOUR_IN_APM = "KK";
    /**
     * 一天内小时(0-23)
     */
    public static final String HOUR_IN_DAY = "HH";
    /**
     * 半天内小时(1-12)
     */
    public static final String HOUR_OF_APM = "hh";
    /**
     *  一天内小时(1-24)
     */
    public static final String HOUR_OF_DAY = "kk";

    /**
     * 年(四位)
     */
    public static final String LONG_YEAR = "yyyy";
    /**
     * 毫秒
     */
    public static final String MILL_SECOND = "SSS";
    /**
     * 分钟
     */
    public static final String MINUTE = "mm";
    /**
     * 月
     */
    public static final String MONTH = "MM";
    /**
     * 秒
     */
    public static final String SECOND = "ss";
    /**
     * 年(二位)
     */
    public static final String SHORT_YEAR = "yy";
    /**
     * 一个月里第几周
     */
    public static final String WEEK_IN_MONTH = "W";
    /**
     * 一年里第几周
     */
    public static final String WEEK_IN_YEAR = "ww";
    //设置时间时区
    static{
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }
    /**
     * 检查目的时间是否已超过源时间值加上时间段长度
     * <p>
     * 用于判别当前是否已经超时
     *
     * @param destDate 目的时间，一般为当前时间
     * @param sourceDate 源时间，一般为事件产生时间
     * @param type 时间计算单位，为分钟、小时等
     * @param elapse 持续时间长度
     * @return 是否超时
     * @throws RuntimeException
     */
    public static boolean compareElapsedTime(
        Date destDate,
        Date sourceDate,
        int type,
        int elapse)
        throws RuntimeException {
        if (destDate == null || sourceDate == null)
            throw new RuntimeException("compared date invalid");

        return destDate.getTime() > getRelativeDate(sourceDate, type, elapse).getTime();
    }
    /**
     * 取当前时间字符串
     * <p>
     * 时间字符串格式为：年(4位)-月份(2位)-日期(2位) 小时(2位):分钟(2位):秒(2位)
     *
     * @return 时间字符串
     */
    public static String getCurrentDateString() {
        return getCurrentDateString("yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 按格式取当前时间字符串
     *
     * @param formatString 格式字符串
     * @return
     */
    public static String getCurrentDateString(String formatString) {

        Date currentDate = new Date();
        return getDateString(currentDate, formatString);
    }

    /**
     * 取当天在一周的第几天
     * @return
     */
    public static int getCurrentDayOfWeek() {
        return getDayOfWeek(new Date());
    }
    /**
     *
     *  @Enclosing_Method: getCurrentDate
     *  @Written by: liuxmi
     *  @Creation Date: Jun 9, 2010 7:31:50 AM
     *  @version: v1.00
     *  @Description:获取当前时间
     *  @return
     *  @return Date
     *
     */
    public static Date  getCurrentDate() {
        return getDateFromString(getDateString(new Date(), "yyyy-MM-dd HH:mm:ss"), "yyyy-MM-dd HH:mm:ss");
    }
    /**
     *
     *  @Enclosing_Method: getDate
     *  @Written by: liuxmi
     *  @Creation Date: Jun 9, 2010 7:32:11 AM
     *  @version: v1.00
     *  @Description:  日期格式化
     *  @param date
     *  @return
     *  @return Date
     *
     */
    public static Date getDate(Date date) {
        return getDateFromString(getDateString(date, "yyyy-MM-dd"), "yyyy-MM-dd");
    }
    /**
     * 根据时间字符串生成时间
     *
     * @param dateString 时间字符串格式
     * @return 时间
     * @throws RuntimeException
     */
    public static Date getDateFromString(String dateString)
        throws RuntimeException {
        return getDateFromString(dateString, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 根据字符串生成时间
     *
     * @param dateString 时间字符串
     * @param pattern 时间字符串格式定义
     * @return 时间
     * @throws RuntimeException
     */
    public static Date getDateFromString(String dateString, String pattern)
        throws RuntimeException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(dateString);
        } catch (java.text.ParseException e) {
            throw new RuntimeException(
                "parse date string '"
                    + dateString
                    + "' with pattern '"
                    + pattern
                    + "' failed: "
                    + e.getMessage());
        }
        return date;
    }
    /**
     * 取时间字符串
     *
     * @param date 时间
     * @return 时间字符串
     */
    public static String getDateString(Date date) {
        return getDateString(date, "yyyy-MM-dd HH:mm:ss");
    }
    /**
     * 取时间字符串
     *
     * @param date 时间
     * @param formatString 转换格式
     * @return 时间字符串
     */
    public static String getDateString(Date date, String formatString) {
        return getDateString(date, formatString, Locale.PRC);
    }

    /**
     * 取时间字符串
     *
     * @param date 时间
     * @param formatString 转换格式
     * @param locale 地区
     * @return 时间字符串
     */
    public static String getDateString(Date date, String formatString, Locale locale) {
        if (date == null)
            return null;
        SimpleDateFormat dateFormat = new SimpleDateFormat(formatString);
        return dateFormat.format(date);
    }


    /**
     * 取日期在一周的第几天
     *
     * @param date 日期
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_WEEK);
    }
    /**
     * 取日期在一月的第几天
     *
     * @param date 日期
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取一个月的最大天数
     *
     * @param date 日期
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取日期所在月份的最大天数
     *
     * @param date 日期
     * @return
     */
    public static int getMaximumDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.getMaximum(Calendar.DAY_OF_MONTH);
    }
    /**
     * 根据源时间和时长计算目的时间
     *
     * @param date 源时间
     * @param type 时间单位
     * @param relate 时长
     * @return 目的时间
     */
    public static Date getRelativeDate(Date date, int type, int relate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(type, relate);
        return calendar.getTime();
    }

    /**
     * 根据当前时间和时长计算目的时间
     *
     * @param type 时间单位
     * @param relate 时长
     * @return 目的时间
     */
    public static Date getRelativeDate(int type, int relate) {
        Date current = new Date();
        return getRelativeDate(current, type, relate);
    }
    /**
     * 根据当前时间和时长生成目的时间字符串
     *
     * @param type 时间单位
     * @param relate 时长
     * @param formatString 时间格式
     * @return 时间字符串
     */
    public static String getRelativeDateString(
        int type,
        int relate,
        String formatString) {
        return getDateString(getRelativeDate(type, relate), formatString);
    }

    public static Date getStartDate(Date date) {
        return getDateFromString(getDateString(date, "yyyyMMdd") + "00:00:00", "yyyyMMddHH:mm:ss");
    }

    public static Date getEndDate(Date date) {
        return getDateFromString(getDateString(date, "yyyyMMdd") + "23:59:59", "yyyyMMddHH:mm:ss");
    }

    /**
     * 取时间戳字符串
     *
     * @param date 时间
     * @return 时间戳字符串
     */
    public static String getTimestampString(Date date) {
        return getDateString(date, "yyyyMMddHHmmssSSS");
    }
    /**
     * 取当天日期值
     *
     * @return 日期的整数值
     */
    public static int getToday() {
        return Integer.parseInt(getCurrentDateString("dd"));
    }

    public static long getTimeDiff(Date fromDate, Date toDate, int type) {
        fromDate = (fromDate == null) ? new Date() : fromDate;
        toDate = (toDate == null) ? new Date() : toDate;
        long diff = toDate.getTime() - fromDate.getTime();

        switch(type) {
            case DIFF_MILLSECOND:
                break;

            case DIFF_SECOND:
                diff /= 1000;
                break;

            case DIFF_MINUTE:
                diff /= 1000 * 60;
                break;

            case DIFF_HOUR:
                diff /= 1000 * 60 * 60;
                break;

            case DIFF_DAY:
                diff /= 1000 * 60 * 60 * 24;
                break;

            case DIFF_MONTH:
                diff /= 1000 * 60 * 60 * 24 * 30;
                break;
            case DIFF_YEAR:
                diff /= 1000 * 60 * 60 * 24 * 365;
                break;

            default:
                diff = 0;
                break;
        }

        return diff;
    }

    /**
     * 比较时间戳是否相同
     *
     * @param arg0 时间
     * @param arg1 时间
     * @return 是否相同
     */
    public static boolean isTimestampEqual(Date arg0, Date arg1) {
        return getTimestampString(arg0).compareTo(getTimestampString(arg1)) == 0;
    }

    /**
     *
     *  @Enclosing_Method: nDaysAfterNowDate
     *  @Written by: liuxmi
     *  @Creation Date: May 25, 2010 6:11:01 AM
     *  @version: v1.00
     *  @Description:   当前日期加减n天后的日期
     *  @param n
     *  @return
     *  @return Date
     *
     */
    public static  Date   nDaysAfterNowDate(int   n)   {
        Calendar   rightNow   =   Calendar.getInstance();
        rightNow.add(Calendar.DAY_OF_MONTH,+n);
        return   rightNow.getTime();
    }

    /**
     *
     *  @Enclosing_Method: nDaysAfterOneDateString
     *  @Written by: liuxmi
     *  @Creation Date: May 25, 2010 6:12:37 AM
     *  @version: v1.00
     *  @Description:   给定一个日期型字符串，返回加减n天后的日期型字符串
     *  @param basicDate
     *  @param n
     *  @return
     *  @return String
     *
     */
    public  static String   nDaysAfterOneDateString(String   basicDate,int   n)   {
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
        Date   tmpDate   =   null;
        try   {
            tmpDate   =   df.parse(basicDate);
        }
        catch(Exception   e){
            System.out.println("dateformat:"+e.getMessage());
        }
        long   nDay=(tmpDate.getTime()/(24*60*60*1000)+1+n)*(24*60*60*1000);
        tmpDate.setTime(nDay);

        return   df.format(tmpDate);
    }

    /**
     *
     *  @Enclosing_Method: nDaysAfterOneDate
     *  @Written by: liuxmi
     *  @Creation Date: May 25, 2010 6:13:45 AM
     *  @version: v1.00
     *  @Description:  给定一个日期，返回加减n天后的日期
     *  @param basicDate
     *  @param n
     *  @return
     *  @return Date
     *
     */
    public static   Date   nDaysAfterOneDate(Date   basicDate,int   n)   {
        long   nDay=(basicDate.getTime()/(24*60*60*1000)+n)*(24*60*60*1000);
        basicDate.setTime(nDay);
        return   basicDate;
    }

    /**
     *
     *  @Enclosing_Method: nDaysBetweenTwoDate
     *  @Written by: liuxmi
     *  @Creation Date: May 25, 2010 6:14:10 AM
     *  @version: v1.00
     *  @Description:  计算两个日期相隔的天数
     *  @param firstDate
     *  @param secondDate
     *  @return
     *  @return int
     *
     */
    public static  int   nDaysBetweenTwoDate(Date   firstDate,Date   secondDate)   {
        int   nDay=(int)((secondDate.getTime()-firstDate.getTime())/(24*60*60*1000));
        return   nDay;
    }
    /**
     *
     *  @Enclosing_Method: nYearsBetweenTwoDate
     *  @Written by: liuxmi
     *  @Creation Date: May 25, 2010 6:56:55 AM
     *  @version: v1.00
     *  @Description:  计算两个日期相隔的年数
     *  @param firstDate
     *  @param secondDate
     *  @return
     *  @return int
     *
     */
    public static  int   nYearsBetweenTwoDate(Date   firstDate,Date   secondDate)   {
        int   nYear=nDaysBetweenTwoDate(firstDate, secondDate)/365+1;
        return   nYear;
    }
    /**
     *
     *  @Enclosing_Method: nDaysBetweenTwoDate
     *  @Written by: liuxmi
     *  @Creation Date: May 25, 2010 6:32:15 AM
     *  @version: v1.00
     *  @Description: 计算两个日期相隔的天数
     *  @param firstString
     *  @param secondString
     *  @return
     *  @return int
     *
     */
    public  static  int   nDaysBetweenTwoDate(String   firstString,String   secondString)   {
        SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
        Date   firstDate=null;
        Date   secondDate=null;
        try   {
            firstDate   =   df.parse(firstString);
            secondDate=df.parse(secondString);
        }
        catch(Exception   e)   {
            System.out.println("dateformat:"+e.getMessage());
        }
        int   nDay=(int)((secondDate.getTime()-firstDate.getTime())/(24*60*60*1000));
        return   nDay;
    }
    /**
     *
     *  @Enclosing_Method: getSundayOneDate
     *  @Written by: liuxmi
     *  @Creation Date: May 28, 2010 1:36:06 AM
     *  @version: v1.00
     *  @Description:  获取给定日期所在的周的第一天
     *  @param date
     *  @return void
     *
     */
    public static Date getFirstOfWeekOneDate(Date date){
        int day = DateFunc.getDayOfWeek(date);
        Date sunDay = DateFunc.getRelativeDate(date, DateFunc.DIFF_DAY,-(day-1) );
        return getDate(sunDay);
    }
    /**
     *
     *  @Enclosing_Method: getWeeksOfYear
     *  @Written by: liuxmi
     *  @Creation Date: May 28, 2010 4:02:48 AM
     *  @version: v1.00
     *  @Description:  返回给定日期在有一年中的第几周
     *  @param date
     *  @return
     *  @return int
     *
     */
    public static int getWeeksOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.WEEK_OF_YEAR);
    }
}
