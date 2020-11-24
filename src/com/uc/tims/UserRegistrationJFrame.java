package com.uc.tims;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.sql.PreparedStatement;
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
	private String name;
	private String username;
	private String nic;
	private String uc;
	private String password;

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

		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNicNumber.setBounds(39, 294, 103, 34);
		contentPane.add(lblNicNumber);

		JLabel lblUcPosition = new JLabel("UC Position\n");
		lblUcPosition.setFont(new Font("Dialog", Font.BOLD, 15));
		lblUcPosition.setBounds(39, 347, 123, 34);
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
		txtnic.setBounds(237, 298, 177, 27);
		contentPane.add(txtnic);

		txtuc = new JTextField();
		txtuc.setHorizontalAlignment(SwingConstants.LEFT);
		txtuc.setFont(new Font("Dialog", Font.BOLD, 15));
		txtuc.setColumns(10);
		txtuc.setBounds(237, 351, 177, 27);
		contentPane.add(txtuc);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSubmit.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSubmit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserRegistrationJFrame userRegistrationObject = new UserRegistrationJFrame();
				userRegistrationObject.setName(txtname.getText());
				userRegistrationObject.setUsername(txtuname.getText());
				userRegistrationObject.setPassword(String.valueOf(txtpassword.getPassword()));
				userRegistrationObject.setNic(txtnic.getText());
				userRegistrationObject.setUc(txtuc.getText());

				if (userRegistrationObject.getName().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid name!");
				} else if (UserExistenceTIMS.isNameExist(userRegistrationObject.getName())) {
					JOptionPane.showMessageDialog(null, "Sorry, name has already taken!");
				}

				else if (userRegistrationObject.getUsername().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid username!");
				}

				else if (UserExistenceTIMS.isUserNameExist(userRegistrationObject.getUsername())) {
					JOptionPane.showMessageDialog(null, "Sorry, username has already taken!");
				} else if (userRegistrationObject.getPassword().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid password!");
				} else if (userRegistrationObject.getNic().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid nic!");
				} else if (!((userRegistrationObject.getNic().length() == 10)
						|| (userRegistrationObject.getNic().length() == 12))) {
					JOptionPane.showMessageDialog(null, "Sorry, Check NIC length!");
				} else if (userRegistrationObject.getUc().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid UC position!");
				} else {

					PreparedStatement ps = null;

					try {
						ps = SqliteConnection.establishSqliteConnection()
								.prepareStatement(StaticMembers.sqlQueryForUserRegistration);
						ps.setString(1, userRegistrationObject.getName());
						ps.setString(2, userRegistrationObject.getUsername());
						ps.setString(3, userRegistrationObject.getPassword());
						ps.setString(4, userRegistrationObject.getNic());
						ps.setString(5, userRegistrationObject.getUc());

						if (ps.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "User Registration Successful!");
							AdminHandeledJFrame adminHandeledJFrame = new AdminHandeledJFrame();
							adminHandeledJFrame.setVisible(true);
							adminHandeledJFrame.setLocationRelativeTo(null);
							dispose();
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
							SqliteConnection.establishSqliteConnection().close();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
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
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	public String getUc() {
		return uc;
	}

	public void setUc(String uc) {
		this.uc = uc;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
