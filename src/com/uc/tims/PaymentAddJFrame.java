package com.uc.tims;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.uc.tims.entity.Payment;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PaymentAddJFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtname;
	private JTextField txtnic;
	private JTextField txt13;
	private JTextField txt15;
	private JTextField txt17;
	private JTextField txt19;
	private JTextField txt21;
	private JTextField txttotal;
	private JTextField txt14;
	private JTextField txt16;
	private JTextField txt18;
	private JTextField txt20;
	private JTextField txt22;
	
	private Payment payment;
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
					PaymentAddJFrame frame = new PaymentAddJFrame();
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
	public PaymentAddJFrame() {

		payment = new Payment();
		
		setTitle("Payment history");
		setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/tims.png")));

		setResizable(false);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 635);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblPaymentHistory = new JLabel("Payment History");
		lblPaymentHistory.setHorizontalAlignment(SwingConstants.CENTER);
		lblPaymentHistory.setFont(new Font("Dialog", Font.BOLD, 18));
		lblPaymentHistory.setBounds(168, 12, 207, 42);
		contentPane.add(lblPaymentHistory);

		JLabel label = new JLabel("Driver Name");
		label.setFont(new Font("Dialog", Font.BOLD, 15));
		label.setBounds(46, 97, 111, 30);
		contentPane.add(label);

		JLabel lblNicNumber = new JLabel("NIC Number");
		lblNicNumber.setFont(new Font("Dialog", Font.BOLD, 15));
		lblNicNumber.setBounds(46, 150, 111, 30);
		contentPane.add(lblNicNumber);

		JLabel label_2 = new JLabel("2013");
		label_2.setFont(new Font("Dialog", Font.BOLD, 15));
		label_2.setBounds(46, 197, 50, 30);
		contentPane.add(label_2);

		JLabel label_1 = new JLabel("2015");
		label_1.setFont(new Font("Dialog", Font.BOLD, 15));
		label_1.setBounds(46, 249, 56, 30);
		contentPane.add(label_1);

		JLabel label_3 = new JLabel("2017");
		label_3.setFont(new Font("Dialog", Font.BOLD, 15));
		label_3.setBounds(46, 304, 56, 30);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("2019");
		label_4.setFont(new Font("Dialog", Font.BOLD, 15));
		label_4.setBounds(46, 356, 56, 30);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("2021");
		label_5.setFont(new Font("Dialog", Font.BOLD, 15));
		label_5.setBounds(46, 411, 56, 30);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("2014");
		label_6.setFont(new Font("Dialog", Font.BOLD, 15));
		label_6.setBounds(333, 197, 56, 30);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("2016");
		label_7.setFont(new Font("Dialog", Font.BOLD, 15));
		label_7.setBounds(333, 249, 45, 30);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("2018");
		label_8.setFont(new Font("Dialog", Font.BOLD, 15));
		label_8.setBounds(333, 304, 56, 30);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("2020");
		label_9.setFont(new Font("Dialog", Font.BOLD, 15));
		label_9.setBounds(333, 356, 56, 30);
		contentPane.add(label_9);

		JLabel lblTotal_1 = new JLabel("Total");
		lblTotal_1.setFont(new Font("Dialog", Font.BOLD, 20));
		lblTotal_1.setBounds(46, 479, 67, 30);
		contentPane.add(lblTotal_1);

		JLabel lblTotal = new JLabel("2022");
		lblTotal.setFont(new Font("Dialog", Font.BOLD, 15));
		lblTotal.setBounds(333, 411, 50, 30);
		contentPane.add(lblTotal);

		txtname = new JTextField();
		txtname.setText((String) null);
		txtname.setFont(new Font("Dialog", Font.BOLD, 15));
		txtname.setColumns(10);
		txtname.setBounds(211, 97, 262, 26);
		contentPane.add(txtname);
		txtname.setText(StaticMembers.paymentDriverName);
		txtname.setEditable(false);

		txtnic = new JTextField();
		txtnic.setText((String) null);
		txtnic.setFont(new Font("Dialog", Font.BOLD, 15));
		txtnic.setColumns(10);
		txtnic.setBounds(211, 150, 262, 26);
		contentPane.add(txtnic);
		txtnic.setText(StaticMembers.paymentNIC);
		txtnic.setEditable(false);

		txt13 = new JTextField();
		txt13.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt13.setText((String) null);
		txt13.setFont(new Font("Dialog", Font.BOLD, 15));
		txt13.setColumns(10);
		txt13.setBounds(139, 203, 123, 26);
		contentPane.add(txt13);
		txt13.setText(Double.toString(StaticMembers.y13));

		txt15 = new JTextField();
		txt15.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt15.setText((String) null);
		txt15.setFont(new Font("Dialog", Font.BOLD, 15));
		txt15.setColumns(10);
		txt15.setBounds(139, 255, 123, 26);
		contentPane.add(txt15);
		txt15.setText(Double.toString(StaticMembers.y15));

		txt17 = new JTextField();
		txt17.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt17.setText((String) null);
		txt17.setFont(new Font("Dialog", Font.BOLD, 15));
		txt17.setColumns(10);
		txt17.setBounds(139, 310, 123, 26);
		contentPane.add(txt17);
		txt17.setText(Double.toString(StaticMembers.y17));

		txt19 = new JTextField();
		txt19.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt19.setText((String) null);
		txt19.setFont(new Font("Dialog", Font.BOLD, 15));
		txt19.setColumns(10);
		txt19.setBounds(139, 362, 123, 26);
		contentPane.add(txt19);
		txt19.setText(Double.toString(StaticMembers.y19));

		txt21 = new JTextField();
		txt21.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt21.setText((String) null);
		txt21.setFont(new Font("Dialog", Font.BOLD, 15));
		txt21.setColumns(10);
		txt21.setBounds(139, 417, 123, 26);
		contentPane.add(txt21);
		txt21.setText(Double.toString(StaticMembers.y21));

		txttotal = new JTextField();
		txttotal.setHorizontalAlignment(SwingConstants.CENTER);
		txttotal.setText((String) null);
		txttotal.setFont(new Font("Dialog", Font.BOLD, 20));
		txttotal.setColumns(10);
		txttotal.setBounds(139, 482, 162, 26);
		contentPane.add(txttotal);
		txttotal.setText(Double.toString(StaticMembers.total));
		txttotal.setEditable(false);

		txt14 = new JTextField();
		txt14.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt14.setText((String) null);
		txt14.setFont(new Font("Dialog", Font.BOLD, 15));
		txt14.setColumns(10);
		txt14.setBounds(413, 197, 123, 26);
		contentPane.add(txt14);
		txt14.setText(Double.toString(StaticMembers.y14));

		txt16 = new JTextField();
		txt16.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt16.setText((String) null);
		txt16.setFont(new Font("Dialog", Font.BOLD, 15));
		txt16.setColumns(10);
		txt16.setBounds(413, 255, 123, 26);
		contentPane.add(txt16);
		txt16.setText(Double.toString(StaticMembers.y16));

		txt18 = new JTextField();
		txt18.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt18.setText((String) null);
		txt18.setFont(new Font("Dialog", Font.BOLD, 15));
		txt18.setColumns(10);
		txt18.setBounds(413, 310, 123, 26);
		contentPane.add(txt18);
		txt18.setText(Double.toString(StaticMembers.y18));

		txt20 = new JTextField();
		txt20.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt20.setText((String) null);
		txt20.setFont(new Font("Dialog", Font.BOLD, 15));
		txt20.setColumns(10);
		txt20.setBounds(413, 362, 123, 26);
		contentPane.add(txt20);
		txt20.setText(Double.toString(StaticMembers.y20));

		txt22 = new JTextField();
		txt22.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				txttotal.setText(payment.calculateTotalPayment(txt13.getText(), txt14.getText(), txt15.getText(), txt16.getText(),
						txt17.getText(), txt18.getText(), txt19.getText(), txt20.getText(), txt21.getText(),
						txt22.getText()));
			}

			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE)
						|| c == KeyEvent.VK_PERIOD)) {
					e.consume();
				}
			}
		});
		txt22.setText((String) null);
		txt22.setFont(new Font("Dialog", Font.BOLD, 15));
		txt22.setColumns(10);
		txt22.setBounds(413, 417, 123, 26);
		contentPane.add(txt22);
		txt22.setText(Double.toString(StaticMembers.y22));

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
		btnBack.setBounds(46, 536, 114, 37);
		contentPane.add(btnBack);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					
					connection = MySQLConnection.establishMySqlConnection();
					
					String sqlQueryForUpdatePaymentDetails = "UPDATE `payment` SET `year2013`= ?,`year2014`= ? ,`year2015`= ? ,`year2016`= ? ,`year2017`=  ? ,`year2018`= ? ,`year2019`= ? ,`year2020`= ? ,`year2021`= ? ,`year2022` = ?, `totalpayment` = ? WHERE `name`= ?";
					preparedStatement = connection.prepareStatement(sqlQueryForUpdatePaymentDetails);

					preparedStatement.setDouble(1, payment.convertToDouble(txt13.getText()));
					preparedStatement.setDouble(2, payment.convertToDouble(txt14.getText()));
					preparedStatement.setDouble(3, payment.convertToDouble(txt15.getText()));
					preparedStatement.setDouble(4, payment.convertToDouble(txt16.getText()));
					preparedStatement.setDouble(5, payment.convertToDouble(txt17.getText()));
					preparedStatement.setDouble(6, payment.convertToDouble(txt18.getText()));
					preparedStatement.setDouble(7, payment.convertToDouble(txt19.getText()));
					preparedStatement.setDouble(8, payment.convertToDouble(txt20.getText()));
					preparedStatement.setDouble(9, payment.convertToDouble(txt21.getText()));
					preparedStatement.setDouble(10, payment.convertToDouble(txt22.getText()));
					preparedStatement.setDouble(11, payment.convertToDouble(txttotal.getText()));
					preparedStatement.setString(12, txtname.getText());

					System.out.println(sqlQueryForUpdatePaymentDetails);

					if (preparedStatement.executeUpdate() > 0) {
						JOptionPane.showMessageDialog(null, "Successfully Saved!");
						DashboardJFrame dashboardJFrame = new DashboardJFrame();
						dashboardJFrame.setVisible(true);
						dashboardJFrame.setLocationRelativeTo(null);
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Updation failed. Check entered details or Try again.");
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
		});
		btnSave.setFont(new Font("Dialog", Font.BOLD, 15));
		btnSave.setBounds(422, 536, 114, 37);
		contentPane.add(btnSave);
	}

}
