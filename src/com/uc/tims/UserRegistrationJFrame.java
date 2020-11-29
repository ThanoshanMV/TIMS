package com.uc.tims;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Employee;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.WindowConstants;
import javax.swing.SwingConstants;
import java.awt.Cursor;

public class UserRegistrationJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtuname;
	private JTextField txtnic;
	private JTextField txtuc;
	private JPasswordField txtpassword;
	
	private Employee employee; 
	private Connection connection; 
	private PreparedStatement preparedStatement;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserRegistrationJFrame frame = new UserRegistrationJFrame();
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
	public UserRegistrationJFrame() {
		
		// set new employee instance 
		setEmployee(new Employee());
		
		setTitle("User registration form");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 467, 551);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel usrRegiForm = new JLabel("User Registration  Form");
		usrRegiForm.setFont(new Font("Dialog", Font.BOLD, 15));
		usrRegiForm.setBounds(120, 62, 209, 42);
		contentPane.add(usrRegiForm);

		JLabel lblName = new JLabel("Name\n");
		lblName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblName.setBounds(39, 139, 73, 34);
		contentPane.add(lblName);

		JLabel lblUsername = new JLabel("Username\n\n");
		lblUsername.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUsername.setBounds(39, 192, 86, 34);
		contentPane.add(lblUsername);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPassword.setBounds(39, 244, 86, 34);
		contentPane.add(lblPassword);

		JLabel lblUcPosition = new JLabel("UC Position\n");
		lblUcPosition.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUcPosition.setBounds(39, 363, 123, 34);
		contentPane.add(lblUcPosition);

		txtname = new JTextField();
		txtname.setHorizontalAlignment(SwingConstants.LEFT);
		txtname.setFont(new Font("Dialog", Font.BOLD, 15));
		txtname.setBounds(237, 143, 177, 27);
		contentPane.add(txtname);
		txtname.setColumns(10);

		txtuname = new JTextField();
		txtuname.setHorizontalAlignment(SwingConstants.LEFT);
		txtuname.setFont(new Font("Dialog", Font.BOLD, 15));
		txtuname.setColumns(10);
		txtuname.setBounds(237, 196, 177, 27);
		contentPane.add(txtuname);

		txtnic = new JTextField();
		txtnic.setHorizontalAlignment(SwingConstants.LEFT);
		txtnic.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnic.setColumns(10);
		txtnic.setBounds(237, 310, 177, 27);
		contentPane.add(txtnic);

		txtuc = new JTextField();
		txtuc.setHorizontalAlignment(SwingConstants.LEFT);
		txtuc.setFont(new Font("Dialog", Font.BOLD, 15));
		txtuc.setColumns(10);
		txtuc.setBounds(237, 367, 177, 27);
		contentPane.add(txtuc);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employee.setName(txtname.getText());
				employee.setUserName(txtuname.getText());
				employee.setPassword(String.valueOf(txtpassword.getPassword()));
				employee.setNic(txtnic.getText());
				employee.setJob(txtuc.getText());

				if (employee.getName().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid name!");
				} else if (UserExistenceTIMS.isNameExist(employee.getName())) {
					JOptionPane.showMessageDialog(null, "Sorry, name has already taken!");
				}

				else if (employee.getUserName().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid username!");
				}

				else if (UserExistenceTIMS.isUserNameExist(employee.getUserName())) {
					JOptionPane.showMessageDialog(null, "Sorry, username has already taken!");
				} else if (employee.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid password!");
				} else if (employee.getNic().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid nic!");
				} else if (!((employee.getNic().length() == 10)
						|| (employee.getNic().length() == 12))) {
					JOptionPane.showMessageDialog(null, "Sorry, Check NIC length!");
				} else if (employee.getJob().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid UC position!");
				} else {
					try {
						// establishing MySQL connection
						connection = MySQLConnection.establishMySqlConnection();
						
						// creating prepared statement to execute parameterized query
						preparedStatement = connection.prepareStatement(StaticMembers.sqlQueryForUserRegistration);

						// setting vales using PreparedStatement's setter methods 
						// registering for user, his (user) role id is 2 
						preparedStatement.setInt(1, 2);
						preparedStatement.setString(2, employee.getName());
						preparedStatement.setString(3, employee.getUserName());
						preparedStatement.setString(4, employee.getNic());
						preparedStatement.setString(5, employee.getJob());
						preparedStatement.setString(6, employee.getPassword());
						
						// executeUpdate() returns either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
						if (preparedStatement.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "User Registration Successful!");
							// create instance of AdminHandeledJFrame
							AdminHandeledJFrame adminHandeledJFrame = new AdminHandeledJFrame();
							
							// make it visible 
							adminHandeledJFrame.setVisible(true);
							
							// center this JFrame
							adminHandeledJFrame.setLocationRelativeTo(null);
							
							// dispose the current JFrame
							dispose();
						}
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null, e1);
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
			}
		});
		btnSubmit.setBounds(300, 443, 114, 34);
		contentPane.add(btnSubmit);

		txtpassword = new JPasswordField();
		txtpassword.setHorizontalAlignment(SwingConstants.LEFT);
		txtpassword.setFont(new Font("Dialog", Font.BOLD, 15));
		txtpassword.setBounds(237, 248, 177, 27);
		contentPane.add(txtpassword);

		JButton btnBack = new JButton("Back");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AdminHandeledJFrame adminHandeledJFrame = new AdminHandeledJFrame();
				adminHandeledJFrame.setVisible(true);
				adminHandeledJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBounds(39, 443, 114, 34);
		contentPane.add(btnBack);
		
		JLabel label = new JLabel("NIC Number");
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(39, 306, 103, 34);
		contentPane.add(label);
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public PreparedStatement getPreparedStatement() {
		return preparedStatement;
	}

	public void setPreparedStatement(PreparedStatement preparedStatement) {
		this.preparedStatement = preparedStatement;
	}
}
