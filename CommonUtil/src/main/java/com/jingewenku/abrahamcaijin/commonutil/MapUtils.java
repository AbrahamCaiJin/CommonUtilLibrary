package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年12月11日 14:06
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mask on 2017/9/13.
 */

public class MapUtils {

    public static void startGuide(Context context, String latitude, String longitude) {
        if (isAvilible(context, "com.google.android.apps.maps")) {
            startNaviGoogle(context, latitude, longitude);
        } else if (isAvilible(context, "com.autonavi.minimap")) {
            startNaviGao(context, latitude, longitude);
        } else if (isAvilible(context, "com.baidu.BaiduMap")) {
            startNaviBaidu(context, latitude, longitude);
        }

    }

    //谷歌地图,起点就是定位点
    public static void startNaviGoogle(Context context, String latitude, String longitude) {
        Uri gmmIntentUri = Uri.parse("google.navigation:q=" + latitude + "," + longitude+"&mode=w");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        context.startActivity(mapIntent);
    }

    //高德地图,起点就是定位点
    // 终点是LatLng ll = new LatLng("你的纬度latitude","你的经度longitude");
    public static void startNaviGao(Context context, String latitude, String longitude) {
        try {
            //sourceApplication
            Intent intent = Intent.getIntent("androidamap://navi?sourceApplication=公司的名称&poiname=我的目的地&lat=" + latitude + "&lon=" + longitude + "&dev=0");
            context.startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }

    //百度地图
    public static void startNaviBaidu(Context context, String latitude, String longitude) {
        try {
            Intent intent = Intent.getIntent("intent://map/direction?destination=latlng:"+ latitude + "," + longitude +
                "|name:&origin=" + "我的位置" + "&mode=driving?ion=" + "我的位置"+ "&referer=Autohome|GasStation#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //验证各种导航地图是否安装
    public static boolean isAvilible(Context context, String packageName) {
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }


}