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
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Cursor;

public class UserLoginJFrame extends JFrame {

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
					UserLoginJFrame frame = new UserLoginJFrame();
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
	public UserLoginJFrame() {

		employee = new Employee();

		mySQLQueryMethod = new MySQLQueryMethod();

		setTitle("User login");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 453);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblUserLogin = new JLabel("User Login");
		lblUserLogin.setFont(new Font("Dialog", Font.BOLD, 18));
		lblUserLogin.setBounds(194, 60, 127, 49);
		contentPane.add(lblUserLogin);

		JLabel label = new JLabel("Username");
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(55, 142, 103, 42);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Password");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.setBounds(55, 227, 103, 42);
		contentPane.add(label_1);

		txtuname = new JTextField();
		txtuname.setHorizontalAlignment(SwingConstants.LEFT);
		txtuname.setFont(new Font("Dialog", Font.BOLD, 15));
		txtuname.setColumns(10);
		txtuname.setBounds(194, 150, 204, 28);
		contentPane.add(txtuname);

		txtpassword = new JPasswordField();
		txtpassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtpassword.setFont(new Font("Dialog", Font.BOLD, 15));
		txtpassword.setBounds(194, 238, 204, 28);
		contentPane.add(txtpassword);

		JButton btnBack = new JButton("Back");
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
		btnBack.setBounds(55, 322, 114, 36);
		contentPane.add(btnBack);

		JButton btnLogin = new JButton("Login\n");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Dialog", Font.BOLD, 15));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employee.setUserName(txtuname.getText());
				employee.setPassword(String.valueOf(txtpassword.getPassword()));

				try {

					resultSet = mySQLQueryMethod.loginAdmin(employee);

					if (resultSet.next()) {
						JOptionPane.showMessageDialog(null, "Login successful");
						StaticMembers.name = resultSet.getString("username");
						StaticMembers.adminLoggedin = false;
						DashboardJFrame dashboardJFrame = new DashboardJFrame();
						dashboardJFrame.setVisible(true);
						dashboardJFrame.setLocationRelativeTo(null);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Check Username or Password.");
					}
				}

				catch (SQLException e1) {
					JOptionPane.showMessageDialog(null, "Error while establishing connection.");
				} finally {
					try {
						resultSet.close();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		btnLogin.setBounds(284, 322, 114, 36);
		contentPane.add(btnLogin);

	}

}
