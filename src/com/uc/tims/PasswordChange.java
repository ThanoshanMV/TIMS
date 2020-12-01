package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Employee;
import com.uc.tims.mysql.MySQLConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.WindowConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

public class PasswordChange extends JFrame {

	private JPanel contentPane;
	private JPasswordField txtOld;
	private JPasswordField txtNew;
	private JPasswordField txtRe;

	private String oldPassword;
	private String newPassword;
	private String retypePassword;
	
	private Employee employee;
	private PreparedStatement preparedStatement = null;
	private Connection connection = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					PasswordChange frame = new PasswordChange();
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
	public PasswordChange() {
		
		employee = new Employee();
		
		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 506, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblOldPassword = new JLabel("Old Password");
		lblOldPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblOldPassword.setBounds(46, 88, 124, 33);
		contentPane.add(lblOldPassword);

		JLabel lblNewPassword = new JLabel("New Password");
		lblNewPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewPassword.setBounds(46, 143, 124, 33);
		contentPane.add(lblNewPassword);

		JLabel lblRetypePassword = new JLabel("Re-type Password");
		lblRetypePassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblRetypePassword.setBounds(46, 199, 152, 33);
		contentPane.add(lblRetypePassword);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PasswordChange object = new PasswordChange();
				object.setOldPassword(String.valueOf(txtOld.getPassword()));
				object.setNewPassword(String.valueOf(txtNew.getPassword()));
				object.setRetypePassword(String.valueOf(txtRe.getPassword()));
				
				employee.setPassword(object.getNewPassword());

				System.out.println(object.getNewPassword());
				System.out.println(StaticMembers.name);

				if (object.getNewPassword().equals(object.getRetypePassword())) {
					if (StaticMembers.adminLoggedin) {
						try {
							String sqlQueryForAdminPassChange = "UPDATE `employee` SET `password`= ? WHERE `username`= ?";
							
							connection = MySQLConnection.establishMySqlConnection();
							preparedStatement = connection.prepareStatement(sqlQueryForAdminPassChange);
							
							preparedStatement.setString(1, employee.getPassword());
							preparedStatement.setString(2, StaticMembers.name);

							if (preparedStatement.executeUpdate() > 0) {
								JOptionPane.showMessageDialog(null, "Successfully Password Changed!");
								DashboardJFrame dashboardJFrame = new DashboardJFrame();
								dashboardJFrame.setVisible(true);
								dashboardJFrame.setLocationRelativeTo(null);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Sorry, Check your fields");
							}
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Error while establishing connection.");
						} finally {
							try {
								preparedStatement.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								connection.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					} else {
						try {
							String sqlQueryForUserPassChange = "UPDATE `employee` SET `password`= ? WHERE `username`= ?";
							
							connection = MySQLConnection.establishMySqlConnection();
							
							preparedStatement = connection.prepareStatement(sqlQueryForUserPassChange);

							preparedStatement.setString(1, employee.getPassword());
							preparedStatement.setString(2, StaticMembers.name);

							if (preparedStatement.executeUpdate() > 0) {
								JOptionPane.showMessageDialog(null, "Successfully Password Changed!");
								DashboardJFrame dashboardJFrame = new DashboardJFrame();
								dashboardJFrame.setVisible(true);
								dashboardJFrame.setLocationRelativeTo(null);
								dispose();
							} else {
								JOptionPane.showMessageDialog(null, "Sorry, Check your fields");
							}
						} catch (SQLException e1) {
							JOptionPane.showMessageDialog(null, "Error while establishing connection.");
						} finally {
							try {
								preparedStatement.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
							try {
								connection.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Please retype password");
				}

			}
		});
		btnSave.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSave.setBounds(321, 274, 130, 39);
		contentPane.add(btnSave);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				dashboardJFrame.setVisible(true);
				dashboardJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBack.setBounds(46, 274, 130, 39);
		contentPane.add(btnBack);

		txtOld = new JPasswordField();
		txtOld.setFont(new Font("Dialog", Font.BOLD, 15));
		txtOld.setBounds(267, 92, 181, 26);
		contentPane.add(txtOld);

		txtNew = new JPasswordField();
		txtNew.setFont(new Font("Dialog", Font.BOLD, 15));
		txtNew.setBounds(267, 147, 181, 26);
		contentPane.add(txtNew);

		txtRe = new JPasswordField();
		txtRe.setFont(new Font("Dialog", Font.BOLD, 15));
		txtRe.setBounds(270, 199, 181, 26);
		contentPane.add(txtRe);

		JLabel lblChangePassword = new JLabel("Change Password");
		lblChangePassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangePassword.setFont(new Font("Dialog", Font.BOLD, 18));
		lblChangePassword.setBounds(158, 28, 204, 33);
		contentPane.add(lblChangePassword);
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getRetypePassword() {
		return retypePassword;
	}

	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}
	
}
