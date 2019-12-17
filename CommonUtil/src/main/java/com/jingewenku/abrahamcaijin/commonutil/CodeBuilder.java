package com.jingewenku.abrahamcaijin.commonutil;

/**
 * @Description:主要功能:
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年12月20日 16:43
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */


import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 编号生成工具
 */
@SuppressWarnings("Duplicates")
public abstract class CodeBuilder {
    /**
     * 生成新的流水编号
     *
     * @return 20位字符串
     */
    public static final String newTxCode() {
        // 年月日时分秒毫秒(15位)
        String time = new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date());
        StringBuilder builder = new StringBuilder(time);
        Random random = new Random();
        // 随机数(5位)
        for (int i = 0; i < 5; i++) {
            int nextInt = random.nextInt(10);
            builder.append(nextInt);
        }

        return builder.toString();
    }

    public static final String genNextRadixCode(String precode) throws Exception {
        if (AppStringUtils.isEmpty(precode)) {
            return "01";
        }
        int n=Integer.parseInt(precode, 32);
        n++;
        return ten2radix(n,36).length()==1?"0"+ten2radix(n,36):ten2radix(n,36);
    }

    public static void main(String[] args) {
        try {
            System.out.println(genNextRadixCode("ZX"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * baseString 递归调用
     *
     * @param num  十进制数
     * @param base 要转换成的进制数
     */
    public static String ten2radix(int num, int base) {
        String str = "", digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (num == 0) {
            return "";
        } else {
            str = ten2radix(num / base, base);
            return str + digit.charAt(num % base);
        }
    }

    /**
     * baseString 递归调用
     *
     * @param num  十进制数
     * @param base 要转换成的进制数
     */
    public static String ten2radix(BigInteger num, int base) {
        String str = "", digit = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        if (num.shortValue() == 0) {
            return "";
        } else {
            BigInteger valueOf = BigInteger.valueOf(base);
            str = ten2radix(num.divide(valueOf), base);
            return str + digit.charAt(num.mod(valueOf).shortValue());
        }
    }

    //随机生成指定长度的代码,包含数字
    public static final String generateRandomNumber(int length) {
        String allChar = "0123456789";
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(allChar.charAt(random.nextInt(allChar.length())));
        }
        return sb.toString();
    }

}