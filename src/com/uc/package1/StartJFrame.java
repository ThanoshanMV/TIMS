package com.uc.package1;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.WindowConstants;

import java.awt.Color;
import java.awt.Cursor;
import javax.swing.SwingConstants;

public class StartJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					StartJFrame frame = new StartJFrame();
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
	public StartJFrame() {
		SqliteConnection.createBackupTable();
		SendEmail.UpdateChecker();
		SendEmail.backupNow();

		setTitle("Welcome-TIMS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 635, 454);
		contentPane = new JPanel();
		contentPane.setBorder(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		setLocationRelativeTo(null);

		JLabel lblNewLabel = new JLabel("Three-Wheel Information Management System");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		lblNewLabel.setBounds(12, 162, 611, 45);
		contentPane.add(lblNewLabel);

		JButton btnAdmin = new JButton("ADMIN\n");
		btnAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAdmin.setRequestFocusEnabled(false);
		btnAdmin.setForeground(new Color(51, 51, 51));
		btnAdmin.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminLoginJFrame adminLoginObject = new AdminLoginJFrame();
				adminLoginObject.setVisible(true);
				adminLoginObject.setLocationRelativeTo(null);
				dispose();
				SqliteConnection.createAdminTable();
			}
		});
		btnAdmin.setBounds(109, 301, 125, 37);
		contentPane.add(btnAdmin);

		JButton btnUser = new JButton("USER");
		btnUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUser.setForeground(new Color(51, 51, 51));
		btnUser.setFont(new Font("Dialog", Font.BOLD, 15));
		btnUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserLoginJFrame userLoginJFrame = new UserLoginJFrame();
				userLoginJFrame.setVisible(true);
				userLoginJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnUser.setBounds(411, 301, 125, 37);
		contentPane.add(btnUser);

		JLabel lblUC = new JLabel("");
		lblUC.setHorizontalAlignment(SwingConstants.CENTER);
		lblUC.setBounds(29, 12, 125, 150);
		Image imageUcLogo = new ImageIcon(this.getClass().getResource("/uc.png")).getImage();
		lblUC.setIcon(new ImageIcon(imageUcLogo));
		contentPane.add(lblUC);

		JLabel lblHattondickoyaUc = new JLabel("Hatton-Dickoya Urban Council");
		lblHattondickoyaUc.setHorizontalAlignment(SwingConstants.CENTER);
		lblHattondickoyaUc.setFont(new Font("Serif", Font.BOLD, 25));
		lblHattondickoyaUc.setBounds(173, 62, 436, 45);
		contentPane.add(lblHattondickoyaUc);

		JLabel lblTmis = new JLabel("TIMS");
		lblTmis.setHorizontalAlignment(SwingConstants.CENTER);
		lblTmis.setFont(new Font("Dialog", Font.BOLD, 25));
		lblTmis.setBounds(12, 207, 611, 45);
		contentPane.add(lblTmis);

		JLabel label = new JLabel("2013 - 2023");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 20));
		label.setBounds(12, 235, 611, 45);
		contentPane.add(label);

	}
}
