/**
 * 
 */
package com.maibang;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * @author jason
 *
 */
public class MainFrameUI {

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

	/**
	 * Create the application.
	 */
	public MainFrameUI() {
		initialize();
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
		
		JLabel Sex = new JLabel("刷卡类型：");
		Sex.setBounds(10, 49, 65, 15);
		controlPanel.add(Sex);
		
		JRadioButton mornCheckInRadio = new JRadioButton("早晨签到");
		buttonGroup.add(mornCheckInRadio);
		mornCheckInRadio.setBounds(70, 45, 86, 23);
		controlPanel.add(mornCheckInRadio);
		
		JRadioButton mornCheckOutRadio = new JRadioButton("下午离开");
		buttonGroup.add(mornCheckOutRadio);
		mornCheckOutRadio.setBounds(168, 45, 86, 23);
		controlPanel.add(mornCheckOutRadio);
		

		JButton readPointsCardsBtn = new JButton("\u5f00\u59cb\u8bfb\u5361");
		readPointsCardsBtn.setBounds(10, 7, 154, 25);
		controlPanel.add(readPointsCardsBtn);

		JButton stopPointsCardsBtn = new JButton("\u505c\u6b62\u8bfb\u5361");
		stopPointsCardsBtn.setBounds(181, 7, 154, 25);
		controlPanel.add(stopPointsCardsBtn);

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
				System.out.println("start");
				Consts.readFlag = true;
			}
		});

		stopPointsCardsBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Consts.readFlag = false;
			}
		});
	}

	public JFrame getFrame() {
		return frame;
	}

}
