package com.maibang;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class P381Problem6B extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	private Set Hobbies = new HashSet();//��Ȥ����
    private String Gender ="";//�Ա�  
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					P381Problem6B frame = new P381Problem6B();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public P381Problem6B() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 414, 74);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel Sex = new JLabel("�Ա�");
		Sex.setBounds(10, 49, 54, 15);
		panel.add(Sex);
		
		JRadioButton Man = new JRadioButton("��");
		Man.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gender =Man.getText(); 
			    PrintJTextArea();
			}
		});
		buttonGroup.add(Man);
		Man.setBounds(70, 45, 66, 23);
		panel.add(Man);
		
		JRadioButton Fale = new JRadioButton("Ů");
		Fale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gender =Fale.getText(); 
			    PrintJTextArea();
			}
		});
		buttonGroup.add(Fale);
		Fale.setBounds(138, 45, 66, 23);
		panel.add(Fale);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 414, 303);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}

	protected void PrintJTextArea() {
		// TODO Auto-generated method stub
		textArea.setText(""); 
	      if (Hobbies.size() > 0)//��� Set ��������Ԫ�أ���ӡ��Ȥ  
	        textArea.append("�����Ȥ������: "); 
	     Iterator it = Hobbies.iterator(); 
	     while (it.hasNext()) { 
	    	 textArea.append(it.next() + " "); 
	      }
	     if (!"".equals(Gender))//��� Gender ��Ϊ���ַ�������ӡ�Ա� 
	    	 textArea.append("\n����Ա�Ϊ�� " + Gender); 
	}
}
