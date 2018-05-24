/**
 * 
 */
package com.maibang;

/**
 * @author jason
 *
 */
public class Consts {
	
	/**
	 * 讀標誌
	 */
	public static boolean readFlag = false;
	
	public static String action = "CKIN";
	
	/**
	 * 早晨刷卡
	 */
	public static final String CHECK_IN = "CKIN";
	
	/**
	 * 下午離開
	 */
	public static final String CHECK_OUT = "CKOUT";
	
	/**
	 * 購物
	 */
	public static final String SHOP = "SHOP";
	
	/**
	 * 醫療
	 */
	public static final String DOCT = "DOCT";
	
	public static final int SLEEP_SECONDS = 2000;
	
	//public static final String RECORD_POINTS_URL = "http://www.mbflk.com/pointsRecord/save";
	public static final String RECORD_POINTS_URL = "http://10.144.19.125:8080/pointsRecord/save";
}
