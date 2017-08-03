package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能: 类型转换类
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:26
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class ConvertUtils {
    private static final char[] DIGITS_LOWER = { '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
    private static final char[] DIGITS_UPPER = { '0', '1', '2', '3', '4', '5',
        '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

    private ConvertUtils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 十六进制字符串转换为byte数组
     *
     * @param hexString
     * @return
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * char转换为byte数组
     * @param c
     * @return
     */
    public static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    /**
     * 16进制转化为数字
     * @param ch 16进制
     * @param index 索引
     * @return 转化结果
     * @throws Exception 转化失败异常
     */
    private static int toDigit(final char ch, final int index)
        throws Exception {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new Exception("Illegal hexadecimal character " + ch
                + " at index " + index);
        }
        return digit;
    }

    /**
     * bytes数组转16进制String
     * @param data bytes数组
     * @param toDigits DIGITS_LOWER或DIGITS_UPPER
     * @return 转化结果
     */
    private static String bytes2Hex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        // two characters form the hex value.
        for (int i = 0, j = 0; i < l; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
        return new String(out);
    }

    /**
     * byte数组转换为十六进制字符串
     *
     * @param b
     * @return
     */
    public static String bytesToHexString(byte[] b) {
        if (b.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < b.length; i++) {
            int value = b[i] & 0xFF;
            String hv = Integer.toHexString(value);
            if (hv.length() < 2) {
                sb.append(0);
            }

            sb.append(hv);
        }
        return sb.toString();
    }

    /**
     * int转换为byte数组
     *
     * @param res
     * @return
     */
    public static byte[] intToByte(int res) {
        byte[] targets = new byte[4];
        targets[0] = (byte) (res & 0xff);// 最低位
        targets[1] = (byte) ((res >> 8) & 0xff);// 次低位
        targets[2] = (byte) ((res >> 16) & 0xff);// 次高位
        targets[3] = (byte) (res >>> 24);// 最高位,无符号右移。
        return targets;
    }

    /**
     * byte数组转换为int
     *
     * @param res
     * @return
     */
    public static int byteToInt(byte[] res) {
        // 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
        int targets = (res[3] & 0xff) | ((res[2] << 8) & 0xff00) | ((res[1] << 16) & 0xff0000) | ((res[0] << 24) & 0xff000000);
        return targets;
    }



    /**
     * 保留几位小数
     */
    public static String saveDecimals(int cnt, double value) {
        if (cnt == 2)
            return String.format("%.02f", value);
        else if (cnt == 1)
            return String.format("%.01f", value);
        else
            return String.format("%.0f", value);
    }

    /**
     * null转String
     * @param str
     * @return
     */
    public static String nullOfString(String str) {
        if (str == null) {
            str = "";
        }
        return str;
    }

    /**
     * String转Byte
     * @param str
     * @return
     */
    public static byte stringToByte(String str) {
        byte b = 0;
        if (str != null) {
            try {
                b = Byte.parseByte(str);
            } catch (Exception e) {

            }
        }
        return b;
    }

    /**
     * String转Boolean
     * @param str
     * @return
     */
    public static boolean stringToBoolean(String str) {
        if (str == null) {
            return false;
        } else {
            if (str.equals("1")) {
                return true;
            } else if (str.equals("0")) {
                return false;
            } else {
                try {
                    return Boolean.parseBoolean(str);
                } catch (Exception e) {
                    return false;
                }
            }
        }
    }

    /**
     * String转Int
     * @param str
     * @return
     */
    public static int stringToInt(String str) {
        int i = 0;
        if (str != null) {
            try {
                i = Integer.parseInt(str.trim());
            } catch (Exception e) {
                i = 0;
            }

        } else {
            i = 0;
        }
        return i;
    }

    /**
     * String转Short
     * @param str
     * @return
     */
    public static short stringToShort(String str) {
        short i = 0;
        if (str != null) {
            try {
                i = Short.parseShort(str.trim());
            } catch (Exception e) {
                i = 0;
            }
        } else {
            i = 0;
        }
        return i;
    }

    /**
     * String转Double
     * @param str
     * @return
     */
    public static double stringToDouble(String str) {
        double i = 0;
        if (str != null) {
            try {
                i = Double.parseDouble(str.trim());
            } catch (Exception e) {
                i = 0;
            }
        } else {
            i = 0;
        }
        return i;
    }

    /**
     * Int转String
     * @param i
     * @return
     */
    public static String intToString(int i) {
        String str = "";
        try {
            str = String.valueOf(i);
        } catch (Exception e) {
            str = "";
        }
        return str;
    }

    /**
     * Double转Long
     * @param d
     * @return
     */
    public static long doubleToLong(double d) {
        long lo = 0;
        try {
            //double转换成long前要过滤掉double类型小数点后数据
            lo = Long.parseLong(String.valueOf(d).substring(0, String.valueOf(d).lastIndexOf(".")));
        } catch (Exception e) {
            lo = 0;
        }
        return lo;
    }

    /**
     * Double转Int
     * @param d
     * @return
     */
    public static int doubleToInt(double d) {
        int i = 0;
        try {
            //double转换成long前要过滤掉double类型小数点后数据
            i = Integer.parseInt(String.valueOf(d).substring(0, String.valueOf(d).lastIndexOf(".")));
        } catch (Exception e) {
            i = 0;
        }
        return i;
    }

    /**
     * Long转Double
     * @param d
     * @return
     */
    public static double longToDouble(long d) {
        double lo = 0;
        try {
            lo = Double.parseDouble(String.valueOf(d));
        } catch (Exception e) {
            lo = 0;
        }
        return lo;
    }

    /**
     * Long转Int
     * @param d
     * @return
     */
    public static int longToInt(long d) {
        int lo = 0;
        try {
            lo = Integer.parseInt(String.valueOf(d));
        } catch (Exception e) {
            lo = 0;
        }
        return lo;
    }

    /**
     * String转Long
     * @param str
     * @return
     */
    public static long stringToLong(String str) {
        Long li = new Long(0);
        try {
            li = Long.valueOf(str);
        } catch (Exception e) {
            //li = new Long(0);
        }
        return li.longValue();
    }

    /**
     * Long转String
     * @param li
     * @return
     */
    public static String longToString(long li) {
        String str = "";
        try {
            str = String.valueOf(li);
        } catch (Exception e) {

        }
        return str;
    }

}
