package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Driver;
import com.uc.tims.mysql.MySQLConnection;
import com.uc.tims.mysql.MySQLQuery;
import com.uc.tims.mysql.MySQLQueryMethod;
import com.uc.tims.utilities.CharacterLimit;
import com.uc.tims.validator.mysqlvalidator.DriverValidator;
import com.uc.tims.validator.mysqlvalidator.MySQLValidatable;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Cursor;

public class AutoDriverRegistrationJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtparkno;
	private JTextField txtwheelno;
	private JTextField txtdrivername;
	private JTextField txtaddress;
	private JTextField txtnicnumber;
	private JTextField txtphonenumber;
	private JTextField txtImageUrl;

	
	private Driver driver; 
	private DriverValidator driverValidator;
	private Connection connection; 
	private PreparedStatement preparedStatement;
	private MySQLQueryMethod mySQLQueryMethod;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AutoDriverRegistrationJFrame frame = new AutoDriverRegistrationJFrame();
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
	public AutoDriverRegistrationJFrame() {
		
		// creating new driver object
		setDriver(new Driver());
		
		// create DriverValidator object
		driverValidator = new DriverValidator();
		
		setTitle("Driver registration form");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 634);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtparkno = new JTextField();
		txtparkno.setFont(new Font("Dialog", Font.BOLD, 15));
		txtparkno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// as the key released, we'll set that text in upper case
				txtparkno.setText(txtparkno.getText().toUpperCase());
			}
		});
		txtparkno.setColumns(10);
		txtparkno.setBounds(329, 128, 142, 26);
		contentPane.add(txtparkno);
		txtparkno.setText("A");

		JComboBox comboBoxParkName = new JComboBox();
		comboBoxParkName.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBoxParkName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// selected combo box item will be set into park number text
				txtparkno.setText((String) comboBoxParkName.getSelectedItem());
			}
		});
		comboBoxParkName.setModel(new DefaultComboBoxModel(driver.getAvailableparks()));
		comboBoxParkName.setBounds(329, 82, 133, 24);
		contentPane.add(comboBoxParkName);

		JComboBox comboBoxGs = new JComboBox();
		comboBoxGs.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBoxGs.setModel(new DefaultComboBoxModel(new String[] { "YES", "NO" }));
		comboBoxGs.setBounds(329, 404, 133, 24);
		contentPane.add(comboBoxGs);

		JLabel lblNewLabel = new JLabel("Three-Wheeler Registration Form");
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(155, 12, 354, 42);
		contentPane.add(lblNewLabel);

		JLabel lblPark = new JLabel("Park");
		lblPark.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPark.setBounds(54, 77, 73, 34);
		contentPane.add(lblPark);

		JLabel lblParkNo = new JLabel("Park No\n");
		lblParkNo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblParkNo.setBounds(54, 123, 73, 34);
		contentPane.add(lblParkNo);

		JLabel lblWheelNo = new JLabel("Wheel No\n");
		lblWheelNo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWheelNo.setBounds(54, 169, 97, 34);
		contentPane.add(lblWheelNo);

		JLabel lblDriverName = new JLabel("Driver Name\n");
		lblDriverName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDriverName.setBounds(54, 215, 133, 34);
		contentPane.add(lblDriverName);

		JLabel lblAddress = new JLabel("Address\n");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(54, 261, 73, 34);
		contentPane.add(lblAddress);

		JLabel lblNicNumber = new JLabel("NIC Number\n");
		lblNicNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNicNumber.setBounds(54, 307, 109, 34);
		contentPane.add(lblNicNumber);

		JLabel lblPhoneNumber = new JLabel("Phone Number\n");
		lblPhoneNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPhoneNumber.setBounds(54, 353, 133, 34);
		contentPane.add(lblPhoneNumber);

		JLabel lblGs = new JLabel("GS");
		lblGs.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGs.setBounds(54, 399, 73, 34);
		contentPane.add(lblGs);

		JButton btnRegister = new JButton("Register");
		btnRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnRegister.setFont(new Font("Dialog", Font.BOLD, 15));
		btnRegister.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driver.setPark((String) comboBoxParkName.getSelectedItem());
				driver.setParkNumber(txtparkno.getText());
				driver.setWheelNumber(txtwheelno.getText());
				driver.setName(txtdrivername.getText());
				driver.setAddress(txtaddress.getText());
				driver.setNic(txtnicnumber.getText());
				driver.setPhoneNumber(txtphonenumber.getText());
				driver.setGsDecision((String) comboBoxGs.getSelectedItem());
				driver.setImageUrl(txtImageUrl.getText());

				if (driver.getParkNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid park no!");
				} else if (driverValidator.isParkNumberExists(driver.getParkNumber())) {
					JOptionPane.showMessageDialog(null, "Sorry, Park Number has already taken!");
				}
				else if (driver.getWheelNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid wheel no!");
				}
				else if (driverValidator.isWheelNumberExists(driver.getWheelNumber())) {
					JOptionPane.showMessageDialog(null, "Sorry, Wheel Number has already taken!");
				} else if (driver.getName().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver name!");
				} else if (driverValidator.isNameExists(driver.getName())) {
					JOptionPane.showMessageDialog(null, "Sorry, Driver Name has already taken!");
				} else if (driver.getAddress().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid address!");
				} else if (driver.getNic().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid nic number!");
				} else if (driverValidator.isNICExists(driver.getNic())) {
					JOptionPane.showMessageDialog(null, "Sorry, NIC has already taken!");
				} else if (!((driver.getNic().length() == 10) || (driver.getNic().length() == 12))) {
					JOptionPane.showMessageDialog(null, "Sorry, Check NIC length!");
				} else if (driver.getPhoneNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid phone number!");
				} else if (!(driver.getPhoneNumber().length() == 10)) {
					JOptionPane.showMessageDialog(null, "Phone number should be in 10 characters!");
				} else if (driver.getGsDecision().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid GS information : OK or NO");
				} else if (driver.getImageUrl().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver image");
				} 
				else {
					try {
						// establishing MySQL connection
						connection = MySQLConnection.establishMySqlConnection();
						
						// creating prepared statement to execute parameterized query
						preparedStatement = connection.prepareStatement(MySQLQuery.sqlQueryForDriverRegistration);
						
						// setting vales using PreparedStatement's setter methods 
						// preparedStatement.setString(1, driver.getPaymentId());
						preparedStatement.setString(1, driver.getName());
						preparedStatement.setString(2, driver.getNic());
						preparedStatement.setString(3, driver.getPhoneNumber());
						preparedStatement.setString(4, driver.getWheelNumber());
						preparedStatement.setString(5, driver.getAddress());
						preparedStatement.setString(6, driver.getParkNumber());
						preparedStatement.setString(7, driver.getPark());
						preparedStatement.setBytes(8, driver.readImageFile(driver.getImageUrl()));
						preparedStatement.setString(9, driver.getImageUrl());
						preparedStatement.setString(10, driver.getGsDecision());

						// executeUpdate() returns either (1) the row count for SQL Data Manipulation Language (DML) statements or (2) 0 for SQL statements that return nothing
						if (preparedStatement.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Auto Driver Registration Successful!");
							
							// create instance of AdminHandeledJFrame
							AutoDriverRegistrationJFrame autoDriverRegistrationJFrame = new AutoDriverRegistrationJFrame();
							
							// make it visible 
							autoDriverRegistrationJFrame.setVisible(true);
							
							// center this JFrame
							autoDriverRegistrationJFrame.setLocationRelativeTo(null);
							
							// get now registered driver id from driver table 
							int driverId = mySQLQueryMethod.getDriverIdByNic(driver.getNic());
							
							// check if we have successfully get the driver id from driver table
							if(driverId != -1) {
							// insert a new row for newly registered driver 
							mySQLQueryMethod.insertPaymentRow(driverId, driver.getName(),driver.getNic(), driver.getPark());
							}
							else {
								System.out.println("Can not able to retrieve driver id");
							}
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
		btnRegister.setBounds(468, 521, 114, 34);
		contentPane.add(btnRegister);

		JButton btnCancel = new JButton("Back");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 15));
		btnCancel.addActionListener(new ActionListener() {
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
		btnCancel.setBounds(54, 521, 114, 34);
		contentPane.add(btnCancel);

		txtwheelno = new JTextField();
		txtwheelno.setFont(new Font("Dialog", Font.BOLD, 15));
		txtwheelno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// as the key released, we'll set that text in upper case
				txtwheelno.setText(txtwheelno.getText().toUpperCase());
			}
		});
		txtwheelno.setColumns(10);
		txtwheelno.setBounds(329, 174, 253, 26);
		contentPane.add(txtwheelno);

		txtdrivername = new JTextField();
		txtdrivername.setFont(new Font("Dialog", Font.BOLD, 15));
		txtdrivername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// as the key released, we'll set that text in upper case
				txtdrivername.setText(txtdrivername.getText().toUpperCase());
			}
		});
		txtdrivername.setColumns(10);
		txtdrivername.setBounds(329, 220, 253, 26);
		contentPane.add(txtdrivername);

		txtaddress = new JTextField();
		txtaddress.setFont(new Font("Dialog", Font.BOLD, 15));
		txtaddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// as the key released, we'll set that text in upper case
				txtaddress.setText(txtaddress.getText().toUpperCase());
			}
		});
		txtaddress.setColumns(10);
		txtaddress.setBounds(329, 266, 253, 26);
		contentPane.add(txtaddress);

		txtnicnumber = new JTextField();
		txtnicnumber.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnicnumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// as the key released, we'll set that text in upper case
				txtnicnumber.setText(txtnicnumber.getText().toUpperCase());
			}
		});
		txtnicnumber.setColumns(10);
		txtnicnumber.setBounds(329, 312, 253, 26);
		contentPane.add(txtnicnumber);

		txtphonenumber = new JTextField();
		txtphonenumber.setDocument(new CharacterLimit(10));
		txtphonenumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// allow only digits to be entered 
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}

			}
		});
		txtphonenumber.setFont(new Font("Dialog", Font.BOLD, 15));
		txtphonenumber.setColumns(10);
		txtphonenumber.setBounds(329, 358, 253, 26);
		contentPane.add(txtphonenumber);

		JButton btnChoose = new JButton("Choose Image");
		btnChoose.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnChoose.setHorizontalAlignment(SwingConstants.LEFT);
		btnChoose.setFont(new Font("Dialog", Font.BOLD, 15));
		btnChoose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Pick the image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
					// setting selected image's absolute path to txtImgeUrl textfield
					txtImageUrl.setText(selectedFile.getAbsolutePath());
				}

			}
		});
		btnChoose.setBounds(54, 453, 158, 25);
		contentPane.add(btnChoose);

		txtImageUrl = new JTextField();
		txtImageUrl.setFont(new Font("Dialog", Font.BOLD, 15));
		txtImageUrl.setColumns(10);
		txtImageUrl.setBounds(329, 453, 253, 26);
		contentPane.add(txtImageUrl);

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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public MySQLValidatable getDriverValidator() {
		return driverValidator;
	}

	public void setDriverValidator(DriverValidator driverValidator) {
		this.driverValidator = driverValidator;
	}
}
