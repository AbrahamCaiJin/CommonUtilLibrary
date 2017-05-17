package com.jingewenku.abrahamcaijin.commonutil;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * @Description:主要功能:Intent管理类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月15日 11:52
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class IntentUtil {
    private static final String TAG = IntentUtil.class.getSimpleName();
    private static Intent intent;
    private static final Object lock = new Object();

    /**
     *
     * @Title: startActivity
     */
    public static <T> void doAction(Activity activity, Class<T> class1) {
        synchronized (lock) {
            try {
                intent = new Intent(activity, class1);
                activity.startActivity(intent);
                // activity.overridePendingTransition(R.anim.zoom_enter,
                // R.anim.zoom_exit);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @Title: startActivity
     */
    public static <T> void doAction(Activity activity, Class<T> class1,
        String key, Object object) {
        synchronized (lock) {
            try {
                intent = new Intent(activity, class1);
                if (object != null && object instanceof String) {
                    intent.putExtra(key, (String) object);
                } else if (object != null && object instanceof Integer) {
                    intent.putExtra(key, (Integer) object);
                }
                activity.startActivity(intent);
                // activity.overridePendingTransition(R.anim.zoom_enter,
                // R.anim.zoom_exit);
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @Title: startActivity
     */
    public static <T> void doAction(Activity activity, Class<T> class1,
        HashMap<String, Serializable> map) {
        synchronized (lock) {
            try {
                intent = new Intent(activity, class1);
                if (null != map) {
                    Set<String> keys = map.keySet();
                    for (String key : keys) {
                        intent.putExtra(key, map.get(key));
                    }
                    activity.startActivity(intent);
                    // activity.overridePendingTransition(R.anim.zoom_enter,
                    // R.anim.zoom_exit);
                }
            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     * @Title: startActivity
     */
    public static <T> void doAction(Activity activity, Class<T> class1,
        Bundle bundle) {
        synchronized (lock) {
            try {
                intent = new Intent(activity, class1);
                if (bundle != null) {
                    intent.putExtras(bundle);
                }

                activity.startActivity(intent);
                // activity.overridePendingTransition(R.anim.zoom_enter,
                // R.anim.zoom_exit);

            } catch (ActivityNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
    public static Intent getLauncherIntent() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_HOME);
        return intent;
    }
    public static void logIntent(String tag, Intent intent) {
        if (intent == null) {
            return;
        }
        StringBuffer sb = new StringBuffer();
        sb.append("\nAction:" + intent.getAction());
        sb.append("\nData:" + intent.getData());
        sb.append("\nDataStr:" + intent.getDataString());
        sb.append("\nScheme:" + intent.getScheme());
        sb.append("\nType:" + intent.getType());
        Bundle extras = intent.getExtras();
        if (extras != null && !extras.isEmpty()) {
            for (String key : extras.keySet()) {
                Object value = extras.get(key);
                sb.append("\nEXTRA: {" + key + "::" + value + "}");
            }
        } else {
            sb.append("\nNO EXTRAS");
        }
        Log.i(tag, sb.toString());
    }

    public static int sdkVersion() {
        return new Integer(Build.VERSION.SDK).intValue();
    }

    public static void startDialer(Context context, String phoneNumber) {
        try {
            Intent dial = new Intent();
            dial.setAction(Intent.ACTION_DIAL);
            dial.setData(Uri.parse("tel:" + phoneNumber));
            context.startActivity(dial);
        } catch (Exception ex) {
            Log.e(TAG, "Error starting phone dialer intent.", ex);
            Toast.makeText(context,
                "Sorry, we couldn't find any app to place a phone call!",
                Toast.LENGTH_SHORT).show();
        }
    }

    public static void startSmsIntent(Context context, String phoneNumber) {
        try {
            Uri uri = Uri.parse("sms:" + phoneNumber);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.putExtra("address", phoneNumber);
            intent.setType("vnd.android-dir/mms-sms");
            context.startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Error starting sms intent.", ex);
            Toast.makeText(context,
                "Sorry, we couldn't find any app to send an SMS!",
                Toast.LENGTH_SHORT).show();
        }
    }

    public static void startEmailIntent(Context context, String emailAddress) {
        try {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("plain/text");
            intent.putExtra(Intent.EXTRA_EMAIL,
                new String[]{emailAddress});
            context.startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Error starting email intent.", ex);
            Toast.makeText(context,
                "Sorry, we couldn't find any app for sending emails!",
                Toast.LENGTH_SHORT).show();
        }
    }

    public static void startWebIntent(Context context, String url) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);
        } catch (Exception ex) {
            Log.e(TAG, "Error starting url intent.", ex);
            Toast.makeText(context,
                "Sorry, we couldn't find any app for viewing this url!",
                Toast.LENGTH_SHORT).show();
        }
    }
}
