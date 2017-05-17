package com.jingewenku.abrahamcaijin.commonutil;



/**
 * 主要功能： 提供APP应用计算，算法等
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
public class AppCalculateMgr {

	/**
	 * 两点间的距离
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public static double distance(double x1,double y1,double x2,double y2)
	{
		return Math.sqrt(Math.abs(x1-x2)*Math.abs(x1-x2)+Math.abs(y1-y2)*Math.abs(y1-y2));
	}

	/**
	 * 计算点a(x,y)的角度
	 * @param x
	 * @param y
	 * @return
	 */
	public static double pointTotoDegrees(double x,double y)
	{
		return Math.toDegrees(Math.atan2(x,y));
	}
	
	
	/**
	 * 点在圆肉
	 * @param sx
	 * @param sy
	 * @param r
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean checkInRound(float sx, float sy, float r, float x, float y)
	{
		return Math.sqrt((sx - x) * (sx - x) + (sy - y) * (sy - y)) < r;
	}
}
