# API

## BankCheck -> 银行卡管理 
    checkBankCard       : 校验银行卡卡号是否合法
    getBankCardCheckCode: 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
    getNameOfBank       : 通过银行卡的前六位确定判断银行开户行及卡种
## AppSharePreferenceMgr -> SharePreference缓存数据
    put     : 保存数据的方法
    get     : 获取数据的方法
    putImage: 保存图片到SharedPreferences
    getImage: 从SharedPreferences读取图片
    remove  : 移除某个key值已经对应的值
    clear   : 清除所有数据
    contains: 查询某个key是否已经存在
    getAll  : 返回所有的键值对<br>
## AppCleanMgr -> 提供App数据清理工作的类
    cleanInternalCache   ： 清除本应用内部缓存数据
    cleanExternalCache   ： 清除本应用外部缓存数据
    cleanDatabases       ： 清除本应用所有数据库
    cleanSharedPreference： 清除本应用SharedPreference
    cleanDatabaseByName  ： 根据名字清除本应用数据库
    cleanFiles           ： 清除本应用files文件
    cleanApplicationData ： 清除本应用所有的数据
    getAppClearSize      ： 获取App应用缓存的大小
## AppExit2Back -> App应用退出
    exitApp: 退出App程序应用
## AppDavikActivityMgr -> 管理和回收Activity
    getScreenManager      : 单例堆栈集合对象
    removeActivity        : 堆栈中销毁并移除
    removeAllActivity     : 栈中销毁并移除所有Act对象
    currentActivity       : 取当前Act对象
    getCurrentActivityName: 获得当前Act的类名
    addActivity           : 将Act纳入推栈集合中
    exitApp               : 退出栈中所有Activity
## AppApplicationMgr -> 获取App应用版本信息
    getAppName    : 获取本地apk的名称
    getVersionName: 获取本地Apk版本名称
    getVersionCode: 获取本地Apk版本号
    getMetaData   : 根据key获取xml中Meta的值
## AppKeyBoardMgr -> 软键盘管理
    openKeybord      : 打卡软键盘
    closeKeybord     : 关闭软键盘
    TimerHideKeyboard: 通过定时器强制隐藏虚拟键盘
    isKeybord        : 输入法是否显示
