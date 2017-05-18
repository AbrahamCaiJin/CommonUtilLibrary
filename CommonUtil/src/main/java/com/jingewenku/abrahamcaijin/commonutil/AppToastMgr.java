package com.jingewenku.abrahamcaijin.commonutil;


import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * 主要功能： 自定义Toast提示框
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月04日 14:13
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
public class AppToastMgr {

	
	//对话框时长号(毫秒)
	private static int duration = 200;
	
	//自定义toast对象
	private static Toast toast;

	/**
	 * 自定义短Toast调用
	 * @param context 上下文
	 * @param message 显示文本
	 * @return void   
	 */
	public static void shortToast(final Context context, final String message) {
		if (null == toast) {
			toast = new Toast(context);
			toast.setDuration(Toast.LENGTH_SHORT);
			View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.sys_show_toast, null);
			TextView textView = (TextView) view.findViewById(R.id.sys_show_toast_txt);
			textView.setText(message);
			toast.setView(view);
			toast.show();
		} else {
			TextView textView = (TextView) toast.getView().findViewById(R.id.sys_show_toast_txt);
			textView.setText(message);
			toast.show();
		}
	}
	

	/**
	 * 自定义长Toast调用
	 * @param context 上下文
	 * @param message 显示文本
	 * @return void   
	 */
	public static void longToast(final Context context, final String message) {
		if (null == toast) {
			toast = new Toast(context);
			toast.setDuration(Toast.LENGTH_LONG);
			View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.sys_show_toast, null);
			TextView textView = (TextView) view.findViewById(R.id.sys_show_toast_txt);
			textView.setText(message);
			toast.setView(view);
			toast.show();
		} else {
			TextView textView = (TextView) toast.getView().findViewById(R.id.sys_show_toast_txt);
			textView.setText(message);
			toast.show();
		}
	}
	
	/**
	 * 取消显示Toast
	 * 
	 */
	public static void cancelToast() {
		if (null != toast) {
			toast.cancel();
		}
	}
	
	/**
	 * 默认Toast调用
	 * @param context 上下文
	 * @param message 显示文本
	 */
	public static void Toast(final Context context, final String message) {
		Toast.makeText(context, message, duration).show();
	}

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
