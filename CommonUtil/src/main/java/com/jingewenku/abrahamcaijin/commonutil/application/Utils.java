package com.jingewenku.abrahamcaijin.commonutil.application;

import android.annotation.SuppressLint;
import android.content.Context;

/**
 * @Description:主要功能:初始化在application中获取context
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil.application
 * @author: AbrahamCaiJin
 * @date: 2017年05月19日 14:09
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class Utils {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    private Utils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 初始化工具类
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        Utils.context = context.getApplicationContext();
    }

    /**
     * 获取ApplicationContext
     *
     * @return ApplicationContext
     */
    public static Context getContext() {
        if (context != null) return context;
        throw new NullPointerException("u should init first");
    }
}
