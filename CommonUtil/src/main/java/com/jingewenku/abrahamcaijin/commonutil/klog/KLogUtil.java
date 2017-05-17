package com.jingewenku.abrahamcaijin.commonutil.klog;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil.klog
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 16:54
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by zhaokaiqiang on 15/12/11.
 */
public class KLogUtil {

    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.d(tag, "╔═══════════════════════════════════════════════════════════════════════════════════════");
        } else {
            Log.d(tag, "╚═══════════════════════════════════════════════════════════════════════════════════════");
        }
    }

}