## AppLogMessageMgr -> 系统日志输出工具类
    isEnableDebug: 设置log总开关,debug模式(true:打印日志  false：不打印)
    i            : Info日志
    d            : Debug日志
    w            : Warn日志
    v            : Verbose日志
    e            : Error日志
    这里推荐使用凯子哥的日志管理工具，灰常好用: [KLog](http://kaizige.vip/2016/06/13/klog/)  
## AppNetworkMgr -> App网络管理
    getNetworkState   : 获取当前手机连接的网络类型
    isNetworkConnected: 判断网络是否连接
    openNetSetting    : 打开网络设置界面
## AppPhoneMgr -> 手机管理工具类
    getInstance        : 单例对象
    getSDKVersionNumber: 获取手机系统版本号
    getPhoneModel      : 获取手机型号
    getPhoneWidth      : 获取手机宽度
    getPhoneHeight     : 获取手机高度
    getPhoneImei       : 获取手机imei串号 ,GSM手机的 IMEI 和 CDMA手机的 MEID
    getPhoneSim        : 获取手机sim卡号
    getPhoneNum        : 获取手机号
    isSDCardMount      : 判断sd卡是否挂载
    getSDFreeSize      : 获取sd卡剩余空间的大小
    getSDAllSize       : 获取sd卡空间的总大小
    isTablet           : 判断是否是平板
    isApkInstalled     : 判断一个apk是否安装
    call               : 拨打电话
    openWeb            : 打开网页
    getAppPermissions  : 获取应用权限 名称列表
    getInstalledApp    : 获取手机内安装的应用
    getUserInstalledApp: 获取手机安装非系统应用
    getInstalledAppInfo: 获取安装应用的信息
    startAppPkg        : 打开指定包名的应用
    unInstallApk       : 卸载指定包名的应用
## AppReflectionMgr -> 反射工具类
    getProperty          : 得到某个对象的公共属性
    getStaticProperty    : 得到某类的静态公共属性
    invokeMethod         : 执行某对象方法
    invokeStaticMethod   : 执行某类的静态方法
    newInstance          : 新建实例
    isInstance           : 是不是某个类的实例
    getByArray           : 得到数组中的某个元素
    GetClassListByPackage:得到类的集合
## AppResourceMgr -> 获取本地指定资源信息
    getStringByAssets: 根据本地Assets目录下资源名称，获取String数据信息
    getListByAssets  : 根据本地Assets目录下资源名称，获取List集合信息
    getStringByRaw   : 根据本地Raw目录下资源标识，获取String数据信息
    getListByRaw     : 根据本地Raw目录下资源标识，获取List集合信息
## AppScreenMgr -> 有关Android屏幕的工具类
    getScreenWidth          : 获得屏幕宽度
    getScreenHeight         : 获得屏幕高度
    getStatusHeight         : 获得状态栏的高度
    getRealScreenHeight     : 获取整块屏幕的高度
    getNavigationAreaHeight : 获取虚拟按键区域的高度
    snapShotWithStatusBar   : 获取当前屏幕截图，包含状态栏
    snapShotWithoutStatusBar: 获取当前屏幕截图，不包含状态栏
## AppSysMgr -> 获取App应用系统基本信息
    getSysClientOs             : 获得客户端操作系统名称
    getSysSdk                  : 获取当前操作系统的sdk版本
    getSysLanguage             : 获取当前操作系统的语言
    getSysModel                :获取手机型号
    getSysRelease              : 获取操作系统的版本号
    getSysSIMSerialNum         : 读取SIM卡序列号
    getSysCPUSerialNum         : 获取手机CPU序列号
    getSysTelephonyManager     : 获得电话管理实例对象
    getSysTelephoneSerialNum   : 读唯一的设备ID(唯一的设备ID【GSM手机的IMEI】和【CDMA手机的 MEID】,如果获取不到返回一个默认字符串)
    getSysCarrier              : 获取运营商信息(三大运营商)
    getSysPhoneState           : 获取手机状态(0：无活动 1：响铃 2：待机)
    getSysPhoneLoaction        : 获得手机方位
    getSysDeviceSoftVersion    :获得设备的软件版本号(注：the IMEI/SV(software version) for GSM phones 不支持返回“not available”)
    getSysPhoneNumber          : 获得手机号
    getSysSimCode              : 获得SIM卡提供的移动国家码和移动网络码.5或6位的十进制数字。(注：SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断))
    getSysSimPrivatorName      : 服务商名称(注:例如：中国移动、联通SIM卡的状态必须是 SIM_STATE_READY(使用getSimState()判断))
    getSysUserPhoneId          : 唯一的用户ID (注：例如：IMSI(国际移动用户识别码) for a GSM phone. 需要权限：READ_PHONE_STATE)
    getWindowManager           : 获取WindowManager对象
    getSysDefaultThreadPoolSize: 获得系统配置相符的线程池大小
    getSysSampleSize           : 获取当前APP应用的SampleSize大小
    getVibrator                : 获取震动器对象
    getSysLocalIpAddress       : 获取手机IP地址
## AppToastMgr -> 自定义Toast提示框
    shortToast : 自定义Toast调用
    longToast  : 自定义Toast调用
    cancelToast: 取消显示Toast
    Toast      : 默认Toast调用
## AppValidationMgr -> 正则表达式
    isEmpty      : 验证是否为空串 (包括空格、制表符、回车符、换行符组成的字符串 若输入字符串为null或空字符串,返回true)
    isNotEmpty   : 是否不为空
    isNotZero    : 验证非零正整数
    isNumber     : 验证是数字
    isUpChar     : 验证是大写字母
    isLowChar    : 验证是小写字母
    isLetter     : 验证是英文字母
    isChinese    : 验证输入汉字
    isRealName   : 验证真实姓名
    isOneCode    : 验证是否是条形码
    isEmail      : 验证邮箱是否正确
    isPhone      : 验证手机号是否正确
    isPlane      : 验证座机号码是否正确
    isPostalCode : 验证邮政编码是否正确
    isIpAddress  : 验证IP地址是否正确
    isURL        : 验证URL地址是否正确
    isInteger    : 验证是否是正整数
    isPoint      : 验证是否是小数
    isBankNo     : 验证是否银行卡号
    isIDCard     : 验证身份证号码是否正确
    isPeculiarStr:判断是否有特殊字符
    isUserName   : 判断是否为用户名账号(规则如下：用户名由下划线或字母开头，由数字、字母、下划线、点、减号组成的4-32位字符)
## AppWifiHelperMgr -> Wifi管理工具类
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
## ConvertUtils -> 类型转换类
    hexStringToBytes: 十六进制字符串转换为byte数组
    bytesToHexString: byte数组转换为十六进制字符串
    charToByte      : char转换为byte数组 
    intToByte       : int转换为byte数组
    byteToInt       : byte数组转换为int
    saveDecimals    : 保留几位小数
