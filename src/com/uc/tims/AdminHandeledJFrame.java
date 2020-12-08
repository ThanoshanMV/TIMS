package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Cursor;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class AdminHandeledJFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AdminHandeledJFrame frame = new AdminHandeledJFrame();
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
	public AdminHandeledJFrame() {

		setTitle("Admin Home");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));
		// setIconImage(Toolkit.getDefaultToolkit().getImage(AdminHandeledJFrame.class.getResource("/timsLogo.ico")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 403);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("What you want to do Admin ?\n");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(109, 91, 310, 52);
		contentPane.add(lblNewLabel);

		JButton btnAddUser = new JButton("Add User");
		btnAddUser.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAddUser.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAddUser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// create instance of UserRegistrationJFrame
				UserRegistrationJFrame userRegistrationJFrameObject = new UserRegistrationJFrame();

				// make it visible
				userRegistrationJFrameObject.setVisible(true);

				// center this JFrame
				userRegistrationJFrameObject.setLocationRelativeTo(null);

				// dispose current JFrame
				dispose();
				// SqliteConnection.createUserTable();
			}
		});
		btnAddUser.setBounds(86, 198, 114, 36);
		contentPane.add(btnAddUser);

		JButton btnAccess = new JButton("Access TIMS");
		btnAccess.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAccess.setFont(new Font("Dialog", Font.BOLD, 15));
		btnAccess.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// create instance of DashboardJFrame
				DashboardJFrame dashboardJFrame = new DashboardJFrame();

				// make it visible
				dashboardJFrame.setVisible(true);

				// center this JFrame
				dashboardJFrame.setLocationRelativeTo(null);

				// dispose current JFrame
				dispose();

			}
		});
		btnAccess.setBounds(305, 198, 144, 36);
		contentPane.add(btnAccess);

		JLabel lblLogout = new JLabel("");
		lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// create instance of StartJFrame
				StartJFrame startJFrameObject = new StartJFrame();

				// make it visible
				startJFrameObject.setVisible(true);

				// center this JFrame
				startJFrameObject.setLocationRelativeTo(null);

				// dispose current JFrame
				dispose();
			}
		});
		lblLogout.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogout.setBounds(401, 277, 48, 46);
		Image imageLog = new ImageIcon(this.getClass().getResource("/logout.png")).getImage();
		lblLogout.setIcon(new ImageIcon(imageLog));
		contentPane.add(lblLogout);

		JLabel label_1 = new JLabel("Log out");
		label_1.setFont(new Font("Dialog", Font.BOLD, 14));
		label_1.setBounds(394, 326, 70, 22);
		contentPane.add(label_1);
	}
}
