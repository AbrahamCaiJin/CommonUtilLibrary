package com.jingewenku.abrahamcaijin.commonutil;


import android.content.Context;
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
	 * 自定义Toast调用
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
	 * 自定义Toast调用
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
	
	

	
}
