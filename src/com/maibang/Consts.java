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

	public static int point = 0;

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

	public static final String RECORD_POINTS_URL = "http://www.mbflk.com/pointsRecord/save";
	// public static final String RECORD_POINTS_URL = "http://10.144.19.125:8080/pointsRecord/save";

	public static final String EXPLANATION_LABEL = "<html><h5>说明：</h5>1.读取卡号按钮，用于开卡时读取卡号，然后将卡号和对应客户信息配置到系统积分卡管理页面中；<br>2.开始读卡按钮，用于日常客户签到时使用，点击该按钮后，客户讲卡放置到读卡器上，系统会自动读取客户卡号并记录到系统中。<br>3.使用中出现其他问题，请及时联系研发人员。</html>";
}
