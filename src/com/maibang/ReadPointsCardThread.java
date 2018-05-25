/**
 * 
 */
package com.maibang;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import org.xvolks.jnative.exceptions.NativeException;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author jason
 *
 */
public class ReadPointsCardThread implements Runnable {

	private Log log = LogFactory.get();

	private MainFrameUI mainFrameUI;

	private static StringBuffer outputSb = new StringBuffer();

	private static StringBuffer idCardNoSb = new StringBuffer();

	private static String prefixCardNo = "";

	public ReadPointsCardThread(MainFrameUI mainFrameUI) {
		super();
		this.mainFrameUI = mainFrameUI;
	}

	public static void cleanHistoryRecord() {
		outputSb.delete(0, outputSb.length());
		idCardNoSb.delete(0, idCardNoSb.length());
		prefixCardNo = "";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		while (true) {

			if (!Consts.readFlag) {
				try {
					Thread.sleep(Consts.SLEEP_SECONDS);
				} catch (InterruptedException e1) {
					JOptionPane.showMessageDialog(mainFrameUI.getFrame(), "\u63d0\u793a", "\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01", 0);
					continue;
				}
				continue;
			}

			outputSb = new StringBuffer();
			idCardNoSb = new StringBuffer();

			byte mode = 0x01;
			int ret = Function.UL_Request(mode);
			if (ret == 0) {
				outputSb.append("\u5237\u5361\u6210\u529f\uff0c\u5361\u53f7\u662f\uff1a");
				for (int i = 0; i < 7; i++) {
					try {
						outputSb.append(String.format("%02X", Function.a.getAsByte(i)) + "");
						idCardNoSb.append(String.format("%02X", Function.a.getAsByte(i)) + "");
					} catch (NativeException e1) {
						JOptionPane.showMessageDialog(mainFrameUI.getFrame(), "\u63d0\u793a", "\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01", 0);
					}
				}
				outputSb.append("\n");

				log.info("prefixCardNo=" + prefixCardNo + ", idCardNoSb=" + idCardNoSb + ", equals=" + prefixCardNo.equalsIgnoreCase(idCardNoSb.toString()));

				if (!prefixCardNo.equalsIgnoreCase(idCardNoSb.toString())) {
					mainFrameUI.output(outputSb.toString());
					sendHttpRequest(idCardNoSb.toString());
					prefixCardNo = idCardNoSb.toString();
				}
			} else {
				Function.falsereason(Integer.toString(ret));
				log.info(Function.reason);
				Function.falsereason(String.format("%02X", Function.byte0));
				log.info(Function.reason + "\n");
				Function.reason = "";
			}

			try {
				Thread.sleep(Consts.SLEEP_SECONDS);
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(mainFrameUI.getFrame(), "\u63d0\u793a", "\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01", 0);
			}
		}

	}

	private void sendHttpRequest(String idCardNo) {

		try {
			if (StrUtil.isEmpty(idCardNo)) {
				return;
			}

			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("action", Consts.action);
			paramMap.put("imei", idCardNo);
			paramMap.put("point", Consts.point);

			String json = JSONUtil.parseFromMap(paramMap).toString();

			HttpRequest.post(Consts.RECORD_POINTS_URL).body(json).execute().body();
		} catch (HttpException e) {
			log.error("has exception:", e);
		}
	}
}
