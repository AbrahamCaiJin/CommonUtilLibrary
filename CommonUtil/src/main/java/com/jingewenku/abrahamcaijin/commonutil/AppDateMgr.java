package com.jingewenku.abrahamcaijin.commonutil;


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
    public static final SimpleDateFormat YYYYMMDD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public static final SimpleDateFormat HHMMSS_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
    public static final SimpleDateFormat YYYYMMDDHHMMSS_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    private static final String[] CHINESE_ZODIAC = new String[]{"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final String[] ZODIAC = new String[]{"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};
    private static final int[] ZODIAC_FLAGS = new int[]{20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};

    public AppDateMgr() {
    }

    public static String todayYyyyMmDd() {
        return YYYYMMDD_FORMAT.format(new Date());
    }

    public static String todayHhMmSs() {
        return HHMMSS_FORMAT.format(new Date());
    }

    public static String todayYyyyMmDdHhMmSs() {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date());
    }

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

    public static int parseYyyy(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(1);
    }

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

    public static int parseMm(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(2);
    }

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

    public static int parseDd(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(5);
    }

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

    public static String parseYyyyMmDd(Date date) {
        return YYYYMMDD_FORMAT.format(date);
    }

    public static String parseHhMmSs(String dateTime) {
        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            return HHMMSS_FORMAT.format(e);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return "";
        }
    }

    public static String parseHhMmSs(String dateTime, SimpleDateFormat simpleDateFormat) {
        try {
            Date e = simpleDateFormat.parse(dateTime);
            return HHMMSS_FORMAT.format(e);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return "";
        }
    }

    public static String parseHhMmSs(Date date) {
        return HHMMSS_FORMAT.format(date);
    }

    public static int getWeekNumber(String dateTime) {
        return getWeekNumber(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    public static int getWeekNumber(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekNumber(string2Date(dateTime, simpleDateFormat));
    }

    public static int getWeekNumber(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(7);
    }

    public static int getWeekOfMonth(String dateTime) {
        return getWeekOfMonth(string2Date(dateTime, YYYYMMDDHHMMSS_FORMAT));
    }

    public static int getWeekOfMonth(String dateTime, SimpleDateFormat simpleDateFormat) {
        return getWeekOfMonth(string2Date(dateTime, simpleDateFormat));
    }

    public static int getWeekOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(4);
    }

    public static int getWeekOfYear(String time) {
        return getWeekOfYear(string2Date(time, YYYYMMDDHHMMSS_FORMAT));
    }

    public static int getWeekOfYear(String time, SimpleDateFormat simpleDateFormat) {
        return getWeekOfYear(string2Date(time, simpleDateFormat));
    }

    public static int getWeekOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(3);
    }

    public static Long dateTimeToTimeStamp(String dateTime) {
        try {
            Date e = YYYYMMDDHHMMSS_FORMAT.parse(dateTime);
            return Long.valueOf(e.getTime() / 1000L);
        } catch (ParseException var2) {
            var2.printStackTrace();
            return Long.valueOf(0L);
        }
    }

    public static String timeStampToDateTime(Long timeStamp) {
        return YYYYMMDDHHMMSS_FORMAT.format(new Date(timeStamp.longValue() * 1000L));
    }

    public static Date string2Date(String time) {
        return string2Date(time, YYYYMMDDHHMMSS_FORMAT);
    }

    public static Date string2Date(String time, SimpleDateFormat simpleDateFormat) {
        try {
            return simpleDateFormat.parse(time);
        } catch (ParseException var3) {
            var3.printStackTrace();
            return null;
        }
    }

    public static String date2String(Date date) {
        return date2String(date, YYYYMMDDHHMMSS_FORMAT);
    }

    public static String date2String(Date date, SimpleDateFormat simpleDateFormat) {
        return simpleDateFormat.format(date);
    }

    public static boolean dateIsBefore(String standDate, String desDate) {
        try {
            return YYYYMMDDHHMMSS_FORMAT.parse(desDate).before(YYYYMMDDHHMMSS_FORMAT.parse(standDate));
        } catch (ParseException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    public static long minutesBetweenTwoDate(String beginDate, String endDate) {
        long millisBegin = dateTimeToTimeStamp(beginDate).longValue();
        long millisEnd = dateTimeToTimeStamp(endDate).longValue();
        return (millisEnd - millisBegin) / 60L;
    }

    public static String getChineseZodiac(String dateTime) {
        int yyyy = parseYyyy(dateTime);
        return getChineseZodiac(yyyy);
    }

    public static String getChineseZodiac(String dateTime, SimpleDateFormat simpleDateFormat) {
        int yyyy = parseYyyy(dateTime, simpleDateFormat);
        return getChineseZodiac(yyyy);
    }

    public static String getChineseZodiac(Date date) {
        int yyyy = parseYyyy(date);
        return getChineseZodiac(yyyy);
    }

    public static String getChineseZodiac(int year) {
        return CHINESE_ZODIAC[year % 12];
    }

    public static String getZodiac(String dateTime) {
        int dd = parseDd(dateTime);
        int month = parseMm(dateTime);
        return getZodiac(month, dd);
    }

    public static String getZodiac(String dateTime, SimpleDateFormat simpleDateFormat) {
        int dd = parseDd(dateTime, simpleDateFormat);
        int month = parseMm(dateTime, simpleDateFormat);
        return getZodiac(month, dd);
    }

    public static String getZodiac(Date date) {
        int dd = parseDd(date);
        int month = parseMm(date);
        return getZodiac(month, dd);
    }

    public static String getZodiac(int month, int day) {
        return ZODIAC[day >= ZODIAC_FLAGS[month - 1]?month - 1:(month + 10) % 12];
    }
}
