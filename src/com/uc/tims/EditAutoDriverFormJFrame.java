package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Driver;
import com.uc.tims.entity.Payment;
import com.uc.tims.mysql.MySQLConnection;
import com.uc.tims.mysql.MySQLQueryMethod;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class EditAutoDriverFormJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtparkno;
	private JTextField txtwheelno;
	private JTextField txtdrivername;
	private JTextField txtaddress;
	private JTextField txtnic;
	private JTextField txtphonenumber;
	private JTextField txtImageUrl;

	
	private Driver driver;
	private Payment payment;
	private MySQLQueryMethod mySQLQueryMethod;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					EditAutoDriverFormJFrame frame = new EditAutoDriverFormJFrame();
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
	public EditAutoDriverFormJFrame() {

		setTitle("Edit details");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));
		
		// creating driver object
		driver = new Driver();
		
		payment = new Payment();
		
		// create MySQLQueryMethod instance
		mySQLQueryMethod = new MySQLQueryMethod();

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 612, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtImageUrl = new JTextField();
		txtImageUrl.setText((String) null);
		txtImageUrl.setFont(new Font("Dialog", Font.BOLD, 15));
		txtImageUrl.setColumns(10);
		txtImageUrl.setBounds(298, 516, 262, 26);
		contentPane.add(txtImageUrl);
		txtImageUrl.setText(StaticMembers.imageURL);

		JComboBox comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(driver.getAvailableparks()));
		comboBox1.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox1.setBounds(298, 91, 157, 33);
		contentPane.add(comboBox1);
		comboBox1.setSelectedItem(StaticMembers.park);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "YES", "NO" }));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setBounds(298, 467, 133, 24);
		contentPane.add(comboBox);
		if (StaticMembers.gs.equals("YES")) {
			comboBox.setSelectedIndex(0);
		} else {
			comboBox.setSelectedIndex(1);
		}

		JLabel lblThreewheelerEditForm = new JLabel("Three-Wheeler Edit Form");
		lblThreewheelerEditForm.setFont(new Font("Dialog", Font.BOLD, 18));
		lblThreewheelerEditForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblThreewheelerEditForm.setBounds(137, 23, 262, 42);
		contentPane.add(lblThreewheelerEditForm);

		JLabel lblPark = new JLabel("Park");
		lblPark.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPark.setBounds(52, 94, 71, 30);
		contentPane.add(lblPark);

		JLabel lblParkNo = new JLabel("Park No");
		lblParkNo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblParkNo.setBounds(52, 146, 71, 30);
		contentPane.add(lblParkNo);

		JLabel lblWheelNo = new JLabel("Wheel No");
		lblWheelNo.setFont(new Font("Dialog", Font.BOLD, 15));
		lblWheelNo.setBounds(52, 199, 111, 30);
		contentPane.add(lblWheelNo);

		JLabel lblDriverName = new JLabel("Driver Name");
		lblDriverName.setFont(new Font("Dialog", Font.BOLD, 15));
		lblDriverName.setBounds(52, 255, 111, 30);
		contentPane.add(lblDriverName);

		JLabel lblAddress = new JLabel("Address\n");
		lblAddress.setFont(new Font("Dialog", Font.BOLD, 15));
		lblAddress.setBounds(52, 307, 98, 30);
		contentPane.add(lblAddress);

		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNicNumber.setBounds(52, 360, 139, 30);
		contentPane.add(lblNicNumber);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblPhoneNumber.setBounds(52, 412, 139, 30);
		contentPane.add(lblPhoneNumber);

		JLabel lblGs = new JLabel("GS Certification");
		lblGs.setFont(new Font("Dialog", Font.BOLD, 15));
		lblGs.setBounds(52, 464, 139, 30);
		contentPane.add(lblGs);

		txtparkno = new JTextField();
		txtparkno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtparkno.setText(txtparkno.getText().toUpperCase());
			}
		});
		txtparkno.setFont(new Font("Dialog", Font.BOLD, 15));
		txtparkno.setColumns(10);
		txtparkno.setBounds(298, 148, 262, 26);
		contentPane.add(txtparkno);
		txtparkno.setText(StaticMembers.parkNo);

		txtwheelno = new JTextField();
		txtwheelno.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtwheelno.setText(txtwheelno.getText().toUpperCase());
			}
		});
		txtwheelno.setFont(new Font("Dialog", Font.BOLD, 15));
		txtwheelno.setColumns(10);
		txtwheelno.setBounds(298, 201, 262, 26);
		contentPane.add(txtwheelno);
		txtwheelno.setText(StaticMembers.wheelNo);

		txtdrivername = new JTextField();
		txtdrivername.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtdrivername.setText(txtdrivername.getText().toUpperCase());
			}
		});
		txtdrivername.setFont(new Font("Dialog", Font.BOLD, 15));
		txtdrivername.setColumns(10);
		txtdrivername.setBounds(298, 257, 262, 26);
		contentPane.add(txtdrivername);
		txtdrivername.setText(StaticMembers.driverName);

		txtaddress = new JTextField();
		txtaddress.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtaddress.setText(txtaddress.getText().toUpperCase());
			}
		});
		txtaddress.setFont(new Font("Dialog", Font.BOLD, 15));
		txtaddress.setColumns(10);
		txtaddress.setBounds(298, 309, 262, 26);
		contentPane.add(txtaddress);
		txtaddress.setText(StaticMembers.address);

		txtnic = new JTextField();
		txtnic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtnic.setText(txtnic.getText().toUpperCase());
			}
		});
		txtnic.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnic.setColumns(10);
		txtnic.setBounds(298, 362, 262, 26);
		contentPane.add(txtnic);
		txtnic.setText(StaticMembers.nic);

		txtphonenumber = new JTextField();
		txtphonenumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				// avoid non-digits 
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();

				}
			}
		});
		txtphonenumber.setFont(new Font("Dialog", Font.BOLD, 15));
		txtphonenumber.setColumns(10);
		txtphonenumber.setBounds(298, 414, 262, 26);
		contentPane.add(txtphonenumber);
		txtphonenumber.setText(StaticMembers.phoneNumber);

		JButton btnSave = new JButton("Save");
		btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnSave.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driver.setPark(comboBox1.getSelectedItem().toString());
				driver.setParkNumber(txtparkno.getText());
				driver.setWheelNumber(txtwheelno.getText());
				driver.setName(txtdrivername.getText());
				driver.setAddress(txtaddress.getText());
				driver.setNic(txtnic.getText());
				driver.setPhoneNumber(txtphonenumber.getText());
				driver.setGsDecision(comboBox.getSelectedItem().toString());
				driver.setImageUrl(txtImageUrl.getText());
				driver.setImage(driver.readImageFile(driver.getImageUrl()));
				
				payment.setName(driver.getName());
				payment.setNic(driver.getNic());
				payment.setPark(driver.getPark());
				
				if (driver.getParkNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid park no!");
				} else if (driver.getWheelNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid wheel no!");
				} else if (driver.getName().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver name!");
				} else if (driver.getAddress().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid address!");
				} else if (driver.getNic().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid nic number!");
				} else if (!((driver.getNic().length() == 10) || (driver.getNic().length() == 12))) {
					JOptionPane.showMessageDialog(null, "Sorry, Check NIC length!");
				} else if (driver.getPhoneNumber().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid phone number!");
				} else if (!(driver.getPhoneNumber().length() == 10)) {
					JOptionPane.showMessageDialog(null, "Phone number should be in 10 characters!");
				} else if (driver.getImageUrl().equals("")) {
					JOptionPane.showMessageDialog(null, "Please add a valid driver image");
				} else {

					int updateDriver = mySQLQueryMethod.updateDriver(driver);
					int updatePayment = mySQLQueryMethod.updatePayment(payment, StaticMembers.nic);
					
						if (updateDriver > 0 && updatePayment > 0) {
							JOptionPane.showMessageDialog(null, "Successfully Saved!");
							DashboardJFrame dashboardJFrame = new DashboardJFrame();
							dashboardJFrame.setVisible(true);
							dashboardJFrame.setLocationRelativeTo(null);
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "Updation failed. Check entered details or Try again.");
						}

				}
			}
		});
		btnSave.setBounds(449, 585, 111, 37);
		contentPane.add(btnSave);

		JButton btnBack = new JButton("Back");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFont(new Font("Dialog", Font.BOLD, 15));
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				dashboardJFrame.setVisible(true);
				dashboardJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		btnBack.setBounds(60, 585, 114, 37);
		contentPane.add(btnBack);

		JButton btnUpdate = new JButton("Choose Image");
		btnUpdate.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnUpdate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				EditAutoDriverFormJFrame object = new EditAutoDriverFormJFrame();
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
		btnUpdate.setHorizontalAlignment(SwingConstants.LEFT);
		btnUpdate.setFont(new Font("Dialog", Font.BOLD, 15));
		btnUpdate.setBounds(52, 517, 158, 25);
		contentPane.add(btnUpdate);

	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
}
