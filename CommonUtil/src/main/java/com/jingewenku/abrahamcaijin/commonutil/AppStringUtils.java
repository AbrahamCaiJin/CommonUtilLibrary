package com.jingewenku.abrahamcaijin.commonutil;
/**
* 主要功能： 字符判断工具类
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
*/
public class AppStringUtils {
	/**
	 * 判断字符串是否为空
	 * @param str 字符串
	 * @return
	 */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
	/**
	 * 判断str null,"","null" 均视为空.
	 * @param str      字符
	 * @return 结果 boolean
	 */
	public static boolean isNotEmpty(String str) {
		boolean bool = true;
		if (str == null || "null".equals(str) || "".equals(str)) {
			bool = false;
		} else {
			bool = true;
		}
		return bool;
	}
	/**
	 * 
	 * 检测String是否全是中文
	 * @param name
	 * @return
	 */

	public static boolean checkNameChese(String name) {
		boolean res = true;
		char[] cTemp = name.toCharArray();
		for (int i = 0; i < name.length(); i++) {
			if (!isChinese(cTemp[i])) {
				res = false;
				break;
			}
		}
		return res;
	}
	/**
	 * 
	 * 判定输入汉字
	 * @param c
	 * @return
	 */

	public static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {

			return true;
		}
		return false;

	}
}
