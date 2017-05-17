package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月15日 11:47
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import com.socks.library.KLog;

/**
 * 基于静态内部类实现的单例，保证线程安全的网络信息工具类 <per> 使用该工具类之前，记得在AndroidManifest.xml添加权限许可 <xmp>
 * <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
 * </xmp> </per>
 *
 * 安卓判断网络状态，只需要在相应的Activity的相关方法（onCreat/onResum）调用一行代码即可
 * NetWorkUtils.getInstance(getActivity()).validateNetWork();
 *
 */
public class ToolNetwork {
    public final static String NETWORK_CMNET = "CMNET";
    public final static String NETWORK_CMWAP = "CMWAP";
    public final static String NETWORK_WIFI = "WIFI";
    public final static String TAG = "ToolNetwork";
    private NetworkInfo networkInfo = null;
    private Context mContext = null;

    private ToolNetwork() {
    }

    public static ToolNetwork getInstance() {
        return SingletonHolder.instance;
    }

    public ToolNetwork init(Context context) {
        this.mContext = context;
        return this;
    }

    /**
     * 判断网络是否可用
     *
     * @return 是/否
     */
    public boolean isAvailable() {
        ConnectivityManager manager = (ConnectivityManager) mContext
            .getApplicationContext().getSystemService(
                Context.CONNECTIVITY_SERVICE);
        if (null == manager) {
            return false;
        }
        networkInfo = manager.getActiveNetworkInfo();
        if (null == networkInfo || !networkInfo.isAvailable()) {
            return false;
        }
        return true;
    }

    /**
     * 判断网络是否已连接
     *
     * @return 是/否
     */
    public boolean isConnected() {
        if (!isAvailable()) {
            return false;
        }
        if (!networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    /**
     * 检查当前环境网络是否可用，不可用跳转至开启网络界面,不设置网络强制关闭当前Activity
     */
    public void validateNetWork() {

        if (!isConnected()) {
            Builder dialogBuilder = new AlertDialog.Builder(mContext);
            dialogBuilder.setTitle("网络错误");
            dialogBuilder.setMessage("网络错误,请检查手机网络设置或尝试重启手机。");
            dialogBuilder.setPositiveButton("退出",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 退出整个程序
//                        RYApplication.getApplicationInstance().removeAll();
                    }
                });
            dialogBuilder.setNegativeButton("重试",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // 重新检查网络
                        ToolNetwork.getInstance().validateNetWork();
                        // dialog.cancel();
                    }
                });
            dialogBuilder.setNeutralButton("设置网络",
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {// which不能大于2的16次方
                        // 去设置网络
                        ((Activity) mContext).startActivityForResult(
                            new Intent(Settings.ACTION_SETTINGS), 2);// 不能用which
                    }
                });
            dialogBuilder.create();
            dialogBuilder.show();
        }
    }

    /**
     * 获取网络连接信息</br> 无网络：</br> WIFI网络：WIFI</br> WAP网络：CMWAP</br>
     * NET网络：CMNET</br>
     *
     * @return
     */

    public String getNetworkType() {
        if (isConnected()) {
            int type = networkInfo.getType();
            if (ConnectivityManager.TYPE_MOBILE == type) {
                KLog.i(TAG,
                    "networkInfo.getExtraInfo()-->"
                        + networkInfo.getExtraInfo());
                if (NETWORK_CMWAP.equals(networkInfo.getExtraInfo()
                                                    .toLowerCase())) {
                    return NETWORK_CMWAP;
                } else {
                    return NETWORK_CMNET;
                }
            } else if (ConnectivityManager.TYPE_WIFI == type) {
                return NETWORK_WIFI;
            }
        }

        return "";
    }

    private static class SingletonHolder {

        private static ToolNetwork instance = new ToolNetwork();
    }
}
