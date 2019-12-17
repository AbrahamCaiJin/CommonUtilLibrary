package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年12月20日 16:42
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

import java.util.Calendar;

/**
 * 身份证转换工具类
 *
 * <P>
 * 第十八位数字的计算方法为：
 * </P>
 * <P>
 * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2;
 * 2.将这17位数字和系数相乘的结果相加; 3.用加出来和除以11，看余数是多少？ 4余数只可能有0 1 2 3 4 5 6 7 8 9
 * 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2;
 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
 * </P>
 */
public class IDCardUtils {
    /**
     * 15位的身份证号码转换成18位
     * @param idCardNo15 待转换的 15 位身份证号码
     * @return
     */
    public static String from15to18(String idCardNo) {
        String finalID = null;// 最终的ID
        if (!isIdCardNo(idCardNo))
            throw new IllegalArgumentException("您输入的身份证号码不正确:" + idCardNo);
        String century = "19";
        if (idCardNo.length() == 18) {
            finalID = idCardNo;
        } else {
            // 对应的17位的各个系数
            int[] weight = new int[] { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10,
                5, 8, 4, 2 };

            // 通过加入世纪码, 变成 17 位的新号码本体.
            String tempNo = idCardNo.substring(0, 6) + century
                + idCardNo.substring(6);

            // 下面算最后一位校验码
            int checkSum = 0;
            for (int i = 0; i < weight.length; i++) {
                int ai = Integer.parseInt("" + tempNo.charAt(i)); // 位于 i 位置的数值
                checkSum = checkSum + ai * weight[i];
            }
            // 求余数
            int checkNum = checkSum % 11;
            String lastNumber = null;
            switch (checkNum) {
                case 0:
                    lastNumber = "1";
                    break;
                case 1:
                    lastNumber = "0";
                    break;
                case 2:
                    lastNumber = "X";
                    break;
                case 3:
                    lastNumber = "9";
                    break;
                case 4:
                    lastNumber = "8";
                    break;
                case 5:
                    lastNumber = "7";
                    break;
                case 6:
                    lastNumber = "6";
                    break;
                case 7:
                    lastNumber = "5";
                    break;
                case 8:
                    lastNumber = "4";
                    break;
                case 9:
                    lastNumber = "3";
                    break;
                case 10:
                    lastNumber = "2";
                    break;
            }
            finalID = tempNo + lastNumber;
        }

        return finalID;

    }

    /**
     * 18位的身份证号码转换成15位
     * @param idCardNo18
     * @return
     */
    public static String from18to15(String idCardNo18) {
        if (!(isIdCardNo(idCardNo18) && idCardNo18.length() == 18)) {
            throw new IllegalArgumentException("身份证号参数格式不正确！");
        }
        return idCardNo18.substring(0, 6) + idCardNo18.substring(8, 17);
    }

    /**
     * 传入18位的身份证号,得到生日,格式:19880101
     * @param idCardNo18
     * @return
     */
    public static String getBirthday(String idCardNo18) {
        return idCardNo18.substring(6, 14);
    }

    /**
     * 根据身份证号码获取年龄
     * @param idCardNo18
     * @return
     */
    public static int getAge(String idCardNo18) {
        idCardNo18 = from15to18(idCardNo18);
        // 当前年份
        Calendar instance = Calendar.getInstance();
        int currentYear = instance.get(Calendar.YEAR);
        int age = currentYear - Integer.parseInt(getBirthYear(idCardNo18));
        return age;
    }

    /**
     * 传入身份证号,得到性别
     * @param idCardNo18
     * @return
     */
    public static String getSex(String idCardNo18) {
        idCardNo18 = from15to18(idCardNo18);
        // 定位到倒数第二位
        char c = idCardNo18.charAt(16);
        String sex = "";
        if (c % 2 != 0) {
            sex = "男";
        } else {
            sex = "女";
        }
        return sex;
    }

    /**
     * 传入身份证号,获取出生年份
     * @param idCardNo18
     * @return
     */
    private static String getBirthYear(String idCardNo18) {
        return idCardNo18.substring(6, 10);
    }

    /**
     * 判断输入的身份证号码是否符合身份证号的要求
     * @param idCardNO
     * @return
     */
    public static boolean isIdCardNo(String idCardNO) {
        boolean isID = false;
        if (idCardNO == null)
            return false;
        int len = idCardNO.length();
        if (len != 15 && len != 18) {
            isID = false;
        } else {
            // 排除最后一位是:X的情况
            for (int i = 0; i < len; i++) {
                try {
                    if (i < len - 1)
                        Integer.parseInt("" + idCardNO.charAt(i));
                    else if ((idCardNO.charAt(i) + "").equalsIgnoreCase("X")
                        || Integer.parseInt("" + idCardNO.charAt(i)) > -1)
                        isID = true;
                    else
                        isID = false;
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return isID;
    }

    public static void main(String[] args) {
        System.out.println(getSex(from15to18("110104830512301")));
    }
}