## DateUtils -> 日期管理类
    getNowDayOffset: 获取日期
    getTime        : 获取日期
    forward        : 使日期倒一天
    isLeapYear     : 判断平年闰年
    getDaysOfMonth : 计算某月的天数
    secondsMorning : 获取当天凌晨的秒数
    secondsNight   : 获取第二天凌晨的秒数
    isSameDay      : 判断某两天是不是同一天
## DensityUtils ->手机常用单位转换的辅助类
    dip2px: 据手机的分辨率从 dip 的单位 转成为 px(像素)
    px2dip: 根据手机的分辨率从 px(像素) 的单位 转成为 dp
    dp2px : dp转px
    sp2px : sp转px
    px2dp : px转dp
    px2sp : px转sp
## FileUtils -> 文件管理类
    hasSdcard    : 检查是否存在SD卡
    createFileDir: 创建目录
    delFile      : 删除文件（若为目录，则递归删除子目录和文件）
    getFileSize  : 取文件大小，单位为byte（若为目录，则包括所有子目录和文件）
    saveBitmap   : 保存Bitmap到指定目录
    isFileExists : 判断某目录下文件是否存在
## ToastUtil -> Toast信息工具类
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
## ToolAnimation -> 控件点击效果动画工具类
    addTouchDrak : 给视图添加点击效果,让背景变深
    addTouchLight: 给视图添加点击效果,让背景变暗
## ViewUtils -> view管理类
    removeSelfFromParent: 把自身从父View中移除
    isTouchInView       : 判断触点是否落在该View上
## ToolPhone -> 手机相关操作API
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
## ToolFile -> 文件工具类
    isMountedSDCard    : 检查是否已挂载SD卡镜像（是否存在SD卡）
    gainSDFreeSize     : 获取SD卡剩余容量（单位Byte）
    gainSDAllSize      : 获取SD卡总容量（单位Byte）
    gainSDCardPath     : 获取可用的SD卡路径（若SD卡不没有挂载则返回""）
    readFileByLines    : 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
    readFileByLines    : 以行为单位读取文件内容，一次读一整行，常用于读面向行的格式化文件
    saveToFile         : 保存内容
    saveToFile         : 指定编码保存内容
    appendToFile       : 追加文本
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
    write              : 写入应用程序包files目录下文件
    write              : 写入应用程序包files目录下文件
    write              : 指定编码将内容写入目标文件
    write              : 指定目录写入文件内容
    write              : 写入文件
    saveAsJPEG         : 指定目录写入文件内容
    saveAsPNG          : 指定目录写入文件内容
## ToolDateTime -> 日期管理类
    formatFriendly: 将日期格式化成友好的字符串：几分钟前、几小时前、几天前、几月前、几年前、刚刚
    formatDateTime: 将日期以yyyy-MM-dd HH:mm:ss格式化
    formatDateTime: 将日期以yyyy-MM-dd HH:mm:ss格式化
    formatDateTime: 将日期以yyyy-MM-dd HH:mm:ss格式化
    parseDate: 将日期字符串转成日期
    gainCurrentDate: 获取系统当前日期
    compareDate: 验证日期是否比当前日期早
    addDateTime: 对日期进行增加操作
    subDateTime: 对日期进行相减操作
    formatDateForExcelDate: 格式化excel中的时间
    formatDateForFileName: 将日期格式化作为文件名
    formatDateSecond: 格式化日期(精确到秒)
    tempDateSecond: 格式化日期(精确到秒)
    tempDateSecond: 格式化日期(精确到秒)
    formatDateDay: 格式化日期(精确到天)
    formatDateDetailDay: 式化日期(精确到天)
    formatNumber: double类型的数字保留两位小数（四舍五入）
    formateDate: 将字符串转换成日期
    parseStringToDate: 将字符日期转换成Date
    formatDoubleNumber: 将double日期转换成String
    
## About
    这里把开发中常用的一些工具类做一个整理，主要用到的时候不用再费力的百度或者Google了，大家有需要的可以随时借鉴走，同时也希望这个越来越强大，有什么错误的地方还希望各位不吝指出，让我得以完善，当然，在此声明，这些并不是我一个人，也用了许多前辈们的东西，这里一并感谢，目前还在测试，还没有上传到Jcenter，需要的自己拷贝，测试完成后会给大家贴出引用地址的，持续更新中。<br />
    我的qq群：523167548<br />
    我的博客：http://blog.csdn.net/u014727709?viewmode=contents<br />
    对你有用就给一个Start吧！
