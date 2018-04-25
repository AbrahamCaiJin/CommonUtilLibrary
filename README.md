#
[![Travis branch](https://img.shields.io/badge/Github-Abraham-ff69b4.svg)](https://github.com/AbrahamCaiJin)
[![Travis](https://img.shields.io/badge/简书-Abraham-blue.svg)](http://www.jianshu.com/u/8a0908d85e0a)
[![CircleCI](https://img.shields.io/badge/Blog-Abraham-brightgreen.svg)](http://blog.csdn.net/u014727709?viewmode=contents)
[![TeamCity (simple build status)](https://img.shields.io/badge/Weibo-Abraham%20-lightgrey.svg)](http://weibo.com/p/1005053895373916/home?from=page_100505&mod=TAB&is_all=1#place)
[![Travis](https://img.shields.io/badge/QQ群-523167548%20-ff69b4.svg)](https://shang.qq.com/wpa/qunwpa?idkey=4898a63b0283bc98cc61daeeb9eb6648a34886cf554a3ec272063ef5d999b012)</br>

# API

## 银行卡管理 → [BankCheck](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/BankCheck.java)
    checkBankCard       : 校验银行卡卡号是否合法
    getBankCardCheckCode: 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    getNameOfBank       : 通过银行卡的前六位确定判断银行开户行及卡种
## SharePreference缓存数据 → [AppSharePreferenceMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppSharePreferenceMgr.java)
    put     : 保存数据的方法
    get     : 获取数据的方法
    putImage: 保存图片到SharedPreferences
    getImage: 从SharedPreferences读取图片
    remove  : 移除某个key值已经对应的值
    clear   : 清除所有数据
    contains: 查询某个key是否已经存在
    getAll  : 返回所有的键值对
## 提供App数据清理工作的类 → [AppCleanMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppCleanMgr.java)
    cleanInternalCache   ： 清除本应用内部缓存数据
    cleanExternalCache   ： 清除本应用外部缓存数据
    cleanDatabases       ： 清除本应用所有数据库
    cleanSharedPreference： 清除本应用SharedPreference
    cleanDatabaseByName  ： 根据名字清除本应用数据库
    cleanFiles           ： 清除本应用files文件
    cleanApplicationData ： 清除本应用所有的数据
    getAppClearSize      ： 获取App应用缓存的大小
## 缓存工具类 → [AppACache](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppACache.java)
    put             : 保存String数据到缓存中
    getAsString     : 读取String数据
    getAsJSONObject : 读取JSONObject数据
    getAsJSONArray  : 读取JSONArray数据
    getAsBinary     : 获取byte数据
    getAsObject     : 读取Serializable数据
    getAsBitmap     : 读取bitmap数据
    getAsDrawable   : 读取Drawable数据
    file            : 获取缓存文件
    remove          : 除某个key
    clear           : 清除所有数据
## App应用退出 → [AppExit2Back](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppExit2Back.java)
    exitApp: 退出App程序应用
## 管理和回收Activity → [AppDavikActivityMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppDavikActivityMgr.java)
    getScreenManager      : 单例堆栈集合对象
    removeActivity        : 堆栈中销毁并移除
    removeAllActivity     : 栈中销毁并移除所有Act对象
    currentActivity       : 取当前Act对象
    getCurrentActivityName: 获得当前Act的类名
    addActivity           : 将Act纳入推栈集合中
    exitApp               : 退出栈中所有Activity
## 获取App应用版本信息 → [AppApplicationMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppApplicationMgr.java)
    getAppName             : 获取本地apk的名称
    getVersionName         : 获取本地Apk版本名称
    getVersionCode         : 获取本地Apk版本号
    getMetaData            : 根据key获取xml中Meta的值
    getAppIcon             : 获取应用图标
    getAppFirstInstallTime : 获取应用第一次安装日期
    getAppLastUpdateTime   : 获取应用更新日期
    getAppSize             : 获取应用大小
    getAppApk              : 获取应用apk文件
    getAppInstaller        : 获取应用的安装市场
    getAppSign             : 获取应用签名
    getAppTargetSdkVersion : 获取应用兼容sdk
    getAppUid              : 获取应用uid
    getNumCores            : 获取Cpu内核数
    getRootPermission      : 获得root权限
    getAppPermissions      : 获取应用的所有权限
    hasPermission          : 是否有权限
    isInstalled            : 应用是否安装
    installApk             : 安装应用
    uninstallApk           : 卸载应用
    isSystemApp            : 是否是系统应用
    isServiceRunning       : 服务是否在运行
    stopRunningService     : 停止服务
    killProcesses          : 结束进程
    runScript              : 运行脚本
    runApp                 : 启动应用
    getPackageName         : 获得包名
    getApplicationMetaData : 获取application层级的metadata

## 软键盘管理 → [AppKeyBoardMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppKeyBoardMgr.java)
    openKeybord      : 打卡软键盘
    closeKeybord     : 关闭软键盘
    TimerHideKeyboard: 通过定时器强制隐藏虚拟键盘
    isKeybord        : 输入法是否显示
    hideInputMethod  : 隐藏输入法
    showInputMethod  : 显示输入法
## 系统日志输出工具类 → [AppLogMessageMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppLogMessageMgr.java)
    isEnableDebug: 设置log总开关,debug模式(true:打印日志  false：不打印)
    i            : Info日志
    d            : Debug日志
    w            : Warn日志
    v            : Verbose日志
    e            : Error日志
###### 这里推荐使用凯子哥的日志管理工具,灰常好用，我也集成到了我的项目里面，KLog对超长字符串进行处理，保证全部的字符串都可以打印出来，再也没有了只能打印一部分的问题了，部分使用方法如下
## 系统日志输出工具类 → [KLog](https://github.com/AbrahamCaiJin/CommonUtilLibrary/tree/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/klog)
    init: 设置log总开关,debug模式(true:打印日志  false：不打印)
    a   : assert日志或者打印是否执行到这里等
    i   : Info日志或者打印是否执行到这里等
    d   : Debug日志或者打印是否执行到这里等
    w   : Warn日志或者打印是否执行到这里等
    v   : Verbose日志或者打印是否执行到这里等
    e   : Error日志或者打印是否执行到这里等
    json: 输出Json的格式字符串
    xml : 输出xml的格式字符串
    file: 保存到文件
###### 更多使用方法信息点击这里查看：[KLog](http://kaizige.vip/2016/06/13/klog/)
## App网络管理 → [AppNetworkMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppNetworkMgr.java)
    getNetworkState          : 获取当前手机连接的网络类型
    isNetworkConnected       : 判断网络是否连接
    openNetSetting           : 打开网络设置界面
    is3gConnected            : 检测3G是否连接
    getNetworkTypeName       : 获取网络类型名称
    getCurrentNetworkState   : 获取当前网络的状态
    getCurrentNetworkSubtype : 获取当前网络的具体类型
    isConnectedByState       : 判断当前网络是否已经连接
    isConnectingByState      : 判断当前网络是否正在连接
    isDisconnectedByState    : 判断当前网络是否已经断开
    isDisconnectingByState   : 判断当前网络是否正在断开
    isSuspendedByState       : 判断当前网络是否已经暂停
    isUnknownByState         : 判断当前网络是否处于未知状态中
    isBluetoothByType        : 判断当前网络的类型是否是蓝牙
    isDummyByType            : 判断当前网络的类型是否是虚拟网络
    isEthernetByType         : 判断当前网络的类型是否是ETHERNET
    isMobileByType           : 判断当前网络的类型是否是移动网络
    isMobileDunByType        : 判断当前网络的类型是否是MobileDun
    isMobileHipriByType      : 判断当前网络的类型是否是MobileHipri
    isMobileMmsByType        : 判断当前网络的类型是否是MobileMms
    isMobileSuplByType       : 判断当前网络的类型是否是MobileSupl
    isWifiByType             : 判断当前网络的类型是否是Wifi
    isWimaxByType            : 判断当前网络的类型是否是Wimax
    is1XRTTBySubtype         : 判断当前网络的具体类型是否是1XRTT
    isCDMABySubtype          : 判断当前网络的具体类型是否是CDMA（Either IS95A or IS95B）
    isEDGEBySubtype          : 判断当前网络的具体类型是否是EDGE
    isEHRPDBySubtype         :  判断当前网络的具体类型是否是EHRPD
    isEVDO_0BySubtype        : 判断当前网络的具体类型是否是EVDO_0
    isEVDO_ABySubtype        : 判断当前网络的具体类型是否是EVDO_A
    isEVDO_BBySubtype        : 判断当前网络的具体类型是否是EDGE
    isGPRSBySubtype          : 判断当前网络的具体类型是否是GPRS
    isHSDPABySubtype         : 判断当前网络的具体类型是否是HSDPA
    isHSPABySubtype          : 判断当前网络的具体类型是否是HSPA
    isHSPAPBySubtype         : 判断当前网络的具体类型是否是HSPAP
    isHSUPABySubtype         : 判断当前网络的具体类型是否是HSUPA
    isIDENBySubtype          : 判断当前网络的具体类型是否是IDEN
    isLTEBySubtype           : 判断当前网络的具体类型是否是LTE
    isUMTSBySubtype          : 判断当前网络的具体类型是否是UMTS
    isUNKNOWNBySubtype       : 判断当前网络的具体类型是否是UNKNOWN
    isChinaMobile2G          : 判断当前网络是否是中国移动2G网络
    isChinaUnicom2G          : 判断当前网络是否是中国联通2G网络
    isChinaUnicom3G          : 判断当前网络是否是中国联通3G网络
    isChinaTelecom2G         : 判断当前网络是否是中国电信2G网络
    isChinaTelecom3G         : 判断当前网络是否是中国电信3G网络
    getWifiState             : 获取Wifi的状态，需要ACCESS_WIFI_STATE权限
    isWifiOpen               : 判断Wifi是否打开，需要ACCESS_WIFI_STATE权限
    setWifi                  : 设置Wifi，需要CHANGE_WIFI_STATE权限
    isMobileNetworkOpen      : 判断移动网络是否打开，需要ACCESS_NETWORK_STATE权限
    getIpAddress             : 获取本机IP地址
    setDataEnabled           : 设置数据流量状态
    getWifiScanResults       : 获取wifi列表
    getScanResultsByBSSID    : 过滤扫描结果
    getWifiConnectionInfo    : 获取wifi连接信息
    getProxy                 : 获得Proxy地址

## 手机管理工具类 → [AppPhoneMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppPhoneMgr.java)
    getInstance          : 单例对象
    getSDKVersionNumber  : 获取手机系统版本号
    getPhoneModel        : 获取手机型号
    getPhoneWidth        : 获取手机宽度
    getPhoneHeight       : 获取手机高度
    getPhoneImei         : 获取手机imei串号 ,GSM手机的 IMEI 和 CDMA手机的 MEID
    getPhoneSim          : 获取手机sim卡号
    getPhoneNum          : 获取手机号
    isSDCardMount        : 判断sd卡是否挂载
    getSDFreeSize        : 获取sd卡剩余空间的大小
    getSDAllSize         : 获取sd卡空间的总大小
    isTablet             : 判断是否是平板
    isApkInstalled       : 判断一个apk是否安装
    getAppPermissions    : 获取应用权限 名称列表
    getInstalledApp      : 获取手机内安装的应用
    getUserInstalledApp  : 获取手机安装非系统应用
    getInstalledAppInfo  : 获取安装应用的信息
    startAppPkg          : 打开指定包名的应用
    unInstallApk         : 卸载指定包名的应用
    callPhone            : 直接呼叫指定的号码
    toCallPhoneActivity  : 跳转至拨号界面
    sendMessage          : 直接调用短信API发送信息(设置监听发送和接收状态)
    toSendMessageActivity: 跳转至发送短信界面(自动设置接收方的号码)
    toChooseContactsList : 跳转至联系人选择界面
    getChoosedPhoneNumber: 获取选择的联系人的手机号码
    toCameraActivity     : 跳转至拍照程序界面
    toImagePickerActivity: 跳转至相册选择界面
    getChoosedImage      : 获得选中相册的图片
    openWebSite          : 调用本地浏览器打开一个网页
    toSettingActivity    : 跳转至系统设置界面
    toWIFISettingActivity: 跳转至WIFI设置界面
    openPDFFile          : 启动本地应用打开PDF
    openWordFile         : 启动本地应用打开PDF
    openOfficeByWPS      : 调用WPS打开office文档
    isInstalledApp       : 判断是否安装指定包名的APP
    isExistActivity      : 判断是否存在指定的Activity
    getPhoneType         : 获取移动终端类型
    isSimCardReady       : 判断sim卡是否准备好
    getPhoneStatus       : 获取手机状态信息
    getAllSMS            : 获取手机短信并保存到xml中
    isGpsEnabled         : Gps是否打开

## 反射工具类 → [AppReflectionMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppReflectionMgr.java)
    getProperty          : 得到某个对象的公共属性
    getStaticProperty    : 得到某类的静态公共属性
    invokeMethod         : 执行某对象方法
    invokeStaticMethod   : 执行某类的静态方法
    newInstance          : 新建实例
    isInstance           : 是不是某个类的实例
    getByArray           : 得到数组中的某个元素
    GetClassListByPackage: 得到类的集合
## 获取本地指定资源信息 → [AppResourceMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppResourceMgr.java)
    getStringByAssets    : 根据本地Assets目录下资源名称，获取String数据信息
    getListByAssets      : 根据本地Assets目录下资源名称，获取List集合信息
    getStringByRaw       : 根据本地Raw目录下资源标识，获取String数据信息
    getListByRaw         : 根据本地Raw目录下资源标识，获取List集合信息
    getResourceId        : 根据资源名获得资源id
    readBytesFromAssets  : 从assets目录下读取文件内容
    readBytesFromRaw     : 从res/raw目录下读取文件内容
    readStringFromAssets : 从assets目录读取文本
    readStringFromRaw    : 从raw目录读取文本
    getString            : 获得字符串
    getColor             : 获得颜色
    getDrawable          : 获得Drawable

## 有关Android屏幕的工具类 → [AppScreenMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppScreenMgr.java)
    getScreenWidth           : 获得屏幕宽度
    getScreenHeight          : 获得屏幕高度
    getStatusHeight          : 获得状态栏的高度
    getRealScreenHeight      : 获取整块屏幕的高度
    getNavigationAreaHeight  : 获取虚拟按键区域的高度
    getNavigationBarrH       : 获取导航栏高度
    snapShotWithStatusBar    : 获取当前屏幕截图，包含状态栏
    snapShotWithoutStatusBar : 获取当前屏幕截图，不包含状态栏
    getTitleBarHeight        : 获得标题栏高度
    getStatusBarHeight       : 获取通知栏高度
    takeScreenShot           : 获取指定Activity的截屏，保存到png文件
    savePic                  : 保存bitmap
    captureWebView           : 截取webView快照(webView加载的整个内容的大小)
    shoot                    : 截屏并保存
    shootWebView             : 截屏并保存
## 获取App应用系统基本信息 → [AppSysMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppSysMgr.java)
    getSysClientOs             : 获得客户端操作系统名称
    getSysSdk                  : 获取当前操作系统的sdk版本
    getSysLanguage             : 获取当前操作系统的语言
    getSysModel                : 获取手机型号
    getSysRelease              : 获取操作系统的版本号
    getSysSIMSerialNum         : 读取SIM卡序列号
    getSysCPUSerialNum         : 获取手机CPU序列号
    getSysTelephonyManager     : 获得电话管理实例对象
    getSysTelephoneSerialNum   : 读唯一的设备ID(唯一的设备ID【GSM手机的IMEI】和【CDMA手机的 MEID】,如果获取不到返回一个默认字符串)
    getSysCarrier              : 获取运营商信息(三大运营商)
    getSysPhoneState           : 获取手机状态(0：无活动 1：响铃 2：待机)
    getSysPhoneLoaction        : 获得手机方位
    getSysDeviceSoftVersion    : 获得设备的软件版本号(注：the IMEI/SV(software version) for GSM phones 不支持返回“not available”)
    getSysPhoneNumber          : 获得手机号
    getSysSimCode              : 获得SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字。(注：SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断))
    getSysSimPrivatorName      : 服务商名称(注:例如：中国移动、联通SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断))
    getSysUserPhoneId          : 唯一的用户ID (注：例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE)
    getWindowManager           : 获取WindowManager对象
    getSysDefaultThreadPoolSize: 获得系统配置相符的线程池大小
    getSysSampleSize           : 获取当前APP应用的SampleSize大小
    getVibrator                : 获取震动器对象
    getSysLocalIpAddress       : 获取手机IP地址
    getAndroidID               : 获取AndroidID
    getIMSI                    : 获取设备IMSI码
    getIP                      : 获取网络IP地址(优先获取wifi地址)
    getWifiIP                  : 获取WIFI连接下的ip地址
    getGPRSIP                  : 获取GPRS连接下的ip地址
    getSerial                  : 获取设备序列号
    getSIMSerial               : 获取SIM序列号
    getMNC                     : 获取网络运营商 46000,46002,46007 中国移动,46001 中国联通,46003 中国电信
    getCarrier                 : 获取网络运营商：中国电信,中国移动,中国联通
    getModel                   : 获取硬件型号
    getBuildBrand              : 获取编译厂商
    getBuildHost               : 获取编译服务器主机
    getBuildTags               : 获取描述Build的标签
    getBuildTime               : 获取系统编译时间
    getBuildUser               : 获取系统编译作者
    getBuildVersionRelease     : 获取编译系统版本(5.1)
    getBuildVersionCodename    : 获取开发代号
    getBuildVersionIncremental : 获取源码控制版本号
    getBuildVersionSDK         : 获取编译的SDK
    getBuildID                 : 获取修订版本列表(LMY47D)
    getSupportedABIS           : CPU指令集
    getManufacturer            : 获取硬件制造厂商
    getBootloader              : 获取系统启动程序版本号
    getDisplayVersion          : 获取系统版本号
    getLanguage                : 获取语言
    getCountry                 : 获取国家
    getOSVersion               : 获取系统版本:5.1.1
    getGSFID                   : 获取GSF序列号
    getBluetoothMAC            : 获取蓝牙地址
    getPsuedoUniqueID          : Android设备物理唯一标识符
    getFingerprint             : 构建标识,包括brand,name,device,version.release,id,version.incremental,type,tags这些信息
    getHardware                ：获取硬件信息
    getProduct                 ：获取产品信息
    getDevice                  ：获取设备信息
    getBoard                   ：获取主板信息
    getRadioVersion            ：获取基带版本(无线电固件版本 Api14以上)
    getUA                      : 获取的浏览器指纹(User-Agent)
    getDensity                 : 获取得屏幕密度
    getGoogleAccounts          : 获取google账号
## 自定义Toast提示框 → [AppToastMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppToastMgr.java)
    shortToast            : 自定义Toast调用
    longToast             : 自定义Toast调用
    cancelToast           : 取消显示Toast
    Toast                 : 默认Toast调用
    show                  : 屏幕中心位置短时间显示Toast。
    ToastShortBottomCenter: 屏幕底部中间位置显示短时间Toast
    ToastShortBottomLeft  : 屏幕底部左边位置短时间显示Toast
    ToastShortBottomRight : 屏幕底部右边位置短时间显示Toast
    ToastShortCenter      : 屏幕中心位置短时间显示Toast
    ToastShortCenterLeft  : 屏幕中心左边位置短时间显示Toast
    ToastShortCenterRight : 屏幕中心右边位置短时间显示Toast
    ToastShortTopCenter   : 屏幕顶部中心位置短时间显示Toast
    ToastShortTopLeft     : 屏幕顶部左边位置短时间显示Toast
    ToastShortTopRight    : 屏幕顶部右边位置短时间显示Toast
    ToastLongBottomCenter : 屏幕底部中间位置显示长时间Toast
    ToastLongBottomLeft   : 屏幕底部左边位置长时间显示Toast
    ToastLongBottomRight  : 屏幕底部右边位置长时间显示Toast
    ToastLongCenter       : 屏幕中心位置长时间显示Toast
    ToastLongCenterLeft   : 屏幕中心左边位置长时间显示Toast
    ToastLongCenterRight  : 屏幕中心右边位置短时间显示Toast
    ToastLongTopCenter    : 屏幕顶部中心位置长时间显示Toast
    ToastLongTopLeft      : 屏幕顶部左边位置长时间显示Toast
    ToastLongTopRight     : 屏幕顶部右边位置长时间显示Toast
## 正则表达式 → [AppValidationMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppValidationMgr.java)
    isEmpty              : 验证是否为空串 (包括空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串,返回true)
    isNotEmpty           : 是否不为空
    isNotZero            : 验证非零正整数
    isNumber             : 验证是数字
    isUpChar             : 验证是大写字母
    isLowChar            : 验证是小写字母
    isLetter             : 验证是英文字母
    isChinese            : 验证输入汉字
    isRealName           : 验证真实姓名
    isOneCode            : 验证是否是条形码
    isEmail              : 验证邮箱是否正确
    isPhone              : 验证手机号是否正确
    isPlane              : 验证座机号码是否正确
    isPostalCode         : 验证邮政编码是否正确
    isIpAddress          : 验证IP地址是否正确
    isURL                : 验证URL地址是否正确
    isInteger            : 验证是否是正整数
    isPoint              : 验证是否是小数
    isBankNo             : 验证是否银行卡号
    isIDCard             : 验证身份证号码是否正确
    isPeculiarStr        : 判断是否有特殊字符
    isUserName           : 判断是否为用户名账号(规则如下：用户名由下划线或字母开头，由数字、字母、下划线、点、减号组成的4-32位字符)
    chineseLength        : 获取字符串中文字符的长度（每个中文算2个字符）
    strLength            : 获取字符串的长度
    subStringLength      : 获取指定长度的字符所在位置
    isNumberLetter       : 是否只是字母和数字
    isContainChinese     : 是否包含中文
    convertStreamToString: 从输入流中获得String
    cutString            : 截取字符串到指定字节长度
    cutStringFromChar    : 截取字符串从第一个指定字符
    strlen               : 获取字节长度
    getSizeDesc          : 获取大小的描述
    ip2int               : ip地址转换为10进制数
    gainUUID             : 获取UUID
    phoneNoHide          : 手机号码，中间4位星号替换
    cardIdHide           : 银行卡号，保留最后4位，其他星号替换
    idHide               : 身份证号，中间10位星号替换
    checkVehicleNo       : 是否为车牌号（沪A88888）
    isContinuousNum      : 判断字符串是否为连续数字 45678901等
    isAlphaBetaString    : 是否是纯字母
    isContinuousWord     : 判断字符串是否为连续字母 xyZaBcd等
    isRealDate           : 是否是日期 20120506 共八位，前四位-年，中间两位-月，最后两位-日

## Wifi管理工具类 → [AppWifiHelperMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppWifiHelperMgr.java)
    isWifiEnabled     : Wifi状态
    openWifi          : 打开wifi
    closeWifi         : 关闭Wifi
    lockWifi          : 锁定WiFI就是判断wifi是否建立成功，在这里使用的是held(握手) acquire
    unLockWifi        : 解锁
    wificreateWifiLock: 创建一个Wifi锁，需要时调用
    startScan         : 扫描网络
    getWifiList       : 获取wifi列表
    getWifiConfigList : 获取wifi配置列表
    lookupScanInfo    : 获取扫描WIFI列表的信息
    getSSID           : 获取指定Wifi的ssid名称
    getBSSID          : 获取指定Wifi的物理地址
    getFrequency      : 获取指定Wifi的频率
    getCapabilities   : 获取指定Wifi的功能
    getLevel          : 获取指定Wifi的信号强度
    getBSSID          : 获取SSID
    getCurrentNetId   : 返回当前连接的网络的ID
    getWifiInfo       : 返回所有信息
    getIP             ： 获取IP地址
    addNetWordLink    ： 添加一个连接
    disableNetWordLink： 禁用一个链接
    removeNetworkLink ： 移除一个链接
    hiddenSSID        : 不显示SSID
    displaySSID       : 显示SSID
## 类型转换类 → [ConvertUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/ConvertUtils.java)
    hexStringToBytes: 十六进制字符串转换为byte数组
    bytesToHexString: byte数组转换为十六进制字符串
    charToByte      : char转换为byte数组
    intToByte       : int转换为byte数组
    byteToInt       : byte数组转换为int
    saveDecimals    : 保留几位小数
    nullOfString    : null转String
    stringToByte    : String转Byte
    stringToBoolean : String转Boolean
    stringToInt     : String转Int
    stringToShort   : String转Short
    stringToDouble  : String转Double
    intToString     : Int转String
    doubleToLong    : Double转Long
    doubleToInt     : Double转Int
    longToDouble    : Long转Double
    longToInt       : Long转Int
    stringToLong    : String转Long
    longToString    : Long转String
## 日期管理类 → [AppDateMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppDateMgr.java)
    todayYyyyMmDd         : 当天的年月日
    todayHhMmSs           : 当天的时分秒
    todayYyyyMmDdHhMmSs   : 当天的年月日时分秒
    parseYyyy             : 获取年
    parseMm               : 获取月
    parseDd               : 获取日
    parseYyyyMmDd         : 获取年月日
    parseHhMmSs           : 时分秒
    getWeekNumber         : 获取星期几
    getWeekOfMonth        : 日期中某个月份的第几周
    getWeekOfYear         : 日期中某个年份的第几周
    dateTimeToTimeStamp   : 将年月日时分秒转成Long类型
    timeStampToDateTime     : 将Long类型转成年月日时分秒
    string2Date             : 将年月日时分秒转成Date类型
    date2String             : 将Date类型转成年月日时分秒
    dateIsBefore            : 比较日期
    minutesBetweenTwoDate   : 相差多少分钟
    getChineseZodiac        : 获取日期中的生肖
    getZodiac               : 获取日期中的星座
    getNowDayOffset         : 获取日期
    getTime                 : 获取日期
    forward                 : 使日期倒一天
    isLeapYear              : 判断平年闰年
    getDaysOfMonth          : 计算某月的天数
    secondsMorning          : 获取当天凌晨的秒数
    secondsNight            : 获取第二天凌晨的秒数
    isSameDay               : 判断某两天是不是同一天
    formatFriendly          : 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
    formatDateTime          : 将日期以yyyy-MM-dd HH:mm:ss格式化
    formatDateTime          : 将日期以yyyy-MM-dd HH:mm:ss格式化
    formatDateTime          : 将日期以yyyy-MM-dd HH:mm:ss格式化
    parseDate               : 将日期字符串转成日期
    gainCurrentDate         : 获取系统当前日期
    compareDate             : 验证日期是否比当前日期早
    addDateTime             : 对日期进行增加操作
    subDateTime             : 对日期进行相减操作
    formatDateForExcelDate  : 格式化excel中的时间
    formatDateForFileName   : 将日期格式化作为文件名
    formatDateSecond        : 格式化日期(精确到秒)
    tempDateSecond          : 格式化日期(精确到秒)
    tempDateSecond          : 格式化日期(精确到秒)
    formatDateDay           : 格式化日期(精确到天)
    formatDateDetailDay     : 式化日期(精确到天)
    formatNumber            : double类型的数字保留两位小数（四舍五入）
    formateDate             : 将字符串转换成日期
    parseStringToDate       : 将字符日期转换成Date
    formatDoubleNumber      : 将double日期转换成String
    getTimeMillis           : 获得指定Date类型的毫秒数
    getCurrentDayTimeMillis : 获得当前时间的毫秒数
    convertMillisecond      : 将格式化过的时间串转换成毫秒
    getDateInterval         : 得到两个日期的天数
    compareTime             : 时间比较

## 手机常用单位转换的辅助类 → [DensityUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/DensityUtils.java)
    dip2px: 据手机的分辨率从 dip 的单位 转成为 px(像素)
    px2dip: 根据手机的分辨率从 px(像素) 的单位 转成为 dp
    dp2px : dp转px
    sp2px : sp转px
    px2dp : px转dp
    px2sp : px转sp
## 文件管理类 → [FileUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/FileUtils.java)
    createFileDir      : 创建目录
    delFile            : 删除文件（若为目录，则递归删除子目录和文件）
    getFileSize        : 取文件大小，单位为byte（若为目录，则包括所有子目录和文件）
    saveBitmap         : 保存Bitmap到指定目录
    isFileExists       : 判断某目录下文件是否存在
    isMountedSDCard    : 检查是否已挂载SD卡镜像（是否存在SD卡）
    gainSDFreeSize     : 获取SD卡剩余容量（单位Byte）
    gainSDAllSize      : 获取SD卡总容量（单位Byte）
    gainSDCardPath     : 获取可用的SD卡路径（若SD卡不没有挂载则返回""）
    readFileByLines    : 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
    saveToFile         : 保存内容
    saveToFile         : 指定编码保存内容
    appendToFile       : 追加文本
    isExsit            : 判断文件是否存在
    read               : 快速读取程序应用包下的文件内容
    read               : 读取指定目录文件的文件内容
    read               : 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
    readRawValue       : 读取raw目录的文件内容
    readAssetsValue    : 读取assets目录的文件内容
    readAssetsListValue: 读取assets目录的文件内容
    readShrePerface    : 获取SharedPreferences文件内容
    writeShrePerface   : 写入SharedPreferences文件内容
    write              : 写入应用程序包files目录下文件
    write              : 指定编码将内容写入目标文件
    write              : 指定目录写入文件内容
    write              : 写入文件
    saveAsJPEG         : 指定目录写入文件内容
    saveAsPNG          : 指定目录写入文件内容
    getStringFromFile  : 将文件转成字符串
    copyFile           : 复制文件
    copyFileFast       : 快速复制
    shareFile          : 分享文件
    zip                : 压缩
    unzip              : 解压
    formatFileSize     : 格式化文件大小
    Stream2File        : 将输入流写入到文件
    createFolder       : 创建文件夹(支持覆盖已存在的同名文件夹)
    getFileName        : 获取文件名
    rename             : 重命名文件\文件夹
    getFolderName      : 获取文件夹名称
    getFilesArray      : 获取文件夹下所有文件
    openImage          : 打开图片
    openVideo          : 打开视频
    openURL            : 打开URL
    downloadFile       : 下载文件
    upgradeApp         : 通过APKURL升级应用
## 控件点击效果动画工具类 → [ToolAnimation](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/ToolAnimation.java)
    addTouchDrak : 给视图添加点击效果,让背景变深
    addTouchLight: 给视图添加点击效果,让背景变暗
## view管理类 → [ViewUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/ViewUtils.java)
    removeSelfFromParent   : 把自身从父View中移除
    isTouchInView          : 判断触点是否落在该View上
    setTVUnderLine         : 给TextView设置下划线
    showPopupWindow        : 显示PopupWindow
    dismissPopup           : 关闭PopupWindow
    captureView            : 截图
    createViewBitmap       : 截图
    convertViewToBitmap    : 截图
    getActivityBitmap      : 获取Activity的截图
    getStatusBarHeight     : 获取状态栏高度
    getToolbarHeight       : 获取工具栏高度
    getNavigationBarHeight : 获取导航栏高度
    measureView            : 测量view
    getViewWidth           : 获取view的宽度
    getViewHeight          : 获取view的高度
    getActivity            : 获取view的上下文
## 提供APP应用计算，算法等 -> [AppCalculateMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppCalculateMgr.java)
    distance        : 两点间的距离
    pointTotoDegrees: 计算点a(x,y)的角度
    checkInRound    : 点在圆内
## SpannableString工具类 → [SpannableStringUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/SpannableStringUtils.java)
    Builder.setFlag           : 设置标识
    Builder.setForegroundColor: 设置前景色
    Builder.setBackgroundColor: 设置背景色
    Builder.setQuoteColor     : 设置引用线的颜色
    Builder.setLeadingMargin  : 设置缩进
    Builder.setMargin         : 设置间距
    Builder.setBullet         : 设置列表标记
    Builder.setFontSize       : 设置字体尺寸
    Builder.setFontProportion : 设置字体比例
    Builder.setFontXProportion: 设置字体横向比例
    Builder.setStrikethrough  : 设置删除线
    Builder.setUnderline      : 设置下划线
    Builder.setSuperscript    : 设置上标
    Builder.setSubscript      : 设置下标
    Builder.setBold           : 设置粗体
    Builder.setItalic         : 设置斜体
    Builder.setBoldItalic     : 设置粗斜体
    Builder.setFontFamily     : 设置字体系列
    Builder.setTypeface       : 设置字体
    Builder.setAlign          : 设置对齐
    Builder.setBitmap         : 设置图片
    Builder.setDrawable       : 设置图片
    Builder.setUri            : 设置图片
    Builder.setResourceId     : 设置图片
    Builder.setClickSpan      : 设置点击事件
    Builder.setUrl            : 设置超链接
    Builder.setBlur           : 设置模糊
    Builder.append            : 追加样式字符串
    Builder.create            : 创建样式字符串
## Snackbar工具类 → [SnackbarUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/SnackbarUtils.java)
    ShortSnackbar     : 短显示Snackbar，自定义颜色
    LongSnackbar      : 长显示Snackbar，自定义颜色
    IndefiniteSnackbar: 自定义时常显示Snackbar，自定义颜色
    ShortSnackbar     : 短显示Snackbar，可选预设类型
    LongSnackbar      : 显示Snackbar，可选预设类型
    IndefiniteSnackbar: 自定义时常显示Snackbar，可选预设类型
    setSnackbarColor  : 设置Snackbar背景颜色
    setSnackbarColor  : 设置Snackbar文字和背景颜色
    SnackbarAddView   : 向Snackbar中添加view
## Picasso图片加载工具类 → [PicassoUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/PicassoUtils.java)
    getinstance               : 单例对象
    LoadImage                 : 加载图片
    LoadImageWithWidtAndHeight: 加载图片,设置宽高,图片默认居中(centerCrop())
## Glide图片加载工具类 → [GlideUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/GlideUtils.java)
    instance                         : 单例对象
    LoadContextBitmap                : Glide请求图片，会受到Context生命周期控制
    LoadFragmentBitmap               : Glide请求图片，会受到Fragment生命周期控制
    LoadSupportv4FragmentBitmap      : Glide请求图片，会受到support.v4.app.Fragment生命周期控制
    LoadContextCircleBitmap          : 加载设置圆形图片
    LoadfragmentCircleBitmap         : Glide请求图片设置圆形，会受到android.app.Fragment生命周期控制
    LoadSupportv4FragmentCircleBitmap: Glide请求图片设置圆形，会受到android.support.v4.app.Fragment生命周期控制
    LoadContextRoundBitmap           : 加载设置圆角图片
    LoadfragmentRoundBitmap          : Glide请求图片设置圆角，会受到android.app.Fragment生命周期控制
    LoadSupportv4FragmentRoundBitmap : Glide请求图片设置圆角，会受到android.support.v4.app.Fragment生命周期控制
    LoadContextBlurBitmap            : Glide加载模糊图片
    LoadFragmentBlurBitmap           : Glide加载模糊图片会受到Fragment生命周期控制
    LoadSupportv4FragmentBlurBitmap  : Glide加载模糊图片会受到support.v4.app.Fragment生命周期控制
    LoadContextRotateBitmap          : 旋转图片
    LoadFragmentRotateBitmap         : Glide加载旋转图片会受到Fragment生命周期控制
    LoadSupportv4FragmentRotateBitmap: Glide加载旋转图片会受到support.v4.app.Fragment生命周期控制
## Fresco图片加载工具类 → [FrescoUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/FrescoUtils.java)
    initConfig             : 初始化配置
    initImagePipelineConfig: 单例
    init                   : 默认加载图片和失败图片
    configureCaches        : 初始化配置
    setImageURI            : 开始加载图片
    changeImgSize          : 自定义图片尺寸
    loadGif                : 加载gif图
    moreImgRequst          : 多图请求需自定义ImageRequest(图片预览)
    localImg               : 缩略图预览(仅支持本地图片,并且是JPEG图片格式)
    loadImage              : 请求图片
    getImageDecodeOptions  : 图片解码
## 图片管理工具类 → [AppImageMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppImageMgr.java)
    getBitmap                  : 根据drawable id获取Bitmap
    getDrawable                : 根据drawable id获取Drawable
    bitmapToDrawble            : bitmap转drawable
    readDrawableBitmap         : 以最省内存的方式读取本地资源的图片
    readDrawableBigBitmap      : 读取本地drawable中较大的资源图片
    getBitmapFromFile          : 从文件得到BitMap
    getBitmapByteArray         : 从数组得到Bitmap
    getBitmapFromStream        : 从流中得到Bitmap
    setAlpha                   : 图片透明度处理
    getImgCacheFromLocal2Bitmap: 获取源图片的BITMAP，压缩，本地图片
    getBitmap2Byte             : bitmap转byte[]
    decodeBitmapToThumbnail    : 获取缩略图
    saveImage                  : 保存图片
    grayMasking                : 光晕效果
    getBitmapSize              : 获取bitmap的字节大小
    convertToBlackWhite        : 将彩色图转换为黑白图
    convertToRoundedCorner     : 转换成圆角
    BoxBlurFilter              : 高斯模糊
    getCircleBitmap            : 圆形图片
    getRoundedCornerBitmap     : 获取圆角
    lessenBitmap               : 按比例缩小图片（单位像素） lessen the bitmap
    readPictureDegree          : 判断图片旋转情况
    rotaingImageView           : 旋转图片
    rotateBitmap               : 图片旋转
    getBitmapFromUri           : 根据uri获取图片
    getPicPathFromUri          : 图片uri转path
    getSmallBitmap             : 根据路径获得图片并压缩返回bitmap用于显示
    decodeBitmap               : 从文件中获取图片
    computeSampleSize          : 使用该算法，就可动态计算出图片的inSampleSize
    applyDimension             : 长度单位转换
## 资金运算工具类 → [AppBigDecimal](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppBigDecimal.java)
    add              : 提供精确的加法运算
    substract        : 提供精确的减法运算
    multiply         : 提供精确的乘法运算
    divide           : 提供（相对）精确的除法运算.当发生除不尽的情况时,由scale参数指 定精度,以后的数字四舍五入.
    round            : 提供精确的小数位四舍五入处理
    remainder        : 取余数
    formatMoney      : 金额分割，四舍五人金额
    compareBigDecimal: 比较大小
    adjustDouble     : 获取自己想要的数据格式
## WebService网络工具类 → [WebServiceUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/WebServiceUtils.java)
    callWebService: 请求网络数据
## HttpURLConnection网络工具类 → [HttpURLConnectionUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/HttpURLConnectionUtils.java)
    doGetAsyn : 异步的Get请求
    doPostAsyn: 异步的Post请求
    doGet     : Get请求，获得返回数据
    doPost    : 向指定 URL 发送POST方法的请求

## 字符串判断工具类 → [AppStringUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppStringUtils.java)
    isEmpty                  : 判断字符串是否为空
    isNotEmpty               : 判断str null,"","null" 均视为空
    checkNameChese           : 检测String是否全是中文
    isChinese                : 判定输入汉字
    toLowerCaseFirstOne      : 将字符串的第一位转为小写
    toUpperCaseFirstOne      : 将字符串的第一位转为大写
    underScoreCase2CamelCase : 下划线命名转为驼峰命名
    camelCase2UnderScoreCase : 驼峰命名法转为下划线命名
    throwable2String         : 将异常栈信息转为字符串
    concat                   : 字符串连接，将参数列表拼接为一个字符串
    concatSpiltWith          : 字符串连接，将参数列表拼接为一个字符串
    toASCII                  : 将字符串转移为ASCII码
    toUnicode                : 将字符串转移为Unicode码
    toUnicodeString          : 将字符串转移为Unicode码
    containsChineseChar      : 是否包含中文字符
    isNumber                 : 参数是否是有效数字 （整数或者小数）
    matcherFirst             : 匹配到第一个字符串
    isInt                    : 参数是否是有效整数
    isDouble                 : 字符串参数是否是double
    isBoolean                : 判断一个对象是否为boolean类型,包括字符串中的true和false
    isTrue                   : 对象是否为true
    contains                 : 判断一个数组里是否包含指定对象
    toInt                    : 将对象转为int值,如果对象无法进行转换,则使用默认值
    toLong                   : 将对象转为long类型,如果对象无法转换,将返回默认值
    toDouble                 : 将对象转为Double,如果对象无法转换,将使用默认值
    splitFirst               : 分隔字符串,根据正则表达式分隔字符串,只分隔首个,剩下的的不进行分隔,如: 1,2,3,4 将分隔为 ['1','2,3,4']
    toString                 : 将对象转为字符串,如果对象为null,则返回null,而不是"null"
    toStringAndSplit         : 将对象转为String后进行分割，如果为对象为空或者空字符,则返回null
## Json工具类 → [JsonUtils]()
    toJson            : 对象转json
    fromJson          : json转对象
    map2Json          : Map转为JSONObject
    collection2Json   : 集合转换为JSONArray
    object2Json       : Object对象转换为JSONArray
    string2JSONObject : json字符串生成JSONObject对象
    object2json       : 对象转换为Json
    list2json         : List集合转换为Json
    array2json        : 对象数组转换为Json
    set2json          : Set集合转为Json
    string2json       : 字符串转换为Json
## 系统媒体管理工具类 → [MediaUtil]()
    startActivityForCamera   : 进入系统拍照
    startActivityForGallery  : 进入系统图库
    startActivityForImageCut : 进入系统裁剪
## GPS坐标转换工具 → [CoordinateTransformUtil]()
    bd09towgs84  : 百度坐标系(BD-09)转WGS坐标(百度坐标纬度,百度坐标经度),WGS84坐标数组
    wgs84tobd09  : WGS坐标转百度坐标系(BD-09)(WGS84坐标系的经度,WGS84坐标系的纬度),百度坐标数组
    gcj02tobd09  : 火星坐标系(GCJ-02)转百度坐标系(BD-09)(火星坐标经度,火星坐标纬度),百度坐标数组
    bd09togcj02  : 百度坐标系(BD-09)转火星坐标系(GCJ-02)(百度坐标纬度,百度坐标经度),火星坐标数组
    wgs84togcj02 : WGS84转GCJ02(火星坐标系)(WGS84坐标系的经度,WGS84坐标系的纬度),火星坐标数组
    gcj02towgs84 : GCJ02(火星坐标系)转GPS84(火星坐标系的经度,火星坐标系纬度),WGS84坐标数组
    transformlat : 纬度转换
    transformlng : 经度转换
    out_of_china : 判断是否在国内，不在国内不做偏移
## 程序崩溃处理类 → [CrashHandlerUtil]()
    getInstance ： 获取CrashHandler实例 ,单例模式
    init        ： 初始化
    setCrashTip ： 设置程序退出时的提示信息
## html标签管理类 → [HtmlUtils]()
    addHtmlRedFlag : 为给定的字符串添加HTML红色标记，当使用Html.fromHtml()方式显示到TextView 的时候其将是红色的
    keywordMadeRed : 将给定的字符串中所有给定的关键字标红
## root权限工具类 → [RootPermissionUtils]()
    isRoot : 根据/system/bin/或/system/xbin目录下是否存在su文件判断是否已ROOT
## 快捷图标管理类 → [ShortCutUtils]()
    hasShortcut : 检测是否存在快捷键
    addShortcut : 为程序创建桌面快捷方式
    delShortcut : 删除程序的快捷方式
## 单例工具类 → [SingletonUtils]()
    getInstance : 获取单例
    getInstance : 获取单例
## WebView管理类 → [WebViewManager]()
    enableAdaptive                            : 开启自适应功能
    disableAdaptive                           : 禁用自适应功能
    enableZoom                                : 开启缩放功能
    disableZoom                               : 禁用缩放功能
    enableJavaScript                          : 开启JavaScript
    disableJavaScript                         : 禁用JavaScript
    enableJavaScriptOpenWindowsAutomatically  : 开启JavaScript自动弹窗
    disableJavaScriptOpenWindowsAutomatically : 禁用JavaScript自动弹窗
    goBack                                    : 返回
## 窗口管理类 → [WindowUtils]()
    getDisplayRotation    : 获取当前窗口的旋转角度
    isLandscape           : 当前是否是横屏
    isPortrait            : 当前是否是竖屏
    dimBackground         : 调整窗口的透明度  1.0f,0.5f 变暗
    setLandscape          : 设置Activity为横屏
    setPortrait           : 设置Activity为竖屏
    setActivityFullScreen : 设置全屏,要在setContentView之前调用
## pull解析xml管理类 → [XmlParseUtiles]()
    getXmlList   : 解析XML
    getXmlObject : 解析XML
    setXmlValue  : 把xml标签的值，转换成对象里属性的值
## 压缩工具类 → [ZipUtil]()
    zipFiles              : 批量压缩文件（夹）
    upZipFile             : 解压缩一个文件
    upZipSelectedFile     : 解压文件名包含传入文字的文件
    getEntriesNames       : 获得压缩文件内文件列表
    getEntriesEnumeration : 获得压缩文件内压缩文件对象以取得其属性
    getEntryComment       : 取得压缩文件对象的注释
    getEntryName          : 取得压缩文件对象的名称
    zipFile               : 压缩文件
## Fragment管理类 → [FragmentUtils]()
    addFragment              : 新增fragment
    addFragment              : 新增fragment
    hideAddFragment          : 先隐藏后新增fragment
    addFragments             : 新增多个fragment
    removeFragment           : 移除fragment
    removeToFragment         : 移除到指定fragment
    removeFragments          : 移除同级别fragment
    removeAllFragments       : 移除所有fragment
    replaceFragment          : 替换fragment
    popFragment              : 出栈fragment
    popToFragment            : 出栈到指定fragment
    popFragments             : 出栈同级别fragment
    popAllFragments          : 出栈所有fragment
    popAddFragment           : 先出栈后新增fragment
    hideFragment             : 隐藏fragment
    hideFragments            : 隐藏同级别fragment
    showFragment             : 显示fragment
    hideShowFragment         : 先隐藏后显示fragment
    getLastAddFragment       : 获取同级别最后加入的fragment
    getLastAddFragmentInStack: 获取栈中同级别最后加入的fragment
    getTopShowFragment       : 获取顶层可见fragment
    getTopShowFragmentInStack: 获取栈中顶层可见fragment
    getFragments             : 获取同级别fragment
    getFragmentsInStack      : 获取栈中同级别fragment
    getAllFragments          : 获取所有fragment
    getAllFragmentsInStack   : 获取栈中所有fragment
    getPreFragment           : 获取目标fragment的前一个fragment
    findFragment             : 查找fragment
    dispatchBackPress        : 处理fragment回退键
    setBackgroundColor       : 设置背景色
    setBackgroundResource    : 设置背景资源
    setBackground            : 设置背景
## AES对称加密 → [AESUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/encryption/AESUtils.java)
    initKey ： 生成密钥
    encrypt ： 加密
    decrypt ： 解密
## DES对称加密 → [DESUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/encryption/DESUtils.java)
    initKey : 生成密钥
    encrypt : DES 加密
    decrypt : DES 解密
## MD5加密 → [MD5Utils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/encryption/MD5Utils.java)
    encryptMD5 : MD5加密
## SHA-1加密不可逆 → [SHAUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/encryption/SHAUtils.java)
    encryptSHA : SHA-512 加密
## 3DES对称加密 → [TripleDESUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/encryption/TripleDESUtils.java)
    initKey : 生成密钥
    encrypt : 3DES 加密
    decrypt : 3DES 解密
## 加密工具类 → [CipherUtils]
    md5 : 输入流转md5
    XorEncode : 异或加密
    XorDecode : 异或解密
    sha1 : 字符串sha1值

## About
&ensp;&ensp;&ensp;&ensp;这里把开发中常用的一些工具类做一个整理，主要用到的时候不用再费力的百度或者Google了，大家有需要的可以随时借鉴走，同时也希望这个越来越强大，有什么错误的地方还希望各位不吝指出，让我得以完善，当然，在此声明，这些并不是我一个人，也用了许多前辈们的东西，这里一并感谢，目前还在测试，持续更新中。对你有用就给一个Start吧！</br></br>
#
[![Travis branch](https://img.shields.io/badge/Github-Abraham-ff69b4.svg)](https://github.com/AbrahamCaiJin)
[![Travis](https://img.shields.io/badge/简书-Abraham-blue.svg)](http://www.jianshu.com/u/8a0908d85e0a)
[![CircleCI](https://img.shields.io/badge/Blog-Abraham-brightgreen.svg)](http://blog.csdn.net/u014727709?viewmode=contents)
[![TeamCity (simple build status)](https://img.shields.io/badge/Weibo-Abraham%20-lightgrey.svg)](http://weibo.com/p/1005053895373916/home?from=page_100505&mod=TAB&is_all=1#place)
[![Travis](https://img.shields.io/badge/QQ群-523167548%20-ff69b4.svg)](https://shang.qq.com/wpa/qunwpa?idkey=4898a63b0283bc98cc61daeeb9eb6648a34886cf554a3ec272063ef5d999b012)</br>
## Download

Gradle:
``` groovy
compile 'com.abrahamcaijin.commonutil:CommonUtil:1.0.2'

```
Maven:
``` groovy
<dependency>
  <groupId>com.abrahamcaijin.commonutil</groupId>
  <artifactId>CommonUtil</artifactId>
  <version>1.0.2</version>
  <type>pom</type>
</dependency>
```
 Ivy:
``` groovy
<dependency org='com.abrahamcaijin.commonutil' name='CommonUtil' rev='1.0.2'>
  <artifact name='CommonUtil' ext='pom' ></artifact>
</dependency>
```
## How to use

```
这里说明一下，发布1.0.2的时候手抖，把初始化改成了Utils，除了1.0.2，其他的版本号都是AppUtils
// init it in the function of onCreate in ur Application
1.0.1  1.0.3...
AppUtils.init(context);
1.0.2
Utils.init(context);
```
## Proguard

```
-keep class com.jingewenku.abrahamcaijin.commonutil.** { *; }
-keepclassmembers class com.jingewenku.abrahamcaijin.commonutil.** { *; }
-dontwarn com.jingewenku.abrahamcaijin.commonutil.**
```
