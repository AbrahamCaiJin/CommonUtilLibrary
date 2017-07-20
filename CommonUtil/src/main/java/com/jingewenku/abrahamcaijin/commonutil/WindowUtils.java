package com.jingewenku.abrahamcaijin.commonutil;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

/**
 * @Description:主要功能:窗口工具箱
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月24日 18:20
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public final class WindowUtils {

    /**
     * Don't let anyone instantiate this class.
     */
    private WindowUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * 获取当前窗口的旋转角度
     *
     * @param activity activity
     * @return  int
     */
    public static int getDisplayRotation(Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }


    /**
     * 当前是否是横屏
     *
     * @param context  context
     * @return  boolean
     */
    public static final boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * 设置Activity为横屏
     * @param activity
     */
    public static void setLandscape(Activity activity){
        if(activity.getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    /**
     * 设置Activity为竖屏
     * @param activity
     */
    public static void setPortrait(Activity activity){
        if(activity.getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){
            activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    /**
     * 当前是否是竖屏
     *
     * @param context  context
     * @return   boolean
     */
    public static final boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * 设置全屏,要在setContentView之前调用
     * @param activity
     */
    public static  void setActivityFullScreen(Activity activity){
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
    }

    /**
     *  调整窗口的透明度  1.0f,0.5f 变暗
     * @param from  from>=0&&from<=1.0f
     * @param to  to>=0&&to<=1.0f
     * @param context  当前的activity
     */
    public static void dimBackground(final float from, final float to, Activity context) {
        final Window window = context.getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(
            new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    WindowManager.LayoutParams params
                        = window.getAttributes();
                    params.alpha = (Float) animation.getAnimatedValue();
                    window.setAttributes(params);
                }
            });
        valueAnimator.start();
    }
}
