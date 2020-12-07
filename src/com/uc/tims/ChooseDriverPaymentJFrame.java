package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Driver;
import com.uc.tims.mysql.MySQLConnection;
import com.uc.tims.mysql.MySQLQueryMethod;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChooseDriverPaymentJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtnic;
	
	private Driver driver;
	private ResultSet resultSet = null;
	
	private MySQLQueryMethod mySQLQueryMethod;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					ChooseDriverPaymentJFrame frame = new ChooseDriverPaymentJFrame();
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
	public ChooseDriverPaymentJFrame() {

		driver = new Driver();
		
		mySQLQueryMethod = new MySQLQueryMethod();

		
		setTitle("Details of driver");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 432, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Details of driver");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		label.setBounds(110, 47, 223, 33);
		contentPane.add(label);

		JLabel label_1 = new JLabel("Park ");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.setBounds(44, 109, 91, 33);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("NIC Number");
		label_2.setFont(new Font("Dialog", Font.BOLD, 15));
		label_2.setBounds(44, 172, 108, 24);
		contentPane.add(label_2);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "DA", "DB", "DC", "DD", "DE", "DF", "DG", "DH"}));
		comboBox.setFont(new Font("Dialog", Font.BOLD, 15));
		comboBox.setBounds(218, 109, 157, 33);
		contentPane.add(comboBox);

		txtnic = new JTextField();
		txtnic.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txtnic.setText(txtnic.getText().toUpperCase());
			}
		});
		txtnic.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnic.setColumns(10);
		txtnic.setBounds(218, 163, 157, 33);
		contentPane.add(txtnic);

		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DashboardJFrame dashboardJFrame = new DashboardJFrame();
				dashboardJFrame.setVisible(true);
				dashboardJFrame.setLocationRelativeTo(null);
				dispose();
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 15));
		button.setBounds(44, 237, 114, 33);
		contentPane.add(button);

		JButton btnPayment = new JButton("Add Payment");
		btnPayment.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				driver.setPark((String) comboBox.getSelectedItem());
				driver.setNic(txtnic.getText());

				try {

					resultSet = mySQLQueryMethod.findPaymentByParkNic(driver);


					if (resultSet.next()) {
						JOptionPane.showMessageDialog(null, "Driver Connected");
						//////// Get all values from PAYMENT table.
						StaticMembers.paymentDriverName = resultSet.getString("name");
						StaticMembers.paymentNIC = resultSet.getString("nic");
						StaticMembers.y13 = resultSet.getDouble("year2013");
						StaticMembers.y14 = resultSet.getDouble("year2014");
						StaticMembers.y15 = resultSet.getDouble("year2015");
						StaticMembers.y16 = resultSet.getDouble("year2016");
						StaticMembers.y17 = resultSet.getDouble("year2017");
						StaticMembers.y18 = resultSet.getDouble("year2018");
						StaticMembers.y19 = resultSet.getDouble("year2019");
						StaticMembers.y20 = resultSet.getDouble("year2020");
						StaticMembers.y21 = resultSet.getDouble("year2021");
						StaticMembers.y22 = resultSet.getDouble("year2022");
						StaticMembers.total = resultSet.getDouble("totalpayment");

						PaymentAddJFrame object = new PaymentAddJFrame();
						object.setVisible(true);
						object.setLocationRelativeTo(null);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Check Park or NIC Number");
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
		btnPayment.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPayment.setBounds(227, 237, 148, 33);
		contentPane.add(btnPayment);
	}
}
