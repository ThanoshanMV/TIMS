package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Employee;
import com.uc.tims.mysql.MySQLQueryMethod;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Color;
import javax.swing.SwingConstants;

public class AdminLoginJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtuname;
	private JPasswordField txtpassword;

	private Employee employee;
	private ResultSet resultSet;
	private MySQLQueryMethod mySQLQueryMethod;

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

		// creating Employee object
		employee = new Employee();

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
				employee.setUserName(txtuname.getText());
				employee.setPassword(String.valueOf(txtpassword.getPassword()));

				// execute the selected query and return an instance of ResultSet
				resultSet = mySQLQueryMethod.loginAdmin(employee);

				// resultSet.next() returns true if the new current row is valid otherwise false
				// if there are no more rows
				try {
					if (resultSet.next()) {
						// valid row from query is available!
						JOptionPane.showMessageDialog(null, "Login successful");

						// getting username column value for this specified row
						StaticMembers.name = resultSet.getString("username");

						// setting adminLoggenin as true because of admin's login was success
						StaticMembers.adminLoggedin = true;

						// create instance of AdminHandeledJFrame
						AdminHandeledJFrame adminHandeledJFrame = new AdminHandeledJFrame();

						// make it visible
						adminHandeledJFrame.setVisible(true);

						// center this JFrame
						adminHandeledJFrame.setLocationRelativeTo(null);

						// dispose the current JFrame
						dispose();
					} else {
						// no valid row for that query is available!
						JOptionPane.showMessageDialog(null, "Check Username or Password.");
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
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
				// create instance of StartJFrame
				StartJFrame startJFrameObject = new StartJFrame();

				// make it visible
				startJFrameObject.setVisible(true);

				// center this JFrame
				startJFrameObject.setLocationRelativeTo(null);

				// dispose current (AdminLoginJFrame)
				dispose();
			}
		});
		btnBack.setBounds(47, 336, 113, 37);
		contentPane.add(btnBack);
	}

}
