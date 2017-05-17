package com.jingewenku.abrahamcaijin.commonutil;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @Description:主要功能:Toast信息工具
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月15日 11:41
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class ToastUtil {
    /**
     * 将最长使用的显示方法单独提出来，方便使用。
     * 屏幕中心位置短时间显示Toast。
     *
     * @param context
     * @param message
     */
    public static void show(Context context, String message) {
        ToastShortCenter(context,message);
    }

    /**
     * 屏幕底部中间位置显示短时间Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortBottomCenter(Context context, String message) {
        if (context != null) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 屏幕底部左边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortBottomLeft(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕底部右边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortBottomRight(Context context, String message) {

        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕中心位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortCenter(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕中心左边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortCenterLeft(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕中心右边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortCenterRight(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕顶部中心位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortTopCenter(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕顶部左边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortTopLeft(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕顶部右边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastShortTopRight(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕底部中间位置显示长时间Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongBottomCenter(Context context, String message) {
        if (context != null) {

            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 屏幕底部左边位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongBottomLeft(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.LEFT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕底部右边位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongBottomRight(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM | Gravity.RIGHT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕中心位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongCenter(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕中心左边位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongCenterLeft(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.LEFT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕中心右边位置短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongCenterRight(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER | Gravity.RIGHT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕顶部中心位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongTopCenter(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕顶部左边位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongTopLeft(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.LEFT, 0, 0);
            toast.show();
        }
    }

    /**
     * 屏幕顶部右边位置长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void ToastLongTopRight(Context context, String message) {
        if (context != null) {

            Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.TOP | Gravity.RIGHT, 0, 0);
            toast.show();
        }
    }
}
