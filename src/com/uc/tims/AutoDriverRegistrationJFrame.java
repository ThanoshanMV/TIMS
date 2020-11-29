package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Driver;

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

	
	private Driver driver; 
	private Connection connection; 
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;

	private String fileName = null;
	private int s = 0;
	private byte[] personImage = null;
	private ImageIcon format = null;
	private JTextField txtImageUrl;

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
		driver = new Driver();
		
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
				} else if (DriverExistenceTIMS.isParkNoExist(driver.getParkNumber())) {
					JOptionPane.showMessageDialog(null, "Sorry, Park Number has already taken!");
				}
				else if (driver.getWheelNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid wheel no!");
				}
				else if (DriverExistenceTIMS.isWheelNO(driver.getWheelNumber())) {
					JOptionPane.showMessageDialog(null, "Sorry, Wheel Number has already taken!");
				} else if (driver.getName().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver name!");
				} else if (DriverExistenceTIMS.isName(driver.getName())) {
					JOptionPane.showMessageDialog(null, "Sorry, Driver Name has already taken!");
				} else if (driver.getAddress().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid address!");
				} else if (driver.getNic().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid nic number!");
				} else if (DriverExistenceTIMS.isNIC(driver.getNic())) {
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
					PreparedStatement ps = null;
					try {
						ps = SqliteConnection.establishSqliteConnection()
								.prepareStatement(StaticMembers.sqlQueryForDriverRegistration);
						ps.setString(1, driver.getPark());
						ps.setString(2, driver.getParkNumber());
						ps.setString(3, driver.getWheelNumber());
						ps.setString(4, driver.getName());
						ps.setString(5, driver.getAddress());
						ps.setString(6, driver.getNic());
						ps.setString(7, driver.getPhoneNumber());
						ps.setString(8, driver.getGsDecision());
						ps.setBytes(9, readFile(driver.getImageUrl()));
						ps.setString(10, driver.getImageUrl());

						if (ps.executeUpdate() > 0) {
							JOptionPane.showMessageDialog(null, "Auto Driver Registration Successful!");
							AutoDriverRegistrationJFrame obj = new AutoDriverRegistrationJFrame();
							obj.setVisible(true);
							obj.setLocationRelativeTo(null);
							SqliteConnection.insertPaymentRow(driver.getPark(), driver.getName(),
									driver.getNic());
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
		btnRegister.setBounds(468, 521, 114, 34);
		contentPane.add(btnRegister);

		JButton btnCancel = new JButton("Back");
		btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCancel.setFont(new Font("Dialog", Font.BOLD, 15));
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				dashboardJFrame.setVisible(true);
				dashboardJFrame.setLocationRelativeTo(null);
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
				AutoDriverRegistrationJFrame object = new AutoDriverRegistrationJFrame();
				JFileChooser chooser = new JFileChooser();
				chooser.setDialogTitle("Pick the image");
				chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
				int response = chooser.showOpenDialog(null);
				if (response == JFileChooser.APPROVE_OPTION) {
					File selectedFile = chooser.getSelectedFile();
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

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public byte[] getPersonImage() {
		return personImage;
	}

	public void setPersonImage(byte[] personImage) {
		this.personImage = personImage;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	static private byte[] readFile(String file) {
		ByteArrayOutputStream bos = null;
		try {
			File f = new File(file);
			FileInputStream fis = new FileInputStream(f);
			byte[] buffer = new byte[1024];
			bos = new ByteArrayOutputStream();
			for (int len; (len = fis.read(buffer)) != -1;) {
				bos.write(buffer, 0, len);
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e2) {
			System.err.println(e2.getMessage());
		}
		return bos != null ? bos.toByteArray() : null;
	}
}
