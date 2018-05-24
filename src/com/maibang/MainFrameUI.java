/**
 * 
 */
package com.maibang;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.xvolks.jnative.exceptions.NativeException;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

/**
 * @author jason
 *
 */
public class MainFrameUI {
	
	private Log log = LogFactory.get();

	private JFrame frame;

	private final ButtonGroup buttonGroup = new ButtonGroup();
	JTextArea dataArea = new JTextArea();
	String s100 = "";

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		
		MainFrameUI window = new MainFrameUI();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					window.frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		ReadPointsCardThread cardThread = new ReadPointsCardThread(window);
		cardThread.run();
	}

	private static void InitGlobalFont(Font font) {
		FontUIResource fontRes = new FontUIResource(font);
		for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
			Object key = keys.nextElement();
			Object value = UIManager.get(key);
			if (value instanceof FontUIResource) {
				UIManager.put(key, fontRes);
			}
		}
	}

	/**
	 * Create the application.
	 */
	public MainFrameUI() {
		log.info("MainFrameUI start initialize.");
		initialize();
		log.info("MainFrameUI initialize finish.");
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public void output(String s) {
		dataArea.setText(dataArea.getText() + s);
	}

	public void checkdata(String s) {
		String t0 = "";
		for (int i = 0; i < s.length(); i++) {
			char t1 = s.charAt(i);
			if ((t1 >= '0' && t1 <= '9') || (t1 >= 'A' && t1 <= 'Z') || (t1 >= 'a' && t1 <= 'z')) {
				t0 = t0 + t1;
			}
		}
		s100 = t0;
	}

	private void initialize() {

		InitGlobalFont(new Font("微软雅黑", Font.PLAIN, 12));
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			UIManager.put("RootPane.setupButtonVisible", false);
			BeautyEyeLNFHelper.translucencyAtFrameInactive = false;
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			System.err.println("set skin fail!");
		}

		/**
		 * The MainWindow UI.
		 */
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane);

		JPanel controlPanel = new JPanel();
		tabbedPane.addTab("\u79ef\u5206\u5361\u8bfb\u53d6", null, controlPanel, null);
		controlPanel.setLayout(null);

		JLabel pointsTypeLabel = new JLabel("刷卡类型：");
		pointsTypeLabel.setBounds(10, 11, 65, 15);
		controlPanel.add(pointsTypeLabel);

		JRadioButton mornCheckInRadio = new JRadioButton("早晨签到", true);
		buttonGroup.add(mornCheckInRadio);
		mornCheckInRadio.setBounds(70, 7, 86, 23);
		controlPanel.add(mornCheckInRadio);

		JRadioButton mornCheckOutRadio = new JRadioButton("下午离开", false);
		buttonGroup.add(mornCheckOutRadio);
		mornCheckOutRadio.setBounds(168, 7, 86, 23);
		controlPanel.add(mornCheckOutRadio);

		JLabel currentLabel = new JLabel("当前状态：");
		currentLabel.setBounds(10, 45, 65, 15);
		controlPanel.add(currentLabel);

		JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(70, 45, 65, 15);
		controlPanel.add(statusLabel);

		JButton readPointsCardsBtn = new JButton("\u5f00\u59cb\u8bfb\u5361");
		readPointsCardsBtn.setBounds(10, 78, 100, 25);
		controlPanel.add(readPointsCardsBtn);

		JButton stopPointsCardsBtn = new JButton("\u505c\u6b62\u8bfb\u5361");
		stopPointsCardsBtn.setBounds(126, 78, 100, 25);
		controlPanel.add(stopPointsCardsBtn);

		JButton readCardsNoBtn = new JButton("读取卡号");
		readCardsNoBtn.setBounds(241, 78, 100, 25);
		controlPanel.add(readCardsNoBtn);

		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));

		JLabel lblData = new JLabel("\u5237\u5361\u7ed3\u679c:");
		panel_1.add(lblData, BorderLayout.NORTH);

		JScrollPane scrollPane = new JScrollPane();
		panel_1.add(scrollPane, BorderLayout.CENTER);

		dataArea = new JTextArea();
		scrollPane.setViewportView(dataArea);

		// ultralight
		readPointsCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("开始读卡");
				Consts.readFlag = true;
			}
		});

		mornCheckInRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Consts.action = Consts.CHECK_IN;

			}
		});

		mornCheckOutRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Consts.action = Consts.CHECK_OUT;

			}
		});

		stopPointsCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("停止读卡");
				Consts.readFlag = false;
			}
		});

		readCardsNoBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				StringBuffer outputSb = new StringBuffer();

				byte mode = 0x01;
				int ret = Function.UL_Request(mode);
				if (ret == 0) {
					outputSb.append("\u8bfb\u5361\u6210\u529f\uff0c\u5361\u53f7\u662f\uff1a");
					for (int i = 0; i < 7; i++) {
						try {
							outputSb.append(String.format("%02X", Function.a.getAsByte(i)) + "");
						} catch (NativeException e1) {
							JOptionPane.showMessageDialog(frame, "\u63d0\u793a", "\u7cfb\u7edf\u5185\u90e8\u9519\u8bef\uff0c\u8bf7\u8054\u7cfb\u6280\u672f\u4eba\u5458\uff01", 0);
						}
					}
					outputSb.append("\n");

					output(outputSb.toString());
				} else {
					Function.falsereason(Integer.toString(ret));
					output(Function.reason);
					Function.falsereason(String.format("%02X", Function.byte0));
					output(Function.reason + "\n");
					Function.reason = "";
				}
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

}
