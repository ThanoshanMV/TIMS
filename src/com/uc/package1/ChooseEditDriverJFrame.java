package com.uc.package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Cursor;

public class ChooseEditDriverJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtnicnumber;
	private String park;
	private String nicnumber;

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

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 434, 353);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "DA", "DB", "DC"}));
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
				ChooseEditDriverJFrame obj = new ChooseEditDriverJFrame();
				obj.setPark((String) comboBox.getSelectedItem());
				obj.setNicnumber(txtnicnumber.getText());

				PreparedStatement ps = null;
				ResultSet rs = null;

				try {

					ps = SqliteConnection.establishSqliteConnection()
							.prepareStatement(StaticMembers.sqlQueryForChooseDriverToEdit);
					ps.setString(1, obj.getPark());
					ps.setString(2, obj.getNicnumber());

					rs = ps.executeQuery();

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Driver Connected");
						StaticMembers.park = rs.getString("PARK");
						StaticMembers.parkNo = rs.getString("PARK NO");
						StaticMembers.wheelNo = rs.getString("WHEEL NO");
						StaticMembers.driverName = rs.getString("DRIVER NAME");
						StaticMembers.address = rs.getString("ADDRESS");
						StaticMembers.nic = rs.getString("NIC NUMBER");
						StaticMembers.phoneNumber = rs.getString("PHONE NUMBER");
						StaticMembers.gs = rs.getString("GS");
						StaticMembers.imageURL = rs.getString("IMAGEURL");
						EditAutoDriverFormJFrame editAutoDriverJFrame = new EditAutoDriverFormJFrame();
						editAutoDriverJFrame.setVisible(true);
						editAutoDriverJFrame.setLocationRelativeTo(null);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Check Park No or NIC Number");
					}
				}

				catch (SQLException e1) {
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
						rs.close();
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

	public String getNicnumber() {
		return nicnumber;
	}

	public void setNicnumber(String nicnumber) {
		this.nicnumber = nicnumber;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

}
