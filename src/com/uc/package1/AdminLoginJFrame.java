package com.uc.package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AdminLoginJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtuname;
	private JPasswordField txtpassword;
	private String username;
	private String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AdminLoginJFrame frame = new AdminLoginJFrame();
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
	public AdminLoginJFrame() {
		setTitle("Admin login page");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 455, 491);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAdminLogin = new JLabel("Admin Login");
		lblAdminLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminLogin.setFont(new Font("Serif", Font.BOLD, 20));
		lblAdminLogin.setBounds(149, 66, 168, 45);
		contentPane.add(lblAdminLogin);

		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUsername.setBounds(47, 158, 113, 62);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(47, 238, 90, 45);
		contentPane.add(lblPassword);

		txtuname = new JTextField();
		txtuname.setBorder(null);
		txtuname.setForeground(new Color(51, 51, 51));
		txtuname.setBackground(new Color(255, 255, 255));
		txtuname.setFont(new Font("Dialog", Font.BOLD, 15));
		txtuname.setBounds(201, 175, 204, 28);
		contentPane.add(txtuname);
		txtuname.setColumns(10);

		txtpassword = new JPasswordField();
		txtpassword.setForeground(new Color(51, 51, 51));
		txtpassword.setBorder(null);
		txtpassword.setBackground(new Color(255, 255, 255));
		txtpassword.setFont(new Font("Dialog", Font.BOLD, 15));
		txtpassword.setBounds(201, 246, 204, 28);
		contentPane.add(txtpassword);

		JButton btnLogin = new JButton("Login\n");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminLoginJFrame object1 = new AdminLoginJFrame();
				object1.setUsername(txtuname.getText());
				object1.setPassword(String.valueOf(txtpassword.getPassword()));

				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					ps = SqliteConnection.establishSqliteConnection()
							.prepareStatement(StaticMembers.sqlQueryForAdminLogIn);
					ps.setString(1, object1.getUsername());
					ps.setString(2, object1.getPassword());
					rs = ps.executeQuery();
					System.out.println("Username is " + object1.getUsername());
					System.out.println("Password is " + object1.getPassword());
					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Login successful");
						StaticMembers.name = rs.getString("USERNAME");
						StaticMembers.adminLoggedin = true;
						AdminHandeledJFrame adminHandeledJFrame = new AdminHandeledJFrame();
						adminHandeledJFrame.setVisible(true);
						adminHandeledJFrame.setLocationRelativeTo(null);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Check Username or Password.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Error while establishing connection.");
				} finally {
					try {
						ps.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						rs.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					try {
						SqliteConnection.establishSqliteConnection().close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnLogin.setBounds(291, 336, 121, 37);
		contentPane.add(btnLogin);

		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(51, 51, 51));
		btnBack.setBorder(null);
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				StartJFrame startJFrameObject = new StartJFrame();
				startJFrameObject.setVisible(true);
				startJFrameObject.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBounds(47, 336, 113, 37);
		contentPane.add(btnBack);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
