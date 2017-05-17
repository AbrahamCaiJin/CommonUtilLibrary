package com.jingewenku.abrahamcaijin.commonutil.klog.base;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil.klog.base
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 16:56
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import android.util.Log;
import com.jingewenku.abrahamcaijin.commonutil.klog.KLogUtil;
import com.socks.library.KLog;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zhaokaiqiang on 15/11/18.
 */
public class JsonLog {

    public static void printJson(String tag, String msg, String headString) {

        String message;

        try {
            if (msg.startsWith("{")) {
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(KLog.JSON_INDENT);
            } else if (msg.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(KLog.JSON_INDENT);
            } else {
                message = msg;
            }
        } catch (JSONException e) {
            message = msg;
        }

        KLogUtil.printLine(tag, true);
        message = headString + KLog.LINE_SEPARATOR + message;
        String[] lines = message.split(KLog.LINE_SEPARATOR);
        for (String line : lines) {
            Log.d(tag, "║ " + line);
        }
        KLogUtil.printLine(tag, false);
    }
}
