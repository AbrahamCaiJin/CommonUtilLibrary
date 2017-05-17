package com.jingewenku.abrahamcaijin.commonutil;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;

import java.util.List;

/**
 * 主要功能:Wifi管理工具类
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:20
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class AppWifiHelperMgr {
    private WifiManager wifiManager;// 声明Wifi管理对象
    private WifiInfo wifiInfo;// Wifi信息
    private List<ScanResult> scanResultList; // 扫描出来的网络连接列表
    private List<WifiConfiguration> wifiConfigList;// 网络配置列表
    private WifiLock wifiLock;// Wifi锁

    /**
     * 构造函数
     * @param context
     */
    public AppWifiHelperMgr(Context context){
        this.wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);// 获取Wifi服务
        // 得到Wifi信息
        this.wifiInfo = wifiManager.getConnectionInfo();// 得到连接信息
    }

    /**
     * Wifi状态.
     * @return
     */
    public boolean isWifiEnabled(){
        return wifiManager.isWifiEnabled();
    }

    /**
     * 打开 wifi
     * @return
     */
    public boolean openWifi(){
        if (!isWifiEnabled()) {
            return wifiManager.setWifiEnabled(true);
        } else {
            return false;
        }
    }

    /**
     * 关闭Wifi
     * @return
     */
    public boolean closeWifi(){
        if (!isWifiEnabled()) {
            return true;
        } else {
            return wifiManager.setWifiEnabled(false);
        }
    }

    /**
     *  锁定wifi
     *  锁定WiFI就是判断wifi是否建立成功，在这里使用的是held(握手) acquire
     */
    public void lockWifi() {
        wifiLock.acquire();
    }


    /**
     * 解锁wifi
     */
    public void unLockWifi() {
        if (!wifiLock.isHeld()) {
            wifiLock.release(); // 释放资源
        }
    }

    /**
     * 创建一个Wifi锁，需要时调用
     */
    public void createWifiLock() {
        wifiLock = wifiManager.createWifiLock("flyfly"); // 创建一个锁的标志
    }

    /**
     * 扫描网络
     */
    public void startScan() {
        wifiManager.startScan();
        scanResultList = wifiManager.getScanResults(); // 扫描返回结果列表
        wifiConfigList = wifiManager.getConfiguredNetworks(); // 扫描配置列表
    }

    public List<ScanResult> getWifiList() {
        return scanResultList;
    }

    public List<WifiConfiguration> getWifiConfigList() {
        return wifiConfigList;
    }

    /**
     * 获取扫描WIFI列表的信息
     * @return
     */
    public String lookupScanInfo() {
        StringBuilder scanBuilder = new StringBuilder();
        if(scanResultList == null){
            return "";
        }
        for (int i = 0; i < scanResultList.size(); i++) {
            ScanResult sResult = scanResultList.get(i);
            scanBuilder.append("编号：" + (i + 1));
            scanBuilder.append(" ");
            scanBuilder.append(sResult.toString());  //所有信息
            scanBuilder.append("\n");
        }
        scanBuilder.append("--------------华丽分割线--------------------");
        for(int i=0;i<wifiConfigList.size();i++){
            scanBuilder.append(wifiConfigList.get(i).toString());
            scanBuilder.append("\n");
        }
        return scanBuilder.toString();
    }

    /**
     * 获取指定Wifi的ssid名称
     * @param NetId
     * @return
     */
    public String getSSID(int NetId){
        return scanResultList.get(NetId).SSID;
    }

    /**
     * 获取指定Wifi的物理地址
     * @param NetId
     * @return
     */
    public String getBSSID(int NetId){
        return scanResultList.get(NetId).BSSID;
    }

    /**
     * 获取指定Wifi的频率
     * @param NetId
     * @return
     */
    public int getFrequency(int NetId){
        return scanResultList.get(NetId).frequency;
    }

    /**
     * 获取指定Wifi的功能
     * @param NetId
     * @return
     */
    public String getCapabilities(int NetId){
        return scanResultList.get(NetId).capabilities;
    }
    /**
     * 获取指定Wifi的信号强度
     * @param NetId
     * @return
     */
    public int getLevel(int NetId){
        return scanResultList.get(NetId).level;
    }





    /**
     * 获取本机Mac地址
     * @return
     */
    public String getMac() {
        return (wifiInfo == null) ? "" : wifiInfo.getMacAddress();
    }

    public String getBSSID() {
        return (wifiInfo == null) ? null : wifiInfo.getBSSID();
    }

    public String getSSID() {
        return (wifiInfo == null) ? null : wifiInfo.getSSID();
    }

    /**
     * 返回当前连接的网络的ID
     * @return
     */
    public int getCurrentNetId() {
        return (wifiInfo == null) ? null : wifiInfo.getNetworkId();
    }

    /**
     * 返回所有信息
     * @return
     */
    public String getWifiInfo() {
        return (wifiInfo == null) ? null : wifiInfo.toString();
    }

    /**
     * 获取IP地址
     * @return
     */
    public int getIP() {
        return (wifiInfo == null) ? null : wifiInfo.getIpAddress();
    }

    /**
     * 添加一个连接
     * @param config
     * @return
     */
    public boolean addNetWordLink(WifiConfiguration config) {
        int NetId = wifiManager.addNetwork(config);
        return wifiManager.enableNetwork(NetId, true);
    }

    /**
     * 禁用一个链接
     * @param NetId
     * @return
     */
    public boolean disableNetWordLink(int NetId) {
        wifiManager.disableNetwork(NetId);
        return wifiManager.disconnect();
    }

    /**移除一个链接
     *
     * @param NetId
     * @return
     */
    public boolean removeNetworkLink(int NetId) {
        return wifiManager.removeNetwork(NetId);
    }

    /**
     * 不显示SSID
     * @param NetId
     */
    public void hiddenSSID(int NetId){
        wifiConfigList.get(NetId).hiddenSSID=true;
    }

    /**
     * 显示SSID
     * @param NetId
     */
    public void displaySSID(int NetId){
        wifiConfigList.get(NetId).hiddenSSID=false;
    }
}
