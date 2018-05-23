/**
 * 
 */
package com.maibang;

import javax.swing.JOptionPane;

import org.xvolks.jnative.exceptions.NativeException;

/**
 * @author jason
 *
 */
public class ReadPointsCardThread implements Runnable {

	private MainFrameUI mainFrameUI;

	public ReadPointsCardThread(MainFrameUI mainFrameUI) {
		super();
		this.mainFrameUI = mainFrameUI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		System.out.println("threadstart");
		while (true) {
			if (!Consts.readFlag) {
				try {
					Thread.sleep(Consts.SLEEP_SECONDS);
				} catch (InterruptedException e1) {
					JOptionPane.showMessageDialog(mainFrameUI.getFrame(), "\u63d0\u793a",
							"\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01",
							0);
					continue;
				}
				continue;
			}

			System.out.println("start1");
			byte mode = 0x01;
			int ret = Function.UL_Request(mode);
			if (ret == 0) {
				mainFrameUI.output("\u5237\u5361\u6210\u529f\uff0c\u5361\u53f7\u662f\uff1a:\n");
				for (int i = 0; i < 7; i++) {
					try {
						mainFrameUI.output(String.format("%02X", Function.a.getAsByte(i)) + "");
					} catch (NativeException e1) {
						JOptionPane.showMessageDialog(mainFrameUI.getFrame(), "\u63d0\u793a",
								"\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01",
								0);
					}
				}
				mainFrameUI.output("\n");
			} else {
				Function.falsereason(Integer.toString(ret));
				mainFrameUI.output(Function.reason);
				Function.falsereason(String.format("%02X", Function.byte0));
				mainFrameUI.output(Function.reason + "\n");
				Function.reason = "";
			}

			try {
				Thread.sleep(Consts.SLEEP_SECONDS);
			} catch (InterruptedException e1) {
				JOptionPane.showMessageDialog(mainFrameUI.getFrame(), "\u63d0\u793a",
						"\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01",
						0);
			}
		}

	}

}
