package com.jingewenku.abrahamcaijin.commonutil;


import com.jingewenku.abrahamcaijin.commonutil.klog.KLog;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @Description:主要功能:时间日期管理
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: june
 * @date: 2017年05月05日 14:18
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AppDateMgr {

    public AppDateMgr() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final String[] CHINESE_ZODIAC = new String[]{"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final String[] ZODIAC = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    private static final int[] ZODIAC_FLAGS = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};

    /**
     * 当天的年月日
     * @return
     */
    public static String todayYyyyMmDd() {
        return YYYYMMDD_FORMAT.format(new Date());
    }

    /**
     * 当天的时分秒
     * @return
     */
    public static String todayHhMmSs() {
        return HHMMSS_FORMAT.format(new Date());
    }

    /**
     * 当天的年月日时分秒
     * @return
     */
    public static String todayYyyyMmDdHhMmSs() {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date());
    }

    /**
     * 获取年
     * @param dateTime
     * @return
     */
    public static int parseYyyy(String dateTime) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            e.setTime(date);
            return e.get(1);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取年
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int parseYyyy(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = simpleDateFormat.parse(dateTime);
            e.setTime(date);
            return e.get(1);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取年
     * @param date
     * @return
     */
    public static int parseYyyy(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

    /**
     * 获取月
     * @param dateTime
     * @return
     */
    public static int parseMm(String dateTime) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            e.setTime(date);
            return e.get(2);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取月
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int parseMm(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = simpleDateFormat.parse(dateTime);
            e.setTime(date);
            return e.get(2);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取月
     * @param date
     * @return
     */
    public static int parseMm(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2);
    }

    /**
     * 获取日
     * @param dateTime
     * @return
     */
    public static int parseDd(String dateTime) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            e.setTime(date);
            return e.get(5);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取日
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int parseDd(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Calendar e = Calendar.getInstance();
            Date date = simpleDateFormat.parse(dateTime);
            e.setTime(date);
            return e.get(5);
        } catch (ParseException var4) {
            var4.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取日
     * @param date
     * @return
     */
    public static int parseDd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

    /**
     * 获取年月日
     * @param dateTime
     * @return
     */
    public static String parseYyyyMmDd(String dateTime) {
        String result = "";

        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            result = YYYYMMDD_FORMAT.format(e);
        } catch (ParseException var3) {
            var3.printStackTrace();
        }

        return result;
    }

    /**
     * 获取年月日
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static String parseYyyyMmDd(String dateTime, SimpleDateFormat simpleDateFormat) {
        String result = "";

        try {
            Date e = simpleDateFormat.parse(dateTime);
            result = YYYYMMDD_FORMAT.format(e);
        } catch (ParseException var4) {
            var4.printStackTrace();
        }

        return result;
    }

    /**
     * 获取年月日
     * @param date
     * @return
     */
    public static String parseYyyyMmDd(Date date) {
        return YYYYMMDD_FORMAT.format(date);
    }

    /**
     * 时分秒
     * @param dateTime
     * @return
     */
    public static String parseHhMmSs(String dateTime) {
        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            return HHMMSS_FORMAT.format(e);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    /**
     * 时分秒
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static String parseHhMmSs(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Date e = simpleDateFormat.parse(dateTime);
            return HHMMSS_FORMAT.format(e);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return "";
        }
    }

    /**
     * 时分秒
     * @param date
     * @return
     */
    public static String parseHhMmSs(Date date) {
        return HHMMSS_FORMAT.format(date);
    }

    /**
     * 获取星期几
     * @param dateTime
     * @return
     */
    public static int getWeekNumber(String dateTime) {
        return getWeekNumber(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    /**
     * 获取星期几
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int getWeekNumber(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekNumber(string2Date(dateTime, simpleDateFormat));
    }

    /**
     * 获取星期几
     * @param date
     * @return
     */
    public static int getWeekNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    /**
     * 日期中某个月份的第几周
     * @param dateTime
     * @return
     */
    public static int getWeekOfMonth(String dateTime) {
        return getWeekOfMonth(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    /**
     * 日期中某个月份的第几周
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static int getWeekOfMonth(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekOfMonth(string2Date(dateTime, simpleDateFormat));
    }

    /**
     * 日期中某个月份的第几周
     * @param date
     * @return
     */
    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(4);
    }

    /**
     * 日期中某个年份的第几周
     * @param time
     * @return
     */
    public static int getWeekOfYear(String time) {
        return getWeekOfYear(string2Date(time, YYYYMMDDHHMMSS_FORMAT));
    }

    /**
     * 日期中某个年份的第几周
     * @param time
     * @param simpleDateFormat
     * @return
     */
    public static int getWeekOfYear(String time, SimpleDateFormat simpleDateFormat) {
        return getWeekOfYear(string2Date(time, simpleDateFormat));
    }

    /**
     * 日期中某个年份的第几周
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(3);
    }

    /**
     * 将年月日时分秒转成Long类型
     * @param dateTime
     * @return
     */
    public static Long dateTimeToTimeStamp(String dateTime) {
        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            return Long.valueOf(e.getTime() / 1000L);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return Long.valueOf(0L);
        }
    }

    /**
     * 将Long类型转成年月日时分秒
     * @param timeStamp
     * @return
     */
    public static String timeStampToDateTime(Long timeStamp) {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date(timeStamp.longValue() * 1000L));
    }

    /**
     * 将年月日时分秒转成Date类型
     * @param time
     * @return
     */
    public static Date string2Date(String time) {
        return string2Date(time, YYYYMMDDHHMMSS_FORMAT);
    }

    /**
     * 将年月日时分秒转成Date类型
     * @param time
     * @param simpleDateFormat
     * @return
     */
    public static Date string2Date(String time, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    /**
     * 将Date类型转成年月日时分秒
     * @param date
     * @return
     */
    public static String date2String(Date date) {
        return date2String(date, YYYYMMDDHHMMSS_FORMAT);
    }

    /**
     * 将Date类型转成年月日时分秒
     * @param date
     * @param simpleDateFormat
     * @return
     */
    public static String date2String(Date date, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(date);
    }

    /**
     * 比较日期
     * @param standDate
     * @param desDate
     * @return
     */
    public static boolean dateIsBefore(String standDate, String desDate) {
        try {
            return YYYYMMDDHHMMSS_FORMAT.parse(desDate).before(YYYYMMDDHHMMSS_FORMAT.parse(standDate));
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    /**
     * 相差多少分钟
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long minutesBetweenTwoDate(String beginDate, String endDate) {
        long millisBegin = dateTimeToTimeStamp(beginDate).longValue();
        long millisEnd = dateTimeToTimeStamp(endDate).longValue();
        return (millisEnd - millisBegin) / 60L;
    }

    /**
     * 获取日期中的生肖
     * @param dateTime
     * @return
     */
    public static String getChineseZodiac(String dateTime) {
        int yyyy = parseYyyy(dateTime);
        return getChineseZodiac(yyyy);
    }

    /**
     * 获取日期中的生肖
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static String getChineseZodiac(String dateTime, SimpleDateFormat simpleDateFormat) {
        int yyyy = parseYyyy(dateTime, simpleDateFormat);
        return getChineseZodiac(yyyy);
    }

    /**
     * 获取日期中的生肖
     * @param date
     * @return
     */
    public static String getChineseZodiac(Date date) {
        int yyyy = parseYyyy(date);
        return getChineseZodiac(yyyy);
    }

    /**
     * 获取日期中的生肖
     * @param year
     * @return
     */
    public static String getChineseZodiac(int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    /**
     * 获取日期中的星座
     * @param dateTime
     * @return
     */
    public static String getZodiac(String dateTime) {
        int dd = parseDd(dateTime);
        int month = parseMm(dateTime);
        return getZodiac(month, dd);
    }

    /**
     * 获取日期中的星座
     * @param dateTime
     * @param simpleDateFormat
     * @return
     */
    public static String getZodiac(String dateTime, SimpleDateFormat simpleDateFormat) {
        int dd = parseDd(dateTime, simpleDateFormat);
        int month = parseMm(dateTime, simpleDateFormat);
        return getZodiac(month, dd);
    }

    /**
     * 获取日期中的星座
     * @param date
     * @return
     */
    public static String getZodiac(Date date) {
        int dd = parseDd(date);
        int month = parseMm(date);
        return getZodiac(month, dd);
    }

    /**
     * 获取日期中的星座
     * @param month
     * @param day
     * @return
     */
    public static String getZodiac(int month, int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1]?month - 1:(month + 10) % 12];
    }

    /**
     * 获取日期
     *
     * @param offset 表示偏移天数
     * @return
     */
    public String getNowDayOffset(int offset) {
        Calendar m_Calendar = Calendar.getInstance();
        long time = (long) m_Calendar.getTimeInMillis();
        time = time + offset * 24 * 3600 * 1000;
        Date myDate = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(myDate);
    }

    /**
     * 获取日期
     *
     * @param
     * @return
     */
    public String getTime(long time) {
        Date myDate = new Date(time);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return df.format(myDate);
    }

    /**
     * 使指定日期向前走一天，变成“明天”的日期
     *
     * @param cal 等处理日期
     */
    public void forward(Calendar cal) {
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);//0到11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int days = getDaysOfMonth(year, month + 1);
        if (day == days) {//如果是本月最后一天，还要判断年份是不是要向前滚
            if (month == 11) {//如果是12月份，年份要向前滚
                cal.roll(Calendar.YEAR, true);
                cal.set(Calendar.MONTH, 0);//月份，第一月是0
                cal.set(Calendar.DAY_OF_MONTH, 1);

            } else {//如果不是12月份
                cal.roll(Calendar.MONTH, true);
                cal.set(Calendar.DAY_OF_MONTH, 1);
            }

        } else {
            cal.roll(Calendar.DAY_OF_MONTH, 1);//如果是月内，直接天数加1
        }
    }

    /**
     * 使日期倒一天
     *
     * @param cal
     */
    public void backward(Calendar cal) {
        //计算上一月有多少天
        int month = cal.get(Calendar.MONTH);//0到11
        int year = cal.get(Calendar.YEAR);
        int days = getDaysOfMonth(year, month);//上个月的天数
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day == 1) {//如果是本月第一天，倒回上一月
            if (month == 0) {//如果是本年第一个月，年份倒一天
                cal.roll(Calendar.YEAR, false);
                cal.set(Calendar.MONTH, 11);//去年最后一个月是12月
                cal.set(Calendar.DAY_OF_MONTH, 31);//12月最后一天总是31号
            } else {//月份向前
                cal.roll(Calendar.MONTH, false);
                cal.set(Calendar.DAY_OF_MONTH, days);//上个月最后一天
            }
        } else {
            cal.roll(Calendar.DAY_OF_MONTH, false);//如果是月内，日期倒一天
        }
    }

    /**
     * 判断平年闰年
     *
     * @param year
     * @return true表示闰年，false表示平年
     */
    public boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 != 0 && year % 4 == 0) {
            return true;
        }
        return false;
    }

    /**
     * 计算某月的天数
     *
     * @param year
     * @param month 现实生活中的月份，不是系统存储的月份，从1到12
     * @return
     */

    public int getDaysOfMonth(int year, int month) {
        if (month < 1 || month > 12) {
            return 0;
        }
        boolean isLeapYear = isLeapYear(year);
        int daysOfMonth = 0;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                daysOfMonth = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                daysOfMonth = 30;
                break;
            case 2:
                if (isLeapYear) {
                    daysOfMonth = 29;
                } else {
                    daysOfMonth = 28;
                }

        }
        return daysOfMonth;
    }

    /**
     * 获取当天凌晨的秒数
     *
     * @return
     */
    public long secondsMorning(Calendar c) {
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return tempCalendar.getTimeInMillis();
    }

    /**
     * 获取第二天凌晨的秒数
     *
     * @return
     */
    public long secondsNight(Calendar c) {
        Calendar tempCalendar = Calendar.getInstance();
        tempCalendar.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        forward(tempCalendar);
        return tempCalendar.getTimeInMillis();
    }

    /**
     * 判断某两天是不是同一天
     *
     * @param c1
     * @param c2
     * @return
     */
    public boolean isSameDay(Calendar c1, Calendar c2) {

        if (c1.get(Calendar.YEAR) != c2.get(Calendar.YEAR))
            return false;
        if (c1.get(Calendar.MONTH) != c2.get(Calendar.MONTH))
            return false;
        if (c1.get(Calendar.DAY_OF_MONTH) != c2.get(Calendar.DAY_OF_MONTH))
            return false;
        return true;
    }

    /** 日期格式：yyyy-MM-dd HH:mm:ss **/
    public static final String DF_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /** 日期格式：yyyy-MM-dd HH:mm **/
    public static final String DF_YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /** 日期格式：yyyy-MM-dd **/
    public static final String DF_YYYY_MM_DD = "yyyy-MM-dd";

    /** 日期格式：HH:mm:ss **/
    public static final String DF_HH_MM_SS = "HH:mm:ss";

    /** 日期格式：HH:mm **/
    public static final String DF_HH_MM = "HH:mm";

    private final static long MINUTE = 60 * 1000;// 1分钟
    private final static long HOUR = 60 * MINUTE;// 1小时
    private final static long DAY = 24 * HOUR;// 1天
    private final static long MONTH = 31 * DAY;// 月
    private final static long YEAR = 12 * MONTH;// 年

    /** Log输出标识 **/
    private static final String TAG = AppDateMgr.class.getSimpleName();

    /**
     * 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
     *
     * @param date
     * @return
     */
    public static String formatFriendly(Date date) {
        if (date == null) {
            return null;
        }
        long diff = new Date().getTime() - date.getTime();
        long r = 0;
        if (diff > YEAR) {
            r = (diff / YEAR);
            return r + "年前";
        }
        if (diff > MONTH) {
            r = (diff / MONTH);
            return r + "个月前";
        }
        if (diff > DAY) {
            r = (diff / DAY);
            return r + "天前";
        }
        if (diff > HOUR) {
            r = (diff / HOUR);
            return r + "个小时前";
        }
        if (diff > MINUTE) {
            r = (diff / MINUTE);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL
     *            日期
     * @return
     */
    public static String formatDateTime(long dateL) {
        SimpleDateFormat sdf = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        Date date = new Date(dateL);
        return sdf.format(date);
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param dateL
     *            日期
     * @return
     */
    public static String formatDateTime(long dateL, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(new Date(dateL));
    }

    /**
     * 将日期以yyyy-MM-dd HH:mm:ss格式化
     *
     * @param formater
     *            日期
     * @return
     */
    public static String formatDateTime(Date date, String formater) {
        SimpleDateFormat sdf = new SimpleDateFormat(formater);
        return sdf.format(date);
    }

    /**
     * 将日期字符串转成日期
     *
     * @param strDate
     *            字符串日期
     * @return java.util.date日期类型
     */

    public static Date parseDate(String strDate) {
        DateFormat dateFormat = new SimpleDateFormat(DF_YYYY_MM_DD_HH_MM_SS);
        Date returnDate = null;
        try {
            returnDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
            KLog.v(TAG, "parseDate failed !");

        }
        return returnDate;

    }

    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static Date gainCurrentDate() {
        return new Date();
    }

    /**
     * 验证日期是否比当前日期早
     *
     * @param target1
     *            比较时间1
     * @param target2
     *            比较时间2
     * @return true 则代表target1比target2晚或等于target2，否则比target2早
     */
    public static boolean compareDate(Date target1, Date target2) {
        boolean flag = false;
        try {
            String target1DateTime = AppDateMgr.formatDateTime(target1,
                DF_YYYY_MM_DD_HH_MM_SS);
            String target2DateTime = AppDateMgr.formatDateTime(target2,
                DF_YYYY_MM_DD_HH_MM_SS);
            if (target1DateTime.compareTo(target2DateTime) <= 0) {
                flag = true;
            }
        } catch (Exception e1) {
            KLog.e("比较失败，原因：" + e1.getMessage());
        }
        return flag;
    }

    /**
     * 对日期进行增加操作
     *
     * @param target
     *            需要进行运算的日期
     * @param hour
     *            小时
     * @return
     */
    public static Date addDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }

        return new Date(target.getTime() + (long) (hour * 60 * 60 * 1000));
    }

    /**
     * 对日期进行相减操作
     *
     * @param target
     *            需要进行运算的日期
     * @param hour
     *            小时
     * @return
     */
    public static Date subDateTime(Date target, double hour) {
        if (null == target || hour < 0) {
            return target;
        }

        return new Date(target.getTime() - (long) (hour * 60 * 60 * 1000));
    }
    private static SimpleDateFormat second = new SimpleDateFormat(
        "yy-MM-dd hh:mm:ss");

    private static SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
    private static SimpleDateFormat detailDay = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
    private static SimpleDateFormat tempTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat excelDate = new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 格式化excel中的时间
     * @param date
     * @return
     */
    public static String formatDateForExcelDate(Date date) {
        return excelDate.format(date);
    }

    /**
     * 将日期格式化作为文件名
     * @param date
     * @return
     */
    public static String formatDateForFileName(Date date) {
        return fileName.format(date);
    }

    /**
     * 格式化日期(精确到秒)
     *
     * @param date
     * @return
     */
    public static String formatDateSecond(Date date) {
        return second.format(date);
    }

    /**
     * 格式化日期(精确到秒)
     *
     * @param date
     * @return
     */
    public static String tempDateSecond(Date date) {
        return tempTime.format(date);
    }

    /**
     * 格式化日期(精确到秒)
     *
     * @param str
     * @return
     */
    public static Date tempDateSecond(String str) {
        try {
            return tempTime.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }
    /**
     * 格式化日期(精确到天)
     *
     * @param date
     * @return
     */
    public static String formatDateDay(Date date) {
        return day.format(date);
    }

    /**
     * 格式化日期(精确到天)
     *
     * @param date
     * @return
     */
    public static String formatDateDetailDay(Date date) {
        return detailDay.format(date);
    }

    /**
     * 将double类型的数字保留两位小数（四舍五入）
     *
     * @param number
     * @return
     */
    public static String formatNumber(double number) {
        DecimalFormat df = new DecimalFormat();
        df.applyPattern("#0.00");
        return df.format(number);
    }

    /**
     * 将字符串转换成日期
     *
     * @param date
     * @return
     * @throws Exception
     */
    public static Date formateDate(String date) throws Exception {
        return day.parse(date);
    }

    /**
     * 将字符日期转换成Date
     * @param date
     * @return
     * @throws Exception
     */
    public static Date parseStringToDate(String date) throws Exception {
        return day.parse(date);
    }

    /**
     * 将double日期转换成String
     * @param number
     * @return
     */
    public static String formatDoubleNumber(double number) {
        DecimalFormat df = new DecimalFormat("#");
        return df.format(number);
    }

    /**
     * 获得指定Date类型的毫秒数
     * @param date 指定的Date
     * @return 指定Date类型的毫秒数
     */
    public static long getTimeMillis(Date date){
        return date.getTime();
    }

    /**
     * 获得当前时间的毫秒数
     * @return 当前时间的毫秒数
     */
    public static long getCurrentDayTimeMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 将格式化过的时间串转换成毫秒
     * @param day 将格式化过的时间
     * @param format 格式化字符串
     * @return 毫秒
     */
    public static long convertMillisecond(String day, String format) {
        if (day == null || format == null)
            return 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        try {
            Date dt = formatter.parse(day);
            return dt.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 得到两个日期的天数
     * @param sdate1 日期一
     * @param sdate2 日期二
     * @return 天数
     */
    public static int getDateInterval(String sdate1, String sdate2) {
        Date date1 = null;
        Date date2 = null;
        long betweenDays=0;

        try {
            date1 = new SimpleDateFormat("yyyy-MM-dd").parse(sdate1);
            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(sdate2);

            long beginTime = date1.getTime();
            long endTime = date2.getTime();
            betweenDays = (long) ((endTime - beginTime) / (1000 * 60 * 60 * 24));

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (int) betweenDays;
    }

    /**
     * 时间比较
     * @param format 格式化字符串
     * @param time1 时间1
     * @param time2 时间2
     * @return time1比time2早返回-1,time1与time2相同返回0,time1比time2晚返回1
     */
    public static int compareTime(String format, String time1, String time2) {
        if (format == null || time1 == null || time2 == null)
            return 0;
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        try {
            c1.setTime(formatter.parse(time1));
            c2.setTime(formatter.parse(time2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return c1.compareTo(c2);
    }

//    /**
//     * 身份证号转生日
//     *
//     * @param identityCard 身份证
//     * @return 生日
//     */
//    public static Date identityCard2Date(String identityCard) {
//        try {
//            String dateStr;
//            if (identityCard.length() == 18) {
//                dateStr = identityCard.substring(6, 14);// 截取18位身份证身份证中生日部分
//                return formatDateString(dateStr, "yyyyMMdd");
//            }
//            if (identityCard.length() == 15) {
//                dateStr = identityCard.substring(6, 12);// 截取15位身份证中生日部分
//                return formatDateString(dateStr, "yyMMdd");
//            }
//            return null;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
//    /**
//     * 格式化日期时间字符串
//     *
//     * @param dateString 日期时间字符串
//     * @param pattern    模式
//     * @return Date对象
//     */
//    public static Date formatDateString(String dateString, String pattern) {
//        try {
//            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(pattern);
//            return dateTimeFormatter.parseDateTime(dateString).toDate();
//        } catch (Exception e) {
//            return null;
//        }
//    }




}
