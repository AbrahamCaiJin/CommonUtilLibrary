package com.jingewenku.abrahamcaijin.commonutil;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 主要功能:管理Activity跳转
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AppActivitySkipUtil {

    public AppActivitySkipUtil() {
        throw new UnsupportedOperationException("ActivitySkipUtil不能实例化");
    }

    /**
     * 功能描述:简单地Activity的跳转(不携带任何数据)
     *
     * @param activity
     *            发起跳转的Activity实例
     * @param TargetActivity
     *            目标Activity实例
     */
    public static void skipAnotherActivity(Activity activity,
        Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * 功能描述：带数据的Activity之间的跳转
     *
     * @param activity
     * @param cls
     * @param hashMap
     */
    public static void skipAnotherActivity(Activity activity,
        Class<? extends Activity> cls,
        HashMap<String, ? extends Object> hashMap) {
        Intent intent = new Intent(activity, cls);
        Iterator<?> iterator = hashMap.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("unchecked")
            Entry<String, Object> entry = (Entry<String, Object>) iterator
                .next();
            String key = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof String) {
                intent.putExtra(key, (String) value);
            }
            if (value instanceof Boolean) {
                intent.putExtra(key, (boolean) value);
            }
            if (value instanceof Integer) {
                intent.putExtra(key, (int) value);
            }
            if (value instanceof Float) {
                intent.putExtra(key, (float) value);
            }
            if (value instanceof Double) {
                intent.putExtra(key, (double) value);
            }
        }
        activity.startActivity(intent);
    }

//    public static void start_activity(Activity activity,Class<?> cls,BasicNameValuePair...name)
//    {
//        Intent intent=new Intent();
//        intent.setClass(activity,cls);
//        for(int i=0;i<name.length;i++)
//        {
//            intent.putExtra(name[i].getName(), name[i].getValue());
//        }
//        activity.startActivity(intent);
//        activity.overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//    }

    private static final String TAG = AppActivitySkipUtil.class.getSimpleName();
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

}
