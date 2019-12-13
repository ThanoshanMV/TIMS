package com.uc.package1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ChooseDriverPaymentJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtnic;
	private String park;
	private String nic;

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
				ChooseDriverPaymentJFrame obj = new ChooseDriverPaymentJFrame();
				obj.setPark((String) comboBox.getSelectedItem());
				obj.setNic(txtnic.getText());
				PreparedStatement ps = null;
				ResultSet rs = null;

				try {
					String query = "SELECT * FROM `PAYMENT` WHERE `PARK`= ? AND `NIC NUMBER`= ?";
					ps = SqliteConnection.establishSqliteConnection().prepareStatement(query);
					ps.setString(1, obj.getPark());
					ps.setString(2, obj.getNic());
					rs = ps.executeQuery();

					if (rs.next()) {
						JOptionPane.showMessageDialog(null, "Driver Connected");
						//////// Get all values from PAYMENT table.
						StaticMembers.paymentDriverName = rs.getString("NAME");
						StaticMembers.paymentNIC = rs.getString("NIC NUMBER");
						StaticMembers.y13 = rs.getDouble("2013");
						StaticMembers.y14 = rs.getDouble("2014");
						StaticMembers.y15 = rs.getDouble("2015");
						StaticMembers.y16 = rs.getDouble("2016");
						StaticMembers.y17 = rs.getDouble("2017");
						StaticMembers.y18 = rs.getDouble("2018");
						StaticMembers.y19 = rs.getDouble("2019");
						StaticMembers.y20 = rs.getDouble("2020");
						StaticMembers.y21 = rs.getDouble("2021");
						StaticMembers.y22 = rs.getDouble("2022");
						StaticMembers.total = rs.getDouble("TOTAL");

						PaymentAddJFrame object = new PaymentAddJFrame();
						object.setVisible(true);
						object.setLocationRelativeTo(null);
						dispose();

					} else {
						JOptionPane.showMessageDialog(null, "Check Park or NIC Number");
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
		btnPayment.setFont(new Font("Dialog", Font.BOLD, 15));
		btnPayment.setBounds(227, 237, 148, 33);
		contentPane.add(btnPayment);
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getNic() {
		return nic;
	}

	public void setNic(String nic) {
		this.nic = nic;
	}

	/*
	 * static void selectPaymentRow() { PreparedStatement pst = null; ResultSet rst
	 * = null; try { String query1 = "SELECT * FROM `PAYMENT` WHERE `NAME`= ?"; pst
	 * = SqliteConnection.establishSqliteConnection().prepareStatement(query1);
	 * pst.setString(1, StaticMembers.paymentDriverName); rst = pst.executeQuery();
	 * if(rst.next()) { JOptionPane.showMessageDialog(null,
	 * "Payment history exists"); StaticMembers.y13 = rs.getDouble("2013");
	 * StaticMembers.y14 = rs.getDouble("2014"); StaticMembers.y15 =
	 * rs.getDouble("2015"); StaticMembers.y16 = rs.getDouble("2016");
	 * StaticMembers.y17 = rs.getDouble("2017"); StaticMembers.y18 =
	 * rs.getDouble("2018"); StaticMembers.y19 = rs.getDouble("2019");
	 * StaticMembers.y20 = rs.getDouble("2020"); StaticMembers.y21 =
	 * rs.getDouble("2021"); StaticMembers.y22 = rs.getDouble("2022");
	 * StaticMembers.total = rs.getDouble("TOTAL");
	 * 
	 * PaymentAddJFrame object = new PaymentAddJFrame(); object.setVisible(true);
	 * object.setLocationRelativeTo(null); dispose(); } else {
	 * insertPaymentRow(obj.getPark()); dispose(); }
	 * 
	 * } catch (SQLException e1) { // TODO Auto-generated catch block
	 * JOptionPane.showMessageDialog(null, "Error while establishing connection.");
	 * } finally { try { pst.close(); } catch (SQLException e1) { // TODO
	 * Auto-generated catch block e1.printStackTrace(); } try { rst.close(); } catch
	 * (SQLException e1) { // TODO Auto-generated catch block e1.printStackTrace();
	 * } } }
	 */
}
