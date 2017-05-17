package com.jingewenku.abrahamcaijin.commonutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:27
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class DateUtils {

    private DateUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
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

}
