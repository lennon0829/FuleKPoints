/**
 * 
 */
package com.maibang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import java.util.List;

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
import org.jb2011.lnf.beautyeye.ch3_button.BEButtonUI;
import org.xvolks.jnative.exceptions.NativeException;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.setting.dialect.Props;

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
	
	Props props = new Props("config.properties");

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

		InitGlobalFont(new Font("\u5FAE\u8F6F\u96C5\u9ED1", Font.PLAIN, 12));
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.translucencySmallShadow;
			UIManager.put("RootPane.setupButtonVisible", false);
		} catch (Exception e) {
			log.error("set skin fail!");
		}

		/**
		 * The MainWindow UI.
		 */
		frame = new JFrame();
		frame.setBounds(100, 100, 908, 608);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 2, 0, 0));

		JPanel mainPanel = new JPanel(new GridLayout(1, 1));
		frame.getContentPane().add(mainPanel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		mainPanel.add(tabbedPane);

		JPanel controlPanel = new JPanel();
		tabbedPane.addTab("\u79ef\u5206\u5361\u8bfb\u53d6", null, controlPanel, null);
		controlPanel.setLayout(null);

		JLabel pointsTypeLabel = new JLabel("\u5237\u5361\u7c7b\u578b\uff1a");
		pointsTypeLabel.setBounds(10, 11, 65, 15);
		controlPanel.add(pointsTypeLabel);

		JRadioButton mornCheckInRadio = new JRadioButton("\u65e9\u6668\u7b7e\u5230", true);
		buttonGroup.add(mornCheckInRadio);
		mornCheckInRadio.setBounds(70, 7, 86, 23);
		controlPanel.add(mornCheckInRadio);

		/**
		 * 下午离开
		 */
		JRadioButton nightCheckOutRadio = new JRadioButton("\u4e0b\u5348\u79bb\u5f00", false);
		buttonGroup.add(nightCheckOutRadio);
		nightCheckOutRadio.setBounds(168, 7, 86, 23);
		controlPanel.add(nightCheckOutRadio);

		JLabel currentLabel = new JLabel("\u5f53\u524d\u72b6\u6001\uff1a");
		currentLabel.setBounds(10, 45, 65, 15);
		controlPanel.add(currentLabel);

		JLabel statusLabel = new JLabel("");
		statusLabel.setBounds(70, 45, 65, 15);
		statusLabel.setFont(new Font("\u5FAE\u8F6F\u96C5\u9ED1", Font.BOLD, 12));
		controlPanel.add(statusLabel);

		JButton readPointsCardsBtn = new JButton("\u5f00\u59cb\u8bfb\u5361");
		readPointsCardsBtn.setBounds(10, 78, 100, 30);
		controlPanel.add(readPointsCardsBtn);

		JButton stopPointsCardsBtn = new JButton("\u505c\u6b62\u8bfb\u5361");
		stopPointsCardsBtn.setBounds(126, 78, 100, 30);
		stopPointsCardsBtn.setUI(new BEButtonUI().setNormalColor(BEButtonUI.NormalColor.red));
		stopPointsCardsBtn.setForeground(Color.white);
		controlPanel.add(stopPointsCardsBtn);

		JButton readCardsNoBtn = new JButton("\u8bfb\u53d6\u5361\u53f7");
		readCardsNoBtn.setBounds(241, 78, 100, 30);
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

		JLabel explanationLabel = new JLabel();
		explanationLabel.setBounds(20, 300, 400, 200);
		explanationLabel.setFont(new Font("\u5FAE\u8F6F\u96C5\u9ED1", Font.PLAIN, 11));
		explanationLabel.setText(Consts.EXPLANATION_LABEL);
		controlPanel.add(explanationLabel);

		// 开始读卡
		readPointsCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("\u5f00\u59cb\u8bfb\u5361");
				Consts.readFlag = true;
				ReadPointsCardThread.cleanHistoryRecord();
			}
		});

		/**
		 * 早晨签到
		 */
		mornCheckInRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Consts.action = Consts.CHECK_IN;
				ReadPointsCardThread.cleanHistoryRecord();
			}
		});

		/**
		 * 早晨签到
		 */
		nightCheckOutRadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Consts.action = Consts.CHECK_OUT;
				ReadPointsCardThread.cleanHistoryRecord();

			}
		});

		// 停止读卡
		stopPointsCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				statusLabel.setText("\u505c\u6b62\u8bfb\u5361");
				Consts.readFlag = false;
				ReadPointsCardThread.cleanHistoryRecord();
			}
		});

		readCardsNoBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				statusLabel.setText("\u8BFB\u53D6\u5361\u53F7");

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
	
	private List<PointType> loadPoints() {
		props
	}

}
