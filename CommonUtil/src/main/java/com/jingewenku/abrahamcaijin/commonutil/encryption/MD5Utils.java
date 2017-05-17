package com.jingewenku.abrahamcaijin.commonutil.encryption;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description:主要功能:MD5加密 不可逆（Message Digest，消息摘要算法）
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil.encryption
 * @author: AbrahamCaiJin
 * @date: 2017年05月16日 15:56
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */

public class MD5Utils {

    private MD5Utils() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * MD5加密
     */
    public static String encryptMD5(String securityStr) {
        byte[] data = securityStr.getBytes();
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            md5.update(data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] resultBytes = md5.digest();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < resultBytes.length; i++) {
            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {
                builder.append("0").append(
                    Integer.toHexString(0xFF & resultBytes[i]));
            } else {
                builder.append(Integer.toHexString(0xFF & resultBytes[i]));
            }
        }
        return builder.toString();
    }

}
