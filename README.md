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
    getAll  : 返回所有的键值对<br>
## 提供App数据清理工作的类 → [AppCleanMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppCleanMgr.java)
    cleanInternalCache   ： 清除本应用内部缓存数据
    cleanExternalCache   ： 清除本应用外部缓存数据
    cleanDatabases       ： 清除本应用所有数据库
    cleanSharedPreference： 清除本应用SharedPreference
    cleanDatabaseByName  ： 根据名字清除本应用数据库
    cleanFiles           ： 清除本应用files文件
    cleanApplicationData ： 清除本应用所有的数据
    getAppClearSize      ： 获取App应用缓存的大小
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
    getAppName    : 获取本地apk的名称
    getVersionName: 获取本地Apk版本名称
    getVersionCode: 获取本地Apk版本号
    getMetaData   : 根据key获取xml中Meta的值
## 软键盘管理 → [AppKeyBoardMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppKeyBoardMgr.java)
    openKeybord      : 打卡软键盘
    closeKeybord     : 关闭软键盘
    TimerHideKeyboard: 通过定时器强制隐藏虚拟键盘
    isKeybord        : 输入法是否显示
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
    a   : assert日志或者打印是否执行到这里等
    i   : Info日志或者打印是否执行到这里等
    d   : Debug日志或者打印是否执行到这里等
    w   : Warn日志或者打印是否执行到这里等
    v   : Verbose日志或者打印是否执行到这里等
    e   : Error日志或者打印是否执行到这里等
    json: 输出Json的格式字符串
    xml : 输出xml的格式字符串
    file: 保存到文件
###### 更多使用方法信息点击这里查看：[KLog](http://kaizige.vip/2016/06/13/klog/)
## App网络管理 → [AppNetworkMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppNetworkMgr.java)
    getNetworkState   : 获取当前手机连接的网络类型
    isNetworkConnected: 判断网络是否连接
    openNetSetting    : 打开网络设置界面
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
    getBootTimeString    : 获取开机时间
    printSystemInfo      : 打印系统信息
    getNetType           : 获取网络类型
    getNativePhoneNumber : 获取当前设置的电话号码
    getMacAddress        : 获取 MAC 地址
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
    getStringByAssets: 根据本地Assets目录下资源名称，获取String数据信息
    getListByAssets  : 根据本地Assets目录下资源名称，获取List集合信息
    getStringByRaw   : 根据本地Raw目录下资源标识，获取String数据信息
    getListByRaw     : 根据本地Raw目录下资源标识，获取List集合信息
## 有关Android屏幕的工具类 → [AppScreenMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppScreenMgr.java)
    getScreenWidth          : 获得屏幕宽度
    getScreenHeight         : 获得屏幕高度
    getStatusHeight         : 获得状态栏的高度
    getRealScreenHeight     : 获取整块屏幕的高度
    getNavigationAreaHeight : 获取虚拟按键区域的高度
    snapShotWithStatusBar   : 获取当前屏幕截图，包含状态栏
    snapShotWithoutStatusBar: 获取当前屏幕截图，不包含状态栏
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
    isPeculiarStr        :判断是否有特殊字符
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
    getLevel          : 获取指定Wifi的信号强度
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
    timeStampToDateTime   : 将Long类型转成年月日时分秒
    string2Date           : 将年月日时分秒转成Date类型
    date2String           : 将Date类型转成年月日时分秒
    dateIsBefore          : 比较日期
    minutesBetweenTwoDate : 相差多少分钟
    getChineseZodiac      : 获取日期中的生肖
    getZodiac             : 获取日期中的星座
    getNowDayOffset       : 获取日期
    getTime               : 获取日期
    forward               : 使日期倒一天
    isLeapYear            : 判断平年闰年
    getDaysOfMonth        : 计算某月的天数
    secondsMorning        : 获取当天凌晨的秒数
    secondsNight          : 获取第二天凌晨的秒数
    isSameDay             : 判断某两天是不是同一天
    formatFriendly        : 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
    formatDateTime        : 将日期以yyyy-MM-dd HH:mm:ss格式化
    formatDateTime        : 将日期以yyyy-MM-dd HH:mm:ss格式化
    formatDateTime        : 将日期以yyyy-MM-dd HH:mm:ss格式化
    parseDate             : 将日期字符串转成日期
    gainCurrentDate       : 获取系统当前日期
    compareDate           : 验证日期是否比当前日期早
    addDateTime           : 对日期进行增加操作
    subDateTime           : 对日期进行相减操作
    formatDateForExcelDate: 格式化excel中的时间
    formatDateForFileName : 将日期格式化作为文件名
    formatDateSecond      : 格式化日期(精确到秒)
    tempDateSecond        : 格式化日期(精确到秒)
    tempDateSecond        : 格式化日期(精确到秒)
    formatDateDay         : 格式化日期(精确到天)
    formatDateDetailDay   : 式化日期(精确到天)
    formatNumber          : double类型的数字保留两位小数（四舍五入）
    formateDate           : 将字符串转换成日期
    parseStringToDate     : 将字符日期转换成Date
    formatDoubleNumber    : 将double日期转换成String
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
## 控件点击效果动画工具类 → [ToolAnimation](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/ToolAnimation.java)
    addTouchDrak : 给视图添加点击效果,让背景变深
    addTouchLight: 给视图添加点击效果,让背景变暗
## view管理类 → [ViewUtils](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/ViewUtils.java)
    removeSelfFromParent: 把自身从父View中移除
    isTouchInView       : 判断触点是否落在该View上
## 提供APP应用计算，算法等 -> [AppCalculateMgr](https://github.com/AbrahamCaiJin/CommonUtilLibrary/blob/master/CommonUtil/src/main/java/com/jingewenku/abrahamcaijin/commonutil/AppCalculateMgr.java)
    distance        : 两点间的距离
    pointTotoDegrees: 计算点a(x,y)的角度
    checkInRound    : 点在圆内
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
## 图片管理工具类 → [AppImageMgr]()
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
    computeSampleSize          : 使用该算法，就可动态计算出图片的inSampleSize。
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
## About
    这里把开发中常用的一些工具类做一个整理，主要用到的时候不用再费力的百度或者Google了，大家有需要的可以随时借鉴走，同时也希望这个越来越强大，有什么错误的地方还希望各位不吝指出，让我得以完善，当然，在此声明，这些并不是我一个人，也用了许多前辈们的东西，这里一并感谢，目前还在测试，还没有上传到Jcenter，需要的自己拷贝，测试完成后会给大家贴出引用地址的，持续更新中。<br />
    我的qq群：523167548<br />
    我的博客：http://blog.csdn.net/u014727709?viewmode=contents<br />
    对你有用就给一个Start吧！
