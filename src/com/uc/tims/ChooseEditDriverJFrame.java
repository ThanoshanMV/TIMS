package com.uc.tims;

import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Driver;
import com.uc.tims.mysql.MySQLQueryMethod;

public class ChooseEditDriverJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtnicnumber;

	private Driver driver;
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
					ChooseEditDriverJFrame frame = new ChooseEditDriverJFrame();
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
	public ChooseEditDriverJFrame() {

		setTitle("Details of driver");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		driver = new Driver();

		mySQLQueryMethod = new MySQLQueryMethod();

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L",
				"M", "N", "P", "Q", "R", "S", "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH" }));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setBounds(224, 112, 157, 33);
		contentPane.add(comboBox);

		JLabel lblNewLabel = new JLabel("Details of driver");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		lblNewLabel.setBounds(104, 41, 223, 33);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Park ");
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNewLabel_1.setBounds(36, 112, 91, 33);
		contentPane.add(lblNewLabel_1);

		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNicNumber.setBounds(36, 177, 108, 24);
		contentPane.add(lblNicNumber);

		txtnicnumber = new JTextField();
		txtnicnumber.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtnicnumber.setText(txtnicnumber.getText().toUpperCase());
			}
		});
		txtnicnumber.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnicnumber.setColumns(10);
		txtnicnumber.setBounds(224, 173, 157, 33);
		contentPane.add(txtnicnumber);

		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnNewButton.setFont(new Font("Dialog", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driver.setPark((String) comboBox.getSelectedItem());
				driver.setNic(txtnicnumber.getText());
				try {

					resultSet = mySQLQueryMethod.findDriverByParkNic(driver);

					System.out.println(resultSet);

					if (resultSet.next()) {
						JOptionPane.showMessageDialog(null, "Driver Connected");
						StaticMembers.park = resultSet.getString("park");
						StaticMembers.parkNo = resultSet.getString("parkno");
						StaticMembers.wheelNo = resultSet.getString("wheelno");
						StaticMembers.driverName = resultSet.getString("name");
						StaticMembers.address = resultSet.getString("address");
						StaticMembers.nic = resultSet.getString("nic");
						StaticMembers.phoneNumber = resultSet.getString("phoneno");
						StaticMembers.gs = resultSet.getString("gs");
						StaticMembers.imageURL = resultSet.getString("imageurl");
						EditAutoDriverFormJFrame editAutoDriverJFrame = new EditAutoDriverFormJFrame();
						editAutoDriverJFrame.setVisible(true);
						editAutoDriverJFrame.setLocationRelativeTo(null);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Check Park No or NIC Number");
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
		btnNewButton.setBounds(267, 241, 114, 33);
		contentPane.add(btnNewButton);

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
		btnBack.setBounds(36, 241, 114, 33);
		contentPane.add(btnBack);

	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

}
